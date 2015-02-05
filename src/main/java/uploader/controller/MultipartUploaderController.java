package uploader.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Controller
public class MultipartUploaderController {
    private static final Logger log = LoggerFactory.getLogger(MultipartUploaderController.class);

    @Autowired
    ServletContext servletContext;

    @RequestMapping(value="/upload", method= RequestMethod.POST)
    @ResponseBody
    public void fileUpload(@RequestParam("description") Optional<String> description, @RequestParam("file") MultipartFile file) throws IOException {
        log.info(description.toString());
        log.info(file.getOriginalFilename());
        log.info(file.getContentType());
        log.info("" + file.getSize());

        final File dest = new File(servletContext.getRealPath("/"), file.getOriginalFilename());
        log.info(dest.getAbsolutePath());

        file.transferTo(dest);
    }

    @RequestMapping(value="/file/{name:.*}", method= RequestMethod.GET)
    @ResponseBody
    public FileSystemResource fileDownload(@PathVariable("name") String name, HttpServletResponse response) {
        final File source = new File(servletContext.getRealPath("/"), name);
        if (!source.exists()) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return null;
        }

        log.info("downloading " + source.getAbsolutePath());
        return new FileSystemResource(source);
    }
}

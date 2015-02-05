package uploader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) throws MalformedURLException, UnsupportedEncodingException {
        SpringApplication.run(Application.class, args);
    }
}

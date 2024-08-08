package org.example.projectboard1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class ProjectBoard1Application {

    public static void main(String[] args) {
        SpringApplication.run(ProjectBoard1Application.class, args);
    }

}

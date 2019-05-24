package com.matija.softtehn;

import com.matija.softtehn.properties.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class SofttehnApplication {

	public static void main(String[] args) {
		ApplicationContext app = SpringApplication.run(SofttehnApplication.class, args);
	}

}


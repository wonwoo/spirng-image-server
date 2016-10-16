package me.wonwoo;

import me.wonwoo.file.storage.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class SpirngImageServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpirngImageServerApplication.class, args);
	}
}

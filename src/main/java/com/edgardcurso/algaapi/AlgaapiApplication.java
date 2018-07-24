package com.edgardcurso.algaapi;

import com.edgardcurso.algaapi.config.property.ApiProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties(ApiProperty.class)
public class AlgaapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlgaapiApplication.class, args);
	}
}

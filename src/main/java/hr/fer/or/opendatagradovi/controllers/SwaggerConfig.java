package hr.fer.or.opendatagradovi.controllers;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;


import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	
	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))              
          .paths(PathSelectors.any())                          
          .build()
          .apiInfo(apiInfo());                                           
    }
	
	private ApiInfo apiInfo() {
		return new ApiInfo("Api Documentation",
							"Api Documentation for Cities and Districts", 
							"1.0", 
							null, 
							new Contact("Luka Lacković", null, "luka.lackovic@fer.hr"), 
							"Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", 
							Collections.emptyList());
		
	}

}

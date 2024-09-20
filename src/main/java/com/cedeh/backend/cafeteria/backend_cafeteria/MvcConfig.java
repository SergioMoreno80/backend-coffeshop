package com.cedeh.backend.cafeteria.backend_cafeteria;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer{

	  @Value("${spring.web.resources.static-locations}")
	    private String imageBasePath;
	  
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		WebMvcConfigurer.super.addResourceHandlers(registry);
		//toma el valor desde applications Propperties
		registry.addResourceHandler("/imagenes/**").
		addResourceLocations(imageBasePath);
//		addResourceLocations("file:/Users/sergiomoreno/Documents/imagenes_activos/");
		//addResourceLocations("file:/home/ec2-user/imagenes_activos/");
		registry.addResourceHandler("/documentos/**")
        .addResourceLocations("file:/home/ec2-user/doc_activos/")
		.addResourceLocations("file:/Users/sergiomoreno/Documents/doc_activos/");

		
	}

	

}

package io.getarrays.server;

import java.util.Arrays;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.SpringVersion;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import io.getarrays.server.model.Server;
import io.getarrays.server.model.Status;
import io.getarrays.server.repository.ServerRepository;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}
	
	/*
	
	@Bean
	CommandLineRunner run(ServerRepository serverRepository) {
		
		System.out.println("Spring version is: "+SpringVersion.getVersion());
		
		return args->{
			serverRepository.save(new Server(null, "192.168.1.1", "Ubuntu Linux", "16 GB", "Personal PC", "http://localhost:8080/server/image/server1.png",Status.SERVER_UP));
			serverRepository.save(new Server(null, "192.168.1.58", "Fedora Linux", "36 GB", "Dell PC", "http://localhost:8080/server/image/server2.png",Status.SERVER_DOWN));
			serverRepository.save(new Server(null, "192.168.1.21", "MS 2008", "8 GB", "Web server", "http://localhost:8080/server/image/server3.png",Status.SERVER_DOWN));
			serverRepository.save(new Server(null, "192.168.1.31", "Red Hat Enterprise Linux", "64 GB", "Mail server", "http://localhost:8080/server/image/server4.png",Status.SERVER_UP));
		};
	}
	
	*/
	
		@Bean
    	public CorsFilter corsFilter() {
			
			CorsConfiguration config = new CorsConfiguration();
			config.setAllowCredentials(true);
		    config.setAllowedOrigins(Arrays.asList("http://localhost:3000","http://localhost:4200")); // Allow all origins
		    config.setAllowedHeaders(Arrays.asList("Origin","Access-Control-Allow-Origin","Content-Type","Accept","Jwt-Token","Authorization","Origin, Accept", "X-Requested-With", "Access-Control-Request-Method", "Access-Control-Request-Headers")); // Allow all headers
			config.setExposedHeaders(Arrays.asList("Origin","Access-Control-Allow-Origin","Content-Type","Accept","Jwt-Token","Authorization","Access-Control-Allow-Credentials", "Filename"));
		    config.setAllowedMethods(Arrays.asList("GET", "PUT", "POST" ,"PATCH", "DELETE", "OPTIONS")); // Allow all HTTP methods
		    
		    
		    UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
		    source.registerCorsConfiguration("/**", config);
		    return new CorsFilter(source);
		    
		    
    }
    

}

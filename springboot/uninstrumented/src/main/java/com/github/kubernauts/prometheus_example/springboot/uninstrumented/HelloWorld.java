// Define code's package
package com.github.kubernauts.prometheus_example.springboot.uninstrumented;

// import the default Spring Boot packages
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

// Declare the Standard Spring boot annotation for instantiation
@SpringBootApplication
// Add route annotations below
@RestController

// Main application class
public class HelloWorld {

	@RequestMapping("/")
	String home() {
		return "Hello World!";
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(HelloWorld.class, args);
	}

}

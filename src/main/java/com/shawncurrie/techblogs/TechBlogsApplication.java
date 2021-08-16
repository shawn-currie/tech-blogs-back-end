package com.shawncurrie.techblogs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TechBlogsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechBlogsApplication.class, args);
	}

}

/* TODO
How do I want to handle sending the company logo
How do I want to get company and blog details
	would this take two db calls or a join?

Add favorites
add accounts
add security
	set cors to something more specific
consider having a company page where you can see all the companies and the blogs they posted
 */
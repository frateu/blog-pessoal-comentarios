package br.com.frateu.blogcomentario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BlogcomentarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogcomentarioApplication.class, args);
	}

}

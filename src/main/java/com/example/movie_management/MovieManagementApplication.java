package com.example.movie_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

import org.apache.http.HttpException;
import java.io.IOException;
import java.util.Scanner;

@SpringBootApplication
public class MovieManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieManagementApplication.class, args);
	}

}

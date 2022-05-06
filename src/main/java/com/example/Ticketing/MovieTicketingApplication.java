package com.example.ticketing;

import com.example.ticketing.model.Movie;
import com.example.ticketing.repository.MovieRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.awt.print.Book;
import java.time.Duration;
import java.util.Optional;


//@ComponentScan(basePackages="main")
@SpringBootApplication
public class MovieTicketingApplication {
//	public MovieTicketingApplication(MovieRepository movieRepository) {
//		this.movieRepository = movieRepository;
//	}

	public static void main(String[] args) {
		SpringApplication.run(MovieTicketingApplication.class, args);
	}
//	@Bean
//	public JavaMailSender getJavaMailSender() {
//		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//		mailSender.setHost("smtp.gmail.com");
//		mailSender.setPort(587);
//		mailSender.setUsername("teresiawachirakabura1@gmail.com");
//		mailSender.setPassword("tiejytriksi411");
//
//		Properties props = mailSender.getJavaMailProperties();
//		props.put("mail.transport.protocol", "smtp");
//		props.put("mail.smtp.auth", "true");
//		props.put("mail.smtp.starttls.enable", "true");
//		props.put("mail.debug", "true");
//
//		return mailSender;
//	}
//	public final MovieRepository movieRepository;
//@PostConstruct
//public void initMovie() {
//	Movie movie = new Movie();
//	movie.setName("Spider Man");
//	movie.setType("Documentary");
//	movie.setLanguage("English");
//	movie.setCast("Grace");
//	movie.setDuration(Duration.ofHours(3));
//	movie.setPlot("The highest Jump Ever Heard");
//	movie.setImageUrl("https://www.pexels.com/photo/boy-wearing-a-red-costume-8421972/");
//	movie.setMinAge(7);
//	movieRepository.save(movie);
//
//
//	movie = new Movie();
//	movie.setType("Christian-based");
//	movie.setName("God With Us");
//	movie.setLanguage("Spanish");
//	movie.setCast("CLJ Ministry");
//	movie.setDuration(Duration.ofHours(2));
//	movie.setPlot("Daniel in the Lion's Den");
//	movie.setImageUrl("https://www.pexels.com/photo/boy-wearing-a-red-costume-8421972/");
//	movie.setMinAge(3);
//	movieRepository.save(movie);
//
//	Optional<Movie> spiderMan= Optional.ofNullable(movieRepository.findByName("Jason Patron"));
//
//	if(spiderMan.isPresent()){
//		System.out.println(movie.getName());
//	}
//
//	}
}
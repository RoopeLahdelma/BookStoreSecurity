package com.example.bookstore;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;
import com.example.bookstore.domain.Category;
import com.example.bookstore.domain.CategoryRepository;
import com.example.bookstore.domain.User;
import com.example.bookstore.domain.UserRepository;



@SpringBootApplication
public class BookStoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookStoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookStoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookstoreDemo(BookRepository brepository, CategoryRepository crepository, UserRepository urepository) {
		return (args) -> {

			crepository.save(new Category("Seikkailu"));
			crepository.save(new Category("Toiminta"));
			crepository.save(new Category("Romanttinen-komedia"));

			log.info("save a couple of books"); 
			brepository.save(new Book("Apinanraivo", "Jorma Alamaa", "2813981298", 2019,
					crepository.findByName("Toiminta").get(0)));
			brepository.save(new Book("Karmea Karhu", "Jille Jamppanen", "3485983459", 1999,
					crepository.findByName("Seikkailu").get(0)));
			brepository
					.save(new Book("Alamaailman ihmeet", "Samppa Linna", "3728918273", 2020, crepository.findByName("Romanttinen-komedia").get(0)));
			brepository.save(new Book("Maamerentarinat", "Some One", "32892017564", 1950,
					crepository.findByName("Seikkailu").get(0)));
			brepository.save(new Book("Karnevaali", "Yukki Yuno", "8197263547", 1997,
					crepository.findByName("Toiminta").get(0)));
			
			//USERS
			
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			
			
			log.info("fetch all books");
			for (Book book : brepository.findAll()) {
				log.info(book.toString());
			}

		};
	}

}


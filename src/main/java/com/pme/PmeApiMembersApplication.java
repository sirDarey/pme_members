package com.pme;

import com.pme.entity.TechStack;
import com.pme.repo.StackRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class PmeApiMembersApplication extends SpringBootServletInitializer  implements CommandLineRunner {

	private final StackRepo stackRepo;

	@Autowired
	public PmeApiMembersApplication (StackRepo stackRepo) {
		this.stackRepo = stackRepo;
	}

	public static void main(String[] args) {
		SpringApplication.run(PmeApiMembersApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(PmeApiMembersApplication.class);
	}

	@Override
	public void run(String... args) {
		List<TechStack> stacks = Arrays.asList(
				new TechStack(null, "JavaScript"),
				new TechStack(null, "React"),
				new TechStack(null, "Python"),
				new TechStack(null, "Django"),
				new TechStack(null, "Java"),
				new TechStack(null, "SpringBoot"),
				new TechStack(null, "C#"),
				new TechStack(null, ".NET Core"),
				new TechStack(null, "PHP"),
				new TechStack(null, "Flutter")

		);

		stackRepo.saveAll(stacks);
	}
}

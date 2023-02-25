package com.pme;

import com.pme.entity.TechStack;
import com.pme.repo.StackRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class PmeApiMembersApplication {

	private final StackRepo stackRepo;

	@Autowired
	public PmeApiMembersApplication (StackRepo stackRepo) {
		this.stackRepo = stackRepo;
	}

	public static void main(String[] args) {
		SpringApplication.run(PmeApiMembersApplication.class, args);
	}

	@PostConstruct  //Initializes these entries to DB at application start up
	public void initStacks() {
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

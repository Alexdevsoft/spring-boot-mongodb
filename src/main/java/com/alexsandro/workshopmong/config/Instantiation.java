package com.alexsandro.workshopmong.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.alexsandro.workshopmong.domain.Post;
import com.alexsandro.workshopmong.domain.User;
import com.alexsandro.workshopmong.dto.AuthorDTO;
import com.alexsandro.workshopmong.dto.ComentDTO;
import com.alexsandro.workshopmong.repository.PostRepository;
import com.alexsandro.workshopmong.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepo.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepo.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, sdf.parse("19/02/2023"), "Partiu viagem", "Vou viajar para Viçosa, Abraço!", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("20/02/2023"), "Bom dia", "Acordei feliz hoje", new AuthorDTO(maria));
		
		ComentDTO c1 = new ComentDTO("Boa viagem, mano!", sdf.parse("21/01/2023"), new AuthorDTO(alex));
		ComentDTO c2 = new ComentDTO("Aproveite!.", sdf.parse("22/01/2023"), new AuthorDTO(bob));
		ComentDTO c3 = new ComentDTO("Tenha um ótimo dia.", sdf.parse("23/01/2023"), new AuthorDTO(alex));
		
		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepo.save(maria);
		
	}

}

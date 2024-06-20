package com.supportportal;

import com.supportportal.domain.User;
import com.supportportal.repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

import static com.supportportal.enumeration.Role.ROLE_ADMIN;

@SpringBootApplication
public class SupportportalApplication implements CommandLineRunner {

	private final UserRepository userRepository;

    public SupportportalApplication(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
		SpringApplication.run(SupportportalApplication.class, args);




	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void run(String... args) throws Exception {
		User user = new User();
		user.setUserId(RandomStringUtils.randomNumeric(10));
		user.setUsername("admin");
		user.setFirstName("kenzo");
		user.setLastName("Enzo");
		user.setEmail("kenzo@gmail.com");
		user.setRole(ROLE_ADMIN.name());
		user.setAuthorities(ROLE_ADMIN.getAuthorities());
		user.setPassword(new BCryptPasswordEncoder().encode("admin"));
		user.setActive(true);
		user.setNotLocked(true);
		user.setJoinDate(new Date());
		user.setProfileImageUrl("http://localhost:8081/user/image/profile/temp");

		userRepository.save(user);
	}
}

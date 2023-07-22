package com.qlsp.quanlysanpham;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.qlsp.quanlysanpham.users.AccountService;

@SpringBootApplication
public class QuanlysanphamApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuanlysanphamApplication.class, args);
	}

	//@Bean
	CommandLineRunner commandLineRunnerUserDetails(AccountService accountService){
		return args ->{
			accountService.addNewUser("admin", "1234", "admin@gmail.com", "1234");
			accountService.addNewUser("user", "1234", "user@gmail.com", "1234");
			accountService.addNewUser("user1", "1234", "user1@gmail.com", "1234");

			accountService.addRoleToUser("admin", "ADMIN");
			accountService.addRoleToUser("admin", "USER");
			accountService.addRoleToUser("user", "USER");
			accountService.addRoleToUser("user1", "USER");
		};
	}


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}

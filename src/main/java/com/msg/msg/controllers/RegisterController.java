package com.msg.msg.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.msg.msg.encryption.CryptoConverter;
import com.msg.msg.entities.User;
import com.msg.msg.mail.MailService;
import com.msg.msg.repositories.UserRepository;

@RestController
@RequestMapping("/register")
@CrossOrigin(origins = "*")
public class RegisterController {

	@Autowired
	public UserRepository userRepository;

	@Autowired
	public MailService mailService;

	@PostMapping("/save")
	public void registerUser(@RequestBody User user) throws MailException {
		User user2 = userRepository.findByUsername(user.getUsername());
		if (user2 == null) {
			String password = user.retrievePassword();
			String email = user.getEmail();
			String salt = password + email;
			user.setPassword(CryptoConverter.encrypt(salt));
			userRepository.save(user);
//			mailService.sendMail(user);
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username Already Exists");
		}
	}

	@PostMapping("/confirmed/{iduser}")
	public void enableAcount(@PathVariable int iduser) {
		User user = userRepository.findById(iduser);
		User.validateUser(user);
		user.setActiveStatus(1);
		userRepository.save(user);
	}

}

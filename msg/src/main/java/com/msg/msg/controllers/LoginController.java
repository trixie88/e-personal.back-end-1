package com.msg.msg.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.msg.msg.encryption.CryptoConverter;
import com.msg.msg.entities.Login;
import com.msg.msg.entities.Token;
import com.msg.msg.entities.User;
import com.msg.msg.repositories.TokenRepository;
import com.msg.msg.repositories.UserRepository;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*")
public class LoginController {

	@Autowired
	public UserRepository userRepository;

	@Autowired
	public TokenRepository tokenRepository;

//	@PostMapping("/user")
//	public Token loginUser(@RequestBody Login login) {
//		String username = login.getUsername();
//		String password = login.getPassword();
//		User user = userRepository.findByUsernameAndPassword(username, CryptoConverter.encrypt(password));
//		Token token = DatabaseHelper.createToken(user);
//		return token;
//	}

	@PostMapping("/user")
	public Token loginUser(@RequestBody Login login) {
		String username = login.getUsername();
		String password = login.getPassword();
		User user = userRepository.findByUsernameAndPassword(username, CryptoConverter.encrypt(password));
		if (user != null) {
			String alphanumeric = UUID.randomUUID().toString();
			Token token = new Token(alphanumeric, user);
			tokenRepository.save(token);
			return token;
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Username/Password");
		}
	}

	@GetMapping("/userFromToken")
	public User getFromToken(@RequestHeader(value = "X-MSG-AUTH") String alphanumeric) {
		int userId = tokenRepository.getUserIDFromTokenAlphaNumeric(alphanumeric);
		return userRepository.findById(userId);
	}

	@PostMapping("/logout")
	public void logout(@RequestHeader(value = "X-MSG-AUTH") String alphanumeric) {
		tokenRepository.deleteByAlphanumeric(alphanumeric);
	}
}

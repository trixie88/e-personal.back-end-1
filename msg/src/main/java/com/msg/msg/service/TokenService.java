package com.msg.msg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.msg.msg.entities.Token;
import com.msg.msg.repositories.TokenRepository;

@Service
public class TokenService {

	@Autowired
	private TokenRepository tokenRepository;

	public void validateToken(String alphanumeric) {
		Token token = tokenRepository.findByAlphanumeric(alphanumeric);
		if (token == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not Authorized");
		}
	}
}

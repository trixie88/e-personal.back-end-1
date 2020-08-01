package com.msg.msg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.msg.msg.entities.Token;

@CrossOrigin("*") // because this web service is only used locally i have crossOrigin all (*) if
					// it was to be deployed this must change
@RepositoryRestResource
public interface TokenRepository extends JpaRepository<Token, Integer> {

	@Query(value = "select iduser from token where alphanumeric=?", nativeQuery = true)
	int getUserIDFromTokenAlphaNumeric(String alphanumeric);

	Token findByAlphanumeric(String alphanumeric);

	@Transactional
	void deleteByAlphanumeric(String alphanumeric);

}

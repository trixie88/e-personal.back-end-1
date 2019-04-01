package com.msg.msg.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msg.msg.entities.Area;
import com.msg.msg.entities.Token;
import com.msg.msg.entities.TrainingType;
import com.msg.msg.entities.User;
import com.msg.msg.repositories.AreaRepository;
import com.msg.msg.repositories.TokenRepository;
import com.msg.msg.repositories.TrainingTypeRepository;
import com.msg.msg.repositories.UserRepository;

@RestController
@RequestMapping("/find")
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	public UserRepository userRepository;

	@Autowired
	public AreaRepository areaRepository;

	@Autowired
	public TrainingTypeRepository trainingTypeRepository;

	@Autowired
	TokenRepository tokenRepository;

	@GetMapping("/trainer/{specialization_title}/{city}")
	public List<User> getYourTrainer(@PathVariable String specialization_title, @PathVariable String city) {
		return userRepository.findTrainerByAreaAndType(specialization_title, city);
	}

	@GetMapping("/trainer/{specialization_title}/{city}/{price}")
	public List<User> getYourTrainer(@PathVariable String specialization_title, @PathVariable String city,
			@PathVariable double price) {
		return userRepository.findTrainerByAreaAndTypeAndPrice(specialization_title, city, price);
	}

	@GetMapping("trainer-area/{idarea}")
	public List<User> getTrainerByArea(@PathVariable int idarea) {
		return userRepository.findTrainerByArea(idarea);
	}

	@GetMapping("trainer-area-price/{idarea}/{price}")
	public List<User> getTrainerByAreaAndPrice(@PathVariable int idarea, @PathVariable double price) {
		return userRepository.findTrainerByAreaAndPrice(idarea, price);
	}

	@GetMapping("trainer-type/{idtraining_type}")
	public List<User> getTrainerByType(@PathVariable int idtraining_type) {
		return userRepository.findByTrainingType(idtraining_type);
	}

	@GetMapping("trainer-type-price/{idtraining_type}/{price}")
	public List<User> getTrainerByTypeAndPrice(@PathVariable int idtraining_type, @PathVariable double price) {
		return userRepository.findTrainerByTypeAndPrice(idtraining_type, price);
	}

	@GetMapping("trainer-price/{price}")
	public List<User> getTrainerByPrice(@PathVariable double price) {
		return userRepository.findTrainerByPrice(price);
	}

	@PostMapping("set-price/{iduser}/{price}")
	public void setPrice(@RequestHeader(value ="X-MSG-AUTH") String alphanumeric, @PathVariable int iduser,
			@PathVariable double price) {
		Token token = tokenRepository.findByAlphanumeric(alphanumeric);
		Token.validateToken(token);
		User user = userRepository.findById(iduser);
		user.setPrice(price);
	}

	@PostMapping("trainer-choose-area/{fk_trainer_id}/{fk_area_id}")
	public void chooseArea(@RequestHeader(value ="X-MSG-AUTH") String alphanumeric, @PathVariable int fk_trainer_id,
			@PathVariable int fk_area_id) {
		Token token = tokenRepository.findByAlphanumeric(alphanumeric);
		Token.validateToken(token);
		areaRepository.addArea(fk_trainer_id, fk_area_id);

	}

	@PostMapping("trainer-choose-type/{fk_trainer_id}/{fk_training_type}")
	public void trainerSpecialization(@RequestHeader(value ="X-MSG-AUTH") String alphanumeric,
			@PathVariable int fk_trainer_id, @PathVariable int fk_training_type) {
		Token token = tokenRepository.findByAlphanumeric(alphanumeric);
		Token.validateToken(token);
		trainingTypeRepository.addType(fk_trainer_id, fk_training_type);
	}

	@PostMapping("trainer-remove-area/{fk_trainer_id}/{fk_area_id}")
	public void removeArea(@RequestHeader(value ="X-MSG-AUTH") String alphanumeric, @PathVariable int fk_trainer_id,
			@PathVariable int fk_area_id) {
		Token token = tokenRepository.findByAlphanumeric(alphanumeric);
		Token.validateToken(token);
		areaRepository.removeArea(fk_trainer_id, fk_area_id);
	}

	@PostMapping("trainer-remove-type/{fk_trainer_id}/{fk_training_type}")
	public void removeType(@RequestHeader(value ="X-MSG-AUTH") String alphanumeric, @PathVariable int fk_trainer_id,
			@PathVariable int fk_training_type) {
		Token token = tokenRepository.findByAlphanumeric(alphanumeric);
		Token.validateToken(token);
		trainingTypeRepository.removeType(fk_trainer_id, fk_training_type);
	}

	@PostMapping("bann-user/{iduser}")
	public void bannUser(@RequestHeader(value ="X-MSG-AUTH") String alphanumeric, @PathVariable int iduser) {
		Token token = tokenRepository.findByAlphanumeric(alphanumeric);
		Token.validateToken(token);
		User user = userRepository.findById(iduser);
		User.validateUser(user);
		user.setActiveStatus(0);
		userRepository.save(user);

	}

	@PostMapping("unbann-user/{iduser}")
	public void unBannUser(@RequestHeader(value ="X-MSG-AUTH") String alphanumeric, @PathVariable int iduser) {
		Token token = tokenRepository.findByAlphanumeric(alphanumeric);
		Token.validateToken(token);
		User user = userRepository.findById(iduser);
		User.validateUser(user);
		user.setActiveStatus(1);
		userRepository.save(user);
		;

	}

	@GetMapping("/trainers-types/{iduser}")
	public List<TrainingType> getTrainersTypes(@PathVariable int iduser) {
		return trainingTypeRepository.findTrainersTypes(iduser);
	}

	@GetMapping("/trainers-areas/{iduser}")
	public List<Area> getTrainersAreas(@PathVariable int iduser) {
		return areaRepository.findTrainersAreas(iduser);
	}

}

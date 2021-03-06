package com.msg.msg.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.msg.msg.entities.Area;
import com.msg.msg.entities.TrainingType;
import com.msg.msg.entities.User;
import com.msg.msg.repositories.AreaRepository;
import com.msg.msg.repositories.TokenRepository;
import com.msg.msg.repositories.TrainingTypeRepository;
import com.msg.msg.repositories.UserRepository;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*") // because this web service is only used locally i have crossOrigin all (*) if
							// it was to be deployed this must change
public class UserController {

	@Autowired
	public UserRepository userRepository;

	@Autowired
	public AreaRepository areaRepository;

	@Autowired
	public TrainingTypeRepository trainingTypeRepository;

	@Autowired
	TokenRepository tokenRepository;

	@GetMapping("/getUser/{id}")
	public User findUser(@PathVariable int id) {
		User user = userRepository.findById(id);
		if (user == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid userId");
		} else {
			return user;
		}
	}

	@GetMapping("/allUsers")
	public List<User> allUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/trainersByAreaAndTrainingType/{areaIdList}/{trainingTypeIdList}")
	public List<User> getByAreasAndTypes(@PathVariable int[] areaIdList, @PathVariable int[] trainingTypeIdList) {
		List<Area> areas = new ArrayList<Area>();
		List<TrainingType> trainingTypes = new ArrayList<TrainingType>();
		for (int areadId : areaIdList) {
			areas.add(areaRepository.findById(areadId));
		}
		for (int trainingTypeId : trainingTypeIdList) {
			trainingTypes.add(trainingTypeRepository.findById(trainingTypeId));
		}
		return userRepository.findDistinctUsersByAreasInAndTrainingTypesIn(areas, trainingTypes);
	}

	@PutMapping("/update")
	public User updateUser(@RequestBody User user, @RequestHeader(value = "X-MSG-AUTH") String tokenAlphanumeric) {
//		Token.validateToken(tokenAlphanumeric, tokenRepository); //Validation takes place inside ValidateTokenAspect
		try {
			User clonedUser = userRepository.findById(user.getId());
			clonedUser.setFirstName(user.getFirstName());
			clonedUser.setLastName(user.getLastName());
			clonedUser.setEmail(user.getEmail());
			clonedUser.setPrice(user.getPrice());
			clonedUser.setAreas(user.getAreas());
			clonedUser.setTrainingTypes(user.getTrainingTypes());
			userRepository.save(clonedUser);
			return clonedUser;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Couldn't Update User");
		}

	}

	@GetMapping("/trainer/{title}/{city}")
	public List<User> getYourTrainer(@PathVariable String title, @PathVariable String city) {
		TrainingType trainingType = trainingTypeRepository.findByTitle(title);
		Area area = areaRepository.findByCity(city);
		return userRepository.findByAreasAndTrainingTypes(area, trainingType);
	}

	@GetMapping("/trainer/{title}/{city}/{price}")
	public List<User> getYourTrainer(@PathVariable String title, @PathVariable String city, @PathVariable double price) {
		TrainingType trainingType = trainingTypeRepository.findByTitle(title);
		Area area = areaRepository.findByCity(city);
		return userRepository.findByAreasAndTrainingTypesAndPrice(area, trainingType, price);
	}

	@GetMapping("trainer-area/{areaId}")
	public List<User> getTrainerByArea(@PathVariable int areaId) {
		Area area = areaRepository.findById(areaId);
		return userRepository.findByAreas(area);
	}

	@GetMapping("trainer-area-price/{areaId}/{price}")
	public List<User> getTrainerByAreaAndPrice(@PathVariable int areaId, @PathVariable double price) {
		Area area = areaRepository.findById(areaId);
		return userRepository.findByAreasAndPrice(area, price);
	}

	@GetMapping("trainer-type/{trainingTypeId}")
	public List<User> getTrainerByType(@PathVariable int trainingTypeId) {
		TrainingType trainingType = trainingTypeRepository.findById(trainingTypeId);
		return userRepository.findByTrainingTypes(trainingType);
	}

	@GetMapping("trainer-type-price/{trainingTypeId}/{price}")
	public List<User> getTrainerByTypeAndPrice(@PathVariable int trainingTypeId, @PathVariable double price) {
		TrainingType trainingType = trainingTypeRepository.findById(trainingTypeId);
		return userRepository.findByTrainingTypesAndPrice(trainingType, price);
	}

	@GetMapping("trainer-price/{price}")
	public List<User> getTrainerByPrice(@PathVariable double price) {
		return userRepository.findByPriceGreaterThanAndPriceLessThanEqual(0, price);
	}

	@PostMapping("set-price/{iduser}/{price}")
	public void setPrice(@RequestHeader(value = "X-MSG-AUTH") String tokenAlphanumeric, @PathVariable int iduser, @PathVariable double price) {
//		Token.validateToken(tokenAlphanumeric, tokenRepository);
		User user = userRepository.findById(iduser);
		user.setPrice(price);
		userRepository.save(user);
	}

	@PostMapping("trainer-choose-area/{fk_trainer_id}/{fk_area_id}")
	public void chooseArea(@RequestHeader(value = "X-MSG-AUTH") String tokenAlphanumeric, @PathVariable int fk_trainer_id, @PathVariable int fk_area_id) {
//		Token.validateToken(tokenAlphanumeric, tokenRepository);
		User user = userRepository.findById(fk_trainer_id);
		User.validateUser(user);
		Area area = areaRepository.findById(fk_area_id);
		user.addArea(area);
		userRepository.save(user);
	}

	@PostMapping("/addAreas/{userId}")
	public void addAreas(@RequestHeader(value = "X-MSG-AUTH") String tokenAlphanumeric, @PathVariable int userId, @RequestBody List<Area> areas) {
//		Token.validateToken(tokenAlphanumeric, tokenRepository);
		User user = userRepository.findById(userId);
		User.validateUser(user);
		user.setAreas(areas);
		userRepository.save(user);
	}

	@PostMapping("/addTrainingTypes/{userId}")
	public void addTrainingTypes(@RequestHeader(value = "X-MSG-AUTH") String tokenAlphanumeric, @PathVariable int userId, @RequestBody List<TrainingType> types) {

//		Token.validateToken(tokenAlphanumeric, tokenRepository);
		User user = userRepository.findById(userId);
		User.validateUser(user);
		user.setTrainingTypes(types);
		userRepository.save(user);
	}

	@PostMapping("trainer-remove-area/{fk_trainer_id}/{fk_area_id}")
	public void removeArea(@RequestHeader(value = "X-MSG-AUTH") String tokenAlphanumeric, @PathVariable int fk_trainer_id, @PathVariable int fk_area_id) {
//		Token.validateToken(tokenAlphanumeric, tokenRepository);
		User user = userRepository.findById(fk_trainer_id);
		User.validateUser(user);
		Area area = areaRepository.findById(fk_area_id);
		user.removeArea(area);
		userRepository.save(user);
	}

	@PostMapping("trainer-remove-type/{fk_trainer_id}/{training_type_id}")
	public void removeType(@RequestHeader(value = "X-MSG-AUTH") String tokenAlphanumeric, @PathVariable int fk_trainer_id, @PathVariable int training_type_id) {
//		Token.validateToken(tokenAlphanumeric, tokenRepository);
		User user = userRepository.findById(fk_trainer_id);
		User.validateUser(user);
		TrainingType trainingType = trainingTypeRepository.findById(training_type_id);
		user.removeTrainingType(trainingType);
		userRepository.save(user);
	}

	@PostMapping("bann-user/{iduser}")
	public void bannUser(@RequestHeader(value = "X-MSG-AUTH") String tokenAlphanumeric, @PathVariable int iduser) {
//		Token.validateToken(tokenAlphanumeric, tokenRepository);
		User user = userRepository.findById(iduser);
		user.setActiveStatus(0);
		userRepository.save(user);

	}

	@PostMapping("unbann-user/{iduser}")
	public void unBannUser(@RequestHeader(value = "X-MSG-AUTH") String tokenAlphanumeric, @PathVariable int iduser) {
//		Token.validateToken(tokenAlphanumeric, tokenRepository);
		User user = userRepository.findById(iduser);
		user.setActiveStatus(1);
		userRepository.save(user);

	}

	@GetMapping("/trainers-types/{iduser}")
	public List<TrainingType> getTrainersTypes(@PathVariable int iduser) {
		User user = userRepository.findById(iduser);
		return user.getTrainingTypes();
	}

	@GetMapping("/trainers-areas/{iduser}")
	public List<Area> getTrainersAreas(@PathVariable int iduser) {
		User user = userRepository.findById(iduser);
		return user.getAreas();
	}

}

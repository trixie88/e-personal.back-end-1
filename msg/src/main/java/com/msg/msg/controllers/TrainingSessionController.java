package com.msg.msg.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.msg.msg.database.DatabaseHelper;
import com.msg.msg.entities.Area;
import com.msg.msg.entities.Result;
import com.msg.msg.entities.Review;
import com.msg.msg.entities.Token;
import com.msg.msg.entities.TrainingSession;
import com.msg.msg.entities.TrainingType;
import com.msg.msg.entities.User;
import com.msg.msg.repositories.AreaRepository;
import com.msg.msg.repositories.ReviewRepository;
import com.msg.msg.repositories.TokenRepository;
import com.msg.msg.repositories.TrainingSessionRepository;
import com.msg.msg.repositories.TrainingTypeRepository;
import com.msg.msg.repositories.UserRepository;

@RestController
@RequestMapping("/session")
@CrossOrigin(origins = "*")
public class TrainingSessionController {

	@Autowired
	public TrainingSessionRepository trainingSessionRepository;

	@Autowired
	public UserRepository userRepository;

	@Autowired
	public TokenRepository tokenRepository;

	@Autowired
	public TrainingTypeRepository trainingTypeRepository;

	@Autowired
	public AreaRepository areaRepository;

	@Autowired
	public ReviewRepository reviewRepository;

	@GetMapping("/myTrainingSessions")
	public List<TrainingSession> getTrainersSessions(@RequestHeader(value ="X-MSG-AUTH") String tokenAlphanumeric) {
		Token.validateToken(tokenAlphanumeric, tokenRepository);
		int id = tokenRepository.getUserIDFromTokenAlphaNumeric(tokenAlphanumeric);
		return trainingSessionRepository.findTrainersSessions(id);
	}
	
	@GetMapping("/trainersSession/{id}")
	public List<TrainingSession> getSessions(@RequestHeader(value ="X-MSG-AUTH") String tokenAlphanumeric, @PathVariable int id){
		Token.validateToken(tokenAlphanumeric, tokenRepository);
		User user=userRepository.findById(id);
		if (user==null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid User ID");
		}else {
			return trainingSessionRepository.findTrainersSessions(id);
		}
	}

	@GetMapping("/trainer-sessions-date/{date}") 
	public List<TrainingSession> getLoggedInTrainersSessionsByDate(@RequestHeader("X-MSG-AUTH") String tokenAlphanumeric,
			@PathVariable String date) {
		Token.validateToken(tokenAlphanumeric, tokenRepository);
		int id = tokenRepository.getUserIDFromTokenAlphaNumeric(tokenAlphanumeric);
		return trainingSessionRepository.findTrainersSessionsByDate(id, date);
//		return trainingSessionRepository.findByIdAndDate(id, date);
	}
	
	@GetMapping("/trainer-sessions-date/{date}/{id}") 
	public List<TrainingSession> getTrainersSessionsByDate(@RequestHeader("X-MSG-AUTH") String tokenAlphanumeric,
			@PathVariable String date, @PathVariable int id) {
		Token.validateToken(tokenAlphanumeric, tokenRepository);
		return trainingSessionRepository.findTrainersSessionsByDate(id, date);
//		return trainingSessionRepository.findByIdAndDate(id, date);
	}
	

	@GetMapping("/client-sessions")
	public List<TrainingSession> getClientSessions(@RequestHeader(value ="X-MSG-AUTH") String tokenAlphanumeric) {
		Token.validateToken(tokenAlphanumeric, tokenRepository);
		int id = tokenRepository.getUserIDFromTokenAlphaNumeric(tokenAlphanumeric);
		return trainingSessionRepository.findUserSessions(id);
	}

//	@GetMapping("/client-sessions-date/{date}") 
//	public List<TrainingSession> getCientssSessionsByDate(@RequestHeader("X-MSG-AUTH") String tokenAlphanumeric,
//			@PathVariable String date) {
//		Token.validateToken(tokenAlphanumeric, tokenRepository);
//		int id = tokenRepository.getUserIDFromTokenAlphaNumeric(tokenAlphanumeric);
//		return trainingSessionRepository.findUserSessionsBydate(id, date);
//	}
	
	@GetMapping("/client-sessions-date/{date}/{id}") 
	public List<TrainingSession> getCientssSessionsByDate(@RequestHeader("X-MSG-AUTH") String tokenAlphanumeric,
			@PathVariable String date, @PathVariable int id) {
		Token.validateToken(tokenAlphanumeric, tokenRepository);
		
		return trainingSessionRepository.findUserSessionsBydate(id, date);
	}

	@PostMapping("/book/{fk_trainer_id}/{idtraining_type}/{idarea}/{date}/{time}")
	public void bookSession(@RequestHeader(value ="X-MSG-AUTH") String tokenAlphanumeric, @PathVariable int fk_trainer_id,
			@PathVariable int idtraining_type, @PathVariable int idarea, @PathVariable String date,
			@PathVariable String time) {
		Token.validateToken(tokenAlphanumeric, tokenRepository);
		int id = tokenRepository.getUserIDFromTokenAlphaNumeric(tokenAlphanumeric);
		User client = userRepository.findById(id);
		User.validateUser(client);
		User trainer = userRepository.findById(fk_trainer_id);
		User.validateUser(trainer);
		Area area = areaRepository.findById(idarea);
		Area.validateArea(area);
		TrainingType trainingType = trainingTypeRepository.findById(idtraining_type);
		TrainingType.validateTrainingType(trainingType);
		TrainingSession trainingSession = new TrainingSession(client, trainer, area, trainingType, date, time);
		trainingSessionRepository.save(trainingSession);

	}
	
	@PostMapping("/cancel-session/{idtraining_session}")
	public void cancelSession(@RequestHeader(value ="X-MSG-AUTH") String tokenAlphanumeric, @PathVariable int idtraining_session) {
		Token.validateToken(tokenAlphanumeric, tokenRepository);
		TrainingSession trainingSession = trainingSessionRepository.findById(idtraining_session);
		TrainingSession.validateTrainingSession(trainingSession);
		trainingSession.setCancelationStatus(1);
		trainingSessionRepository.save(trainingSession);
	}
	
	@GetMapping("/notify-booked-sessions/{fk_trainer_id}")
	public List<TrainingSession> notifyForUnreadSessions(@RequestHeader(value ="X-MSG-AUTH") String tokenAlphanumeric, @PathVariable int fk_trainer_id){
		Token.validateToken(tokenAlphanumeric, tokenRepository);
		return trainingSessionRepository.findUnreadSessions(fk_trainer_id);
	}
	
	@PostMapping("/notified/{idtraining_session}")
	public void notified(@RequestHeader(value ="X-MSG-AUTH") String tokenAlphanumeric, @PathVariable int idtraining_session) {
		Token.validateToken(tokenAlphanumeric, tokenRepository);
		TrainingSession trainingSession = trainingSessionRepository.findById(idtraining_session);
		TrainingSession.validateTrainingSession(trainingSession);
		trainingSession.setNotificationStatus(1);
		trainingSessionRepository.save(trainingSession);
	}
	
	@GetMapping("/notify-canceled-sessions/{fk_trainer_id}")
	public List<TrainingSession> getCanceledSessions(@RequestHeader(value ="X-MSG-AUTH") String tokenAlphanumeric, @PathVariable int fk_trainer_id){
		Token.validateToken(tokenAlphanumeric, tokenRepository);
		return trainingSessionRepository.findCanceledSessions(fk_trainer_id);
	}

	@GetMapping("/review/{idtraining_session}")
	public Review getSessionReview(@PathVariable int idtraining_session) {
		return reviewRepository.getSessionComment(idtraining_session);
	}

	@GetMapping("/review-trainer/{fk_trainer_id}")
	public Result<Review> getTrainerReview(@PathVariable int fk_trainer_id, @RequestParam int index1,
			@RequestParam int index2) {
//		Result.validateIndexes(index1, index2);
		List<Review> reviews = reviewRepository.getTrainerComments(fk_trainer_id,index1,index2);
		int count = DatabaseHelper.getTrainersReviews(fk_trainer_id);
		return new Result<Review>(count, reviews);
	}

	@PostMapping("/add-comment/{idtraining_session}/{rating}")
	public void reviewSession(@RequestHeader(value ="X-MSG-AUTH") String tokenAlphanumeric,
			@PathVariable int idtraining_session,@PathVariable int rating, @RequestBody String comment) {
			Token.validateToken(tokenAlphanumeric, tokenRepository);
			TrainingSession trainingSession = trainingSessionRepository.findById(idtraining_session);
			TrainingSession.validateTrainingSession(trainingSession);
			Review review = new Review(trainingSession, comment, rating);
			reviewRepository.save(review);

	}

//	@PostMapping("/trainer-sessions-cancel/{idtraining_session}")
//	public void cancelSession(@PathVariable int idtraining_session) {
//		DatabaseHelper.cancelSession(idtraining_session);
//	}

}

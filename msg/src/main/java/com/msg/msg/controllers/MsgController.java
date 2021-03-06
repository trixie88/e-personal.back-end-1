package com.msg.msg.controllers;

import java.util.List;
import java.util.Optional;

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
import com.msg.msg.entities.Message;
import com.msg.msg.entities.Result;
import com.msg.msg.entities.Token;
import com.msg.msg.entities.User;
import com.msg.msg.repositories.MessageRepository;
import com.msg.msg.repositories.TokenRepository;
import com.msg.msg.repositories.UserRepository;

@RestController
@RequestMapping("/messages")
@CrossOrigin(origins = "*") // because this web service is only used locally i have crossOrigin all (*) if
							// it was to be deployed this must change
public class MsgController {

	@Autowired
	public MessageRepository messageRepository;

	@Autowired
	public UserRepository userRepository;

	@Autowired
	public TokenRepository tokenRepository;

	@PostMapping("/setSeen/{messageId}")
	public Message setMessageSeen(@PathVariable int messageId, @RequestHeader("X-MSG-AUTH") String tokenAlphanumeric) {
		Optional<Message> result = messageRepository.findById(messageId);
		Message msg = null;
		if (result.isPresent()) {
			msg = result.get();
			msg.setSeen(1);
			messageRepository.save(msg);
			return msg;
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid message id");
		}
	}

	@GetMapping("/newMessagesCount/{userId}")
	public int newMessagesCount(@PathVariable int userId) {
		return DatabaseHelper.getNewMessagesCount(userId);
	}

	@GetMapping("/sent")
	public Result<Message> getSentMessages(@RequestHeader("X-MSG-AUTH") String tokenAlphanumeric, @RequestParam int index1, @RequestParam int index2) {
		Token token = tokenRepository.findByAlphanumeric(tokenAlphanumeric);
		int senderId = token.getUser().getId();
		List<Message> msgs = messageRepository.findSentMessages(senderId, index1, index2);
		int count = DatabaseHelper.getSentMsgCount(senderId);
		return new Result<Message>(count, msgs);
	}

	@GetMapping("/inbox")
	public Result<Message> getInboxMessages(@RequestHeader("X-MSG-AUTH") String tokenAlphanumeric, @RequestParam int index1, @RequestParam int index2) {
		Token token = tokenRepository.findByAlphanumeric(tokenAlphanumeric);
		int receiverId = token.getUser().getId();
		List<Message> msgs = messageRepository.findInboxMessages(receiverId, index1, index2);
		int count = DatabaseHelper.getInboxMsgCount(receiverId);
		return new Result<Message>(count, msgs);
	}

	@GetMapping("/UsersMsg/{trainerUsername}/{clientUsername}")
	public Result<Message> getUserMessages(@RequestHeader("X-MSG-AUTH") String tokenAlphanumeric, @PathVariable String trainerUsername, @PathVariable String clientUsername, @RequestParam int index1, @RequestParam int index2) {
		User trainer = userRepository.findByUsername(trainerUsername);
		User.validateUser(trainer);
		User client = userRepository.findByUsername(clientUsername);
		User.validateUser(client);
		List<Message> msgs = messageRepository.findUserMessages(client.getId(), trainer.getId(), trainer.getId(), client.getId(), index1, index2);
		int count = DatabaseHelper.getUsersMsgCount(trainer.getId(), client.getId());
		return new Result<Message>(count, msgs);
	}

	@PostMapping("/save/{receiverUsername}")
	public void sendMessage(@RequestHeader("X-MSG-AUTH") String tokenAlphanumeric, @PathVariable String receiverUsername, @RequestBody String content) {
		Token token = tokenRepository.findByAlphanumeric(tokenAlphanumeric);
		User sender = token.getUser();
		User.validateUser(sender);
		User receiver = userRepository.findByUsername(receiverUsername);
		User.validateUser(receiver);

		Message message = new Message(sender, receiver, content);
		messageRepository.save(message);
	}

}

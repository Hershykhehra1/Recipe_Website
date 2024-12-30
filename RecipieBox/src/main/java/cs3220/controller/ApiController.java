package cs3220.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import cs3220.model.RecepieDto;
import cs3220.model.RecepieEntry;
import cs3220.model.UserDto;
import cs3220.model.UserEntry;
import cs3220.repository.RecepieRepository;
import cs3220.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class ApiController {
	int x = 0;
	private final RecepieRepository recepieRepository;
	private final UserRepository userRepository;

	public ApiController(UserRepository userRepository, RecepieRepository recepieRepository) {
		this.userRepository = userRepository;
		this.recepieRepository = recepieRepository;
	}
	
	@GetMapping("/messages")
	public Iterable<RecepieEntry> getMessage() {
		return recepieRepository.findAll();
	}

	@GetMapping("/usersDto")
	public Iterable<UserDto> getEmployeeDto() {
		List<UserDto> users = new ArrayList<UserDto>();
		for (UserEntry user : userRepository.findAll()) {
			users.add(new UserDto(user));
		}
		return users;
	}

	@GetMapping("/messagesDto")
	public Iterable<RecepieDto> getMessageDto() {
		List<RecepieDto> messages = new ArrayList<RecepieDto>();
		for (RecepieEntry message : recepieRepository.findAll()) {
			messages.add(new RecepieDto(message));
		}
		if (messages.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Please fill out all fields");
		}
		return messages;
	}

	@GetMapping("/messages/{id}")
	public RecepieDto getMessage(@PathVariable int id) {
		return new RecepieDto(recepieRepository.findById(id).orElse(null));
	}

	@GetMapping("/messages404/{id}")
	public RecepieDto getMessage404(@PathVariable int id) {
		RecepieEntry message = recepieRepository.findById(id).orElse(null);
		if (message == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Message not found");
		}
		return new RecepieDto(message);
	}

	@GetMapping("/getUser/{id}")
	public String getUser(@PathVariable int id) {
		UserEntry user1 = userRepository.findById(id).orElse(null);
		return user1.getName();
	}

	@GetMapping("/getMessage/{id}")
	public RecepieEntry getmessageId(@PathVariable int id) {
		RecepieEntry message = recepieRepository.findById(id).orElse(null);
		return message;
	}

	@PostMapping("/addmessages")
	public Integer addMessage(@RequestBody RecepieDto newMessage) {
	    RecepieEntry message = newMessage.newRecepie();
	    message.setDate(LocalDate.now());
	    message = recepieRepository.save(message);
	    return message.getUser().getId();
	}


	@PatchMapping("/editmessages/{userId}/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void editMessage(@PathVariable int userId, @PathVariable int id, @RequestBody RecepieDto editedMessage) {
	    Optional<RecepieEntry> messageOptional = recepieRepository.findById(id);
	    if (messageOptional.isPresent()) {
	        RecepieEntry message = messageOptional.get();
	        if (message.getUser().getId() == userId) {
	            if (!editedMessage.getRecepie().isEmpty()) {
	                message.setRecepie(editedMessage.getRecepie());
	                recepieRepository.save(message);
	            } else {
	                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Message cannot be empty");
	            }
	        } else {
	            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User does not have permission to edit this message");
	        }
	    } else {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Message not found");
	    }
	}


	@DeleteMapping("/deleteMessage/{id}")
	public void deleteMessage(@PathVariable int id, @RequestParam int userId) {
		RecepieEntry message = recepieRepository.findById(id).orElse(null);
	    if (message != null) {
	        if (message.getUser().getId() == userId) {
	            recepieRepository.delete(message);
	        } else {
	            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You don't have permission to delete this message");
	        }
	    } else {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Message not found");
	    }
	}



	@PostMapping("/Login")
	public Integer postLogin(@RequestBody UserDto userdto) {
		Integer userId = 0;
		List<UserEntry> users = userRepository.findByEmail(userdto.getEmail());

		if (!users.isEmpty()) {
			UserEntry user1 = users.get(0);
			if (user1.getPassword().equals(userdto.getPassword())) {
				userId = user1.getId();
			} else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Email or Password is Incorrect");
			}
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Doesn't Exist");
		}
		return userId;
	}

	// RequestBody converts json input attributes to an object
	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public Integer postRegister(@RequestBody UserDto userdto) {
		Integer id = null;
		List<UserEntry> users = userRepository.findByEmail(userdto.getEmail());
		if (userdto.getEmail().isEmpty() && userdto.getPassword().isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Email or Password is Empty");

		}
		if (users.isEmpty()) {
			UserEntry user = userRepository.save(userdto.newUser());
			id = user.getId();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Exists");
		}
		return id;

	}

	@GetMapping("/logout")
	public String getLogout() {
		return "redirect:/";
	}
}
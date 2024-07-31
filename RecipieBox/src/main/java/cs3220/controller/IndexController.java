package cs3220.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cs3220.model.RecepieDto;
import cs3220.model.RecepieEntry;
import cs3220.model.UserEntry;
import cs3220.repository.RecepieRepository;
import cs3220.repository.UserRepository;
import jakarta.validation.Valid;

@Controller
public class IndexController {
	private final RecepieRepository recepieRepository;
	private final UserRepository userRepository;
	private Integer userId;
	private String errorMessage = null;
	
	public IndexController(UserRepository userRepository, RecepieRepository recepieRepository) {
		this.userRepository = userRepository;
		this.recepieRepository = recepieRepository;
	}
	
	@RequestMapping("/")
	public String index(@ModelAttribute("users") UserEntry newEntry, BindingResult result) {
		return "main";
	}

}
package me.anrin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// 控制器
@Controller
@RequestMapping("/home")
public class HomeController {

	@RequestMapping("/index")
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String detail(Model model) { 
		model.addAttribute("name", "Michael Jordan");
		return "detail";
	}
}

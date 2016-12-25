package me.anrin.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

	// 返回 josn 格式
	@RequestMapping("/json")
	@ResponseBody
	public Map<String,Object> json() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		return map;
	}
}

package me.anrin.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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

	// 定义请求类型
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String detail(Model model) { 
		model.addAttribute("name", "Michael Jordan");
		return "detail";
	}

	// 活动地址
	@RequestMapping("/{address}")
	public String active(@PathVariable String address) {
		return address;
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

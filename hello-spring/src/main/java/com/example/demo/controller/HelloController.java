package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

	@GetMapping("hello")
	public String hello(Model model) {
		model.addAttribute("data", "hello!!");
		return "hello"; 
		/*
		 컨트롤러에서 리턴값으로 문자를 반환하면 뷰 리졸버(viewResolver)가 화면을 찾아서 처리한다
		  - 스프링 부트 템플릿엔진 기본 viewName 매핑 
		  - resources:templates/ + {ViewName} + .html
		  - spring-boot-devtools 라이브러리를 추가하면, html 파일을 컴파일만 해주면 서버 재시작 없이 view파일 변경이 가능하다
		 */
	}
	
	@GetMapping("hello-mvc")
	public String helloMvc(@RequestParam("name") String name, Model model) {
		model.addAttribute("name", name);
		return "hello-template";
	}
	/*
	 MVC
	  - View : 화면을 구성
	  - Controller, Model : 비즈니스 로직, 내부적인 처리
	 */
	
	@GetMapping("hello-string")
	@ResponseBody
	public String helloString(@RequestParam("name") String name) {
		
		return "hello" + name;
	}
	
	@GetMapping("hello-api")
	@ResponseBody
	public Hello helloApi(@RequestParam("name") String name) {
		Hello hello = new Hello();
		hello.setName(name);
		return hello;
	}
	
	static class Hello{
		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
		
	}
	/*
	 API
	  - responseBody : HTTP의 BODY에 문자 내용을 직접 반환
	  				   viewResolver 대신에 HttpMessageConverter가 동작
	  				   기본 문자처리 : StringHttpMessageConverter
	  				   기본 객체처리 : MappingJackson2HttpMessageConverter
	  				   객체가 전달되는 경우 default는 json방식으로 응답
	 */
}

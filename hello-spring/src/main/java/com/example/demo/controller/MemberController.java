package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.domain.Member;
import com.example.demo.domain.MemberForm;
import com.example.demo.service.MemberService;

//Controller를 통해서 외부 요청을 받는다
@Controller //controller어노테이션을 통해서 spring container가 관리를 한다
public class MemberController {

//	private final MemberService memberService = new MemberService(); 스프링이 관리하는 경우 container에 등록하고 받아쓰는 방식, 생성자 방식으로 생성
	private final MemberService memberService;
	
	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@GetMapping("/members/new") //get방식은 주로 조회할때 사용(url의 직접 입력하는 방식)
	public String createForm() {
		return "members/createMemberForm";
	}
	
	@PostMapping("/members/new") //post방식은 data를 form같은 곳에 넣어서 전달할때 사용한다
	public String create(MemberForm form) {
		Member member = new Member();
		member.setName(form.getName());
		
		memberService.join(member);
		
		return "redirect:/";
	}
	
	@GetMapping("/members")
	public String list(Model model) {
		List<Member> members = memberService.findMembers();
		model.addAttribute("members", members);
		
		return "members/memberlist";
	}
	
	
}

/*
 스프링 빈을 등록하는 2가지 방법
  1. 컴포넌트 스캔과 자동 의존관계를 설정
   - 컴포넌트 어노테이션이 있는 경우에는 스프링이 각각 객체를 생성해서 container에 등록을 한다
   - Autowired 같은 경우에는 연관관계를 형성한다
   - 스프링은 스프링 컨테이너에 스프링 빈을 등록할 때, 기본으로 싱글통으로 등록한다. 설정으로 싱글톤이 아니게 설정 할 수 있지만, 특별한 경우를 제외하면 대부분 싱글톤을 사용한다
   
  2. 자바 코드로 직접 스프링 빈 등록하기
   - @Configuration을 설정해준다
   - @Bean을 통해서 각각 하나씩 등록을 해준다
   
  주로 정형화된 컨트롤러, 서비스, 리포지토리 같은 코드는 컴포넌트 스캔을 사용한다. 그리고 정형화되지 않거나, 상황에 따라 구현 클래스를 변경해야 하면 설정을 통해 스프링 빈으로 등록한다
  Autowired를 통한 DI는 스프링이 관리하는 객체에서만 동작한다. 스프링 빈으로 등록하지 않고 내가 직접 생성한 객체에서는 동작하지 않는다
 */
 

package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.MemoryMemberRepository;

//Service에서 비즈니스 로직을 만든다
//@Service
public class MemberService { // service같은 경우에는 비지니스에 해당하기 때문에 naming을 할때 직관적으로 하는게 좋다
	/*
	 이클립스 같은 경우에는 moreunit플러그인을 다운받으면 테스트를 편리하게 진행할수 있다
	 단축키
	  - ctrl + j는 클래스의 테스트 클래스를 만들어준다
	  - ctrl + r은 테스트 run
	  - ctrl + alt + shift + r은 현재 위치한 유닛테스트만 실행
	 */
//	private final MemberRepository  memberRepository = new MemoryMemberRepository(); new를 통해 객체를 생성하면 테스트와 서로 다른 객체를 생성하기 떄문에 생성자를 통해 만드는것이 좋다 
	

	private final MemberRepository  memberRepository;
	
	
	
	@Autowired
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository; //의존성 주입(dependency injection)
	}


	/**
	 회원가입
	 */
	public Long join(Member member) {
		
		validateDuplicateMember(member); // 중복회원 검증
		
		memberRepository.save(member);
		return member.getId();
	}


	private void validateDuplicateMember(Member member) {
		memberRepository.findByName(member.getName())
			.ifPresent(m ->{
				throw new IllegalStateException("이미 존재하는 회원입니다.");
			}); // 로직이 나오는 경우에는 메서드로 뽑는게 좋다
	}
	
	/**
	 전체 회원 조회
	 */
	public List<Member> findMembers(){
		return memberRepository.findAll();
	}
	
	public Optional<Member> findOne(Long memberId){
		return memberRepository.findById(memberId);
	}
}

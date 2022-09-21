package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemoryMemberRepository;

public class MemberServiceTest {
	
	MemberService memberservice;
	MemoryMemberRepository memberRepository;
	
	@BeforeEach
	public void beforeEach() {
		memberRepository = new MemoryMemberRepository();
		memberservice = new MemberService(memberRepository); // 테스트를 실행할때 마다 각각 생성해주므로 독립적인 테스트가 가능해진다
	}
	
	@AfterEach
	public void afterEach() {
		memberRepository.clearStroe();
	} //test가 하나 끝날때마다 데이터를 비워주는 메서드, 의존관계를 없애주기 위해서

	@Test
	void testJoin() {
		//given
		Member member = new Member();
		member.setName("hello");
		
		//when
		Long saveId = memberservice.join(member);
		
		//then
		Member findMember = memberservice.findOne(saveId).get();
		assertThat(member.getName()).isEqualTo(findMember.getName());
		
	}
	
	@Test
	public void 중복_회원_예외() {
		//given
		Member member1 = new Member();
		member1.setName("spring");
		
		Member member2 = new Member();
		member2.setName("spring");
		
		//when
		memberservice.join(member1);
		assertThrows(IllegalStateException.class, () -> memberservice.join(member2)); //try catch문을 통해 예외를 test하지 않고 assertThrows를 통해 바로 테스트가 가능하다
		
//		try {
//			memberservice.join(member2);
//			fail();
//		}catch(IllegalStateException e) {
//			assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//		}
	
		
		//then
	}

	@Test
	void testFindMembers() {
		
	}

	@Test
	void testFindOne() {
		
	}

}

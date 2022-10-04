package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;

@SpringBootTest // 스프링 컨테이너와 테스트를 함께 실행한다
@Transactional // TEST 시작 전에 트랜잭션을 시작하고 TEST후에 rollback을 통해서 DB에 반영이 안되게하는 어노테이션(TEST를 반복적으로 가능하도록, 다음 테스트에 여향을 주지 않는다)
public class MemberServiceIntegrationTest {
	
	@Autowired MemberService memberservice;
	@Autowired MemberRepository memberRepository; //테스트 같은 경우에는 가장 끝단이기 때문에 편리한 방법으로 실행하는것이 좋다
	
	
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
	public void 중복_회원_예외() throws Exception {
		//given
		Member member1 = new Member();
		member1.setName("spring");
		
		Member member2 = new Member();
		member2.setName("spring");
		
		//when
		memberservice.join(member1);
		IllegalStateException e = assertThrows(IllegalStateException.class,() -> memberservice.join(member2)); // 예외가 발생해야 한다.
		
		
	
		
		//then
		assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
	}

	@Test
	void testFindMembers() {
		
	}

	@Test
	void testFindOne() {
		
	}

} // 가급적이면 통합테스트보다 순수한 단위테스트가 훨씬 좋은 테스트일 경우가 높다(스프링 컨테이너 없이 테스트)

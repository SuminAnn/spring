package com.example.demo;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.repository.JdbcMemberRepository;
import com.example.demo.repository.JdbcTemplateMemberRepository;
import com.example.demo.repository.JpaMemberRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.MemberService;

@Configuration
public class SpringConfig {
	
//	private final DataSource dataSource;
//	
//	public SpringConfig(DataSource dataSource) {
//		this.dataSource = dataSource;
//	} // JDBCTemplate
	
//	private EntityManager em;
//	
//	@Autowired
//	public SpringConfig(EntityManager em) {
//		this.em = em;
//	} // JPA
	
	private final MemberRepository memberRepository;
	
	@Autowired
	public SpringConfig(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository); // memberSerive와 의존관계 등록
	}
	
	



//	@Bean
//	public MemberRepository memberRepository() {
//		return new MemoryMemberRepository();
//		return new JdbcMemberRepository(dataSource); // JDBC
		// interface 구현체를 바꾸면서 기존 코드를 변경하지 않고 변경할수 있는 점이 객체지향 언어의 장점이다
		// interface를 두고 구현체를 변경을 통해 디형성을 활용 (spring contatiner가 지원을 해준다)
		
//		return new JdbcTemplateMemberRepository(dataSource); // JdbcTemplate
//		return new JpaMemberRepository(em); //JPA
		
//	}
}

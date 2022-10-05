package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Member;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository { // interface가 interface를 상속받는 경우에는 extends를 사용한다
	
	//JPQL : select m from Member m wher m.name = ?
	//공통적인 CRUD를 제외한 것들은 규칙에 맞춘 method 이름을 통해 개발이 가능하다
	@Override
	Optional<Member> findByName(String name);

	
	
	/*
	 스프링 부트와 JPA만 사용해도 개발 생산성이 많이 증가한다
	 개발해야할 코드도 줄어들고, 스프링 데이터 JPA를 사용하면 기존의 한계를 넘어 리포지토리에 구현 클래스 없이 인터페이스 만으로 개발을 완료할 수 있다
	 기본 CRUD 기능도 스프링 데이터 JPA가 모두 제공한다
	 따라서 개발자는 핵심 비즈니스 로직을 개발하는데 집중할 수 있다
	 실무에서 관계형 데이터베이스를 사용한다면 스프링 데이터 JPA는 필수이다
	 
	 SpringDataJPA가 JpaRepository를 보고 SpringBean을 자동으로 만든다
	 인터페이스를 통한 기본적인 CRUD를 제공한다
	 findByName(), findByEmail() 처럼 메서드 이름 만으로 조회 기능을 제공한다
	 페이징 기능 자동 제공한다
	 실무에서는 JPA와 스프링 데이터 JPA를 기본으로 사용하고 복잡한 동적 쿼리는 Querydsl이라는 라이브러리를 사용한다
	 Querydsl을 사용하면 쿼리도 자바 코드로 안전하게 작성할 수 있다
	 
	 */
}

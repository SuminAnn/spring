package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import com.example.demo.domain.Member;

public class JpaMemberRepository implements MemberRepository{

	private final EntityManager em; //JPA는 EntitiyManager를 통해 동작한다
	
	
	
	public JpaMemberRepository(EntityManager em) {
		this.em = em;
	}

	@Override
	public Member save(Member member) {
		em.persist(member);
		return member;
	}

	@Override
	public Optional<Member> findById(Long id) {
		Member member = em.find(Member.class, id); //조회는 (Type, pk)값을 세팅해주면 된다
		return Optional.ofNullable(member);
	}

	@Override
	public Optional<Member> findByName(String name) {
		List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class).setParameter("name", name).getResultList();
		return result.stream().findAny();
	}

	@Override
	public List<Member> findAll() {
		return em.createQuery("select m from Member m", Member.class).getResultList(); //객체를 대상으로 query를 실행
	} //pk기반이 아닌 select같은 경우에는 jpql을 작성해줘야한다 

	
	
	/*
	 JPA는 기존의 반복 코드는 물론이고, 기본적인 SQL도 JPA가 직접 만들어서 실행해준다
	 JPA를 사용하면, SQL과 데이터 중심의 설계에서 객체 중심의 설계로 패러다임을 전환할 수 있다
	 JPA를 사용하면 개발 생산성을 크게 높일 수 있다
	 JPA는 객체랑 ORM 기술이다
	 JPA를 사용할려면 항상 Transactional이 있어야한다
	 */
}


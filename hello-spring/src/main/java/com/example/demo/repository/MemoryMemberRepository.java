package com.example.demo.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.demo.domain.Member;

//Repository에서 data를 저장한다
//@Repository
public class MemoryMemberRepository implements MemberRepository {

	private static Map<Long, Member> store = new HashMap<>(); // 실무에서는 동시성 문제가 있어서 공유되는 변수는 ConcurrentHashMap을 사용한다
	private static long sequence = 0L; // 실무에서는 동시성 문제가 있어서 공유되는 변수는 AtomicLong을 사용한다
	
	@Override
	public Member save(Member member) {
		member.setId(++sequence); // id는 시스템이 정해주기 때문에 ++을 해준다. 이름 같은 경우에는 사용자가 작성하여 넘어온다
		store.put(member.getId(), member);
		return  member;
	}

	@Override
	public Optional<Member> findById(Long id) {
		return Optional.ofNullable(store.get(id)); // null이 반환될 가능성이 있는 경우에는 Optional로 감싸서 반환해준다.
	}

	@Override
	public Optional<Member> findByName(String name) {
		return store.values().stream()
				.filter(member -> member.getName().equals(name))
				.findAny();
	}

	@Override
	public List<Member> findAll() {
		return new ArrayList<>(store.values());
	}
	
	public void clearStroe() {
		store.clear();
	} //비워주는 메서드

}

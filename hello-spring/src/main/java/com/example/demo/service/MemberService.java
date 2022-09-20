package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.MemoryMemberRepository;

public class MemberService { // service같은 경우에는 비지니스에 해당하기 때문에 naming을 할때 직관적으로 하는게 좋다

	private final MemberRepository  memberRepository = new MemoryMemberRepository();
	
	
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

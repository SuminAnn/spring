package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity //JPA가 관리하는 Entity
public class Member {

	@Id //PK를 mapping
	@GeneratedValue(strategy = GenerationType.IDENTITY) // IDENTITY는 DB가 알아서 생성해준다
	private Long id;
	
	private String name;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}

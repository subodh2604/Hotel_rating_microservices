package com.lcwd.user.service.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "micro_users")
public class User {
	
	@Id
	@Column(name="ID")
	private String userId;
	
	@Column(name="NAME",length = 20)
	private String name;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="ABOUT")
	private String about;
	
	
	@Transient //it will prevent ratings from getting stored in database. we don't need to store it as we will get ratings from ratings service.
	private List<Rating> ratings=new ArrayList<>();
	
}

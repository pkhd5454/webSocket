package com.socialChat.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of="id")
@Table(name="members")
@Entity
public class MemberDTO {
	@Id
	private String id;
	private String password;
	private String name;
	private String email;
	private String gender;
	private String phoneNumber;
	private String nickName;
	@CreationTimestamp
	private Date regdate;
	@UpdateTimestamp
	private Date updatedate;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="member")
	private List<MemberRoleDTO> roles;
}

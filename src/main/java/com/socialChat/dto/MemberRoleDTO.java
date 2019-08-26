package com.socialChat.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of="no")
@Table(name="member_roles")
@Entity
public class MemberRoleDTO {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long no;
	private String role;
}
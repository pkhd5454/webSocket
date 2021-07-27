package com.socialChat.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/** 멤버권한 */
@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "memberrole")
public class MemberRole {
	public static final MemberRole NONE = new MemberRole();

	// 일련번호
	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "no")
	private Long no;

	// 역할
	@Setter
	@Column(name = "role")
	private String role;
}

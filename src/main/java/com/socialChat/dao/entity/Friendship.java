package com.socialChat.dao.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/** 친구 관계 */
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@IdClass(FriendshipId.class)
@Entity(name = "friendship")
public class Friendship {
	public static final Friendship NONE = new Friendship();

	// 사용자
	@Id
	@NonNull
	@EqualsAndHashCode.Include
	@Column(name = "user")
	private String user;

	// 친구
	@Id
	@NonNull
	@EqualsAndHashCode.Include
	@Column(name = "friend")
	private String friend;

	// 등록일시
	@Setter
	@Column(name = "regDate")
	private LocalDateTime registrationDate;

	// 수정일시
	@Setter
	@Column(name = "updDate")
	private LocalDateTime updateDate;
}

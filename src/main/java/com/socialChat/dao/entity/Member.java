package com.socialChat.dao.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/** 사용자 */
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "member")
public class Member {
  public static final Member NONE = new Member();

  // 아이디
  @Id
  @NonNull
  @EqualsAndHashCode.Include
  @Column(name = "id")
  private String id;

  // 패스워드
  @Setter
  @Column(name = "password")
  private String password;

  // 이름
  @Setter
  @Column(name = "name")
  private String name;

  // 이메일
  @Setter
  @Column(name = "email")
  private String email;

  // 성
  @Setter
  @Column(name = "gender")
  private String gender;

  // 휴대폰번호
  @Setter
  @Column(name = "phoneNumber")
  private String phoneNumber;

  // 별명
  @Setter
  @Column(name = "nickName")
  private String nickName;

  // 등록일시
  @Setter
  @Column(name = "regDate")
  private Date registrationDate;

  // 수정일시
  @Setter
  @Column(name = "updDate")
  private Date updateDate;

  // 멤버 롤 리스트
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = MemberRole.class)
  @JoinColumn(name = "member", referencedColumnName = "id")
  private List<MemberRole> roles;
}

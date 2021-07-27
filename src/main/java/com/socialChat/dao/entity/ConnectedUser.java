package com.socialChat.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "connected_user")
public class ConnectedUser {
  public static final ConnectedUser NONE = new ConnectedUser();

  @Id
  @NonNull
  @EqualsAndHashCode.Include
  @Column(name = "sessionId")
  private String sessionId;

  @Setter
  @Column(name = "roomId")
  private String roomId;
}

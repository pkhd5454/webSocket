package com.socialChat.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Table(name="connecteduser")
@EqualsAndHashCode(of= {"sessionId","roomId"})
@Entity
public class ConnectedUser {
	@Id
	private String sessionId;
	private String roomId;
}

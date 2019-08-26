package com.socialChat.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import groovy.transform.ToString;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@ToString
public class FriendShipPK implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="user", nullable=false)
	private String user;
	
	@Column(name="friend", nullable=false)
	private String friend;
}

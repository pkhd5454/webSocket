package com.socialChat.dto;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@EqualsAndHashCode(of="friendPK")
@Table(name="friendship")
@Entity
public class FriendShip {
	@EmbeddedId
	private FriendShipPK friendPK;
	
	@CreationTimestamp
	private Date regdate;
	@UpdateTimestamp
	private Date updatedate;
}

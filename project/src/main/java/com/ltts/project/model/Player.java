package com.ltts.project.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GeneratorType;

@Entity
public class Player implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int playerId;
	
	private String playerName;
	
	@Id
	private String mobile;

	
	
	public Player() {
		super();
	}

	public Player(int playerId, String playerName, String mobile) {
		super();
		this.playerId = playerId;
		this.playerName = playerName;
		this.mobile = mobile;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return "Player playerId=" + playerId + ", playerName=" + playerName + ", mobile=" + mobile;
	}
	
	
}

package de.saschascherrer.code.blogroulette.persistence;

/**
 * 
 * @author felix
 *
 */
public class Comment implements Sendable{
	private long id;

	public long getId() {
		return id;
	}
	
	@Override
	public String getJson() {
		return "";
	}
}

package de.saschascherrer.code.blogroulette.persistence;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 
 * @author felix
 *
 */
@Entity
public class User {
	@Id
	private long id;

	public long getId() {
		return id;
	}
}

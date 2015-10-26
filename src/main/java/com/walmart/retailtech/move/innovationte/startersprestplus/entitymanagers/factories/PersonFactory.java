package com.walmart.retailtech.move.innovationte.startersprestplus.entitymanagers.factories;

import com.walmart.retailtech.move.innovationte.startersprestplus.entities.Person;

public class PersonFactory
{
	private PersonFactory(){}
	
	public static Person assemblePerson()
	{
		return new Person();
	}
}
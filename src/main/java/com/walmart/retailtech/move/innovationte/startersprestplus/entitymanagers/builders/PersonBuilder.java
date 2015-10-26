package com.walmart.retailtech.move.innovationte.startersprestplus.entitymanagers.builders;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.walmart.retailtech.move.innovationte.startersprestplus.entities.Person;
import com.walmart.retailtech.move.innovationte.startersprestplus.entitymanagers.factories.PersonFactory;

public class PersonBuilder
{
	private Person person = PersonFactory.assemblePerson();
	
	public PersonBuilder(){}
	
	public Person constructPerson()
	{
		return person;	
	}
	
	public Person constructPerson( int id, String location, String name )
	{
		person.setId( id );
		person.setLocation( location );
		person.setName( name );
		
		return person;	
	}

	public Person constructPerson( String requestTransaction )
		throws JsonSyntaxException
	{
		person = new Gson().fromJson( requestTransaction, Person.class );
		
		return person;
	}
}
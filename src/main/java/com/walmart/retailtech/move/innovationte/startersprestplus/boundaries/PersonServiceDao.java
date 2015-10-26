package com.walmart.retailtech.move.innovationte.startersprestplus.boundaries;

import javax.ws.rs.core.Response.Status;

import com.walmart.retailtech.move.innovationte.startersprestplus.entities.Person;

public interface PersonServiceDao
{
	public Person getAllPersonDetail( int offset, int limit ); 
	public Person getPersonDetail( Integer id );
	public Person postPersonDetail( String requestTransaction );
	public Person putPersonDetail( String requestTransaction );
	public Person deletePersonDetail( Integer id );
	
	public void setPersonResponseStatus( Status responseStatus );
	public Status getPersonResponseStatus();
}
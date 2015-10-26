package com.walmart.retailtech.move.innovationte.startersprestplus.interactors.implementations;

import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.gson.JsonSyntaxException;
import com.walmart.retailtech.move.innovationte.startersprestplus.boundaries.PersonServiceDao;
import com.walmart.retailtech.move.innovationte.startersprestplus.entities.Person;
import com.walmart.retailtech.move.innovationte.startersprestplus.entitymanagers.builders.PersonBuilder;
import com.walmart.retailtech.move.innovationte.startersprestplus.utilities.ObjectValidatorUtility;

@Service( "personService" )
public class PersonServiceDaoImpl
	implements PersonServiceDao
{
	private Status responseStatus;
	private static org.apache.log4j.Logger logger = Logger.getLogger( PersonServiceDaoImpl.class );
	
	@Override
	public Person getAllPersonDetail( int offset, int limit )
	{
		logger.debug( "Getting all " + offset + ", " + limit );
		
		PersonBuilder personBuilder = new PersonBuilder();
		Person p = personBuilder.constructPerson( -1, "ISD", "behowar" );
		
		if( !ObjectValidatorUtility.isNull( p ) )
			setPersonResponseStatus( Status.OK );
		else
			setPersonResponseStatus( Status.NO_CONTENT );
		
		return p;
	}
	
	@Override
	public Person getPersonDetail( Integer id )
	{
		logger.debug( id );
		
		PersonBuilder personBuilder = new PersonBuilder();
		Person p = personBuilder.constructPerson( id, "ISD", "behowar" );
		
		if( !ObjectValidatorUtility.isNull( p ) )
			setPersonResponseStatus( Status.OK );
		else
			setPersonResponseStatus( Status.NO_CONTENT );
		
		return p;
	}
	
	@Override
	public Person postPersonDetail( String requestTransaction )
	{
		PersonBuilder personBuilder = new PersonBuilder();
		
		try
		{
			Person p = personBuilder.constructPerson( requestTransaction );
			
			setPersonResponseStatus( Status.OK );
			
			return p;
		}
		catch( JsonSyntaxException jse )
		{
			setPersonResponseStatus( Status.BAD_REQUEST );
			
			throw jse;
		}
	}
	
	@Override
	public Person putPersonDetail( String requestTransaction )
	{
		PersonBuilder personBuilder = new PersonBuilder();
		
		try
		{
			Person p = personBuilder.constructPerson( requestTransaction );
			
			setPersonResponseStatus( Status.OK );
			
			return p;
		}
		catch( JsonSyntaxException jse )
		{
			setPersonResponseStatus( Status.BAD_REQUEST );
			
			throw jse;
		}
	}
	
	@Override
	public Person deletePersonDetail( Integer id )
	{	
		try
		{
			Person p = getPersonDetail( id );
			
			setPersonResponseStatus( Status.OK );
			
			return p;
		}
		catch( JsonSyntaxException jse )
		{
			setPersonResponseStatus( Status.BAD_REQUEST );
			
			throw jse;
		}
	}
	
	@Override
	public Status getPersonResponseStatus() 
	{
		return responseStatus;
	}

	@Override
	public void setPersonResponseStatus( Status responseStatus )
	{
		this.responseStatus = responseStatus;
	}
	
} 
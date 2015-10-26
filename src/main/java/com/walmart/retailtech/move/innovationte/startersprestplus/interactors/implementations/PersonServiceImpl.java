package com.walmart.retailtech.move.innovationte.startersprestplus.interactors.implementations;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.interceptor.InInterceptors;
import org.springframework.beans.factory.annotation.Autowired;

import com.walmart.retailtech.move.innovationte.startersprestplus.boundaries.PersonService;
import com.walmart.retailtech.move.innovationte.startersprestplus.boundaries.PersonServiceDao;
import com.walmart.retailtech.move.innovationte.startersprestplus.interactors.managers.ServiceRequestManager;
import com.walmart.retailtech.move.innovationte.startersprestplus.interactors.managers.ServiceResponseManager;

@Path( "/person" )
@Consumes()
@Produces()
public class PersonServiceImpl
	implements PersonService
{
	@Autowired
	private PersonServiceDao personServiceDao;
	@Autowired
	private ServiceRequestManager serviceRequestManager;
	@Autowired
	private ServiceResponseManager serviceResponseManager;
	
	public PersonServiceImpl(){};
	
	@HEAD
	@Path( "/" )
	@Consumes()
	@Produces( MediaType.TEXT_PLAIN )
	@Override
	public Response getPersonHead( @Context HttpHeaders headers )
	{
		// Calls a method to validate the headers
		//  process the HEAD request
		//  and return a response.
		return serviceRequestManager.delegateHeadRequest( headers );
	}
	
	@OPTIONS
	@Path( "/" )
	@Consumes()
	@Produces()
	@Override
	public Response getPersonOptions( @Context HttpHeaders headers )
	{
		// Calls a method to validate the headers
		//  process the OPTIONS request
		//  and return a response.
		return new ServiceRequestManager().delegateOptionsRequest( headers );
	}
	
	@GET
	@Path( "/" )
	@Consumes()
	@Produces( MediaType.APPLICATION_JSON )
	@Override
	public Response getAllPersonDetail( @Context HttpHeaders headers
			                          , @DefaultValue( "0" ) @QueryParam( "offset" ) int offset
			                          , @DefaultValue( "100" ) @QueryParam( "limit" ) int limit )                                          
	{
		// Calls a method to validate the headers
		//  process the GET request
		//  and return a response.
		return new ServiceRequestManager()
					.delegateGetAllRequest( headers, offset, limit );
	}
	
	@GET
	@Path( "/{id}" )
	@Consumes()
	@Produces( MediaType.APPLICATION_JSON )
	@Override
	public Response getPersonDetail( @Context HttpHeaders headers
			                       , @PathParam( "id" ) Integer id )                                          
	{
		// Calls a method to validate the headers
		//  process the GET request
		//  and return a response.
		return new ServiceRequestManager()
					.delegateGetRequest( headers, id );
	}
	
	@POST
	@Path( "/" )
	@Consumes( MediaType.APPLICATION_JSON)
	@Produces( MediaType.APPLICATION_JSON )
	@Override
	public Response postPersonDetail( @Context HttpHeaders headers 
			                        , String requestTransaction )                                         
	{
		// Calls a method to validate the headers
		//  process the POST request
		//  and return a response.
		return new ServiceRequestManager()
			.delegatePostRequest( headers, requestTransaction );
	}
	
	@PUT
	@Path( "/" )
	@Consumes( MediaType.APPLICATION_JSON )
	@Produces( MediaType.APPLICATION_JSON )
	@Override
	public Response putPersonDetail( @Context HttpHeaders headers 
			                        , String requestTransaction )                                         
	{
		// Calls a method to validate the headers
		//  process the PUT request
		//  and return a response.
		return new ServiceRequestManager()
			.delegatePutRequest( headers, requestTransaction );
	}
	
	@DELETE
	@Path( "/{id}" )
	@Consumes()
	@Produces( MediaType.APPLICATION_JSON )
	@Override
	public Response deletePersonDetail( @Context HttpHeaders headers 
			                          , @PathParam( "id" ) Integer id )                                         
	{
		// Calls a method to validate the headers
		//  process the DELETE request
		//  and return a response.
		return new ServiceRequestManager()
			.delegateDeleteRequest( headers, id );
	}
} 
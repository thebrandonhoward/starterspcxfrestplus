package com.walmart.retailtech.move.innovationte.startersprestplus.interactors.managers;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import com.google.gson.JsonSyntaxException;
import com.walmart.retailtech.move.innovationte.startersprestplus.boundaries.PersonServiceDao;
import com.walmart.retailtech.move.innovationte.startersprestplus.configurations.ManagerConfig;
import com.walmart.retailtech.move.innovationte.startersprestplus.entities.Person;
import com.walmart.retailtech.move.innovationte.startersprestplus.entitymanagers.builders.ServiceResponseBuilder;
import com.walmart.retailtech.move.innovationte.startersprestplus.interactors.implementations.PersonServiceDaoImpl;

public class ServiceRequestManager
{
	PersonServiceDao personServiceDao 
		= new PersonServiceDaoImpl();
	
	ServiceResponseBuilder serviceResponseBuilder 
		= new ServiceResponseBuilder();
	
	// This method validates the Headers and returns true or false
	public boolean delegateHeaderValidation( HttpHeaders headers )
	{
		ServiceHeadersManager serviceHeadersManager 
			= new ServiceHeadersManager();
		
		return serviceHeadersManager.delegateHeadersValidation( headers );
	}
	
	// This method processes the headers and HEAD method request and sends a response
	public Response delegateHeadRequest( HttpHeaders headers )
	{
		// Evaluate Header
		if( delegateHeaderValidation( headers ) )
		{
			// Return Response
			return new ServiceResponseManager().delegateHeadResponse();
		}
		else
		{
			return new ServiceResponseManager().delegateGenericResponse( ""
					                                                   , Status.UNAUTHORIZED
					                                                   , MediaType.TEXT_PLAIN_TYPE );
		}
		
	}
	
	// This method processes the headers and OPTIONS method request and sends a response
	public Response delegateOptionsRequest( HttpHeaders headers )
	{
		// Evaluate Header
		if( delegateHeaderValidation( headers ) )
		{
			// Return Response
			return new ServiceResponseManager().delegateOptionsResponse();
		}
		else
		{
			return new ServiceResponseManager().delegateGenericResponse( ""
							                                           , Status.UNAUTHORIZED
							                                           , MediaType.TEXT_PLAIN_TYPE );
		}
	}
	
	public Response delegateGetAllRequest( HttpHeaders headers
                                         , int offset
                                         , int limit )
	{
		// Evaluate Header
		if( delegateHeaderValidation( headers ) )
		{
			// Process request
			PersonServiceDao personServiceDao = new PersonServiceDaoImpl();
			Person person = personServiceDao.getAllPersonDetail( offset, limit );
			
			Status status = personServiceDao.getPersonResponseStatus();
			
			// Return response
			return new ServiceResponseManager().delegateGetResponse( person, status );
		}
		else
		{
			return new ServiceResponseManager().delegateGenericResponse( ""
									                                   , Status.UNAUTHORIZED
									                                   , MediaType.TEXT_PLAIN_TYPE );
		}
	}

	// This method processes the headers and GET request and sends a response
	public Response delegateGetRequest( HttpHeaders headers, int id )
	{
		// Evaluate Header
		if( delegateHeaderValidation( headers ) )
		{
			// Process request
			PersonServiceDao personServiceDao = new PersonServiceDaoImpl();
			Person person = personServiceDao.getPersonDetail( id );
			
			Status status = personServiceDao.getPersonResponseStatus();
			
			// Return response
			return new ServiceResponseManager().delegateGetResponse( person, status );
		}
		else
		{
			return new ServiceResponseManager().delegateGenericResponse( ""
									                                   , Status.UNAUTHORIZED
									                                   , MediaType.TEXT_PLAIN_TYPE );
		}
	}
	
	public Response delegatePostRequest( HttpHeaders headers
			                           , String requestTransaction )
	{
		//AnnotationConfigApplicationContext ctx
    	//= new AnnotationConfigApplicationContext( ManagerConfig.class );
		
		//ctx.refresh();
		//ManagerConfig config = ctx.getBean( ManagerConfig.class );
		
		// Evaluate Header
		if( delegateHeaderValidation( headers ) )
		{
			try
			{
				// Process request
				PersonServiceDao personServiceDao = new PersonServiceDaoImpl();
				Person person = personServiceDao.postPersonDetail( requestTransaction );
				
				Status status = personServiceDao.getPersonResponseStatus();
					
				// Return response
				return new ServiceResponseManager()
					.delegatePostResponse( person 
							             , status );
			}
			catch( JsonSyntaxException jse )
			{
				// Return Exception Response
				return new ServiceResponseManager()
					.delegateGenericResponse( jse.getMessage()
			                                , Status.BAD_REQUEST
			                                , MediaType.TEXT_PLAIN_TYPE );
			}					
		}
		else
		{
			return new ServiceResponseManager().delegateGenericResponse( ""
											                           , Status.UNAUTHORIZED
											                           , MediaType.TEXT_PLAIN_TYPE );
		}
	}
	
	public Response delegatePutRequest( HttpHeaders headers
		                              , String requestTransaction )
	{
		// Evaluate Header
		if( delegateHeaderValidation( headers ) )
		{
			try
			{
				// Process request
				PersonServiceDao personServiceDao = new PersonServiceDaoImpl();
				Person person = personServiceDao.putPersonDetail( requestTransaction );
				
				Status status = personServiceDao.getPersonResponseStatus();
					
				// Return response
				return new ServiceResponseManager()
					.delegatePutResponse( person 
							            , status );
			}
			catch( JsonSyntaxException jse )
			{
				// Return Exception Response
				return new ServiceResponseManager()
					.delegateGenericResponse( jse.getMessage()
			                                , Status.BAD_REQUEST
			                                , MediaType.TEXT_PLAIN_TYPE );
			}					
		}
		else
		{
			return new ServiceResponseManager().delegateGenericResponse( ""
											                           , Status.UNAUTHORIZED
											                           , MediaType.TEXT_PLAIN_TYPE );
		}
	}

	public Response delegateDeleteRequest( HttpHeaders headers
			                             , int id )
	{
		// Evaluate Header
		if( delegateHeaderValidation( headers ) )
		{
			try
			{
				// Process request
				PersonServiceDao personServiceDao = new PersonServiceDaoImpl();
				Person person = personServiceDao.deletePersonDetail( id );
				
				Status status = personServiceDao.getPersonResponseStatus();
					
				// Return response
				return new ServiceResponseManager()
					.delegateDeleteResponse( person 
							               , status );
			}
			catch( JsonSyntaxException jse )
			{
				// Return Exception Response
				return new ServiceResponseManager()
					.delegateGenericResponse( jse.getMessage()
			                                , Status.BAD_REQUEST
			                                , MediaType.TEXT_PLAIN_TYPE );
			}					
		}
		else
		{
			return new ServiceResponseManager().delegateGenericResponse( ""
											                           , Status.UNAUTHORIZED
											                           , MediaType.TEXT_PLAIN_TYPE );
		}
	}

}
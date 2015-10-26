package com.walmart.retailtech.move.innovationte.startersprestplus.interactors.managers;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.walmart.retailtech.move.innovationte.startersprestplus.entitymanagers.builders.ServiceResponseBuilder;

public class ServiceResponseManager
{	
	ServiceResponseBuilder serviceResponseBuilder
		= new ServiceResponseBuilder();
	
	public ServiceResponseManager(){}
	
	public Response delegateGenericResponse( Object object
										   , Status status 
			                               , MediaType type )
	{
		return serviceResponseBuilder.constructGenericServiceResponse( object, status, type );
	}
	
	public Response delegateHeadResponse()
	{
		return serviceResponseBuilder.constructHeadServiceResponse();
	}
	
	public Response delegateOptionsResponse()
	{
		return serviceResponseBuilder.constructOptionsServiceResponse();
	}
	
	public Response delegateGetResponse( Object object, Status status )
	{
		return serviceResponseBuilder.constructGetServiceResponse( object, status );
	}

	public Response delegatePostResponse( Object object, Status status ) 
	{
		return serviceResponseBuilder.constructPostServiceResponse( object, status );
	}
	
	public Response delegatePutResponse( Object object, Status status ) 
	{
		return serviceResponseBuilder.constructPutServiceResponse( object, status );
	}

	public Response delegateDeleteResponse( Object object, Status status )
	{
		return serviceResponseBuilder.constructDeleteServiceResponse( object, status );
	}

}
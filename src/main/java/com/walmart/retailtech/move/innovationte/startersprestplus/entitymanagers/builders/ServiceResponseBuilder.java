package com.walmart.retailtech.move.innovationte.startersprestplus.entitymanagers.builders;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import com.google.gson.Gson;

public class ServiceResponseBuilder
{
	public ServiceResponseBuilder(){}
	
	public Response constructGenericServiceResponse( Object object
												   , Status status
												   , MediaType type )
	{
		ResponseBuilder responseBuilder = Response.status( status );
			
		return responseBuilder.header( "Access-Control-Allow-Origin","*" )
			                  .header( "Access-Control-Allow-Methods"
			                		 , "OPTIONS,GET,HEAD,PUT,POST,DELETE,PATCH" )
			                  .header( "Access-Control-Allow-Headers"
			                		 , "Access-Control-Allow-Headers,Accept,Access-Control-Allow-Origin,subscriberid,consumerid,userid,version,token,password" )
			                  .entity( object )
							  .type( type )
			                  .build();	    
	}
	
	public Response constructHeadServiceResponse()
	{
		ResponseBuilder responseBuilder = Response.status( Status.OK );
			
		return responseBuilder.header( "Access-Control-Allow-Origin","*" )
			                  .header( "Access-Control-Allow-Methods"
			                		 , "OPTIONS,GET,HEAD,PUT,POST,DELETE,PATCH" )
			                  .header( "Access-Control-Allow-Headers"
			                		 , "Access-Control-Allow-Headers,Accept,Access-Control-Allow-Origin,subscriberid,consumerid,userid,version,token,password" )
			                  .entity( "" )
							  .type( MediaType.TEXT_PLAIN_TYPE )
			                  .build();	    
	}
	
	public Response constructOptionsServiceResponse()
	{
		ResponseBuilder responseBuilder = Response.status( Status.OK );
			
		return responseBuilder.header( "Access-Control-Allow-Origin","*" )
			                  .header( "Access-Control-Allow-Methods"
			                		 , "OPTIONS,GET,HEAD,PUT,POST,DELETE,PATCH" )
			                  .header( "Access-Control-Allow-Headers"
			                		 , "Access-Control-Allow-Headers,Accept,Access-Control-Allow-Origin,subscriberid,consumerid,userid,version,token,password" )
			                  .build();	    
	}
	
	public Response constructGetServiceResponse( Object object
	            							   , Status status )
	{
		ResponseBuilder responseBuilder = Response.status( status );
		
		String json = new Gson().toJson( object );
		
		return responseBuilder.header( "Access-Control-Allow-Origin","*" )
							  .header( "Access-Control-Allow-Methods","*" )
							  .header( "Access-Control-Allow-Headers"
								     , "Access-Control-Allow-Headers,Accept,Access-Control-Allow-Origin,subscriberid,consumerid,userid,version,token,password" )
							  .entity( json )
							  .type( MediaType.APPLICATION_JSON_TYPE )
							  .build();	    
	}

	public Response constructPostServiceResponse( Object object
			                                    , Status status ) 
	{
		ResponseBuilder responseBuilder = Response.status( status );
		
		String json = new Gson().toJson( object );
		
		return responseBuilder.header( "Access-Control-Allow-Origin","*" )
							  .header( "Access-Control-Allow-Methods","*" )
							  .header( "Access-Control-Allow-Headers"
								     , "Access-Control-Allow-Headers,Accept,Access-Control-Allow-Origin,subscriberid,consumerid,userid,version,token,password" )
							  .entity( json )
							  .type( MediaType.APPLICATION_JSON_TYPE )
							  .build();	    
	}

	public Response constructPutServiceResponse( Object object
            								   , Status status ) 
	{
		ResponseBuilder responseBuilder = Response.status( status );
		
		String json = new Gson().toJson( object );
		
		return responseBuilder.header( "Access-Control-Allow-Origin","*" )
							  .header( "Access-Control-Allow-Methods","*" )
							  .header( "Access-Control-Allow-Headers"
							         , "Access-Control-Allow-Headers,Accept,Access-Control-Allow-Origin,subscriberid,consumerid,userid,version,token,password" )
							  .entity( json )
							  .type( MediaType.APPLICATION_JSON_TYPE )
							  .build();	    
	}

	public Response constructDeleteServiceResponse( Object object
            								      , Status status ) 
	{
		ResponseBuilder responseBuilder = Response.status( status );
		
		String json = new Gson().toJson( object );
		
		return responseBuilder.header( "Access-Control-Allow-Origin","*" )
							  .header( "Access-Control-Allow-Methods","*" )
							  .header( "Access-Control-Allow-Headers"
							         , "Access-Control-Allow-Headers,Accept,Access-Control-Allow-Origin,subscriberid,consumerid,userid,version,token,password" )
							  .entity( json )
							  .type( MediaType.APPLICATION_JSON_TYPE )
							  .build();	    
	}
}
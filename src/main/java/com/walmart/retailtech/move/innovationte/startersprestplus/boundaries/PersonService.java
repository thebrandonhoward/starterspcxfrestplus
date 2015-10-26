package com.walmart.retailtech.move.innovationte.startersprestplus.boundaries;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

public interface PersonService
{
	public Response getPersonHead( HttpHeaders headers );
	public Response getPersonOptions( HttpHeaders headers );
	public Response getAllPersonDetail( HttpHeaders headers
            						  , int offset
            						  , int limit );
	public Response getPersonDetail( HttpHeaders headers
			                       , Integer id );
	public Response postPersonDetail( HttpHeaders headers
			                        , String requestTransaction );
	public Response putPersonDetail( HttpHeaders headers
			                       , String requestTransaction );
	public Response deletePersonDetail( HttpHeaders headers
			                          , Integer id );
}
package com.walmart.retailtech.move.innovationte.startersprestplus.utilities;

import javax.ws.rs.core.HttpHeaders;

import com.walmart.retailtech.move.innovationte.startersprestplus.entities.Headers;
import com.walmart.retailtech.move.innovationte.startersprestplus.entitymanagers.builders.HeadersBuilder;

public class HeadersValidatorUtility 
{
	private boolean valid;
	private Headers headersObj;
	
	public HeadersValidatorUtility() {};

	public HeadersValidatorUtility( HttpHeaders headers )
	{
		this.valid = validateHeaders( headers );
		this.headersObj = getHeadersObj();
	}
	
	private boolean validateHeaders( HttpHeaders headers )
	{
		Boolean validHeaders = validateHeadersKeys( headers );
		
		if( validHeaders )
		{
			headersObj = new HeadersBuilder().constructHeaders( headers );
			
			if(   headersObj.getConsumerid().equals( "" )   
			   || headersObj.getSubscriberid().equals( "" ) 
		       || headersObj.getUserid().equals( "" ) )
				return false;
		
			if(   headersObj.getToken().equals( "" )  
			   && headersObj.getPassword().equals( "" ) ) 
				return false;
			
			return validHeaders;

		} else {
			return validHeaders;
		}
	}
	
	private boolean validateHeadersKeys( HttpHeaders headers )
	{
		if(   headers.getRequestHeaders().containsKey( "consumerid" ) 
		   && headers.getRequestHeaders().containsKey( "subscriberid" )
		   && headers.getRequestHeaders().containsKey( "userid" )
		   && headers.getRequestHeaders().containsKey( "password" )
		   && headers.getRequestHeaders().containsKey( "version" )
		   && headers.getRequestHeaders().containsKey( "token" ) )
			return true;
		
		return false;
	}
	
	public boolean getValid()
	{
		return this.valid;
	}
	
	public Headers getHeadersObj()
	{
		return this.headersObj;
	}
}
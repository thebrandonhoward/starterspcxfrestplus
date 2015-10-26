package com.walmart.retailtech.move.innovationte.startersprestplus.entitymanagers.builders;

import javax.ws.rs.core.HttpHeaders;

import com.walmart.retailtech.move.innovationte.startersprestplus.entities.Headers;
import com.walmart.retailtech.move.innovationte.startersprestplus.entitymanagers.factories.HeadersFactory;

public class HeadersBuilder 
{
	public HeadersBuilder() {}
	
	public Headers constructHeaders( HttpHeaders headers )
	{
		Headers factoryHeaders = new HeadersFactory().assembleHeaders();
		
		factoryHeaders.setConsumerid( headers.getRequestHeader( "consumerid" ).get(0) );	
		factoryHeaders.setSubscriberid( headers.getRequestHeader( "subscriberid" ).get(0) );	
		factoryHeaders.setUserid( headers.getRequestHeader( "userid" ).get(0) );
		factoryHeaders.setPassword( headers.getRequestHeader( "password" ).get(0) );
		factoryHeaders.setVersion( Double.valueOf( headers.getRequestHeader( "version" ).get(0) ) );
		factoryHeaders.setToken( headers.getRequestHeader( "token" ).get(0) );
		
		return factoryHeaders;
	}
}
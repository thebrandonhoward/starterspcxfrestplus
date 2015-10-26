package com.walmart.retailtech.move.innovationte.startersprestplus.interactors.managers;

import javax.ws.rs.core.HttpHeaders;

import com.walmart.retailtech.move.innovationte.startersprestplus.entities.Headers;
import com.walmart.retailtech.move.innovationte.startersprestplus.utilities.HeadersValidatorUtility;

public class ServiceHeadersManager 
{
	private boolean valid;
	private Headers headersObj;
	
	public ServiceHeadersManager() {}
	
	public ServiceHeadersManager( HttpHeaders headers )
	{
		this.valid = delegateHeadersValidation( headers );
	}
	
	public boolean delegateHeadersValidation( HttpHeaders headers )
	{
		this.valid = new HeadersValidatorUtility( headers ).getValid();
		return this.valid;
	}
	
	public Headers delegateFinalHeaders()
	{
		return this.headersObj;
	}
	
}
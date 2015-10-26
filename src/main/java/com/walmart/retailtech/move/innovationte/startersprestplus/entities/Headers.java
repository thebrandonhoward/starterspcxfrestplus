package com.walmart.retailtech.move.innovationte.startersprestplus.entities;

import com.walmart.retailtech.move.innovationte.startersprestplus.parameters.HeaderParameters;

public class Headers 
{
	private String consumerid;
	private String subscriberid;
	private String userid;
	private String password;
	private double version;
	private String token;
	
	public Headers(){}
	
	public String getConsumerid() 
	{
		return consumerid;
	}
	public void setConsumerid(String consumerid) 
	{
		if( !consumerid.equals( null ) &&
		    !consumerid.equals( "" ) )
			this.consumerid = consumerid;
		else
			this.consumerid = HeaderParameters.HEADER_CONSUMERID_DEFAULT;
	}
	
	public String getSubscriberid() 
	{
		return subscriberid;
	}
	public void setSubscriberid(String subscriberid) 
	{
		if( !subscriberid.equals( null ) &&
			!subscriberid.equals( "" ) )
		    this.subscriberid = subscriberid;
		else
			this.subscriberid = HeaderParameters.HEADER_SUBSCRIBERID_DEFAULT;
	}
	
	public String getUserid() 
	{
		return userid;
	}
	public void setUserid(String userid) 
	{
		if( !userid.equals( null ) &&
			!userid.equals( "" ) )
			this.userid = userid;
	    else
			this.userid = HeaderParameters.HEADER_USERID_DEFAULT;
	}
	
	public String getPassword() 
	{
		return password;
	}
	public void setPassword(String password) 
	{
		if( !password.equals( null ) )
		    this.password = password;
		else
			this.password = HeaderParameters.HEADER_PASSWORD_DEFAULT;
	}
	
	public double getVersion() 
	{
		return version;
	}
	public void setVersion(double version) 
	{
		if( version >= 1.0 )
			this.version = version;
		else
			this.version = HeaderParameters.HEADER_VERSION_DEFAULT;
	}
	
	public String getToken() 
	{
		return token;
	}
	public void setToken(String token) 
	{
		if( !token.equals( null ) )
			this.token = token;
		else
			this.token = HeaderParameters.HEADER_TOKEN_DEFAULT;
	}
	
}
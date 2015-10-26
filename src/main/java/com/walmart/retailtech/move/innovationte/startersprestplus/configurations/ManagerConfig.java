package com.walmart.retailtech.move.innovationte.startersprestplus.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.walmart.retailtech.move.innovationte.startersprestplus.entitymanagers.builders.ServiceResponseBuilder;
import com.walmart.retailtech.move.innovationte.startersprestplus.interactors.managers.ServiceRequestManager;
import com.walmart.retailtech.move.innovationte.startersprestplus.interactors.managers.ServiceResponseManager;

@Configuration
@ComponentScan( "com.walmart.retailtech.move.innovationte.startersprestplus" )
public class ManagerConfig
{
	@Bean
	public ServiceRequestManager serviceRequestManager()
	{
		return new ServiceRequestManager();
	}
	
	@Bean
	public ServiceResponseBuilder serviceResponseBuilder()
	{
		return new ServiceResponseBuilder();
	}
	
	@Bean
	public ServiceResponseManager serviceResponseManager()
	{
		return new ServiceResponseManager();
	}
}
package com.walmart.retailtech.move.innovationte.startersprestplus.configurations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import javax.ws.rs.ext.RuntimeDelegate;

import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingMessage;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.walmart.retailtech.move.innovationte.startersprestplus.boundaries.PersonServiceDao;
import com.walmart.retailtech.move.innovationte.startersprestplus.entitymanagers.builders.ServiceResponseBuilder;
import com.walmart.retailtech.move.innovationte.startersprestplus.interactors.implementations.ExceptionImpl;
import com.walmart.retailtech.move.innovationte.startersprestplus.interactors.implementations.PersonServiceImpl;
import com.walmart.retailtech.move.innovationte.startersprestplus.interactors.implementations.PersonServiceDaoImpl;
import com.walmart.retailtech.move.innovationte.startersprestplus.interactors.managers.ServiceRequestManager;
import com.walmart.retailtech.move.innovationte.startersprestplus.interactors.managers.ServiceResponseManager;
import com.walmart.retailtech.move.innovationte.startersprestplus.listeners.LogWriterInterceptor;

@Configuration
@ComponentScan( "com.walmart.retailtech.move.innovationte.startersprestplus" )
public class AppConfig
{
	/*
	 * Identifies the application path that serves as the base URI for all resource 
	 * URIs provided by Path. May only be applied to a subclass of Application.
     * When published in a Servlet container, the value of the application path may 
     * be overridden using a servlet-mapping element in the web.xml.
	 */
	@ApplicationPath( "/" )
	public class JaxRsApiApplication
		extends Application{}

	@Bean( destroyMethod = "shutdown" )
	public SpringBus cxf()
	{
		return new SpringBus();
	}

	/*
	 * (@DependsOn)Beans on which the current bean depends. Any beans specified 
	 * are guaranteed to be created by the container before this bean. 
	 * Used infrequently in cases where a bean does not explicitly 
	 * depend on another through properties or constructor arguments, 
	 * but rather depends on the side effects of another bean's initialization.
	 */
	@Bean
	@DependsOn( "cxf" )
	public Server jaxRsServer( ApplicationContext appContext )
	{
		/*
		 * Bean to help easily create Server endpoints for JAX-RS.
		 */
		JAXRSServerFactoryBean factory 
			= RuntimeDelegate.getInstance()
				             .createEndpoint( jaxRsApiApplication()
						                    , JAXRSServerFactoryBean.class );
		
		/*
		 * Root resource classes are POJOs (Plain Old Java Objects) that are annotated with 
		 * @Path have at least one method annotated with @Path or a resource method designator 
		 * annotation such as @GET, @PUT, @POST, @DELETE. Resource methods are methods of a 
		 * resource class annotated with a resource method designator.
		 */
		factory.setServiceBeans( Arrays.<Object> asList( personServiceImpl()
				                                       , exceptionImpl() ) );
		factory.setAddress( factory.getAddress() );
		factory.setProvider( jsonProvider() );
		
		List<Interceptor<? extends Message>> l = new ArrayList<>();
		
		//l.add( new LoggingInInterceptor() );
		l.add( new LogWriterInterceptor( UUID.randomUUID().toString(), Phase.RECEIVE) );
		factory.setInInterceptors( l );
		
		return factory.create();
	}
	
	@Bean
	public JaxRsApiApplication jaxRsApiApplication()
	{
		return new JaxRsApiApplication();
	}

	@Bean
	public JacksonJsonProvider jsonProvider()
	{
		return new JacksonJsonProvider();
	}

	@Bean
	public PersonServiceDao personServiceDao()
	{
		return new PersonServiceDaoImpl();
	}
	
	@Bean
	public PersonServiceImpl personServiceImpl()
	{
		return new PersonServiceImpl();
	}
	
	@Bean
	public ExceptionImpl exceptionImpl()
	{
		return new ExceptionImpl();
	}
}
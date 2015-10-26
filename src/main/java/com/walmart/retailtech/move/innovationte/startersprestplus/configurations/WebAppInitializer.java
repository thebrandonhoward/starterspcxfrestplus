package com.walmart.retailtech.move.innovationte.startersprestplus.configurations;

import java.util.Set;

import javax.servlet.ServletContext;  
import javax.servlet.ServletException; 
import javax.servlet.ServletRegistration.Dynamic;  

import org.apache.cxf.transport.servlet.CXFServlet;  
import org.springframework.web.WebApplicationInitializer;  
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext; 

public class WebAppInitializer
	implements WebApplicationInitializer
{
	/*
	 * (non-Javadoc)
	 * @see org.springframework.web.WebApplicationInitializer#onStartup(javax.servlet.ServletContext)
	 * Configure the deployment descriptor using only Java,
	 * no web.xml is needed.
	 */
	public void onStartup( ServletContext servletContext )
			throws ServletException 
	{  
		servletContext.addListener( new ContextLoaderListener( createWebAppContext() ) );
        
		addApacheCxfServlet( servletContext );  
    }
	
	private void addApacheCxfServlet( ServletContext servletContext )
	{
        CXFServlet cxfServlet = new CXFServlet();

        Dynamic dynamicAppServlet = servletContext.addServlet( "CXFServlet"
        		                                             , cxfServlet );
        dynamicAppServlet.setLoadOnStartup( 1 );

        dynamicAppServlet.addMapping( "/api/*" );
    }

    private WebApplicationContext createWebAppContext()
    {
        AnnotationConfigWebApplicationContext appContext
        	= new AnnotationConfigWebApplicationContext();
        appContext.register( AppConfig.class );
        appContext.register( ManagerConfig.class );
     
        return appContext;
    }
}
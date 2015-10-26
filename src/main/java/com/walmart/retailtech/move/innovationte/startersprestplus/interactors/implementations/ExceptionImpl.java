package com.walmart.retailtech.move.innovationte.startersprestplus.interactors.implementations;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path( "/exception" )
public class ExceptionImpl
{
    public ExceptionImpl() { }

    @GET
    @Produces( MediaType.TEXT_PLAIN )
    public String generateException()
    		throws Exception
    {
        throw new Exception("generateException from ExceptionResource");
    }
}
package com.walmart.retailtech.move.innovationte.startersprestplus.listeners;

	/**
	 * Licensed to the Apache Software Foundation (ASF) under one
	 * or more contributor license agreements. See the NOTICE file
	 * distributed with this work for additional information
	 * regarding copyright ownership. The ASF licenses this file
	 * to you under the Apache License, Version 2.0 (the
	 * "License"); you may not use this file except in compliance
	 * with the License. You may obtain a copy of the License at
	 *
	 * http://www.apache.org/licenses/LICENSE-2.0
	 *
	 * Unless required by applicable law or agreed to in writing,
	 * software distributed under the License is distributed on an
	 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
	 * KIND, either express or implied. See the License for the
	 * specific language governing permissions and limitations
	 * under the License.
	 */

	import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.SequenceInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.cxf.common.injection.NoJSR250Annotations;
import org.apache.cxf.common.logging.LogUtils;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.interceptor.AbstractLoggingInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingMessage;
import org.apache.cxf.io.CachedOutputStream;
import org.apache.cxf.io.CachedWriter;
import org.apache.cxf.io.DelegatingInputStream;
import org.apache.cxf.message.Message;
import org.apache.cxf.service.model.EndpointInfo;
import org.apache.cxf.service.model.InterfaceInfo;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.walmart.retailtech.move.innovationte.startersprestplus.interactors.implementations.PersonServiceDaoImpl;

	/**
	 * A simple logging handler which outputs the bytes of the message to the
	 * Logger.
	 */
	@NoJSR250Annotations
	public class LogWriterInterceptor
		extends AbstractLoggingInterceptor
	{
	    public LogWriterInterceptor( String id, String phase )
	    {
			super( id, phase );
			
			LogUtils.setLoggerClass( org.apache.cxf.common.logging.Log4jLogger.class );
		}

		private static final Logger LOG = LogUtils.getLogger( LoggingInInterceptor.class );

		//private static org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger( LogWriterInterceptor.class );
	    
		public void handleMessage( Message message ) 
	    	throws Fault
	    {	
	    	/*
	    	 * Validate the requestor
	    	 */
	    
	    	// Begin by parsing the header.
	    	String method = message.get( Message.HTTP_REQUEST_METHOD).toString();
	    	if( method.equals( "OPTIONS" ) ||
	    		method.equals( "HEAD" ) )
	    		return;
	    	
    		JsonObject jsonHeaders = new JsonObject();
	    	try
	    	{
	    		String headers 
	    			= new Gson().toJson( message.get( Message.PROTOCOL_HEADERS ) );
	    	
	    		jsonHeaders = new Gson().fromJson( headers, JsonObject.class );
	    		
	    	} catch( Exception e )
	    		{
	    			throw new Fault( "Access Absolutely Denied!" + e.getMessage(), LOG );
	    		}      
	    	
	    	String user = jsonHeaders.get( "userid" ).getAsString();
	    	if( user.trim().equals("") || user.equals( null ) )
    			throw new Fault( "Access Absolutely Denied!", LOG );
	    	
	    	// Validate user credentials
	    	HttpClient httpClient = HttpClientBuilder.create().build();
			
	    	HttpGet get = new HttpGet( "http://ldap-api.dev.test-w4spenc.isd-enterpriseinfomgmt.qa.walmart.com/ldap-ui/service/query/"
			                         + user 
			                         + "/dc=homeoffice,dc=wal-mart,dc=com" );
			
	    	HttpResponse response = null;
			try 
			{
				response = httpClient.execute( get );
				
				StatusLine status = response.getStatusLine();
				if( status.getStatusCode() != 200 )
					throw new Fault( "Access Absolutely Denied!", LOG );
					//throw new RuntimeException( "Access Denied" );
					
				//( "**** DATA ****" );
				//HttpEntity ent = response.getEntity();
				//System.out.println( "Entity: " + EntityUtils.toString( ent ) );
					
			} catch ( ClientProtocolException cpe ) 
				{
					throw new Fault( cpe.getMessage(), LOG );
					//throw new RuntimeException( cpe );
				} 
			  catch ( IOException ioe ) 
				{
					throw new Fault( ioe.getMessage(), LOG );
					//throw new RuntimeException( ioe );
				}
	    	
			/*
			 * Uncomment the below code if you need to
			 * do something with the incoming payload.
			 */
			
			// Parse the request message payload
			/*
	    	String payload = null;
	    	
	        Logger logger = getMessageLogger( message );
	        if ( writer != null || logger.isLoggable(Level.INFO) )
	        {
	        	payload = logging(logger, message).toString().replaceAll("\n", "");
	        	// Do something with the payload...
	        }
	        */
	    }

	    public Logger getMessageLogger( Message message )
	    {
	        Endpoint ep = message.getExchange().getEndpoint();
	        if ( ep == null || ep.getEndpointInfo() == null )
	        {
	            return getLogger();
	        }
	        
	        EndpointInfo endpoint = ep.getEndpointInfo();
	        if ( endpoint.getService() == null )
	        {
	            return getLogger();
	        }
	        
	        Logger logger = endpoint.getProperty( "MessageLogger", Logger.class );
	        if (logger == null)
	        {
	            String serviceName = endpoint.getService().getName().getLocalPart();
	            InterfaceInfo iface = endpoint.getService().getInterface();
	            String portName = endpoint.getName().getLocalPart();
	            String portTypeName = iface.getName().getLocalPart();
	            String logName = "org.apache.cxf.services." + serviceName + "." 
	                + portName + "." + portTypeName;
	            logger = LogUtils.getL7dLogger(this.getClass(), null, logName);
	            endpoint.setProperty("MessageLogger", logger);
	        }
	        
	        return logger;
	    }
	    
	    protected StringBuilder logging( Logger logger, Message message )
	    	throws Fault
	    {
	    	StringBuilder sb = new StringBuilder();
	        if (message.containsKey(LoggingMessage.ID_KEY)) {
	            return sb;
	        }
	        String id = (String)message.getExchange().get(LoggingMessage.ID_KEY);
	        if (id == null) {
	            id = LoggingMessage.nextId();
	            message.getExchange().put(LoggingMessage.ID_KEY, id);
	        }
	        message.put(LoggingMessage.ID_KEY, id);
	        final LoggingMessage buffer 
	            = new LoggingMessage("Inbound Message\n----------------------------", id);
	        
	        if (!Boolean.TRUE.equals(message.get(Message.DECOUPLED_CHANNEL_MESSAGE))) {
	            // avoid logging the default responseCode 200 for the decoupled responses
	            Integer responseCode = (Integer)message.get(Message.RESPONSE_CODE);
	            if (responseCode != null) {
	                buffer.getResponseCode().append(responseCode);
	            }
	        }

	        String encoding = (String)message.get(Message.ENCODING);

	        if (encoding != null) {
	            buffer.getEncoding().append(encoding);
	        }
	        String httpMethod = (String)message.get(Message.HTTP_REQUEST_METHOD);
	        if (httpMethod != null) {
	            buffer.getHttpMethod().append(httpMethod);
	        }
	        String ct = (String)message.get(Message.CONTENT_TYPE);
	        if (ct != null) {
	            buffer.getContentType().append(ct);
	        }
	        
	        /*
	        Object headers = message.get(Message.PROTOCOL_HEADERS);

	        if (headers != null) {
	            buffer.getHeader().append(""); //.append(headers);
	        }
	        */
	        
	        String uri = (String)message.get(Message.REQUEST_URL);
	        if (uri == null) {
	            String address = (String)message.get(Message.ENDPOINT_ADDRESS);
	            uri = (String)message.get(Message.REQUEST_URI);
	            if (uri != null && uri.startsWith("/")) {
	                if (address != null && !address.startsWith(uri)) {
	                    if (address.endsWith("/") && address.length() > 1) {
	                        address = address.substring(0, address.length()); 
	                    }
	                    uri = address + uri;
	                }
	            } else {
	                uri = address;
	            }
	        } 
	        if (uri != null) {
	            buffer.getAddress().append(uri);
	            String query = (String)message.get(Message.QUERY_STRING);
	            if (query != null) {
	                buffer.getAddress().append("?").append(query);
	            }
	        }
	        
	        if (!isShowBinaryContent() && isBinaryContent(ct)) {
	            buffer.getMessage().append(BINARY_CONTENT_MESSAGE).append('\n');
	            log(logger, buffer.toString());
	            return sb;
	        }
	        
	        InputStream is = message.getContent(InputStream.class);
	        if (is != null) {
	            sb = logInputStream(message, is, buffer, encoding, ct);
	        } else {
	            Reader reader = message.getContent(Reader.class);
	            if (reader != null) {
	                logReader(message, reader, buffer);
	            }
	        }
	        log(logger, formatLoggingMessage(buffer));
	        
	        return sb;
	    }

	    protected void logReader(Message message, Reader reader, LoggingMessage buffer) {
	        try {
	            CachedWriter writer = new CachedWriter();
	            IOUtils.copyAndCloseInput(reader, writer);
	            message.setContent(Reader.class, writer.getReader());
	            
	            if (writer.getTempFile() != null) {
	                //large thing on disk...
	                buffer.getMessage().append("\nMessage (saved to tmp file):\n");
	                buffer.getMessage().append("Filename: " + writer.getTempFile().getAbsolutePath() + "\n");
	            }
	            if (writer.size() > limit && limit != -1) {
	                buffer.getMessage().append("(message truncated to " + limit + " bytes)\n");
	            }
	            writer.writeCacheTo(buffer.getPayload(), limit);
	        } catch (Exception e) {
	            throw new Fault(e);
	        }
	    }
	    protected StringBuilder logInputStream(Message message, InputStream is, LoggingMessage buffer,
	                                  String encoding, String ct) {
	    	StringBuilder sb = new StringBuilder();
	        CachedOutputStream bos = new CachedOutputStream();
	        if (threshold > 0) {
	            bos.setThreshold(threshold);
	        }
	        try {
	            // use the appropriate input stream and restore it later
	            InputStream bis = is instanceof DelegatingInputStream 
	                ? ((DelegatingInputStream)is).getInputStream() : is;
	            

	            //only copy up to the limit since that's all we need to log
	            //we can stream the rest
	            IOUtils.copyAtLeast(bis, bos, limit == -1 ? Integer.MAX_VALUE : limit);
	            bos.flush();
	            bis = new SequenceInputStream(bos.getInputStream(), bis);
	            
	            // restore the delegating input stream or the input stream
	            if (is instanceof DelegatingInputStream) {
	                ((DelegatingInputStream)is).setInputStream(bis);
	            } else {
	                message.setContent(InputStream.class, bis);
	            }

	            if (bos.getTempFile() != null) {
	                //large thing on disk...
	                buffer.getMessage().append("\nMessage (saved to tmp file):\n");
	                buffer.getMessage().append("Filename: " + bos.getTempFile().getAbsolutePath() + "\n");
	            }
	            if (bos.size() > limit && limit != -1) {
	                buffer.getMessage().append("(message truncated to " + limit + " bytes)\n");
	            }
	           
	            writePayload(buffer.getPayload(), bos, encoding, ct); 
	            
	            sb = buffer.getPayload();
	                
	            bos.close();
	        } catch (Exception e) {
	            throw new Fault(e);
	        }
	        
	        return sb;
	    }

	    protected String formatLoggingMessage(LoggingMessage loggingMessage) {
	        return loggingMessage.toString();
	    }

	    @Override
	    protected Logger getLogger() {
	        return LOG;
	    }
	}
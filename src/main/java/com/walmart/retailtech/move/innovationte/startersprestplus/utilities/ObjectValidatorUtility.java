package com.walmart.retailtech.move.innovationte.startersprestplus.utilities;

public class ObjectValidatorUtility
{
	public static boolean isNull( Object object )
	{
		if( !object.equals( null ) )
			return false;
		
		return true;
	}
	
	public static boolean isSpaces( Object object )
	{
		if( !object.equals( "" ) )
			return false;
		
		return true;
	}
	
	public static boolean isNullOrSpaces( Object object )
	{
		if( !object.equals( null ) && !object.equals( "" )  )
			return false;
		
		return true;
	}
}
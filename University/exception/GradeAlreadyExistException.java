package com.University.exception;

public class GradeAlreadyExistException extends RuntimeException
{
	public  GradeAlreadyExistException(String msg)
	{
		super(msg);
	}
}

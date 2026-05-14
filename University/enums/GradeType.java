package com.University.enums;

public enum GradeType 
{
	A(10),
	B(8),
	C(6),
	D(5),
	E(4),
	F(3);
	int value;
	GradeType(int value)
	{
		this.value = value;
	}
	
	public int getValue() 
	{
		return value;
	}
	
	
	
	
}

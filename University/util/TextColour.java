package com.University.util;

public class TextColour {
	public static final String RESET = "\u001B[0m";
	public static final String BOLD = "\u001B[1m";
	
	public static final String RED = "\u001B[31m";
	public static final String YELLOW = "\u001B[33m";
	public static final String CYAN = "\u001B[36m";
	public static final String	MAGENTA	= "\u001B[35m";
	public static final String	GREEN = "\u001B[32m";
	public static final String	BLUE = "\u001B[34m";
	
	public static final String	BG_WHITE= "\u001B[47m";
	public static final String	HIGH_INTENSITY = "\u001B[1m";
	
	public static final String	ITALIC = "\u001B[3m";
	
	public static String color(String text, String colorCode) {
        return colorCode + text + RESET;
    }
	
	public static String color(String text, String colorCode, String func) {
		return colorCode + func + text + RESET;
	}
}

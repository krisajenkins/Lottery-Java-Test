package com.lottery;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class App {
	private static DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/mm/yyyy");
	
    public static void main(String [] args) {
    	// Parse out the command line arguments.
    	DateTime endDate = formatter.parseDateTime(args[0]);
    	
    	List<Integer> selection = new ArrayList<Integer>();
    	for (int i = 1; i < args.length; i++) {
    		Integer number = Integer.parseInt(args[i]);
    		selection.add(number);
    	}
    	
    	System.out.format("Date was: %s\n", endDate);
    	System.out.format("Selection was: %s\n", selection);
    }
}

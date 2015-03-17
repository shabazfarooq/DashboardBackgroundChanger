package main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

	public static void main (String[] args) {
		
		//
		// Copy to location
		//
		
    	File destination1           = new File("/System/Library/CoreServices/Dock.app/Contents/Resources/dbgrid.png");
    	File destination2           = new File("/System/Library/CoreServices/Dock.app/Contents/Resources/dbgrid@2x.png");
    	
    	File backgroundFileLocation = null;
    	
    	
        //
        // Get and list all files in current directory
        //

        // - Get all file names in current directory
        File currentFileLocation = new File(".");

        // - Insert all file names into a list
        File[] listOfFilesInDir  = currentFileLocation.listFiles();
        LinkedList<File> listOfImagesInDir = new LinkedList<File>();
        
        
        boolean imageFileSelected  = false;
        
        while (imageFileSelected == false) {
        	
        	System.out.println("Select the number cooresponding the image file you'd like to set as your Dashboard Background:");
        	System.out.println("");
        	System.out.println("0  -  EXIT PROGRAM");
        
        	// - List current directory file names
	        for (int i=0; i < listOfFilesInDir.length; i++) {
	        	
	        	if (listOfFilesInDir[i].toString().endsWith(".png") || listOfFilesInDir[i].toString().endsWith(".jpg")) {
	        		listOfImagesInDir.add(listOfFilesInDir[i]);
	        		
	        		System.out.println(listOfImagesInDir.size() + "  -  " + listOfImagesInDir.get(listOfImagesInDir.size()-1));
	        	}
	        	
	        	
	        }
	        
			// - Get user input
			@SuppressWarnings("resource")
			Scanner userInput         = new Scanner(System.in);
			System.out.print("\nInput: ");
			String userInputNumber    = userInput.next();
	        
	        // - Evaluate user input
	        if (userInputNumber.equals("0")) {
	        	System.exit(1);
	        } else {
	        	//check isDigit, is within list range

	        	backgroundFileLocation = listOfImagesInDir.get( Integer.parseInt(  (userInputNumber))-1 );
	        	
	        	imageFileSelected = true;
	        }
	        
        }
        

        //
        // Copy image to destination
        //
        
        System.out.println("\nApplying " + backgroundFileLocation.toString() + " as your dashboard background!\n");
        
        
        try {
			Files.copy(backgroundFileLocation.toPath(), destination1.toPath(), StandardCopyOption.REPLACE_EXISTING);
			Files.copy(backgroundFileLocation.toPath(), destination2.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		} 

        System.out.println("...SUCCESS!!");

    }
    
    
}

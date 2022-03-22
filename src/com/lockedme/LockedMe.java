package com.lockedme;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class LockedMe {
    File folder_name;
    static String FOLDER_DIR;	


    public LockedMe() {
    	FOLDER_DIR = System.getProperty("user.dir");
        folder_name = new File(FOLDER_DIR+"/files");
        if (!folder_name.exists())
            folder_name.mkdirs();
        System.out.println("Folder : "+ folder_name.getAbsolutePath());
    }

    private static final String WELCOME_SCREEN =
            "*****************  LockedMe.com *******************"+
                    "***************** MANIKANTA TANGUDU *******************";{
                System.out.println("\n*************************************************\n");
                    }
    
    private static final String MAIN_MENU_SCREEN =
           "\n |********  MAIN MENU  ********| \n"+
               "\n Select any of the options:- \n"+
                    "1 -> List of user files in FOLDER_DIR\n"+
                    "2 -> Add, Delete or Search user specified file\n"+
                    "3 -> Quit ";
    
    private static final String SECONDARY_MENU_SCREEN =
            "\n |********  SECONDARY MENU ********| \n"+
            "   \nSelect any of the options: \n"+
                    "   a -> Add a user file\n"+
                    "   b -> Delete a user file\n"+
                    "   c -> Search a user file\n"+
                    "   d -> Return to Main menu";

    void showPrimaryMenu() {
        System.out.println(MAIN_MENU_SCREEN);
        try{
            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            switch (option){
                case 1 : {
                    showFiles();
                    showPrimaryMenu();
                }
                case 2 : {
                    showSecondaryMenu();
                }
                case 3 : {
                    System.out.println("**********Thank You*********");
                    System.exit(0);
                }
                default: 
                	 System.out.println("Please Select options 1, 2 or 3");
                	 showPrimaryMenu();
            }
        }
        catch (Exception e){
            System.out.println("Please Select options 1, 2 or 3");
            showPrimaryMenu();
        }
    }



    void showFiles() {
        if (folder_name.list().length==0)
            System.out.println("empty folder");
        else {
            String[] list = folder_name.list();
            System.out.println("The files in "+ folder_name +" are :");
            Arrays.sort(list);
            for (String str:list) {
                System.out.println(str);
            }
            
        }
    }

    void addFile(String filename) throws IOException {
        File filepath = new File(folder_name +"/"+filename);
        String[] list = folder_name.list();
        for (String file: list) {
            if (filename.equalsIgnoreCase(file)) {
                System.out.println("File " + filename + " already exists at " + folder_name);
				System.out.println("Try with another filename");

                return;
            }
        }
        filepath.createNewFile();
        System.out.println("File "+filename+" successfully added to "+ folder_name);
    }

    void deleteFile(String filename) {
        File filepath = new File(folder_name +"/"+filename);
        String[] list = folder_name.list();
        for (String file: list) {
            if (filename.equals(file) && filepath.delete()) {
                System.out.println("File " + filename + "successfully deleted from " + folder_name);
                return;
            }
        }
		System.out.println("Error occurred while Deleting File..");
		System.out.println("Please enter existing file name to delete..");

    }

    void searchFile(String filename) {
        String[] list = folder_name.list();
        for (String file: list) {
            if (filename.equals(file)) {
                System.out.println("FILE FOUND : File " + filename + " exists at " + folder_name);
                return;
            }
        }
        System.out.println("Oops!! File NOT found (FNF)");
		System.out.println("Please enter existing file name to search..");

    }

    public static void main(String[] args) {
        System.out.println(WELCOME_SCREEN);
        LockedMe menu = new LockedMe();
        menu.showPrimaryMenu();
    }
    void showSecondaryMenu() {
        System.out.println(SECONDARY_MENU_SCREEN);
        try{
            Scanner scanner = new Scanner(System.in);
            char[] input = scanner.nextLine().toLowerCase().trim().toCharArray();
            char option = input[0];

            switch (option){
                case 'a' : {
                    System.out.print("==> Adding a file..Please Enter a File Name : ");
                    String filename = scanner.next().trim();
                    addFile(filename);
                    break;
                }
                case 'b' : {
                    System.out.print("==>Deleting a file..Please Enter a File Name : ");
                    String filename = scanner.next().trim();
                    deleteFile(filename);
                    break;
                }
                case 'c' : {
                    System.out.print("==>Searching a file..Please Enter a File Name : ");
                    String filename = scanner.next().trim();
                    searchFile(filename);
                    break;
                }
                case 'd' : {
                    System.out.println("Return to MAIN menu");
                    showPrimaryMenu();
                    break;
                }
                default : System.out.println("Please select correct option a, b, c or d");
            }
            showSecondaryMenu();
        }
        catch (Exception e){
            System.out.println("Please enter a, b, c or d");
            showSecondaryMenu();
        }
    }
}
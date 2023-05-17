package com.Redot;

import java.io.IOException;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;


public class Main {

    public static void main(String[] args) throws IOException {


//        File directory = new File("C:\\Users\\Redot\\Desktop");
//
//        // Create a file filter to exclude shortcuts
//        FileFilter fileFilter = new FileFilter() {
//            @Override
//            public boolean accept(File file) {
//                return !file.getName().toLowerCase().endsWith(".lnk") && !file.getName().equals("desktop.ini");
//            }
//        };
//
//        // Get a list of files in the directory, excluding shortcuts
//        File[] files = directory.listFiles(fileFilter);
//
//        // Create an ArrayList to store the file names
//        ArrayList<String> fileList = new ArrayList<>();
//
//        // Process the files...
//        if (files != null) {
//            for (File file : files) {
//                fileList.add(file.getName());
//            }
//        } else {
//            System.out.println("Directory not found or not accessible.");
//        }

        // Print the list of file names
//        System.out.println(fileList);


        /////////////////
        DirectorySelectorApp dirSA = new DirectorySelectorApp();
        dirSA.starter();
        /////////////////

    }
}

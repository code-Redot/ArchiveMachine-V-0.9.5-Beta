package com.Redot;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;

import java.io.File;
import java.io.IOException;

public class CopyMachine {



    public static boolean copyRecurseHandler(String source, String destination, int limit, int partioner) throws IOException {

        //selecting the path in file and turning it into array
        File srcFolder = new File(source);
        File[] listOfFiles = srcFolder.listFiles();

        int increment = 0;

        //stop condition
        if (listOfFiles.length == 0 || !listOfFiles[0].exists()) {
            System.out.println("Exiting1");
            return true;
        }

        //sub-folder creating and checking
        if (DirectoryFileFilter.DIRECTORY.accept(new File(destination + "\\P" + partioner))) {
            partioner++;
            copyRecurseHandler(source, destination, limit, partioner);
        } else {
            System.out.println("\nCreating the sub-destination.");
            FileUtils.forceMkdir(new File(destination + "\\P" + partioner));
        }

        //copying method
        System.out.print("copying");
        copyRecurse(source, destination, limit, partioner);
        //recurring till source is zero

        //System.exit(0);
        copyRecurseHandler(source, destination, limit, partioner);

        return false;
    }


    public static boolean copyRecurse(String source, String destination, int limit, int partioner) {

        //selecting the path in file and turning it into array
        File srcFolder = new File(source);
        File[] listOfFiles = srcFolder.listFiles();

        //Checks id source is empty
        assert listOfFiles != null;
        if (listOfFiles.length == 0 || !listOfFiles[0].exists()) {
            System.out.println("Finished");
            System.exit(0);
        }

        //stop conditions
        if (limit < 0) {
            System.out.println("Exiting2");
            return true;
        }

        System.out.print(".");

        try {
            if (listOfFiles[0].isDirectory()) {

                FileUtils.moveDirectoryToDirectory(listOfFiles[0], new File(destination + "\\P" + partioner), false);
            }
            if (listOfFiles[0].isFile()) {

                FileUtils.moveFileToDirectory(listOfFiles[0], new File(destination + "\\P" + partioner), false);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        copyRecurse(source, destination, --limit, partioner);
        return true;
    }


}

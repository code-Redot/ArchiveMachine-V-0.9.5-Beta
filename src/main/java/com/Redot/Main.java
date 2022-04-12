package com.Redot;

import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



public class Main {

    public static File[] convertFileCollectionToFileArray(Collection<File> files){

        return files.toArray(new File[0]);
    }

    public static boolean copyRecurseHandler(String source, String destination, int limit,int init,int folderer) throws IOException {

        //selecting the path in file and turning it into array
        File srcFolder = new File(source);
        File[] listOfFiles = srcFolder.listFiles();

        //stop condition
        if (listOfFiles[limit] ==null || !listOfFiles[limit].exists()){System.out.println("Exiting..."); return true;}

        //sub-folder creating and checking
        if (DirectoryFileFilter.DIRECTORY.accept(new File(destination+"\\P"+folderer))){
            folderer++;
            copyRecurseHandler(source,destination,limit,init,++folderer);
        }else{
            System.out.println("creating the sub-destination..");
            FileUtils.forceMkdir(new File(destination+"\\P"+folderer));
        }


        copyRecurse(source,destination,limit,folderer);

        copyRecurseHandler(source,destination,limit,init,++folderer);
        return true;
    }


    public static boolean copyRecurse(String source, String destination, int limit,int folderer){
        //stop condition
        if (limit <= -1){System.out.println("copying..."); return true;}

        //selecting the path in file and turning it into array
        File srcFolder = new File(source);
        File[] listOfFiles = srcFolder.listFiles();


        try {
            if (listOfFiles[limit].isDirectory()) {


                FileUtils.moveDirectoryToDirectory(listOfFiles[limit], new File(destination+"\\P"+folderer), false);
            }
            if (listOfFiles[limit].isFile()){

                FileUtils.moveFileToDirectory(listOfFiles[limit], new File(destination+"\\P"+folderer),false);
            }


        }catch (IOException e) {e.printStackTrace();}

        copyRecurse(source,destination,limit-1,folderer);
        return true;
    }


    public static void main(String[] args) throws IOException {

        //initiation of source\destination locations
        String src = "E:\\test\\src";
        String dest = "E:\\test\\";

        //destination naming
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy MMM");
        String destination = dest+"\\"+(myDateObj.format(myFormatObj));


        //the limit of destination partition limit items
        int folderlim = 4;


        if (!DirectoryFileFilter.DIRECTORY.accept(new File(src))) {
            System.out.println("source does not exist \nExiting...");
            System.exit(0);
        }

        if (!DirectoryFileFilter.DIRECTORY.accept(new File(dest))){
            System.out.println("creating the destination...");
            FileUtils.forceMkdir(new File(dest));

        }




        copyRecurseHandler(src,destination,folderlim,folderlim,1);


    }
}

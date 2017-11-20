package com.knoldus.file_read.hello.impl;

/**
 * Created by knoldus on 17/11/17.
 */

import com.knoldus.file_read.hello.api.ResultPojo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHandle {
    private static final String filename = "/home/knoldus/Knoldus/mukesh/file-read/hello-impl/src/main/java/com/knoldus/file_read/hello/impl/content.txt";
    
    public List<ResultPojo> retrieveData(){
        BufferedReader bufferedReader = null;
        FileReader fileReader = null;
        String sAllLines="";
        List<ResultPojo> resultPojo =new ArrayList<ResultPojo>();
        
        
        try{
            fileReader = new FileReader(filename);
            bufferedReader = new BufferedReader(fileReader);
            String sCurrentLine;
            while ((sCurrentLine = bufferedReader.readLine())!=null){
                sAllLines=sAllLines+"::"+sCurrentLine;
            }
            ResultPojo objinfo=new ResultPojo();
            ResultPojo objinfo1=new ResultPojo();
            
            objinfo.setOut(sAllLines);
            objinfo.setMeraProperty("mukesh");
    
            objinfo1.setOut(sAllLines);
            objinfo1.setMeraProperty("raj");
            
            resultPojo.add(objinfo);
            resultPojo.add(objinfo1);
            
            return resultPojo;
        }
        catch(IOException exception)
        {
            exception.printStackTrace();
        }
        finally {
            try{
                if(bufferedReader!=null){bufferedReader.close();}
            }
            catch(IOException exception){exception.printStackTrace();}
        }
        return resultPojo;
    }
}

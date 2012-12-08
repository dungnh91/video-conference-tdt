package service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileUtils {

	
	public String uploadFile(String name, String directory, byte[] content){
        File file = new File(directory);
        if(!file.exists()){
            file.mkdir();
        }
        name = directory + "/" + name;
        File fileToUpload = new File(name);
        try{
            FileOutputStream fos = new FileOutputStream(fileToUpload);
            fos.write(content);
            fos.close();
        }catch(IOException ex){
            Logger.getLogger(FileUtils.class.getSimpleName()).log(Level.SEVERE, ex.getMessage());
            return "File not uploaded";
        }
        return "File uploaded";

    }
    public byte[] getFile(String fullName){
        byte[] output = null;
        File file = new File(fullName);
        if(file.exists()){
            output = new byte[(int)file.length()];
            try{
                FileInputStream fis = new FileInputStream(file);
                fis.read(output);
                fis.close();
            }catch(IOException ex){
                Logger.getLogger(FileUtils.class.getSimpleName()).log(Level.SEVERE, ex.getMessage());
            }
        }
        return output;
    }
}

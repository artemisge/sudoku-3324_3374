import com.sun.deploy.util.ArrayUtil;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.*;
import java.util.ArrayList;

public class IOManager
{
    private String filename;
    private File file;
    private int hashCode;
    private static ArrayList<File> normalPuzzles;
    private static ArrayList<File> killerPuzzles;
    private static ArrayList<File> duidokuPuzzles;

    public IOManager()
    {
        //PrintWriter outputFile=new PrintWriter(new FileWriter(usernameFile));
    }

    public String getFilename()
    {
        return filename;
    }

    public int createNewFile()
    {
        try{
            file=new File("C:\\%s.txt",filename);
            hashCode=file.hashCode();
            if(file.exists())
                return hashCode;
            else
                return -1;
        }catch(Exception e)
        {
            System.out.println("Error occured");
            return -1;
        }

    }

    public int getHashCode()
    {
        return hashCode;
    }

    public boolean saveUser(String name)
    {
        try {
            File newFile = new File("C:\\%s.txt", name);
            return true;
        }catch(Exception e)
        {
            return false;
        }
    }
}
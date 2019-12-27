import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.*;

public class IOManager
{
    private String filename;
    private File file;
    private int hashCode;

    public IOManager(String usernameFile)
    {
        filename=usernameFile;
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
}

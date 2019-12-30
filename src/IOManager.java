import com.sun.deploy.util.ArrayUtil;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class IOManager
{
    private String filename;
    //private File file;
    //private int hashCode;
    private static File normalPuzzles;
    private static File killerPuzzles;
    //private static File duidokuPuzzles;
    private static File players;

    public IOManager() throws IOException
    {
        normalPuzzles=new File("C:\\Users\\Oxi reman KALA\\IdeaProjects\\sudoku-3324_3374\\src\\Normal.txt");
        killerPuzzles=new File("C:\\Users\\Oxi reman KALA\\IdeaProjects\\sudoku-3324_3374\\src\\Killer.txt");
       // duidokuPuzzles=new File("C:\\%s.txt",filename);
        players=new File("C:\\Users\\Oxi reman KALA\\IdeaProjects\\sudoku-3324_3374\\src\\players.txt");
    }

    public void addPlayer(String playerName) throws IOException
    {
        String line;
        boolean exists=false;
        Scanner sc = new Scanner(players);
        while (sc.hasNextLine())
        {
            line=sc.nextLine();
            String [] splitLine=line.split(" ",5);
            if(splitLine[0]==playerName)
            {
                exists=true;
                break;
            }
        }
        if(!exists) {
            BufferedWriter writer = new BufferedWriter(new FileWriter(players, true));
            writer.append('\n');
            writer.append(playerName);
            writer.append(" 0 0 - -");
            writer.close();
        }
    }

    public void modifyPlayerFile(Player player, String whatToModify) throws IOException
    {
        try {
            String line;
            File tempFile = new File(players.getAbsolutePath() + ".txt");
            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
            Scanner sc = new Scanner(players);
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                if (line.contains(player.getName())) {
                    String[] splitLine = line.split(" ", 5);
                    switch (whatToModify)
                    {
                        case "wins": line=line.replace(splitLine[1], Integer.toString(player.getWins()));
                        case "losses":  line=line.replace(splitLine[2], Integer.toString(player.getLosses()));
                        case "solvedNormal":  line=line.replace(splitLine[3], player.getNormalSolvedPuzzles());
                        case "solvedKiller":  line=line.replace(splitLine[4], player.getKillerSolvedPuzzles());
                    }
                }
                pw.println(line);
                pw.flush();
            }
            pw.close();

            //delete original file
            if (!players.delete()) {
                System.out.println("Could not delete file");
                return;
            }

            players = tempFile;
            //rename the new file to the original file name
            if (!tempFile.renameTo(players)) {
                System.out.println("Could not rename file");
            }
        }catch(IOException e)
        {
            e.printStackTrace();
        }

    }


    //public String getFilename()
    //{
      //  return filename;
    //}

    /*public int createNewFile()
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

    }*/

    /*public int getHashCode()
    {
        return hashCode;
    }*/




    //chjeck if user exists
    /*public boolean saveUser(String name)
    {
        try {
            File newFile = new File("C:\\%s.txt", name);
            return true;
        }catch(Exception e)
        {
            return false;
        }
    }*/
}
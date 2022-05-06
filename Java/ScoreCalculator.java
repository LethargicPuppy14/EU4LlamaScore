package Java;

import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ScoreCalculator extends JFrame{
    //private File savedGame = new File("R:\\Documents\\Paradox Interactive\\Europa Universalis IV\\save games\\MP\\Britain 1614 uncompressed.eu4");
    private File savedGame;
    private Scanner readFile;
    private ArrayList<Player> playerList;

    public static void main(String[]args){
        ScoreCalculator calculator = new ScoreCalculator();
    }

    public ScoreCalculator(){
        savedGame = loadFile();
        playerList = new ArrayList<Player>();

        try{
            readFile = new Scanner(savedGame);
            while(readFile.hasNextLine()){
                String thisLine = cleanUp(readFile.nextLine());
                handleCommand(thisLine);
            }
        }
        catch(Exception e){
            System.out.println("File not found, or something");
        }
    }

    /**
     * A truly horrifying method which checks what each line does and calls its corresponding method.
     * @param saveLine Current line from the save file being read
     */
    public void handleCommand(String saveLine){
        if(saveLine.equalsIgnoreCase("players_countries={")){
            findPlayers();
        }
    }

    public static String removeQuotes(String theLine){
        return theLine.replace("\"", "");
    }

    public void findPlayers(){
        String thisLine = cleanUp(readFile.nextLine());
        int counter = 0;
        boolean hasRun = false;

            while(true){
                counter++;
                String player;
                if(!hasRun){
                    player = removeQuotes(thisLine);
                    hasRun = true;
                }
                else
                    player = removeQuotes(readFile.nextLine());//spaces already cleaned out
                //System.out.print("Player: " + player);
                if(player.equals("}")){
                    break;
                }
                String country = removeQuotes(readFile.nextLine());
                //System.out.println("\t Country: " + country);
                playerList.add(new Player(player, country));

                //To prevent infinite loops. No eu4 save file is 10,000,000 lines long, right?
                if(counter > 10000000) break;
            }
    }

    /**
     * Removes spaces, tabs, and comments from a String
     */
    private static String cleanUp(String toClean){
        String noSpaces = toClean.replace(" ", "").replace("\t", "");
        if(noSpaces.indexOf("#") == -1){
            return noSpaces;
        }
        return noSpaces.substring(0, noSpaces.indexOf("#"));
    }

    public File loadFile(){
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("EU4", "eu4");
        fileChooser.setFileFilter(filter);
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        //TODO: Try to set this to the eu4 saves directory, or at least the documents folder
        int result = fileChooser.showOpenDialog(this);
        if(result == JFileChooser.APPROVE_OPTION){
            return fileChooser.getSelectedFile();
        }
        return null;
    }

}
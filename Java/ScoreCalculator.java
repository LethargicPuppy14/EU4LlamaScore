package Java;

import java.util.Scanner;
import java.util.HashMap;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ScoreCalculator{
    static File savedGame = new File("R:\\Documents\\Paradox Interactive\\Europa Universalis IV\\save games\\MP\\Britain 1614 uncompressed.eu4");
    static Scanner readFile;
    static HashMap<String, String> playerList;
    public static void main(String[]args){
        //File savedGame = loadFile();
        playerList = new HashMap<String, String>();

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

        for(String player : playerList.keySet()){
            System.out.println(player + ", " + playerList.get(player));
        }
        
    }

    /**
     * A truly horrifying method which checks what each line does and calls its corresponding method.
     * @param saveLine Current line from the save file being read
     */
    public static void handleCommand(String saveLine){
        if(saveLine.equalsIgnoreCase("players_countries={")){
            findPlayers();
        }
    }

    public static String removeQuotes(String theLine){
        return theLine.replace("\"", "");
    }

    public static void findPlayers(){
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
                playerList.put(player, country);
                //if(counter > 30) break;
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

    public static File loadFile(){
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("EU4", "eu4");
        fileChooser.setFileFilter(filter);
        //int returnVal = fileChooser.showOpenDialog(ScoreCalculator.this);
        return null;
    }

}
package Java;

import java.util.ArrayList;
import java.util.HashMap;
import java.time.LocalDate;

/**
 * Stores a player, their countries, their Llama score, and the wars that they fought in.
 */
public class Player {

    /**
     * A country and the range of dates that this player was playing on it.
     * 
     * country is the country tag of this nation, start is the in-game date that they started
     * playing as the country, and end is the in-game date that they stopped playing as the country.
     */
    private class CountryRange{
        protected String country;
        protected LocalDate start;
        protected LocalDate end;

        public CountryRange(String country, LocalDate start, LocalDate end){//TODO: Make start and end be entered in an easy yyyy.mm.dd type of way instead of however this stores it
            this.country = country;
            this.start = start;
            this.end = end;
        }

        //Methods are a pain, so the variables are just classified as protected so that the Player can access them directly

    }//end of CountryRange sub-class

    String name;
    HashMap<String, CountryRange> countries; //Which countries they played, and when
    ArrayList<War> wars;//all PvP wars fought in

    public Player(String name, String initialCountry){//tricky implementation for multiple countries; might have to be manually entered. I'll only support 1 for now
        this.name = name;
        countries = new HashMap<String, CountryRange>();
        addCountry(initialCountry);
    }

    /**
     * Adds a country to the map of countries. Asks user when this player played the country, giving options for
     * always, never (?), and a custom range.
     * @param country The country the player played as.
     */
    private void addCountry(String country){
        //TODO: Implement this method
    }

}

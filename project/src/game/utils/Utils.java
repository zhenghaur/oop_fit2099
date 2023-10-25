package game.utils;

import java.util.Random;

/**
 * Class to generate random numbers.
 */
public class Utils {

    /**
     * Generate a random number from 0 to 99
     *
     * @return an integer from 0 to 99
     */
    public static int rollHundred(){
        Random r = new Random();
        return r.nextInt(100);
    }

    /**
     * Generate a random number from 0 to input value - 1
     *
     * @param diceSides maximum value of random number (not inclusive)
     * @return an integer from 0 to input value - 1
     */
    public static int roll(int diceSides){
        Random r = new Random();
        return r.nextInt(diceSides);
    }
}

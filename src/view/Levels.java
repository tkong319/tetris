/*
 * TCSS 305 - Autumn 2015
 * Assignment 6 - Tetris
 */

package view;

/**
 * A class to keep track of current level and difficulty.
 * @author Tung Kong
 * @version 1 11/30/15
 */
public class Levels {
    
    /** Initial difficulty. */
    private static final int INITIAL_DIFFICULTY = 1000;
    
    /** Initial amount of lines until next level. */
    private static final int INITIAL_LINES_TO_NEXT_LEVEL = 10;
    
    /** Amount to increase or decrease difficulty. */
    private static final int INCREASE_DECREASE_VALUE = 105;

    /** Current level. */
    private int myLevel = 1;
    
    /** Current difficulty. */
    private int myDifficulty = INITIAL_DIFFICULTY;
    
    /** Lines until next level. */
    private int myLinesToNextLevel = INITIAL_LINES_TO_NEXT_LEVEL;
    
    
    
    /** Increase level and difficulty. */
    public void setIncreaseDifficulty() {
        myLevel++;
        myDifficulty -= INCREASE_DECREASE_VALUE;
    }
    
    /** Decrease level and difficulty. */
    public void setDecreaseDifficulty() {
        myLevel--;
        myDifficulty += INCREASE_DECREASE_VALUE;
    }
    
    /**
     * Returns difficulty.
     * @return the difficulty.
     */
    public int getDifficulty() {
        return myDifficulty;
    }
    
    /**
     * Returns current level. 
     * @return current level.
     */
    public int getLevel() {
        return myLevel;
    }
    
    /** Resets level back to 1. */
    public void resetLevel() {
        myLevel = 1;
    }
    
    /** Resets difficulty back to 1000. */
    public void resetDifficulty() {
        myDifficulty = INITIAL_DIFFICULTY;
    }
    
    /** decrease lines until next level. */
    public void decreaseLinesToNextLevel() {
        myLinesToNextLevel--;
    }
    
    /**
     * Returns lines until next level.
     * @return lines until next level.
     */
    public int getLinesToNextLevel() {
        return myLinesToNextLevel;
    }
    
    /** Resets lines to next level back to 10. */
    public void resetLinesToNextLevel() {
        myLinesToNextLevel = INITIAL_LINES_TO_NEXT_LEVEL;
    }

}

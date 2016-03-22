/*
 * TCSS 305 - Autumn 2015
 * Assignment 6 - Tetris
 */


package view;

import java.awt.EventQueue;

/**
 * A main class to run tetris.
 * @author Tung Kong
 * @version 1 11/30/15
 */
public final class TetrisMain {
    
    /** Private constructor so main class can't be instantiated. */
    private TetrisMain() {
        
    }
    
    /**
     * Runs a tetris game.
     * @param theArgs command line arguments.
     */
    public static void main(final String[] theArgs) {
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TetrisGUI().setUpTetris();
            }
        });

    }

}

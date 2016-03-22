/*
 * TCSS 305 - Autumn 2015
 * Assignment 6 - Tetris
 */

package view;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.Timer;

import model.Board;

/**
 * A Listener for tetris key controls.
 * @author Tung Kong
 * @version 1 11/30/2015
 */
public class TetrisControlsListener extends KeyAdapter {
    
    /** Board object. */
    private final Board myBoard;
    
    /** Timer object. */
    private final Timer myTimer;

    
    /** 
     * Constructs a Listener for the given board. 
     * @param theBoard to change after key events happen.
     * @param theTimer the timer to pause or start.
     */
    public TetrisControlsListener(final Board theBoard, final Timer theTimer) {
        super();
        myBoard = theBoard;
        myTimer = theTimer;
    }

    @Override
    public void keyPressed(final KeyEvent theEvent) {
        final int key = theEvent.getKeyCode();
        final boolean gameOver = myBoard.isGameOver();
        if (myTimer.isRunning()) {
            if (key == KeyEvent.VK_DOWN) {
                myBoard.moveDown();
            } else if (key == KeyEvent.VK_RIGHT) {
                myBoard.moveRight();
            } else if (key == KeyEvent.VK_LEFT) {
                myBoard.moveLeft();
            } else if (key == KeyEvent.VK_UP) {
                myBoard.rotate();
            } else if (key == KeyEvent.VK_SPACE) {
                myBoard.hardDrop();
            } 
        }
        if (key == KeyEvent.VK_ENTER) {
            if (myTimer.isRunning() && !gameOver) {
                myTimer.stop();
                myBoard.setPaused();
                
            } else if (!gameOver) {
                myBoard.setPaused();
                myTimer.start();  
            }
        }
        
    }

}

/*
 * TCSS 305 - Autumn 2015
 * Assignment 6 - Tetris
 */

package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.Board;

/**
 * A Frame that displays a tetris game.
 * @author Tung Kong
 * @version 1 11/30/15
 */
public class TetrisGUI extends JFrame {
    
    /** Compiler generated serialVersionUID. */
    private static final long serialVersionUID = 6247192212512626695L;
    
    /** Board width in number of blocks. */
    private static final int BOARD_WIDTH = 10;
    
    /** Board height in number of blocks. */
    private static final int BOARD_HEIGHT = 20;
    
    /** Border size. */
    private static final int BORDER = 10;
    
    /** Timer delay. */
    private static final int TIMER_DELAY = 1000;
    
    /** Object keeps track of current level and difficulty. */
    private static final Levels LEVELS = new Levels();
    
    /** Board object. */
    private final Board myBoard;
    
    /** BoardPanel object. */
    private final BoardPanel myBoardPanel;
    
    /** Timer object. */
    private final Timer myTimer;
    
    /** PiecePanel object. */
    private final PiecePanel myPiecePanel;
    
    /** Panel to display PiecePanel. */
    private final JPanel myDisplayPanel;
    
    /** Panel to display BoardPanel. */
    private final JPanel myGamePanel;
    
    /** Buttons panels. */
    private final ButtonsPanel myButtonsPanel;
    
    /** Score and level panel. */
    private final ScorePanel myScorePanel;
    
    
    
    /** Constructs a frame with a new tetris game. */
    public TetrisGUI() {
        super("Tetris");
        myBoard = new Board(BOARD_WIDTH, BOARD_HEIGHT);
        myBoardPanel = new BoardPanel(myBoard);
        myTimer = new Timer(TIMER_DELAY, new TimerListener());
        myDisplayPanel = new JPanel();
        myPiecePanel = new PiecePanel(myBoard);
        myGamePanel = new JPanel();
        myScorePanel = new ScorePanel(myBoard, myTimer, LEVELS); 
        myButtonsPanel = new ButtonsPanel(myBoard, myTimer, myScorePanel, LEVELS);
   
    }
    
    /** Sets up all components of frame and tetris game. */
    public void setUpTetris() {
    
        myGamePanel.add(myBoardPanel);
        addKeyListener(new TetrisControlsListener(myBoard, myTimer));
        myGamePanel.setBorder(BorderFactory.createEmptyBorder(BORDER, BORDER, BORDER, 0));
        add(myGamePanel, BorderLayout.WEST);
        myDisplayPanel.setLayout(new BorderLayout());
        myDisplayPanel.add(myPiecePanel, BorderLayout.NORTH);
        myDisplayPanel.setBorder(BorderFactory.createEmptyBorder(BORDER, BORDER,
                                                                 0, BORDER));
        myDisplayPanel.add(new ControlsPanel(), BorderLayout.SOUTH);
        myDisplayPanel.add(myScorePanel, BorderLayout.CENTER);
        add(myDisplayPanel, BorderLayout.EAST);
        add(myButtonsPanel, BorderLayout.SOUTH);
        pack();
        setMinimumSize(getSize());
        setResizable(false);
        myBoard.addObserver(myBoardPanel);
        myBoard.addObserver(myPiecePanel);
        myBoard.addObserver(myScorePanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        myTimer.start();
        
    }
    
    /** Action listener class for timer. */
    class TimerListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent theEvent) {
            if (myBoard.isGameOver()) {
                myTimer.stop();
            }
            myBoard.step();
            
        }
        
    }


}

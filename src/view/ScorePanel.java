/*
 * TCSS 305 - Autumn 2015
 * Assignment 6 - Tetris
 */


package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.Board;

/**
 * A Panel that shows current level, score, and lines cleared.
 * @author Tung Kong
 * @version 1 11/30/15
 */
public class ScorePanel extends JPanel implements Observer {
    
    /** Compiler generated serialVersionUID. */
    private static final long serialVersionUID = -1291122567166595796L;
    
    /** Layout rows. */
    private static final int GRID_ROWS = 4;
    
    /** Layout columns. */
    private static final int GRID_COLUMNS = 2;
    
    /** Number of Lines until next level. */
    private static final int LINES_TO_NEXT_LEVEL = 10;

    /** Font style. */
    private static final Font FONT_STYLE = new Font("Impact", Font.BOLD, 18);
    
    /** Font color. */
    private static final Color FONT_COLOR = new Color(204, 153, 255);
    
    /** Level label. */
    private static final JLabel LEVEL_LABEL = new JLabel("Level");
    
    /** Lines label. */
    private static final JLabel LINE_LABEL = new JLabel("Lines");
    
    /** Score label. */
    private static final JLabel SCORE_LABEL = new JLabel("Score");
    
    /** Lines to next level label. */
    private static final JLabel NEXT_LEVEL = new JLabel("Next level");
    
    /** Initial score and lines cleared value. */
    private static final String INITIAL_SCORE_LINES_VALUE = "0";
    
    /** Point each line is worth. */
    private static final int POINTS = 200;
   
    /** Label that shows current level. */
    private final JLabel myLevelLabel = new JLabel("1");
    
    /** Label that shows total lines cleared. */
    private final JLabel myLinesClearedLabel = new JLabel(INITIAL_SCORE_LINES_VALUE);
    
    /** Label that shows current score. */
    private final JLabel myScoreLabel = new JLabel(INITIAL_SCORE_LINES_VALUE);
    
    /** Label to indicate how many lines until next level. */
    private final JLabel myNextLevelLabel = new JLabel();
    

    /** Current tetris board. */
    private final Board myBoard;
    
    /** Current timer. */
    private final Timer myTimer;

    /** Current score. */
    private int myScore;
    
    /** Current lines cleared. */
    private int myLines;
    
    
    /** Keeps track of current level and difficulty. */
    private final Levels myLevels;
    
    /**
     * Constructs a panel to display score, level and lines cleared.
     * @param theBoard current tetris game.
     * @param theTimer the timer being used.
     * @param theLevels keeps track of level and difficulty.
     */
    public ScorePanel(final Board theBoard, final Timer theTimer, final Levels theLevels) {
        super();
        myBoard = theBoard;
        myTimer = theTimer;
        myLevels = theLevels;
        setLayout(new GridLayout(GRID_ROWS, GRID_COLUMNS));
        addLabels();
        setVisible(true);
    }
    
    /** Set lable font and color. */
    private void addLabels() {
        LEVEL_LABEL.setForeground(FONT_COLOR);
        LEVEL_LABEL.setFont(FONT_STYLE);
        add(LEVEL_LABEL);
        myLevelLabel.setForeground(FONT_COLOR);
        myLevelLabel.setFont(FONT_STYLE);
        add(myLevelLabel);
        LINE_LABEL.setFont(FONT_STYLE);
        LINE_LABEL.setForeground(FONT_COLOR);
        add(LINE_LABEL);
        myLinesClearedLabel.setForeground(FONT_COLOR);
        myLinesClearedLabel.setFont(FONT_STYLE);
        add(myLinesClearedLabel);
        SCORE_LABEL.setForeground(FONT_COLOR);
        SCORE_LABEL.setFont(FONT_STYLE);
        add(SCORE_LABEL);
        myScoreLabel.setForeground(FONT_COLOR);
        myScoreLabel.setFont(FONT_STYLE);
        add(myScoreLabel);
        NEXT_LEVEL.setForeground(FONT_COLOR);
        NEXT_LEVEL.setFont(FONT_STYLE);
        add(NEXT_LEVEL);
        myNextLevelLabel.setForeground(FONT_COLOR);
        myNextLevelLabel.setFont(FONT_STYLE);
        myNextLevelLabel.setText(Integer.toString(myLevels.getLinesToNextLevel()));
        add(myNextLevelLabel);
        
        
    }
    
    /** Calculates score. Each line is worth 200 multiplied by level. */
    private void calculateScore() {
        final int currentLevel = myLevels.getLevel();
        myScore += POINTS * currentLevel;
        
    }
    
    /**
     * Sets level label to display current level. 
     * @param theLevel the level to set label.
     */
    public void setLevelLabel(final int theLevel) {
        myLevelLabel.setText(Integer.toString(theLevel));
    }
    
    /** Sets Next level label to display lines until next level. */
    public void resetNextLevelLabel() {
        myNextLevelLabel.setText(Integer.toString(myLevels.getLinesToNextLevel()));
    }
    
    /** Resets lines cleared back to 0. */
    public void resetLines() {
        myLines = 0;
    }
    
    /** Resets score back to 0. */
    public void resetScore() {
        myScore = 0;
    }
    

    @Override
    public void update(final Observable theObservable, final Object theObject) {
        if (theObject == myBoard.getFrozenBlocks()) { 
            myLines++;
            calculateScore();
            myLevels.decreaseLinesToNextLevel();
            if (myLevels.getLinesToNextLevel() == 0) {
                myLevels.resetLinesToNextLevel();
            }
            
        }
        
        if (myLines % LINES_TO_NEXT_LEVEL == 0 && myLines != 0
                        && myLevels.getLevel() < LINES_TO_NEXT_LEVEL
                        && theObject == myBoard.getFrozenBlocks()) {
            myLevels.setIncreaseDifficulty();
            setLevelLabel(myLevels.getLevel());
            myTimer.setDelay(myLevels.getDifficulty());
        }
        myNextLevelLabel.setText(Integer.toString(myLevels.getLinesToNextLevel()));
        myLinesClearedLabel.setText(Integer.toString(myLines));
        myScoreLabel.setText(Integer.toString(myScore));
    }

}

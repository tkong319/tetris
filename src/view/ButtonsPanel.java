/*
 * TCSS 305 - Autumn 2015
 * Assignment 6 - Tetris
 */


package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.Board;

/**
 * A Panel that contains buttons to start/end game, and choose level difficulty.
 * @author Tung Kong
 * @version 1 11/30/15
 */
public class ButtonsPanel extends JPanel {

    /** Compiler generated serialVersionUID. */
    private static final long serialVersionUID = -1453734899958807693L;
    
    /** Panel height. */
    private static final int PANEL_HEIGHT = 50;
    
    /** Panel width. */
    private static final int PANEL_WIDTH = 500;
    
    /** Font type. */
    private static final Font FONT_STYLE = new Font("Impact", Font.BOLD, 22);
    
    /** Font color. */
    private static final Color FONT_COLOR = new Color(204, 153, 255);
    
    /** A string of spaces. */
    private static final String BLANK_STRING = "               ";
    
    
    /** Maximum level. */
    private static final int MAX_LEVEL = 10;
    
    /** Minimun level. */
    private static final int MIN_LEVEL = 1;
    
    /** A Blank label. */
    private final JLabel myBlankLabel = new JLabel(BLANK_STRING);
    
    /** A blank label. */
    private final JLabel myBlankLabel2 = new JLabel(BLANK_STRING);
    
    /** A blank label. */
    private final JLabel myBlankLabel3 = new JLabel(BLANK_STRING);
    
    /** A label for new game. */
    private final JLabel myNewGame = new JLabel("New Game");
    
    /** A label for quit. */
    private final JLabel myQuitGame = new JLabel("Quit");
    
    /** A label for level. */
    private final JLabel myLevelLabel = new JLabel("Level");
    
    /** An increase label. */
    private final JLabel myLevelIncrease = new JLabel(">");
    
    /** A decrease label. */
    private final JLabel myLevelDecrease = new JLabel("<");
    
    /** A help label. */
    private final JLabel myHelpLabel = new JLabel("Help");
    
    /** A board object. */
    private final Board myBoard;
    
    /** A timer object. */
    private final Timer myTimer;
    
    /** A Level object. */
    private final Levels myLevels;
    
    /** ScorePanel object. */
    private final ScorePanel myScorePanel;

    
    /**
     * Constructs a panel of Labels used as buttons.
     * @param theBoard the current tetris board.
     * @param theTimer the timer being used.
     * @param theScorePanel the score panel.
     * @param theLevels the current level and difficulty.
     */
    public ButtonsPanel(final Board theBoard, final Timer theTimer,
                        final ScorePanel theScorePanel, final Levels theLevels) {
        super();
        myBoard = theBoard;
        myTimer = theTimer;
        myLevels = theLevels;
        myScorePanel = theScorePanel;
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setLabelFont();
        addLabelListeners();
        addLabels();
        setVisible(true);
        
    }
    
    /** Sets the Font style and color of labels. */
    private void setLabelFont() {
        myQuitGame.setFont(FONT_STYLE);
        myNewGame.setFont(FONT_STYLE);
        myLevelLabel.setFont(FONT_STYLE);
        myLevelDecrease.setFont(FONT_STYLE);
        myLevelIncrease.setFont(FONT_STYLE);
        myHelpLabel.setFont(FONT_STYLE);
        myLevelDecrease.setForeground(FONT_COLOR);
        myLevelIncrease.setForeground(FONT_COLOR);
        myLevelLabel.setForeground(FONT_COLOR);
        myQuitGame.setForeground(FONT_COLOR);
        myNewGame.setForeground(FONT_COLOR);
        myHelpLabel.setForeground(FONT_COLOR);
    }
    
    /** Adds listener to labels. */
    private void addLabelListeners() {
        final MouseAdapter labelListener = new LabelListener();
        myQuitGame.addMouseMotionListener(labelListener);
        myNewGame.addMouseMotionListener(labelListener);
        myQuitGame.addMouseListener(labelListener);
        myNewGame.addMouseListener(labelListener);
        myLevelLabel.addMouseMotionListener(labelListener);
        myLevelLabel.addMouseListener(labelListener);
        myLevelDecrease.addMouseListener(labelListener);
        myLevelDecrease.addMouseMotionListener(labelListener);
        myLevelIncrease.addMouseListener(labelListener);
        myLevelIncrease.addMouseMotionListener(labelListener);
        myHelpLabel.addMouseListener(labelListener);
        myHelpLabel.addMouseMotionListener(labelListener);
    }
    

    /** Adds labels to panel. */
    private void addLabels() {
        add(myNewGame);
        add(myBlankLabel);
        add(myQuitGame);
        add(myBlankLabel2);
        add(myLevelDecrease);
        add(myLevelLabel);
        add(myLevelIncrease);
        add(myBlankLabel3);
        add(myHelpLabel);
    }
    
    /** Listener for the labels. */
    class LabelListener extends MouseAdapter {
        @Override
        public void mouseEntered(final MouseEvent theEvent) {
            final Component thisEvent = theEvent.getComponent();
            if (thisEvent.equals(myQuitGame) || thisEvent.equals(myNewGame) 
                            || thisEvent.equals(myLevelDecrease)
                            || thisEvent.equals(myLevelIncrease)
                            || thisEvent.equals(myHelpLabel)) {
                thisEvent.setForeground(Color.BLACK);
            }
        }
        
        @Override 
        public void mouseExited(final MouseEvent theEvent) {
            final Component thisEvent = theEvent.getComponent();
            if (thisEvent.equals(myQuitGame) || thisEvent.equals(myNewGame) 
                            || thisEvent.equals(myLevelDecrease)
                            || thisEvent.equals(myLevelIncrease)
                            || thisEvent.equals(myHelpLabel)) {
                thisEvent.setForeground(FONT_COLOR);
            }
        }
        
        @Override
        public void mouseClicked(final MouseEvent theEvent) {
            final Component thisEvent = theEvent.getComponent();
            if (thisEvent.equals(myNewGame) && myBoard.isGameOver()) {
                myScorePanel.resetLines();
                myScorePanel.resetScore();
                myLevels.resetLevel();
                myScorePanel.setLevelLabel(myLevels.getLevel());
                myBoard.newGame(myBoard.getWidth(), myBoard.getHeight(), null);
                myLevels.resetDifficulty();
                myLevels.resetLinesToNextLevel();
                myScorePanel.resetNextLevelLabel();
                myTimer.setDelay(myLevels.getDifficulty());
                myTimer.restart();
                
            } else if (thisEvent.equals(myQuitGame)) {
                myTimer.stop();
                myBoard.setGameOver();
            } else if (thisEvent.equals(myLevelDecrease) && myLevels.getLevel() > MIN_LEVEL
                            && myLevels.getLevel() <= MAX_LEVEL) {
                myLevels.setDecreaseDifficulty();
                myScorePanel.setLevelLabel(myLevels.getLevel());
                myTimer.setDelay(myLevels.getDifficulty());
            } else if (thisEvent.equals(myLevelIncrease) && myLevels.getLevel() < MAX_LEVEL
                            && myLevels.getLevel() >= MIN_LEVEL) {
                myLevels.setIncreaseDifficulty();
                myScorePanel.setLevelLabel(myLevels.getLevel());
                myTimer.setDelay(myLevels.getDifficulty());
            } else if (thisEvent.equals(myHelpLabel)) {
                JOptionPane.showMessageDialog(getParent(), "For every line cleared, "
                                + "you get 200 points multiplied by the level you are on.");
            }
        }
    }
    
    

}

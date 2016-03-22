/*
 * TCSS 305 - Autumn 2015
 * Assignment 6 - Tetris
 */

package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import model.AbstractPiece;
import model.Block;
import model.Board;
import model.Piece;

/**
 * A Panel to show the next piece of tetris game.
 * @author Tung Kong
 * @version 1 11/30/15
 */
public class PiecePanel extends JPanel implements Observer {
    
    /** Compiler generated serialVersionUID. */
    private static final long serialVersionUID = -2650750783264091408L;
    
    /** Panel width. */
    private static final int DISPLAY_WIDTH = 200;
    
    /** Panel height. */
    private static final int DISPLAY_HEIGHT = 120;
    
    /** Size of individual block of pieces. */
    private static final int BLOCK_SIZE = 30;
    
    /** A number to divide board width. */
    private static final int DIVIDE_BY_4 = 4;
    
    /** Background color. */
    private static final Color BACKGROUND_COLOR = new Color(204, 204, 255);
    
    /** Border color. */
    private static final Color BORDER_COLOR = new Color(204, 153, 255);
    
    /** Board object. */
    private final Board myBoard;
    
    /** Map to hold each block and its corresponding color. */
    private final Map<Block, Color> myBlockColorMap;
    
    /** Panel border. */
    private final Border myBorder = BorderFactory.createLineBorder(BORDER_COLOR, 5);

    
    /**
     * Constructs a panel to display next piece in tetris game.
     * @param theBoard the current tetris board game. 
     */
    public PiecePanel(final Board theBoard) {
        super();
        myBoard = theBoard;
        myBlockColorMap = new PieceColor().getColorMap();

        setBorder(myBorder);

        setBackground(BACKGROUND_COLOR);
        setPreferredSize(new Dimension(DISPLAY_WIDTH, DISPLAY_HEIGHT));
        setOpaque(true);
    }
    
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        final Piece nextPiece = myBoard.getNextPiece();
        final Block nextBlock = ((AbstractPiece) nextPiece).getBlock();
        final int[][] nextBlockRotation = ((AbstractPiece) nextPiece).getRotation(); 
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setPaint(myBlockColorMap.get(nextBlock));
        
        
        for (int i = 0; i < nextBlockRotation.length; i++) {
            final int x = nextBlockRotation[i][0];
            final int y = nextBlockRotation[i][1];
            g2d.fill3DRect(x * BLOCK_SIZE + (DISPLAY_WIDTH / DIVIDE_BY_4),
                           DISPLAY_HEIGHT - ((y + 1) * BLOCK_SIZE),
                           BLOCK_SIZE, BLOCK_SIZE, true);
        }
        
        
    }

    @Override
    public void update(final Observable theObservable, final Object theObject) {
        repaint();    
    }
    

}

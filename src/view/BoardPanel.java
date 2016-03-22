/*
 * TCSS 305 - Autumn 2015
 * Assignment 6 - Tetris
 */

package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.List;
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
 * A Panel that displays the board of a tetris game.
 * @author Tung Kong
 * @version 1 11/30/15
 */
public class BoardPanel extends JPanel implements Observer {
    
    /** Compiler generated serialVersionUID. */
    private static final long serialVersionUID = -4711477916331889313L;
    
    /** Divide by 2. */
    private static final int DIVIDE_BY_2 = 2;
    
    /** Divide by 4. */
    private static final int DIVIDE_BY_4 = 4;
    
    /** Divide by 6. */
    private static final int DIVIDE_BY_6 = 6;
    
    /** Value to add to x coordinates. */
    private static final int ADD_TO_X_1 = 15;
    /** Value to add to x coordinates. */
    private static final int ADD_TO_X_2 = 20;
    /** Size of individual block of pieces. */
    private static final int BLOCK_SIZE = 30;
    
    /** Font style. */
    private static final Font FONT_STYLE = new Font("Impact", Font.BOLD, 36);
    
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
     * Constructs a panel to display tetris game. 
     * @param theBoard current tetris board.
     */
    public BoardPanel(final Board theBoard) {
        super();
        myBoard = theBoard;
        myBlockColorMap = new PieceColor().getColorMap();
        setBorder(myBorder);
        setBackground(BACKGROUND_COLOR);
        setOpaque(true);
        setPreferredSize(new Dimension(myBoard.getWidth() * BLOCK_SIZE,
                                       myBoard.getHeight() * BLOCK_SIZE));
    }
    
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
        
        final Piece currentPiece = myBoard.getCurrentPiece();
        final Block currentBlock = ((AbstractPiece) currentPiece).getBlock();
        final int[][] currentBoardCoord = ((AbstractPiece) currentPiece).getBoardCoordinates();
        final List<Block[]> blocks = myBoard.getFrozenBlocks();
        
        for (int i = 0; i < blocks.size(); i++) {
            final Block[] type = blocks.get(i);
            
            for (int j = 0; j < type.length; j++) {
                final Block b = type[j];
                if (b != Block.EMPTY) {
                    g2d.setPaint(myBlockColorMap.get(b));
                    g2d.fill3DRect(j * BLOCK_SIZE, getHeight() - (i + 1) * BLOCK_SIZE,
                                 BLOCK_SIZE, BLOCK_SIZE, true);
                }
            }
            
        }
        
        g2d.setPaint(myBlockColorMap.get(currentBlock));
        
        for (int i = 0; i < currentBoardCoord.length; i++) {
            final int x = currentBoardCoord[i][0];
            final int y = currentBoardCoord[i][1];
            g2d.fill3DRect(x * BLOCK_SIZE, (myBoard.getHeight() - y - 1) * BLOCK_SIZE,
                         BLOCK_SIZE, BLOCK_SIZE, true);
        }
        if (myBoard.isGameOver()) {
            g2d.setFont(FONT_STYLE);
            g2d.setPaint(Color.WHITE);
            g2d.drawString("GAME OVER", getWidth() / DIVIDE_BY_6 + ADD_TO_X_1,
                           getHeight() / DIVIDE_BY_2);
        }
        
        if (myBoard.isPaused() && !myBoard.isGameOver()) {
            g2d.setFont(FONT_STYLE);
            g2d.setPaint(Color.WHITE);
            g2d.drawString("PAUSED", getWidth() / DIVIDE_BY_4 + ADD_TO_X_2,
                           getHeight() / DIVIDE_BY_2);
        }
    }
    
    
    @Override
    public void update(final Observable theObservable, final Object theObject) {
        repaint(); 
    }



}

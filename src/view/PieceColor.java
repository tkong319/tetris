/*
 * TCSS 305 - Autumn 2015
 * Assignment 6 - Tetris
 */


package view;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import model.Block;
/**
 * A class to hold the colors of each block.
 * @author Tung Kong
 * @version 1 11/30/15
 */
public class PieceColor {
    
    /** Color of I block. */
    private static final Color I_PIECE_COLOR = new Color(51, 255, 255);
    
    /** Color of J block. */
    private static final Color J_PIECE_COLOR = new Color(0, 128, 255);
    
    /** Color of L block. */
    private static final Color L_PIECE_COLOR = Color.ORANGE;
    
    /** Color of O block. */
    private static final Color O_PIECE_COLOR = Color.YELLOW;
    
    /** Color of S block. */
    private static final Color S_PIECE_COLOR = new Color(51, 255, 153);
    
    /** Color of T block. */
    private static final Color T_PIECE_COLOR = new Color(178, 102, 255);
    
    /** Color of Z block. */
    private static final Color Z_PIECE_COLOR = new Color(255, 102, 102);
    
    /** Map to hold each block and its corresponding color. */
    private final Map<Block, Color> myBlockColorMap;
    
    /** Constructs a Hash map of each block and its corresponding color. */
    public PieceColor() {
        myBlockColorMap = new HashMap<>();
        addToMap();
    }
    
    
    /**
     *  Returns a copy of map.
     * @return a copy of map.
     */
    public Map<Block, Color> getColorMap() {
        return new HashMap<>(myBlockColorMap);
    }
    
    /** private method to add blocks and their corresponding colors to map. */
    private void addToMap() {
        myBlockColorMap.put(Block.I, I_PIECE_COLOR);
        myBlockColorMap.put(Block.J, J_PIECE_COLOR);
        myBlockColorMap.put(Block.L, L_PIECE_COLOR);
        myBlockColorMap.put(Block.O, O_PIECE_COLOR);
        myBlockColorMap.put(Block.S, S_PIECE_COLOR);
        myBlockColorMap.put(Block.T, T_PIECE_COLOR);
        myBlockColorMap.put(Block.Z, Z_PIECE_COLOR);
    }
    
    

}

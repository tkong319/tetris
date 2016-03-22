/*
 * TCSS 305 - Autumn 2015
 * Assignment 6 - Tetris
 */

package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
/**
 * A Panel to game controls.
 * @author Tung Kong
 * @version 1 11/30/15
 */
public class ControlsPanel extends JPanel {

    /** Compiler generated serialVersionUID. */
    private static final long serialVersionUID = 1031758740304867701L;
 
    
    /** Panel width. */
    private static final int DISPLAY_WIDTH = 200;
    
    /** Panel height. */
    private static final int DISPLAY_HEIGHT = 150;
    
    /** Layout rows. */
    private static final int GRID_ROWS = 6;
    
    /** Layout columns. */
    private static final int GRID_COLUMNS = 2;
    
    
    /** Constructs panel to display controls. */
    public ControlsPanel() {
        super();
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(
                                                     EtchedBorder.LOWERED), "Controls"));
        setLayout(new GridLayout(GRID_ROWS, GRID_COLUMNS));
        setPreferredSize(new Dimension(DISPLAY_WIDTH, DISPLAY_HEIGHT));
        addLabels();
        
        setVisible(true);
    }
    
    
    /** Adds labels to this panel. */
    private void addLabels() {
        
        add(new JLabel("Move Down:"));
        add(new JLabel(KeyEvent.getKeyText(KeyEvent.VK_DOWN)));
        add(new JLabel("Move Right:"));
        add(new JLabel(KeyEvent.getKeyText(KeyEvent.VK_RIGHT)));
        add(new JLabel("Move Left:"));
        add(new JLabel(KeyEvent.getKeyText(KeyEvent.VK_LEFT)));
        add(new JLabel("Rotate:"));
        add(new JLabel(KeyEvent.getKeyText(KeyEvent.VK_UP)));
        add(new JLabel("Hard Drop:"));
        add(new JLabel(KeyEvent.getKeyText(KeyEvent.VK_SPACE)));
        add(new JLabel("Pause:"));
        add(new JLabel(KeyEvent.getKeyText(KeyEvent.VK_ENTER)));
    }

}

package rss.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import rss.logic.RSSData;

public class RSSDataRenderer extends JLabel implements ListCellRenderer <RSSData> {
    private RSSData data = null;
    private boolean isSelected = false;
    
    private final Font titleFont = new Font("monospaced", Font.BOLD, 14);
    private final Font dateFont = new Font("monospaced", Font.ITALIC, 11);
    private final Font bodyFont = new Font("monospaced", Font.ROMAN_BASELINE, 12);
    
    private final Color titleColor = new Color(56, 56, 56);
    private final Color dateColor = new Color(127, 127, 127);
    private final Color bodyColor = new Color(78, 78, 78);
    
    private final Color backgroundSelected = new Color(0, 162, 232);
    
    private JList list = null;
    
    @Override
    public Component getListCellRendererComponent(JList<? extends RSSData> list, RSSData value, int index, boolean isSelected, boolean cellHasFocus) {
        this.data = (RSSData) value;
        this.list = list;
        this.isSelected = isSelected;
        this.setPreferredSize(new Dimension(-1, 110));
        
        return this;
    }
    
    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        
        g2.setBackground(isSelected?backgroundSelected:Color.white);
        g2.clearRect(0, 0, getWidth(), getHeight());
        
        g2.setFont(titleFont);
        g2.setColor(isSelected?Color.white:titleColor);
        g2.drawString(data.title, 5, 15);
        
        g2.setFont(dateFont);
        g2.setColor(isSelected?Color.white:dateColor);
        g2.drawString(data.pubDate, 5, 30);
        
        g2.setFont(bodyFont);
        g2.setColor(isSelected?Color.white:bodyColor);
        
        // http://stackoverflow.com/questions/400566/full-justification-with-a-java-graphics-drawstring-replacement
        FontMetrics fm = g2.getFontMetrics();
        int curX = 5;
        int curY = 45;
        
        int w = getWidth();
        double wquarter = (double) w/4;
        int lineH = fm.getHeight();
        
        for (String word : data.description.split(" ")) {
            int wordWidth = fm.stringWidth(word + " ");
            if (curX + wordWidth > 5 + w) {
                curY += lineH;
                curX = 5;
            }
            
            if (curY > 100)
                break;
            else if (curX > w-wquarter && curY > 70) {
                g2.drawString(word+"...", curX, curY);
                break;
            } else
                g2.drawString(word, curX, curY);
            
            curX += wordWidth;
        }
    }
}
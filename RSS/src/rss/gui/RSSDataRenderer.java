package rss.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import rss.logic.RSSData;

public class RSSDataRenderer extends JLabel implements ListCellRenderer<Object> {
    private RSSData data = null;
    private boolean isSelected = false;
     public RSSDataRenderer() {
         setOpaque(true);
     }

     public Component getListCellRendererComponent(JList<?> list,
                                                   Object value,
                                                   int index,
                                                   boolean isSelected,
                                                   boolean cellHasFocus) {
         data = (RSSData) value;
         this.isSelected = isSelected;

         /*setText(value.toString());

         
         Color background;
         Color foreground;

         // check if this cell represents the current DnD drop location
         JList.DropLocation dropLocation = list.getDropLocation();
         if (dropLocation != null
                 && !dropLocation.isInsert()
                 && dropLocation.getIndex() == index) {

             background = Color.BLUE;
             foreground = Color.WHITE;

         // check if this cell is selected
         } else if (isSelected) {
             background = Color.GREEN;
             foreground = Color.WHITE;

         // unselected, and not the DnD drop location
         } else {
             background = Color.WHITE;
             foreground = Color.BLACK;
         };

         setBackground(background);
         setForeground(foreground);*/

        return this;
    }
     
    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        
        g2.setBackground(isSelected?Color.blue:Color.white);
        g2.clearRect(0, 0, getWidth(), getHeight());
        
        g2.setColor(isSelected?Color.white:Color.darkGray);
        g2.drawString(data.title, 5, 10);
        
        g2.setColor(isSelected?Color.lightGray:Color.black);
        g2.drawString(data.description, 5, 25);
    }
}


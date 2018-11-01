/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kitaponeri;

import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.ListCellRenderer;


public class ImgRenderer  extends DefaultListCellRenderer implements ListCellRenderer<Object> {
    //rivate static final Color HIGHLIGHT_COLOR = new Color(0, 0, 128);
    
    public ImgRenderer() {
    setOpaque(true);
    setIconTextGap(12);
  }
    
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        //return super.getListCellRendererComponent (list, value, index, isSelected, cellHasFocus);
        ImgInformation is= (ImgInformation) value;
        setIcon(is.getImg() );
        setText(is.getBookTitle());
        
        if(isSelected) {
            setBackground(Color.LIGHT_GRAY);
            setForeground(Color.white);
        }else{
            setBackground(Color.white);
            setForeground(Color.BLACK);
        }
        setEnabled(true);
        setFont(list.getFont() );
        
        return this;
        
    }    
}

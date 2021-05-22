package settlerstrailstorails;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;

class AveryJButton extends JButton {
    
    JButton button;
    boolean able;

    AveryJButton(JButton b){
        button = b;
        button.setEnabled(true);
        button.setVisible(true);
        b.addMouseListener(new MouseAdapter() {

            private boolean mouseOver, buttonPressed;

            @Override
            public void mouseEntered(MouseEvent evt) {
                mouseOver = true;
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                if (mouseOver && buttonPressed) {
                    button.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                    button.setLocation(button.getX() + 1, button.getY());
                }
                mouseOver = false;
                buttonPressed = false;
                setAble(false);
            }

            @Override
            public void mousePressed(MouseEvent evt) {
                if (button.isEnabled()) {
                    gameFrame.playSound("click");
                    button.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
                    button.setLocation(button.getX() - 1, button.getY());
                    buttonPressed = true;
                    setAble(true);
                }
            }

            @Override
            public void mouseReleased(MouseEvent evt) {
                if (mouseOver && buttonPressed) {
                    button.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                    button.setLocation(button.getX() + 1, button.getY());
                    buttonPressed = false;
                    setAble(false);
                }
            }
        });
    }
    public boolean isAble(){
        return able;
    }
    public void setAble(boolean a){
        able=a;
    }
    @Override
    public boolean isVisible(){        
        return button.isVisible();
    }
    @Override
    public void setVisible(boolean b){
        button.setVisible(b);
    }
    @Override
    public void setEnabled(boolean b){        
        button.setEnabled(b);
    }
    @Override
    public boolean isEnabled(){
        if(button==null){
            return true;
        }
        return button.isEnabled();
    }
    @Override
    public java.awt.Color getBackground(){
        if(button==null){
            return null;
        }
        return button.getBackground();
    }
}
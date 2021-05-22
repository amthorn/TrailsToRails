/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MineralRightsWindow.java
 *
 * Created on Oct 15, 2013, 11:37:42 PM
 */
package settlerstrailstorails;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.imageio.ImageIO;

/**
 *
 * @author User 1
 */
public class MineralRightsWindow extends javax.swing.JFrame {

    AveryJButton lumberButton,oreButton,coalButton,wheatButton,cattleButton;
    gameFrame game_Frame;
    boolean secondResource;
    /** Creates new form MineralRightsWindow */
    MineralRightsWindow(gameFrame g) {
        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(((dim.width/2)-(this.getSize().width/2)), ((dim.height/2)-((this.getSize().height/2)+20)));
        try{
            setIconImage(ImageIO.read(getClass().getResource("/settlerstrailstorails/resources/icon.png")));
        }catch(Exception e){
            
        }
        lumberButton = new AveryJButton(lumberButton1);
        oreButton = new AveryJButton(oreButton1);
        coalButton = new AveryJButton(coalButton1);
        wheatButton = new AveryJButton(wheatButton1);
        cattleButton = new AveryJButton(cattleButton1);
        game_Frame=g;
    }
    MineralRightsWindow(){
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        questionLabel = new javax.swing.JLabel();
        cattleButton1 = new javax.swing.JButton();
        oreButton1 = new javax.swing.JButton();
        wheatButton1 = new javax.swing.JButton();
        coalButton1 = new javax.swing.JButton();
        lumberButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setAlwaysOnTop(true);
        setFocusable(false);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        questionLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        questionLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        questionLabel.setText("Please select your first resource");
        jPanel1.add(questionLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 20, 370, -1));

        cattleButton1.setBackground(new java.awt.Color(239, 228, 176));
        cattleButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        cattleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/cattleRaw.png"))); // NOI18N
        cattleButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cattleButton1.setContentAreaFilled(false);
        cattleButton1.setFocusable(false);
        cattleButton1.setOpaque(true);
        cattleButton1.setPreferredSize(new java.awt.Dimension(75, 25));
        cattleButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cattleButton1MouseReleased(evt);
            }
        });
        jPanel1.add(cattleButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(165, 65, 50, 50));

        oreButton1.setBackground(new java.awt.Color(239, 228, 176));
        oreButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        oreButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/oreRaw.png"))); // NOI18N
        oreButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        oreButton1.setContentAreaFilled(false);
        oreButton1.setFocusable(false);
        oreButton1.setOpaque(true);
        oreButton1.setPreferredSize(new java.awt.Dimension(75, 25));
        oreButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                oreButton1MouseReleased(evt);
            }
        });
        jPanel1.add(oreButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(235, 65, 50, 50));

        wheatButton1.setBackground(new java.awt.Color(239, 228, 176));
        wheatButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        wheatButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/wheatRaw.png"))); // NOI18N
        wheatButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        wheatButton1.setContentAreaFilled(false);
        wheatButton1.setFocusable(false);
        wheatButton1.setOpaque(true);
        wheatButton1.setPreferredSize(new java.awt.Dimension(75, 25));
        wheatButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                wheatButton1MouseReleased(evt);
            }
        });
        jPanel1.add(wheatButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(305, 65, 50, 50));

        coalButton1.setBackground(new java.awt.Color(239, 228, 176));
        coalButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        coalButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/coalRaw.png"))); // NOI18N
        coalButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        coalButton1.setContentAreaFilled(false);
        coalButton1.setFocusable(false);
        coalButton1.setOpaque(true);
        coalButton1.setPreferredSize(new java.awt.Dimension(75, 25));
        coalButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                coalButton1MouseReleased(evt);
            }
        });
        jPanel1.add(coalButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 65, 50, 50));

        lumberButton1.setBackground(new java.awt.Color(239, 228, 176));
        lumberButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        lumberButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/lumberRaw.png"))); // NOI18N
        lumberButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lumberButton1.setContentAreaFilled(false);
        lumberButton1.setFocusable(false);
        lumberButton1.setOpaque(true);
        lumberButton1.setPreferredSize(new java.awt.Dimension(75, 25));
        lumberButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lumberButton1MouseReleased(evt);
            }
        });
        jPanel1.add(lumberButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 65, 50, 50));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/mineralRightsBackground.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void coalButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_coalButton1MouseReleased
        if(coalButton.isAble()){
            if(secondResource){
                game_Frame.getCurrPlayer().plusCoal();
                this.setVisible(false);
                game_Frame.enableMenu();
            }else{
                game_Frame.getCurrPlayer().plusCoal();
                secondResource=true;
                questionLabel.setText("Please select your second resource");
            }
        }
}//GEN-LAST:event_coalButton1MouseReleased
    private void lumberButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lumberButton1MouseReleased
        if(lumberButton.isAble()){
            if(secondResource){
                game_Frame.getCurrPlayer().plusLumber();
                this.setVisible(false);
                game_Frame.enableMenu();
            }else{
                game_Frame.getCurrPlayer().plusLumber();
                secondResource=true;
                questionLabel.setText("Please select your second resource");
            }
        }
    }//GEN-LAST:event_lumberButton1MouseReleased
    private void wheatButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_wheatButton1MouseReleased
        if(wheatButton.isAble()){
            if(secondResource){
                game_Frame.getCurrPlayer().plusWheat();
                this.setVisible(false);
                game_Frame.enableMenu();
            }else{
                game_Frame.getCurrPlayer().plusWheat();
                secondResource=true;
                questionLabel.setText("Please select your second resource");
            }
        }
    }//GEN-LAST:event_wheatButton1MouseReleased
    private void oreButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_oreButton1MouseReleased
        if(oreButton.isAble()){
            if(secondResource){
                game_Frame.getCurrPlayer().plusOre();
                this.setVisible(false);
                game_Frame.enableMenu();
            }else{
                game_Frame.getCurrPlayer().plusOre();
                secondResource=true;
                questionLabel.setText("Please select your second resource");
            }
        }
    }//GEN-LAST:event_oreButton1MouseReleased
    private void cattleButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cattleButton1MouseReleased
        if(cattleButton.isAble()){
            if(secondResource){
                game_Frame.getCurrPlayer().plusCattle();
                this.setVisible(false);
                game_Frame.enableMenu();
            }else{
                game_Frame.getCurrPlayer().plusCattle();
                secondResource=true;
                questionLabel.setText("Please select your second resource");
            }
        }
    }//GEN-LAST:event_cattleButton1MouseReleased
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new MineralRightsWindow().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cattleButton1;
    private javax.swing.JButton coalButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton lumberButton1;
    private javax.swing.JButton oreButton1;
    private javax.swing.JLabel questionLabel;
    private javax.swing.JButton wheatButton1;
    // End of variables declaration//GEN-END:variables
}
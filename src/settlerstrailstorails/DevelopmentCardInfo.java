/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DevelopmentCardInfo.java
 *
 * Created on Oct 14, 2013, 2:36:35 AM
 */
package settlerstrailstorails;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.imageio.ImageIO;

/**
 *
 * @author User 1
 */
public class DevelopmentCardInfo extends javax.swing.JFrame {

    AvaJButton closeButtonR1, closeButtonR2, closeButtonR3,
            closeButtonR4, closeButtonR5, closeButtonR6,closeButtonR7;
    /** Creates new form DevelopmentCardInfo */
    public DevelopmentCardInfo(int card) {
        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(((dim.width/2)-(this.getSize().width/2)), ((dim.height/2)-((this.getSize().height/2)+20)));
        try{
            setIconImage(ImageIO.read(getClass().getResource("/settlerstrailstorails/resources/icon.png")));
        }catch(Exception e){
            
        }
        jPanel1.setVisible(false);
        jPanel2.setVisible(false);
        jPanel3.setVisible(false);
        jPanel4.setVisible(false);
        jPanel5.setVisible(false);
        jPanel6.setVisible(false);
        jPanel7.setVisible(false);
        switch(card){
            case 1: jPanel1.setVisible(true);
                break;
            case 2: jPanel2.setVisible(true);
                break;
            case 3: jPanel3.setVisible(true);
                break;
            case 4: jPanel4.setVisible(true);
                break;
            case 5: jPanel5.setVisible(true);
                break;
            case 6: jPanel6.setVisible(true);
                break;
            case 7: jPanel7.setVisible(true);
                break;
        }
        closeButtonR1 = new AvaJButton(closeButton1);
        closeButtonR2 = new AvaJButton(closeButton2);
        closeButtonR3 = new AvaJButton(closeButton3);
        closeButtonR4 = new AvaJButton(closeButton4);
        closeButtonR5 = new AvaJButton(closeButton5);
        closeButtonR6 = new AvaJButton(closeButton6);
        closeButtonR7 = new AvaJButton(closeButton7);
    }
    public DevelopmentCardInfo(){
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

        jPanel7 = new javax.swing.JPanel();
        closeButton7 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        closeButton6 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        closeButton5 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        closeButton4 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        closeButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        closeButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        closeButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(239, 228, 176));
        setFocusable(false);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(239, 228, 176));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        closeButton7.setBackground(new java.awt.Color(239, 228, 176));
        closeButton7.setFont(new java.awt.Font("Tahoma", 1, 11));
        closeButton7.setText("CLOSE");
        closeButton7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        closeButton7.setContentAreaFilled(false);
        closeButton7.setFocusable(false);
        closeButton7.setOpaque(true);
        closeButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                closeButton7MouseReleased(evt);
            }
        });
        jPanel7.add(closeButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 137, 30));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/cardCattleDrive.png"))); // NOI18N
        jPanel7.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 137, 200));

        getContentPane().add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 137, 230));

        jPanel6.setBackground(new java.awt.Color(239, 228, 176));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        closeButton6.setBackground(new java.awt.Color(239, 228, 176));
        closeButton6.setFont(new java.awt.Font("Tahoma", 1, 11));
        closeButton6.setText("CLOSE");
        closeButton6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        closeButton6.setContentAreaFilled(false);
        closeButton6.setFocusable(false);
        closeButton6.setOpaque(true);
        closeButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                closeButton6MouseReleased(evt);
            }
        });
        jPanel6.add(closeButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 137, 30));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/cardRightOfWay.png"))); // NOI18N
        jPanel6.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 137, 200));

        getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 137, 230));

        jPanel5.setBackground(new java.awt.Color(239, 228, 176));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        closeButton5.setBackground(new java.awt.Color(239, 228, 176));
        closeButton5.setFont(new java.awt.Font("Tahoma", 1, 11));
        closeButton5.setText("CLOSE");
        closeButton5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        closeButton5.setContentAreaFilled(false);
        closeButton5.setFocusable(false);
        closeButton5.setOpaque(true);
        closeButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                closeButton5MouseReleased(evt);
            }
        });
        jPanel5.add(closeButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 137, 30));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/cardNativeSupport.png"))); // NOI18N
        jPanel5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 137, 200));

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 137, 230));

        jPanel4.setBackground(new java.awt.Color(239, 228, 176));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        closeButton4.setBackground(new java.awt.Color(239, 228, 176));
        closeButton4.setFont(new java.awt.Font("Tahoma", 1, 11));
        closeButton4.setText("CLOSE");
        closeButton4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        closeButton4.setContentAreaFilled(false);
        closeButton4.setFocusable(false);
        closeButton4.setOpaque(true);
        closeButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                closeButton4MouseReleased(evt);
            }
        });
        jPanel4.add(closeButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 137, 30));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/cardMineralRights.png"))); // NOI18N
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 137, 200));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 137, 230));

        jPanel3.setBackground(new java.awt.Color(239, 228, 176));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        closeButton3.setBackground(new java.awt.Color(239, 228, 176));
        closeButton3.setFont(new java.awt.Font("Tahoma", 1, 11));
        closeButton3.setText("CLOSE");
        closeButton3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        closeButton3.setContentAreaFilled(false);
        closeButton3.setFocusable(false);
        closeButton3.setOpaque(true);
        closeButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                closeButton3MouseReleased(evt);
            }
        });
        jPanel3.add(closeButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 137, 30));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/cardScout.png"))); // NOI18N
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 137, 200));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 137, 230));

        jPanel2.setBackground(new java.awt.Color(239, 228, 176));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        closeButton2.setBackground(new java.awt.Color(239, 228, 176));
        closeButton2.setFont(new java.awt.Font("Tahoma", 1, 11));
        closeButton2.setText("CLOSE");
        closeButton2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        closeButton2.setContentAreaFilled(false);
        closeButton2.setFocusable(false);
        closeButton2.setOpaque(true);
        closeButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                closeButton2MouseReleased(evt);
            }
        });
        jPanel2.add(closeButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 137, 30));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/cardEngineer.png"))); // NOI18N
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 137, 200));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 137, 230));

        jPanel1.setBackground(new java.awt.Color(239, 228, 176));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        closeButton1.setBackground(new java.awt.Color(239, 228, 176));
        closeButton1.setFont(new java.awt.Font("Tahoma", 1, 11));
        closeButton1.setText("CLOSE");
        closeButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        closeButton1.setContentAreaFilled(false);
        closeButton1.setFocusable(false);
        closeButton1.setOpaque(true);
        closeButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                closeButton1MouseReleased(evt);
            }
        });
        jPanel1.add(closeButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 137, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/cardCalvary.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 137, 200));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 137, 230));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButton1MouseReleased
        if(closeButtonR1.isAble()){
            this.setVisible(false);            
        }
}//GEN-LAST:event_closeButton1MouseReleased
    private void closeButton2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButton2MouseReleased
        if(closeButtonR2.isAble()){
            this.setVisible(false);            
        }
    }//GEN-LAST:event_closeButton2MouseReleased
    private void closeButton3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButton3MouseReleased
        if(closeButtonR3.isAble()){
            this.setVisible(false);            
        }
    }//GEN-LAST:event_closeButton3MouseReleased
    private void closeButton4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButton4MouseReleased
        if(closeButtonR4.isAble()){
            this.setVisible(false);            
        }
    }//GEN-LAST:event_closeButton4MouseReleased
    private void closeButton5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButton5MouseReleased
        if(closeButtonR5.isAble()){
            this.setVisible(false);            
        }
    }//GEN-LAST:event_closeButton5MouseReleased
    private void closeButton6MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButton6MouseReleased
        if(closeButtonR6.isAble()){
            this.setVisible(false);            
        }
    }//GEN-LAST:event_closeButton6MouseReleased
    private void closeButton7MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButton7MouseReleased
        if(closeButtonR7.isAble()){
            this.setVisible(false);            
        }
    }//GEN-LAST:event_closeButton7MouseReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new DevelopmentCardInfo().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton1;
    private javax.swing.JButton closeButton2;
    private javax.swing.JButton closeButton3;
    private javax.swing.JButton closeButton4;
    private javax.swing.JButton closeButton5;
    private javax.swing.JButton closeButton6;
    private javax.swing.JButton closeButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TradeConfirm.java
 *
 * Created on Oct 18, 2013, 4:19:50 PM
 */
package settlerstrailstorails;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author User 1
 */
public class TradeConfirm extends javax.swing.JFrame {
    
    Player them,you;    
    ArrayList<Player> playersLeft;
    AveryJButton rejectButton,acceptButton;
    int lumberGet,lumberGive,coalGet,coalGive,cattleGet,cattleGive,
            oreGet,oreGive,wheatGive,wheatGet,goldGive,goldGet;
    gameFrame game_Frame;
    
    /** Creates new form TradeConfirm */
    TradeConfirm(Player them, Player you, int lumberGet, int lumberGive,
            int coalGet,int coalGive,int cattleGet,int cattleGive,int oreGet,
            int oreGive, int wheatGive,int wheatGet, int goldGive,int goldGet,
            ArrayList<Player> playersLeft,gameFrame g) {
        initComponents();
        game_Frame=g;
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        try{
            setIconImage(ImageIO.read(getClass().getResource("/settlerstrailstorails/resources/icon.png")));
        }catch(Exception e){
            
        }
        this.playersLeft=playersLeft;
        this.lumberGet=lumberGet;
        this.lumberGive=lumberGive;
        this.coalGet=coalGet;
        this.coalGive=coalGive;
        this.cattleGet=cattleGet;
        this.cattleGive=cattleGive;
        this.oreGet=oreGet;
        this.oreGive=oreGive;
        this.wheatGive=wheatGive;
        this.wheatGet=wheatGet;
        this.goldGive=goldGive;
        this.goldGet=goldGet;
        this.them=them;
        this.you=you;
        ImageIcon redHouse= new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/redHouse.png", Color.WHITE));
        ImageIcon orangeHouse= new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/orangeHouse.png", Color.WHITE));
        ImageIcon greenHouse= new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/greenHouse.png", Color.WHITE));
        ImageIcon whiteHouse= new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/whiteHouse.png", Color.WHITE));
        rejectButton = new AveryJButton(rejectButton1);
        acceptButton = new AveryJButton(acceptButton1);
        topLabel.setText(them.getName()+" is offering a trade");
        if(them.getColor() == Player.Color.WHITE) {
            themColorLabel.setIcon(whiteHouse);
        }else if(them.getColor() == Player.Color.ORANGE) {
            themColorLabel.setIcon(orangeHouse);
        }else if(them.getColor() == Player.Color.GREEN) {
            themColorLabel.setIcon(greenHouse);
        }else if(them.getColor() == Player.Color.RED) {
            themColorLabel.setIcon(redHouse);
        }
        themColorLabel.setText(them.getName());
        if(you.getColor() == Player.Color.WHITE) {
            youColorLabel.setIcon(whiteHouse);
        }else if(you.getColor() == Player.Color.ORANGE) {
            youColorLabel.setIcon(orangeHouse);
        }else if(you.getColor() == Player.Color.GREEN) {
            youColorLabel.setIcon(greenHouse);
        }else if(you.getColor() == Player.Color.RED) {
            youColorLabel.setIcon(redHouse);
        }
        youColorLabel.setText("You");
        lumberLabelPlayerGiveVar.setText(String.valueOf(lumberGive));
        coalLabelPlayerGiveVar.setText(String.valueOf(coalGive));
        wheatLabelPlayerGiveVar.setText(String.valueOf(wheatGive));
        cattleLabelPlayerGiveVar.setText(String.valueOf(cattleGive));
        oreLabelPlayerGiveVar.setText(String.valueOf(oreGive));
        goldLabelPlayerGiveVar.setText(String.valueOf(goldGive));
        
        lumberLabelPlayerGetVar.setText(String.valueOf(lumberGet));
        coalLabelPlayerGetVar.setText(String.valueOf(coalGet));
        wheatLabelPlayerGetVar.setText(String.valueOf(wheatGet));
        cattleLabelPlayerGetVar.setText(String.valueOf(cattleGet));
        oreLabelPlayerGetVar.setText(String.valueOf(oreGet));
        goldLabelPlayerGetVar.setText(String.valueOf(goldGet));
    }
    public TradeConfirm(){
        initComponents();
    }
    public final BufferedImage loadImage(String ref) {  //<editor-fold>
        BufferedImage bimg = null;  
        try {    
            bimg = ImageIO.read(getClass().getResource(ref));  
        } catch (Exception e) {
            System.err.print(e);
        }
        return bimg;  
    }//</editor-fold>
    public final BufferedImage makeColorTransparent(String ref, Color color) {//<editor-fold>
        BufferedImage image = loadImage(ref);
        BufferedImage dimg = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);  
    Graphics2D g = dimg.createGraphics();  
    g.setComposite(AlphaComposite.Src);  
    g.drawImage(image, null, 0, 0);  
    g.dispose();  
    for(int i = 0; i < dimg.getHeight(); i++) {  
        for(int j = 0; j < dimg.getWidth(); j++) {  
            if(dimg.getRGB(j, i) == color.getRGB()) {  
            dimg.setRGB(j, i, 0x8F1C1C);  
            }  
        }  
    }  
    return dimg;  
}//</editor-fold>

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        topLabel = new javax.swing.JLabel();
        rejectButton1 = new javax.swing.JButton();
        acceptButton1 = new javax.swing.JButton();
        tradeGivePlayerDisplayPanel = new javax.swing.JPanel();
        lumberLabelPlayerGive = new javax.swing.JLabel();
        lumberLabelPlayerGiveVar = new javax.swing.JLabel();
        coalLabelPlayerGive = new javax.swing.JLabel();
        coalLabelPlayerGiveVar = new javax.swing.JLabel();
        cattleLabelPlayerGive = new javax.swing.JLabel();
        cattleLabelPlayerGiveVar = new javax.swing.JLabel();
        oreLabelPlayerGive = new javax.swing.JLabel();
        oreLabelPlayerGiveVar = new javax.swing.JLabel();
        wheatLabelPlayerGiveVar = new javax.swing.JLabel();
        wheatLabelPlayerGive = new javax.swing.JLabel();
        goldLabelPlayerGive = new javax.swing.JLabel();
        goldLabelPlayerGiveVar = new javax.swing.JLabel();
        tradeGetPlayerDisplayPanel = new javax.swing.JPanel();
        goldLabelPlayerGetVar = new javax.swing.JLabel();
        goldLabelPlayerGet = new javax.swing.JLabel();
        wheatLabelPlayerGetVar = new javax.swing.JLabel();
        wheatLabelPlayerGet = new javax.swing.JLabel();
        oreLabelPlayerGetVar = new javax.swing.JLabel();
        oreLabelPlayerGet = new javax.swing.JLabel();
        lumberLabelPlayerGetVar = new javax.swing.JLabel();
        lumberLabelPlayerGet = new javax.swing.JLabel();
        coalLabelPlayerGetVar = new javax.swing.JLabel();
        coalLabelPlayerGet = new javax.swing.JLabel();
        cattleLabelPlayerGetVar = new javax.swing.JLabel();
        cattleLabelPlayerGet = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        themColorLabel = new javax.swing.JLabel();
        youColorLabel = new javax.swing.JLabel();
        labelBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        topLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        topLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        topLabel.setText("Red Player is offering a trade");
        jPanel1.add(topLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 360, -1));

        rejectButton1.setBackground(new java.awt.Color(239, 228, 176));
        rejectButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        rejectButton1.setText("Reject");
        rejectButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        rejectButton1.setContentAreaFilled(false);
        rejectButton1.setFocusable(false);
        rejectButton1.setOpaque(true);
        rejectButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                rejectButton1MouseReleased(evt);
            }
        });
        jPanel1.add(rejectButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 190, 150, 30));

        acceptButton1.setBackground(new java.awt.Color(239, 228, 176));
        acceptButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        acceptButton1.setText("Accept");
        acceptButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        acceptButton1.setContentAreaFilled(false);
        acceptButton1.setFocusable(false);
        acceptButton1.setOpaque(true);
        acceptButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                acceptButton1MouseReleased(evt);
            }
        });
        jPanel1.add(acceptButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(197, 190, 150, 30));

        tradeGivePlayerDisplayPanel.setBackground(new java.awt.Color(239, 228, 176));
        tradeGivePlayerDisplayPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tradeGivePlayerDisplayPanel.setFocusable(false);
        tradeGivePlayerDisplayPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lumberLabelPlayerGive.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/lumberRawSmall.png"))); // NOI18N
        tradeGivePlayerDisplayPanel.add(lumberLabelPlayerGive, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 5, -1, -1));

        lumberLabelPlayerGiveVar.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        lumberLabelPlayerGiveVar.setText("0");
        tradeGivePlayerDisplayPanel.add(lumberLabelPlayerGiveVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 7, -1, -1));

        coalLabelPlayerGive.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/coalRawSmall.png"))); // NOI18N
        tradeGivePlayerDisplayPanel.add(coalLabelPlayerGive, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 34, -1, -1));

        coalLabelPlayerGiveVar.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        coalLabelPlayerGiveVar.setText("0");
        tradeGivePlayerDisplayPanel.add(coalLabelPlayerGiveVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 36, -1, -1));

        cattleLabelPlayerGive.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/cattleRawSmall.png"))); // NOI18N
        tradeGivePlayerDisplayPanel.add(cattleLabelPlayerGive, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 64, -1, -1));

        cattleLabelPlayerGiveVar.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        cattleLabelPlayerGiveVar.setText("0");
        tradeGivePlayerDisplayPanel.add(cattleLabelPlayerGiveVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 66, -1, -1));

        oreLabelPlayerGive.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/oreRawSmall.png"))); // NOI18N
        tradeGivePlayerDisplayPanel.add(oreLabelPlayerGive, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 5, -1, -1));

        oreLabelPlayerGiveVar.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        oreLabelPlayerGiveVar.setText("0");
        tradeGivePlayerDisplayPanel.add(oreLabelPlayerGiveVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(86, 7, -1, -1));

        wheatLabelPlayerGiveVar.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        wheatLabelPlayerGiveVar.setText("0");
        tradeGivePlayerDisplayPanel.add(wheatLabelPlayerGiveVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(86, 36, -1, -1));

        wheatLabelPlayerGive.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/wheatRawSmall.png"))); // NOI18N
        tradeGivePlayerDisplayPanel.add(wheatLabelPlayerGive, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 34, -1, -1));

        goldLabelPlayerGive.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/gold.png"))); // NOI18N
        tradeGivePlayerDisplayPanel.add(goldLabelPlayerGive, new org.netbeans.lib.awtextra.AbsoluteConstraints(64, 68, -1, -1));

        goldLabelPlayerGiveVar.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        goldLabelPlayerGiveVar.setText("0");
        tradeGivePlayerDisplayPanel.add(goldLabelPlayerGiveVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(86, 66, -1, -1));

        jPanel1.add(tradeGivePlayerDisplayPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 90, 105, 90));

        tradeGetPlayerDisplayPanel.setBackground(new java.awt.Color(239, 228, 176));
        tradeGetPlayerDisplayPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tradeGetPlayerDisplayPanel.setFocusable(false);
        tradeGetPlayerDisplayPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        goldLabelPlayerGetVar.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        goldLabelPlayerGetVar.setText("0");
        tradeGetPlayerDisplayPanel.add(goldLabelPlayerGetVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(86, 66, -1, -1));

        goldLabelPlayerGet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/gold.png"))); // NOI18N
        tradeGetPlayerDisplayPanel.add(goldLabelPlayerGet, new org.netbeans.lib.awtextra.AbsoluteConstraints(64, 68, -1, -1));

        wheatLabelPlayerGetVar.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        wheatLabelPlayerGetVar.setText("0");
        tradeGetPlayerDisplayPanel.add(wheatLabelPlayerGetVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(86, 36, -1, -1));

        wheatLabelPlayerGet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/wheatRawSmall.png"))); // NOI18N
        tradeGetPlayerDisplayPanel.add(wheatLabelPlayerGet, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 34, -1, -1));

        oreLabelPlayerGetVar.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        oreLabelPlayerGetVar.setText("0");
        tradeGetPlayerDisplayPanel.add(oreLabelPlayerGetVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(86, 7, -1, -1));

        oreLabelPlayerGet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/oreRawSmall.png"))); // NOI18N
        tradeGetPlayerDisplayPanel.add(oreLabelPlayerGet, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 5, -1, -1));

        lumberLabelPlayerGetVar.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        lumberLabelPlayerGetVar.setText("0");
        tradeGetPlayerDisplayPanel.add(lumberLabelPlayerGetVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 7, -1, -1));

        lumberLabelPlayerGet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/lumberRawSmall.png"))); // NOI18N
        tradeGetPlayerDisplayPanel.add(lumberLabelPlayerGet, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 5, -1, -1));

        coalLabelPlayerGetVar.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        coalLabelPlayerGetVar.setText("0");
        tradeGetPlayerDisplayPanel.add(coalLabelPlayerGetVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 36, -1, -1));

        coalLabelPlayerGet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/coalRawSmall.png"))); // NOI18N
        tradeGetPlayerDisplayPanel.add(coalLabelPlayerGet, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 34, -1, -1));

        cattleLabelPlayerGetVar.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        cattleLabelPlayerGetVar.setText("0");
        tradeGetPlayerDisplayPanel.add(cattleLabelPlayerGetVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 66, -1, -1));

        cattleLabelPlayerGet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/cattleRawSmall.png"))); // NOI18N
        tradeGetPlayerDisplayPanel.add(cattleLabelPlayerGet, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 64, -1, -1));

        jPanel1.add(tradeGetPlayerDisplayPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(242, 90, 105, 90));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/tradeForArrow.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, -1, -1));

        themColorLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        themColorLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/orangeHouse.png"))); // NOI18N
        themColorLabel.setText("Orange Player");
        jPanel1.add(themColorLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 70, 170, -1));

        youColorLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        youColorLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/orangeHouse.png"))); // NOI18N
        youColorLabel.setText("Orange Player");
        jPanel1.add(youColorLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(242, 70, 135, -1));

        labelBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/tradeConfirmBackground.png"))); // NOI18N
        jPanel1.add(labelBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rejectButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rejectButton1MouseReleased
        if(rejectButton.isAble()){
            this.setVisible(false);
            if(!playersLeft.isEmpty()){
                Player p = playersLeft.remove(0);
                game_Frame.getCurrPlayer().setTurn(false);
                game_Frame.setCurrPlayer(p);
                p.setTurn(true);
                game_Frame.setBackgroundPlayer();
                TradeConfirm tc = new TradeConfirm(them,p, lumberGet, 
                        lumberGive, coalGet,coalGive,cattleGet,cattleGive,
                        oreGet,oreGive,wheatGive,wheatGet,goldGive,goldGet,
                        playersLeft,game_Frame);
                tc.setVisible(true);
            }else{
                game_Frame.enableMenu();
                game_Frame.getCurrPlayer().setTurn(false);
                game_Frame.setCurrPlayer(game_Frame.getRealPlayer());
                game_Frame.getCurrPlayer().setTurn(true);
                game_Frame.setBackgroundPlayer();
                new SettlersConfirmDialog(21).setVisible(true);
            }
        }
}//GEN-LAST:event_rejectButton1MouseReleased

    private void acceptButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_acceptButton1MouseReleased
        if(acceptButton.isAble()){
            this.setVisible(false);
            you.setLumber(you.getLumber()+lumberGive);
            you.setOre(you.getOre()+oreGive);
            you.setWheat(you.getWheat()+wheatGive);
            you.setCattle(you.getCattle()+cattleGive);
            you.setCoal(you.getCoal()+coalGive);
            
            you.setLumber(you.getLumber()-lumberGet);
            you.setOre(you.getOre()-oreGet);
            you.setWheat(you.getWheat()-wheatGet);
            you.setCattle(you.getCattle()-cattleGet);
            you.setCoal(you.getCoal()-coalGet);
            
            them.setLumber(them.getLumber()+lumberGet);
            them.setOre(them.getOre()+oreGet);
            them.setWheat(them.getWheat()+wheatGet);
            them.setCattle(them.getCattle()+cattleGet);
            them.setCoal(them.getCoal()+coalGet);
            
            them.setLumber(them.getLumber()-lumberGive);
            them.setOre(them.getOre()-oreGive);
            them.setWheat(them.getWheat()-wheatGive);
            them.setCattle(them.getCattle()-cattleGive);
            them.setCoal(them.getCoal()-coalGive);
            
            new SettlersConfirmDialog(you).setVisible(true);            
            game_Frame.getCurrPlayer().setTurn(false);
            game_Frame.setCurrPlayer(game_Frame.getRealPlayer());
            game_Frame.getCurrPlayer().setTurn(true);
            game_Frame.setBackgroundPlayer();
            game_Frame.enableMenu();
        }
    }//GEN-LAST:event_acceptButton1MouseReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new TradeConfirm().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton acceptButton1;
    private javax.swing.JLabel cattleLabelPlayerGet;
    private javax.swing.JLabel cattleLabelPlayerGetVar;
    private javax.swing.JLabel cattleLabelPlayerGive;
    private javax.swing.JLabel cattleLabelPlayerGiveVar;
    private javax.swing.JLabel coalLabelPlayerGet;
    private javax.swing.JLabel coalLabelPlayerGetVar;
    private javax.swing.JLabel coalLabelPlayerGive;
    private javax.swing.JLabel coalLabelPlayerGiveVar;
    private javax.swing.JLabel goldLabelPlayerGet;
    private javax.swing.JLabel goldLabelPlayerGetVar;
    private javax.swing.JLabel goldLabelPlayerGive;
    private javax.swing.JLabel goldLabelPlayerGiveVar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelBackground;
    private javax.swing.JLabel lumberLabelPlayerGet;
    private javax.swing.JLabel lumberLabelPlayerGetVar;
    private javax.swing.JLabel lumberLabelPlayerGive;
    private javax.swing.JLabel lumberLabelPlayerGiveVar;
    private javax.swing.JLabel oreLabelPlayerGet;
    private javax.swing.JLabel oreLabelPlayerGetVar;
    private javax.swing.JLabel oreLabelPlayerGive;
    private javax.swing.JLabel oreLabelPlayerGiveVar;
    private javax.swing.JButton rejectButton1;
    private javax.swing.JLabel themColorLabel;
    private javax.swing.JLabel topLabel;
    private javax.swing.JPanel tradeGetPlayerDisplayPanel;
    private javax.swing.JPanel tradeGivePlayerDisplayPanel;
    private javax.swing.JLabel wheatLabelPlayerGet;
    private javax.swing.JLabel wheatLabelPlayerGetVar;
    private javax.swing.JLabel wheatLabelPlayerGive;
    private javax.swing.JLabel wheatLabelPlayerGiveVar;
    private javax.swing.JLabel youColorLabel;
    // End of variables declaration//GEN-END:variables
}

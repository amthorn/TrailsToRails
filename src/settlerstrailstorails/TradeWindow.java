/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TradeWindow.java
 *
 * Created on Oct 17, 2013, 5:14:13 PM
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
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;

/**
 *
 * @author User 1
 */
public class TradeWindow extends javax.swing.JFrame {
    
    boolean lumberBankGivePressed,coalBankGivePressed,oreBankGivePressed,
            wheatBankGivePressed,cattleBankGivePressed,goldBankGivePressed,
            lumberBankGetPressed,coalBankGetPressed,oreBankGetPressed,
            wheatBankGetPressed,cattleBankGetPressed,goldBankGetPressed,
            tradeWithBank=true;    
    int receiveLumber=0,receiveCoal=0,receiveCattle=0,receiveWheat=0,receiveOre=0,receiveGold=0,
            giveLumber=0,giveCoal=0,giveCattle=0,giveWheat=0,giveOre=0,giveGold=0;
    ImageIcon gold,lumber,coal,ore,wheat,cattle,smallGold;
    Player currPlayer;
    AveryJButton tradeButton,xButton,lumberPlayerGetButton,coalPlayerGetButton,
            cattlePlayerGetButton,orePlayerGetButton,wheatPlayerGetButton,
            goldPlayerGetButton,lumberPlayerGiveButton,coalPlayerGiveButton,
            cattlePlayerGiveButton,orePlayerGiveButton,wheatPlayerGiveButton,
            goldPlayerGiveButton,clearGetButton,clearGiveButton;
    gameFrame game_Frame;
    
    /** Creates new form TradeWindow */
    TradeWindow(Player p,gameFrame g) {
        initComponents();
        game_Frame=g;
        currPlayer=p;
        tradeButton = new AveryJButton(tradeButton1);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(((dim.width/2)-(this.getSize().width/2)), ((dim.height/2)-((this.getSize().height/2)+20)));
        try{
            setIconImage(ImageIO.read(getClass().getResource("/settlerstrailstorails/resources/icon.png")));
        }catch(Exception e){
            
        }
        gold= new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/goldButton.png", Color.WHITE));
        lumber=new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/lumberRaw.png", Color.WHITE));
        ore=new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/oreRaw.png", Color.WHITE));
        cattle=new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/cattleRaw.png", Color.WHITE));
        coal=new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/coalRaw.png", Color.WHITE));
        wheat=new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/wheatRaw.png", Color.WHITE));
        smallGold=new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/gold.png", Color.WHITE));
        goldLabelPlayerGive.setIcon(smallGold);
        goldLabelPlayerGet.setIcon(smallGold);
        clearGetButton = new AveryJButton(clearGetButton1);
        clearGiveButton = new AveryJButton(clearGiveButton1);
        lumberPlayerGiveButton = new AveryJButton(lumberPlayerGiveButton1);
        coalPlayerGiveButton = new AveryJButton(coalPlayerGiveButton1);
        cattlePlayerGiveButton = new AveryJButton(cattlePlayerGiveButton1);
        wheatPlayerGiveButton = new AveryJButton(wheatPlayerGiveButton1);
        orePlayerGiveButton = new AveryJButton(orePlayerGiveButton1);
        goldPlayerGiveButton = new AveryJButton(goldPlayerGiveButton1);
        lumberPlayerGetButton = new AveryJButton(lumberPlayerGetButton1);
        coalPlayerGetButton = new AveryJButton(coalPlayerGetButton1);
        cattlePlayerGetButton = new AveryJButton(cattlePlayerGetButton1);
        wheatPlayerGetButton = new AveryJButton(wheatPlayerGetButton1);
        orePlayerGetButton = new AveryJButton(orePlayerGetButton1);
        goldPlayerGetButton = new AveryJButton(goldPlayerGetButton1);
        goldBankGetButton.setIcon(gold);
        goldBankGiveButton.setIcon(gold);
        goldPlayerGetButton.setIcon(gold);
        goldPlayerGiveButton.setIcon(gold);
        jLabel8.setVisible(false);
        jLabel9.setVisible(false);
        xButton = new AveryJButton(xButton1);
        bankTradePanel.setVisible(true);
        playerTradePanel.setVisible(false);
        jLabel10.setVisible(false);
        if(p.getOre()<3){
            oreBankGiveButton.setEnabled(false);
        }
        if(p.getCattle()<3){
            cattleBankGiveButton.setEnabled(false);
        }
        if(p.getCoal()<3){
            coalBankGiveButton.setEnabled(false);
        }
        if(p.getWheat()<3){
            wheatBankGiveButton.setEnabled(false);
        }
        if(p.getLumber()<3){
            lumberBankGiveButton.setEnabled(false);
        }        
        if(p.getGold()<2 || p.getGoldTrade()>1){
            goldBankGiveButton.setEnabled(false);
        }
        
        if(p.getOre()<1){
            orePlayerGiveButton.setEnabled(false);
        }
        if(p.getCattle()<1){
            cattlePlayerGiveButton.setEnabled(false);
        }
        if(p.getCoal()<1){
            coalPlayerGiveButton.setEnabled(false);
        }
        if(p.getWheat()<1){
            wheatPlayerGiveButton.setEnabled(false);
        }
        if(p.getLumber()<1){
            lumberPlayerGiveButton1.setEnabled(false);
        }        
        if(p.getGold()<1){
            goldPlayerGiveButton.setEnabled(false);
        }
    }
    TradeWindow(){
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
    public void increase(JLabel l){
        String s = l.getText();
        int i = Integer.parseInt(s);
        s=String.valueOf(++i);
        l.setText(s);
    }
    public void decrease(JLabel l){
        String s = l.getText();
        int i = Integer.parseInt(s);
        s=String.valueOf(--i);
        l.setText(s);
    }
    public void resetGiveLabels(){
        cattleLabelPlayerGiveVar.setText("0");
        coalLabelPlayerGiveVar.setText("0");
        lumberLabelPlayerGiveVar.setText("0");
        wheatLabelPlayerGiveVar.setText("0");
        oreLabelPlayerGiveVar.setText("0");
        goldLabelPlayerGiveVar.setText("0");
    }
    public void resetGetLabels(){        
        cattleLabelPlayerGetVar.setText("0");
        coalLabelPlayerGetVar.setText("0");
        lumberLabelPlayerGetVar.setText("0");
        wheatLabelPlayerGetVar.setText("0");
        oreLabelPlayerGetVar.setText("0");
        goldLabelPlayerGetVar.setText("0");
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        mainQLabel = new javax.swing.JLabel();
        bankTradeTButton = new javax.swing.JToggleButton();
        playerTradeTButton = new javax.swing.JToggleButton();
        playerTradePanel = new javax.swing.JPanel();
        tradeWithPlayerLabel = new javax.swing.JLabel();
        tradePlayerGivePanel = new javax.swing.JPanel();
        tradePlayerGiveLabel = new javax.swing.JLabel();
        lumberPlayerGiveButton1 = new javax.swing.JButton();
        goldPlayerGiveButton1 = new javax.swing.JButton();
        coalPlayerGiveButton1 = new javax.swing.JButton();
        cattlePlayerGiveButton1 = new javax.swing.JButton();
        orePlayerGiveButton1 = new javax.swing.JButton();
        wheatPlayerGiveButton1 = new javax.swing.JButton();
        tradePlayerGetPanel = new javax.swing.JPanel();
        tradePlayerGetLabel = new javax.swing.JLabel();
        lumberPlayerGetButton1 = new javax.swing.JButton();
        coalPlayerGetButton1 = new javax.swing.JButton();
        cattlePlayerGetButton1 = new javax.swing.JButton();
        orePlayerGetButton1 = new javax.swing.JButton();
        wheatPlayerGetButton1 = new javax.swing.JButton();
        goldPlayerGetButton1 = new javax.swing.JButton();
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
        jLabel20 = new javax.swing.JLabel();
        clearGetButton1 = new javax.swing.JButton();
        clearGiveButton1 = new javax.swing.JButton();
        bankTradePanel = new javax.swing.JPanel();
        tradeWithBankLabel = new javax.swing.JLabel();
        tradeWithBankLabel1 = new javax.swing.JLabel();
        tradeBankGivePanel = new javax.swing.JPanel();
        tradeBankGiveLabel = new javax.swing.JLabel();
        lumberBankGiveButton = new javax.swing.JToggleButton();
        goldBankGiveButton = new javax.swing.JButton();
        coalBankGiveButton = new javax.swing.JButton();
        cattleBankGiveButton = new javax.swing.JButton();
        oreBankGiveButton = new javax.swing.JButton();
        wheatBankGiveButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tradeBankGetPanel = new javax.swing.JPanel();
        tradeBankGetLabel = new javax.swing.JLabel();
        lumberBankGetButton = new javax.swing.JToggleButton();
        coalBankGetButton = new javax.swing.JButton();
        cattleBankGetButton = new javax.swing.JButton();
        oreBankGetButton = new javax.swing.JButton();
        wheatBankGetButton = new javax.swing.JButton();
        goldBankGetButton = new javax.swing.JButton();
        tradeGiveBankDisplayPanel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        tradeGetBankDisplayPanel = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tradeButton1 = new javax.swing.JButton();
        xButton1 = new javax.swing.JButton();
        backgroundLabel = new javax.swing.JLabel();

        setAlwaysOnTop(true);
        setFocusable(false);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        mainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        mainQLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        mainQLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mainQLabel.setText("Who would you like to trade with?");
        mainPanel.add(mainQLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 360, -1));

        bankTradeTButton.setBackground(new java.awt.Color(239, 228, 176));
        bankTradeTButton.setFont(new java.awt.Font("Tahoma", 1, 11));
        bankTradeTButton.setSelected(true);
        bankTradeTButton.setText("Bank");
        bankTradeTButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        bankTradeTButton.setContentAreaFilled(false);
        bankTradeTButton.setFocusable(false);
        bankTradeTButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bankTradeTButtonMousePressed(evt);
            }
        });
        mainPanel.add(bankTradeTButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 130, 30));

        playerTradeTButton.setBackground(new java.awt.Color(239, 228, 176));
        playerTradeTButton.setFont(new java.awt.Font("Tahoma", 1, 11));
        playerTradeTButton.setText("Players");
        playerTradeTButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        playerTradeTButton.setContentAreaFilled(false);
        playerTradeTButton.setFocusable(false);
        playerTradeTButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                playerTradeTButtonMousePressed(evt);
            }
        });
        mainPanel.add(playerTradeTButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 50, 130, 30));

        playerTradePanel.setBackground(new java.awt.Color(239, 228, 176));
        playerTradePanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        playerTradePanel.setFocusable(false);
        playerTradePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tradeWithPlayerLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        tradeWithPlayerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tradeWithPlayerLabel.setText("Trade with other players");
        playerTradePanel.add(tradeWithPlayerLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 300, -1));

        tradePlayerGivePanel.setBackground(new java.awt.Color(239, 228, 176));
        tradePlayerGivePanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tradePlayerGivePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tradePlayerGiveLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        tradePlayerGiveLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tradePlayerGiveLabel.setText("What would you like to trade?");
        tradePlayerGivePanel.add(tradePlayerGiveLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 280, -1));

        lumberPlayerGiveButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/lumberRaw.png"))); // NOI18N
        lumberPlayerGiveButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lumberPlayerGiveButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lumberPlayerGiveButton1MouseReleased(evt);
            }
        });
        tradePlayerGivePanel.add(lumberPlayerGiveButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 40, 40));

        goldPlayerGiveButton1.setBackground(new java.awt.Color(239, 228, 176));
        goldPlayerGiveButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        goldPlayerGiveButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/gold.png"))); // NOI18N
        goldPlayerGiveButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        goldPlayerGiveButton1.setContentAreaFilled(false);
        goldPlayerGiveButton1.setFocusable(false);
        goldPlayerGiveButton1.setOpaque(true);
        goldPlayerGiveButton1.setPreferredSize(new java.awt.Dimension(75, 25));
        goldPlayerGiveButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                goldPlayerGiveButton1MouseReleased(evt);
            }
        });
        tradePlayerGivePanel.add(goldPlayerGiveButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(251, 40, 40, 40));

        coalPlayerGiveButton1.setBackground(new java.awt.Color(239, 228, 176));
        coalPlayerGiveButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        coalPlayerGiveButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/coalRaw.png"))); // NOI18N
        coalPlayerGiveButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        coalPlayerGiveButton1.setContentAreaFilled(false);
        coalPlayerGiveButton1.setFocusable(false);
        coalPlayerGiveButton1.setOpaque(true);
        coalPlayerGiveButton1.setPreferredSize(new java.awt.Dimension(75, 25));
        coalPlayerGiveButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                coalPlayerGiveButton1MouseReleased(evt);
            }
        });
        tradePlayerGivePanel.add(coalPlayerGiveButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(57, 40, 40, 40));

        cattlePlayerGiveButton1.setBackground(new java.awt.Color(239, 228, 176));
        cattlePlayerGiveButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        cattlePlayerGiveButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/cattleRaw.png"))); // NOI18N
        cattlePlayerGiveButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cattlePlayerGiveButton1.setContentAreaFilled(false);
        cattlePlayerGiveButton1.setFocusable(false);
        cattlePlayerGiveButton1.setOpaque(true);
        cattlePlayerGiveButton1.setPreferredSize(new java.awt.Dimension(75, 25));
        cattlePlayerGiveButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cattlePlayerGiveButton1MouseReleased(evt);
            }
        });
        tradePlayerGivePanel.add(cattlePlayerGiveButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 40, 40, 40));

        orePlayerGiveButton1.setBackground(new java.awt.Color(239, 228, 176));
        orePlayerGiveButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        orePlayerGiveButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/oreRaw.png"))); // NOI18N
        orePlayerGiveButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        orePlayerGiveButton1.setContentAreaFilled(false);
        orePlayerGiveButton1.setFocusable(false);
        orePlayerGiveButton1.setOpaque(true);
        orePlayerGiveButton1.setPreferredSize(new java.awt.Dimension(75, 25));
        orePlayerGiveButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                orePlayerGiveButton1MouseReleased(evt);
            }
        });
        tradePlayerGivePanel.add(orePlayerGiveButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(154, 40, 40, 40));

        wheatPlayerGiveButton1.setBackground(new java.awt.Color(239, 228, 176));
        wheatPlayerGiveButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        wheatPlayerGiveButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/wheatRaw.png"))); // NOI18N
        wheatPlayerGiveButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        wheatPlayerGiveButton1.setContentAreaFilled(false);
        wheatPlayerGiveButton1.setFocusable(false);
        wheatPlayerGiveButton1.setOpaque(true);
        wheatPlayerGiveButton1.setPreferredSize(new java.awt.Dimension(75, 25));
        wheatPlayerGiveButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                wheatPlayerGiveButton1MouseReleased(evt);
            }
        });
        tradePlayerGivePanel.add(wheatPlayerGiveButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(202, 40, 40, 40));

        playerTradePanel.add(tradePlayerGivePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 35, 300, 90));

        tradePlayerGetPanel.setBackground(new java.awt.Color(239, 228, 176));
        tradePlayerGetPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tradePlayerGetPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tradePlayerGetLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        tradePlayerGetLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tradePlayerGetLabel.setText("What would you like to receive?");
        tradePlayerGetPanel.add(tradePlayerGetLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 280, -1));

        lumberPlayerGetButton1.setBackground(new java.awt.Color(239, 228, 176));
        lumberPlayerGetButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        lumberPlayerGetButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/lumberRaw.png"))); // NOI18N
        lumberPlayerGetButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lumberPlayerGetButton1.setContentAreaFilled(false);
        lumberPlayerGetButton1.setFocusable(false);
        lumberPlayerGetButton1.setOpaque(true);
        lumberPlayerGetButton1.setPreferredSize(new java.awt.Dimension(75, 25));
        lumberPlayerGetButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lumberPlayerGetButton1MouseReleased(evt);
            }
        });
        tradePlayerGetPanel.add(lumberPlayerGetButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 40, 40));

        coalPlayerGetButton1.setBackground(new java.awt.Color(239, 228, 176));
        coalPlayerGetButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        coalPlayerGetButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/coalRaw.png"))); // NOI18N
        coalPlayerGetButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        coalPlayerGetButton1.setContentAreaFilled(false);
        coalPlayerGetButton1.setFocusable(false);
        coalPlayerGetButton1.setOpaque(true);
        coalPlayerGetButton1.setPreferredSize(new java.awt.Dimension(75, 25));
        coalPlayerGetButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                coalPlayerGetButton1MouseReleased(evt);
            }
        });
        tradePlayerGetPanel.add(coalPlayerGetButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(57, 40, 40, 40));

        cattlePlayerGetButton1.setBackground(new java.awt.Color(239, 228, 176));
        cattlePlayerGetButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        cattlePlayerGetButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/cattleRaw.png"))); // NOI18N
        cattlePlayerGetButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cattlePlayerGetButton1.setContentAreaFilled(false);
        cattlePlayerGetButton1.setFocusable(false);
        cattlePlayerGetButton1.setOpaque(true);
        cattlePlayerGetButton1.setPreferredSize(new java.awt.Dimension(75, 25));
        cattlePlayerGetButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cattlePlayerGetButton1MouseReleased(evt);
            }
        });
        tradePlayerGetPanel.add(cattlePlayerGetButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 40, 40, 40));

        orePlayerGetButton1.setBackground(new java.awt.Color(239, 228, 176));
        orePlayerGetButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        orePlayerGetButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/oreRaw.png"))); // NOI18N
        orePlayerGetButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        orePlayerGetButton1.setContentAreaFilled(false);
        orePlayerGetButton1.setFocusable(false);
        orePlayerGetButton1.setOpaque(true);
        orePlayerGetButton1.setPreferredSize(new java.awt.Dimension(75, 25));
        orePlayerGetButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                orePlayerGetButton1MouseReleased(evt);
            }
        });
        tradePlayerGetPanel.add(orePlayerGetButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(154, 40, 40, 40));

        wheatPlayerGetButton1.setBackground(new java.awt.Color(239, 228, 176));
        wheatPlayerGetButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        wheatPlayerGetButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/wheatRaw.png"))); // NOI18N
        wheatPlayerGetButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        wheatPlayerGetButton1.setContentAreaFilled(false);
        wheatPlayerGetButton1.setFocusable(false);
        wheatPlayerGetButton1.setOpaque(true);
        wheatPlayerGetButton1.setPreferredSize(new java.awt.Dimension(75, 25));
        wheatPlayerGetButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                wheatPlayerGetButton1MouseReleased(evt);
            }
        });
        tradePlayerGetPanel.add(wheatPlayerGetButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(202, 40, 40, 40));

        goldPlayerGetButton1.setBackground(new java.awt.Color(239, 228, 176));
        goldPlayerGetButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        goldPlayerGetButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/gold.png"))); // NOI18N
        goldPlayerGetButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        goldPlayerGetButton1.setContentAreaFilled(false);
        goldPlayerGetButton1.setFocusable(false);
        goldPlayerGetButton1.setOpaque(true);
        goldPlayerGetButton1.setPreferredSize(new java.awt.Dimension(75, 25));
        goldPlayerGetButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                goldPlayerGetButton1MouseReleased(evt);
            }
        });
        tradePlayerGetPanel.add(goldPlayerGetButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(251, 40, 40, 40));

        playerTradePanel.add(tradePlayerGetPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 300, 90));

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

        playerTradePanel.add(tradeGivePlayerDisplayPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 225, 105, 90));

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

        playerTradePanel.add(tradeGetPlayerDisplayPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(205, 225, 105, 90));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/tradeForArrow.png"))); // NOI18N
        playerTradePanel.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 250, -1, -1));

        clearGetButton1.setBackground(new java.awt.Color(239, 228, 176));
        clearGetButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        clearGetButton1.setText("Clear");
        clearGetButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        clearGetButton1.setContentAreaFilled(false);
        clearGetButton1.setFocusable(false);
        clearGetButton1.setOpaque(true);
        clearGetButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                clearGetButton1MouseReleased(evt);
            }
        });
        playerTradePanel.add(clearGetButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(205, 319, 105, 25));

        clearGiveButton1.setBackground(new java.awt.Color(239, 228, 176));
        clearGiveButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        clearGiveButton1.setText("Clear");
        clearGiveButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        clearGiveButton1.setContentAreaFilled(false);
        clearGiveButton1.setFocusable(false);
        clearGiveButton1.setOpaque(true);
        clearGiveButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                clearGiveButton1MouseReleased(evt);
            }
        });
        playerTradePanel.add(clearGiveButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 319, 105, 25));

        mainPanel.add(playerTradePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 320, 350));

        bankTradePanel.setBackground(new java.awt.Color(239, 228, 176));
        bankTradePanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        bankTradePanel.setFocusable(false);
        bankTradePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tradeWithBankLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        tradeWithBankLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tradeWithBankLabel.setText("Trade with the bank");
        bankTradePanel.add(tradeWithBankLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 5, 300, -1));

        tradeWithBankLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        tradeWithBankLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tradeWithBankLabel1.setText("(You can only trade gold twice per turn)");
        bankTradePanel.add(tradeWithBankLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 300, -1));

        tradeBankGivePanel.setBackground(new java.awt.Color(239, 228, 176));
        tradeBankGivePanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tradeBankGivePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tradeBankGiveLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        tradeBankGiveLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tradeBankGiveLabel.setText("What would you like to trade?");
        tradeBankGivePanel.add(tradeBankGiveLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 280, -1));

        lumberBankGiveButton.setBackground(new java.awt.Color(239, 228, 176));
        lumberBankGiveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/lumberRaw.png"))); // NOI18N
        lumberBankGiveButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lumberBankGiveButton.setFocusPainted(false);
        lumberBankGiveButton.setFocusable(false);
        lumberBankGiveButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lumberBankGiveButtonMousePressed(evt);
            }
        });
        tradeBankGivePanel.add(lumberBankGiveButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 60, 40, 40));

        goldBankGiveButton.setBackground(new java.awt.Color(239, 228, 176));
        goldBankGiveButton.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        goldBankGiveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/gold.png"))); // NOI18N
        goldBankGiveButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        goldBankGiveButton.setContentAreaFilled(false);
        goldBankGiveButton.setFocusable(false);
        goldBankGiveButton.setOpaque(true);
        goldBankGiveButton.setPreferredSize(new java.awt.Dimension(75, 25));
        goldBankGiveButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                goldBankGiveButtonMouseReleased(evt);
            }
        });
        tradeBankGivePanel.add(goldBankGiveButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(251, 60, 40, 40));

        coalBankGiveButton.setBackground(new java.awt.Color(239, 228, 176));
        coalBankGiveButton.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        coalBankGiveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/coalRaw.png"))); // NOI18N
        coalBankGiveButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        coalBankGiveButton.setContentAreaFilled(false);
        coalBankGiveButton.setFocusable(false);
        coalBankGiveButton.setOpaque(true);
        coalBankGiveButton.setPreferredSize(new java.awt.Dimension(75, 25));
        coalBankGiveButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                coalBankGiveButtonMouseReleased(evt);
            }
        });
        tradeBankGivePanel.add(coalBankGiveButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(57, 60, 40, 40));

        cattleBankGiveButton.setBackground(new java.awt.Color(239, 228, 176));
        cattleBankGiveButton.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        cattleBankGiveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/cattleRaw.png"))); // NOI18N
        cattleBankGiveButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cattleBankGiveButton.setContentAreaFilled(false);
        cattleBankGiveButton.setFocusable(false);
        cattleBankGiveButton.setOpaque(true);
        cattleBankGiveButton.setPreferredSize(new java.awt.Dimension(75, 25));
        cattleBankGiveButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cattleBankGiveButtonMouseReleased(evt);
            }
        });
        tradeBankGivePanel.add(cattleBankGiveButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 60, 40, 40));

        oreBankGiveButton.setBackground(new java.awt.Color(239, 228, 176));
        oreBankGiveButton.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        oreBankGiveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/oreRaw.png"))); // NOI18N
        oreBankGiveButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        oreBankGiveButton.setContentAreaFilled(false);
        oreBankGiveButton.setFocusable(false);
        oreBankGiveButton.setOpaque(true);
        oreBankGiveButton.setPreferredSize(new java.awt.Dimension(75, 25));
        oreBankGiveButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                oreBankGiveButtonMouseReleased(evt);
            }
        });
        tradeBankGivePanel.add(oreBankGiveButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(154, 60, 40, 40));

        wheatBankGiveButton.setBackground(new java.awt.Color(239, 228, 176));
        wheatBankGiveButton.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        wheatBankGiveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/wheatRaw.png"))); // NOI18N
        wheatBankGiveButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        wheatBankGiveButton.setContentAreaFilled(false);
        wheatBankGiveButton.setFocusable(false);
        wheatBankGiveButton.setOpaque(true);
        wheatBankGiveButton.setPreferredSize(new java.awt.Dimension(75, 25));
        wheatBankGiveButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                wheatBankGiveButtonMouseReleased(evt);
            }
        });
        tradeBankGivePanel.add(wheatBankGiveButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(202, 60, 40, 40));

        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        jLabel1.setText("3:1");
        tradeBankGivePanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 40, -1, -1));

        jLabel2.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        jLabel2.setText("3:1");
        tradeBankGivePanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(66, 40, -1, -1));

        jLabel3.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        jLabel3.setText("3:1");
        tradeBankGivePanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 40, -1, -1));

        jLabel4.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        jLabel4.setText("3:1");
        tradeBankGivePanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(163, 40, -1, -1));

        jLabel5.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        jLabel5.setText("3:1");
        tradeBankGivePanel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(211, 40, -1, -1));

        jLabel6.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        jLabel6.setText("2:1");
        tradeBankGivePanel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(259, 40, -1, -1));

        bankTradePanel.add(tradeBankGivePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 35, 300, 110));

        tradeBankGetPanel.setBackground(new java.awt.Color(239, 228, 176));
        tradeBankGetPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tradeBankGetPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tradeBankGetLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        tradeBankGetLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tradeBankGetLabel.setText("What would you like to receive?");
        tradeBankGetPanel.add(tradeBankGetLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 280, -1));

        lumberBankGetButton.setBackground(new java.awt.Color(239, 228, 176));
        lumberBankGetButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/lumberRaw.png"))); // NOI18N
        lumberBankGetButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lumberBankGetButton.setFocusPainted(false);
        lumberBankGetButton.setFocusable(false);
        lumberBankGetButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lumberBankGetButtonMousePressed(evt);
            }
        });
        tradeBankGetPanel.add(lumberBankGetButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 40, 40, 40));

        coalBankGetButton.setBackground(new java.awt.Color(239, 228, 176));
        coalBankGetButton.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        coalBankGetButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/coalRaw.png"))); // NOI18N
        coalBankGetButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        coalBankGetButton.setContentAreaFilled(false);
        coalBankGetButton.setFocusable(false);
        coalBankGetButton.setOpaque(true);
        coalBankGetButton.setPreferredSize(new java.awt.Dimension(75, 25));
        coalBankGetButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                coalBankGetButtonMouseReleased(evt);
            }
        });
        tradeBankGetPanel.add(coalBankGetButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(57, 40, 40, 40));

        cattleBankGetButton.setBackground(new java.awt.Color(239, 228, 176));
        cattleBankGetButton.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        cattleBankGetButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/cattleRaw.png"))); // NOI18N
        cattleBankGetButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cattleBankGetButton.setContentAreaFilled(false);
        cattleBankGetButton.setFocusable(false);
        cattleBankGetButton.setOpaque(true);
        cattleBankGetButton.setPreferredSize(new java.awt.Dimension(75, 25));
        cattleBankGetButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cattleBankGetButtonMouseReleased(evt);
            }
        });
        tradeBankGetPanel.add(cattleBankGetButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 40, 40, 40));

        oreBankGetButton.setBackground(new java.awt.Color(239, 228, 176));
        oreBankGetButton.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        oreBankGetButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/oreRaw.png"))); // NOI18N
        oreBankGetButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        oreBankGetButton.setContentAreaFilled(false);
        oreBankGetButton.setFocusable(false);
        oreBankGetButton.setOpaque(true);
        oreBankGetButton.setPreferredSize(new java.awt.Dimension(75, 25));
        oreBankGetButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                oreBankGetButtonMouseReleased(evt);
            }
        });
        tradeBankGetPanel.add(oreBankGetButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(154, 40, 40, 40));

        wheatBankGetButton.setBackground(new java.awt.Color(239, 228, 176));
        wheatBankGetButton.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        wheatBankGetButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/wheatRaw.png"))); // NOI18N
        wheatBankGetButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        wheatBankGetButton.setContentAreaFilled(false);
        wheatBankGetButton.setFocusable(false);
        wheatBankGetButton.setOpaque(true);
        wheatBankGetButton.setPreferredSize(new java.awt.Dimension(75, 25));
        wheatBankGetButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                wheatBankGetButtonMouseReleased(evt);
            }
        });
        tradeBankGetPanel.add(wheatBankGetButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(202, 40, 40, 40));

        goldBankGetButton.setBackground(new java.awt.Color(239, 228, 176));
        goldBankGetButton.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        goldBankGetButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/gold.png"))); // NOI18N
        goldBankGetButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        goldBankGetButton.setContentAreaFilled(false);
        goldBankGetButton.setFocusable(false);
        goldBankGetButton.setOpaque(true);
        goldBankGetButton.setPreferredSize(new java.awt.Dimension(75, 25));
        goldBankGetButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                goldBankGetButtonMouseReleased(evt);
            }
        });
        tradeBankGetPanel.add(goldBankGetButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(251, 40, 40, 40));

        bankTradePanel.add(tradeBankGetPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 300, 90));

        tradeGiveBankDisplayPanel.setBackground(new java.awt.Color(239, 228, 176));
        tradeGiveBankDisplayPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tradeGiveBankDisplayPanel.setFocusable(false);
        tradeGiveBankDisplayPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/wheatRaw.png"))); // NOI18N
        tradeGiveBankDisplayPanel.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 23, -1, -1));

        jLabel9.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 24));
        jLabel9.setText("3");
        tradeGiveBankDisplayPanel.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 28, -1, -1));

        bankTradePanel.add(tradeGiveBankDisplayPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 105, 90));

        tradeGetBankDisplayPanel.setBackground(new java.awt.Color(239, 228, 176));
        tradeGetBankDisplayPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tradeGetBankDisplayPanel.setFocusable(false);
        tradeGetBankDisplayPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/wheatRaw.png"))); // NOI18N
        tradeGetBankDisplayPanel.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 23, -1, -1));

        bankTradePanel.add(tradeGetBankDisplayPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(205, 250, 105, 90));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/tradeForArrow.png"))); // NOI18N
        bankTradePanel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 270, -1, -1));

        mainPanel.add(bankTradePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 320, 350));

        tradeButton1.setBackground(new java.awt.Color(239, 228, 176));
        tradeButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 24)); // NOI18N
        tradeButton1.setText("Trade");
        tradeButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tradeButton1.setContentAreaFilled(false);
        tradeButton1.setFocusable(false);
        tradeButton1.setOpaque(true);
        tradeButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tradeButton1MouseReleased(evt);
            }
        });
        mainPanel.add(tradeButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 460, 320, 40));

        xButton1.setBackground(new java.awt.Color(239, 228, 176));
        xButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        xButton1.setText("X");
        xButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        xButton1.setContentAreaFilled(false);
        xButton1.setFocusable(false);
        xButton1.setMargin(new java.awt.Insets(0, 14, 0, 14));
        xButton1.setOpaque(true);
        xButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                xButton1MouseReleased(evt);
            }
        });
        mainPanel.add(xButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(335, 15, 30, 30));

        backgroundLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/MainMenuBackground.png"))); // NOI18N
        mainPanel.add(backgroundLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(mainPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void bankTradeTButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bankTradeTButtonMousePressed
        if (!tradeWithBank) {
            gameFrame.playSound("click");
            if (!bankTradePanel.isVisible()&&playerTradePanel.isVisible()) {
                bankTradePanel.setVisible(true);
                playerTradePanel.setVisible(false);
                bankTradeTButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
                bankTradeTButton.setLocation(bankTradeTButton.getX() - 1, bankTradeTButton.getY());
                playerTradeTButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                playerTradeTButton.setLocation(playerTradeTButton.getX() + 1, playerTradeTButton.getY());
                lumberBankGiveButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                lumberBankGiveButton.setLocation(lumberBankGiveButton.getX() + 1, lumberBankGiveButton.getY());
                coalBankGiveButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                coalBankGiveButton.setLocation(coalBankGiveButton.getX() + 1, coalBankGiveButton.getY());
                wheatBankGiveButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                wheatBankGiveButton.setLocation(wheatBankGiveButton.getX() + 1, wheatBankGiveButton.getY());
                cattleBankGiveButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                cattleBankGiveButton.setLocation(cattleBankGiveButton.getX() + 1, cattleBankGiveButton.getY());
                oreBankGiveButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                oreBankGiveButton.setLocation(oreBankGiveButton.getX() + 1, oreBankGiveButton.getY());
                goldBankGiveButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                goldBankGiveButton.setLocation(goldBankGiveButton.getX() + 1, goldBankGiveButton.getY());
                lumberBankGetButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                lumberBankGetButton.setLocation(lumberBankGetButton.getX() + 1, lumberBankGetButton.getY());
                coalBankGetButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                coalBankGetButton.setLocation(coalBankGetButton.getX() + 1, coalBankGetButton.getY());
                wheatBankGetButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                wheatBankGetButton.setLocation(wheatBankGetButton.getX() + 1, wheatBankGetButton.getY());
                cattleBankGetButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                cattleBankGetButton.setLocation(cattleBankGetButton.getX() + 1, cattleBankGetButton.getY());
                oreBankGetButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                oreBankGetButton.setLocation(oreBankGetButton.getX() + 1, oreBankGetButton.getY());
                goldBankGetButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                goldBankGetButton.setLocation(goldBankGetButton.getX() + 1, goldBankGetButton.getY());
                tradeWithBank = true;
                receiveLumber = 0;
                receiveCoal = 0;
                receiveCattle = 0;
                receiveWheat = 0;
                receiveOre = 0;
                receiveGold = 0;
                giveLumber = 0;
                giveCoal = 0;
                giveCattle = 0;
                giveWheat = 0;
                giveOre = 0;
                giveGold = 0;
                resetGiveLabels();
                resetGetLabels();
                jLabel8.setVisible(false);
                jLabel9.setVisible(false);
                jLabel10.setVisible(false);
                lumberBankGetPressed = false;
                coalBankGetPressed = false;
                oreBankGetPressed = false;
                wheatBankGetPressed = false;
                cattleBankGetPressed = false;
                goldBankGetPressed = false;
                lumberBankGivePressed = false;
                coalBankGivePressed = false;
                oreBankGivePressed = false;
                wheatBankGivePressed = false;
                cattleBankGivePressed = false;
                goldBankGivePressed = false;
                oreBankGiveButton.setEnabled(true);
                lumberBankGiveButton.setEnabled(true);
                wheatBankGiveButton.setEnabled(true);
                coalBankGiveButton.setEnabled(true);
                cattleBankGiveButton.setEnabled(true);
                goldBankGiveButton.setEnabled(true);
                oreBankGetButton.setEnabled(true);
                lumberBankGetButton.setEnabled(true);
                wheatBankGetButton.setEnabled(true);
                coalBankGetButton.setEnabled(true);
                cattleBankGetButton.setEnabled(true);
                goldBankGetButton.setEnabled(true);
                if (currPlayer.getOre() < 3) {
                    oreBankGiveButton.setEnabled(false);
                }
                if (currPlayer.getCattle() < 3) {
                    cattleBankGiveButton.setEnabled(false);
                }
                if (currPlayer.getCoal() < 3) {
                    coalBankGiveButton.setEnabled(false);
                }
                if (currPlayer.getWheat() < 3) {
                    wheatBankGiveButton.setEnabled(false);
                }
                if (currPlayer.getLumber() < 3) {
                    lumberBankGiveButton.setEnabled(false);
                }
                if (currPlayer.getGold() < 2 || currPlayer.getGoldTrade() > 1) {
                    goldBankGiveButton.setEnabled(false);
                }
            }
        }
}//GEN-LAST:event_bankTradeTButtonMousePressed
    private void playerTradeTButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playerTradeTButtonMousePressed
        if(tradeWithBank){
            gameFrame.playSound("click");
            if (bankTradePanel.isVisible() && !playerTradePanel.isVisible()) {
                bankTradePanel.setVisible(false);
                playerTradePanel.setVisible(true);
                playerTradeTButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
                playerTradeTButton.setLocation(playerTradeTButton.getX() - 1, playerTradeTButton.getY());
                bankTradeTButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                bankTradeTButton.setLocation(bankTradeTButton.getX() + 1, bankTradeTButton.getY());
                lumberPlayerGiveButton1.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                lumberPlayerGiveButton1.setLocation(lumberPlayerGiveButton1.getX() + 1, lumberPlayerGiveButton1.getY());
                coalPlayerGiveButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                coalPlayerGiveButton.setLocation(coalPlayerGiveButton.getX() + 1, coalPlayerGiveButton.getY());
                wheatPlayerGiveButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                wheatPlayerGiveButton.setLocation(wheatPlayerGiveButton.getX() + 1, wheatPlayerGiveButton.getY());
                cattlePlayerGiveButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                cattlePlayerGiveButton.setLocation(cattlePlayerGiveButton.getX() + 1, cattlePlayerGiveButton.getY());
                orePlayerGiveButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                orePlayerGiveButton.setLocation(orePlayerGiveButton.getX() + 1, orePlayerGiveButton.getY());
                goldPlayerGiveButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                goldPlayerGiveButton.setLocation(goldPlayerGiveButton.getX() + 1, goldPlayerGiveButton.getY());
                lumberPlayerGetButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                lumberPlayerGetButton.setLocation(lumberPlayerGetButton.getX() + 1, lumberPlayerGetButton.getY());
                coalPlayerGetButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                coalPlayerGetButton.setLocation(coalPlayerGetButton.getX() + 1, coalPlayerGetButton.getY());
                wheatPlayerGetButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                wheatPlayerGetButton.setLocation(wheatPlayerGetButton.getX() + 1, wheatPlayerGetButton.getY());
                cattlePlayerGetButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                cattlePlayerGetButton.setLocation(cattlePlayerGetButton.getX() + 1, cattlePlayerGetButton.getY());
                orePlayerGetButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                orePlayerGetButton.setLocation(orePlayerGetButton.getX() + 1, orePlayerGetButton.getY());
                goldPlayerGetButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                goldPlayerGetButton.setLocation(goldPlayerGetButton.getX() + 1, goldPlayerGetButton.getY());
                tradeWithBank = false;
                receiveLumber = 0;
                receiveCoal = 0;
                receiveCattle = 0;
                receiveWheat = 0;
                receiveOre = 0;
                receiveGold = 0;
                giveLumber = 0;
                giveCoal = 0;
                giveCattle = 0;
                giveWheat = 0;
                giveOre = 0;
                giveGold = 0;
                orePlayerGiveButton.setEnabled(true);
                lumberPlayerGiveButton.setEnabled(true);
                wheatPlayerGiveButton.setEnabled(true);
                coalPlayerGiveButton.setEnabled(true);
                cattlePlayerGiveButton.setEnabled(true);
                goldPlayerGiveButton.setEnabled(true);
                orePlayerGetButton.setEnabled(true);
                lumberPlayerGetButton.setEnabled(true);
                wheatPlayerGetButton.setEnabled(true);
                coalPlayerGetButton.setEnabled(true);
                cattlePlayerGetButton.setEnabled(true);
                goldPlayerGetButton.setEnabled(true);
                if (currPlayer.getOre() < 1) {
                    orePlayerGiveButton.setEnabled(false);
                }
                if (currPlayer.getCattle() < 1) {
                    cattlePlayerGiveButton.setEnabled(false);
                }
                if (currPlayer.getCoal() < 1) {
                    coalPlayerGiveButton.setEnabled(false);
                }
                if (currPlayer.getWheat() < 1) {
                    wheatPlayerGiveButton.setEnabled(false);
                }
                if (currPlayer.getLumber() < 1) {
                    lumberPlayerGiveButton.setEnabled(false);
                }
                if (currPlayer.getGold() < 1) {
                    goldPlayerGiveButton.setEnabled(false);
                }
            }
        }
}//GEN-LAST:event_playerTradeTButtonMousePressed
    private void coalBankGiveButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_coalBankGiveButtonMouseReleased
        if(!coalBankGivePressed&&coalBankGiveButton.isEnabled()){
            gameFrame.playSound("click");
            coalBankGetButton.setEnabled(false);
            goldBankGetButton.setEnabled(true);
            lumberBankGetButton.setEnabled(true);
            oreBankGetButton.setEnabled(true);
            wheatBankGetButton.setEnabled(true);
            cattleBankGetButton.setEnabled(true);
            coalBankGivePressed=true;
            giveLumber=0;
            giveCattle=0;
            giveWheat=0;
            giveOre=0;
            giveGold=0;
            giveCoal=3;
            jLabel8.setIcon(coal);
            jLabel8.setVisible(true);
            jLabel9.setText("3");
            jLabel9.setVisible(true);
            coalBankGiveButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            coalBankGiveButton.setLocation(coalBankGiveButton.getX() - 1, coalBankGiveButton.getY());  
            if(goldBankGivePressed){
                goldBankGiveButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                goldBankGiveButton.setLocation(goldBankGiveButton.getX() + 1, goldBankGiveButton.getY()); 
                goldBankGivePressed=false;               
            }
            if(oreBankGivePressed){
                oreBankGiveButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                oreBankGiveButton.setLocation(oreBankGiveButton.getX() + 1, oreBankGiveButton.getY());        
                oreBankGivePressed=false;                                     
            }
            if(wheatBankGivePressed){
                wheatBankGiveButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                wheatBankGiveButton.setLocation(wheatBankGiveButton.getX() + 1, wheatBankGiveButton.getY());              
                wheatBankGivePressed=false;                                                              
            }
            if(cattleBankGivePressed){
                cattleBankGiveButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                cattleBankGiveButton.setLocation(cattleBankGiveButton.getX() + 1, cattleBankGiveButton.getY());    
                cattleBankGivePressed=false;                                         
            }
            if(lumberBankGivePressed){
                lumberBankGiveButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                lumberBankGiveButton.setLocation(lumberBankGiveButton.getX() + 1, lumberBankGiveButton.getY());               
                lumberBankGivePressed=false;                                                             
            }
        }
}//GEN-LAST:event_coalBankGiveButtonMouseReleased
    private void cattleBankGiveButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cattleBankGiveButtonMouseReleased
        if(!cattleBankGivePressed&&cattleBankGiveButton.isEnabled()){
            gameFrame.playSound("click");
            goldBankGetButton.setEnabled(true);
            coalBankGetButton.setEnabled(true);
            oreBankGetButton.setEnabled(true);
            wheatBankGetButton.setEnabled(true);
            lumberBankGetButton.setEnabled(true);
            cattleBankGetButton.setEnabled(false);
            cattleBankGivePressed=true;
            giveLumber=0;
            giveCoal=0;
            giveWheat=0;
            giveOre=0;
            giveGold=0;
            giveCattle=3;
            jLabel8.setIcon(cattle);
            jLabel8.setVisible(true);
            jLabel9.setText("3");
            jLabel9.setVisible(true);
            cattleBankGiveButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            cattleBankGiveButton.setLocation(cattleBankGiveButton.getX() - 1, cattleBankGiveButton.getY());  
            if(coalBankGivePressed){
                coalBankGiveButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                coalBankGiveButton.setLocation(coalBankGiveButton.getX() + 1, coalBankGiveButton.getY());          
                coalBankGivePressed=false;                                                                                
            }
            if(oreBankGivePressed){
                oreBankGiveButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                oreBankGiveButton.setLocation(oreBankGiveButton.getX() + 1, oreBankGiveButton.getY());           
                oreBankGivePressed=false;                                                  
            }
            if(wheatBankGivePressed){
                wheatBankGiveButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                wheatBankGiveButton.setLocation(wheatBankGiveButton.getX() + 1, wheatBankGiveButton.getY());            
                wheatBankGivePressed=false;                                                                
            }
            if(goldBankGivePressed){
                goldBankGiveButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                goldBankGiveButton.setLocation(goldBankGiveButton.getX() + 1, goldBankGiveButton.getY()); 
                goldBankGivePressed=false;                               
            }
            if(lumberBankGivePressed){
                lumberBankGiveButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                lumberBankGiveButton.setLocation(lumberBankGiveButton.getX() + 1, lumberBankGiveButton.getY());           
                lumberBankGivePressed=false;                                                                            
            }
        }
}//GEN-LAST:event_cattleBankGiveButtonMouseReleased
    private void oreBankGiveButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_oreBankGiveButtonMouseReleased
        if(!oreBankGivePressed&&oreBankGiveButton.isEnabled()){
            gameFrame.playSound("click");
            goldBankGetButton.setEnabled(true);
            coalBankGetButton.setEnabled(true);
            lumberBankGetButton.setEnabled(true);
            wheatBankGetButton.setEnabled(true);
            cattleBankGetButton.setEnabled(true);
            oreBankGetButton.setEnabled(false);
            oreBankGivePressed=true;
            giveLumber=0;
            giveCattle=0;
            giveCoal=0;
            giveWheat=0;
            giveGold=0;
            giveOre=3;
            jLabel8.setIcon(ore);
            jLabel8.setVisible(true);
            jLabel9.setText("3");
            jLabel9.setVisible(true);
            oreBankGiveButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            oreBankGiveButton.setLocation(oreBankGiveButton.getX() - 1, oreBankGiveButton.getY());  
            if(coalBankGivePressed){
                coalBankGiveButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                coalBankGiveButton.setLocation(coalBankGiveButton.getX() + 1, coalBankGiveButton.getY());          
                coalBankGivePressed=false;                                                                                
            }
            if(goldBankGivePressed){
                goldBankGiveButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                goldBankGiveButton.setLocation(goldBankGiveButton.getX() + 1, goldBankGiveButton.getY());  
                goldBankGivePressed=false;                              
            }
            if(wheatBankGivePressed){
                wheatBankGiveButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                wheatBankGiveButton.setLocation(wheatBankGiveButton.getX() + 1, wheatBankGiveButton.getY());            
                wheatBankGivePressed=false;                                                                
            }
            if(cattleBankGivePressed){
                cattleBankGiveButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                cattleBankGiveButton.setLocation(cattleBankGiveButton.getX() + 1, cattleBankGiveButton.getY());  
                cattleBankGivePressed=false;                                           
            }
            if(lumberBankGivePressed){
                lumberBankGiveButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                lumberBankGiveButton.setLocation(lumberBankGiveButton.getX() + 1, lumberBankGiveButton.getY());           
                lumberBankGivePressed=false;                                                                             
            }
        }
}//GEN-LAST:event_oreBankGiveButtonMouseReleased
    private void wheatBankGiveButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_wheatBankGiveButtonMouseReleased
        if(!wheatBankGivePressed&&wheatBankGiveButton.isEnabled()){
            gameFrame.playSound("click");
            goldBankGetButton.setEnabled(true);
            coalBankGetButton.setEnabled(true);
            oreBankGetButton.setEnabled(true);
            lumberBankGetButton.setEnabled(true);
            cattleBankGetButton.setEnabled(true);
            wheatBankGetButton.setEnabled(false);
            wheatBankGivePressed=true;
            giveLumber=0;
            giveCattle=0;
            giveCoal=0;
            giveOre=0;
            giveGold=0;
            giveWheat=3;
            jLabel8.setIcon(wheat);
            jLabel9.setText("3");
            jLabel8.setVisible(true);
            jLabel9.setVisible(true);
            wheatBankGiveButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            wheatBankGiveButton.setLocation(wheatBankGiveButton.getX() - 1, wheatBankGiveButton.getY());  
            if(coalBankGivePressed){
                coalBankGiveButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                coalBankGiveButton.setLocation(coalBankGiveButton.getX() + 1, coalBankGiveButton.getY());          
                coalBankGivePressed=false;                                                                                
            }
            if(oreBankGivePressed){
                oreBankGiveButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                oreBankGiveButton.setLocation(oreBankGiveButton.getX() + 1, oreBankGiveButton.getY());           
                oreBankGivePressed=false;                                                  
            }
            if(goldBankGivePressed){
                goldBankGiveButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                goldBankGiveButton.setLocation(goldBankGiveButton.getX() + 1, goldBankGiveButton.getY());  
                goldBankGivePressed=false;                              
            }
            if(cattleBankGivePressed){
                cattleBankGiveButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                cattleBankGiveButton.setLocation(cattleBankGiveButton.getX() + 1, cattleBankGiveButton.getY());  
                cattleBankGivePressed=false;                                           
            }
            if(lumberBankGivePressed){
                lumberBankGiveButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                lumberBankGiveButton.setLocation(lumberBankGiveButton.getX() + 1, lumberBankGiveButton.getY());           
                lumberBankGivePressed=false;                                                                             
            }
        }
}//GEN-LAST:event_wheatBankGiveButtonMouseReleased
    private void goldBankGiveButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_goldBankGiveButtonMouseReleased
        if(!goldBankGivePressed&&goldBankGiveButton.isEnabled()){
            gameFrame.playSound("click");
            goldBankGivePressed=true;
            lumberBankGetButton.setEnabled(true);
            coalBankGetButton.setEnabled(true);
            oreBankGetButton.setEnabled(true);
            wheatBankGetButton.setEnabled(true);
            cattleBankGetButton.setEnabled(true);
            goldBankGetButton.setEnabled(false);
            giveLumber=0;
            giveCattle=0;
            giveCoal=0;
            giveWheat=0;
            giveOre=0;
            giveGold=2;
            jLabel8.setIcon(gold);
            jLabel9.setText("2");
            jLabel8.setVisible(true);
            jLabel9.setVisible(true);
            goldBankGiveButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            goldBankGiveButton.setLocation(goldBankGiveButton.getX() - 1, goldBankGiveButton.getY());  
            if(coalBankGivePressed){
                coalBankGiveButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                coalBankGiveButton.setLocation(coalBankGiveButton.getX() + 1, coalBankGiveButton.getY());          
                coalBankGivePressed=false;                                                                                
            }
            if(oreBankGivePressed){
                oreBankGiveButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                oreBankGiveButton.setLocation(oreBankGiveButton.getX() + 1, oreBankGiveButton.getY());           
                oreBankGivePressed=false;                                                  
            }
            if(wheatBankGivePressed){
                wheatBankGiveButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                wheatBankGiveButton.setLocation(wheatBankGiveButton.getX() + 1, wheatBankGiveButton.getY());            
                wheatBankGivePressed=false;                                                                
            }
            if(cattleBankGivePressed){
                cattleBankGiveButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                cattleBankGiveButton.setLocation(cattleBankGiveButton.getX() + 1, cattleBankGiveButton.getY());  
                cattleBankGivePressed=false;                                           
            }
            if(lumberBankGivePressed){
                lumberBankGiveButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                lumberBankGiveButton.setLocation(lumberBankGiveButton.getX() + 1, lumberBankGiveButton.getY());           
                lumberBankGivePressed=false;                                                                             
            }
        }
    }//GEN-LAST:event_goldBankGiveButtonMouseReleased
    private void lumberBankGiveButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lumberBankGiveButtonMousePressed
        if(!lumberBankGivePressed&&lumberBankGiveButton.isEnabled()){
            gameFrame.playSound("click");
            lumberBankGivePressed=true;
            goldBankGetButton.setEnabled(true);
            coalBankGetButton.setEnabled(true);
            oreBankGetButton.setEnabled(true);
            wheatBankGetButton.setEnabled(true);
            cattleBankGetButton.setEnabled(true);
            lumberBankGetButton.setEnabled(false);
            giveCattle=0;
            giveCoal=0;
            giveWheat=0;
            giveOre=0;
            giveGold=0;
            giveLumber=3;
            jLabel8.setIcon(lumber);
            jLabel8.setVisible(true);
            jLabel9.setText("3");
            jLabel9.setVisible(true);
            lumberBankGiveButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            lumberBankGiveButton.setLocation(lumberBankGiveButton.getX() - 1, lumberBankGiveButton.getY());  
            if(coalBankGivePressed){
                coalBankGiveButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                coalBankGiveButton.setLocation(coalBankGiveButton.getX() + 1, coalBankGiveButton.getY());            
                coalBankGivePressed=false;                                                                            
            }            
            if(oreBankGivePressed){
                oreBankGiveButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                oreBankGiveButton.setLocation(oreBankGiveButton.getX() + 1, oreBankGiveButton.getY());            
                oreBankGivePressed=false;                                                 
            }            
            if(wheatBankGivePressed){
                wheatBankGiveButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                wheatBankGiveButton.setLocation(wheatBankGiveButton.getX() + 1, wheatBankGiveButton.getY());            
                wheatBankGivePressed=false;                                                 
            }
            if(cattleBankGivePressed){
                cattleBankGiveButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                cattleBankGiveButton.setLocation(cattleBankGiveButton.getX() + 1, cattleBankGiveButton.getY());   
                cattleBankGivePressed=false;                             
            }            
            if(goldBankGivePressed){
                goldBankGiveButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                goldBankGiveButton.setLocation(goldBankGiveButton.getX() + 1, goldBankGiveButton.getY());   
                goldBankGivePressed=false;                             
            }
        }
    }//GEN-LAST:event_lumberBankGiveButtonMousePressed

    private void lumberBankGetButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lumberBankGetButtonMousePressed
        if(!lumberBankGetPressed&&lumberBankGetButton.isEnabled()){
            gameFrame.playSound("click");
            lumberBankGetPressed=true;
            if(currPlayer.getLumber()>3){
                lumberBankGiveButton.setEnabled(false);
            }            
            if(currPlayer.getCoal()>3){
                coalBankGiveButton.setEnabled(true);
            }
            if(currPlayer.getOre()>3){
                oreBankGiveButton.setEnabled(true);
            }
            if(currPlayer.getWheat()>3){
                wheatBankGiveButton.setEnabled(true);
            }
            if(currPlayer.getCattle()>3){
                cattleBankGiveButton.setEnabled(true);
            }
            if(currPlayer.getGold()>2 && currPlayer.getGoldTrade()<2){
                goldBankGiveButton.setEnabled(true);
            }
            receiveCattle=0;
            receiveCoal=0;
            receiveWheat=0;
            receiveOre=0;
            receiveGold=0;
            receiveLumber=1;
            jLabel10.setIcon(lumber);
            jLabel10.setVisible(true);
            lumberBankGetButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            lumberBankGetButton.setLocation(lumberBankGetButton.getX() - 1, lumberBankGetButton.getY());  
            if(coalBankGetPressed){
                coalBankGetButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                coalBankGetButton.setLocation(coalBankGetButton.getX() + 1, coalBankGetButton.getY());           
                coalBankGetPressed=false;                                                                               
            }            
            if(oreBankGetPressed){
                oreBankGetButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                oreBankGetButton.setLocation(oreBankGetButton.getX() + 1, oreBankGetButton.getY());               
                oreBankGetPressed=false;                                                                         
            }            
            if(wheatBankGetPressed){
                wheatBankGetButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                wheatBankGetButton.setLocation(wheatBankGetButton.getX() + 1, wheatBankGetButton.getY());           
                wheatBankGetPressed=false;                                                                             
            }
            if(cattleBankGetPressed){
                cattleBankGetButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                cattleBankGetButton.setLocation(cattleBankGetButton.getX() + 1, cattleBankGetButton.getY());           
                cattleBankGetPressed=false;                                                                             
            }            
            if(goldBankGetPressed){
                goldBankGetButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                goldBankGetButton.setLocation(goldBankGetButton.getX() + 1, goldBankGetButton.getY());             
                goldBankGetPressed=false;                                                                           
            }
        }
    }//GEN-LAST:event_lumberBankGetButtonMousePressed
    private void coalBankGetButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_coalBankGetButtonMouseReleased
        if(!coalBankGetPressed&&coalBankGetButton.isEnabled()){
            gameFrame.playSound("click");
            coalBankGetPressed=true;
            if(currPlayer.getLumber()>3){
                lumberBankGiveButton.setEnabled(true);
            }            
            if(currPlayer.getCoal()>3){
                coalBankGiveButton.setEnabled(false);
            }
            if(currPlayer.getOre()>3){
                oreBankGiveButton.setEnabled(true);
            }
            if(currPlayer.getWheat()>3){
                wheatBankGiveButton.setEnabled(true);
            }
            if(currPlayer.getCattle()>3){
                cattleBankGiveButton.setEnabled(true);
            }
            if(currPlayer.getGold()>2 && currPlayer.getGoldTrade()<2){
                goldBankGiveButton.setEnabled(true);
            }
            receiveLumber=0;
            receiveCattle=0;
            receiveWheat=0;
            receiveOre=0;
            receiveGold=0;
            receiveCoal=1;
            jLabel10.setIcon(coal);
            jLabel10.setVisible(true);
            coalBankGetButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            coalBankGetButton.setLocation(coalBankGetButton.getX() - 1, coalBankGetButton.getY());  
            if(lumberBankGetPressed){
                lumberBankGetButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                lumberBankGetButton.setLocation(lumberBankGetButton.getX() + 1, lumberBankGetButton.getY());              
                lumberBankGetPressed=false;                                                                          
            }            
            if(oreBankGetPressed){
                oreBankGetButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                oreBankGetButton.setLocation(oreBankGetButton.getX() + 1, oreBankGetButton.getY());              
                oreBankGetPressed=false;                                                                          
            }            
            if(wheatBankGetPressed){
                wheatBankGetButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                wheatBankGetButton.setLocation(wheatBankGetButton.getX() + 1, wheatBankGetButton.getY());              
                wheatBankGetPressed=false;                                                                          
            }
            if(cattleBankGetPressed){
                cattleBankGetButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                cattleBankGetButton.setLocation(cattleBankGetButton.getX() + 1, cattleBankGetButton.getY());            
                cattleBankGetPressed=false;                                                                            
            }            
            if(goldBankGetPressed){
                goldBankGetButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                goldBankGetButton.setLocation(goldBankGetButton.getX() + 1, goldBankGetButton.getY());              
                goldBankGetPressed=false;                                                                          
            }
        }
    }//GEN-LAST:event_coalBankGetButtonMouseReleased
    private void cattleBankGetButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cattleBankGetButtonMouseReleased
        if(!cattleBankGetPressed&&cattleBankGetButton.isEnabled()){
            gameFrame.playSound("click");
            cattleBankGetPressed=true;
            if(currPlayer.getLumber()>3){
                lumberBankGiveButton.setEnabled(true);
            }            
            if(currPlayer.getCoal()>3){
                coalBankGiveButton.setEnabled(true);
            }
            if(currPlayer.getOre()>3){
                oreBankGiveButton.setEnabled(true);
            }
            if(currPlayer.getWheat()>3){
                wheatBankGiveButton.setEnabled(true);
            }
            if(currPlayer.getCattle()>3){
                cattleBankGiveButton.setEnabled(false);
            }
            if(currPlayer.getGold()>2 && currPlayer.getGoldTrade()<2){
                goldBankGiveButton.setEnabled(true);
            }
            receiveLumber=0;
            receiveCoal=0;
            receiveWheat=0;
            receiveOre=0;
            receiveGold=0;
            receiveCattle=1;
            jLabel10.setIcon(cattle);
            jLabel10.setVisible(true);
            cattleBankGetButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            cattleBankGetButton.setLocation(cattleBankGetButton.getX() - 1, cattleBankGetButton.getY());  
            if(coalBankGetPressed){
                coalBankGetButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                coalBankGetButton.setLocation(coalBankGetButton.getX() + 1, coalBankGetButton.getY());          
                coalBankGetPressed=false;                                                                              
            }            
            if(oreBankGetPressed){
                oreBankGetButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                oreBankGetButton.setLocation(oreBankGetButton.getX() + 1, oreBankGetButton.getY());             
                oreBankGetPressed=false;                                                                           
            }            
            if(wheatBankGetPressed){
                wheatBankGetButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                wheatBankGetButton.setLocation(wheatBankGetButton.getX() + 1, wheatBankGetButton.getY());           
                wheatBankGetPressed=false;                                                                             
            }
            if(lumberBankGetPressed){
                lumberBankGetButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                lumberBankGetButton.setLocation(lumberBankGetButton.getX() + 1, lumberBankGetButton.getY());            
                lumberBankGetPressed=false;                                                                            
            }            
            if(goldBankGetPressed){
                goldBankGetButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                goldBankGetButton.setLocation(goldBankGetButton.getX() + 1, goldBankGetButton.getY());              
                goldBankGetPressed=false;                                                                          
            }
        }
    }//GEN-LAST:event_cattleBankGetButtonMouseReleased
    private void oreBankGetButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_oreBankGetButtonMouseReleased
        if(!oreBankGetPressed&&oreBankGetButton.isEnabled()){
            gameFrame.playSound("click");
            oreBankGetPressed=true;
            if(currPlayer.getLumber()>3){
                lumberBankGiveButton.setEnabled(true);
            }            
            if(currPlayer.getCoal()>3){
                coalBankGiveButton.setEnabled(true);
            }
            if(currPlayer.getOre()>3){
                oreBankGiveButton.setEnabled(false);
            }
            if(currPlayer.getWheat()>3){
                wheatBankGiveButton.setEnabled(true);
            }
            if(currPlayer.getCattle()>3){
                cattleBankGiveButton.setEnabled(true);
            }
            if(currPlayer.getGold()>2 && currPlayer.getGoldTrade()<2){
                goldBankGiveButton.setEnabled(true);
            }
            receiveLumber=0;
            receiveCattle=0;
            receiveCoal=0;
            receiveWheat=0;
            receiveGold=0;
            receiveOre=1;
            jLabel10.setIcon(ore);
            jLabel10.setVisible(true);
            oreBankGetButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            oreBankGetButton.setLocation(oreBankGetButton.getX() - 1, oreBankGetButton.getY());  
            if(coalBankGetPressed){
                coalBankGetButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                coalBankGetButton.setLocation(coalBankGetButton.getX() + 1, coalBankGetButton.getY());           
                coalBankGetPressed=false;                                                                             
            }            
            if(lumberBankGetPressed){
                lumberBankGetButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                lumberBankGetButton.setLocation(lumberBankGetButton.getX() + 1, lumberBankGetButton.getY());             
                lumberBankGetPressed=false;                                                                           
            }            
            if(wheatBankGetPressed){
                wheatBankGetButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                wheatBankGetButton.setLocation(wheatBankGetButton.getX() + 1, wheatBankGetButton.getY());             
                wheatBankGetPressed=false;                                                                           
            }
            if(cattleBankGetPressed){
                cattleBankGetButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                cattleBankGetButton.setLocation(cattleBankGetButton.getX() + 1, cattleBankGetButton.getY());           
                cattleBankGetPressed=false;                                                                             
            }            
            if(goldBankGetPressed){
                goldBankGetButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                goldBankGetButton.setLocation(goldBankGetButton.getX() + 1, goldBankGetButton.getY());              
                goldBankGetPressed=false;                                                                          
            }
        }
    }//GEN-LAST:event_oreBankGetButtonMouseReleased
    private void wheatBankGetButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_wheatBankGetButtonMouseReleased
        if(!wheatBankGetPressed&&wheatBankGetButton.isEnabled()){
            gameFrame.playSound("click");
            wheatBankGetPressed=true;
            if(currPlayer.getLumber()>3){
                lumberBankGiveButton.setEnabled(true);
            }            
            if(currPlayer.getCoal()>3){
                coalBankGiveButton.setEnabled(true);
            }
            if(currPlayer.getOre()>3){
                oreBankGiveButton.setEnabled(true);
            }
            if(currPlayer.getWheat()>3){
                wheatBankGiveButton.setEnabled(false);
            }
            if(currPlayer.getCattle()>3){
                cattleBankGiveButton.setEnabled(true);
            }
            if(currPlayer.getGold()>2 && currPlayer.getGoldTrade()<2){
                goldBankGiveButton.setEnabled(true);
            }
            receiveLumber=0;
            receiveCattle=0;
            receiveCoal=0;
            receiveOre=0;
            receiveGold=0;
            receiveWheat=1;
            jLabel10.setIcon(wheat);
            jLabel10.setVisible(true);
            wheatBankGetButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            wheatBankGetButton.setLocation(wheatBankGetButton.getX() - 1, wheatBankGetButton.getY());  
            if(coalBankGetPressed){
                coalBankGetButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                coalBankGetButton.setLocation(coalBankGetButton.getX() + 1, coalBankGetButton.getY());             
                coalBankGetPressed=false;                                                                           
            }            
            if(oreBankGetPressed){
                oreBankGetButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                oreBankGetButton.setLocation(oreBankGetButton.getX() + 1, oreBankGetButton.getY());             
                oreBankGetPressed=false;                                                                           
            }            
            if(lumberBankGetPressed){
                lumberBankGetButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                lumberBankGetButton.setLocation(lumberBankGetButton.getX() + 1, lumberBankGetButton.getY());           
                lumberBankGetPressed=false;                                                                             
            }
            if(cattleBankGetPressed){
                cattleBankGetButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                cattleBankGetButton.setLocation(cattleBankGetButton.getX() + 1, cattleBankGetButton.getY());            
                cattleBankGetPressed=false;                                                                            
            }            
            if(goldBankGetPressed){
                goldBankGetButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                goldBankGetButton.setLocation(goldBankGetButton.getX() + 1, goldBankGetButton.getY());                 
                goldBankGetPressed=false;                                                                       
            }
        }
    }//GEN-LAST:event_wheatBankGetButtonMouseReleased
    private void goldBankGetButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_goldBankGetButtonMouseReleased
        if(!goldBankGetPressed&&goldBankGetButton.isEnabled()){
            gameFrame.playSound("click");
            goldBankGetPressed=true;
            if(currPlayer.getLumber()>3){
                lumberBankGiveButton.setEnabled(true);
            }            
            if(currPlayer.getCoal()>3){
                coalBankGiveButton.setEnabled(true);
            }
            if(currPlayer.getOre()>3){
                oreBankGiveButton.setEnabled(true);
            }
            if(currPlayer.getWheat()>3){
                wheatBankGiveButton.setEnabled(true);
            }
            if(currPlayer.getCattle()>3){
                cattleBankGiveButton.setEnabled(true);
            }
            if(currPlayer.getGold()>2){
                goldBankGiveButton.setEnabled(false);
            }
            receiveLumber=0;
            receiveCattle=0;
            receiveCoal=0;
            receiveWheat=0;
            receiveOre=0;
            receiveGold=1;
            jLabel10.setIcon(gold);
            jLabel10.setVisible(true);
            goldBankGetButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            goldBankGetButton.setLocation(goldBankGetButton.getX() - 1, goldBankGetButton.getY());  
            if(coalBankGetPressed){
                coalBankGetButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                coalBankGetButton.setLocation(coalBankGetButton.getX() + 1, coalBankGetButton.getY());            
                coalBankGetPressed=false;                                                                            
            }            
            if(oreBankGetPressed){
                oreBankGetButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                oreBankGetButton.setLocation(oreBankGetButton.getX() + 1, oreBankGetButton.getY());             
                oreBankGetPressed=false;                                                                           
            }            
            if(wheatBankGetPressed){
                wheatBankGetButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                wheatBankGetButton.setLocation(wheatBankGetButton.getX() + 1, wheatBankGetButton.getY());            
                wheatBankGetPressed=false;                                                                            
            }
            if(cattleBankGetPressed){
                cattleBankGetButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                cattleBankGetButton.setLocation(cattleBankGetButton.getX() + 1, cattleBankGetButton.getY());            
                cattleBankGetPressed=false;                                                                            
            }            
            if(lumberBankGetPressed){
                lumberBankGetButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                lumberBankGetButton.setLocation(lumberBankGetButton.getX() + 1, lumberBankGetButton.getY());            
                lumberBankGetPressed=false;                                                                            
            }
        }
    }//GEN-LAST:event_goldBankGetButtonMouseReleased
    private void tradeButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tradeButton1MouseReleased
        if(tradeButton.isAble()){
            if(!(receiveLumber == 0 && receiveGold == 0 && receiveWheat == 0 && receiveOre == 0 && receiveCoal == 0 && receiveCattle == 0)) {
                if (!(giveLumber == 0 && giveGold == 0 && giveWheat == 0 && giveOre == 0 && giveCoal == 0 && giveCattle == 0)) {
                    if (tradeWithBank) {
                        String s = "";
                        if (giveLumber != 0) {
                            currPlayer.setLumber(currPlayer.getLumber() - giveLumber);
                            s = currPlayer.getName() + " traded 3 lumber ";
                        } else if (giveCoal != 0) {
                            currPlayer.setCoal(currPlayer.getCoal() - giveCoal);
                            s = currPlayer.getName() + " traded 3 coal ";
                        } else if (giveCattle != 0) {
                            currPlayer.setCattle(currPlayer.getCattle() - giveCattle);
                            s = currPlayer.getName() + " traded 3 cattle ";
                        } else if (giveWheat != 0) {
                            currPlayer.setWheat(currPlayer.getWheat() - giveWheat);
                            s = currPlayer.getName() + " traded 3 wheat ";
                        } else if (giveOre != 0) {
                            currPlayer.setOre(currPlayer.getOre() - giveOre);
                            s = currPlayer.getName() + " traded 3 ore ";
                        } else if (giveGold != 0) {
                            currPlayer.setGoldIgnoreStuff(currPlayer.getGold() - giveGold);
                            s = currPlayer.getName() + " traded 2 gold ";
                            currPlayer.plusGoldTrade();
                        }
                        if (receiveLumber != 0) {
                            currPlayer.setLumber(currPlayer.getLumber() + receiveLumber);
                            s += "for a lumber";
                        } else if (receiveCoal != 0) {
                            currPlayer.setCoal(currPlayer.getCoal() + receiveCoal);
                            s += "for a coal";
                        } else if (receiveCattle != 0) {
                            currPlayer.setCattle(currPlayer.getCattle() + receiveCattle);
                            s += "for a cattle";
                        } else if (receiveWheat != 0) {
                            currPlayer.setWheat(currPlayer.getWheat() + receiveWheat);
                            s += "for a wheat";
                        } else if (receiveOre != 0) {
                            currPlayer.setOre(currPlayer.getOre() + receiveOre);
                            s += "for an ore";
                        } else if (receiveGold != 0) {
                            currPlayer.setGoldIgnoreStuff(currPlayer.getGold() + receiveGold);
                            s += "for a gold";
                            gameFrame.playSound("ching");
                        }
                        gameFrame.addToInfo(s, false);
                        this.setVisible(false);
                    } else {
                        Player[] players = game_Frame.getPlayers();
                        ArrayList<Player> playersToTrade = new ArrayList<Player>();
                        for (int i = 0; i < players.length; i++) {
                            if (players[i].getLumber() >= receiveLumber
                                    && players[i].getOre() >= receiveOre
                                    && players[i].getCoal() >= receiveCoal
                                    && players[i].getCattle() >= receiveCattle
                                    && players[i].getWheat() >= receiveWheat
                                    && players[i].getGold() >= receiveGold && players[i] != currPlayer) {
                                playersToTrade.add(players[i]);
                            }
                        }
                        if (!playersToTrade.isEmpty()) {
                            game_Frame.disableMenu();
                            Player p = playersToTrade.remove(0);
                            currPlayer.setTurn(false);
                            game_Frame.setRealPlayer(currPlayer);
                            game_Frame.setCurrPlayer(p);
                            p.setTurn(true);
                            game_Frame.setBackgroundPlayer();
                            TradeConfirm tc = new TradeConfirm(currPlayer,
                                    p, receiveLumber, giveLumber, receiveCoal,
                                    giveCoal, receiveCattle, giveCattle,
                                    receiveOre, giveOre, giveWheat, receiveWheat,
                                    giveGold, receiveGold, playersToTrade, game_Frame);
                            tc.setVisible(true);
                            this.setVisible(false);
                        } else {
                            new SettlersConfirmDialog(21).setVisible(true);
                            this.setVisible(false);
                        }
                    }
                } else {
                    //you need to select something to give    
                    new SettlersConfirmDialog(23).setVisible(true);            
                }
            } else {
                //you need to select something to get
                 new SettlersConfirmDialog(22).setVisible(true);
            }
        }
}//GEN-LAST:event_tradeButton1MouseReleased
    private void xButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xButton1MouseReleased
        if(xButton.isAble()){
            this.setVisible(false);
        }
    }//GEN-LAST:event_xButton1MouseReleased
    private void cattlePlayerGiveButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cattlePlayerGiveButton1MouseReleased
        if (cattlePlayerGiveButton.isAble()) {
            giveCattle++;
            increase(cattleLabelPlayerGiveVar);
            if (currPlayer.getOre() <= giveOre) {
                orePlayerGiveButton.setEnabled(false);
            }
            if (currPlayer.getCattle() <= giveCattle) {
                cattlePlayerGiveButton.setEnabled(false);
            }
            if (currPlayer.getCoal() <= giveCoal) {
                coalPlayerGiveButton.setEnabled(false);
            }
            if (currPlayer.getWheat() <= giveWheat) {
                wheatPlayerGiveButton.setEnabled(false);
            }
            if (currPlayer.getLumber() <= giveLumber) {
                lumberPlayerGiveButton1.setEnabled(false);
            }
            if (currPlayer.getGold() <= giveGold) {
                goldPlayerGiveButton.setEnabled(false);
            }
        }
    }//GEN-LAST:event_cattlePlayerGiveButton1MouseReleased
    private void orePlayerGiveButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_orePlayerGiveButton1MouseReleased
        if (orePlayerGiveButton.isAble()) {
            giveOre++;
            increase(oreLabelPlayerGiveVar);
            if (currPlayer.getOre() <= giveOre) {
                orePlayerGiveButton.setEnabled(false);
            }
            if (currPlayer.getCattle() <= giveCattle) {
                cattlePlayerGiveButton.setEnabled(false);
            }
            if (currPlayer.getCoal() <= giveCoal) {
                coalPlayerGiveButton.setEnabled(false);
            }
            if (currPlayer.getWheat() <= giveWheat) {
                wheatPlayerGiveButton.setEnabled(false);
            }
            if (currPlayer.getLumber() <= giveLumber) {
                lumberPlayerGiveButton1.setEnabled(false);
            }
            if (currPlayer.getGold() <= giveGold) {
                goldPlayerGiveButton.setEnabled(false);
            }
        }
    }//GEN-LAST:event_orePlayerGiveButton1MouseReleased
    private void wheatPlayerGiveButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_wheatPlayerGiveButton1MouseReleased
        if (wheatPlayerGiveButton.isAble()) {
            giveWheat++;
            increase(wheatLabelPlayerGiveVar);
            if (currPlayer.getOre() <= giveOre) {
                orePlayerGiveButton.setEnabled(false);
            }
            if (currPlayer.getCattle() <= giveCattle) {
                cattlePlayerGiveButton.setEnabled(false);
            }
            if (currPlayer.getCoal() <= giveCoal) {
                coalPlayerGiveButton.setEnabled(false);
            }
            if (currPlayer.getWheat() <= giveWheat) {
                wheatPlayerGiveButton.setEnabled(false);
            }
            if (currPlayer.getLumber() <= giveLumber) {
                lumberPlayerGiveButton1.setEnabled(false);
            }
            if (currPlayer.getGold() <= giveGold) {
                goldPlayerGiveButton.setEnabled(false);
            }
        }
    }//GEN-LAST:event_wheatPlayerGiveButton1MouseReleased
    private void coalPlayerGetButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_coalPlayerGetButton1MouseReleased
        if (coalPlayerGetButton.isAble()) {
            receiveCoal++;
            increase(coalLabelPlayerGetVar);
        }
    }//GEN-LAST:event_coalPlayerGetButton1MouseReleased
    private void cattlePlayerGetButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cattlePlayerGetButton1MouseReleased
        if (cattlePlayerGetButton.isAble()) {
            receiveCattle++;
            increase(cattleLabelPlayerGetVar);
        }
    }//GEN-LAST:event_cattlePlayerGetButton1MouseReleased
    private void orePlayerGetButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_orePlayerGetButton1MouseReleased
        if (orePlayerGetButton.isAble()) {
            receiveOre++;
            increase(oreLabelPlayerGetVar);
        }
    }//GEN-LAST:event_orePlayerGetButton1MouseReleased
    private void wheatPlayerGetButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_wheatPlayerGetButton1MouseReleased
        if (wheatPlayerGetButton.isAble()) {
            receiveWheat++;
            increase(wheatLabelPlayerGetVar);
        }
    }//GEN-LAST:event_wheatPlayerGetButton1MouseReleased
    private void goldPlayerGetButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_goldPlayerGetButton1MouseReleased
        if (goldPlayerGetButton.isAble()) {
            receiveGold++;
            increase(goldLabelPlayerGetVar);
        }
    }//GEN-LAST:event_goldPlayerGetButton1MouseReleased
    private void coalPlayerGiveButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_coalPlayerGiveButton1MouseReleased
        if (coalPlayerGiveButton.isAble()) {
            giveCoal++;
            increase(coalLabelPlayerGiveVar);
            if (currPlayer.getOre() <= giveOre) {
                orePlayerGiveButton.setEnabled(false);
            }
            if (currPlayer.getCattle() <= giveCattle) {
                cattlePlayerGiveButton.setEnabled(false);
            }
            if (currPlayer.getCoal() <= giveCoal) {
                coalPlayerGiveButton.setEnabled(false);
            }
            if (currPlayer.getWheat() <= giveWheat) {
                wheatPlayerGiveButton.setEnabled(false);
            }
            if (currPlayer.getLumber() <= giveLumber) {
                lumberPlayerGiveButton1.setEnabled(false);
            }
            if (currPlayer.getGold() <= giveGold) {
                goldPlayerGiveButton.setEnabled(false);
            }
        }
}//GEN-LAST:event_coalPlayerGiveButton1MouseReleased
    private void goldPlayerGiveButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_goldPlayerGiveButton1MouseReleased
        if (goldPlayerGiveButton.isAble()) {
            giveGold++;
            increase(goldLabelPlayerGiveVar);
            if (currPlayer.getOre() <= giveOre) {
                orePlayerGiveButton.setEnabled(false);
            }
            if (currPlayer.getCattle() <= giveCattle) {
                cattlePlayerGiveButton.setEnabled(false);
            }
            if (currPlayer.getCoal() <= giveCoal) {
                coalPlayerGiveButton.setEnabled(false);
            }
            if (currPlayer.getWheat() <= giveWheat) {
                wheatPlayerGiveButton.setEnabled(false);
            }
            if (currPlayer.getLumber() <= giveLumber) {
                lumberPlayerGiveButton1.setEnabled(false);
            }
            if (currPlayer.getGold() <= giveGold) {
                goldPlayerGiveButton.setEnabled(false);
            }
        }
}//GEN-LAST:event_goldPlayerGiveButton1MouseReleased
    private void lumberPlayerGetButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lumberPlayerGetButton1MouseReleased
        if (lumberPlayerGetButton.isAble()) {
            receiveLumber++;
            increase(lumberLabelPlayerGetVar);
        }
    }//GEN-LAST:event_lumberPlayerGetButton1MouseReleased
    private void clearGetButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearGetButton1MouseReleased
        if(clearGetButton.isAble()) {
            receiveLumber = 0;
            receiveCoal = 0;
            receiveWheat = 0;
            receiveCattle = 0;
            receiveGold = 0;
            receiveOre = 0;
            resetGetLabels();
        }
    }//GEN-LAST:event_clearGetButton1MouseReleased
    private void clearGiveButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearGiveButton1MouseReleased
        if(clearGiveButton.isAble()) {
            giveLumber = 0;
            giveCoal = 0;
            giveWheat = 0;
            giveCattle = 0;
            giveGold = 0;
            giveOre = 0;
            resetGiveLabels();
            orePlayerGiveButton.setEnabled(true);
            lumberPlayerGiveButton.setEnabled(true);
            coalPlayerGiveButton.setEnabled(true);
            wheatPlayerGiveButton.setEnabled(true);
            cattlePlayerGiveButton.setEnabled(true);
            goldPlayerGiveButton.setEnabled(true);
            if (currPlayer.getOre() < 1) {
                orePlayerGiveButton.setEnabled(false);
            }
            if (currPlayer.getCattle() < 1) {
                cattlePlayerGiveButton.setEnabled(false);
            }
            if (currPlayer.getCoal() < 1) {
                coalPlayerGiveButton.setEnabled(false);
            }
            if (currPlayer.getWheat() < 1) {
                wheatPlayerGiveButton.setEnabled(false);
            }
            if (currPlayer.getLumber() < 1) {
                lumberPlayerGiveButton1.setEnabled(false);
            }
            if (currPlayer.getGold() < 1) {
                goldPlayerGiveButton.setEnabled(false);
            }
        }
    }//GEN-LAST:event_clearGiveButton1MouseReleased
    private void lumberPlayerGiveButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lumberPlayerGiveButton1MouseReleased
        if (lumberPlayerGiveButton.isAble()) {
            giveLumber++;
            increase(lumberLabelPlayerGiveVar);
            if (currPlayer.getOre() <= giveOre) {
                orePlayerGiveButton.setEnabled(false);
            }
            if (currPlayer.getCattle() <= giveCattle) {
                cattlePlayerGiveButton.setEnabled(false);
            }
            if (currPlayer.getCoal() <= giveCoal) {
                coalPlayerGiveButton.setEnabled(false);
            }
            if (currPlayer.getWheat() <= giveWheat) {
                wheatPlayerGiveButton.setEnabled(false);
            }
            if (currPlayer.getLumber() <= giveLumber) {
                lumberPlayerGiveButton1.setEnabled(false);
            }
            if (currPlayer.getGold() <= giveGold) {
                goldPlayerGiveButton.setEnabled(false);
            }
        }
    }//GEN-LAST:event_lumberPlayerGiveButton1MouseReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new TradeWindow().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel backgroundLabel;
    private javax.swing.JPanel bankTradePanel;
    private javax.swing.JToggleButton bankTradeTButton;
    private javax.swing.JButton cattleBankGetButton;
    private javax.swing.JButton cattleBankGiveButton;
    private javax.swing.JLabel cattleLabelPlayerGet;
    private javax.swing.JLabel cattleLabelPlayerGetVar;
    private javax.swing.JLabel cattleLabelPlayerGive;
    private javax.swing.JLabel cattleLabelPlayerGiveVar;
    private javax.swing.JButton cattlePlayerGetButton1;
    private javax.swing.JButton cattlePlayerGiveButton1;
    private javax.swing.JButton clearGetButton1;
    private javax.swing.JButton clearGiveButton1;
    private javax.swing.JButton coalBankGetButton;
    private javax.swing.JButton coalBankGiveButton;
    private javax.swing.JLabel coalLabelPlayerGet;
    private javax.swing.JLabel coalLabelPlayerGetVar;
    private javax.swing.JLabel coalLabelPlayerGive;
    private javax.swing.JLabel coalLabelPlayerGiveVar;
    private javax.swing.JButton coalPlayerGetButton1;
    private javax.swing.JButton coalPlayerGiveButton1;
    private javax.swing.JButton goldBankGetButton;
    private javax.swing.JButton goldBankGiveButton;
    private javax.swing.JLabel goldLabelPlayerGet;
    private javax.swing.JLabel goldLabelPlayerGetVar;
    private javax.swing.JLabel goldLabelPlayerGive;
    private javax.swing.JLabel goldLabelPlayerGiveVar;
    private javax.swing.JButton goldPlayerGetButton1;
    private javax.swing.JButton goldPlayerGiveButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JToggleButton lumberBankGetButton;
    private javax.swing.JToggleButton lumberBankGiveButton;
    private javax.swing.JLabel lumberLabelPlayerGet;
    private javax.swing.JLabel lumberLabelPlayerGetVar;
    private javax.swing.JLabel lumberLabelPlayerGive;
    private javax.swing.JLabel lumberLabelPlayerGiveVar;
    private javax.swing.JButton lumberPlayerGetButton1;
    private javax.swing.JButton lumberPlayerGiveButton1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel mainQLabel;
    private javax.swing.JButton oreBankGetButton;
    private javax.swing.JButton oreBankGiveButton;
    private javax.swing.JLabel oreLabelPlayerGet;
    private javax.swing.JLabel oreLabelPlayerGetVar;
    private javax.swing.JLabel oreLabelPlayerGive;
    private javax.swing.JLabel oreLabelPlayerGiveVar;
    private javax.swing.JButton orePlayerGetButton1;
    private javax.swing.JButton orePlayerGiveButton1;
    private javax.swing.JPanel playerTradePanel;
    private javax.swing.JToggleButton playerTradeTButton;
    private javax.swing.JLabel tradeBankGetLabel;
    private javax.swing.JPanel tradeBankGetPanel;
    private javax.swing.JLabel tradeBankGiveLabel;
    private javax.swing.JPanel tradeBankGivePanel;
    private javax.swing.JButton tradeButton1;
    private javax.swing.JPanel tradeGetBankDisplayPanel;
    private javax.swing.JPanel tradeGetPlayerDisplayPanel;
    private javax.swing.JPanel tradeGiveBankDisplayPanel;
    private javax.swing.JPanel tradeGivePlayerDisplayPanel;
    private javax.swing.JLabel tradePlayerGetLabel;
    private javax.swing.JPanel tradePlayerGetPanel;
    private javax.swing.JLabel tradePlayerGiveLabel;
    private javax.swing.JPanel tradePlayerGivePanel;
    private javax.swing.JLabel tradeWithBankLabel;
    private javax.swing.JLabel tradeWithBankLabel1;
    private javax.swing.JLabel tradeWithPlayerLabel;
    private javax.swing.JButton wheatBankGetButton;
    private javax.swing.JButton wheatBankGiveButton;
    private javax.swing.JLabel wheatLabelPlayerGet;
    private javax.swing.JLabel wheatLabelPlayerGetVar;
    private javax.swing.JLabel wheatLabelPlayerGive;
    private javax.swing.JLabel wheatLabelPlayerGiveVar;
    private javax.swing.JButton wheatPlayerGetButton1;
    private javax.swing.JButton wheatPlayerGiveButton1;
    private javax.swing.JButton xButton1;
    // End of variables declaration//GEN-END:variables
}

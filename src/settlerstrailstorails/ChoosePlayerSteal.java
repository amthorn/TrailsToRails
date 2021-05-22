/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ChoosePlayerSteal.java
 *
 * Created on Oct 16, 2013, 5:24:54 PM
 */
package settlerstrailstorails;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author User 1
 */
public class ChoosePlayerSteal extends javax.swing.JFrame {
    
    ArrayList<Player> stealPlayers;
    AveryJButton player1Info2PlrButton,player1Slct2PlrButton,player2Info2PlrButton,
            player2Slct2PlrButton,player1Info3PlrButton,player1Slct3PlrButton,
            player2Info3PlrButton,player2Slct3PlrButton,player3Info3PlrButton,
            player3Slct3PlrButton;
    gameFrame game_Frame;
    PlayerInfoWindow piw;

    /** Creates new form ChoosePlayerSteal */
    ChoosePlayerSteal(ArrayList<Player> p,gameFrame g) {
        stealPlayers=p;
        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(((dim.width/2)-(this.getSize().width/2)), ((dim.height/2)-((this.getSize().height/2)+20)));
        try{
            setIconImage(ImageIO.read(getClass().getResource("/settlerstrailstorails/resources/icon.png")));
        }catch(Exception e){
            
        }
        game_Frame=g;
        twoPlayerPanel.setVisible(false);
        threePlayerPanel.setVisible(false);
        if(p.size()==3){
            threePlayerPanel.setVisible(true);
            playerOne3pLabel.setText(p.get(0).getName());
            playerTwo3pLabel.setText(p.get(1).getName());
            playerThree3pLabel.setText(p.get(2).getName());
            player1Info3PlrButton=new AveryJButton(player1Info3PlrButton1);
            player1Slct3PlrButton=new AveryJButton(player1Slct3PlrButton1);
            player2Info3PlrButton=new AveryJButton(player2Info3PlrButton1);
            player2Slct3PlrButton=new AveryJButton(player2Slct3PlrButton1);
            player3Info3PlrButton=new AveryJButton(player3Info3PlrButton1);
            player3Slct3PlrButton=new AveryJButton(player3Slct3PlrButton1);
        }else if(p.size()==2){
            twoPlayerPanel.setVisible(true);
            playerOne2pLabel.setText(p.get(0).getName());
            playerTwo2pLabel.setText(p.get(1).getName());
            player1Info2PlrButton=new AveryJButton(player1Info2PlrButton1);
            player1Slct2PlrButton=new AveryJButton(player1Slct2PlrButton1);
            player2Info2PlrButton=new AveryJButton(player2Info2PlrButton1);
            player2Slct2PlrButton=new AveryJButton(player2Slct2PlrButton1);
        }
    }
    public ChoosePlayerSteal(){
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        threePlayerPanel = new javax.swing.JPanel();
        threePlayer3Panel = new javax.swing.JPanel();
        player3Info3PlrButton1 = new javax.swing.JButton();
        player3Slct3PlrButton1 = new javax.swing.JButton();
        playerThree3pLabel = new javax.swing.JLabel();
        threePlayer2Panel = new javax.swing.JPanel();
        player2Info3PlrButton1 = new javax.swing.JButton();
        player2Slct3PlrButton1 = new javax.swing.JButton();
        playerTwo3pLabel = new javax.swing.JLabel();
        threePlayer1Panel = new javax.swing.JPanel();
        player1Info3PlrButton1 = new javax.swing.JButton();
        player1Slct3PlrButton1 = new javax.swing.JButton();
        playerOne3pLabel = new javax.swing.JLabel();
        questionLabel = new javax.swing.JLabel();
        questionLabel1 = new javax.swing.JLabel();
        twoPlayerPanel = new javax.swing.JPanel();
        twoPlayer2Panel = new javax.swing.JPanel();
        player2Info2PlrButton1 = new javax.swing.JButton();
        player2Slct2PlrButton1 = new javax.swing.JButton();
        playerTwo2pLabel = new javax.swing.JLabel();
        onePlayer2Panel = new javax.swing.JPanel();
        player1Info2PlrButton1 = new javax.swing.JButton();
        player1Slct2PlrButton1 = new javax.swing.JButton();
        playerOne2pLabel = new javax.swing.JLabel();
        questionLabel2 = new javax.swing.JLabel();
        questionLabel3 = new javax.swing.JLabel();
        backgroundLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        threePlayerPanel.setOpaque(false);
        threePlayerPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        threePlayer3Panel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        threePlayer3Panel.setOpaque(false);
        threePlayer3Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        player3Info3PlrButton1.setBackground(new java.awt.Color(239, 228, 176));
        player3Info3PlrButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        player3Info3PlrButton1.setText("Info");
        player3Info3PlrButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        player3Info3PlrButton1.setContentAreaFilled(false);
        player3Info3PlrButton1.setFocusable(false);
        player3Info3PlrButton1.setOpaque(true);
        player3Info3PlrButton1.setPreferredSize(new java.awt.Dimension(75, 25));
        player3Info3PlrButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                player3Info3PlrButton1MouseReleased(evt);
            }
        });
        threePlayer3Panel.add(player3Info3PlrButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 50, -1));

        player3Slct3PlrButton1.setBackground(new java.awt.Color(239, 228, 176));
        player3Slct3PlrButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        player3Slct3PlrButton1.setText("Select");
        player3Slct3PlrButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        player3Slct3PlrButton1.setContentAreaFilled(false);
        player3Slct3PlrButton1.setFocusable(false);
        player3Slct3PlrButton1.setOpaque(true);
        player3Slct3PlrButton1.setPreferredSize(new java.awt.Dimension(75, 25));
        player3Slct3PlrButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                player3Slct3PlrButton1MouseReleased(evt);
            }
        });
        threePlayer3Panel.add(player3Slct3PlrButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 65, 70, -1));

        playerThree3pLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14));
        playerThree3pLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        playerThree3pLabel.setText("Player");
        threePlayer3Panel.add(playerThree3pLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, 5, 110, -1));

        threePlayerPanel.add(threePlayer3Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(255, 70, 110, 100));

        threePlayer2Panel.setBackground(new java.awt.Color(239, 228, 176));
        threePlayer2Panel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        threePlayer2Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        player2Info3PlrButton1.setBackground(new java.awt.Color(239, 228, 176));
        player2Info3PlrButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        player2Info3PlrButton1.setText("Info");
        player2Info3PlrButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        player2Info3PlrButton1.setContentAreaFilled(false);
        player2Info3PlrButton1.setFocusable(false);
        player2Info3PlrButton1.setOpaque(true);
        player2Info3PlrButton1.setPreferredSize(new java.awt.Dimension(75, 25));
        player2Info3PlrButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                player2Info3PlrButton1MouseReleased(evt);
            }
        });
        threePlayer2Panel.add(player2Info3PlrButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 50, -1));

        player2Slct3PlrButton1.setBackground(new java.awt.Color(239, 228, 176));
        player2Slct3PlrButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        player2Slct3PlrButton1.setText("Select");
        player2Slct3PlrButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        player2Slct3PlrButton1.setContentAreaFilled(false);
        player2Slct3PlrButton1.setFocusable(false);
        player2Slct3PlrButton1.setOpaque(true);
        player2Slct3PlrButton1.setPreferredSize(new java.awt.Dimension(75, 25));
        player2Slct3PlrButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                player2Slct3PlrButton1MouseReleased(evt);
            }
        });
        threePlayer2Panel.add(player2Slct3PlrButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 65, 70, -1));

        playerTwo3pLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14));
        playerTwo3pLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        playerTwo3pLabel.setText("Player");
        threePlayer2Panel.add(playerTwo3pLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, 5, 110, -1));

        threePlayerPanel.add(threePlayer2Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(136, 70, 110, 100));

        threePlayer1Panel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        threePlayer1Panel.setOpaque(false);
        threePlayer1Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        player1Info3PlrButton1.setBackground(new java.awt.Color(239, 228, 176));
        player1Info3PlrButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        player1Info3PlrButton1.setText("Info");
        player1Info3PlrButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        player1Info3PlrButton1.setContentAreaFilled(false);
        player1Info3PlrButton1.setFocusable(false);
        player1Info3PlrButton1.setOpaque(true);
        player1Info3PlrButton1.setPreferredSize(new java.awt.Dimension(75, 25));
        player1Info3PlrButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                player1Info3PlrButton1MouseReleased(evt);
            }
        });
        threePlayer1Panel.add(player1Info3PlrButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 50, -1));

        player1Slct3PlrButton1.setBackground(new java.awt.Color(239, 228, 176));
        player1Slct3PlrButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        player1Slct3PlrButton1.setText("Select");
        player1Slct3PlrButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        player1Slct3PlrButton1.setContentAreaFilled(false);
        player1Slct3PlrButton1.setFocusable(false);
        player1Slct3PlrButton1.setOpaque(true);
        player1Slct3PlrButton1.setPreferredSize(new java.awt.Dimension(75, 25));
        player1Slct3PlrButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                player1Slct3PlrButton1MouseReleased(evt);
            }
        });
        threePlayer1Panel.add(player1Slct3PlrButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 65, 70, -1));

        playerOne3pLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14));
        playerOne3pLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        playerOne3pLabel.setText("Player");
        threePlayer1Panel.add(playerOne3pLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, 5, 110, -1));

        threePlayerPanel.add(threePlayer1Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 70, 110, 100));

        questionLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        questionLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        questionLabel.setText("Choose a player to steal");
        threePlayerPanel.add(questionLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 360, -1));

        questionLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        questionLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        questionLabel1.setText("one resource from.");
        threePlayerPanel.add(questionLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 360, -1));

        getContentPane().add(threePlayerPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, 190));

        twoPlayerPanel.setOpaque(false);
        twoPlayerPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        twoPlayer2Panel.setBackground(new java.awt.Color(239, 228, 176));
        twoPlayer2Panel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        twoPlayer2Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        player2Info2PlrButton1.setBackground(new java.awt.Color(239, 228, 176));
        player2Info2PlrButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        player2Info2PlrButton1.setText("Info");
        player2Info2PlrButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        player2Info2PlrButton1.setContentAreaFilled(false);
        player2Info2PlrButton1.setFocusable(false);
        player2Info2PlrButton1.setOpaque(true);
        player2Info2PlrButton1.setPreferredSize(new java.awt.Dimension(75, 25));
        player2Info2PlrButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                player2Info2PlrButton1MouseReleased(evt);
            }
        });
        twoPlayer2Panel.add(player2Info2PlrButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 30, 120, -1));

        player2Slct2PlrButton1.setBackground(new java.awt.Color(239, 228, 176));
        player2Slct2PlrButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        player2Slct2PlrButton1.setText("Select");
        player2Slct2PlrButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        player2Slct2PlrButton1.setContentAreaFilled(false);
        player2Slct2PlrButton1.setFocusable(false);
        player2Slct2PlrButton1.setOpaque(true);
        player2Slct2PlrButton1.setPreferredSize(new java.awt.Dimension(75, 25));
        player2Slct2PlrButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                player2Slct2PlrButton1MouseReleased(evt);
            }
        });
        twoPlayer2Panel.add(player2Slct2PlrButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 65, 140, -1));

        playerTwo2pLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14));
        playerTwo2pLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        playerTwo2pLabel.setText("Player");
        twoPlayer2Panel.add(playerTwo2pLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 5, 114, -1));

        twoPlayerPanel.add(twoPlayer2Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(195, 80, 175, 100));

        onePlayer2Panel.setBackground(new java.awt.Color(239, 228, 176));
        onePlayer2Panel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        onePlayer2Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        player1Info2PlrButton1.setBackground(new java.awt.Color(239, 228, 176));
        player1Info2PlrButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        player1Info2PlrButton1.setText("Info");
        player1Info2PlrButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        player1Info2PlrButton1.setContentAreaFilled(false);
        player1Info2PlrButton1.setFocusable(false);
        player1Info2PlrButton1.setOpaque(true);
        player1Info2PlrButton1.setPreferredSize(new java.awt.Dimension(75, 25));
        player1Info2PlrButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                player1Info2PlrButton1MouseReleased(evt);
            }
        });
        onePlayer2Panel.add(player1Info2PlrButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 30, 110, -1));

        player1Slct2PlrButton1.setBackground(new java.awt.Color(239, 228, 176));
        player1Slct2PlrButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        player1Slct2PlrButton1.setText("Select");
        player1Slct2PlrButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        player1Slct2PlrButton1.setContentAreaFilled(false);
        player1Slct2PlrButton1.setFocusable(false);
        player1Slct2PlrButton1.setOpaque(true);
        player1Slct2PlrButton1.setPreferredSize(new java.awt.Dimension(75, 25));
        player1Slct2PlrButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                player1Slct2PlrButton1MouseReleased(evt);
            }
        });
        onePlayer2Panel.add(player1Slct2PlrButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 65, 130, -1));

        playerOne2pLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14));
        playerOne2pLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        playerOne2pLabel.setText("Player");
        onePlayer2Panel.add(playerOne2pLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 5, 104, -1));

        twoPlayerPanel.add(onePlayer2Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 175, 100));

        questionLabel2.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        questionLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        questionLabel2.setText("Choose a player to steal");
        twoPlayerPanel.add(questionLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 360, -1));

        questionLabel3.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        questionLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        questionLabel3.setText("one resource from.");
        twoPlayerPanel.add(questionLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 360, -1));

        getContentPane().add(twoPlayerPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, 190));

        backgroundLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/choosePlayerBackground.png"))); // NOI18N
        getContentPane().add(backgroundLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void player1Info3PlrButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_player1Info3PlrButton1MouseReleased
        if (player1Info3PlrButton.isAble()) {
            if(piw!=null&&piw.isVisible()){
                piw.setVisible(false);
            }
            piw = new PlayerInfoWindow(stealPlayers.get(0));
            piw.setVisible(true);
        }
}//GEN-LAST:event_player1Info3PlrButton1MouseReleased
    private void player1Slct3PlrButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_player1Slct3PlrButton1MouseReleased
        if(player1Slct3PlrButton.isAble()){
            //steal resources from a player
            game_Frame.getCurrPlayer().randomCardFrom(stealPlayers.get(0));
            gameFrame.addToInfo(game_Frame.getCurrPlayer()+" stole a card from "+stealPlayers.get(0).getName(),false);
            this.setVisible(false);
            game_Frame.enableMenu();
        }
    }//GEN-LAST:event_player1Slct3PlrButton1MouseReleased
    private void player2Info3PlrButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_player2Info3PlrButton1MouseReleased
        if (player2Info3PlrButton.isAble()) {
            if(piw!=null&&piw.isVisible()){
                piw.setVisible(false);
            }
            piw = new PlayerInfoWindow(stealPlayers.get(1));
            piw.setVisible(true);
        }
    }//GEN-LAST:event_player2Info3PlrButton1MouseReleased
    private void player2Slct3PlrButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_player2Slct3PlrButton1MouseReleased
        if(player2Slct3PlrButton.isAble()){
            //steal resources from a player
            game_Frame.getCurrPlayer().randomCardFrom(stealPlayers.get(1));
            gameFrame.addToInfo(game_Frame.getCurrPlayer()+" stole a card from "+stealPlayers.get(1).getName(),false);
            this.setVisible(false);
            game_Frame.enableMenu();
        }
    }//GEN-LAST:event_player2Slct3PlrButton1MouseReleased
    private void player3Info3PlrButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_player3Info3PlrButton1MouseReleased
        if (player3Info3PlrButton.isAble()) {
            if(piw!=null&&piw.isVisible()){
                piw.setVisible(false);
            }
            piw = new PlayerInfoWindow(stealPlayers.get(2));
            piw.setVisible(true);
        }
    }//GEN-LAST:event_player3Info3PlrButton1MouseReleased
    private void player3Slct3PlrButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_player3Slct3PlrButton1MouseReleased
        if(player3Slct3PlrButton.isAble()){
            //steal resources from a player
            game_Frame.getCurrPlayer().randomCardFrom(stealPlayers.get(2));
            gameFrame.addToInfo(game_Frame.getCurrPlayer()+" stole a card from "+stealPlayers.get(2).getName(),false);
            this.setVisible(false);
            game_Frame.enableMenu();
        }
    }//GEN-LAST:event_player3Slct3PlrButton1MouseReleased
    private void player2Info2PlrButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_player2Info2PlrButton1MouseReleased
        if (player2Info2PlrButton.isAble()) {
            if(piw!=null&&piw.isVisible()){
                piw.setVisible(false);
            }
            piw = new PlayerInfoWindow(stealPlayers.get(1));
            piw.setVisible(true);
        }
    }//GEN-LAST:event_player2Info2PlrButton1MouseReleased
    private void player2Slct2PlrButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_player2Slct2PlrButton1MouseReleased
        if(player2Slct2PlrButton.isAble()){
            //steal resources from a player
            game_Frame.getCurrPlayer().randomCardFrom(stealPlayers.get(1));
            gameFrame.addToInfo(game_Frame.getCurrPlayer()+" stole a card from "+stealPlayers.get(1).getName(),false);
            this.setVisible(false);
            game_Frame.enableMenu();
        }
    }//GEN-LAST:event_player2Slct2PlrButton1MouseReleased
    private void player1Info2PlrButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_player1Info2PlrButton1MouseReleased
        if (player1Info2PlrButton.isAble()) {
            if(piw!=null&&piw.isVisible()){
                piw.setVisible(false);
            }
            piw = new PlayerInfoWindow(stealPlayers.get(0));
            piw.setVisible(true);
        }
    }//GEN-LAST:event_player1Info2PlrButton1MouseReleased
    private void player1Slct2PlrButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_player1Slct2PlrButton1MouseReleased
        if(player1Slct2PlrButton.isAble()){
            //steal resources from a player
            game_Frame.getCurrPlayer().randomCardFrom(stealPlayers.get(0));
            gameFrame.addToInfo(game_Frame.getCurrPlayer()+" stole a card from "+stealPlayers.get(0).getName(),false);
            this.setVisible(false);
            game_Frame.enableMenu();
        }
    }//GEN-LAST:event_player1Slct2PlrButton1MouseReleased
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ChoosePlayerSteal().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel backgroundLabel1;
    private javax.swing.JPanel onePlayer2Panel;
    private javax.swing.JButton player1Info2PlrButton1;
    private javax.swing.JButton player1Info3PlrButton1;
    private javax.swing.JButton player1Slct2PlrButton1;
    private javax.swing.JButton player1Slct3PlrButton1;
    private javax.swing.JButton player2Info2PlrButton1;
    private javax.swing.JButton player2Info3PlrButton1;
    private javax.swing.JButton player2Slct2PlrButton1;
    private javax.swing.JButton player2Slct3PlrButton1;
    private javax.swing.JButton player3Info3PlrButton1;
    private javax.swing.JButton player3Slct3PlrButton1;
    private javax.swing.JLabel playerOne2pLabel;
    private javax.swing.JLabel playerOne3pLabel;
    private javax.swing.JLabel playerThree3pLabel;
    private javax.swing.JLabel playerTwo2pLabel;
    private javax.swing.JLabel playerTwo3pLabel;
    private javax.swing.JLabel questionLabel;
    private javax.swing.JLabel questionLabel1;
    private javax.swing.JLabel questionLabel2;
    private javax.swing.JLabel questionLabel3;
    private javax.swing.JPanel threePlayer1Panel;
    private javax.swing.JPanel threePlayer2Panel;
    private javax.swing.JPanel threePlayer3Panel;
    private javax.swing.JPanel threePlayerPanel;
    private javax.swing.JPanel twoPlayer2Panel;
    private javax.swing.JPanel twoPlayerPanel;
    // End of variables declaration//GEN-END:variables
}

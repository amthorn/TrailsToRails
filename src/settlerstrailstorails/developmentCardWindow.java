/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * developmentCardWindow.java
 *
 * Created on Oct 13, 2013, 7:56:20 PM
 */
package settlerstrailstorails;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author User 1
 */
public class developmentCardWindow extends javax.swing.JFrame {
    
    ArrayList<DevelopmentCard> dpCards;
    int card1, card2, card3, card4, card5, card6, card7, panel1,panel2,
            panel3,panel4,panel5,panel6,panel7;
    AvaJButton cardOneInfoButton,cardOnePlayButton,cardTwoInfoButton,
            cardTwoPlayButton,cardThreeInfoButton,cardThreePlayButton,
            cardFourInfoButton,cardFourPlayButton,cardFiveInfoButton,
            cardFivePlayButton,cardSixInfoButton,cardSixPlayButton,
            cardSevenInfoButton,cardSevenPlayButton,xButton1;
    gameFrame game_Frame;
    SettlersConfirmDialog scd;
    DevelopmentCardInfo dci;
    buildWindow bw;
    
    /** Creates new form developmentCardWindow */
    developmentCardWindow(ArrayList<DevelopmentCard> dpCards, gameFrame g,buildWindow bw,
            DevelopmentCardInfo d) {
        initComponents();
        game_Frame = g;
        dci=d;
        this.bw=bw;
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(((dim.width/2)-(this.getSize().width/2)), ((dim.height/2)-((this.getSize().height/2)+20)));
        try{
            setIconImage(ImageIO.read(getClass().getResource("/settlerstrailstorails/resources/icon.png")));
        }catch(Exception e){
            
        }
        cardOneInfoButton = new AvaJButton(cardOneInfoButton1);
        cardOnePlayButton = new AvaJButton(cardOnePlayButton1);
        cardTwoInfoButton = new AvaJButton(cardTwoInfoButton1);
        cardTwoPlayButton = new AvaJButton(cardTwoPlayButton1);
        cardThreeInfoButton = new AvaJButton(cardThreeInfoButton1);
        cardThreePlayButton = new AvaJButton(cardThreePlayButton1);
        cardFourInfoButton = new AvaJButton(cardFourInfoButton1);
        cardFourPlayButton = new AvaJButton(cardFourPlayButton1);
        cardFiveInfoButton = new AvaJButton(cardFiveInfoButton1);
        cardFivePlayButton = new AvaJButton(cardFivePlayButton1);
        cardSixInfoButton = new AvaJButton(cardSixInfoButton1);
        cardSixPlayButton = new AvaJButton(cardSixPlayButton1);
        cardSevenInfoButton = new AvaJButton(cardSevenInfoButton1);
        cardSevenPlayButton = new AvaJButton(cardSevenPlayButton1);
        xButton1 = new AvaJButton(xButton);
        this.dpCards=dpCards;
        JPanel[] cardPanel = new JPanel[7];
        cardPanel[0]=cardPanel1;
        cardPanel[1]=cardPanel2;
        cardPanel[2]=cardPanel3;
        cardPanel[3]=cardPanel4;
        cardPanel[4]=cardPanel5;
        cardPanel[5]=cardPanel6;
        cardPanel[6]=cardPanel7;
        cardPanel1.setVisible(false);
        cardPanel2.setVisible(false);
        cardPanel3.setVisible(false);
        cardPanel4.setVisible(false);
        cardPanel5.setVisible(false);
        cardPanel6.setVisible(false);
        cardPanel7.setVisible(false);
        JLabel[] cardLabel = new JLabel[7];
        cardLabel[0]=cardOneLabel;
        cardLabel[1]=cardTwoLabel;
        cardLabel[2]=cardThreeLabel;
        cardLabel[3]=cardFourLabel;
        cardLabel[4]=cardFiveLabel;
        cardLabel[5]=cardSixLabel;
        cardLabel[6]=cardSevenLabel;
        for(int i=0;i<dpCards.size();i++){
            if(dpCards.get(i).getCard()==1){
                card1++;
            }else if(dpCards.get(i).getCard()==2){
                card2++;
            }else if(dpCards.get(i).getCard()==3){
                card3++;
            }else if(dpCards.get(i).getCard()==4){
                card4++;
            }else if(dpCards.get(i).getCard()==5){
                card5++;
            }else if(dpCards.get(i).getCard()==6){
                card6++;
            }else if(dpCards.get(i).getCard()==7){
                card7++;
            }
        }
        int[] card = new int[7];
        card[0]=card1;
        card[1]=card2;
        card[2]=card3;
        card[3]=card4;
        card[4]=card5;
        card[5]=card6;
        card[6]=card7;
        int panelCount=0;
        ArrayList<Integer> intCards=new ArrayList<Integer>();
        for(int i=0;i<card.length;i++){
            if(card[i] != 0) {
                intCards.add(i+1);
            }
        }
        for(int i=0;i<intCards.size();i++){
            if(intCards.get(i)!=null&&intCards.get(i)!=0){
                cardPanel[panelCount].setVisible(true);
                if (intCards.get(i)== 1) {
                    cardLabel[panelCount].setText("Calvary("+card1+")");
                } else if (intCards.get(i)== 2) {
                    cardLabel[panelCount].setText("Engineer("+card2+")");
                } else if (intCards.get(i)== 3) {
                    cardLabel[panelCount].setText("Scout("+card3+")");
                } else if (intCards.get(i)== 4) {
                    cardLabel[panelCount].setText("Mineral Rights("+card4+")");
                } else if (intCards.get(i)== 5) {
                    cardLabel[panelCount].setText("Native Support("+card5+")");
                } else if (intCards.get(i)== 6) {
                    cardLabel[panelCount].setText("Right of Way("+card6+")");
                } else {
                    cardLabel[panelCount].setText("Cattle Drive("+card7+")");
                }
                panelCount++;
            }   
        }
        for(int i=0;i<intCards.size();i++){
            if(panel1==0 && intCards.get(i)!=0){
                panel1=intCards.get(i);
                continue;
            }
            if(panel2==0 && intCards.get(i)!=0){
                panel2=intCards.get(i);
                continue;
            }
            if(panel3==0 && intCards.get(i)!=0){
                panel3=intCards.get(i);
                continue;
            }
            if(panel4==0 && intCards.get(i)!=0){
                panel4=intCards.get(i);
                continue;
            }
            if(panel5==0 && intCards.get(i)!=0){
                panel5=intCards.get(i);
                continue;
            }
            if(panel6==0 && intCards.get(i)!=0){
                panel6=intCards.get(i);
                continue;
            }
            if(panel7==0 && intCards.get(i)!=0){
                panel7=intCards.get(i);
                continue;
            }
        }
        this.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent we) {
                    closeWindows();
                }
            });
    }
    developmentCardWindow() {
        initComponents();
    }
    public void closeWindows(){
        if(scd!=null && scd.isVisible()){
            scd.setVisible(false);
        }
        if(dci!=null && dci.isVisible()){
            dci.setVisible(false);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        xButton = new javax.swing.JButton();
        topLabel = new javax.swing.JLabel();
        cardPanel7 = new javax.swing.JPanel();
        cardSevenLabel = new javax.swing.JLabel();
        cardSevenInfoButton1 = new javax.swing.JButton();
        cardSevenPlayButton1 = new javax.swing.JButton();
        cardPanel6 = new javax.swing.JPanel();
        cardSixLabel = new javax.swing.JLabel();
        cardSixInfoButton1 = new javax.swing.JButton();
        cardSixPlayButton1 = new javax.swing.JButton();
        cardPanel5 = new javax.swing.JPanel();
        cardFiveLabel = new javax.swing.JLabel();
        cardFiveInfoButton1 = new javax.swing.JButton();
        cardFivePlayButton1 = new javax.swing.JButton();
        cardPanel4 = new javax.swing.JPanel();
        cardFourLabel = new javax.swing.JLabel();
        cardFourInfoButton1 = new javax.swing.JButton();
        cardFourPlayButton1 = new javax.swing.JButton();
        cardPanel3 = new javax.swing.JPanel();
        cardThreeLabel = new javax.swing.JLabel();
        cardThreeInfoButton1 = new javax.swing.JButton();
        cardThreePlayButton1 = new javax.swing.JButton();
        cardPanel2 = new javax.swing.JPanel();
        cardTwoLabel = new javax.swing.JLabel();
        cardTwoInfoButton1 = new javax.swing.JButton();
        cardTwoPlayButton1 = new javax.swing.JButton();
        cardPanel1 = new javax.swing.JPanel();
        cardOneLabel = new javax.swing.JLabel();
        cardOneInfoButton1 = new javax.swing.JButton();
        cardOnePlayButton1 = new javax.swing.JButton();
        backgroundLabel = new javax.swing.JLabel();

        setFocusable(false);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        xButton.setBackground(new java.awt.Color(239, 228, 176));
        xButton.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 24));
        xButton.setText("X");
        xButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        xButton.setContentAreaFilled(false);
        xButton.setFocusable(false);
        xButton.setOpaque(true);
        xButton.setPreferredSize(new java.awt.Dimension(75, 25));
        xButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                xButtonMouseReleased(evt);
            }
        });
        xButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xButtonActionPerformed(evt);
            }
        });
        getContentPane().add(xButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 19, 30, 30));

        topLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 24));
        topLabel.setText("Development Cards");
        getContentPane().add(topLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));

        cardPanel7.setBackground(new java.awt.Color(239, 228, 176));
        cardPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cardPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cardSevenLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        cardSevenLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cardPanel7.add(cardSevenLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 13, 155, -1));

        cardSevenInfoButton1.setBackground(new java.awt.Color(239, 228, 176));
        cardSevenInfoButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        cardSevenInfoButton1.setText("View Card");
        cardSevenInfoButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cardSevenInfoButton1.setContentAreaFilled(false);
        cardSevenInfoButton1.setFocusable(false);
        cardSevenInfoButton1.setOpaque(true);
        cardSevenInfoButton1.setPreferredSize(new java.awt.Dimension(75, 25));
        cardSevenInfoButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cardSevenInfoButton1MouseReleased(evt);
            }
        });
        cardPanel7.add(cardSevenInfoButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 12, 80, -1));

        cardSevenPlayButton1.setBackground(new java.awt.Color(239, 228, 176));
        cardSevenPlayButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        cardSevenPlayButton1.setText("Play Card");
        cardSevenPlayButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cardSevenPlayButton1.setContentAreaFilled(false);
        cardSevenPlayButton1.setFocusable(false);
        cardSevenPlayButton1.setOpaque(true);
        cardSevenPlayButton1.setPreferredSize(new java.awt.Dimension(75, 25));
        cardSevenPlayButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cardSevenPlayButton1MouseReleased(evt);
            }
        });
        cardPanel7.add(cardSevenPlayButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(247, 12, 80, -1));

        getContentPane().add(cardPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, 340, 50));

        cardPanel6.setBackground(new java.awt.Color(239, 228, 176));
        cardPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cardPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cardSixLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        cardSixLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cardPanel6.add(cardSixLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 13, 155, -1));

        cardSixInfoButton1.setBackground(new java.awt.Color(239, 228, 176));
        cardSixInfoButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        cardSixInfoButton1.setText("View Card");
        cardSixInfoButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cardSixInfoButton1.setContentAreaFilled(false);
        cardSixInfoButton1.setFocusable(false);
        cardSixInfoButton1.setOpaque(true);
        cardSixInfoButton1.setPreferredSize(new java.awt.Dimension(75, 25));
        cardSixInfoButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cardSixInfoButton1MouseReleased(evt);
            }
        });
        cardPanel6.add(cardSixInfoButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 12, 80, -1));

        cardSixPlayButton1.setBackground(new java.awt.Color(239, 228, 176));
        cardSixPlayButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        cardSixPlayButton1.setText("Play Card");
        cardSixPlayButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cardSixPlayButton1.setContentAreaFilled(false);
        cardSixPlayButton1.setFocusable(false);
        cardSixPlayButton1.setOpaque(true);
        cardSixPlayButton1.setPreferredSize(new java.awt.Dimension(75, 25));
        cardSixPlayButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cardSixPlayButton1MouseReleased(evt);
            }
        });
        cardPanel6.add(cardSixPlayButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(247, 12, 80, -1));

        getContentPane().add(cardPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 385, 340, 50));

        cardPanel5.setBackground(new java.awt.Color(239, 228, 176));
        cardPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cardPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cardFiveLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        cardFiveLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cardPanel5.add(cardFiveLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 13, 155, -1));

        cardFiveInfoButton1.setBackground(new java.awt.Color(239, 228, 176));
        cardFiveInfoButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        cardFiveInfoButton1.setText("View Card");
        cardFiveInfoButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cardFiveInfoButton1.setContentAreaFilled(false);
        cardFiveInfoButton1.setFocusable(false);
        cardFiveInfoButton1.setOpaque(true);
        cardFiveInfoButton1.setPreferredSize(new java.awt.Dimension(75, 25));
        cardFiveInfoButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cardFiveInfoButton1MouseReleased(evt);
            }
        });
        cardPanel5.add(cardFiveInfoButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 12, 80, -1));

        cardFivePlayButton1.setBackground(new java.awt.Color(239, 228, 176));
        cardFivePlayButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        cardFivePlayButton1.setText("Play Card");
        cardFivePlayButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cardFivePlayButton1.setContentAreaFilled(false);
        cardFivePlayButton1.setFocusable(false);
        cardFivePlayButton1.setOpaque(true);
        cardFivePlayButton1.setPreferredSize(new java.awt.Dimension(75, 25));
        cardFivePlayButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cardFivePlayButton1MouseReleased(evt);
            }
        });
        cardPanel5.add(cardFivePlayButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(247, 12, 80, -1));

        getContentPane().add(cardPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 340, 50));

        cardPanel4.setBackground(new java.awt.Color(239, 228, 176));
        cardPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cardPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cardFourLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        cardFourLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cardPanel4.add(cardFourLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 13, 155, -1));

        cardFourInfoButton1.setBackground(new java.awt.Color(239, 228, 176));
        cardFourInfoButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        cardFourInfoButton1.setText("View Card");
        cardFourInfoButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cardFourInfoButton1.setContentAreaFilled(false);
        cardFourInfoButton1.setFocusable(false);
        cardFourInfoButton1.setOpaque(true);
        cardFourInfoButton1.setPreferredSize(new java.awt.Dimension(75, 25));
        cardFourInfoButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cardFourInfoButton1MouseReleased(evt);
            }
        });
        cardPanel4.add(cardFourInfoButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 12, 80, -1));

        cardFourPlayButton1.setBackground(new java.awt.Color(239, 228, 176));
        cardFourPlayButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        cardFourPlayButton1.setText("Play Card");
        cardFourPlayButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cardFourPlayButton1.setContentAreaFilled(false);
        cardFourPlayButton1.setFocusable(false);
        cardFourPlayButton1.setOpaque(true);
        cardFourPlayButton1.setPreferredSize(new java.awt.Dimension(75, 25));
        cardFourPlayButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cardFourPlayButton1MouseReleased(evt);
            }
        });
        cardPanel4.add(cardFourPlayButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(247, 12, 80, -1));

        getContentPane().add(cardPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 255, 340, 50));

        cardPanel3.setBackground(new java.awt.Color(239, 228, 176));
        cardPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cardPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cardThreeLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18));
        cardThreeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cardPanel3.add(cardThreeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 13, 155, -1));

        cardThreeInfoButton1.setBackground(new java.awt.Color(239, 228, 176));
        cardThreeInfoButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        cardThreeInfoButton1.setText("View Card");
        cardThreeInfoButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cardThreeInfoButton1.setContentAreaFilled(false);
        cardThreeInfoButton1.setFocusable(false);
        cardThreeInfoButton1.setOpaque(true);
        cardThreeInfoButton1.setPreferredSize(new java.awt.Dimension(75, 25));
        cardThreeInfoButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cardThreeInfoButton1MouseReleased(evt);
            }
        });
        cardPanel3.add(cardThreeInfoButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 12, 80, -1));

        cardThreePlayButton1.setBackground(new java.awt.Color(239, 228, 176));
        cardThreePlayButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        cardThreePlayButton1.setText("Play Card");
        cardThreePlayButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cardThreePlayButton1.setContentAreaFilled(false);
        cardThreePlayButton1.setFocusable(false);
        cardThreePlayButton1.setOpaque(true);
        cardThreePlayButton1.setPreferredSize(new java.awt.Dimension(75, 25));
        cardThreePlayButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cardThreePlayButton1MouseReleased(evt);
            }
        });
        cardPanel3.add(cardThreePlayButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(247, 12, 80, -1));

        getContentPane().add(cardPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 340, 50));

        cardPanel2.setBackground(new java.awt.Color(239, 228, 176));
        cardPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cardPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cardTwoLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18));
        cardTwoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cardPanel2.add(cardTwoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 13, 155, -1));

        cardTwoInfoButton1.setBackground(new java.awt.Color(239, 228, 176));
        cardTwoInfoButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        cardTwoInfoButton1.setText("View Card");
        cardTwoInfoButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cardTwoInfoButton1.setContentAreaFilled(false);
        cardTwoInfoButton1.setFocusable(false);
        cardTwoInfoButton1.setOpaque(true);
        cardTwoInfoButton1.setPreferredSize(new java.awt.Dimension(75, 25));
        cardTwoInfoButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cardTwoInfoButton1MouseReleased(evt);
            }
        });
        cardPanel2.add(cardTwoInfoButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 12, 80, -1));

        cardTwoPlayButton1.setBackground(new java.awt.Color(239, 228, 176));
        cardTwoPlayButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        cardTwoPlayButton1.setText("Play Card");
        cardTwoPlayButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cardTwoPlayButton1.setContentAreaFilled(false);
        cardTwoPlayButton1.setFocusable(false);
        cardTwoPlayButton1.setOpaque(true);
        cardTwoPlayButton1.setPreferredSize(new java.awt.Dimension(75, 25));
        cardTwoPlayButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cardTwoPlayButton1MouseReleased(evt);
            }
        });
        cardPanel2.add(cardTwoPlayButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(247, 12, 80, -1));

        getContentPane().add(cardPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 125, 340, 50));

        cardPanel1.setBackground(new java.awt.Color(239, 228, 176));
        cardPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cardPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cardOneLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18));
        cardOneLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cardPanel1.add(cardOneLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 13, 155, -1));

        cardOneInfoButton1.setBackground(new java.awt.Color(239, 228, 176));
        cardOneInfoButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        cardOneInfoButton1.setText("View Card");
        cardOneInfoButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cardOneInfoButton1.setContentAreaFilled(false);
        cardOneInfoButton1.setFocusable(false);
        cardOneInfoButton1.setOpaque(true);
        cardOneInfoButton1.setPreferredSize(new java.awt.Dimension(75, 25));
        cardOneInfoButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cardOneInfoButton1MouseReleased(evt);
            }
        });
        cardPanel1.add(cardOneInfoButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 12, 80, -1));

        cardOnePlayButton1.setBackground(new java.awt.Color(239, 228, 176));
        cardOnePlayButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        cardOnePlayButton1.setText("Play Card");
        cardOnePlayButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cardOnePlayButton1.setContentAreaFilled(false);
        cardOnePlayButton1.setFocusable(false);
        cardOnePlayButton1.setOpaque(true);
        cardOnePlayButton1.setPreferredSize(new java.awt.Dimension(75, 25));
        cardOnePlayButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cardOnePlayButton1MouseReleased(evt);
            }
        });
        cardPanel1.add(cardOnePlayButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(247, 12, 80, -1));

        getContentPane().add(cardPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 340, 50));

        backgroundLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/MainMenuBackground.png"))); // NOI18N
        getContentPane().add(backgroundLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cardOneInfoButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cardOneInfoButton1MouseReleased
        if(cardOneInfoButton.isAble()){
            closeWindows();
            dci = new DevelopmentCardInfo(panel1);
            dci.setVisible(true);
        }
}//GEN-LAST:event_cardOneInfoButton1MouseReleased
    private void cardOnePlayButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cardOnePlayButton1MouseReleased
        if(cardOnePlayButton.isAble()) {
            if (cardOneLabel.getText().startsWith("Calvary")) {
                for (int i = 0; i < dpCards.size(); i++) {
                    if (dpCards.get(i).getPlayable() && dpCards.get(i).getCard() == 1) {
                        if (!game_Frame.getCurrPlayer().getPlayedDPCardThisTurn()) {
                            if (game_Frame.getRolled()) {
                                closeWindows();
                                if (bw != null && bw.isVisible()) {
                                    bw.setVisible(false);
                                }
                                dpCards.get(i).playCard();
                                this.setVisible(false);
                                return;
                            } else {
                                //must roll first
                                closeWindows();
                                scd = new SettlersConfirmDialog(19);
                                scd.setVisible(true);
                                return;
                            }
                        } else {
                            //already played a dp card this turn
                            closeWindows();
                            scd = new SettlersConfirmDialog(17);
                            scd.setVisible(true);
                            return;
                        }
                    } else if (!dpCards.get(i).getPlayable() && dpCards.get(i).getCard() == 1) {
                        //you cannot play it on the same turn it's bought                 
                        closeWindows();
                        scd=new SettlersConfirmDialog(16);
                        scd.setVisible(true);
                            return;
                    }
                }
            } else if (cardOneLabel.getText().startsWith("Engineer")) {
                for (int i = 0; i < dpCards.size(); i++) {
                    if (dpCards.get(i).getPlayable() && dpCards.get(i).getCard() == 2) {
                        if (!game_Frame.getCurrPlayer().getPlayedDPCardThisTurn()) {
                            if (game_Frame.getRolled()) {
                                if (!game_Frame.getCurrPlayer().maxNumberOfCitiesBuilt()) {
                                    closeWindows();
                                    if (bw != null && bw.isVisible()) {
                                        bw.setVisible(false);
                                    }
                                    dpCards.get(i).playCard();
                                    this.setVisible(false);
                                    return;
                                } else {
                                    //you have max rails already error
                                    if (scd != null && scd.isVisible()) {
                                        scd.setVisible(false);
                                    }
                                    scd = new SettlersConfirmDialog(12);
                                    scd.setVisible(true);
                                }
                            } else {
                                //must roll first
                                closeWindows();
                                scd = new SettlersConfirmDialog(19);
                                scd.setVisible(true);
                                return;
                            }
                        } else {
                            //already played a dp card this turn
                            closeWindows();
                            scd = new SettlersConfirmDialog(17);
                            scd.setVisible(true);
                            return;
                        }
                    } else if (!dpCards.get(i).getPlayable() && dpCards.get(i).getCard() == 2){
                        //you cannot play it on the same turn it's bought                      
                        closeWindows();
                        scd=new SettlersConfirmDialog(16);
                        scd.setVisible(true);
                            return;
                    }
                }
            } else if (cardOneLabel.getText().startsWith("Scout")) {
                for (int i = 0; i < dpCards.size(); i++) {
                    if (dpCards.get(i).getPlayable() && dpCards.get(i).getCard() == 3) {
                        if (!game_Frame.getCurrPlayer().getPlayedDPCardThisTurn()) {
                            if (game_Frame.getRolled()) {
                                scd = new SettlersConfirmDialog(this, bw, dpCards.get(i), game_Frame);
                                scd.setVisible(true);
                                return;
                            } else {
                                //must roll first
                                closeWindows();
                                scd = new SettlersConfirmDialog(19);
                                scd.setVisible(true);
                                return;
                            }
                        } else {
                            //already played a dp card this turn
                            closeWindows();
                            scd = new SettlersConfirmDialog(17);
                            scd.setVisible(true);
                            return;
                        }
                    }else if(!dpCards.get(i).getPlayable() && dpCards.get(i).getCard() == 3){
                        //you cannot play it on the same turn it's bought                       
                        closeWindows();
                        scd=new SettlersConfirmDialog(16);
                        scd.setVisible(true);
                            return;
                    }
                }
            } else if (cardOneLabel.getText().startsWith("Mineral Rights")) {
                for (int i = 0; i < dpCards.size(); i++) {
                    if (dpCards.get(i).getPlayable() && dpCards.get(i).getCard() == 4) {
                        if (!game_Frame.getCurrPlayer().getPlayedDPCardThisTurn()) {
                            if (game_Frame.getRolled()) {
                                closeWindows();
                                if (bw != null && bw.isVisible()) {
                                    bw.setVisible(false);
                                }
                                dpCards.get(i).playCard();
                                this.setVisible(false);
                                return;
                            } else {
                                //must roll first
                                closeWindows();
                                scd = new SettlersConfirmDialog(19);
                                scd.setVisible(true);
                                return;
                            }
                        } else {
                            //already played a dp card this turn
                            closeWindows();
                            scd = new SettlersConfirmDialog(17);
                            scd.setVisible(true);
                            return;
                        }
                    } else if (!dpCards.get(i).getPlayable() && dpCards.get(i).getCard() == 4) {
                        //you cannot play it on the same turn it's bought                     
                        closeWindows();
                        scd=new SettlersConfirmDialog(16);
                        scd.setVisible(true);
                            return;
                    }
                }
            } else if (cardOneLabel.getText().startsWith("Native Support")) {
                for (int i = 0; i < dpCards.size(); i++) {
                    if (dpCards.get(i).getPlayable() && dpCards.get(i).getCard() == 5) {
                        if (!game_Frame.getCurrPlayer().getPlayedDPCardThisTurn()) {
                            if (!game_Frame.getRolled()) {
                                if (!game_Frame.hasRolled()) {
                                    closeWindows();
                                    if (bw != null && bw.isVisible()) {
                                        bw.setVisible(false);
                                    }
                                    dpCards.get(i).playCard();
                                    this.setVisible(false);
                                    return;
                                } else {
                                    //must roll first
                                    closeWindows();
                                    scd = new SettlersConfirmDialog(19);
                                    scd.setVisible(true);
                                    return;
                                }
                            } else {
                                //card can only be played before roll                
                                closeWindows();
                                scd = new SettlersConfirmDialog(18);
                                scd.setVisible(true);
                                return;
                            }
                        } else {
                            //already played a dp card this turn
                            closeWindows();
                            scd = new SettlersConfirmDialog(17);
                            scd.setVisible(true);
                            return;
                        }
                    } else if (!dpCards.get(i).getPlayable() && dpCards.get(i).getCard() == 5){
                        //you cannot play it on the same turn it's bought                     
                        closeWindows();
                        scd=new SettlersConfirmDialog(16);
                        scd.setVisible(true);
                            return;
                    }
                }
            } else if (cardOneLabel.getText().startsWith("Right of Way")) {
                for (int i = 0; i < dpCards.size(); i++) {
                    if (dpCards.get(i).getPlayable() && dpCards.get(i).getCard() == 6) {
                        if (!game_Frame.getCurrPlayer().getPlayedDPCardThisTurn()) {
                            if (game_Frame.getRolled()) {
                                closeWindows();
                                if (bw != null && bw.isVisible()) {
                                    bw.setVisible(false);
                                }
                                dpCards.get(i).playCard();
                                this.setVisible(false);
                                return;
                            } else {
                                //must roll first
                                closeWindows();
                                scd = new SettlersConfirmDialog(19);
                                scd.setVisible(true);
                                return;
                            }
                        } else {
                            //already played a dp card this turn
                            closeWindows();
                            scd = new SettlersConfirmDialog(17);
                            scd.setVisible(true);
                            return;
                        }
                    } else if (!dpCards.get(i).getPlayable() && dpCards.get(i).getCard() == 6){
                        //you cannot play it on the same turn it's bought                     
                        closeWindows();
                        scd=new SettlersConfirmDialog(16);
                        scd.setVisible(true);
                            return;
                    }
                }
            } else {
                for (int i = 0; i < dpCards.size(); i++) {
                    if (dpCards.get(i).getPlayable() && dpCards.get(i).getCard() == 7
                            && game_Frame.getCurrPlayer().getCattle() > 0) {
                        if (!game_Frame.getCurrPlayer().getPlayedDPCardThisTurn()) {
                            if (game_Frame.getRolled()) {
                                closeWindows();
                                if (bw != null && bw.isVisible()) {
                                    bw.setVisible(false);
                                }
                                dpCards.get(i).playCard();
                                this.setVisible(false);
                                return;
                            } else {
                                //must roll first
                                closeWindows();
                                scd = new SettlersConfirmDialog(19);
                                scd.setVisible(true);
                                return;
                            }
                        } else {
                            //already played a dp card this turn
                            closeWindows();
                            scd = new SettlersConfirmDialog(17);
                            scd.setVisible(true);
                            return;
                        }
                    }else if(!dpCards.get(i).getPlayable() && dpCards.get(i).getCard() == 7){
                        //you cannot play it on the same turn it's bought                     
                        closeWindows();
                        scd=new SettlersConfirmDialog(16);
                        scd.setVisible(true);
                            return;
                    }
                }
            }
        }
    }//GEN-LAST:event_cardOnePlayButton1MouseReleased
    private void cardFiveInfoButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cardFiveInfoButton1MouseReleased
        if(cardFiveInfoButton.isAble()){
            closeWindows();
            dci = new DevelopmentCardInfo(panel5);
            dci.setVisible(true);
        }
    }//GEN-LAST:event_cardFiveInfoButton1MouseReleased
    private void cardFivePlayButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cardFivePlayButton1MouseReleased
        if(cardFivePlayButton.isAble()){
            if (cardFiveLabel.getText().startsWith("Native Support")) {
                for (int i = 0; i < dpCards.size(); i++) {
                    if (dpCards.get(i).getPlayable() && dpCards.get(i).getCard() == 5) {
                        if (!game_Frame.getCurrPlayer().getPlayedDPCardThisTurn()) {
                            if (!game_Frame.getRolled()) {
                                if (!game_Frame.hasRolled()) {
                                    closeWindows();
                                    if (bw != null && bw.isVisible()) {
                                        bw.setVisible(false);
                                    }
                                    dpCards.get(i).playCard();
                                    this.setVisible(false);
                                    return;
                                } else {
                                    //must roll first
                                    closeWindows();
                                    scd = new SettlersConfirmDialog(19);
                                    scd.setVisible(true);
                                    return;
                                }
                            } else {
                                //card can only be played before roll                
                                closeWindows();
                                scd = new SettlersConfirmDialog(18);
                                scd.setVisible(true);
                                return;
                            }
                        } else {
                            //already played a dp card this turn
                            closeWindows();
                            scd = new SettlersConfirmDialog(17);
                            scd.setVisible(true);
                            return;
                        }
                    } else if (!dpCards.get(i).getPlayable() && dpCards.get(i).getCard() == 5){
                        //you cannot play it on the same turn it's bought                     
                        closeWindows();
                        scd=new SettlersConfirmDialog(16);
                        scd.setVisible(true);
                            return;
                    }
                }
            } else if (cardFiveLabel.getText().startsWith("Right of Way")) {
                for (int i = 0; i < dpCards.size(); i++) {
                    if (dpCards.get(i).getPlayable() && dpCards.get(i).getCard() == 6) {
                        if (!game_Frame.getCurrPlayer().getPlayedDPCardThisTurn()) {
                            if (game_Frame.getRolled()) {
                                closeWindows();
                                if (bw != null && bw.isVisible()) {
                                    bw.setVisible(false);
                                }
                                dpCards.get(i).playCard();
                                this.setVisible(false);
                                return;
                            } else {
                                //must roll first
                                closeWindows();
                                scd = new SettlersConfirmDialog(19);
                                scd.setVisible(true);
                                return;
                            }
                        } else {
                            //already played a dp card this turn
                            closeWindows();
                            scd = new SettlersConfirmDialog(17);
                            scd.setVisible(true);
                            return;
                        }
                    } else if (!dpCards.get(i).getPlayable() && dpCards.get(i).getCard() == 6){
                        //you cannot play it on the same turn it's bought                     
                        closeWindows();
                        scd=new SettlersConfirmDialog(16);
                        scd.setVisible(true);
                            return;
                    }
                }
            } else {
                for (int i = 0; i < dpCards.size(); i++) {
                    if (dpCards.get(i).getPlayable() && dpCards.get(i).getCard() == 7
                            && game_Frame.getCurrPlayer().getCattle() > 0) {
                        if (!game_Frame.getCurrPlayer().getPlayedDPCardThisTurn()) {
                            if (game_Frame.getRolled()) {
                                closeWindows();
                                if (bw != null && bw.isVisible()) {
                                    bw.setVisible(false);
                                }
                                dpCards.get(i).playCard();
                                this.setVisible(false);
                                return;
                            } else {
                                //must roll first
                                closeWindows();
                                scd = new SettlersConfirmDialog(19);
                                scd.setVisible(true);
                                return;
                            }
                        } else {
                            //already played a dp card this turn
                            closeWindows();
                            scd = new SettlersConfirmDialog(17);
                            scd.setVisible(true);
                            return;
                        }
                    }else if(!dpCards.get(i).getPlayable() && dpCards.get(i).getCard() == 7){
                        //you cannot play it on the same turn it's bought                     
                        closeWindows();
                        scd=new SettlersConfirmDialog(16);
                        scd.setVisible(true);
                            return;
                    }
                }
            }
        }
    }//GEN-LAST:event_cardFivePlayButton1MouseReleased
    private void cardTwoInfoButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cardTwoInfoButton1MouseReleased
        if(cardTwoInfoButton.isAble()){
            closeWindows();
            dci = new DevelopmentCardInfo(panel2);
            dci.setVisible(true);
        }
    }//GEN-LAST:event_cardTwoInfoButton1MouseReleased
    private void cardTwoPlayButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cardTwoPlayButton1MouseReleased
        if(cardTwoPlayButton.isAble()){
            if (cardTwoLabel.getText().startsWith("Engineer")) {
                for (int i = 0; i < dpCards.size(); i++) {
                    if (dpCards.get(i).getPlayable() && dpCards.get(i).getCard() == 2) {
                        if (!game_Frame.getCurrPlayer().getPlayedDPCardThisTurn()) {
                            if (game_Frame.getRolled()) {
                                if (!game_Frame.getCurrPlayer().maxNumberOfCitiesBuilt()) {
                                    closeWindows();
                                    if (bw != null && bw.isVisible()) {
                                        bw.setVisible(false);
                                    }
                                    dpCards.get(i).playCard();
                                    this.setVisible(false);
                                    return;
                                } else {
                                    //you have max rails already error
                                    if (scd != null && scd.isVisible()) {
                                        scd.setVisible(false);
                                    }
                                    scd = new SettlersConfirmDialog(12);
                                    scd.setVisible(true);
                                }
                            } else {
                                //must roll first
                                closeWindows();
                                scd = new SettlersConfirmDialog(19);
                                scd.setVisible(true);
                                return;
                            }
                        } else {
                            //already played a dp card this turn
                            closeWindows();
                            scd = new SettlersConfirmDialog(17);
                            scd.setVisible(true);
                            return;
                        }
                    } else if (!dpCards.get(i).getPlayable() && dpCards.get(i).getCard() == 2){
                        //you cannot play it on the same turn it's bought                      
                        closeWindows();
                        scd=new SettlersConfirmDialog(16);
                        scd.setVisible(true);
                            return;
                    }
                }
            } else if (cardTwoLabel.getText().startsWith("Scout")) {
                for (int i = 0; i < dpCards.size(); i++) {
                    if (dpCards.get(i).getPlayable() && dpCards.get(i).getCard() == 3) {
                        if (!game_Frame.getCurrPlayer().getPlayedDPCardThisTurn()) {
                            if (game_Frame.getRolled()) {
                                scd = new SettlersConfirmDialog(this, bw, dpCards.get(i), game_Frame);
                                scd.setVisible(true);
                                return;
                            } else {
                                //must roll first
                                closeWindows();
                                scd = new SettlersConfirmDialog(19);
                                scd.setVisible(true);
                                return;
                            }
                        } else {
                            //already played a dp card this turn
                            closeWindows();
                            scd = new SettlersConfirmDialog(17);
                            scd.setVisible(true);
                            return;
                        }
                    }else if(!dpCards.get(i).getPlayable() && dpCards.get(i).getCard() == 3){
                        //you cannot play it on the same turn it's bought                       
                        closeWindows();
                        scd=new SettlersConfirmDialog(16);
                        scd.setVisible(true);
                            return;
                    }
                }
            } else if (cardTwoLabel.getText().startsWith("Mineral Rights")) {
                for (int i = 0; i < dpCards.size(); i++) {
                    if (dpCards.get(i).getPlayable() && dpCards.get(i).getCard() == 4) {
                        if (!game_Frame.getCurrPlayer().getPlayedDPCardThisTurn()) {
                            if (game_Frame.getRolled()) {
                                closeWindows();
                                if (bw != null && bw.isVisible()) {
                                    bw.setVisible(false);
                                }
                                dpCards.get(i).playCard();
                                this.setVisible(false);
                                return;
                            } else {
                                //must roll first
                                closeWindows();
                                scd = new SettlersConfirmDialog(19);
                                scd.setVisible(true);
                                return;
                            }
                        } else {
                            //already played a dp card this turn
                            closeWindows();
                            scd = new SettlersConfirmDialog(17);
                            scd.setVisible(true);
                            return;
                        }
                    } else if (!dpCards.get(i).getPlayable() && dpCards.get(i).getCard() == 4) {
                        //you cannot play it on the same turn it's bought                     
                        closeWindows();
                        scd=new SettlersConfirmDialog(16);
                        scd.setVisible(true);
                            return;
                    }
                }
            } else if (cardTwoLabel.getText().startsWith("Native Support")) {
                for (int i = 0; i < dpCards.size(); i++) {
                    if (dpCards.get(i).getPlayable() && dpCards.get(i).getCard() == 5) {
                        if (!game_Frame.getCurrPlayer().getPlayedDPCardThisTurn()) {
                            if (!game_Frame.getRolled()) {
                                if (!game_Frame.hasRolled()) {
                                    closeWindows();
                                    if (bw != null && bw.isVisible()) {
                                        bw.setVisible(false);
                                    }
                                    dpCards.get(i).playCard();
                                    this.setVisible(false);
                                    return;
                                } else {
                                    //must roll first
                                    closeWindows();
                                    scd = new SettlersConfirmDialog(19);
                                    scd.setVisible(true);
                                    return;
                                }
                            } else {
                                //card can only be played before roll                
                                closeWindows();
                                scd = new SettlersConfirmDialog(18);
                                scd.setVisible(true);
                                return;
                            }
                        } else {
                            //already played a dp card this turn
                            closeWindows();
                            scd = new SettlersConfirmDialog(17);
                            scd.setVisible(true);
                            return;
                        }
                    } else if (!dpCards.get(i).getPlayable() && dpCards.get(i).getCard() == 5){
                        //you cannot play it on the same turn it's bought                     
                        closeWindows();
                        scd=new SettlersConfirmDialog(16);
                        scd.setVisible(true);
                            return;
                    }
                }
            } else if (cardTwoLabel.getText().startsWith("Right of Way")) {
                for (int i = 0; i < dpCards.size(); i++) {
                    if (dpCards.get(i).getPlayable() && dpCards.get(i).getCard() == 6) {
                        if (!game_Frame.getCurrPlayer().getPlayedDPCardThisTurn()) {
                            if (game_Frame.getRolled()) {
                                closeWindows();
                                if (bw != null && bw.isVisible()) {
                                    bw.setVisible(false);
                                }
                                dpCards.get(i).playCard();
                                this.setVisible(false);
                                return;
                            } else {
                                //must roll first
                                closeWindows();
                                scd = new SettlersConfirmDialog(19);
                                scd.setVisible(true);
                                return;
                            }
                        } else {
                            //already played a dp card this turn
                            closeWindows();
                            scd = new SettlersConfirmDialog(17);
                            scd.setVisible(true);
                            return;
                        }
                    } else if (!dpCards.get(i).getPlayable() && dpCards.get(i).getCard() == 6){
                        //you cannot play it on the same turn it's bought                     
                        closeWindows();
                        scd=new SettlersConfirmDialog(16);
                        scd.setVisible(true);
                            return;
                    }
                }
            } else {
                for (int i = 0; i < dpCards.size(); i++) {
                    if (dpCards.get(i).getPlayable() && dpCards.get(i).getCard() == 7
                            && game_Frame.getCurrPlayer().getCattle() > 0) {
                        if (!game_Frame.getCurrPlayer().getPlayedDPCardThisTurn()) {
                            if (game_Frame.getRolled()) {
                                closeWindows();
                                if (bw != null && bw.isVisible()) {
                                    bw.setVisible(false);
                                }
                                dpCards.get(i).playCard();
                                this.setVisible(false);
                                return;
                            } else {
                                //must roll first
                                closeWindows();
                                scd = new SettlersConfirmDialog(19);
                                scd.setVisible(true);
                                return;
                            }
                        } else {
                            //already played a dp card this turn
                            closeWindows();
                            scd = new SettlersConfirmDialog(17);
                            scd.setVisible(true);
                            return;
                        }
                    }else if(!dpCards.get(i).getPlayable() && dpCards.get(i).getCard() == 7){
                        //you cannot play it on the same turn it's bought                     
                        closeWindows();
                        scd=new SettlersConfirmDialog(16);
                        scd.setVisible(true);
                            return;
                    }
                }
            }
        }
    }//GEN-LAST:event_cardTwoPlayButton1MouseReleased
    private void cardThreeInfoButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cardThreeInfoButton1MouseReleased
        if(cardThreeInfoButton.isAble()){
            closeWindows();
            dci = new DevelopmentCardInfo(panel3);
            dci.setVisible(true);
        }
    }//GEN-LAST:event_cardThreeInfoButton1MouseReleased
    private void cardThreePlayButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cardThreePlayButton1MouseReleased
        if(cardThreePlayButton.isAble()){
            if (cardThreeLabel.getText().startsWith("Scout")) {
                for (int i = 0; i < dpCards.size(); i++) {
                    if (dpCards.get(i).getPlayable() && dpCards.get(i).getCard() == 3) {
                        if (!game_Frame.getCurrPlayer().getPlayedDPCardThisTurn()) {
                            if (game_Frame.getRolled()) {
                                scd = new SettlersConfirmDialog(this, bw, dpCards.get(i), game_Frame);
                                scd.setVisible(true);
                                return;
                            } else {
                                //must roll first
                                closeWindows();
                                scd = new SettlersConfirmDialog(19);
                                scd.setVisible(true);
                                return;
                            }
                        } else {
                            //already played a dp card this turn
                            closeWindows();
                            scd = new SettlersConfirmDialog(17);
                            scd.setVisible(true);
                            return;
                        }
                    }else if(!dpCards.get(i).getPlayable() && dpCards.get(i).getCard() == 3){
                        //you cannot play it on the same turn it's bought                       
                        closeWindows();
                        scd=new SettlersConfirmDialog(16);
                        scd.setVisible(true);
                            return;
                    }
                }
            } else if (cardThreeLabel.getText().startsWith("Mineral Rights")) {
                for (int i = 0; i < dpCards.size(); i++) {
                    if (dpCards.get(i).getPlayable() && dpCards.get(i).getCard() == 4) {
                        if (!game_Frame.getCurrPlayer().getPlayedDPCardThisTurn()) {
                            if (game_Frame.getRolled()) {
                                closeWindows();
                                if (bw != null && bw.isVisible()) {
                                    bw.setVisible(false);
                                }
                                dpCards.get(i).playCard();
                                this.setVisible(false);
                                return;
                            } else {
                                //must roll first
                                closeWindows();
                                scd = new SettlersConfirmDialog(19);
                                scd.setVisible(true);
                                return;
                            }
                        } else {
                            //already played a dp card this turn
                            closeWindows();
                            scd = new SettlersConfirmDialog(17);
                            scd.setVisible(true);
                            return;
                        }
                    } else if (!dpCards.get(i).getPlayable() && dpCards.get(i).getCard() == 4) {
                        //you cannot play it on the same turn it's bought                     
                        closeWindows();
                        scd=new SettlersConfirmDialog(16);
                        scd.setVisible(true);
                            return;
                    }
                }
            } else if (cardThreeLabel.getText().startsWith("Native Support")) {
                for (int i = 0; i < dpCards.size(); i++) {
                    if (dpCards.get(i).getPlayable() && dpCards.get(i).getCard() == 5) {
                        if (!game_Frame.getCurrPlayer().getPlayedDPCardThisTurn()) {
                            if (!game_Frame.getRolled()) {
                                if (!game_Frame.hasRolled()) {
                                    closeWindows();
                                    if (bw != null && bw.isVisible()) {
                                        bw.setVisible(false);
                                    }
                                    dpCards.get(i).playCard();
                                    this.setVisible(false);
                                    return;
                                } else {
                                    //must roll first
                                    closeWindows();
                                    scd = new SettlersConfirmDialog(19);
                                    scd.setVisible(true);
                                    return;
                                }
                            } else {
                                //card can only be played before roll                
                                closeWindows();
                                scd = new SettlersConfirmDialog(18);
                                scd.setVisible(true);
                                return;
                            }
                        } else {
                            //already played a dp card this turn
                            closeWindows();
                            scd = new SettlersConfirmDialog(17);
                            scd.setVisible(true);
                            return;
                        }
                    } else if (!dpCards.get(i).getPlayable() && dpCards.get(i).getCard() == 5){
                        //you cannot play it on the same turn it's bought                     
                        closeWindows();
                        scd=new SettlersConfirmDialog(16);
                        scd.setVisible(true);
                            return;
                    }
                }
            } else if (cardThreeLabel.getText().startsWith("Right of Way")) {
                for (int i = 0; i < dpCards.size(); i++) {
                    if (dpCards.get(i).getPlayable() && dpCards.get(i).getCard() == 6) {
                        if (!game_Frame.getCurrPlayer().getPlayedDPCardThisTurn()) {
                            if (game_Frame.getRolled()) {
                                closeWindows();
                                if (bw != null && bw.isVisible()) {
                                    bw.setVisible(false);
                                }
                                dpCards.get(i).playCard();
                                this.setVisible(false);
                                return;
                            } else {
                                //must roll first
                                closeWindows();
                                scd = new SettlersConfirmDialog(19);
                                scd.setVisible(true);
                                return;
                            }
                        } else {
                            //already played a dp card this turn
                            closeWindows();
                            scd = new SettlersConfirmDialog(17);
                            scd.setVisible(true);
                            return;
                        }
                    } else if (!dpCards.get(i).getPlayable() && dpCards.get(i).getCard() == 6){
                        //you cannot play it on the same turn it's bought                     
                        closeWindows();
                        scd=new SettlersConfirmDialog(16);
                        scd.setVisible(true);
                            return;
                    }
                }
            } else {
                for (int i = 0; i < dpCards.size(); i++) {
                    if (dpCards.get(i).getPlayable() && dpCards.get(i).getCard() == 7
                            && game_Frame.getCurrPlayer().getCattle() > 0) {
                        if (!game_Frame.getCurrPlayer().getPlayedDPCardThisTurn()) {
                            if (game_Frame.getRolled()) {
                                closeWindows();
                                if (bw != null && bw.isVisible()) {
                                    bw.setVisible(false);
                                }
                                dpCards.get(i).playCard();
                                this.setVisible(false);
                                return;
                            } else {
                                //must roll first
                                closeWindows();
                                scd = new SettlersConfirmDialog(19);
                                scd.setVisible(true);
                                return;
                            }
                        } else {
                            //already played a dp card this turn
                            closeWindows();
                            scd = new SettlersConfirmDialog(17);
                            scd.setVisible(true);
                            return;
                        }
                    }else if(!dpCards.get(i).getPlayable() && dpCards.get(i).getCard() == 7){
                        //you cannot play it on the same turn it's bought                     
                        closeWindows();
                        scd=new SettlersConfirmDialog(16);
                        scd.setVisible(true);
                            return;
                    }
                }
            }
        }
    }//GEN-LAST:event_cardThreePlayButton1MouseReleased
    private void cardFourInfoButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cardFourInfoButton1MouseReleased
        if(cardFourInfoButton.isAble()){
            closeWindows();
            dci = new DevelopmentCardInfo(panel4);
            dci.setVisible(true);
        }
    }//GEN-LAST:event_cardFourInfoButton1MouseReleased
    private void cardFourPlayButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cardFourPlayButton1MouseReleased
        if(cardFourPlayButton.isAble()){
            if (cardFourLabel.getText().startsWith("Mineral Rights")) {
                for (int i = 0; i < dpCards.size(); i++) {
                    if (dpCards.get(i).getPlayable() && dpCards.get(i).getCard() == 4) {
                        if (!game_Frame.getCurrPlayer().getPlayedDPCardThisTurn()) {
                            if (game_Frame.getRolled()) {
                                closeWindows();
                                if (bw != null && bw.isVisible()) {
                                    bw.setVisible(false);
                                }
                                dpCards.get(i).playCard();
                                this.setVisible(false);
                                return;
                            } else {
                                //must roll first
                                closeWindows();
                                scd = new SettlersConfirmDialog(19);
                                scd.setVisible(true);
                                return;
                            }
                        } else {
                            //already played a dp card this turn
                            closeWindows();
                            scd = new SettlersConfirmDialog(17);
                            scd.setVisible(true);
                            return;
                        }
                    } else if (!dpCards.get(i).getPlayable() && dpCards.get(i).getCard() == 4) {
                        //you cannot play it on the same turn it's bought                     
                        closeWindows();
                        scd=new SettlersConfirmDialog(16);
                        scd.setVisible(true);
                            return;
                    }
                }
            } else if (cardFourLabel.getText().startsWith("Native Support")) {
                for (int i = 0; i < dpCards.size(); i++) {
                    if (dpCards.get(i).getPlayable() && dpCards.get(i).getCard() == 5) {
                        if (!game_Frame.getCurrPlayer().getPlayedDPCardThisTurn()) {
                            if (!game_Frame.getRolled()) {
                                if (!game_Frame.hasRolled()) {
                                    closeWindows();
                                    if (bw != null && bw.isVisible()) {
                                        bw.setVisible(false);
                                    }
                                    dpCards.get(i).playCard();
                                    this.setVisible(false);
                                    return;
                                } else {
                                    //must roll first
                                    closeWindows();
                                    scd = new SettlersConfirmDialog(19);
                                    scd.setVisible(true);
                                    return;
                                }
                            } else {
                                //card can only be played before roll                
                                closeWindows();
                                scd = new SettlersConfirmDialog(18);
                                scd.setVisible(true);
                                return;
                            }
                        } else {
                            //already played a dp card this turn
                            closeWindows();
                            scd = new SettlersConfirmDialog(17);
                            scd.setVisible(true);
                            return;
                        }
                    } else if (!dpCards.get(i).getPlayable() && dpCards.get(i).getCard() == 5){
                        //you cannot play it on the same turn it's bought                     
                        closeWindows();
                        scd=new SettlersConfirmDialog(16);
                        scd.setVisible(true);
                            return;
                    }
                }
            } else if (cardFourLabel.getText().startsWith("Right of Way")) {
                for (int i = 0; i < dpCards.size(); i++) {
                    if (dpCards.get(i).getPlayable() && dpCards.get(i).getCard() == 6) {
                        if (!game_Frame.getCurrPlayer().getPlayedDPCardThisTurn()) {
                            if (game_Frame.getRolled()) {
                                closeWindows();
                                if (bw != null && bw.isVisible()) {
                                    bw.setVisible(false);
                                }
                                dpCards.get(i).playCard();
                                this.setVisible(false);
                                return;
                            } else {
                                //must roll first
                                closeWindows();
                                scd = new SettlersConfirmDialog(19);
                                scd.setVisible(true);
                                return;
                            }
                        } else {
                            //already played a dp card this turn
                            closeWindows();
                            scd = new SettlersConfirmDialog(17);
                            scd.setVisible(true);
                            return;
                        }
                    } else if (!dpCards.get(i).getPlayable() && dpCards.get(i).getCard() == 6){
                        //you cannot play it on the same turn it's bought                     
                        closeWindows();
                        scd=new SettlersConfirmDialog(16);
                        scd.setVisible(true);
                            return;
                    }
                }
            } else {
                for (int i = 0; i < dpCards.size(); i++) {
                    if (dpCards.get(i).getPlayable() && dpCards.get(i).getCard() == 7
                            && game_Frame.getCurrPlayer().getCattle() > 0) {
                        if (!game_Frame.getCurrPlayer().getPlayedDPCardThisTurn()) {
                            if (game_Frame.getRolled()) {
                                closeWindows();
                                if (bw != null && bw.isVisible()) {
                                    bw.setVisible(false);
                                }
                                dpCards.get(i).playCard();
                                this.setVisible(false);
                                return;
                            } else {
                                //must roll first
                                closeWindows();
                                scd = new SettlersConfirmDialog(19);
                                scd.setVisible(true);
                                return;
                            }
                        } else {
                            //already played a dp card this turn
                            closeWindows();
                            scd = new SettlersConfirmDialog(17);
                            scd.setVisible(true);
                            return;
                        }
                    }else if(!dpCards.get(i).getPlayable() && dpCards.get(i).getCard() == 7){
                        //you cannot play it on the same turn it's bought                     
                        closeWindows();
                        scd=new SettlersConfirmDialog(16);
                        scd.setVisible(true);
                            return;
                    }
                }
            }
        }
    }//GEN-LAST:event_cardFourPlayButton1MouseReleased
    private void cardSixInfoButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cardSixInfoButton1MouseReleased
        if(cardSixInfoButton.isAble()){
            closeWindows();
            dci = new DevelopmentCardInfo(panel6);
            dci.setVisible(true);
        }
    }//GEN-LAST:event_cardSixInfoButton1MouseReleased
    private void cardSixPlayButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cardSixPlayButton1MouseReleased
        if(cardSixPlayButton.isAble()){
            if (cardSixLabel.getText().startsWith("Right of Way")) {
                for (int i = 0; i < dpCards.size(); i++) {
                    if (dpCards.get(i).getPlayable() && dpCards.get(i).getCard() == 6) {
                        if (!game_Frame.getCurrPlayer().getPlayedDPCardThisTurn()) {
                            if (game_Frame.getRolled()) {
                                closeWindows();
                                if (bw != null && bw.isVisible()) {
                                    bw.setVisible(false);
                                }
                                dpCards.get(i).playCard();
                                this.setVisible(false);
                                return;
                            } else {
                                //must roll first
                                closeWindows();
                                scd = new SettlersConfirmDialog(19);
                                scd.setVisible(true);
                                return;
                            }
                        } else {
                            //already played a dp card this turn
                            closeWindows();
                            scd = new SettlersConfirmDialog(17);
                            scd.setVisible(true);
                            return;
                        }
                    } else if (!dpCards.get(i).getPlayable() && dpCards.get(i).getCard() == 6){
                        //you cannot play it on the same turn it's bought                     
                        closeWindows();
                        scd=new SettlersConfirmDialog(16);
                        scd.setVisible(true);
                            return;
                    }
                }
            } else {
                for (int i = 0; i < dpCards.size(); i++) {
                    if (dpCards.get(i).getPlayable() && dpCards.get(i).getCard() == 7
                            && game_Frame.getCurrPlayer().getCattle() > 0) {
                        if (!game_Frame.getCurrPlayer().getPlayedDPCardThisTurn()) {
                            if (game_Frame.getRolled()) {
                                closeWindows();
                                if (bw != null && bw.isVisible()) {
                                    bw.setVisible(false);
                                }
                                dpCards.get(i).playCard();
                                this.setVisible(false);
                                return;
                            } else {
                                //must roll first
                                closeWindows();
                                scd = new SettlersConfirmDialog(19);
                                scd.setVisible(true);
                                return;
                            }
                        } else {
                            //already played a dp card this turn
                            closeWindows();
                            scd = new SettlersConfirmDialog(17);
                            scd.setVisible(true);
                            return;
                        }
                    }else if(!dpCards.get(i).getPlayable() && dpCards.get(i).getCard() == 7){
                        //you cannot play it on the same turn it's bought                     
                        closeWindows();
                        scd=new SettlersConfirmDialog(16);
                        scd.setVisible(true);
                            return;
                    }
                }
            }
        }
    }//GEN-LAST:event_cardSixPlayButton1MouseReleased
    private void cardSevenInfoButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cardSevenInfoButton1MouseReleased
        if(cardSevenInfoButton.isAble()){
            closeWindows();
            dci = new DevelopmentCardInfo(panel7);
            dci.setVisible(true);
        }
    }//GEN-LAST:event_cardSevenInfoButton1MouseReleased
    private void cardSevenPlayButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cardSevenPlayButton1MouseReleased
        if(cardSevenPlayButton.isAble()) {
            for (int i = 0; i < dpCards.size(); i++) {
                if (dpCards.get(i).getPlayable() && dpCards.get(i).getCard() == 7
                        && game_Frame.getCurrPlayer().getCattle() > 0) {
                    if (!game_Frame.getCurrPlayer().getPlayedDPCardThisTurn()) {
                        if (game_Frame.getRolled()) {
                            closeWindows();
                            if (bw != null && bw.isVisible()) {
                                bw.setVisible(false);
                            }
                            dpCards.get(i).playCard();
                            this.setVisible(false);
                            return;
                        } else {
                            //must roll first
                            closeWindows();
                            scd = new SettlersConfirmDialog(19);
                            scd.setVisible(true);
                            return;
                        }
                    } else {
                        //already played a dp card this turn
                        closeWindows();
                        scd = new SettlersConfirmDialog(17);
                        scd.setVisible(true);
                        return;
                    }
                } else if (!dpCards.get(i).getPlayable() && dpCards.get(i).getCard() == 7) {
                    //you cannot play it on the same turn it's bought                     
                    closeWindows();
                    scd = new SettlersConfirmDialog(16);
                    scd.setVisible(true);
                    return;
                }
            }
        }
    }//GEN-LAST:event_cardSevenPlayButton1MouseReleased
    private void xButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xButtonMouseReleased
        if (xButton1.isAble()) {
            game_Frame.enableMenu();
            this.dispose();
        }
    }//GEN-LAST:event_xButtonMouseReleased
    private void xButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_xButtonActionPerformed
    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        closeWindows();
        this.setVisible(false);
    }//GEN-LAST:event_formWindowClosed
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new developmentCardWindow().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel backgroundLabel;
    private javax.swing.JButton cardFiveInfoButton1;
    private javax.swing.JLabel cardFiveLabel;
    private javax.swing.JButton cardFivePlayButton1;
    private javax.swing.JButton cardFourInfoButton1;
    private javax.swing.JLabel cardFourLabel;
    private javax.swing.JButton cardFourPlayButton1;
    private javax.swing.JButton cardOneInfoButton1;
    private javax.swing.JLabel cardOneLabel;
    private javax.swing.JButton cardOnePlayButton1;
    private javax.swing.JPanel cardPanel1;
    private javax.swing.JPanel cardPanel2;
    private javax.swing.JPanel cardPanel3;
    private javax.swing.JPanel cardPanel4;
    private javax.swing.JPanel cardPanel5;
    private javax.swing.JPanel cardPanel6;
    private javax.swing.JPanel cardPanel7;
    private javax.swing.JButton cardSevenInfoButton1;
    private javax.swing.JLabel cardSevenLabel;
    private javax.swing.JButton cardSevenPlayButton1;
    private javax.swing.JButton cardSixInfoButton1;
    private javax.swing.JLabel cardSixLabel;
    private javax.swing.JButton cardSixPlayButton1;
    private javax.swing.JButton cardThreeInfoButton1;
    private javax.swing.JLabel cardThreeLabel;
    private javax.swing.JButton cardThreePlayButton1;
    private javax.swing.JButton cardTwoInfoButton1;
    private javax.swing.JLabel cardTwoLabel;
    private javax.swing.JButton cardTwoPlayButton1;
    private javax.swing.JLabel topLabel;
    private javax.swing.JButton xButton;
    // End of variables declaration//GEN-END:variables
}

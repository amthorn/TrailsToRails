/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * buildWindow.java
 *
 * Created on Oct 1, 2013, 9:19:56 PM
 */

package settlerstrailstorails;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;

/**
 *
 * @author User 1
 */
public class buildWindow extends javax.swing.JFrame {
    
    Player currPlayer;
    ImageIcon redSettler,whiteSettler,greenSettler,orangeSettler,
            redTrain,whiteTrain,greenTrain,orangeTrain;
    boolean mouseOnBuildSettlerButton,mouseOnInfoSettlersButton,
            buttonPressed,mouseOnBuildRailButton,mouseOnBuildMoveSettlerButton,
            mouseOnBuildOkayButton,extraordinaryBuildingPhase;
    gameFrame game_Frame;
    SettlersConfirmDialog scd;
    AveryJButton buildTrainButton, buildMoveTrainButton,buildDPCardButton,
            playDPCardButton;
    developmentCardWindow dpcw = new developmentCardWindow();

    public buildWindow() {
        initComponents();
    }
    buildWindow(Player p, gameFrame gf) {
        extraordinaryBuildingPhase=false;
        game_Frame=gf;
        orangeSettler=new ImageIcon(
        makeColorTransparent("/settlerstrailstorails/resources/orangeSettler.png", Color.WHITE));
        redSettler=new ImageIcon(
        makeColorTransparent("/settlerstrailstorails/resources/redSettler.png", Color.WHITE));
        greenSettler=new ImageIcon(
        makeColorTransparent("/settlerstrailstorails/resources/greenSettler.png", Color.WHITE));
        whiteSettler=new ImageIcon(
        makeColorTransparent("/settlerstrailstorails/resources/whiteSettler.png", Color.WHITE));
        orangeTrain=new ImageIcon(
        makeColorTransparent("/settlerstrailstorails/resources/orangeTrain.png", Color.WHITE));
        redTrain=new ImageIcon(
        makeColorTransparent("/settlerstrailstorails/resources/redTrain.png", Color.WHITE));
        greenTrain=new ImageIcon(
        makeColorTransparent("/settlerstrailstorails/resources/greenTrain.png", Color.WHITE));
        whiteTrain=new ImageIcon(
        makeColorTransparent("/settlerstrailstorails/resources/whiteTrain.png", Color.WHITE));
        currPlayer=p;
        initComponents();
        buildTrainButton=new AveryJButton(buildTrainButton1);
        buildMoveTrainButton=new AveryJButton(buildMoveTrainButton1);
        buildDPCardButton=new AveryJButton(buildDPCardButton1);
        playDPCardButton=new AveryJButton(playDPCardButton1);
        if(p.getLumber()<1 || p.getOre()<1){
            buildRailButton.setEnabled(false);
        }
        if(p.getCattle()<1 || p.getCoal()<1){
            buildDPCardButton.setEnabled(false);
        }
        if(p.getWheat()<1 || p.getCattle()<1 || p.getLumber()<1){
            buildSettlerButton.setEnabled(false);
        }
        if(p.getOre()<1 || p.getCoal()<1 || p.getLumber()<1){
            buildTrainButton.setEnabled(false);
        }
        if(p.getWheat()<1 || p.getSettlers()==0){
            buildMoveSettlerButton.setEnabled(false);
        }        
        if(p.getCoal()<1){
            buildMoveTrainButton.setEnabled(false);
        }
        if(currPlayer.getColor()==Player.Color.RED){
            settlerIconOne.setIcon(redSettler);
            settlerIconTwo.setIcon(redSettler);
            railIconOne.setIcon(
        new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/redRailHorizontal.png", Color.WHITE)));
            railIconTwo.setIcon(
        new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/redRailHorizontal.png", Color.WHITE)));
            trainIconOne.setIcon(redTrain);
            trainIconTwo.setIcon(redTrain);
        }else if(currPlayer.getColor()==Player.Color.GREEN){
            settlerIconOne.setIcon(greenSettler);
            settlerIconTwo.setIcon(greenSettler);         
            railIconOne.setIcon(
        new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/greenRailHorizontal.png", Color.WHITE)));
            railIconTwo.setIcon(
        new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/greenRailHorizontal.png", Color.WHITE)));
            trainIconOne.setIcon(greenTrain);
            trainIconTwo.setIcon(greenTrain);   
        }else if(currPlayer.getColor()==Player.Color.WHITE){
            settlerIconOne.setIcon(whiteSettler);
            settlerIconTwo.setIcon(whiteSettler); 
            railIconOne.setIcon(
        new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/whiteRailHorizontal.png", Color.WHITE)));
            railIconTwo.setIcon(
        new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/whiteRailHorizontal.png", Color.WHITE)));
            trainIconOne.setIcon(whiteTrain);
            trainIconTwo.setIcon(whiteTrain);           
        }else if(currPlayer.getColor()==Player.Color.ORANGE){
            settlerIconOne.setIcon(orangeSettler);
            settlerIconTwo.setIcon(orangeSettler);      
            railIconOne.setIcon(
        new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/orangeRailHorizontal.png", Color.WHITE)));
            railIconTwo.setIcon(
        new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/orangeRailHorizontal.png", Color.WHITE)));
            trainIconOne.setIcon(orangeTrain);
            trainIconTwo.setIcon(orangeTrain);      
        }
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(((dim.width/2)-(this.getSize().width/2)), ((dim.height/2)-((this.getSize().height/2)+20)));
        try{
            setIconImage(ImageIO.read(getClass().getResource("/settlerstrailstorails/resources/icon.png")));
        }catch(Exception e){
            
        }
        this.addWindowListener(new WindowAdapter() {
                @Override
            public void windowClosing(WindowEvent we) {
                dpcw.closeWindows();
                dpcw.setVisible(false);
                setVisible(false);
                game_Frame.enableMenu();
            }
        });
    }
    buildWindow(Player p, gameFrame gf,boolean extraordinaryBuilding) {
        extraordinaryBuildingPhase=extraordinaryBuilding;
        game_Frame=gf;
        orangeSettler=new ImageIcon(
        makeColorTransparent("/settlerstrailstorails/resources/orangeSettler.png", Color.WHITE));
        redSettler=new ImageIcon(
        makeColorTransparent("/settlerstrailstorails/resources/redSettler.png", Color.WHITE));
        greenSettler=new ImageIcon(
        makeColorTransparent("/settlerstrailstorails/resources/greenSettler.png", Color.WHITE));
        whiteSettler=new ImageIcon(
        makeColorTransparent("/settlerstrailstorails/resources/whiteSettler.png", Color.WHITE));
        orangeTrain=new ImageIcon(
        makeColorTransparent("/settlerstrailstorails/resources/orangeTrain.png", Color.WHITE));
        redTrain=new ImageIcon(
        makeColorTransparent("/settlerstrailstorails/resources/redTrain.png", Color.WHITE));
        greenTrain=new ImageIcon(
        makeColorTransparent("/settlerstrailstorails/resources/greenTrain.png", Color.WHITE));
        whiteTrain=new ImageIcon(
        makeColorTransparent("/settlerstrailstorails/resources/whiteTrain.png", Color.WHITE));
        currPlayer=p;
        initComponents();
        buildTrainButton=new AveryJButton(buildTrainButton1);
        buildMoveTrainButton=new AveryJButton(buildMoveTrainButton1);
        buildDPCardButton=new AveryJButton(buildDPCardButton1);
        playDPCardButton=new AveryJButton(playDPCardButton1);
        buildMoveSettlerButton.setEnabled(false);
        buildMoveTrainButton.setEnabled(false);
        playDPCardButton.setEnabled(false);
        if(p.getLumber()<1 || p.getOre()<1){
            buildRailButton.setEnabled(false);
        }
        if(p.getCattle()<1 || p.getCoal()<1){
            buildDPCardButton.setEnabled(false);
        }
        if(p.getWheat()<1 || p.getCattle()<1 || p.getLumber()<1){
            buildSettlerButton.setEnabled(false);
        }
        if(p.getOre()<1 || p.getCoal()<1 || p.getLumber()<1){
            buildTrainButton.setEnabled(false);
        }
        if(p.getWheat()<1){
            buildMoveSettlerButton.setEnabled(false);
        }        
        if(p.getCoal()<1){
            buildMoveTrainButton.setEnabled(false);
        }
        if(currPlayer.getColor()==Player.Color.RED){
            settlerIconOne.setIcon(redSettler);
            settlerIconTwo.setIcon(redSettler);
            railIconOne.setIcon(
        new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/redRailHorizontal.png", Color.WHITE)));
            railIconTwo.setIcon(
        new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/redRailHorizontal.png", Color.WHITE)));
            trainIconOne.setIcon(redTrain);
            trainIconTwo.setIcon(redTrain);
        }else if(currPlayer.getColor()==Player.Color.GREEN){
            settlerIconOne.setIcon(greenSettler);
            settlerIconTwo.setIcon(greenSettler);         
            railIconOne.setIcon(
        new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/greenRailHorizontal.png", Color.WHITE)));
            railIconTwo.setIcon(
        new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/greenRailHorizontal.png", Color.WHITE)));
            trainIconOne.setIcon(greenTrain);
            trainIconTwo.setIcon(greenTrain);   
        }else if(currPlayer.getColor()==Player.Color.WHITE){
            settlerIconOne.setIcon(whiteSettler);
            settlerIconTwo.setIcon(whiteSettler); 
            railIconOne.setIcon(
        new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/whiteRailHorizontal.png", Color.WHITE)));
            railIconTwo.setIcon(
        new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/whiteRailHorizontal.png", Color.WHITE)));
            trainIconOne.setIcon(whiteTrain);
            trainIconTwo.setIcon(whiteTrain);           
        }else if(currPlayer.getColor()==Player.Color.ORANGE){
            settlerIconOne.setIcon(orangeSettler);
            settlerIconTwo.setIcon(orangeSettler);      
            railIconOne.setIcon(
        new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/orangeRailHorizontal.png", Color.WHITE)));
            railIconTwo.setIcon(
        new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/orangeRailHorizontal.png", Color.WHITE)));
            trainIconOne.setIcon(orangeTrain);
            trainIconTwo.setIcon(orangeTrain);      
        }
        if(extraordinaryBuilding){
            buildingCostsLabel.setText("Extraordinary Building Phase");
        }
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(((dim.width / 2) - (this.getSize().width / 2)), ((dim.height / 2) - ((this.getSize().height / 2) + 20)));
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                new SettlersConfirmDialog(20).setVisible(true);
            }
        });
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

        mainPanel = new javax.swing.JPanel();
        developmentCardPanel = new javax.swing.JPanel();
        buildDPCardButton1 = new javax.swing.JButton();
        infoDPCardButton1 = new javax.swing.JButton();
        playDPCardButton1 = new javax.swing.JButton();
        DPCardResourceTwo = new javax.swing.JLabel();
        DPCardResourceOne = new javax.swing.JLabel();
        DPCardLabel = new javax.swing.JLabel();
        costDPCardLabel = new javax.swing.JLabel();
        DPCardPanel = new javax.swing.JPanel();
        movingTrainPanel = new javax.swing.JPanel();
        infoMoveTrainButton = new javax.swing.JButton();
        buildMoveTrainButton1 = new javax.swing.JButton();
        moveTrainResource = new javax.swing.JLabel();
        movingTrainLabel = new javax.swing.JLabel();
        movingTrainArrow = new javax.swing.JLabel();
        costMoveTrainLabel = new javax.swing.JLabel();
        spacesTrainLabel = new javax.swing.JLabel();
        costMoveTrainPanel = new javax.swing.JPanel();
        trainPanel = new javax.swing.JPanel();
        trainResourceOne = new javax.swing.JLabel();
        trainResourceTwo = new javax.swing.JLabel();
        trainResourceThree = new javax.swing.JLabel();
        buildTrainButton1 = new javax.swing.JButton();
        infoTrainButton = new javax.swing.JButton();
        costPanelTrain = new javax.swing.JPanel();
        costTrainLabel = new javax.swing.JLabel();
        trainLabel = new javax.swing.JLabel();
        trainIconOne = new javax.swing.JLabel();
        trainIconTwo = new javax.swing.JLabel();
        railPanel = new javax.swing.JPanel();
        buildRailButton = new javax.swing.JButton();
        infoRailButton = new javax.swing.JButton();
        settlerResourceTwo2 = new javax.swing.JLabel();
        settlerResourceOne1 = new javax.swing.JLabel();
        settlerLabel1 = new javax.swing.JLabel();
        railIconTwo = new javax.swing.JLabel();
        railIconOne = new javax.swing.JLabel();
        costRailLabel = new javax.swing.JLabel();
        costRailPanel = new javax.swing.JPanel();
        movingSettlerPanel = new javax.swing.JPanel();
        infoMoveSettlerButton = new javax.swing.JButton();
        buildMoveSettlerButton = new javax.swing.JButton();
        moveSettlerResource = new javax.swing.JLabel();
        movingSettlerLabel = new javax.swing.JLabel();
        movingSettlerArrow = new javax.swing.JLabel();
        costMoveSettlerLabel = new javax.swing.JLabel();
        spacesSettlerLabel = new javax.swing.JLabel();
        costMoveSettlerPanel = new javax.swing.JPanel();
        settlerPanel = new javax.swing.JPanel();
        settlerLabel = new javax.swing.JLabel();
        buildSettlerButton = new javax.swing.JButton();
        infoSettlersButton = new javax.swing.JButton();
        settlerResourceOne = new javax.swing.JLabel();
        settlerResourceTwo = new javax.swing.JLabel();
        settlerResourceThree = new javax.swing.JLabel();
        settlerIconOne = new javax.swing.JLabel();
        settlerIconTwo = new javax.swing.JLabel();
        costSettlerLabel = new javax.swing.JLabel();
        costPanelSettler = new javax.swing.JPanel();
        buildingCostsLabel = new javax.swing.JLabel();
        buildOkayButton = new javax.swing.JButton();
        backgroundLabel = new javax.swing.JLabel();

        setFocusable(false);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        mainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        developmentCardPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        developmentCardPanel.setOpaque(false);
        developmentCardPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buildDPCardButton1.setBackground(new java.awt.Color(239, 228, 176));
        buildDPCardButton1.setFont(new java.awt.Font("Tahoma", 1, 11));
        buildDPCardButton1.setText("BUILD");
        buildDPCardButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buildDPCardButton1.setContentAreaFilled(false);
        buildDPCardButton1.setFocusable(false);
        buildDPCardButton1.setOpaque(true);
        buildDPCardButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                buildDPCardButton1MouseReleased(evt);
            }
        });
        developmentCardPanel.add(buildDPCardButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 60, 30));

        infoDPCardButton1.setBackground(new java.awt.Color(239, 228, 176));
        infoDPCardButton1.setFont(new java.awt.Font("Tahoma", 1, 11));
        infoDPCardButton1.setText("INFO");
        infoDPCardButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        infoDPCardButton1.setContentAreaFilled(false);
        infoDPCardButton1.setFocusable(false);
        infoDPCardButton1.setOpaque(true);
        infoDPCardButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                infoDPCardButton1MouseReleased(evt);
            }
        });
        developmentCardPanel.add(infoDPCardButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 150, 60, 30));

        playDPCardButton1.setBackground(new java.awt.Color(239, 228, 176));
        playDPCardButton1.setFont(new java.awt.Font("Tahoma", 1, 11));
        playDPCardButton1.setText("PLAY");
        playDPCardButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        playDPCardButton1.setContentAreaFilled(false);
        playDPCardButton1.setFocusable(false);
        playDPCardButton1.setOpaque(true);
        playDPCardButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                playDPCardButton1MouseReleased(evt);
            }
        });
        developmentCardPanel.add(playDPCardButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, 60, 30));

        DPCardResourceTwo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/coalHalfSize.jpg"))); // NOI18N
        developmentCardPanel.add(DPCardResourceTwo, new org.netbeans.lib.awtextra.AbsoluteConstraints(137, 70, -1, -1));

        DPCardResourceOne.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/cattleHalfSize.jpg"))); // NOI18N
        developmentCardPanel.add(DPCardResourceOne, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 70, -1, -1));

        DPCardLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18));
        DPCardLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DPCardLabel.setText("DEVELOPMENT CARD");
        developmentCardPanel.add(DPCardLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 15, 220, 20));

        costDPCardLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        costDPCardLabel.setText("Cost:");
        developmentCardPanel.add(costDPCardLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 45, -1, -1));

        DPCardPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        DPCardPanel.setOpaque(false);

        org.jdesktop.layout.GroupLayout DPCardPanelLayout = new org.jdesktop.layout.GroupLayout(DPCardPanel);
        DPCardPanel.setLayout(DPCardPanelLayout);
        DPCardPanelLayout.setHorizontalGroup(
            DPCardPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 196, Short.MAX_VALUE)
        );
        DPCardPanelLayout.setVerticalGroup(
            DPCardPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 76, Short.MAX_VALUE)
        );

        developmentCardPanel.add(DPCardPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 64, 200, 80));

        mainPanel.add(developmentCardPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(484, 290, 220, 190));

        movingTrainPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        movingTrainPanel.setOpaque(false);
        movingTrainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        infoMoveTrainButton.setBackground(new java.awt.Color(239, 228, 176));
        infoMoveTrainButton.setFont(new java.awt.Font("Tahoma", 1, 11));
        infoMoveTrainButton.setText("INFO");
        infoMoveTrainButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        infoMoveTrainButton.setContentAreaFilled(false);
        infoMoveTrainButton.setFocusable(false);
        infoMoveTrainButton.setOpaque(true);
        infoMoveTrainButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                infoMoveTrainButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                infoMoveTrainButtonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                infoMoveTrainButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                infoMoveTrainButtonMouseReleased(evt);
            }
        });
        infoMoveTrainButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infoMoveTrainButtonActionPerformed(evt);
            }
        });
        movingTrainPanel.add(infoMoveTrainButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, 80, 30));

        buildMoveTrainButton1.setBackground(new java.awt.Color(239, 228, 176));
        buildMoveTrainButton1.setFont(new java.awt.Font("Tahoma", 1, 11));
        buildMoveTrainButton1.setText("BUILD");
        buildMoveTrainButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buildMoveTrainButton1.setContentAreaFilled(false);
        buildMoveTrainButton1.setFocusable(false);
        buildMoveTrainButton1.setOpaque(true);
        buildMoveTrainButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                buildMoveTrainButton1MouseReleased(evt);
            }
        });
        buildMoveTrainButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buildMoveTrainButton1ActionPerformed(evt);
            }
        });
        movingTrainPanel.add(buildMoveTrainButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 80, 30));

        moveTrainResource.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/coalHalfSize.jpg"))); // NOI18N
        movingTrainPanel.add(moveTrainResource, new org.netbeans.lib.awtextra.AbsoluteConstraints(89, 70, -1, -1));

        movingTrainLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 21));
        movingTrainLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        movingTrainLabel.setText("MOVING TRAIN");
        movingTrainPanel.add(movingTrainLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 13, 200, -1));

        movingTrainArrow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/moveTrainArrow.png"))); // NOI18N
        movingTrainPanel.add(movingTrainArrow, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 15, 200, -1));

        costMoveTrainLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        costMoveTrainLabel.setText("Cost:");
        movingTrainPanel.add(costMoveTrainLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 45, -1, -1));

        spacesTrainLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        spacesTrainLabel.setText("(1-3 spaces)");
        movingTrainPanel.add(spacesTrainLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 37, -1, -1));

        costMoveTrainPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        costMoveTrainPanel.setOpaque(false);

        org.jdesktop.layout.GroupLayout costMoveTrainPanelLayout = new org.jdesktop.layout.GroupLayout(costMoveTrainPanel);
        costMoveTrainPanel.setLayout(costMoveTrainPanelLayout);
        costMoveTrainPanelLayout.setHorizontalGroup(
            costMoveTrainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 196, Short.MAX_VALUE)
        );
        costMoveTrainPanelLayout.setVerticalGroup(
            costMoveTrainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 76, Short.MAX_VALUE)
        );

        movingTrainPanel.add(costMoveTrainPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 64, 200, 80));

        mainPanel.add(movingTrainPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(254, 290, 220, 190));

        trainPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        trainPanel.setOpaque(false);
        trainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        trainResourceOne.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/lumberHalfSize.jpg"))); // NOI18N
        trainPanel.add(trainResourceOne, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 70, -1, -1));

        trainResourceTwo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/oreHalfSize.jpg"))); // NOI18N
        trainPanel.add(trainResourceTwo, new org.netbeans.lib.awtextra.AbsoluteConstraints(89, 70, -1, -1));

        trainResourceThree.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/coalHalfSize.jpg"))); // NOI18N
        trainPanel.add(trainResourceThree, new org.netbeans.lib.awtextra.AbsoluteConstraints(161, 70, -1, -1));

        buildTrainButton1.setBackground(new java.awt.Color(239, 228, 176));
        buildTrainButton1.setFont(new java.awt.Font("Tahoma", 1, 11));
        buildTrainButton1.setText("BUILD");
        buildTrainButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buildTrainButton1.setContentAreaFilled(false);
        buildTrainButton1.setFocusable(false);
        buildTrainButton1.setOpaque(true);
        buildTrainButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                buildTrainButton1MouseReleased(evt);
            }
        });
        buildTrainButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buildTrainButton1ActionPerformed(evt);
            }
        });
        trainPanel.add(buildTrainButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 80, 30));

        infoTrainButton.setBackground(new java.awt.Color(239, 228, 176));
        infoTrainButton.setFont(new java.awt.Font("Tahoma", 1, 11));
        infoTrainButton.setText("INFO");
        infoTrainButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        infoTrainButton.setContentAreaFilled(false);
        infoTrainButton.setFocusable(false);
        infoTrainButton.setOpaque(true);
        infoTrainButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                infoTrainButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                infoTrainButtonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                infoTrainButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                infoTrainButtonMouseReleased(evt);
            }
        });
        infoTrainButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infoTrainButtonActionPerformed(evt);
            }
        });
        trainPanel.add(infoTrainButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, 80, 30));

        costPanelTrain.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        costPanelTrain.setOpaque(false);

        org.jdesktop.layout.GroupLayout costPanelTrainLayout = new org.jdesktop.layout.GroupLayout(costPanelTrain);
        costPanelTrain.setLayout(costPanelTrainLayout);
        costPanelTrainLayout.setHorizontalGroup(
            costPanelTrainLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 196, Short.MAX_VALUE)
        );
        costPanelTrainLayout.setVerticalGroup(
            costPanelTrainLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 76, Short.MAX_VALUE)
        );

        trainPanel.add(costPanelTrain, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 64, 200, 80));

        costTrainLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        costTrainLabel.setText("Cost:");
        trainPanel.add(costTrainLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 45, -1, -1));

        trainLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 21));
        trainLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        trainLabel.setText("TRAIN");
        trainPanel.add(trainLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 15, 220, 20));

        trainIconOne.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/redTrain.png"))); // NOI18N
        trainPanel.add(trainIconOne, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 11, -1, -1));

        trainIconTwo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/redTrain.png"))); // NOI18N
        trainPanel.add(trainIconTwo, new org.netbeans.lib.awtextra.AbsoluteConstraints(149, 11, -1, -1));

        mainPanel.add(trainPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 290, 220, 190));

        railPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        railPanel.setOpaque(false);
        railPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buildRailButton.setBackground(new java.awt.Color(239, 228, 176));
        buildRailButton.setFont(new java.awt.Font("Tahoma", 1, 11));
        buildRailButton.setText("BUILD");
        buildRailButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buildRailButton.setContentAreaFilled(false);
        buildRailButton.setFocusable(false);
        buildRailButton.setOpaque(true);
        buildRailButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buildRailButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buildRailButtonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                buildRailButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                buildRailButtonMouseReleased(evt);
            }
        });
        buildRailButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buildRailButtonActionPerformed(evt);
            }
        });
        railPanel.add(buildRailButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 80, 30));

        infoRailButton.setBackground(new java.awt.Color(239, 228, 176));
        infoRailButton.setFont(new java.awt.Font("Tahoma", 1, 11));
        infoRailButton.setText("INFO");
        infoRailButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        infoRailButton.setContentAreaFilled(false);
        infoRailButton.setFocusable(false);
        infoRailButton.setOpaque(true);
        infoRailButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                infoRailButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                infoRailButtonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                infoRailButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                infoRailButtonMouseReleased(evt);
            }
        });
        infoRailButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infoRailButtonActionPerformed(evt);
            }
        });
        railPanel.add(infoRailButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, 80, 30));

        settlerResourceTwo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/oreHalfSize.jpg"))); // NOI18N
        railPanel.add(settlerResourceTwo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(137, 70, -1, -1));

        settlerResourceOne1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/lumberHalfSize.jpg"))); // NOI18N
        railPanel.add(settlerResourceOne1, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 70, -1, -1));

        settlerLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 21));
        settlerLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        settlerLabel1.setText("RAIL");
        railPanel.add(settlerLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 15, 220, 20));

        railIconTwo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/redRailHorizontal.png"))); // NOI18N
        railPanel.add(railIconTwo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 18, -1, -1));

        railIconOne.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/redRailHorizontal.png"))); // NOI18N
        railPanel.add(railIconOne, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 18, -1, -1));

        costRailLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        costRailLabel.setText("Cost:");
        railPanel.add(costRailLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 45, -1, -1));

        costRailPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        costRailPanel.setOpaque(false);

        org.jdesktop.layout.GroupLayout costRailPanelLayout = new org.jdesktop.layout.GroupLayout(costRailPanel);
        costRailPanel.setLayout(costRailPanelLayout);
        costRailPanelLayout.setHorizontalGroup(
            costRailPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 196, Short.MAX_VALUE)
        );
        costRailPanelLayout.setVerticalGroup(
            costRailPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 76, Short.MAX_VALUE)
        );

        railPanel.add(costRailPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 64, 200, 80));

        mainPanel.add(railPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(484, 70, 220, 190));

        movingSettlerPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        movingSettlerPanel.setOpaque(false);
        movingSettlerPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        infoMoveSettlerButton.setBackground(new java.awt.Color(239, 228, 176));
        infoMoveSettlerButton.setFont(new java.awt.Font("Tahoma", 1, 11));
        infoMoveSettlerButton.setText("INFO");
        infoMoveSettlerButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        infoMoveSettlerButton.setContentAreaFilled(false);
        infoMoveSettlerButton.setFocusable(false);
        infoMoveSettlerButton.setOpaque(true);
        infoMoveSettlerButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                infoMoveSettlerButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                infoMoveSettlerButtonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                infoMoveSettlerButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                infoMoveSettlerButtonMouseReleased(evt);
            }
        });
        infoMoveSettlerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infoMoveSettlerButtonActionPerformed(evt);
            }
        });
        movingSettlerPanel.add(infoMoveSettlerButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, 80, 30));

        buildMoveSettlerButton.setBackground(new java.awt.Color(239, 228, 176));
        buildMoveSettlerButton.setFont(new java.awt.Font("Tahoma", 1, 11));
        buildMoveSettlerButton.setText("BUILD");
        buildMoveSettlerButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buildMoveSettlerButton.setContentAreaFilled(false);
        buildMoveSettlerButton.setFocusable(false);
        buildMoveSettlerButton.setOpaque(true);
        buildMoveSettlerButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buildMoveSettlerButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buildMoveSettlerButtonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                buildMoveSettlerButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                buildMoveSettlerButtonMouseReleased(evt);
            }
        });
        buildMoveSettlerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buildMoveSettlerButtonActionPerformed(evt);
            }
        });
        movingSettlerPanel.add(buildMoveSettlerButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 80, 30));

        moveSettlerResource.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/wheatHalfSize.jpg"))); // NOI18N
        movingSettlerPanel.add(moveSettlerResource, new org.netbeans.lib.awtextra.AbsoluteConstraints(89, 70, -1, -1));

        movingSettlerLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 21));
        movingSettlerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        movingSettlerLabel.setText("MOVING SETTLER");
        movingSettlerPanel.add(movingSettlerLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 13, 200, -1));

        movingSettlerArrow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/moveSettlerArrow.png"))); // NOI18N
        movingSettlerPanel.add(movingSettlerArrow, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 15, 200, -1));

        costMoveSettlerLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        costMoveSettlerLabel.setText("Cost:");
        movingSettlerPanel.add(costMoveSettlerLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 45, -1, -1));

        spacesSettlerLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        spacesSettlerLabel.setText("(1-3 spaces)");
        movingSettlerPanel.add(spacesSettlerLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 37, -1, -1));

        costMoveSettlerPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        costMoveSettlerPanel.setOpaque(false);

        org.jdesktop.layout.GroupLayout costMoveSettlerPanelLayout = new org.jdesktop.layout.GroupLayout(costMoveSettlerPanel);
        costMoveSettlerPanel.setLayout(costMoveSettlerPanelLayout);
        costMoveSettlerPanelLayout.setHorizontalGroup(
            costMoveSettlerPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 196, Short.MAX_VALUE)
        );
        costMoveSettlerPanelLayout.setVerticalGroup(
            costMoveSettlerPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 76, Short.MAX_VALUE)
        );

        movingSettlerPanel.add(costMoveSettlerPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 64, 200, 80));

        mainPanel.add(movingSettlerPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(254, 70, 220, 190));

        settlerPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        settlerPanel.setOpaque(false);
        settlerPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        settlerLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 21));
        settlerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        settlerLabel.setText("SETTLER");
        settlerPanel.add(settlerLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 15, 220, 20));

        buildSettlerButton.setBackground(new java.awt.Color(239, 228, 176));
        buildSettlerButton.setFont(new java.awt.Font("Tahoma", 1, 11));
        buildSettlerButton.setText("BUILD");
        buildSettlerButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buildSettlerButton.setContentAreaFilled(false);
        buildSettlerButton.setFocusable(false);
        buildSettlerButton.setOpaque(true);
        buildSettlerButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buildSettlerButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buildSettlerButtonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                buildSettlerButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                buildSettlerButtonMouseReleased(evt);
            }
        });
        buildSettlerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buildSettlerButtonActionPerformed(evt);
            }
        });
        settlerPanel.add(buildSettlerButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 80, 30));

        infoSettlersButton.setBackground(new java.awt.Color(239, 228, 176));
        infoSettlersButton.setFont(new java.awt.Font("Tahoma", 1, 11));
        infoSettlersButton.setText("INFO");
        infoSettlersButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        infoSettlersButton.setContentAreaFilled(false);
        infoSettlersButton.setFocusable(false);
        infoSettlersButton.setOpaque(true);
        infoSettlersButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                infoSettlersButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                infoSettlersButtonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                infoSettlersButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                infoSettlersButtonMouseReleased(evt);
            }
        });
        infoSettlersButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infoSettlersButtonActionPerformed(evt);
            }
        });
        settlerPanel.add(infoSettlersButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, 80, 30));

        settlerResourceOne.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/cattleHalfSize.jpg"))); // NOI18N
        settlerPanel.add(settlerResourceOne, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 70, -1, -1));

        settlerResourceTwo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/wheatHalfSize.jpg"))); // NOI18N
        settlerPanel.add(settlerResourceTwo, new org.netbeans.lib.awtextra.AbsoluteConstraints(89, 70, -1, -1));

        settlerResourceThree.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/lumberHalfSize.jpg"))); // NOI18N
        settlerPanel.add(settlerResourceThree, new org.netbeans.lib.awtextra.AbsoluteConstraints(161, 70, -1, -1));

        settlerIconOne.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/redSettler.png"))); // NOI18N
        settlerPanel.add(settlerIconOne, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 18, -1, -1));

        settlerIconTwo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/redSettler.png"))); // NOI18N
        settlerPanel.add(settlerIconTwo, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 18, -1, -1));

        costSettlerLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        costSettlerLabel.setText("Cost:");
        settlerPanel.add(costSettlerLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 45, -1, -1));

        costPanelSettler.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        costPanelSettler.setOpaque(false);

        org.jdesktop.layout.GroupLayout costPanelSettlerLayout = new org.jdesktop.layout.GroupLayout(costPanelSettler);
        costPanelSettler.setLayout(costPanelSettlerLayout);
        costPanelSettlerLayout.setHorizontalGroup(
            costPanelSettlerLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 196, Short.MAX_VALUE)
        );
        costPanelSettlerLayout.setVerticalGroup(
            costPanelSettlerLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 76, Short.MAX_VALUE)
        );

        settlerPanel.add(costPanelSettler, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 64, 200, 80));

        mainPanel.add(settlerPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 70, 220, 190));

        buildingCostsLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 24));
        buildingCostsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        buildingCostsLabel.setText("BUILDING COSTS");
        mainPanel.add(buildingCostsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 20, 700, -1));

        buildOkayButton.setBackground(new java.awt.Color(239, 228, 176));
        buildOkayButton.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 24));
        buildOkayButton.setText("X");
        buildOkayButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buildOkayButton.setContentAreaFilled(false);
        buildOkayButton.setFocusable(false);
        buildOkayButton.setOpaque(true);
        buildOkayButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buildOkayButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buildOkayButtonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                buildOkayButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                buildOkayButtonMouseReleased(evt);
            }
        });
        buildOkayButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buildOkayButtonActionPerformed(evt);
            }
        });
        mainPanel.add(buildOkayButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(664, 20, 40, 40));

        backgroundLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/playerInfoBackground.png"))); // NOI18N
        mainPanel.add(backgroundLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, 520));

        getContentPane().add(mainPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, 520));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void buildSettlerButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buildSettlerButtonMouseEntered
        mouseOnBuildSettlerButton=true;
}//GEN-LAST:event_buildSettlerButtonMouseEntered
    private void buildSettlerButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buildSettlerButtonMouseExited
        if(mouseOnBuildSettlerButton&&buttonPressed){
            buildSettlerButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            buildSettlerButton.setLocation(buildSettlerButton.getX() + 1, buildSettlerButton.getY());
        }
        mouseOnBuildSettlerButton=false;
        buttonPressed=false;
}//GEN-LAST:event_buildSettlerButtonMouseExited
    private void buildSettlerButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buildSettlerButtonMousePressed
        if(buildSettlerButton.isEnabled()){
        gameFrame.playSound("click");
        buildSettlerButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        buildSettlerButton.setLocation(buildSettlerButton.getX()-1, buildSettlerButton.getY());
        buttonPressed=true;
        }
}//GEN-LAST:event_buildSettlerButtonMousePressed
    private void buildSettlerButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buildSettlerButtonMouseReleased
        if(mouseOnBuildSettlerButton&&buttonPressed) {
            buildSettlerButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            buildSettlerButton.setLocation(buildSettlerButton.getX() + 1, buildSettlerButton.getY());
            if (!game_Frame.getIgnoreResources() && currPlayer.getWheat() > 0 && currPlayer.getLumber() > 0
                    && currPlayer.getCattle() > 0) {
                if (currPlayer.getSettlers() < 2 && !currPlayer.maxNumberOfCitiesBuilt()) {
                    game_Frame.spokane.getIntersection().setAllIntersectionsInvisible();
                    game_Frame.spokane.getIntersection().setAllCityBuildSettler(true, currPlayer);
                    gameFrame.addToInfo("Select a city to build your settler", true);
                    this.setVisible(false);
                    currPlayer.minusWheat();
                    currPlayer.minusCattle();
                    currPlayer.minusLumber();
                    game_Frame.buildingSettler(true, false);
                } else if (!currPlayer.maxNumberOfCitiesBuilt()) {
                    //you have 2 settlers already error
                    if (scd != null && scd.isVisible()) {
                        scd.setVisible(false);
                    }
                    scd = new SettlersConfirmDialog(10, currPlayer, game_Frame, this);
                    scd.setVisible(true);
                } else if (currPlayer.maxNumberOfCitiesBuilt()) {
                    //you already have the maximum number of allowed cities
                    if (scd != null && scd.isVisible()) {
                        scd.setVisible(false);
                    }
                    scd = new SettlersConfirmDialog(11);
                    scd.setVisible(true);
                }
            } else if (game_Frame.getIgnoreResources()) {
                if (currPlayer.getSettlers() < 2 && !currPlayer.maxNumberOfCitiesBuilt()) {
                    game_Frame.spokane.getIntersection().setAllIntersectionsInvisible();
                    game_Frame.spokane.getIntersection().setAllCityBuildSettler(true, currPlayer);
                    game_Frame.buildingSettler(true, false);
                    gameFrame.addToInfo("Select a city to build your settler", true);
                    this.setVisible(false);
                } else if (!currPlayer.maxNumberOfCitiesBuilt()) {
                    //you have 2 settlers already error
                    if (scd != null && scd.isVisible()) {
                        scd.setVisible(false);
                    }
                    scd = new SettlersConfirmDialog(10, currPlayer, game_Frame, this);
                    scd.setVisible(true);
                } else if (currPlayer.maxNumberOfCitiesBuilt()) {
                    //you already have the maximum number of allowed cities
                    if (scd != null && scd.isVisible()) {
                        scd.setVisible(false);
                    }
                    scd = new SettlersConfirmDialog(11);
                    scd.setVisible(true);
                }
            } else {
                //you don't have enough resources
                if (scd != null && scd.isVisible()) {
                    scd.setVisible(false);
                }
                scd = new SettlersConfirmDialog(9);
                scd.setVisible(true);
            }
            if (currPlayer.getLumber() < 1 || currPlayer.getOre() < 1) {
                buildRailButton.setEnabled(false);
            }
            if (currPlayer.getCattle() < 1 || currPlayer.getCoal() < 1) {
                buildDPCardButton.setEnabled(false);
            }
            if (currPlayer.getWheat() < 1 || currPlayer.getCattle() < 1 || currPlayer.getLumber() < 1) {
                buildSettlerButton.setEnabled(false);
            }
            if (currPlayer.getOre() < 1 || currPlayer.getCoal() < 1 || currPlayer.getLumber() < 1) {
                buildTrainButton.setEnabled(false);
            }
            if (currPlayer.getWheat() < 1 || currPlayer.getSettlers() == 0) {
                buildMoveSettlerButton.setEnabled(false);
            }
            if (currPlayer.getCoal() < 1) {
                buildMoveTrainButton.setEnabled(false);
            }
        }
    }//GEN-LAST:event_buildSettlerButtonMouseReleased
    private void buildSettlerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buildSettlerButtonActionPerformed
        
}//GEN-LAST:event_buildSettlerButtonActionPerformed
    private void infoSettlersButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_infoSettlersButtonMouseEntered
        mouseOnInfoSettlersButton=true;
}//GEN-LAST:event_infoSettlersButtonMouseEntered
    private void infoSettlersButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_infoSettlersButtonMouseExited
        if(mouseOnInfoSettlersButton&&buttonPressed){
            infoSettlersButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            infoSettlersButton.setLocation(infoSettlersButton.getX() + 1, infoSettlersButton.getY());
        }
        mouseOnInfoSettlersButton=false;
        buttonPressed=false;
}//GEN-LAST:event_infoSettlersButtonMouseExited
    private void infoSettlersButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_infoSettlersButtonMousePressed
        gameFrame.playSound("click");
        infoSettlersButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        infoSettlersButton.setLocation(infoSettlersButton.getX()-1, infoSettlersButton.getY());
        buttonPressed=true;
}//GEN-LAST:event_infoSettlersButtonMousePressed
    private void infoSettlersButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_infoSettlersButtonMouseReleased
        buttonPressed=false;
        if(mouseOnInfoSettlersButton) {
            infoSettlersButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            infoSettlersButton.setLocation(infoSettlersButton.getX() + 1, infoSettlersButton.getY());
            //func
        }
    }//GEN-LAST:event_infoSettlersButtonMouseReleased
    private void infoSettlersButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_infoSettlersButtonActionPerformed
        
}//GEN-LAST:event_infoSettlersButtonActionPerformed
    private void infoMoveSettlerButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_infoMoveSettlerButtonMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_infoMoveSettlerButtonMouseEntered
    private void infoMoveSettlerButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_infoMoveSettlerButtonMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_infoMoveSettlerButtonMouseExited
    private void infoMoveSettlerButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_infoMoveSettlerButtonMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_infoMoveSettlerButtonMousePressed
    private void infoMoveSettlerButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_infoMoveSettlerButtonMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_infoMoveSettlerButtonMouseReleased
    private void infoMoveSettlerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_infoMoveSettlerButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_infoMoveSettlerButtonActionPerformed
    private void buildMoveSettlerButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buildMoveSettlerButtonMouseEntered
        mouseOnBuildMoveSettlerButton=true;
    }//GEN-LAST:event_buildMoveSettlerButtonMouseEntered
    private void buildMoveSettlerButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buildMoveSettlerButtonMouseExited
        if(mouseOnBuildMoveSettlerButton&&buttonPressed){
            buildMoveSettlerButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            buildMoveSettlerButton.setLocation(buildMoveSettlerButton.getX() + 1, buildMoveSettlerButton.getY());
        }
        mouseOnBuildMoveSettlerButton=false;
        buttonPressed=false;
    }//GEN-LAST:event_buildMoveSettlerButtonMouseExited
    private void buildMoveSettlerButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buildMoveSettlerButtonMousePressed
        if(buildMoveSettlerButton.isEnabled()) {
            gameFrame.playSound("click");
            buildMoveSettlerButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            buildMoveSettlerButton.setLocation(buildMoveSettlerButton.getX() - 1, buildMoveSettlerButton.getY());
            buttonPressed = true;
        }
    }//GEN-LAST:event_buildMoveSettlerButtonMousePressed
    private void buildMoveSettlerButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buildMoveSettlerButtonMouseReleased
        if(mouseOnBuildMoveSettlerButton&&buttonPressed) {
            buildMoveSettlerButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            buildMoveSettlerButton.setLocation(buildMoveSettlerButton.getX() + 1, buildMoveSettlerButton.getY());
            //make sure you have enough resources
            if(currPlayer.getWheat()>0 && !game_Frame.getIgnoreResources()){
                //make sure you have at least one settler
                if(currPlayer.getSettlers() > 0) {
                    currPlayer.minusWheat();
                    this.setVisible(false);
                    if(currPlayer.getSettlers()==2){
                        //choose settler
                        game_Frame.setMovingSettler(true);
                        game_Frame.buildingSettler(true, false);
                        gameFrame.addToInfo("Select the settler you'd like to move.",true);
                        Intersection[] intersections = game_Frame.getIntersections();
                        for(int i=0;i<intersections.length;i++){
                            if(intersections[i].getSettlerColor()==currPlayer.getColor()){
                                intersections[i].getButton().setVisible(true);
                                intersections[i].getButton().setBorderPainted(true);
                                intersections[i].setPlayer(currPlayer);
                                intersections[i].setMoveSettler(true);
                                intersections[i].setNoMoveToCity(true);
                            }
                        }
                    }else{
                        //where to move
                        game_Frame.setMovingSettler(true);
                        game_Frame.buildingSettler(true, false);
                        gameFrame.addToInfo("Select the intersection to which you'd like to move.",true);
                        Intersection[] intersections = game_Frame.getIntersections();
                        for(int i=0;i<intersections.length;i++){
                            if(intersections[i].getSettlerColor()==currPlayer.getColor()){
                                intersections[i].setAllBuildSettlerWithin(true, 3, currPlayer);
                                intersections[i].buildSettler(currPlayer);
                                intersections[i].removeSettler();
                                currPlayer.minusSettlers();
                                intersections[i].setOnTheCusp(true);
                            }
                        }
                    }
                }else{
                    //you don't have a settler to move
                    if (scd != null && scd.isVisible()) {
                        scd.setVisible(false);
                    }
                    scd = new SettlersConfirmDialog(13);
                    scd.setVisible(true);
                }
            } else if(game_Frame.getIgnoreResources()) {
                //make sure you have at least one settler
                if(currPlayer.getSettlers() > 0) {
                    this.setVisible(false);
                    if(currPlayer.getSettlers()==2){
                        //choose settler
                        game_Frame.setMovingSettler(true);
                        game_Frame.buildingSettler(true, false);
                        gameFrame.addToInfo("Select the settler you'd like to move.",true);
                        Intersection[] intersections = game_Frame.getIntersections();
                        for(int i=0;i<intersections.length;i++){
                            if(intersections[i].getSettlerColor()==currPlayer.getColor()){
                                intersections[i].getButton().setVisible(true);
                                intersections[i].setPlayer(currPlayer);
                                intersections[i].setMoveSettler(true);
                                intersections[i].setNoMoveToCity(true);
                            }
                        }
                    }else{
                        //where to move
                        game_Frame.setMovingSettler(true);
                        game_Frame.buildingSettler(true, false);
                        gameFrame.addToInfo("Select the intersection to which you'd like to move.",true);
                        Intersection[] intersections = game_Frame.getIntersections();
                        for(int i=0;i<intersections.length;i++){
                            if(intersections[i].getSettlerColor()==currPlayer.getColor()){
                                intersections[i].setAllBuildSettlerWithin(true, 3, currPlayer);
                                intersections[i].buildSettler(currPlayer);
                                intersections[i].removeSettler();
                            }
                        }
                    }
                } else {
                    //you don't have a settler to move
                    if (scd != null && scd.isVisible()) {
                        scd.setVisible(false);
                    }
                    scd = new SettlersConfirmDialog(13);
                    scd.setVisible(true);
                }
            } else {
                //you don't have enough resources
                if (scd != null && scd.isVisible()) {
                    scd.setVisible(false);
                }
                scd = new SettlersConfirmDialog(9);
                scd.setVisible(true);
            }            
            if (currPlayer.getLumber() < 1 || currPlayer.getOre() < 1) {
                buildRailButton.setEnabled(false);
            }
            if (currPlayer.getCattle() < 1 || currPlayer.getCoal() < 1) {
                buildDPCardButton.setEnabled(false);
            }
            if (currPlayer.getWheat() < 1 || currPlayer.getCattle() < 1 || currPlayer.getLumber() < 1) {
                buildSettlerButton.setEnabled(false);
            }
            if (currPlayer.getOre() < 1 || currPlayer.getCoal() < 1 || currPlayer.getLumber() < 1) {
                buildTrainButton.setEnabled(false);
            }
            if (currPlayer.getWheat() < 1 || currPlayer.getSettlers() == 0) {
                buildMoveSettlerButton.setEnabled(false);
            }
            if (currPlayer.getCoal() < 1) {
                buildMoveTrainButton.setEnabled(false);
            }
        }
    }//GEN-LAST:event_buildMoveSettlerButtonMouseReleased
    private void buildMoveSettlerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buildMoveSettlerButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buildMoveSettlerButtonActionPerformed
    private void buildRailButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buildRailButtonMouseEntered
        mouseOnBuildRailButton=true;
    }//GEN-LAST:event_buildRailButtonMouseEntered
    private void buildRailButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buildRailButtonMouseExited
        if(mouseOnBuildRailButton&&buttonPressed){
            buildRailButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            buildRailButton.setLocation(buildRailButton.getX() + 1, buildRailButton.getY());
        }
        mouseOnBuildRailButton=false;
        buttonPressed=false;
    }//GEN-LAST:event_buildRailButtonMouseExited
    private void buildRailButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buildRailButtonMousePressed
        if(buildRailButton.isEnabled()) {
            gameFrame.playSound("click");
            buildRailButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            buildRailButton.setLocation(buildRailButton.getX() - 1, buildRailButton.getY());
            buttonPressed = true;
        }
    }//GEN-LAST:event_buildRailButtonMousePressed
    private void buildRailButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buildRailButtonMouseReleased
        if(mouseOnBuildRailButton&&buttonPressed) {
            buildRailButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            buildRailButton.setLocation(buildRailButton.getX() + 1, buildRailButton.getY()); 
            //make sure it's even possible to build a rail first
            if(!currPlayer.maxRailsBuilt()){
                if (!game_Frame.getIgnoreResources() && currPlayer.getOre() > 0 && currPlayer.getLumber() > 0) {
                    game_Frame.buildRail();
                    gameFrame.addToInfo("Select a location for your rail.",true);
                    currPlayer.minusOre();
                    currPlayer.minusLumber();
                    this.setVisible(false);
                } else if(game_Frame.getIgnoreResources()){ 
                    game_Frame.buildRail();
                    gameFrame.addToInfo("Select a location for your rail.",true);
                    this.setVisible(false);
                }else{
                    //you don't have enough resources
                    if (scd != null && scd.isVisible()) {
                        scd.setVisible(false);
                    }
                    scd = new SettlersConfirmDialog(9);
                    scd.setVisible(true);
                }
            } else if (currPlayer.maxRailsBuilt()){
                //you have max rails already error
                if (scd != null && scd.isVisible()) {
                    scd.setVisible(false);
                }
                scd = new SettlersConfirmDialog(12);
                scd.setVisible(true);
            }            
            if (currPlayer.getLumber() < 1 || currPlayer.getOre() < 1) {
                buildRailButton.setEnabled(false);
            }
            if (currPlayer.getCattle() < 1 || currPlayer.getCoal() < 1) {
                buildDPCardButton.setEnabled(false);
            }
            if (currPlayer.getWheat() < 1 || currPlayer.getCattle() < 1 || currPlayer.getLumber() < 1) {
                buildSettlerButton.setEnabled(false);
            }
            if (currPlayer.getOre() < 1 || currPlayer.getCoal() < 1 || currPlayer.getLumber() < 1) {
                buildTrainButton.setEnabled(false);
            }
            if (currPlayer.getWheat() < 1 || currPlayer.getSettlers() == 0) {
                buildMoveSettlerButton.setEnabled(false);
            }
            if (currPlayer.getCoal() < 1) {
                buildMoveTrainButton.setEnabled(false);
            }
        }
    }//GEN-LAST:event_buildRailButtonMouseReleased
    private void buildRailButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buildRailButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buildRailButtonActionPerformed
    private void infoRailButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_infoRailButtonMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_infoRailButtonMouseEntered
    private void infoRailButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_infoRailButtonMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_infoRailButtonMouseExited
    private void infoRailButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_infoRailButtonMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_infoRailButtonMousePressed
    private void infoRailButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_infoRailButtonMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_infoRailButtonMouseReleased
    private void infoRailButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_infoRailButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_infoRailButtonActionPerformed
    private void buildTrainButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buildTrainButton1MouseReleased
        if(buildTrainButton.isAble()){
            //make sure you have enough resources
            if(currPlayer.getLumber()>0 && currPlayer.getOre()>0 && 
                    currPlayer.getCoal()>0 && !game_Frame.getIgnoreResources()){
                if (currPlayer.getNumOfTrains() < 2) {
                    game_Frame.getRails()[0].setAllTrainsInvisible();
                    game_Frame.getRails()[0].setAllCityBuildTrain(true);
                    gameFrame.addToInfo("Select a rail to build your train", true);
                    this.setVisible(false);
                    currPlayer.minusOre();
                    currPlayer.minusCoal();
                    currPlayer.minusLumber();
                    game_Frame.buildingTrain(true, false);
                } else {
                    //you have 2 trains already error
                    if (scd != null && scd.isVisible()) {
                        scd.setVisible(false);
                    }
                    scd = new SettlersConfirmDialog(14, currPlayer, game_Frame, this);
                    scd.setVisible(true);
                }
            } else if (game_Frame.getIgnoreResources()) {
                if (currPlayer.getNumOfTrains() < 2) {
                    game_Frame.getRails()[0].setAllTrainsInvisible();
                    game_Frame.getRails()[0].setAllCityBuildTrain(true);
                    gameFrame.addToInfo("Select a rail to build your train", true);
                    this.setVisible(false);
                    currPlayer.minusOre();
                    currPlayer.minusCoal();
                    currPlayer.minusLumber();
                    game_Frame.buildingTrain(true, false);
                } else {
                    //you have 2 trains already error
                    if (scd != null && scd.isVisible()) {
                        scd.setVisible(false);
                    }
                    scd = new SettlersConfirmDialog(14, currPlayer, game_Frame, this);
                    scd.setVisible(true);
                }
            } else {
                //you don't have enough resources
                if (scd != null && scd.isVisible()) {
                    scd.setVisible(false);
                }
                scd = new SettlersConfirmDialog(9);
                scd.setVisible(true);            
            }
            if (currPlayer.getLumber() < 1 || currPlayer.getOre() < 1) {
                buildRailButton.setEnabled(false);
            }
            if (currPlayer.getCattle() < 1 || currPlayer.getCoal() < 1) {
                buildDPCardButton.setEnabled(false);
            }
            if (currPlayer.getWheat() < 1 || currPlayer.getCattle() < 1 || currPlayer.getLumber() < 1) {
                buildSettlerButton.setEnabled(false);
            }
            if (currPlayer.getOre() < 1 || currPlayer.getCoal() < 1 || currPlayer.getLumber() < 1) {
                buildTrainButton.setEnabled(false);
            }
            if (currPlayer.getWheat() < 1 || currPlayer.getSettlers() == 0) {
                buildMoveSettlerButton.setEnabled(false);
            }
            if (currPlayer.getCoal() < 1) {
                buildMoveTrainButton.setEnabled(false);
            }
        }
    }//GEN-LAST:event_buildTrainButton1MouseReleased
    private void buildTrainButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buildTrainButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buildTrainButton1ActionPerformed
    private void infoTrainButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_infoTrainButtonMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_infoTrainButtonMouseEntered
    private void infoTrainButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_infoTrainButtonMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_infoTrainButtonMouseExited
    private void infoTrainButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_infoTrainButtonMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_infoTrainButtonMousePressed
    private void infoTrainButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_infoTrainButtonMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_infoTrainButtonMouseReleased
    private void infoTrainButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_infoTrainButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_infoTrainButtonActionPerformed
    private void infoMoveTrainButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_infoMoveTrainButtonMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_infoMoveTrainButtonMouseEntered
    private void infoMoveTrainButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_infoMoveTrainButtonMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_infoMoveTrainButtonMouseExited
    private void infoMoveTrainButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_infoMoveTrainButtonMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_infoMoveTrainButtonMousePressed
    private void infoMoveTrainButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_infoMoveTrainButtonMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_infoMoveTrainButtonMouseReleased
    private void infoMoveTrainButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_infoMoveTrainButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_infoMoveTrainButtonActionPerformed
    private void buildMoveTrainButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buildMoveTrainButton1MouseReleased
        if (buildMoveTrainButton.isAble()) {
            if (currPlayer.getCoal() > 0 && !game_Frame.getIgnoreResources()) {
                currPlayer.minusCoal();
                this.setVisible(false);
                if (currPlayer.getNumOfTrains() == 2) {
                    //choose train
                    game_Frame.setMovingTrain(true);
                    game_Frame.buildingTrain(true, false);
                    game_Frame.getRails()[0].setAllTrainsInvisible();
                    gameFrame.addToInfo("Select the train you'd like to move.", true);
                    ArrayList<Rail> trainRails = game_Frame.getCurrPlayer().getTrainRails();
                    for (int i = 0; i < trainRails.size(); i++) {
                        if (trainRails.get(i).isBuilt() && trainRails.get(i).getTrainOneColor() == currPlayer.getColor()
                                || trainRails.get(i).getTrainTwoColor() == currPlayer.getColor()) {
                            trainRails.get(i).setTrainHighlight(currPlayer.getColor());
                            trainRails.get(i).setMoveTrain(true);
                            trainRails.get(i).setNoMoveToCity(true);
                        }
                    }
                    this.setVisible(false);
                } else {
                    //where to move
                    game_Frame.setMovingTrain(true);
                    game_Frame.buildingTrain(true, false);
                    gameFrame.addToInfo("Select the rail to which you'd like to move.", true);
                    Rail[] trainRails = game_Frame.getRails();
                    for (int i = 0; i < trainRails.length; i++) {
                        if (trainRails[i].isBuilt() && (trainRails[i].getTrainOneColor() == currPlayer.getColor()
                                || trainRails[i].getTrainTwoColor() == currPlayer.getColor())) {
                            trainRails[i].setAllBuildTrainWithin(true, 3);
                            trainRails[i].buildTrain();
                            trainRails[i].removeTrain(currPlayer.getColor());
                            currPlayer.minusTrains();
                            game_Frame.getCurrPlayer().setMovingFromRail(trainRails[i]);
                            trainRails[i].setOnTheCusp(true);
                        }
                    }
                }
            } else if (game_Frame.getIgnoreResources()) {
                //make sure you have at least one train
                this.setVisible(false);
                if (currPlayer.getNumOfTrains() == 2) {
                    //choose train
                    game_Frame.setMovingTrain(true);
                    game_Frame.buildingTrain(true, false);
                    game_Frame.getRails()[0].setAllTrainsInvisible();
                    gameFrame.addToInfo("Select the train you'd like to move.", true);
                    ArrayList<Rail> trainRails = game_Frame.getCurrPlayer().getTrainRails();
                    for (int i = 0; i < trainRails.size(); i++) {
                        if (trainRails.get(i).getTrainOneColor() == currPlayer.getColor()
                                || trainRails.get(i).getTrainTwoColor() == currPlayer.getColor()) {
                            trainRails.get(i).setTrainHighlight(currPlayer.getColor());
                            trainRails.get(i).setMoveTrain(true);
                            trainRails.get(i).setNoMoveToCity(true);
                        }
                    }
                    this.setVisible(false);
                } else {
                    //where to move
                    game_Frame.setMovingTrain(true);
                    game_Frame.buildingTrain(true, false);
                    gameFrame.addToInfo("Select the rail to which you'd like to move.", true);
                    Rail[] trainRails = game_Frame.getRails();
                    for (int i = 0; i < trainRails.length; i++) {
                        if (trainRails[i].getTrainOneColor() == currPlayer.getColor()
                                || trainRails[i].getTrainTwoColor() == currPlayer.getColor()) {
                            trainRails[i].setAllBuildTrainWithin(true, 3);
                            trainRails[i].buildTrain();
                            trainRails[i].removeTrain(currPlayer.getColor());
                            currPlayer.minusSettlers();
                            trainRails[i].setOnTheCusp(true);
                        }
                    }
                }
            } else {
                //you don't have enough resources
                if (scd != null && scd.isVisible()) {
                    scd.setVisible(false);
                }
                scd = new SettlersConfirmDialog(9);
                scd.setVisible(true);
            }
            if (currPlayer.getLumber() < 1 || currPlayer.getOre() < 1) {
                buildRailButton.setEnabled(false);
            }
            if (currPlayer.getCattle() < 1 || currPlayer.getCoal() < 1) {
                buildDPCardButton.setEnabled(false);
            }
            if (currPlayer.getWheat() < 1 || currPlayer.getCattle() < 1 || currPlayer.getLumber() < 1) {
                buildSettlerButton.setEnabled(false);
            }
            if (currPlayer.getOre() < 1 || currPlayer.getCoal() < 1 || currPlayer.getLumber() < 1) {
                buildTrainButton.setEnabled(false);
            }
            if (currPlayer.getWheat() < 1 || currPlayer.getSettlers() == 0) {
                buildMoveSettlerButton.setEnabled(false);
            }
            if (currPlayer.getCoal() < 1) {
                buildMoveTrainButton.setEnabled(false);
            }
        }
    }//GEN-LAST:event_buildMoveTrainButton1MouseReleased
    private void buildMoveTrainButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buildMoveTrainButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buildMoveTrainButton1ActionPerformed
    private void buildDPCardButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buildDPCardButton1MouseReleased
        if (buildDPCardButton.isAble()) {
            if (currPlayer.getCattle() > 0 && currPlayer.getCoal() > 0 && !game_Frame.getIgnoreResources()) {
                currPlayer.minusCattle();
                currPlayer.minusCoal();
            }else{
                //you don't have enough resources
                if(dpcw!=null && dpcw.isVisible()){
                    dpcw.closeWindows();
                    dpcw.setVisible(false);
                }
                if (scd != null && scd.isVisible()) {
                    scd.setVisible(false);
                }
                scd = new SettlersConfirmDialog(9);
                scd.setVisible(true);
                return;
            }
            if (DevelopmentCard.cardsRemaining()) {
                if(!extraordinaryBuildingPhase) {
                    DevelopmentCard dpcard = new DevelopmentCard(game_Frame);
                    currPlayer.plusDPCard(dpcard);
                    if (dpcw != null && dpcw.isVisible()) {
                        dpcw.closeWindows();
                        dpcw.setVisible(false);
                    }
                    DevelopmentCardInfo d = new DevelopmentCardInfo(dpcard.getCard());
                    d.setVisible(true);
                    dpcw = new developmentCardWindow(currPlayer.getDPCards(), game_Frame, this, d);
                    dpcw.setVisible(true);
                }else{
                    DevelopmentCard dpcard = new DevelopmentCard(game_Frame);
                    currPlayer.plusDPCard(dpcard);
                    DevelopmentCardInfo d = new DevelopmentCardInfo(dpcard.getCard());
                    d.setVisible(true);
                }
            } else {
                //no development cards left
                if(dpcw!=null && dpcw.isVisible()){
                    dpcw.closeWindows();
                    dpcw.setVisible(false);
                }
                if (scd != null && scd.isVisible()) {
                    scd.setVisible(false);
                }
                scd = new SettlersConfirmDialog(15);
                scd.setVisible(true);
                currPlayer.plusCattle();
                currPlayer.plusCoal();
            }
            if (currPlayer.getLumber() < 1 || currPlayer.getOre() < 1) {
                buildRailButton.setEnabled(false);
            }
            if (currPlayer.getCattle() < 1 || currPlayer.getCoal() < 1) {
                buildDPCardButton.setEnabled(false);
            }
            if (currPlayer.getWheat() < 1 || currPlayer.getCattle() < 1 || currPlayer.getLumber() < 1) {
                buildSettlerButton.setEnabled(false);
            }
            if (currPlayer.getOre() < 1 || currPlayer.getCoal() < 1 || currPlayer.getLumber() < 1) {
                buildTrainButton.setEnabled(false);
            }
            if (currPlayer.getWheat() < 1 || currPlayer.getSettlers() == 0) {
                buildMoveSettlerButton.setEnabled(false);
            }
            if (currPlayer.getCoal() < 1) {
                buildMoveTrainButton.setEnabled(false);
            }
        }
    }//GEN-LAST:event_buildDPCardButton1MouseReleased
    private void infoDPCardButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_infoDPCardButton1MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_infoDPCardButton1MouseReleased
    private void buildOkayButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buildOkayButtonMouseEntered
        mouseOnBuildOkayButton=true;
}//GEN-LAST:event_buildOkayButtonMouseEntered
    private void buildOkayButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buildOkayButtonMouseExited
        if(mouseOnBuildOkayButton&&buttonPressed){
            buildOkayButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            buildOkayButton.setLocation(buildOkayButton.getX() + 1, buildOkayButton.getY());
        }
        mouseOnBuildOkayButton=false;
        buttonPressed=false;
}//GEN-LAST:event_buildOkayButtonMouseExited
    private void buildOkayButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buildOkayButtonMousePressed
        gameFrame.playSound("click");
        buildOkayButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        buildOkayButton.setLocation(buildOkayButton.getX()-1, buildOkayButton.getY());
        buttonPressed=true;
}//GEN-LAST:event_buildOkayButtonMousePressed
    private void buildOkayButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buildOkayButtonMouseReleased
        buttonPressed=false;
        if(mouseOnBuildOkayButton) {
            buildOkayButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            buildOkayButton.setLocation(buildOkayButton.getX() + 1, buildOkayButton.getY());
            dpcw.closeWindows();
            dpcw.setVisible(false);
            this.setVisible(false);
            if(!extraordinaryBuildingPhase){
                game_Frame.enableMenu();
            } else {
                if (game_Frame.getExtraordinaryBuildingPhaseNum() != game_Frame.getNumberOfPlayers() - 1) {
                    if (!game_Frame.getIgnoreBuildingPhase()) {
                        //move backwards
                        if (game_Frame.getCurrPlayer() == game_Frame.getOrderOfPlayers()[0] && game_Frame.getNumberOfPlayers() == 4) {
                            game_Frame.setCurrPlayer(game_Frame.getOrderOfPlayers()[3]);
                            game_Frame.getCurrPlayer().setTurn(true);
                        } else if (game_Frame.getNumberOfPlayers() == 3 && game_Frame.getCurrPlayer() == game_Frame.getOrderOfPlayers()[0]) {
                            game_Frame.setCurrPlayer(game_Frame.getOrderOfPlayers()[2]);
                            game_Frame.getCurrPlayer().setTurn(true);
                        } else if (game_Frame.getCurrPlayer() == game_Frame.getOrderOfPlayers()[1]) {
                            game_Frame.setCurrPlayer(game_Frame.getOrderOfPlayers()[0]);
                            game_Frame.getCurrPlayer().setTurn(true);
                        } else if (game_Frame.getCurrPlayer() == game_Frame.getOrderOfPlayers()[2]) {
                            game_Frame.setCurrPlayer(game_Frame.getOrderOfPlayers()[1]);
                            game_Frame.getCurrPlayer().setTurn(true);
                        } else if (game_Frame.getCurrPlayer() == game_Frame.getOrderOfPlayers()[3]) {
                            game_Frame.setCurrPlayer(game_Frame.getOrderOfPlayers()[2]);
                            game_Frame.getCurrPlayer().setTurn(true);
                        }
                        game_Frame.setBackgroundPlayer();
                        game_Frame.getCurrPlayer().extraordinaryBuildingPhase();
                        game_Frame.setExtraordinaryBuildingPhaseNum(game_Frame.getExtraordinaryBuildingPhaseNum()+1);
                    } else {
                        game_Frame.nextPlayer();
                        game_Frame.setRolled(false);
                    }
                } else {
                    game_Frame.setExtraordinaryBuildingPhaseNum(0);
                    if (game_Frame.getCurrPlayer() == game_Frame.getOrderOfPlayers()[0] && game_Frame.getNumberOfPlayers() == 4) {
                        game_Frame.setCurrPlayer(game_Frame.getOrderOfPlayers()[3]);
                        game_Frame.getCurrPlayer().setTurn(true);
                    } else if (game_Frame.getNumberOfPlayers() == 3 && game_Frame.getCurrPlayer() == game_Frame.getOrderOfPlayers()[0]) {
                        game_Frame.setCurrPlayer(game_Frame.getOrderOfPlayers()[2]);
                        game_Frame.getCurrPlayer().setTurn(true);
                    } else if (game_Frame.getCurrPlayer() == game_Frame.getOrderOfPlayers()[1]) {
                        game_Frame.setCurrPlayer(game_Frame.getOrderOfPlayers()[0]);
                        game_Frame.getCurrPlayer().setTurn(true);
                    } else if (game_Frame.getCurrPlayer() == game_Frame.getOrderOfPlayers()[2]) {
                        game_Frame.setCurrPlayer(game_Frame.getOrderOfPlayers()[1]);
                        game_Frame.getCurrPlayer().setTurn(true);
                    } else if (game_Frame.getCurrPlayer() == game_Frame.getOrderOfPlayers()[3]) {
                        game_Frame.setCurrPlayer(game_Frame.getOrderOfPlayers()[2]);
                        game_Frame.getCurrPlayer().setTurn(true);
                    }
                    game_Frame.nextPlayer();
                    game_Frame.setRolled(false);
                }
            }
        }
}//GEN-LAST:event_buildOkayButtonMouseReleased
    private void buildOkayButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buildOkayButtonActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_buildOkayButtonActionPerformed
    private void playDPCardButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playDPCardButton1MouseReleased
        if (playDPCardButton.isAble()) {
            if (dpcw != null && dpcw.isVisible()) {
                dpcw.closeWindows();
                dpcw.setVisible(false);
            }
            dpcw = new developmentCardWindow(currPlayer.getDPCards(),game_Frame,this,null);
            dpcw.setVisible(true);
        }
    }//GEN-LAST:event_playDPCardButton1MouseReleased
    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        dpcw.closeWindows();
        dpcw.setVisible(false);
        this.setVisible(false);
        game_Frame.enableMenu();
    }//GEN-LAST:event_formWindowClosed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new buildWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel DPCardLabel;
    private javax.swing.JPanel DPCardPanel;
    private javax.swing.JLabel DPCardResourceOne;
    private javax.swing.JLabel DPCardResourceTwo;
    private javax.swing.JLabel backgroundLabel;
    private javax.swing.JButton buildDPCardButton1;
    private javax.swing.JButton buildMoveSettlerButton;
    private javax.swing.JButton buildMoveTrainButton1;
    private javax.swing.JButton buildOkayButton;
    private javax.swing.JButton buildRailButton;
    private javax.swing.JButton buildSettlerButton;
    private javax.swing.JButton buildTrainButton1;
    private javax.swing.JLabel buildingCostsLabel;
    private javax.swing.JLabel costDPCardLabel;
    private javax.swing.JLabel costMoveSettlerLabel;
    private javax.swing.JPanel costMoveSettlerPanel;
    private javax.swing.JLabel costMoveTrainLabel;
    private javax.swing.JPanel costMoveTrainPanel;
    private javax.swing.JPanel costPanelSettler;
    private javax.swing.JPanel costPanelTrain;
    private javax.swing.JLabel costRailLabel;
    private javax.swing.JPanel costRailPanel;
    private javax.swing.JLabel costSettlerLabel;
    private javax.swing.JLabel costTrainLabel;
    private javax.swing.JPanel developmentCardPanel;
    private javax.swing.JButton infoDPCardButton1;
    private javax.swing.JButton infoMoveSettlerButton;
    private javax.swing.JButton infoMoveTrainButton;
    private javax.swing.JButton infoRailButton;
    private javax.swing.JButton infoSettlersButton;
    private javax.swing.JButton infoTrainButton;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel moveSettlerResource;
    private javax.swing.JLabel moveTrainResource;
    private javax.swing.JLabel movingSettlerArrow;
    private javax.swing.JLabel movingSettlerLabel;
    private javax.swing.JPanel movingSettlerPanel;
    private javax.swing.JLabel movingTrainArrow;
    private javax.swing.JLabel movingTrainLabel;
    private javax.swing.JPanel movingTrainPanel;
    private javax.swing.JButton playDPCardButton1;
    private javax.swing.JLabel railIconOne;
    private javax.swing.JLabel railIconTwo;
    private javax.swing.JPanel railPanel;
    private javax.swing.JLabel settlerIconOne;
    private javax.swing.JLabel settlerIconTwo;
    private javax.swing.JLabel settlerLabel;
    private javax.swing.JLabel settlerLabel1;
    private javax.swing.JPanel settlerPanel;
    private javax.swing.JLabel settlerResourceOne;
    private javax.swing.JLabel settlerResourceOne1;
    private javax.swing.JLabel settlerResourceThree;
    private javax.swing.JLabel settlerResourceTwo;
    private javax.swing.JLabel settlerResourceTwo2;
    private javax.swing.JLabel spacesSettlerLabel;
    private javax.swing.JLabel spacesTrainLabel;
    private javax.swing.JLabel trainIconOne;
    private javax.swing.JLabel trainIconTwo;
    private javax.swing.JLabel trainLabel;
    private javax.swing.JPanel trainPanel;
    private javax.swing.JLabel trainResourceOne;
    private javax.swing.JLabel trainResourceThree;
    private javax.swing.JLabel trainResourceTwo;
    // End of variables declaration//GEN-END:variables

}

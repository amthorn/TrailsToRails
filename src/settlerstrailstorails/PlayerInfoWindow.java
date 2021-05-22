
/*
 * PlayerInfoWindow.java
 *
 * Created on Sep 30, 2013, 7:00:30 PM
 */
package settlerstrailstorails;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;

/**
 *
 * @author User 1
 */
public class PlayerInfoWindow extends javax.swing.JFrame {
    
    boolean mouseOnBackButton,buttonPressed;
    Player player;
    NumberFormat nFormat= NumberFormat.getPercentInstance(Locale.ENGLISH);

    /** Creates new form PlayerInfoWindow */
    public PlayerInfoWindow() {
        initComponents();
    }
    PlayerInfoWindow(Player p) {
        player=p;    
        initComponents();
        CityHex[] cities = p.getOwnedCities();
        ArrayList<String> firstRow=new ArrayList<String>();
        ArrayList<String> secondRow=new ArrayList<String>();
        ArrayList<String> thirdRow=new ArrayList<String>();
        ArrayList<String> fourthRow=new ArrayList<String>();
        ArrayList<String> fifthRow=new ArrayList<String>();
        for(int i=0;i<cities.length;i++){
            if(i<2 && cities[i]!=null){
                firstRow.add(cities[i].getName());
            }else if(i<5 && cities[i]!=null){
                secondRow.add(cities[i].getName());
            }else if(i<8 && cities[i]!=null){
                thirdRow.add(cities[i].getName());
            }else if(i<10 && cities[i]!=null){
                fourthRow.add(cities[i].getName());
            }else if(i<12 && cities[i]!=null){
                fifthRow.add(cities[i].getName());
            }
        }
        if(firstRow.size()==1){
            citiesOwnedFirstRow.setText(firstRow.get(0));
        }if(firstRow.size()==2){
            citiesOwnedFirstRow.setText(firstRow.get(0)+", "+firstRow.get(1));
        }
        if(secondRow.size()==1){
            citiesOwnedSecondRow.setText(secondRow.get(0));
        }
        if(secondRow.size()==2){
            citiesOwnedSecondRow.setText(secondRow.get(0)+", "+secondRow.get(1));            
        }
        if(secondRow.size()==3){
            citiesOwnedSecondRow.setText(secondRow.get(0)+", "+secondRow.get(1)+", "+secondRow.get(2));            
        }        
        if(thirdRow.size()==1){
            citiesOwnedThirdRow.setText(thirdRow.get(0));            
        }
        if(thirdRow.size()==2){
            citiesOwnedThirdRow.setText(thirdRow.get(0)+", "+thirdRow.get(1));            
        }
        if(thirdRow.size()==3){
            citiesOwnedThirdRow.setText(thirdRow.get(0)+", "+thirdRow.get(1)+", "+thirdRow.get(2));            
        }               
        if(fourthRow.size()==1){
            citiesOwnedFourthRow.setText(fourthRow.get(0));            
        }
        if(fourthRow.size()==2){
            citiesOwnedFourthRow.setText(fourthRow.get(0)+", "+fourthRow.get(1));            
        }
        if(fifthRow.size()==1){
            citiesOwnedFifthRow.setText(fifthRow.get(0));            
        }
        if(fifthRow.size()==2){
            citiesOwnedFifthRow.setText(fifthRow.get(0)+", "+fifthRow.get(1));            
        }
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        try{
            setIconImage(ImageIO.read(getClass().getResource("/settlerstrailstorails/resources/icon.png")));
        }catch(Exception e){
            
        }
        if (p.getHuman()) {
            humanVariableLabel.setText("Yes");
        } else {
            humanVariableLabel.setText("No");
        }
        if(p.getColor()==Player.Color.RED){
            railLabel.setIcon(new ImageIcon(loadImage("/settlerstrailstorails/resources/redRailHorizontal.png")));                    
            cityLabel.setIcon(new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/redHouse.png", Color.WHITE)));
            trainLabel.setIcon(new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/redTrain.png", Color.WHITE)));
        }else if(p.getColor()==Player.Color.GREEN){
            railLabel.setIcon(new ImageIcon(loadImage("/settlerstrailstorails/resources/greenRailHorizontal.png")));                    
            cityLabel.setIcon(new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/greenHouse.png", Color.WHITE)));
            trainLabel.setIcon(new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/greenTrain.png", Color.WHITE)));
        }else if(p.getColor()==Player.Color.WHITE){
            railLabel.setIcon(new ImageIcon(loadImage("/settlerstrailstorails/resources/whiteRailHorizontal.png")));                    
            cityLabel.setIcon(new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/whiteHouse.png", Color.WHITE)));
            trainLabel.setIcon(new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/whiteTrain.png", Color.WHITE)));
        }else if(p.getColor()==Player.Color.ORANGE){
            railLabel.setIcon(new ImageIcon(loadImage("/settlerstrailstorails/resources/orangeRailHorizontal.png")));                    
            cityLabel.setIcon(new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/orangeHouse.png", Color.WHITE)));
            trainLabel.setIcon(new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/orangeTrain.png", Color.WHITE)));
        }
        if (p.getBlockedByRobber()) {
            blockedByRobberVarLabel.setText("Yes");
        } else {
            blockedByRobberVarLabel.setText("No");            
        }
    }
    public final BufferedImage loadImage(String ref) {  //<editor-fold>
        BufferedImage bimg = null;  
        try {    
            bimg = ImageIO.read(getClass().getResource(ref));  
        } catch (Exception e) {
            System.err.print(e);
        }
        return bimg;  
    }  //</editor-fold>
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
        okButton = new javax.swing.JButton();
        trainLabel = new javax.swing.JLabel();
        cityLabel = new javax.swing.JLabel();
        railLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        colorLabel = new javax.swing.JLabel();
        numberOfCitiesLabel = new javax.swing.JLabel();
        numberOfCitiesVariableLabel = new javax.swing.JLabel();
        goodsDeliveredVariableLabel = new javax.swing.JLabel();
        goodsReadyVariableLabel = new javax.swing.JLabel();
        humanVariableLabel = new javax.swing.JLabel();
        resourceCardsVariableLabel = new javax.swing.JLabel();
        colorVariableLabel = new javax.swing.JLabel();
        citiesOwnedLabel = new javax.swing.JLabel();
        citiesOwnedFirstRow = new javax.swing.JLabel();
        citiesOwnedSecondRow = new javax.swing.JLabel();
        citiesOwnedThirdRow = new javax.swing.JLabel();
        citiesOwnedFourthRow = new javax.swing.JLabel();
        citiesOwnedFifthRow = new javax.swing.JLabel();
        goodsDeliveredLabel = new javax.swing.JLabel();
        blockedByRobberLabel = new javax.swing.JLabel();
        blockedByRobberVarLabel = new javax.swing.JLabel();
        goodsReadyLabel = new javax.swing.JLabel();
        humanLabel = new javax.swing.JLabel();
        resourceCardsLabel = new javax.swing.JLabel();
        developmentCardsLabel = new javax.swing.JLabel();
        developmentVariableCardsLabel = new javax.swing.JLabel();
        numberOfSettlersVariableLabel = new javax.swing.JLabel();
        maxNumberOfSettlersVariableLabel = new javax.swing.JLabel();
        numberOfTrainVariableLabel = new javax.swing.JLabel();
        maxNumberOfTrainVariableLabel = new javax.swing.JLabel();
        numberOfRailsVariableLabel = new javax.swing.JLabel();
        numberOfRailsVariableLabel1 = new javax.swing.JLabel();
        numberOfRailsLabel = new javax.swing.JLabel();
        numberOfTrainsLabel = new javax.swing.JLabel();
        numberOfSettlersLabel = new javax.swing.JLabel();
        goldVarLabel = new javax.swing.JLabel();
        goldLabel = new javax.swing.JLabel();
        probabilitiesLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lumberProbVarLabel = new javax.swing.JLabel();
        coalProbVarLabel = new javax.swing.JLabel();
        cattleProbVarLabel = new javax.swing.JLabel();
        wheatProbVarLabel = new javax.swing.JLabel();
        oreProbVarLabel = new javax.swing.JLabel();
        lumberPicture = new javax.swing.JLabel();
        coalPicture = new javax.swing.JLabel();
        cattlePicture = new javax.swing.JLabel();
        wheatPicture = new javax.swing.JLabel();
        orePicture = new javax.swing.JLabel();
        anyLabel = new javax.swing.JLabel();
        goldProbLabel = new javax.swing.JLabel();
        anyVarLabel = new javax.swing.JLabel();
        goldProbVarLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        backgroundLabel = new javax.swing.JLabel();

        setAlwaysOnTop(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        mainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        okButton.setBackground(new java.awt.Color(239, 228, 176));
        okButton.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 24));
        okButton.setText("X");
        okButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        okButton.setContentAreaFilled(false);
        okButton.setFocusable(false);
        okButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                okButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                okButtonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                okButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                okButtonMouseReleased(evt);
            }
        });
        mainPanel.add(okButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 15, 40, 40));

        trainLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        mainPanel.add(trainLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(625, 19, -1, -1));

        cityLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        mainPanel.add(cityLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 25, -1, -1));

        railLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        mainPanel.add(railLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 27, -1, -1));

        nameLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 24));
        nameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nameLabel.setText(player.getName());
        mainPanel.add(nameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 710, -1));

        colorLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        colorLabel.setText("Color:");
        mainPanel.add(colorLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 68, -1, -1));

        numberOfCitiesLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        numberOfCitiesLabel.setText("Number of Cities:");
        mainPanel.add(numberOfCitiesLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 89, -1, -1));

        numberOfCitiesVariableLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        numberOfCitiesVariableLabel.setText(String.valueOf(player.getNumberOfCities()));
        mainPanel.add(numberOfCitiesVariableLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 89, -1, -1));

        goodsDeliveredVariableLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        goodsDeliveredVariableLabel.setText(String.valueOf(player.getGoodsDelivered()));
        mainPanel.add(goodsDeliveredVariableLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 223, -1, -1));

        goodsReadyVariableLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        goodsReadyVariableLabel.setText(String.valueOf(player.getReadyGoods()));
        mainPanel.add(goodsReadyVariableLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 254, -1, -1));

        humanVariableLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        mainPanel.add(humanVariableLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(83, 285, -1, -1));

        resourceCardsVariableLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        resourceCardsVariableLabel.setText(String.valueOf(player.getResourceCards()));
        mainPanel.add(resourceCardsVariableLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 317, -1, -1));

        colorVariableLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        colorVariableLabel.setText(player.getColor().toString());
        mainPanel.add(colorVariableLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(73, 68, -1, -1));

        citiesOwnedLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        citiesOwnedLabel.setText("Cities Owned:");
        mainPanel.add(citiesOwnedLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 111, -1, -1));

        citiesOwnedFirstRow.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        mainPanel.add(citiesOwnedFirstRow, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 113, -1, -1));

        citiesOwnedSecondRow.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        mainPanel.add(citiesOwnedSecondRow, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 127, -1, -1));

        citiesOwnedThirdRow.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        mainPanel.add(citiesOwnedThirdRow, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 143, -1, -1));

        citiesOwnedFourthRow.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        mainPanel.add(citiesOwnedFourthRow, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 159, -1, -1));

        citiesOwnedFifthRow.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        mainPanel.add(citiesOwnedFifthRow, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 175, -1, -1));

        goodsDeliveredLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        goodsDeliveredLabel.setText("Goods Delivered:");
        mainPanel.add(goodsDeliveredLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 223, -1, -1));

        blockedByRobberLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        blockedByRobberLabel.setText("Blocked By Robber: ");
        mainPanel.add(blockedByRobberLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 193, -1, -1));

        blockedByRobberVarLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        mainPanel.add(blockedByRobberVarLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 193, -1, -1));

        goodsReadyLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        goodsReadyLabel.setText("Goods Ready for Delivery:");
        mainPanel.add(goodsReadyLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 254, -1, -1));

        humanLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        humanLabel.setText("Human:");
        mainPanel.add(humanLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 285, -1, -1));

        resourceCardsLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        resourceCardsLabel.setText("Resource Cards:");
        mainPanel.add(resourceCardsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 317, -1, -1));

        developmentCardsLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        developmentCardsLabel.setText("Development Cards:");
        mainPanel.add(developmentCardsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 348, -1, -1));

        developmentVariableCardsLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        developmentVariableCardsLabel.setText(String.valueOf(player.getNumberOfDP()));
        mainPanel.add(developmentVariableCardsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(182, 348, -1, -1));

        numberOfSettlersVariableLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        numberOfSettlersVariableLabel.setText(String.valueOf(player.getSettlers()));
        mainPanel.add(numberOfSettlersVariableLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(178, 444, -1, -1));

        maxNumberOfSettlersVariableLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        maxNumberOfSettlersVariableLabel.setText("(MAX: 2)");
        mainPanel.add(maxNumberOfSettlersVariableLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(222, 444, -1, -1));

        numberOfTrainVariableLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        numberOfTrainVariableLabel.setText(String.valueOf(player.getNumOfTrains()));
        mainPanel.add(numberOfTrainVariableLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(166, 413, -1, -1));

        maxNumberOfTrainVariableLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        maxNumberOfTrainVariableLabel.setText("(MAX: 2)");
        mainPanel.add(maxNumberOfTrainVariableLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 413, -1, -1));

        numberOfRailsVariableLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        numberOfRailsVariableLabel.setText(String.valueOf(player.getNumberOfRailsBuilt()));
        mainPanel.add(numberOfRailsVariableLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(156, 381, -1, -1));

        numberOfRailsVariableLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        numberOfRailsVariableLabel1.setText("(MAX: "+String.valueOf(player.getMaxNumOfRails()+")"));
        mainPanel.add(numberOfRailsVariableLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 381, -1, -1));

        numberOfRailsLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        numberOfRailsLabel.setText("Number of Rails:");
        mainPanel.add(numberOfRailsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 381, -1, -1));

        numberOfTrainsLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        numberOfTrainsLabel.setText("Number of Trains:");
        mainPanel.add(numberOfTrainsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 413, -1, -1));

        numberOfSettlersLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        numberOfSettlersLabel.setText("Number of Settlers:");
        mainPanel.add(numberOfSettlersLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 444, -1, -1));

        goldVarLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        goldVarLabel.setText(String.valueOf(player.getGold()));
        mainPanel.add(goldVarLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(67, 473, -1, -1));

        goldLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        goldLabel.setText("Gold:");
        mainPanel.add(goldLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 473, -1, -1));

        probabilitiesLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        probabilitiesLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        probabilitiesLabel.setText("Probabilities");
        mainPanel.add(probabilitiesLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 65, 324, -1));

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setOpaque(false);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lumberProbVarLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        lumberProbVarLabel.setText(nFormat.format(player.getLumberProb()));
        jPanel2.add(lumberProbVarLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 55, -1, -1));

        coalProbVarLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        coalProbVarLabel.setText(nFormat.format(player.getCoalProb()));
        jPanel2.add(coalProbVarLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 138, -1, -1));

        cattleProbVarLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        cattleProbVarLabel.setText(nFormat.format(player.getCattleProb()));
        jPanel2.add(cattleProbVarLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 221, -1, -1));

        wheatProbVarLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        wheatProbVarLabel.setText(nFormat.format(player.getWheatProb()));
        jPanel2.add(wheatProbVarLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 304, -1, -1));

        oreProbVarLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        oreProbVarLabel.setText(nFormat.format(player.getOreProb()));
        jPanel2.add(oreProbVarLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 387, -1, -1));

        lumberPicture.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        lumberPicture.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/lumberHalfSize.jpg"))); // NOI18N
        lumberPicture.setText(" =");
        jPanel2.add(lumberPicture, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 70, -1));

        coalPicture.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        coalPicture.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/coalHalfSize.jpg"))); // NOI18N
        coalPicture.setText(" =");
        jPanel2.add(coalPicture, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 113, 70, -1));

        cattlePicture.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        cattlePicture.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/cattleHalfSize.jpg"))); // NOI18N
        cattlePicture.setText(" =");
        jPanel2.add(cattlePicture, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 196, 70, -1));

        wheatPicture.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        wheatPicture.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/wheatHalfSize.jpg"))); // NOI18N
        wheatPicture.setText(" =");
        jPanel2.add(wheatPicture, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 279, 70, -1));

        orePicture.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        orePicture.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/oreHalfSize.jpg"))); // NOI18N
        orePicture.setText(" =");
        jPanel2.add(orePicture, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 362, 70, -1));

        anyLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        anyLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        anyLabel.setText("any = ");
        jPanel2.add(anyLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 120, 60, -1));

        goldProbLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        goldProbLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        goldProbLabel.setText("gold = ");
        jPanel2.add(goldProbLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 280, 80, -1));

        anyVarLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        anyVarLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        anyVarLabel.setText(nFormat.format(player.getAnyProb()));
        jPanel2.add(anyVarLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 120, 60, -1));

        goldProbVarLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        goldProbVarLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        goldProbVarLabel.setText(nFormat.format(player.getNoneProb()));
        jPanel2.add(goldProbVarLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 279, 60, -1));

        mainPanel.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(365, 60, 335, 440));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setOpaque(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 331, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 436, Short.MAX_VALUE)
        );

        mainPanel.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 60, 335, 440));

        backgroundLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/playerInfoBackground.png"))); // NOI18N
        mainPanel.add(backgroundLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(mainPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, 520));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okButtonMouseEntered
        mouseOnBackButton=true;
}//GEN-LAST:event_okButtonMouseEntered

    private void okButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okButtonMouseExited
        if(mouseOnBackButton&&buttonPressed){
            okButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            okButton.setLocation(okButton.getX() + 1, okButton.getY());
        }
        mouseOnBackButton=false;
        buttonPressed=false;
}//GEN-LAST:event_okButtonMouseExited

    private void okButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okButtonMousePressed
        gameFrame.playSound("click");
        okButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        okButton.setLocation(okButton.getX()-1, okButton.getY());
        buttonPressed=true;
}//GEN-LAST:event_okButtonMousePressed

    private void okButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okButtonMouseReleased
        buttonPressed=false;
        if(mouseOnBackButton) {
            okButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            okButton.setLocation(okButton.getX() + 1, okButton.getY());
            this.setVisible(false);
        }
}//GEN-LAST:event_okButtonMouseReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new PlayerInfoWindow().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel anyLabel;
    private javax.swing.JLabel anyVarLabel;
    private javax.swing.JLabel backgroundLabel;
    private javax.swing.JLabel blockedByRobberLabel;
    private javax.swing.JLabel blockedByRobberVarLabel;
    private javax.swing.JLabel cattlePicture;
    private javax.swing.JLabel cattleProbVarLabel;
    private javax.swing.JLabel citiesOwnedFifthRow;
    private javax.swing.JLabel citiesOwnedFirstRow;
    private javax.swing.JLabel citiesOwnedFourthRow;
    private javax.swing.JLabel citiesOwnedLabel;
    private javax.swing.JLabel citiesOwnedSecondRow;
    private javax.swing.JLabel citiesOwnedThirdRow;
    private javax.swing.JLabel cityLabel;
    private javax.swing.JLabel coalPicture;
    private javax.swing.JLabel coalProbVarLabel;
    private javax.swing.JLabel colorLabel;
    private javax.swing.JLabel colorVariableLabel;
    private javax.swing.JLabel developmentCardsLabel;
    private javax.swing.JLabel developmentVariableCardsLabel;
    private javax.swing.JLabel goldLabel;
    private javax.swing.JLabel goldProbLabel;
    private javax.swing.JLabel goldProbVarLabel;
    private javax.swing.JLabel goldVarLabel;
    private javax.swing.JLabel goodsDeliveredLabel;
    private javax.swing.JLabel goodsDeliveredVariableLabel;
    private javax.swing.JLabel goodsReadyLabel;
    private javax.swing.JLabel goodsReadyVariableLabel;
    private javax.swing.JLabel humanLabel;
    private javax.swing.JLabel humanVariableLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lumberPicture;
    private javax.swing.JLabel lumberProbVarLabel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel maxNumberOfSettlersVariableLabel;
    private javax.swing.JLabel maxNumberOfTrainVariableLabel;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel numberOfCitiesLabel;
    private javax.swing.JLabel numberOfCitiesVariableLabel;
    private javax.swing.JLabel numberOfRailsLabel;
    private javax.swing.JLabel numberOfRailsVariableLabel;
    private javax.swing.JLabel numberOfRailsVariableLabel1;
    private javax.swing.JLabel numberOfSettlersLabel;
    private javax.swing.JLabel numberOfSettlersVariableLabel;
    private javax.swing.JLabel numberOfTrainVariableLabel;
    private javax.swing.JLabel numberOfTrainsLabel;
    private javax.swing.JButton okButton;
    private javax.swing.JLabel orePicture;
    private javax.swing.JLabel oreProbVarLabel;
    private javax.swing.JLabel probabilitiesLabel;
    private javax.swing.JLabel railLabel;
    private javax.swing.JLabel resourceCardsLabel;
    private javax.swing.JLabel resourceCardsVariableLabel;
    private javax.swing.JLabel trainLabel;
    private javax.swing.JLabel wheatPicture;
    private javax.swing.JLabel wheatProbVarLabel;
    // End of variables declaration//GEN-END:variables
}

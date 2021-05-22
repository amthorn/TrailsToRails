package settlerstrailstorails;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * UtilitiesWindow.java
 *
 * Created on Oct 3, 2013, 7:03:40 PM
 */
/**
 *
 * @author User 1
 */
public class UtilitiesWindow extends javax.swing.JFrame {
    
    boolean mouseOnBackButton, mouseOnNextButtonOne,
            mouseOnXButton,buttonPressed,oneButtonSelected=true,twoButtonSelected,
            threeButtonSelected,mouseOnTwoResourceButton,
            mouseOnThreeResourceButton,mouseOnSearchButton,
            lumberOneButtonSelected=true,lumberTwoButtonSelected=true,
            lumberThreeButtonSelected=true,coalOneButtonSelected,
            coalTwoButtonSelected,coalThreeButtonSelected,
            cattleOneButtonSelected,cattleTwoButtonSelected,
            cattleThreeButtonSelected,wheatOneButtonSelected,
            wheatTwoButtonSelected,wheatThreeButtonSelected,
            oreOneButtonSelected,oreTwoButtonSelected,oreThreeButtonSelected,
            purpleCity=false,cityUnowned=true;
    int numberOfResources=1;
    Hex.Resource resourceOne=Hex.Resource.LUMBER,resourceTwo,resourceThree;
    CityHex[] cities;
    CityHex bestPrefOne,bestPrefTwo,bestPrefThree,bestPrefOneTwo,bestPrefOneThree,
            bestPrefTwoThree,bestPrefOneTwoThree,bestPrefOverall;

    /** Creates new form UtilitiesWindow */
    UtilitiesWindow() {
        initComponents();
    }
    UtilitiesWindow(CityHex[] c){
        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(((dim.width/2)-(this.getSize().width/2)), ((dim.height/2)-((this.getSize().height/2)+20)));
        try{
            setIconImage(ImageIO.read(getClass().getResource("/settlerstrailstorails/resources/icon.png")));
        }catch(Exception e){
            
        }
        resourceThreePanel.setVisible(false);
        resourceTwoPanel.setVisible(false);
        cities=c;
    }
    /**
     * returns true if c has a greater probability of getting the resource r
     * if the probabilities are equal, returns the city with the best 
     * probabilities overall
     * 
     * if resource = null than just compare overall probabilities
     * than c2 does
     * @param c
     * @param c2
     * @param r
     * @return 
     */
    private boolean compareCities(CityHex c, CityHex c2, Hex.Resource r){
        //<editor-fold>
        if(c!=null && c2==null)return true;
        if(c==null)return true;
        if(r==null) {
            double totalCNull = 0, totalCNull2 = 0;
            if (c2.getHexOne() != null) {
                totalCNull2 += c2.getHexOne().getProbability();
            }
            if (c2.getHexTwo() != null) {
                totalCNull2 += c2.getHexTwo().getProbability();
            }
            if (c2.getHexThree() != null) {
                totalCNull2 += c2.getHexThree().getProbability();
            }
            if (c.getHexOne() != null) {
                totalCNull += c.getHexOne().getProbability();
            }
            if (c.getHexTwo() != null) {
                totalCNull += c.getHexTwo().getProbability();
            }
            if (c.getHexThree() != null) {
                totalCNull += c.getHexThree().getProbability();
            }
            if (totalCNull > totalCNull2) {
                return true;
            } else {
                return false;
            }      
        }
        if(cityHasResource(c,r)&&!cityHasResource(c2,r))return true;
        if(!cityHasResource(c,r)&&cityHasResource(c2,r))return false;        
        
        double totalC=0,totalC2=0;
        if(c2.getHexOne()!=null && c2.getHexOne().getResource()==r){
            totalC2+=c2.getHexOne().getProbability();
        }
        if(c2.getHexTwo()!=null && c2.getHexTwo().getResource()==r){
            totalC2+=c2.getHexTwo().getProbability();
        }
        if(c2.getHexThree()!=null && c2.getHexThree().getResource()==r){
            totalC2+=c2.getHexThree().getProbability();
        }
        if(c.getHexOne()!=null && c.getHexOne().getResource()==r){
            totalC+=c.getHexOne().getProbability();
        }
        if(c.getHexTwo()!=null && c.getHexTwo().getResource()==r){
            totalC+=c.getHexTwo().getProbability();
        }
        if(c.getHexThree()!=null && c.getHexThree().getResource()==r){
            totalC+=c.getHexThree().getProbability();
        }        
        if(totalC>totalC2){
            return true;
        }else if(totalC==totalC2){
            totalC = 0;
            totalC2 = 0;
            if (c2.getHexOne() != null) {
                totalC2 += c2.getHexOne().getProbability();
            }
            if (c2.getHexTwo() != null) {
                totalC2 += c2.getHexTwo().getProbability();
            }
            if (c2.getHexThree() != null) {
                totalC2 += c2.getHexThree().getProbability();
            }
            if (c.getHexOne() != null) {
                totalC += c.getHexOne().getProbability();
            }
            if (c.getHexTwo() != null) {
                totalC += c.getHexTwo().getProbability();
            }
            if (c.getHexThree() != null) {
                totalC += c.getHexThree().getProbability();
            }
            if(totalC>totalC2){
                return true;
            }else if(totalC==totalC2){
                //if the cities are perfectly equivalent
                //in all aspects of probability
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }//</editor-fold>
    /**
     * returns true if the city has a probability > 0 for getting
     * resource r. else false.
     * @param c
     * @param r
     * @return 
     */
    private boolean cityHasResource(CityHex c, Hex.Resource r){
        if(c==null)return false;
        if(c.getHexOne()!=null && c.getHexOne().getResource()==r && c.getHexOne().getProbability()>0){
            return true;
        }
        if(c.getHexTwo()!=null && c.getHexTwo().getResource()==r && c.getHexTwo().getProbability()>0){
            return true;
        }
        if(c.getHexThree()!=null && c.getHexThree().getResource()==r && c.getHexThree().getProbability()>0){
            return true;
        }
        return false;
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
        searchPanel = new javax.swing.JPanel();
        xButton = new javax.swing.JButton();
        whereShouldIBuildLabel = new javax.swing.JLabel();
        checkBoxPanel = new javax.swing.JPanel();
        purpleCityCheckBox = new javax.swing.JCheckBox();
        cityIsUnownedCheckBox = new javax.swing.JCheckBox();
        cityIsPurpleToolTip = new javax.swing.JLabel();
        cityIsUnownedToolTip = new javax.swing.JLabel();
        selectResourcePanel = new javax.swing.JPanel();
        howManyResourcesLabel = new javax.swing.JLabel();
        threeResourceToggleButton = new javax.swing.JToggleButton();
        oneResourceToggleButton = new javax.swing.JToggleButton();
        twoResourceToggleButton = new javax.swing.JToggleButton();
        resourceOnePanel = new javax.swing.JPanel();
        howManyResourcesLabel1 = new javax.swing.JLabel();
        lumberButtonOne = new javax.swing.JToggleButton();
        coalButtonOne = new javax.swing.JToggleButton();
        cattleButtonOne = new javax.swing.JToggleButton();
        oreButtonOne = new javax.swing.JToggleButton();
        wheatButtonOne = new javax.swing.JToggleButton();
        resourceTwoPanel = new javax.swing.JPanel();
        howManyResourcesLabel2 = new javax.swing.JLabel();
        lumberButtonTwo = new javax.swing.JToggleButton();
        coalButtonTwo = new javax.swing.JToggleButton();
        cattleButtonTwo = new javax.swing.JToggleButton();
        oreButtonTwo = new javax.swing.JToggleButton();
        wheatButtonTwo = new javax.swing.JToggleButton();
        resourceThreePanel = new javax.swing.JPanel();
        howManyResourcesLabel3 = new javax.swing.JLabel();
        lumberButtonThree = new javax.swing.JToggleButton();
        coalButtonThree = new javax.swing.JToggleButton();
        cattleButtonThree = new javax.swing.JToggleButton();
        oreButtonThree = new javax.swing.JToggleButton();
        wheatButtonThree = new javax.swing.JToggleButton();
        searchButton = new javax.swing.JButton();
        backgroundLabel = new javax.swing.JLabel();

        setFocusable(false);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        mainPanel.setOpaque(false);
        mainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        searchPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        xButton.setBackground(new java.awt.Color(239, 228, 176));
        xButton.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14));
        xButton.setText("X");
        xButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        xButton.setContentAreaFilled(false);
        xButton.setFocusable(false);
        xButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        xButton.setMaximumSize(new java.awt.Dimension(50, 50));
        xButton.setMinimumSize(new java.awt.Dimension(40, 40));
        xButton.setPreferredSize(new java.awt.Dimension(40, 40));
        xButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                xButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                xButtonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                xButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                xButtonMouseReleased(evt);
            }
        });
        xButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xButtonActionPerformed(evt);
            }
        });
        searchPanel.add(xButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(337, 17, 25, 25));

        whereShouldIBuildLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        whereShouldIBuildLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        whereShouldIBuildLabel.setText("Where should I build?");
        searchPanel.add(whereShouldIBuildLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 300, -1));

        checkBoxPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        checkBoxPanel.setOpaque(false);
        checkBoxPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        purpleCityCheckBox.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14));
        purpleCityCheckBox.setText("City is purple");
        purpleCityCheckBox.setFocusable(false);
        purpleCityCheckBox.setOpaque(false);
        purpleCityCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                purpleCityCheckBoxActionPerformed(evt);
            }
        });
        checkBoxPanel.add(purpleCityCheckBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        cityIsUnownedCheckBox.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14));
        cityIsUnownedCheckBox.setSelected(true);
        cityIsUnownedCheckBox.setText("City is unowned");
        cityIsUnownedCheckBox.setFocusable(false);
        cityIsUnownedCheckBox.setOpaque(false);
        cityIsUnownedCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cityIsUnownedCheckBoxActionPerformed(evt);
            }
        });
        checkBoxPanel.add(cityIsUnownedCheckBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, -1, -1));

        cityIsPurpleToolTip.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14));
        cityIsPurpleToolTip.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cityIsPurpleToolTip.setText("?");
        cityIsPurpleToolTip.setToolTipText("<html>If this box is checked, the search will only<br>\nreturn cities that are purple.");
        cityIsPurpleToolTip.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cityIsPurpleToolTip.setFocusable(false);
        checkBoxPanel.add(cityIsPurpleToolTip, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 13, 20, 20));

        cityIsUnownedToolTip.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14));
        cityIsUnownedToolTip.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cityIsUnownedToolTip.setText("?");
        cityIsUnownedToolTip.setToolTipText("<html>If this box is checked, the search will only<br>\nreturn cities that are unowned.");
        cityIsUnownedToolTip.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cityIsUnownedToolTip.setFocusable(false);
        checkBoxPanel.add(cityIsUnownedToolTip, new org.netbeans.lib.awtextra.AbsoluteConstraints(307, 13, 20, 20));

        searchPanel.add(checkBoxPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 340, 45));

        selectResourcePanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        selectResourcePanel.setOpaque(false);
        selectResourcePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        howManyResourcesLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14));
        howManyResourcesLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        howManyResourcesLabel.setText("How many resources are you concerned with?");
        selectResourcePanel.add(howManyResourcesLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 3, 340, -1));

        threeResourceToggleButton.setBackground(new java.awt.Color(239, 228, 176));
        threeResourceToggleButton.setFont(new java.awt.Font("Tahoma", 1, 11));
        threeResourceToggleButton.setText("3");
        threeResourceToggleButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        threeResourceToggleButton.setContentAreaFilled(false);
        threeResourceToggleButton.setFocusable(false);
        threeResourceToggleButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                threeResourceToggleButtonMousePressed(evt);
            }
        });
        selectResourcePanel.add(threeResourceToggleButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(228, 30, 95, 30));

        oneResourceToggleButton.setBackground(new java.awt.Color(239, 228, 176));
        oneResourceToggleButton.setFont(new java.awt.Font("Tahoma", 1, 11));
        oneResourceToggleButton.setSelected(true);
        oneResourceToggleButton.setText("1");
        oneResourceToggleButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        oneResourceToggleButton.setContentAreaFilled(false);
        oneResourceToggleButton.setFocusable(false);
        oneResourceToggleButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                oneResourceToggleButtonMousePressed(evt);
            }
        });
        selectResourcePanel.add(oneResourceToggleButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 30, 95, 30));

        twoResourceToggleButton.setBackground(new java.awt.Color(239, 228, 176));
        twoResourceToggleButton.setFont(new java.awt.Font("Tahoma", 1, 11));
        twoResourceToggleButton.setText("2");
        twoResourceToggleButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        twoResourceToggleButton.setContentAreaFilled(false);
        twoResourceToggleButton.setFocusable(false);
        twoResourceToggleButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                twoResourceToggleButtonMousePressed(evt);
            }
        });
        selectResourcePanel.add(twoResourceToggleButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(123, 30, 95, 30));

        searchPanel.add(selectResourcePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 52, 342, 70));

        resourceOnePanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        resourceOnePanel.setOpaque(false);
        resourceOnePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        howManyResourcesLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14));
        howManyResourcesLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        howManyResourcesLabel1.setText("Selected the resource of your first priority");
        resourceOnePanel.add(howManyResourcesLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 3, 340, -1));

        lumberButtonOne.setBackground(new java.awt.Color(239, 228, 176));
        lumberButtonOne.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/lumberRaw.png"))); // NOI18N
        lumberButtonOne.setSelected(true);
        lumberButtonOne.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        lumberButtonOne.setContentAreaFilled(false);
        lumberButtonOne.setFocusable(false);
        lumberButtonOne.setOpaque(true);
        lumberButtonOne.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lumberButtonOneMousePressed(evt);
            }
        });
        resourceOnePanel.add(lumberButtonOne, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 30, 50, 50));

        coalButtonOne.setBackground(new java.awt.Color(239, 228, 176));
        coalButtonOne.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/coalRaw.png"))); // NOI18N
        coalButtonOne.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        coalButtonOne.setContentAreaFilled(false);
        coalButtonOne.setFocusable(false);
        coalButtonOne.setOpaque(true);
        coalButtonOne.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                coalButtonOneMousePressed(evt);
            }
        });
        resourceOnePanel.add(coalButtonOne, new org.netbeans.lib.awtextra.AbsoluteConstraints(81, 30, 50, 50));

        cattleButtonOne.setBackground(new java.awt.Color(239, 228, 176));
        cattleButtonOne.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/cattleRaw.png"))); // NOI18N
        cattleButtonOne.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cattleButtonOne.setContentAreaFilled(false);
        cattleButtonOne.setFocusable(false);
        cattleButtonOne.setOpaque(true);
        cattleButtonOne.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cattleButtonOneMousePressed(evt);
            }
        });
        resourceOnePanel.add(cattleButtonOne, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 30, 50, 50));

        oreButtonOne.setBackground(new java.awt.Color(239, 228, 176));
        oreButtonOne.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/oreRaw.png"))); // NOI18N
        oreButtonOne.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        oreButtonOne.setContentAreaFilled(false);
        oreButtonOne.setFocusable(false);
        oreButtonOne.setOpaque(true);
        oreButtonOne.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                oreButtonOneMousePressed(evt);
            }
        });
        resourceOnePanel.add(oreButtonOne, new org.netbeans.lib.awtextra.AbsoluteConstraints(207, 30, 50, 50));

        wheatButtonOne.setBackground(new java.awt.Color(239, 228, 176));
        wheatButtonOne.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/wheatRaw.png"))); // NOI18N
        wheatButtonOne.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        wheatButtonOne.setContentAreaFilled(false);
        wheatButtonOne.setFocusable(false);
        wheatButtonOne.setOpaque(true);
        wheatButtonOne.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                wheatButtonOneMousePressed(evt);
            }
        });
        resourceOnePanel.add(wheatButtonOne, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 30, 50, 50));

        searchPanel.add(resourceOnePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 129, 340, 90));

        resourceTwoPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        resourceTwoPanel.setOpaque(false);
        resourceTwoPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        howManyResourcesLabel2.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14));
        howManyResourcesLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        howManyResourcesLabel2.setText("Selected the resource of your second priority");
        resourceTwoPanel.add(howManyResourcesLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 3, 340, -1));

        lumberButtonTwo.setBackground(new java.awt.Color(239, 228, 176));
        lumberButtonTwo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/lumberRaw.png"))); // NOI18N
        lumberButtonTwo.setSelected(true);
        lumberButtonTwo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        lumberButtonTwo.setContentAreaFilled(false);
        lumberButtonTwo.setFocusable(false);
        lumberButtonTwo.setOpaque(true);
        lumberButtonTwo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lumberButtonTwoMousePressed(evt);
            }
        });
        resourceTwoPanel.add(lumberButtonTwo, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 30, 50, 50));

        coalButtonTwo.setBackground(new java.awt.Color(239, 228, 176));
        coalButtonTwo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/coalRaw.png"))); // NOI18N
        coalButtonTwo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        coalButtonTwo.setContentAreaFilled(false);
        coalButtonTwo.setFocusable(false);
        coalButtonTwo.setOpaque(true);
        coalButtonTwo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                coalButtonTwoMousePressed(evt);
            }
        });
        resourceTwoPanel.add(coalButtonTwo, new org.netbeans.lib.awtextra.AbsoluteConstraints(81, 30, 50, 50));

        cattleButtonTwo.setBackground(new java.awt.Color(239, 228, 176));
        cattleButtonTwo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/cattleRaw.png"))); // NOI18N
        cattleButtonTwo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cattleButtonTwo.setContentAreaFilled(false);
        cattleButtonTwo.setFocusable(false);
        cattleButtonTwo.setOpaque(true);
        cattleButtonTwo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cattleButtonTwoMousePressed(evt);
            }
        });
        resourceTwoPanel.add(cattleButtonTwo, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 30, 50, 50));

        oreButtonTwo.setBackground(new java.awt.Color(239, 228, 176));
        oreButtonTwo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/oreRaw.png"))); // NOI18N
        oreButtonTwo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        oreButtonTwo.setContentAreaFilled(false);
        oreButtonTwo.setFocusable(false);
        oreButtonTwo.setOpaque(true);
        oreButtonTwo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                oreButtonTwoMousePressed(evt);
            }
        });
        resourceTwoPanel.add(oreButtonTwo, new org.netbeans.lib.awtextra.AbsoluteConstraints(207, 30, 50, 50));

        wheatButtonTwo.setBackground(new java.awt.Color(239, 228, 176));
        wheatButtonTwo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/wheatRaw.png"))); // NOI18N
        wheatButtonTwo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        wheatButtonTwo.setContentAreaFilled(false);
        wheatButtonTwo.setFocusable(false);
        wheatButtonTwo.setOpaque(true);
        wheatButtonTwo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                wheatButtonTwoMousePressed(evt);
            }
        });
        resourceTwoPanel.add(wheatButtonTwo, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 30, 50, 50));

        searchPanel.add(resourceTwoPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 226, 340, 90));

        resourceThreePanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        resourceThreePanel.setOpaque(false);
        resourceThreePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        howManyResourcesLabel3.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14));
        howManyResourcesLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        howManyResourcesLabel3.setText("Selected the resource of your third priority");
        resourceThreePanel.add(howManyResourcesLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 3, 340, -1));

        lumberButtonThree.setBackground(new java.awt.Color(239, 228, 176));
        lumberButtonThree.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/lumberRaw.png"))); // NOI18N
        lumberButtonThree.setSelected(true);
        lumberButtonThree.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        lumberButtonThree.setContentAreaFilled(false);
        lumberButtonThree.setFocusable(false);
        lumberButtonThree.setOpaque(true);
        lumberButtonThree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lumberButtonThreeMousePressed(evt);
            }
        });
        resourceThreePanel.add(lumberButtonThree, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 30, 50, 50));

        coalButtonThree.setBackground(new java.awt.Color(239, 228, 176));
        coalButtonThree.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/coalRaw.png"))); // NOI18N
        coalButtonThree.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        coalButtonThree.setContentAreaFilled(false);
        coalButtonThree.setFocusable(false);
        coalButtonThree.setOpaque(true);
        coalButtonThree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                coalButtonThreeMousePressed(evt);
            }
        });
        resourceThreePanel.add(coalButtonThree, new org.netbeans.lib.awtextra.AbsoluteConstraints(81, 30, 50, 50));

        cattleButtonThree.setBackground(new java.awt.Color(239, 228, 176));
        cattleButtonThree.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/cattleRaw.png"))); // NOI18N
        cattleButtonThree.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cattleButtonThree.setContentAreaFilled(false);
        cattleButtonThree.setFocusable(false);
        cattleButtonThree.setOpaque(true);
        cattleButtonThree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cattleButtonThreeMousePressed(evt);
            }
        });
        resourceThreePanel.add(cattleButtonThree, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 30, 50, 50));

        oreButtonThree.setBackground(new java.awt.Color(239, 228, 176));
        oreButtonThree.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/oreRaw.png"))); // NOI18N
        oreButtonThree.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        oreButtonThree.setContentAreaFilled(false);
        oreButtonThree.setFocusable(false);
        oreButtonThree.setOpaque(true);
        oreButtonThree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                oreButtonThreeMousePressed(evt);
            }
        });
        resourceThreePanel.add(oreButtonThree, new org.netbeans.lib.awtextra.AbsoluteConstraints(207, 30, 50, 50));

        wheatButtonThree.setBackground(new java.awt.Color(239, 228, 176));
        wheatButtonThree.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/wheatRaw.png"))); // NOI18N
        wheatButtonThree.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        wheatButtonThree.setContentAreaFilled(false);
        wheatButtonThree.setFocusable(false);
        wheatButtonThree.setOpaque(true);
        wheatButtonThree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                wheatButtonThreeMousePressed(evt);
            }
        });
        resourceThreePanel.add(wheatButtonThree, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 30, 50, 50));

        searchPanel.add(resourceThreePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 323, 340, 90));

        searchButton.setBackground(new java.awt.Color(239, 228, 176));
        searchButton.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        searchButton.setText("Search");
        searchButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        searchButton.setContentAreaFilled(false);
        searchButton.setFocusable(false);
        searchButton.setOpaque(true);
        searchButton.setPreferredSize(new java.awt.Dimension(75, 25));
        searchButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                searchButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                searchButtonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                searchButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                searchButtonMouseReleased(evt);
            }
        });
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });
        searchPanel.add(searchButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 475, 110, -1));

        backgroundLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/MainMenuBackground.png"))); // NOI18N
        searchPanel.add(backgroundLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        mainPanel.add(searchPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, 520));

        getContentPane().add(mainPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchButtonMouseEntered
        mouseOnSearchButton=true;
}//GEN-LAST:event_searchButtonMouseEntered
    private void searchButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchButtonMouseExited
        if(mouseOnSearchButton&&buttonPressed){
            searchButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            searchButton.setLocation(searchButton.getX() + 1, searchButton.getY());
        }
        mouseOnSearchButton=false;
        buttonPressed=false;
}//GEN-LAST:event_searchButtonMouseExited
    private void searchButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchButtonMousePressed
        gameFrame.playSound("click");
        searchButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        searchButton.setLocation(searchButton.getX()-1, searchButton.getY());
        buttonPressed=true;
}//GEN-LAST:event_searchButtonMousePressed
    private void searchButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchButtonMouseReleased
        buttonPressed=false;
        if(mouseOnSearchButton) {
            searchButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            searchButton.setLocation(searchButton.getX() + 1, searchButton.getY());
            buttonPressed=false;
            if(resourceOne==resourceTwo){
                resourceTwo=null;
            }
            if(resourceOne==resourceThree){
                resourceThree=null;
            }
            if(resourceTwo==resourceThree){
                resourceThree=null;
            }
            /**
             * algorithm 
             * find the best 3 cities for preference one
             * find the best 3 cities for preference two
             * find the best 3 cities for preference three
             * 
             * return best city for pref one
             * return best city for pref two
             * return best city for pref three
             * return best city for both pref one and pref two
             * return best city for both pref one and pref three
             * return best city for both pref two and pref three
             * return best city for pref one, pref two, and pref three
             */
            bestPrefOne=null;
            bestPrefTwo=null;
            bestPrefThree=null;
            bestPrefOneTwo=null;
            bestPrefOneThree=null;
            bestPrefTwoThree=null;
            bestPrefOneTwoThree=null;
            for(int i=0;i < cities.length; i++) {
                //find best pref overall city
                if (compareCities(cities[i], bestPrefOverall, null)) {
                    if ((purpleCity && cities[i].getPurpleCity()) || !purpleCity) {
                        if ((cityUnowned && cities[i].getOwner() == null) || !cityUnowned) {
                            bestPrefOverall = cities[i];
                        }
                    }
                }   
                //find best pref one city
                if (compareCities(cities[i], bestPrefOne, resourceOne)&&
                    cityHasResource(cities[i],resourceOne)) {
                    if ((purpleCity && cities[i].getPurpleCity()) || !purpleCity) {
                        if ((cityUnowned && cities[i].getOwner() == null) || !cityUnowned) {
                            bestPrefOne = cities[i];
                        }
                    }
                }            
            if (compareCities(cities[i], bestPrefTwo, resourceTwo)&&
                    cityHasResource(cities[i],resourceTwo)) {
                if ((purpleCity && cities[i].getPurpleCity()) || !purpleCity) {
                    if ((cityUnowned && cities[i].getOwner() == null) || !cityUnowned) {
                        bestPrefTwo = cities[i];
                    }
                }
            }
            if (compareCities(cities[i], bestPrefThree, resourceThree)&&
                    cityHasResource(cities[i],resourceThree)) {
                if ((purpleCity && cities[i].getPurpleCity()) || !purpleCity) {
                    if ((cityUnowned && cities[i].getOwner() == null) || !cityUnowned) {
                        bestPrefThree = cities[i];
                    }
                }
            }
            if (compareCities(cities[i], bestPrefOneTwo, resourceOne)&&
                    cityHasResource(cities[i],resourceOne) && cityHasResource(cities[i], resourceTwo)) {
                if ((purpleCity && cities[i].getPurpleCity()) || !purpleCity) {
                    if ((cityUnowned && cities[i].getOwner() == null) || !cityUnowned) {
                        bestPrefOneTwo = cities[i];
                    }
                }
            }
            if (compareCities(cities[i], bestPrefOneThree, resourceOne)&&
                    cityHasResource(cities[i],resourceOne) && cityHasResource(cities[i], resourceThree)) {
                if ((purpleCity && cities[i].getPurpleCity()) || !purpleCity) {
                    if ((cityUnowned && cities[i].getOwner() == null) || !cityUnowned) {
                        bestPrefOneThree = cities[i];
                    }
                }
            }
            if (compareCities(cities[i], bestPrefTwoThree, resourceTwo)&&
                    cityHasResource(cities[i],resourceTwo) && cityHasResource(cities[i], resourceThree)) {
                if ((purpleCity && cities[i].getPurpleCity()) || !purpleCity) {
                    if ((cityUnowned && cities[i].getOwner() == null) || !cityUnowned) {
                        bestPrefTwoThree = cities[i];
                    }
                }
            }
            if (compareCities(cities[i], bestPrefOneTwoThree, resourceOne)&&
                    cityHasResource(cities[i],resourceOne) && cityHasResource(cities[i], resourceTwo)
                    && cityHasResource(cities[i], resourceThree)) {
                if ((purpleCity && cities[i].getPurpleCity()) || !purpleCity) {
                    if ((cityUnowned && cities[i].getOwner() == null) || !cityUnowned) {
                        bestPrefOneTwoThree = cities[i];
                    }
                }
            }
        }
            new searchDisplayWindow(bestPrefOne,bestPrefTwo,bestPrefThree,
                    bestPrefOneTwo,bestPrefOneThree,bestPrefTwoThree,
                    bestPrefOneTwoThree,bestPrefOverall,resourceOne,resourceTwo,
                    resourceThree).setVisible(true);
            this.setVisible(false);
        }
}//GEN-LAST:event_searchButtonMouseReleased
    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_searchButtonActionPerformed
    private void xButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xButtonMouseEntered
        mouseOnXButton=true;
}//GEN-LAST:event_xButtonMouseEntered
    private void xButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xButtonMouseExited
        if(mouseOnXButton&&buttonPressed){
            xButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            xButton.setLocation(xButton.getX() + 1, xButton.getY());
        }
        mouseOnXButton=false;
        buttonPressed=false;
}//GEN-LAST:event_xButtonMouseExited
    private void xButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xButtonMousePressed
        gameFrame.playSound("click");
        xButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        xButton.setLocation(xButton.getX()-1, xButton.getY());
        buttonPressed=true;
}//GEN-LAST:event_xButtonMousePressed
    private void xButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xButtonMouseReleased
        buttonPressed=false;
        if(mouseOnXButton) {
            xButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            xButton.setLocation(xButton.getX() + 1, xButton.getY());
            this.setVisible(false);
        }
}//GEN-LAST:event_xButtonMouseReleased
    private void xButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xButtonActionPerformed
        
}//GEN-LAST:event_xButtonActionPerformed
    private void threeResourceToggleButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_threeResourceToggleButtonMousePressed
        if (!threeButtonSelected) {
            if(resourceTwo==null){
                resourceTwo=Hex.Resource.LUMBER;
            }
            resourceThree=Hex.Resource.LUMBER;
            numberOfResources=3;
            threeButtonSelected = true;            
            twoButtonSelected=false;
            oneButtonSelected=false;
            gameFrame.playSound("click");
            threeResourceToggleButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            threeResourceToggleButton.setLocation(threeResourceToggleButton.getX() - 1, threeResourceToggleButton.getY());
            twoResourceToggleButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            twoResourceToggleButton.setLocation(twoResourceToggleButton.getX() + 1, twoResourceToggleButton.getY());
            oneResourceToggleButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            oneResourceToggleButton.setLocation(oneResourceToggleButton.getX() + 1, oneResourceToggleButton.getY());
            resourceThreePanel.setVisible(true);
            resourceTwoPanel.setVisible(true);
        }
}//GEN-LAST:event_threeResourceToggleButtonMousePressed
    private void oneResourceToggleButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_oneResourceToggleButtonMousePressed
        if (!oneButtonSelected) {
            oneButtonSelected = true;
            threeButtonSelected = false;            
            twoButtonSelected=false;
            numberOfResources=1;
            resourceTwo=null;
            resourceThree=null;
            gameFrame.playSound("click");
            oneResourceToggleButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            oneResourceToggleButton.setLocation(oneResourceToggleButton.getX() - 1, oneResourceToggleButton.getY());
            twoResourceToggleButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            twoResourceToggleButton.setLocation(twoResourceToggleButton.getX() + 1, twoResourceToggleButton.getY());
            threeResourceToggleButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            threeResourceToggleButton.setLocation(threeResourceToggleButton.getX() + 1, threeResourceToggleButton.getY());
            resourceThreePanel.setVisible(false);
            resourceTwoPanel.setVisible(false);
        }
}//GEN-LAST:event_oneResourceToggleButtonMousePressed
    private void twoResourceToggleButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_twoResourceToggleButtonMousePressed
        if (!twoButtonSelected) {
            oneButtonSelected = false;
            threeButtonSelected = false;            
            twoButtonSelected=true;
            numberOfResources=2;
            resourceTwo=Hex.Resource.LUMBER;
            resourceThree=null;
            gameFrame.playSound("click");
            twoResourceToggleButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            twoResourceToggleButton.setLocation(twoResourceToggleButton.getX() - 1, twoResourceToggleButton.getY());
            threeResourceToggleButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            threeResourceToggleButton.setLocation(threeResourceToggleButton.getX() + 1, threeResourceToggleButton.getY());
            oneResourceToggleButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            oneResourceToggleButton.setLocation(oneResourceToggleButton.getX() + 1, oneResourceToggleButton.getY());
            resourceThreePanel.setVisible(false);
            resourceTwoPanel.setVisible(true);
        }
    }//GEN-LAST:event_twoResourceToggleButtonMousePressed
    private void lumberButtonOneMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lumberButtonOneMousePressed
        if (!lumberOneButtonSelected) {
            gameFrame.playSound("click");
            resourceOne = Hex.Resource.LUMBER;
            lumberButtonOne.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            lumberButtonOne.setLocation(lumberButtonOne.getX() - 1, lumberButtonOne.getY());
            if(coalOneButtonSelected){
            coalButtonOne.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            coalButtonOne.setLocation(coalButtonOne.getX() + 1, coalButtonOne.getY());
            }else if(cattleOneButtonSelected){
            cattleButtonOne.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            cattleButtonOne.setLocation(cattleButtonOne.getX() + 1, cattleButtonOne.getY());
            }else if(wheatOneButtonSelected){
            wheatButtonOne.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            wheatButtonOne.setLocation(wheatButtonOne.getX() + 1, wheatButtonOne.getY());
            }else if(oreOneButtonSelected){
            oreButtonOne.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            oreButtonOne.setLocation(oreButtonOne.getX() + 1, oreButtonOne.getY());
            }
            lumberOneButtonSelected = true;
            coalOneButtonSelected = false;
            wheatOneButtonSelected = false;
            oreOneButtonSelected = false;
            cattleOneButtonSelected = false;
        }
    }//GEN-LAST:event_lumberButtonOneMousePressed
    private void coalButtonOneMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_coalButtonOneMousePressed
        if (!coalOneButtonSelected) {
            gameFrame.playSound("click");
            resourceOne = Hex.Resource.COAL;
            coalButtonOne.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            coalButtonOne.setLocation(coalButtonOne.getX() - 1, coalButtonOne.getY());
            if(lumberOneButtonSelected){
            lumberButtonOne.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            lumberButtonOne.setLocation(lumberButtonOne.getX() + 1, lumberButtonOne.getY());
            }else if(cattleOneButtonSelected){
            cattleButtonOne.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            cattleButtonOne.setLocation(cattleButtonOne.getX() + 1, cattleButtonOne.getY());
            }else if(wheatOneButtonSelected){
            wheatButtonOne.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            wheatButtonOne.setLocation(wheatButtonOne.getX() + 1, wheatButtonOne.getY());
            }else if(oreOneButtonSelected){
            oreButtonOne.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            oreButtonOne.setLocation(oreButtonOne.getX() + 1, oreButtonOne.getY());
            }
            lumberOneButtonSelected = false;
            coalOneButtonSelected = true;
            wheatOneButtonSelected = false;
            oreOneButtonSelected = false;
            cattleOneButtonSelected = false;
        }
    }//GEN-LAST:event_coalButtonOneMousePressed
    private void cattleButtonOneMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cattleButtonOneMousePressed
        if (!cattleOneButtonSelected) {
            gameFrame.playSound("click");
            resourceOne = Hex.Resource.CATTLE;
            cattleButtonOne.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            cattleButtonOne.setLocation(cattleButtonOne.getX() - 1, cattleButtonOne.getY());
            if(coalOneButtonSelected){
            coalButtonOne.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            coalButtonOne.setLocation(coalButtonOne.getX() + 1, coalButtonOne.getY());
            }else if(lumberOneButtonSelected){
            lumberButtonOne.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            lumberButtonOne.setLocation(lumberButtonOne.getX() + 1, lumberButtonOne.getY());
            }else if(wheatOneButtonSelected){
            wheatButtonOne.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            wheatButtonOne.setLocation(wheatButtonOne.getX() + 1, wheatButtonOne.getY());
            }else if(oreOneButtonSelected){
            oreButtonOne.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            oreButtonOne.setLocation(oreButtonOne.getX() + 1, oreButtonOne.getY());
            }
            lumberOneButtonSelected = false;
            coalOneButtonSelected = false;
            wheatOneButtonSelected = false;
            oreOneButtonSelected = false;
            cattleOneButtonSelected = true;
        }
    }//GEN-LAST:event_cattleButtonOneMousePressed
    private void oreButtonOneMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_oreButtonOneMousePressed
        if (!oreOneButtonSelected) {
            gameFrame.playSound("click");
            resourceOne = Hex.Resource.ORE;
            oreButtonOne.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            oreButtonOne.setLocation(oreButtonOne.getX() - 1, oreButtonOne.getY());
            if(coalOneButtonSelected){
            coalButtonOne.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            coalButtonOne.setLocation(coalButtonOne.getX() + 1, coalButtonOne.getY());
            }else if(cattleOneButtonSelected){
            cattleButtonOne.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            cattleButtonOne.setLocation(cattleButtonOne.getX() + 1, cattleButtonOne.getY());
            }else if(wheatOneButtonSelected){
            wheatButtonOne.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            wheatButtonOne.setLocation(wheatButtonOne.getX() + 1, wheatButtonOne.getY());
            }else if(lumberOneButtonSelected){
            lumberButtonOne.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            lumberButtonOne.setLocation(lumberButtonOne.getX() + 1, lumberButtonOne.getY());
            }
            lumberOneButtonSelected = false;
            coalOneButtonSelected = false;
            wheatOneButtonSelected = false;
            oreOneButtonSelected = true;
            cattleOneButtonSelected = false;
        }
    }//GEN-LAST:event_oreButtonOneMousePressed
    private void wheatButtonOneMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_wheatButtonOneMousePressed
        if (!wheatOneButtonSelected) {
            gameFrame.playSound("click");
            resourceOne = Hex.Resource.WHEAT;
            wheatButtonOne.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            wheatButtonOne.setLocation(wheatButtonOne.getX() - 1, wheatButtonOne.getY());
            if(coalOneButtonSelected){
            coalButtonOne.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            coalButtonOne.setLocation(coalButtonOne.getX() + 1, coalButtonOne.getY());
            }else if(cattleOneButtonSelected){
            cattleButtonOne.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            cattleButtonOne.setLocation(cattleButtonOne.getX() + 1, cattleButtonOne.getY());
            }else if(lumberOneButtonSelected){
            lumberButtonOne.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            lumberButtonOne.setLocation(lumberButtonOne.getX() + 1, lumberButtonOne.getY());
            }else if(oreOneButtonSelected){
            oreButtonOne.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            oreButtonOne.setLocation(oreButtonOne.getX() + 1, oreButtonOne.getY());
            }
            lumberOneButtonSelected = false;
            coalOneButtonSelected = false;
            wheatOneButtonSelected = true;
            oreOneButtonSelected = false;
            cattleOneButtonSelected = false;
        }
    }//GEN-LAST:event_wheatButtonOneMousePressed
    private void lumberButtonTwoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lumberButtonTwoMousePressed
        if (!lumberTwoButtonSelected) {
            gameFrame.playSound("click");
            resourceTwo = Hex.Resource.LUMBER;
            lumberButtonTwo.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            lumberButtonTwo.setLocation(lumberButtonTwo.getX() - 1, lumberButtonTwo.getY());
            if(coalTwoButtonSelected){
            coalButtonTwo.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            coalButtonTwo.setLocation(coalButtonTwo.getX() + 1, coalButtonTwo.getY());
            }else if(cattleTwoButtonSelected){
            cattleButtonTwo.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            cattleButtonTwo.setLocation(cattleButtonTwo.getX() + 1, cattleButtonTwo.getY());
            }else if(wheatTwoButtonSelected){
            wheatButtonTwo.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            wheatButtonTwo.setLocation(wheatButtonTwo.getX() + 1, wheatButtonTwo.getY());
            }else if(oreTwoButtonSelected){
            oreButtonTwo.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            oreButtonTwo.setLocation(oreButtonTwo.getX() + 1, oreButtonTwo.getY());
            }
            lumberTwoButtonSelected = true;
            coalTwoButtonSelected = false;
            wheatTwoButtonSelected = false;
            oreTwoButtonSelected = false;
            cattleTwoButtonSelected = false;
        }
    }//GEN-LAST:event_lumberButtonTwoMousePressed
    private void coalButtonTwoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_coalButtonTwoMousePressed
        if (!coalTwoButtonSelected) {
            gameFrame.playSound("click");
            resourceTwo = Hex.Resource.COAL;
            coalButtonTwo.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            coalButtonTwo.setLocation(coalButtonTwo.getX() - 1, coalButtonTwo.getY());
            if(lumberTwoButtonSelected){
            lumberButtonTwo.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            lumberButtonTwo.setLocation(lumberButtonTwo.getX() + 1, lumberButtonTwo.getY());
            }else if(cattleTwoButtonSelected){
            cattleButtonTwo.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            cattleButtonTwo.setLocation(cattleButtonTwo.getX() + 1, cattleButtonTwo.getY());
            }else if(wheatTwoButtonSelected){
            wheatButtonTwo.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            wheatButtonTwo.setLocation(wheatButtonTwo.getX() + 1, wheatButtonTwo.getY());
            }else if(oreTwoButtonSelected){
            oreButtonTwo.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            oreButtonTwo.setLocation(oreButtonTwo.getX() + 1, oreButtonTwo.getY());
            }
            lumberTwoButtonSelected = false;
            coalTwoButtonSelected = true;
            wheatTwoButtonSelected = false;
            oreTwoButtonSelected = false;
            cattleTwoButtonSelected = false;
        }
    }//GEN-LAST:event_coalButtonTwoMousePressed
    private void cattleButtonTwoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cattleButtonTwoMousePressed
        if (!cattleTwoButtonSelected) {
            gameFrame.playSound("click");
            resourceTwo = Hex.Resource.CATTLE;
            cattleButtonTwo.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            cattleButtonTwo.setLocation(cattleButtonTwo.getX() - 1, cattleButtonTwo.getY());
            if(coalTwoButtonSelected){
            coalButtonTwo.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            coalButtonTwo.setLocation(coalButtonTwo.getX() + 1, coalButtonTwo.getY());
            }else if(lumberTwoButtonSelected){
            lumberButtonTwo.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            lumberButtonTwo.setLocation(lumberButtonTwo.getX() + 1, lumberButtonTwo.getY());
            }else if(wheatTwoButtonSelected){
            wheatButtonTwo.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            wheatButtonTwo.setLocation(wheatButtonTwo.getX() + 1, wheatButtonTwo.getY());
            }else if(oreTwoButtonSelected){
            oreButtonTwo.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            oreButtonTwo.setLocation(oreButtonTwo.getX() + 1, oreButtonTwo.getY());
            }
            lumberTwoButtonSelected = false;
            coalTwoButtonSelected = false;
            wheatTwoButtonSelected = false;
            oreTwoButtonSelected = false;
            cattleTwoButtonSelected = true;
        }
    }//GEN-LAST:event_cattleButtonTwoMousePressed
    private void oreButtonTwoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_oreButtonTwoMousePressed
        if (!oreTwoButtonSelected) {
            gameFrame.playSound("click");
            resourceTwo = Hex.Resource.ORE;
            oreButtonTwo.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            oreButtonTwo.setLocation(oreButtonTwo.getX() - 1, oreButtonTwo.getY());
            if(coalTwoButtonSelected){
            coalButtonTwo.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            coalButtonTwo.setLocation(coalButtonTwo.getX() + 1, coalButtonTwo.getY());
            }else if(cattleTwoButtonSelected){
            cattleButtonTwo.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            cattleButtonTwo.setLocation(cattleButtonTwo.getX() + 1, cattleButtonTwo.getY());
            }else if(wheatTwoButtonSelected){
            wheatButtonTwo.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            wheatButtonTwo.setLocation(wheatButtonTwo.getX() + 1, wheatButtonTwo.getY());
            }else if(lumberTwoButtonSelected){
            lumberButtonTwo.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            lumberButtonTwo.setLocation(lumberButtonTwo.getX() + 1, lumberButtonTwo.getY());
            }
            lumberTwoButtonSelected = false;
            coalTwoButtonSelected = false;
            wheatTwoButtonSelected = false;
            oreTwoButtonSelected = true;
            cattleTwoButtonSelected = false;
        }
    }//GEN-LAST:event_oreButtonTwoMousePressed
    private void wheatButtonTwoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_wheatButtonTwoMousePressed
        if (!wheatTwoButtonSelected) {
            gameFrame.playSound("click");
            resourceTwo = Hex.Resource.WHEAT;
            wheatButtonTwo.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            wheatButtonTwo.setLocation(wheatButtonTwo.getX() - 1, wheatButtonTwo.getY());
            if(coalTwoButtonSelected){
            coalButtonTwo.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            coalButtonTwo.setLocation(coalButtonTwo.getX() + 1, coalButtonTwo.getY());
            }else if(cattleTwoButtonSelected){
            cattleButtonTwo.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            cattleButtonTwo.setLocation(cattleButtonTwo.getX() + 1, cattleButtonTwo.getY());
            }else if(lumberTwoButtonSelected){
            lumberButtonTwo.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            lumberButtonTwo.setLocation(lumberButtonTwo.getX() + 1, lumberButtonTwo.getY());
            }else if(oreTwoButtonSelected){
            oreButtonTwo.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            oreButtonTwo.setLocation(oreButtonTwo.getX() + 1, oreButtonTwo.getY());
            }
            lumberTwoButtonSelected = false;
            coalTwoButtonSelected = false;
            wheatTwoButtonSelected = true;
            oreTwoButtonSelected = false;
            cattleTwoButtonSelected = false;
        }
    }//GEN-LAST:event_wheatButtonTwoMousePressed
    private void lumberButtonThreeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lumberButtonThreeMousePressed
        if (!lumberThreeButtonSelected) {
            gameFrame.playSound("click");
            resourceThree = Hex.Resource.LUMBER;
            lumberButtonThree.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            lumberButtonThree.setLocation(lumberButtonThree.getX() - 1, lumberButtonThree.getY());
            if(coalThreeButtonSelected){
            coalButtonThree.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            coalButtonThree.setLocation(coalButtonThree.getX() + 1, coalButtonThree.getY());
            }else if(cattleThreeButtonSelected){
            cattleButtonThree.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            cattleButtonThree.setLocation(cattleButtonThree.getX() + 1, cattleButtonThree.getY());
            }else if(wheatThreeButtonSelected){
            wheatButtonThree.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            wheatButtonThree.setLocation(wheatButtonThree.getX() + 1, wheatButtonThree.getY());
            }else if(oreThreeButtonSelected){
            oreButtonThree.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            oreButtonThree.setLocation(oreButtonThree.getX() + 1, oreButtonThree.getY());
            }
            lumberThreeButtonSelected = true;
            coalThreeButtonSelected = false;
            wheatThreeButtonSelected = false;
            oreThreeButtonSelected = false;
            cattleThreeButtonSelected = false;
        }
    }//GEN-LAST:event_lumberButtonThreeMousePressed
    private void coalButtonThreeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_coalButtonThreeMousePressed
        if (!coalThreeButtonSelected) {
            gameFrame.playSound("click");
            resourceThree = Hex.Resource.COAL;
            coalButtonThree.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            coalButtonThree.setLocation(coalButtonThree.getX() - 1, coalButtonThree.getY());
            if(lumberThreeButtonSelected){
            lumberButtonThree.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            lumberButtonThree.setLocation(lumberButtonThree.getX() + 1, lumberButtonThree.getY());
            }else if(cattleThreeButtonSelected){
            cattleButtonThree.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            cattleButtonThree.setLocation(cattleButtonThree.getX() + 1, cattleButtonThree.getY());
            }else if(wheatThreeButtonSelected){
            wheatButtonThree.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            wheatButtonThree.setLocation(wheatButtonThree.getX() + 1, wheatButtonThree.getY());
            }else if(oreThreeButtonSelected){
            oreButtonThree.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            oreButtonThree.setLocation(oreButtonThree.getX() + 1, oreButtonThree.getY());
            }
            lumberThreeButtonSelected = false;
            coalThreeButtonSelected = true;
            wheatThreeButtonSelected = false;
            oreThreeButtonSelected = false;
            cattleThreeButtonSelected = false;
        }
    }//GEN-LAST:event_coalButtonThreeMousePressed
    private void cattleButtonThreeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cattleButtonThreeMousePressed
        if (!cattleThreeButtonSelected) {
            gameFrame.playSound("click");
            resourceThree = Hex.Resource.CATTLE;
            cattleButtonThree.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            cattleButtonThree.setLocation(cattleButtonThree.getX() - 1, cattleButtonThree.getY());
            if(coalThreeButtonSelected){
            coalButtonThree.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            coalButtonThree.setLocation(coalButtonThree.getX() + 1, coalButtonThree.getY());
            }else if(lumberThreeButtonSelected){
            lumberButtonThree.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            lumberButtonThree.setLocation(lumberButtonThree.getX() + 1, lumberButtonThree.getY());
            }else if(wheatThreeButtonSelected){
            wheatButtonThree.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            wheatButtonThree.setLocation(wheatButtonThree.getX() + 1, wheatButtonThree.getY());
            }else if(oreThreeButtonSelected){
            oreButtonThree.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            oreButtonThree.setLocation(oreButtonThree.getX() + 1, oreButtonThree.getY());
            }
            lumberThreeButtonSelected = false;
            coalThreeButtonSelected = false;
            wheatThreeButtonSelected = false;
            oreThreeButtonSelected = false;
            cattleThreeButtonSelected = true;
        }
    }//GEN-LAST:event_cattleButtonThreeMousePressed
    private void oreButtonThreeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_oreButtonThreeMousePressed
        if (!oreThreeButtonSelected) {
            gameFrame.playSound("click");
            resourceThree = Hex.Resource.ORE;
            oreButtonThree.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            oreButtonThree.setLocation(oreButtonThree.getX() - 1, oreButtonThree.getY());
            if(coalThreeButtonSelected){
            coalButtonThree.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            coalButtonThree.setLocation(coalButtonThree.getX() + 1, coalButtonThree.getY());
            }else if(cattleThreeButtonSelected){
            cattleButtonThree.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            cattleButtonThree.setLocation(cattleButtonThree.getX() + 1, cattleButtonThree.getY());
            }else if(wheatThreeButtonSelected){
            wheatButtonThree.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            wheatButtonThree.setLocation(wheatButtonThree.getX() + 1, wheatButtonThree.getY());
            }else if(lumberThreeButtonSelected){
            lumberButtonThree.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            lumberButtonThree.setLocation(lumberButtonThree.getX() + 1, lumberButtonThree.getY());
            }
            lumberThreeButtonSelected = false;
            coalThreeButtonSelected = false;
            wheatThreeButtonSelected = false;
            oreThreeButtonSelected = true;
            cattleThreeButtonSelected = false;
        }
    }//GEN-LAST:event_oreButtonThreeMousePressed
    private void wheatButtonThreeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_wheatButtonThreeMousePressed
        if (!wheatThreeButtonSelected) {
            gameFrame.playSound("click");
            resourceThree = Hex.Resource.WHEAT;
            wheatButtonThree.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            wheatButtonThree.setLocation(wheatButtonThree.getX() - 1, wheatButtonThree.getY());
            if(coalThreeButtonSelected){
            coalButtonThree.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            coalButtonThree.setLocation(coalButtonThree.getX() + 1, coalButtonThree.getY());
            }else if(cattleThreeButtonSelected){
            cattleButtonThree.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            cattleButtonThree.setLocation(cattleButtonThree.getX() + 1, cattleButtonThree.getY());
            }else if(lumberThreeButtonSelected){
            lumberButtonThree.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            lumberButtonThree.setLocation(lumberButtonThree.getX() + 1, lumberButtonThree.getY());
            }else if(oreThreeButtonSelected){
            oreButtonThree.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            oreButtonThree.setLocation(oreButtonThree.getX() + 1, oreButtonThree.getY());
            }
            lumberThreeButtonSelected = false;
            coalThreeButtonSelected = false;
            wheatThreeButtonSelected = true;
            oreThreeButtonSelected = false;
            cattleThreeButtonSelected = false;
        }
    }//GEN-LAST:event_wheatButtonThreeMousePressed
    private void purpleCityCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_purpleCityCheckBoxActionPerformed
        gameFrame.playSound("click");
        purpleCity=!purpleCity;
    }//GEN-LAST:event_purpleCityCheckBoxActionPerformed
    private void cityIsUnownedCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cityIsUnownedCheckBoxActionPerformed
        gameFrame.playSound("click");
        cityUnowned=!cityUnowned;
    }//GEN-LAST:event_cityIsUnownedCheckBoxActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new UtilitiesWindow().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel backgroundLabel;
    private javax.swing.JToggleButton cattleButtonOne;
    private javax.swing.JToggleButton cattleButtonThree;
    private javax.swing.JToggleButton cattleButtonTwo;
    private javax.swing.JPanel checkBoxPanel;
    private javax.swing.JLabel cityIsPurpleToolTip;
    private javax.swing.JCheckBox cityIsUnownedCheckBox;
    private javax.swing.JLabel cityIsUnownedToolTip;
    private javax.swing.JToggleButton coalButtonOne;
    private javax.swing.JToggleButton coalButtonThree;
    private javax.swing.JToggleButton coalButtonTwo;
    private javax.swing.JLabel howManyResourcesLabel;
    private javax.swing.JLabel howManyResourcesLabel1;
    private javax.swing.JLabel howManyResourcesLabel2;
    private javax.swing.JLabel howManyResourcesLabel3;
    private javax.swing.JToggleButton lumberButtonOne;
    private javax.swing.JToggleButton lumberButtonThree;
    private javax.swing.JToggleButton lumberButtonTwo;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JToggleButton oneResourceToggleButton;
    private javax.swing.JToggleButton oreButtonOne;
    private javax.swing.JToggleButton oreButtonThree;
    private javax.swing.JToggleButton oreButtonTwo;
    private javax.swing.JCheckBox purpleCityCheckBox;
    private javax.swing.JPanel resourceOnePanel;
    private javax.swing.JPanel resourceThreePanel;
    private javax.swing.JPanel resourceTwoPanel;
    private javax.swing.JButton searchButton;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JPanel selectResourcePanel;
    private javax.swing.JToggleButton threeResourceToggleButton;
    private javax.swing.JToggleButton twoResourceToggleButton;
    private javax.swing.JToggleButton wheatButtonOne;
    private javax.swing.JToggleButton wheatButtonThree;
    private javax.swing.JToggleButton wheatButtonTwo;
    private javax.swing.JLabel whereShouldIBuildLabel;
    private javax.swing.JButton xButton;
    // End of variables declaration//GEN-END:variables
}

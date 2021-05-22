/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * searchDisplayWindow.java
 *
 * Created on Oct 4, 2013, 12:59:14 AM
 */
package settlerstrailstorails;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.text.NumberFormat;
import java.util.Locale;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;

/**
 *
 * @author User 1
 */
public class searchDisplayWindow extends javax.swing.JFrame {
    CityHex bestPrefOne,bestPrefTwo,bestPrefThree,bestPrefOneTwo,
            bestPrefOneThree,bestPrefTwoThree,bestPrefOneTwoThree;
    Hex.Resource resourceOne,resourceTwo,resourceThree;
    boolean mouseOnOkButton,buttonPressed;

    /** Creates new form searchDisplayWindow */
    public searchDisplayWindow() {
        initComponents();
    }
    searchDisplayWindow(CityHex bestPrefOne, CityHex bestPrefTwo, 
            CityHex bestPrefThree, CityHex bestPrefOneTwo,
            CityHex bestPrefOneThree, CityHex bestPrefTwoThree, 
            CityHex bestPrefOneTwoThree, CityHex bestPrefOverall,
            Hex.Resource resourceOne, Hex.Resource resourceTwo, 
            Hex.Resource resourceThree) {
        this.bestPrefOne=bestPrefOne;
        this.bestPrefTwo=bestPrefTwo;
        this.bestPrefThree=bestPrefThree;
        this.bestPrefOneTwo=bestPrefOneTwo;
        this.bestPrefOneThree=bestPrefOneThree;
        this.bestPrefTwoThree=bestPrefTwoThree;
        this.bestPrefOneTwoThree=bestPrefOneTwoThree;
        this.resourceOne=resourceOne;
        this.resourceTwo=resourceTwo;
        this.resourceThree=resourceThree;
        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        try{
            setIconImage(ImageIO.read(getClass().getResource("/settlerstrailstorails/resources/icon.png")));
        }catch(Exception e){
            
        }
        prefOnePanel.setVisible(false);
        prefTwoPanel.setVisible(false);
        prefThreePanel.setVisible(false);
        prefFourPanel.setVisible(false);
        prefFivePanel.setVisible(false);
        prefSixPanel.setVisible(false);
        prefSevenPanel.setVisible(false);
        NumberFormat nFormat = NumberFormat.getPercentInstance(Locale.ENGLISH);
        bestPrefOverallLabel.setText("Best city for only overall");
        //best city for pref one
        cityNamePrefOverallVarLabel.setText(bestPrefOverall.getName());
        lumberProbPrefOverallVar.setText(nFormat.format(bestPrefOverall.getProbabilityOfResource(Hex.Resource.LUMBER)));
        coalProbPrefOverallVar.setText(nFormat.format(bestPrefOverall.getProbabilityOfResource(Hex.Resource.COAL)));
        wheatProbPrefOverallVar.setText(nFormat.format(bestPrefOverall.getProbabilityOfResource(Hex.Resource.WHEAT)));
        oreProbPrefOverallVar.setText(nFormat.format(bestPrefOverall.getProbabilityOfResource(Hex.Resource.ORE)));
        cattleProbPrefOverallVar.setText(nFormat.format(bestPrefOverall.getProbabilityOfResource(Hex.Resource.CATTLE)));
        if (resourceOne != null) {
            if (bestPrefOne != null) {
                prefOnePanel.setVisible(true);
                bestPrefOneLabel.setText("Best city for only " + resourceOne.toString());
                //best city for pref one
                cityNamePrefOneVarLabel.setText(bestPrefOne.getName());
                lumberProbPrefOneVar.setText(nFormat.format(bestPrefOne.getProbabilityOfResource(Hex.Resource.LUMBER)));
                coalProbPrefOneVar.setText(nFormat.format(bestPrefOne.getProbabilityOfResource(Hex.Resource.COAL)));
                wheatProbPrefOneVar.setText(nFormat.format(bestPrefOne.getProbabilityOfResource(Hex.Resource.WHEAT)));
                oreProbPrefOneVar.setText(nFormat.format(bestPrefOne.getProbabilityOfResource(Hex.Resource.ORE)));
                cattleProbPrefOneVar.setText(nFormat.format(bestPrefOne.getProbabilityOfResource(Hex.Resource.CATTLE)));
            }
        }
        if (resourceTwo != null) {
            if (bestPrefTwo != null) {
                prefTwoPanel.setVisible(true);
                bestPrefTwoLabel.setText("Best city for only " + resourceTwo.toString());
                //best city for pref two
                cityNamePrefTwoVarLabel.setText(bestPrefTwo.getName());
                lumberProbPrefTwoVar.setText(nFormat.format(bestPrefTwo.getProbabilityOfResource(Hex.Resource.LUMBER)));
                coalProbPrefTwoVar.setText(nFormat.format(bestPrefTwo.getProbabilityOfResource(Hex.Resource.COAL)));
                wheatProbPrefTwoVar.setText(nFormat.format(bestPrefTwo.getProbabilityOfResource(Hex.Resource.WHEAT)));
                oreProbPrefTwoVar.setText(nFormat.format(bestPrefTwo.getProbabilityOfResource(Hex.Resource.ORE)));
                cattleProbPrefTwoVar.setText(nFormat.format(bestPrefTwo.getProbabilityOfResource(Hex.Resource.CATTLE)));
            }
        }
        if (resourceThree != null) {
            if (bestPrefThree != null) {
                prefThreePanel.setVisible(true);
                bestPrefThreeLabel.setText("Best city for only " + resourceThree.toString());
                //best city for pref three
                cityNamePrefThreeVarLabel.setText(bestPrefThree.getName());
                lumberProbPrefThreeVar.setText(nFormat.format(bestPrefThree.getProbabilityOfResource(Hex.Resource.LUMBER)));
                coalProbPrefThreeVar.setText(nFormat.format(bestPrefThree.getProbabilityOfResource(Hex.Resource.COAL)));
                wheatProbPrefThreeVar.setText(nFormat.format(bestPrefThree.getProbabilityOfResource(Hex.Resource.WHEAT)));
                oreProbPrefThreeVar.setText(nFormat.format(bestPrefThree.getProbabilityOfResource(Hex.Resource.ORE)));
                cattleProbPrefThreeVar.setText(nFormat.format(bestPrefThree.getProbabilityOfResource(Hex.Resource.CATTLE)));
            }
        }
        if (resourceOne != null && resourceTwo != null) {
            if (bestPrefOneTwo != null) {
                prefFourPanel.setVisible(true);
                bestPrefFourLabel.setText("Best city for both " + resourceOne.toString() + " and " + resourceTwo.toString());
                //best city for pref four
                cityNamePrefFourVarLabel.setText(bestPrefOneTwo.getName());
                lumberProbPrefFourVar.setText(nFormat.format(bestPrefOneTwo.getProbabilityOfResource(Hex.Resource.LUMBER)));
                coalProbPrefFourVar.setText(nFormat.format(bestPrefOneTwo.getProbabilityOfResource(Hex.Resource.COAL)));
                wheatProbPrefFourVar.setText(nFormat.format(bestPrefOneTwo.getProbabilityOfResource(Hex.Resource.WHEAT)));
                oreProbPrefFourVar.setText(nFormat.format(bestPrefOneTwo.getProbabilityOfResource(Hex.Resource.ORE)));
                cattleProbPrefFourVar.setText(nFormat.format(bestPrefOneTwo.getProbabilityOfResource(Hex.Resource.CATTLE)));
            }
        }
        if (resourceOne != null && resourceThree != null) {
            if (bestPrefOneThree != null) {
                prefFivePanel.setVisible(true);
                bestPrefFiveLabel.setText("Best city for both " + resourceOne.toString() + " and " + resourceThree.toString());
                //best city for pref four
                cityNamePrefFiveVarLabel.setText(bestPrefOneThree.getName());
                lumberProbPrefFiveVar.setText(nFormat.format(bestPrefOneThree.getProbabilityOfResource(Hex.Resource.LUMBER)));
                coalProbPrefFiveVar.setText(nFormat.format(bestPrefOneThree.getProbabilityOfResource(Hex.Resource.COAL)));
                wheatProbPrefFiveVar.setText(nFormat.format(bestPrefOneThree.getProbabilityOfResource(Hex.Resource.WHEAT)));
                oreProbPrefFiveVar.setText(nFormat.format(bestPrefOneThree.getProbabilityOfResource(Hex.Resource.ORE)));
                cattleProbPrefFiveVar.setText(nFormat.format(bestPrefOneThree.getProbabilityOfResource(Hex.Resource.CATTLE)));
            }
        } 
               
        if (resourceTwo != null && resourceThree != null) {
            if(bestPrefTwoThree != null) {
                prefSixPanel.setVisible(true);
                bestPrefSixLabel.setText("Best city for both " + resourceTwo.toString() + " and " + resourceThree.toString());
                //best city for pref four
                cityNamePrefSixVarLabel.setText(bestPrefTwoThree.getName());
                lumberProbPrefSixVar.setText(nFormat.format(bestPrefTwoThree.getProbabilityOfResource(Hex.Resource.LUMBER)));
                coalProbPrefSixVar.setText(nFormat.format(bestPrefTwoThree.getProbabilityOfResource(Hex.Resource.COAL)));
                wheatProbPrefSixVar.setText(nFormat.format(bestPrefTwoThree.getProbabilityOfResource(Hex.Resource.WHEAT)));
                oreProbPrefSixVar.setText(nFormat.format(bestPrefTwoThree.getProbabilityOfResource(Hex.Resource.ORE)));
                cattleProbPrefSixVar.setText(nFormat.format(bestPrefTwoThree.getProbabilityOfResource(Hex.Resource.CATTLE)));
            }
        }        
        if (resourceOne != null && resourceThree != null && resourceTwo!= null) {
            if(bestPrefOneTwoThree != null) {
                prefSevenPanel.setVisible(true);
                bestPrefSevenLabel.setText("Best city for " + resourceOne.toString() + ", " + resourceTwo.toString() + ", and " + resourceThree.toString());
                //best city for pref four
                cityNamePrefSevenVarLabel.setText(bestPrefOneTwoThree.getName());
                lumberProbPrefSevenVar.setText(nFormat.format(bestPrefOneTwoThree.getProbabilityOfResource(Hex.Resource.LUMBER)));
                coalProbPrefSevenVar.setText(nFormat.format(bestPrefOneTwoThree.getProbabilityOfResource(Hex.Resource.COAL)));
                wheatProbPrefSevenVar.setText(nFormat.format(bestPrefOneTwoThree.getProbabilityOfResource(Hex.Resource.WHEAT)));
                oreProbPrefSevenVar.setText(nFormat.format(bestPrefOneTwoThree.getProbabilityOfResource(Hex.Resource.ORE)));
                cattleProbPrefSevenVar.setText(nFormat.format(bestPrefOneTwoThree.getProbabilityOfResource(Hex.Resource.CATTLE)));
            }
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

        searchResultsPanels = new javax.swing.JPanel();
        prefSevenPanel = new javax.swing.JPanel();
        bestPrefSevenLabel = new javax.swing.JLabel();
        cityNamePrefSevenLabel = new javax.swing.JLabel();
        cityNamePrefSevenVarLabel = new javax.swing.JLabel();
        lumberProbPrefSevenIcon = new javax.swing.JLabel();
        coalProbPrefSevenIcon = new javax.swing.JLabel();
        cattleProbPrefSevenIcon = new javax.swing.JLabel();
        oreProbPrefSevenIcon = new javax.swing.JLabel();
        wheatProbPrefSevenIcon = new javax.swing.JLabel();
        resourceLabelPrefSeven = new javax.swing.JLabel();
        probabilityLabelPrefSeven = new javax.swing.JLabel();
        lumberProbPrefSevenVar = new javax.swing.JLabel();
        coalProbPrefSevenVar = new javax.swing.JLabel();
        cattleProbPrefSevenVar = new javax.swing.JLabel();
        oreProbPrefSevenVar = new javax.swing.JLabel();
        wheatProbPrefSevenVar = new javax.swing.JLabel();
        prefOverallPanel = new javax.swing.JPanel();
        bestPrefOverallLabel = new javax.swing.JLabel();
        cityNamePrefOverallLabel = new javax.swing.JLabel();
        cityNamePrefOverallVarLabel = new javax.swing.JLabel();
        lumberProbPrefOverallIcon = new javax.swing.JLabel();
        coalProbPrefOverallIcon = new javax.swing.JLabel();
        cattleProbPrefOverallIcon = new javax.swing.JLabel();
        oreProbPrefOverallIcon = new javax.swing.JLabel();
        wheatProbPrefOverallIcon = new javax.swing.JLabel();
        resourceLabelPrefOverall = new javax.swing.JLabel();
        probabilityLabelPrefOverall = new javax.swing.JLabel();
        lumberProbPrefOverallVar = new javax.swing.JLabel();
        coalProbPrefOverallVar = new javax.swing.JLabel();
        cattleProbPrefOverallVar = new javax.swing.JLabel();
        oreProbPrefOverallVar = new javax.swing.JLabel();
        wheatProbPrefOverallVar = new javax.swing.JLabel();
        prefSixPanel = new javax.swing.JPanel();
        bestPrefSixLabel = new javax.swing.JLabel();
        cityNamePrefSixLabel = new javax.swing.JLabel();
        cityNamePrefSixVarLabel = new javax.swing.JLabel();
        lumberProbPrefSixIcon = new javax.swing.JLabel();
        coalProbPrefSixIcon = new javax.swing.JLabel();
        cattleProbPrefSixIcon = new javax.swing.JLabel();
        oreProbPrefSixIcon = new javax.swing.JLabel();
        wheatProbPrefSixIcon = new javax.swing.JLabel();
        resourceLabelPrefSix = new javax.swing.JLabel();
        probabilityLabelPrefSix = new javax.swing.JLabel();
        lumberProbPrefSixVar = new javax.swing.JLabel();
        coalProbPrefSixVar = new javax.swing.JLabel();
        cattleProbPrefSixVar = new javax.swing.JLabel();
        oreProbPrefSixVar = new javax.swing.JLabel();
        wheatProbPrefSixVar = new javax.swing.JLabel();
        prefFivePanel = new javax.swing.JPanel();
        bestPrefFiveLabel = new javax.swing.JLabel();
        cityNamePrefFiveLabel = new javax.swing.JLabel();
        cityNamePrefFiveVarLabel = new javax.swing.JLabel();
        lumberProbPrefFiveIcon = new javax.swing.JLabel();
        coalProbPrefFiveIcon = new javax.swing.JLabel();
        cattleProbPrefFiveIcon = new javax.swing.JLabel();
        oreProbPrefFiveIcon = new javax.swing.JLabel();
        wheatProbPrefFiveIcon = new javax.swing.JLabel();
        resourceLabelPrefFive = new javax.swing.JLabel();
        probabilityLabelPrefFive = new javax.swing.JLabel();
        lumberProbPrefFiveVar = new javax.swing.JLabel();
        coalProbPrefFiveVar = new javax.swing.JLabel();
        cattleProbPrefFiveVar = new javax.swing.JLabel();
        oreProbPrefFiveVar = new javax.swing.JLabel();
        wheatProbPrefFiveVar = new javax.swing.JLabel();
        prefFourPanel = new javax.swing.JPanel();
        bestPrefFourLabel = new javax.swing.JLabel();
        cityNamePrefFourLabel = new javax.swing.JLabel();
        cityNamePrefFourVarLabel = new javax.swing.JLabel();
        lumberProbPrefFourIcon = new javax.swing.JLabel();
        coalProbPrefFourIcon = new javax.swing.JLabel();
        cattleProbPrefFourIcon = new javax.swing.JLabel();
        oreProbPrefFourIcon = new javax.swing.JLabel();
        wheatProbPrefFourIcon = new javax.swing.JLabel();
        resourceLabelPrefFour = new javax.swing.JLabel();
        probabilityLabelPrefFour = new javax.swing.JLabel();
        lumberProbPrefFourVar = new javax.swing.JLabel();
        coalProbPrefFourVar = new javax.swing.JLabel();
        cattleProbPrefFourVar = new javax.swing.JLabel();
        oreProbPrefFourVar = new javax.swing.JLabel();
        wheatProbPrefFourVar = new javax.swing.JLabel();
        prefThreePanel = new javax.swing.JPanel();
        bestPrefThreeLabel = new javax.swing.JLabel();
        cityNamePrefThreeLabel = new javax.swing.JLabel();
        cityNamePrefThreeVarLabel = new javax.swing.JLabel();
        lumberProbPrefThreeIcon = new javax.swing.JLabel();
        coalProbPrefThreeIcon = new javax.swing.JLabel();
        cattleProbPrefThreeIcon = new javax.swing.JLabel();
        oreProbPrefThreeIcon = new javax.swing.JLabel();
        wheatProbPrefThreeIcon = new javax.swing.JLabel();
        resourceLabelPrefThree = new javax.swing.JLabel();
        probabilityLabelPrefThree = new javax.swing.JLabel();
        lumberProbPrefThreeVar = new javax.swing.JLabel();
        coalProbPrefThreeVar = new javax.swing.JLabel();
        cattleProbPrefThreeVar = new javax.swing.JLabel();
        oreProbPrefThreeVar = new javax.swing.JLabel();
        wheatProbPrefThreeVar = new javax.swing.JLabel();
        prefTwoPanel = new javax.swing.JPanel();
        bestPrefTwoLabel = new javax.swing.JLabel();
        cityNamePrefTwoLabel = new javax.swing.JLabel();
        cityNamePrefTwoVarLabel = new javax.swing.JLabel();
        lumberProbPrefTwoIcon = new javax.swing.JLabel();
        coalProbPrefTwoIcon = new javax.swing.JLabel();
        cattleProbPrefTwoIcon = new javax.swing.JLabel();
        oreProbPrefTwoIcon = new javax.swing.JLabel();
        wheatProbPrefTwoIcon = new javax.swing.JLabel();
        resourceLabelPrefTwo = new javax.swing.JLabel();
        probabilityLabelPrefTwo = new javax.swing.JLabel();
        lumberProbPrefTwoVar = new javax.swing.JLabel();
        coalProbPrefTwoVar = new javax.swing.JLabel();
        cattleProbPrefTwoVar = new javax.swing.JLabel();
        oreProbPrefTwoVar = new javax.swing.JLabel();
        wheatProbPrefTwoVar = new javax.swing.JLabel();
        prefOnePanel = new javax.swing.JPanel();
        bestPrefOneLabel = new javax.swing.JLabel();
        cityNamePrefOneLabel = new javax.swing.JLabel();
        cityNamePrefOneVarLabel = new javax.swing.JLabel();
        lumberProbPrefOneIcon = new javax.swing.JLabel();
        coalProbPrefOneIcon = new javax.swing.JLabel();
        cattleProbPrefOneIcon = new javax.swing.JLabel();
        oreProbPrefOneIcon = new javax.swing.JLabel();
        wheatProbPrefOneIcon = new javax.swing.JLabel();
        resourceLabelPrefOne = new javax.swing.JLabel();
        probabilityLabelPrefOne = new javax.swing.JLabel();
        lumberProbPrefOneVar = new javax.swing.JLabel();
        coalProbPrefOneVar = new javax.swing.JLabel();
        cattleProbPrefOneVar = new javax.swing.JLabel();
        oreProbPrefOneVar = new javax.swing.JLabel();
        wheatProbPrefOneVar = new javax.swing.JLabel();
        okButton = new javax.swing.JButton();
        backgroundSearchLabel = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(728, 520));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        searchResultsPanels.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        prefSevenPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        prefSevenPanel.setOpaque(false);
        prefSevenPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bestPrefSevenLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14));
        prefSevenPanel.add(bestPrefSevenLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 5, 300, -1));

        cityNamePrefSevenLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14));
        cityNamePrefSevenLabel.setText("City Name:");
        prefSevenPanel.add(cityNamePrefSevenLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 25, -1, -1));

        cityNamePrefSevenVarLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14));
        prefSevenPanel.add(cityNamePrefSevenVarLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 24, -1, -1));

        lumberProbPrefSevenIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/lumberRaw.png"))); // NOI18N
        lumberProbPrefSevenIcon.setPreferredSize(new java.awt.Dimension(39, 39));
        prefSevenPanel.add(lumberProbPrefSevenIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 46, -1, -1));

        coalProbPrefSevenIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/coalRaw.png"))); // NOI18N
        coalProbPrefSevenIcon.setPreferredSize(new java.awt.Dimension(39, 39));
        prefSevenPanel.add(coalProbPrefSevenIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 46, -1, -1));

        cattleProbPrefSevenIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/cattleRaw.png"))); // NOI18N
        cattleProbPrefSevenIcon.setPreferredSize(new java.awt.Dimension(39, 39));
        prefSevenPanel.add(cattleProbPrefSevenIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 46, -1, -1));

        oreProbPrefSevenIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/oreRaw.png"))); // NOI18N
        prefSevenPanel.add(oreProbPrefSevenIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(245, 46, -1, -1));

        wheatProbPrefSevenIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/wheatRaw.png"))); // NOI18N
        wheatProbPrefSevenIcon.setPreferredSize(new java.awt.Dimension(39, 39));
        prefSevenPanel.add(wheatProbPrefSevenIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 46, -1, -1));

        resourceLabelPrefSeven.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        resourceLabelPrefSeven.setText("Resource:");
        prefSevenPanel.add(resourceLabelPrefSeven, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 57, -1, -1));

        probabilityLabelPrefSeven.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        probabilityLabelPrefSeven.setText("Probability:");
        prefSevenPanel.add(probabilityLabelPrefSeven, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 90, -1, -1));
        prefSevenPanel.add(lumberProbPrefSevenVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 92, -1, -1));
        prefSevenPanel.add(coalProbPrefSevenVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(166, 92, -1, -1));
        prefSevenPanel.add(cattleProbPrefSevenVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(211, 92, -1, -1));
        prefSevenPanel.add(oreProbPrefSevenVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(256, 92, -1, -1));
        prefSevenPanel.add(wheatProbPrefSevenVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 92, -1, -1));

        searchResultsPanels.add(prefSevenPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(369, 390, 340, 115));

        prefOverallPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        prefOverallPanel.setOpaque(false);
        prefOverallPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bestPrefOverallLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14));
        prefOverallPanel.add(bestPrefOverallLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 5, 300, -1));

        cityNamePrefOverallLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14));
        cityNamePrefOverallLabel.setText("City Name:");
        prefOverallPanel.add(cityNamePrefOverallLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 25, -1, -1));

        cityNamePrefOverallVarLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14));
        prefOverallPanel.add(cityNamePrefOverallVarLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 24, -1, -1));

        lumberProbPrefOverallIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/lumberRaw.png"))); // NOI18N
        lumberProbPrefOverallIcon.setPreferredSize(new java.awt.Dimension(39, 39));
        prefOverallPanel.add(lumberProbPrefOverallIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 46, -1, -1));

        coalProbPrefOverallIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/coalRaw.png"))); // NOI18N
        coalProbPrefOverallIcon.setPreferredSize(new java.awt.Dimension(39, 39));
        prefOverallPanel.add(coalProbPrefOverallIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 46, -1, -1));

        cattleProbPrefOverallIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/cattleRaw.png"))); // NOI18N
        cattleProbPrefOverallIcon.setPreferredSize(new java.awt.Dimension(39, 39));
        prefOverallPanel.add(cattleProbPrefOverallIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 46, -1, -1));

        oreProbPrefOverallIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/oreRaw.png"))); // NOI18N
        prefOverallPanel.add(oreProbPrefOverallIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(245, 46, -1, -1));

        wheatProbPrefOverallIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/wheatRaw.png"))); // NOI18N
        wheatProbPrefOverallIcon.setPreferredSize(new java.awt.Dimension(39, 39));
        prefOverallPanel.add(wheatProbPrefOverallIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 46, -1, -1));

        resourceLabelPrefOverall.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        resourceLabelPrefOverall.setText("Resource:");
        prefOverallPanel.add(resourceLabelPrefOverall, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 57, -1, -1));

        probabilityLabelPrefOverall.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        probabilityLabelPrefOverall.setText("Probability:");
        prefOverallPanel.add(probabilityLabelPrefOverall, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 90, -1, -1));
        prefOverallPanel.add(lumberProbPrefOverallVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 92, -1, -1));
        prefOverallPanel.add(coalProbPrefOverallVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(166, 92, -1, -1));
        prefOverallPanel.add(cattleProbPrefOverallVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(211, 92, -1, -1));
        prefOverallPanel.add(oreProbPrefOverallVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(256, 92, -1, -1));
        prefOverallPanel.add(wheatProbPrefOverallVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 92, -1, -1));

        searchResultsPanels.add(prefOverallPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 21, 340, 115));

        prefSixPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        prefSixPanel.setOpaque(false);
        prefSixPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bestPrefSixLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14));
        prefSixPanel.add(bestPrefSixLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 5, 300, -1));

        cityNamePrefSixLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14));
        cityNamePrefSixLabel.setText("City Name:");
        prefSixPanel.add(cityNamePrefSixLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 25, -1, -1));

        cityNamePrefSixVarLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14));
        prefSixPanel.add(cityNamePrefSixVarLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 24, -1, -1));

        lumberProbPrefSixIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/lumberRaw.png"))); // NOI18N
        lumberProbPrefSixIcon.setPreferredSize(new java.awt.Dimension(39, 39));
        prefSixPanel.add(lumberProbPrefSixIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 46, -1, -1));

        coalProbPrefSixIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/coalRaw.png"))); // NOI18N
        coalProbPrefSixIcon.setPreferredSize(new java.awt.Dimension(39, 39));
        prefSixPanel.add(coalProbPrefSixIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 46, -1, -1));

        cattleProbPrefSixIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/cattleRaw.png"))); // NOI18N
        cattleProbPrefSixIcon.setPreferredSize(new java.awt.Dimension(39, 39));
        prefSixPanel.add(cattleProbPrefSixIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 46, -1, -1));

        oreProbPrefSixIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/oreRaw.png"))); // NOI18N
        prefSixPanel.add(oreProbPrefSixIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(245, 46, -1, -1));

        wheatProbPrefSixIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/wheatRaw.png"))); // NOI18N
        wheatProbPrefSixIcon.setPreferredSize(new java.awt.Dimension(39, 39));
        prefSixPanel.add(wheatProbPrefSixIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 46, -1, -1));

        resourceLabelPrefSix.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        resourceLabelPrefSix.setText("Resource:");
        prefSixPanel.add(resourceLabelPrefSix, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 57, -1, -1));

        probabilityLabelPrefSix.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        probabilityLabelPrefSix.setText("Probability:");
        prefSixPanel.add(probabilityLabelPrefSix, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 90, -1, -1));
        prefSixPanel.add(lumberProbPrefSixVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 92, -1, -1));
        prefSixPanel.add(coalProbPrefSixVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(166, 92, -1, -1));
        prefSixPanel.add(cattleProbPrefSixVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(211, 92, -1, -1));
        prefSixPanel.add(oreProbPrefSixVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(256, 92, -1, -1));
        prefSixPanel.add(wheatProbPrefSixVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 92, -1, -1));

        searchResultsPanels.add(prefSixPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(369, 267, 340, 115));

        prefFivePanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        prefFivePanel.setOpaque(false);
        prefFivePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bestPrefFiveLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14));
        prefFivePanel.add(bestPrefFiveLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 5, 300, -1));

        cityNamePrefFiveLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14));
        cityNamePrefFiveLabel.setText("City Name:");
        prefFivePanel.add(cityNamePrefFiveLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 25, -1, -1));

        cityNamePrefFiveVarLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14));
        prefFivePanel.add(cityNamePrefFiveVarLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 24, -1, -1));

        lumberProbPrefFiveIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/lumberRaw.png"))); // NOI18N
        lumberProbPrefFiveIcon.setPreferredSize(new java.awt.Dimension(39, 39));
        prefFivePanel.add(lumberProbPrefFiveIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 46, -1, -1));

        coalProbPrefFiveIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/coalRaw.png"))); // NOI18N
        coalProbPrefFiveIcon.setPreferredSize(new java.awt.Dimension(39, 39));
        prefFivePanel.add(coalProbPrefFiveIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 46, -1, -1));

        cattleProbPrefFiveIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/cattleRaw.png"))); // NOI18N
        cattleProbPrefFiveIcon.setPreferredSize(new java.awt.Dimension(39, 39));
        prefFivePanel.add(cattleProbPrefFiveIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 46, -1, -1));

        oreProbPrefFiveIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/oreRaw.png"))); // NOI18N
        prefFivePanel.add(oreProbPrefFiveIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(245, 46, -1, -1));

        wheatProbPrefFiveIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/wheatRaw.png"))); // NOI18N
        wheatProbPrefFiveIcon.setPreferredSize(new java.awt.Dimension(39, 39));
        prefFivePanel.add(wheatProbPrefFiveIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 46, -1, -1));

        resourceLabelPrefFive.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        resourceLabelPrefFive.setText("Resource:");
        prefFivePanel.add(resourceLabelPrefFive, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 57, -1, -1));

        probabilityLabelPrefFive.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        probabilityLabelPrefFive.setText("Probability:");
        prefFivePanel.add(probabilityLabelPrefFive, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 90, -1, -1));
        prefFivePanel.add(lumberProbPrefFiveVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 92, -1, -1));
        prefFivePanel.add(coalProbPrefFiveVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(166, 92, -1, -1));
        prefFivePanel.add(cattleProbPrefFiveVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(211, 92, -1, -1));
        prefFivePanel.add(oreProbPrefFiveVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(256, 92, -1, -1));
        prefFivePanel.add(wheatProbPrefFiveVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 92, -1, -1));

        searchResultsPanels.add(prefFivePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(369, 144, 340, 115));

        prefFourPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        prefFourPanel.setOpaque(false);
        prefFourPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bestPrefFourLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14));
        prefFourPanel.add(bestPrefFourLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 5, 300, -1));

        cityNamePrefFourLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14));
        cityNamePrefFourLabel.setText("City Name:");
        prefFourPanel.add(cityNamePrefFourLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 25, -1, -1));

        cityNamePrefFourVarLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14));
        prefFourPanel.add(cityNamePrefFourVarLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 24, -1, -1));

        lumberProbPrefFourIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/lumberRaw.png"))); // NOI18N
        lumberProbPrefFourIcon.setPreferredSize(new java.awt.Dimension(39, 39));
        prefFourPanel.add(lumberProbPrefFourIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 46, -1, -1));

        coalProbPrefFourIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/coalRaw.png"))); // NOI18N
        coalProbPrefFourIcon.setPreferredSize(new java.awt.Dimension(39, 39));
        prefFourPanel.add(coalProbPrefFourIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 46, -1, -1));

        cattleProbPrefFourIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/cattleRaw.png"))); // NOI18N
        cattleProbPrefFourIcon.setPreferredSize(new java.awt.Dimension(39, 39));
        prefFourPanel.add(cattleProbPrefFourIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 46, -1, -1));

        oreProbPrefFourIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/oreRaw.png"))); // NOI18N
        prefFourPanel.add(oreProbPrefFourIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(245, 46, -1, -1));

        wheatProbPrefFourIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/wheatRaw.png"))); // NOI18N
        wheatProbPrefFourIcon.setPreferredSize(new java.awt.Dimension(39, 39));
        prefFourPanel.add(wheatProbPrefFourIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 46, -1, -1));

        resourceLabelPrefFour.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        resourceLabelPrefFour.setText("Resource:");
        prefFourPanel.add(resourceLabelPrefFour, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 57, -1, -1));

        probabilityLabelPrefFour.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        probabilityLabelPrefFour.setText("Probability:");
        prefFourPanel.add(probabilityLabelPrefFour, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 90, -1, -1));
        prefFourPanel.add(lumberProbPrefFourVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 92, -1, -1));
        prefFourPanel.add(coalProbPrefFourVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(166, 92, -1, -1));
        prefFourPanel.add(cattleProbPrefFourVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(211, 92, -1, -1));
        prefFourPanel.add(oreProbPrefFourVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(256, 92, -1, -1));
        prefFourPanel.add(wheatProbPrefFourVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 92, -1, -1));

        searchResultsPanels.add(prefFourPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 21, 340, 115));

        prefThreePanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        prefThreePanel.setOpaque(false);
        prefThreePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bestPrefThreeLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14));
        prefThreePanel.add(bestPrefThreeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 5, 300, -1));

        cityNamePrefThreeLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14));
        cityNamePrefThreeLabel.setText("City Name:");
        prefThreePanel.add(cityNamePrefThreeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 25, -1, -1));

        cityNamePrefThreeVarLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14));
        prefThreePanel.add(cityNamePrefThreeVarLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 24, -1, -1));

        lumberProbPrefThreeIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/lumberRaw.png"))); // NOI18N
        lumberProbPrefThreeIcon.setPreferredSize(new java.awt.Dimension(39, 39));
        prefThreePanel.add(lumberProbPrefThreeIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 46, -1, -1));

        coalProbPrefThreeIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/coalRaw.png"))); // NOI18N
        coalProbPrefThreeIcon.setPreferredSize(new java.awt.Dimension(39, 39));
        prefThreePanel.add(coalProbPrefThreeIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 46, -1, -1));

        cattleProbPrefThreeIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/cattleRaw.png"))); // NOI18N
        cattleProbPrefThreeIcon.setPreferredSize(new java.awt.Dimension(39, 39));
        prefThreePanel.add(cattleProbPrefThreeIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 46, -1, -1));

        oreProbPrefThreeIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/oreRaw.png"))); // NOI18N
        prefThreePanel.add(oreProbPrefThreeIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(245, 46, -1, -1));

        wheatProbPrefThreeIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/wheatRaw.png"))); // NOI18N
        wheatProbPrefThreeIcon.setPreferredSize(new java.awt.Dimension(39, 39));
        prefThreePanel.add(wheatProbPrefThreeIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 46, -1, -1));

        resourceLabelPrefThree.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        resourceLabelPrefThree.setText("Resource:");
        prefThreePanel.add(resourceLabelPrefThree, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 57, -1, -1));

        probabilityLabelPrefThree.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        probabilityLabelPrefThree.setText("Probability:");
        prefThreePanel.add(probabilityLabelPrefThree, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 90, -1, -1));
        prefThreePanel.add(lumberProbPrefThreeVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 92, -1, -1));
        prefThreePanel.add(coalProbPrefThreeVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(166, 92, -1, -1));
        prefThreePanel.add(cattleProbPrefThreeVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(211, 92, -1, -1));
        prefThreePanel.add(oreProbPrefThreeVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(256, 92, -1, -1));
        prefThreePanel.add(wheatProbPrefThreeVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 92, -1, -1));

        searchResultsPanels.add(prefThreePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, 340, 115));

        prefTwoPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        prefTwoPanel.setOpaque(false);
        prefTwoPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bestPrefTwoLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14));
        prefTwoPanel.add(bestPrefTwoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 5, 300, -1));

        cityNamePrefTwoLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14));
        cityNamePrefTwoLabel.setText("City Name:");
        prefTwoPanel.add(cityNamePrefTwoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 25, -1, -1));

        cityNamePrefTwoVarLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14));
        prefTwoPanel.add(cityNamePrefTwoVarLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 24, -1, -1));

        lumberProbPrefTwoIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/lumberRaw.png"))); // NOI18N
        lumberProbPrefTwoIcon.setPreferredSize(new java.awt.Dimension(39, 39));
        prefTwoPanel.add(lumberProbPrefTwoIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 46, -1, -1));

        coalProbPrefTwoIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/coalRaw.png"))); // NOI18N
        coalProbPrefTwoIcon.setPreferredSize(new java.awt.Dimension(39, 39));
        prefTwoPanel.add(coalProbPrefTwoIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 46, -1, -1));

        cattleProbPrefTwoIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/cattleRaw.png"))); // NOI18N
        cattleProbPrefTwoIcon.setPreferredSize(new java.awt.Dimension(39, 39));
        prefTwoPanel.add(cattleProbPrefTwoIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 46, -1, -1));

        oreProbPrefTwoIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/oreRaw.png"))); // NOI18N
        prefTwoPanel.add(oreProbPrefTwoIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(245, 46, -1, -1));

        wheatProbPrefTwoIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/wheatRaw.png"))); // NOI18N
        wheatProbPrefTwoIcon.setPreferredSize(new java.awt.Dimension(39, 39));
        prefTwoPanel.add(wheatProbPrefTwoIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 46, -1, -1));

        resourceLabelPrefTwo.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        resourceLabelPrefTwo.setText("Resource:");
        prefTwoPanel.add(resourceLabelPrefTwo, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 57, -1, -1));

        probabilityLabelPrefTwo.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        probabilityLabelPrefTwo.setText("Probability:");
        prefTwoPanel.add(probabilityLabelPrefTwo, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 90, -1, -1));
        prefTwoPanel.add(lumberProbPrefTwoVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 92, -1, -1));
        prefTwoPanel.add(coalProbPrefTwoVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(166, 92, -1, -1));
        prefTwoPanel.add(cattleProbPrefTwoVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(211, 92, -1, -1));
        prefTwoPanel.add(oreProbPrefTwoVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(256, 92, -1, -1));
        prefTwoPanel.add(wheatProbPrefTwoVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 92, -1, -1));

        searchResultsPanels.add(prefTwoPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 267, 340, 115));

        prefOnePanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        prefOnePanel.setOpaque(false);
        prefOnePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bestPrefOneLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14));
        prefOnePanel.add(bestPrefOneLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 5, 300, -1));

        cityNamePrefOneLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14));
        cityNamePrefOneLabel.setText("City Name:");
        prefOnePanel.add(cityNamePrefOneLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 25, -1, -1));

        cityNamePrefOneVarLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14));
        prefOnePanel.add(cityNamePrefOneVarLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 24, -1, -1));

        lumberProbPrefOneIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/lumberRaw.png"))); // NOI18N
        lumberProbPrefOneIcon.setPreferredSize(new java.awt.Dimension(39, 39));
        prefOnePanel.add(lumberProbPrefOneIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 46, -1, -1));

        coalProbPrefOneIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/coalRaw.png"))); // NOI18N
        coalProbPrefOneIcon.setPreferredSize(new java.awt.Dimension(39, 39));
        prefOnePanel.add(coalProbPrefOneIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 46, -1, -1));

        cattleProbPrefOneIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/cattleRaw.png"))); // NOI18N
        cattleProbPrefOneIcon.setPreferredSize(new java.awt.Dimension(39, 39));
        prefOnePanel.add(cattleProbPrefOneIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 46, -1, -1));

        oreProbPrefOneIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/oreRaw.png"))); // NOI18N
        prefOnePanel.add(oreProbPrefOneIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(245, 46, -1, -1));

        wheatProbPrefOneIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/wheatRaw.png"))); // NOI18N
        wheatProbPrefOneIcon.setPreferredSize(new java.awt.Dimension(39, 39));
        prefOnePanel.add(wheatProbPrefOneIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 46, -1, -1));

        resourceLabelPrefOne.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        resourceLabelPrefOne.setText("Resource:");
        prefOnePanel.add(resourceLabelPrefOne, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 57, -1, -1));

        probabilityLabelPrefOne.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        probabilityLabelPrefOne.setText("Probability:");
        prefOnePanel.add(probabilityLabelPrefOne, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 90, -1, -1));
        prefOnePanel.add(lumberProbPrefOneVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 92, -1, -1));
        prefOnePanel.add(coalProbPrefOneVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(166, 92, -1, -1));
        prefOnePanel.add(cattleProbPrefOneVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(211, 92, -1, -1));
        prefOnePanel.add(oreProbPrefOneVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(256, 92, -1, -1));
        prefOnePanel.add(wheatProbPrefOneVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 92, -1, -1));

        searchResultsPanels.add(prefOnePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 144, 340, 115));

        okButton.setBackground(new java.awt.Color(239, 228, 176));
        okButton.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        okButton.setText("OK");
        okButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        okButton.setContentAreaFilled(false);
        okButton.setFocusable(false);
        okButton.setOpaque(true);
        okButton.setPreferredSize(new java.awt.Dimension(75, 25));
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
        searchResultsPanels.add(okButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 515, 110, 30));

        backgroundSearchLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/searchResultsBackground.png"))); // NOI18N
        searchResultsPanels.add(backgroundSearchLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(searchResultsPanels, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 728, 570));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okButtonMouseEntered
        mouseOnOkButton=true;
}//GEN-LAST:event_okButtonMouseEntered
    private void okButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okButtonMouseExited
        if(mouseOnOkButton&&buttonPressed){
            okButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            okButton.setLocation(okButton.getX() + 1, okButton.getY());
        }
        mouseOnOkButton=false;
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
        if(mouseOnOkButton) {
            okButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            okButton.setLocation(okButton.getX() + 1, okButton.getY());
            buttonPressed=false;
            this.setVisible(false);
        }
}//GEN-LAST:event_okButtonMouseReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new searchDisplayWindow().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel backgroundSearchLabel;
    private javax.swing.JLabel bestPrefFiveLabel;
    private javax.swing.JLabel bestPrefFourLabel;
    private javax.swing.JLabel bestPrefOneLabel;
    private javax.swing.JLabel bestPrefOverallLabel;
    private javax.swing.JLabel bestPrefSevenLabel;
    private javax.swing.JLabel bestPrefSixLabel;
    private javax.swing.JLabel bestPrefThreeLabel;
    private javax.swing.JLabel bestPrefTwoLabel;
    private javax.swing.JLabel cattleProbPrefFiveIcon;
    private javax.swing.JLabel cattleProbPrefFiveVar;
    private javax.swing.JLabel cattleProbPrefFourIcon;
    private javax.swing.JLabel cattleProbPrefFourVar;
    private javax.swing.JLabel cattleProbPrefOneIcon;
    private javax.swing.JLabel cattleProbPrefOneVar;
    private javax.swing.JLabel cattleProbPrefOverallIcon;
    private javax.swing.JLabel cattleProbPrefOverallVar;
    private javax.swing.JLabel cattleProbPrefSevenIcon;
    private javax.swing.JLabel cattleProbPrefSevenVar;
    private javax.swing.JLabel cattleProbPrefSixIcon;
    private javax.swing.JLabel cattleProbPrefSixVar;
    private javax.swing.JLabel cattleProbPrefThreeIcon;
    private javax.swing.JLabel cattleProbPrefThreeVar;
    private javax.swing.JLabel cattleProbPrefTwoIcon;
    private javax.swing.JLabel cattleProbPrefTwoVar;
    private javax.swing.JLabel cityNamePrefFiveLabel;
    private javax.swing.JLabel cityNamePrefFiveVarLabel;
    private javax.swing.JLabel cityNamePrefFourLabel;
    private javax.swing.JLabel cityNamePrefFourVarLabel;
    private javax.swing.JLabel cityNamePrefOneLabel;
    private javax.swing.JLabel cityNamePrefOneVarLabel;
    private javax.swing.JLabel cityNamePrefOverallLabel;
    private javax.swing.JLabel cityNamePrefOverallVarLabel;
    private javax.swing.JLabel cityNamePrefSevenLabel;
    private javax.swing.JLabel cityNamePrefSevenVarLabel;
    private javax.swing.JLabel cityNamePrefSixLabel;
    private javax.swing.JLabel cityNamePrefSixVarLabel;
    private javax.swing.JLabel cityNamePrefThreeLabel;
    private javax.swing.JLabel cityNamePrefThreeVarLabel;
    private javax.swing.JLabel cityNamePrefTwoLabel;
    private javax.swing.JLabel cityNamePrefTwoVarLabel;
    private javax.swing.JLabel coalProbPrefFiveIcon;
    private javax.swing.JLabel coalProbPrefFiveVar;
    private javax.swing.JLabel coalProbPrefFourIcon;
    private javax.swing.JLabel coalProbPrefFourVar;
    private javax.swing.JLabel coalProbPrefOneIcon;
    private javax.swing.JLabel coalProbPrefOneVar;
    private javax.swing.JLabel coalProbPrefOverallIcon;
    private javax.swing.JLabel coalProbPrefOverallVar;
    private javax.swing.JLabel coalProbPrefSevenIcon;
    private javax.swing.JLabel coalProbPrefSevenVar;
    private javax.swing.JLabel coalProbPrefSixIcon;
    private javax.swing.JLabel coalProbPrefSixVar;
    private javax.swing.JLabel coalProbPrefThreeIcon;
    private javax.swing.JLabel coalProbPrefThreeVar;
    private javax.swing.JLabel coalProbPrefTwoIcon;
    private javax.swing.JLabel coalProbPrefTwoVar;
    private javax.swing.JLabel lumberProbPrefFiveIcon;
    private javax.swing.JLabel lumberProbPrefFiveVar;
    private javax.swing.JLabel lumberProbPrefFourIcon;
    private javax.swing.JLabel lumberProbPrefFourVar;
    private javax.swing.JLabel lumberProbPrefOneIcon;
    private javax.swing.JLabel lumberProbPrefOneVar;
    private javax.swing.JLabel lumberProbPrefOverallIcon;
    private javax.swing.JLabel lumberProbPrefOverallVar;
    private javax.swing.JLabel lumberProbPrefSevenIcon;
    private javax.swing.JLabel lumberProbPrefSevenVar;
    private javax.swing.JLabel lumberProbPrefSixIcon;
    private javax.swing.JLabel lumberProbPrefSixVar;
    private javax.swing.JLabel lumberProbPrefThreeIcon;
    private javax.swing.JLabel lumberProbPrefThreeVar;
    private javax.swing.JLabel lumberProbPrefTwoIcon;
    private javax.swing.JLabel lumberProbPrefTwoVar;
    private javax.swing.JButton okButton;
    private javax.swing.JLabel oreProbPrefFiveIcon;
    private javax.swing.JLabel oreProbPrefFiveVar;
    private javax.swing.JLabel oreProbPrefFourIcon;
    private javax.swing.JLabel oreProbPrefFourVar;
    private javax.swing.JLabel oreProbPrefOneIcon;
    private javax.swing.JLabel oreProbPrefOneVar;
    private javax.swing.JLabel oreProbPrefOverallIcon;
    private javax.swing.JLabel oreProbPrefOverallVar;
    private javax.swing.JLabel oreProbPrefSevenIcon;
    private javax.swing.JLabel oreProbPrefSevenVar;
    private javax.swing.JLabel oreProbPrefSixIcon;
    private javax.swing.JLabel oreProbPrefSixVar;
    private javax.swing.JLabel oreProbPrefThreeIcon;
    private javax.swing.JLabel oreProbPrefThreeVar;
    private javax.swing.JLabel oreProbPrefTwoIcon;
    private javax.swing.JLabel oreProbPrefTwoVar;
    private javax.swing.JPanel prefFivePanel;
    private javax.swing.JPanel prefFourPanel;
    private javax.swing.JPanel prefOnePanel;
    private javax.swing.JPanel prefOverallPanel;
    private javax.swing.JPanel prefSevenPanel;
    private javax.swing.JPanel prefSixPanel;
    private javax.swing.JPanel prefThreePanel;
    private javax.swing.JPanel prefTwoPanel;
    private javax.swing.JLabel probabilityLabelPrefFive;
    private javax.swing.JLabel probabilityLabelPrefFour;
    private javax.swing.JLabel probabilityLabelPrefOne;
    private javax.swing.JLabel probabilityLabelPrefOverall;
    private javax.swing.JLabel probabilityLabelPrefSeven;
    private javax.swing.JLabel probabilityLabelPrefSix;
    private javax.swing.JLabel probabilityLabelPrefThree;
    private javax.swing.JLabel probabilityLabelPrefTwo;
    private javax.swing.JLabel resourceLabelPrefFive;
    private javax.swing.JLabel resourceLabelPrefFour;
    private javax.swing.JLabel resourceLabelPrefOne;
    private javax.swing.JLabel resourceLabelPrefOverall;
    private javax.swing.JLabel resourceLabelPrefSeven;
    private javax.swing.JLabel resourceLabelPrefSix;
    private javax.swing.JLabel resourceLabelPrefThree;
    private javax.swing.JLabel resourceLabelPrefTwo;
    private javax.swing.JPanel searchResultsPanels;
    private javax.swing.JLabel wheatProbPrefFiveIcon;
    private javax.swing.JLabel wheatProbPrefFiveVar;
    private javax.swing.JLabel wheatProbPrefFourIcon;
    private javax.swing.JLabel wheatProbPrefFourVar;
    private javax.swing.JLabel wheatProbPrefOneIcon;
    private javax.swing.JLabel wheatProbPrefOneVar;
    private javax.swing.JLabel wheatProbPrefOverallIcon;
    private javax.swing.JLabel wheatProbPrefOverallVar;
    private javax.swing.JLabel wheatProbPrefSevenIcon;
    private javax.swing.JLabel wheatProbPrefSevenVar;
    private javax.swing.JLabel wheatProbPrefSixIcon;
    private javax.swing.JLabel wheatProbPrefSixVar;
    private javax.swing.JLabel wheatProbPrefThreeIcon;
    private javax.swing.JLabel wheatProbPrefThreeVar;
    private javax.swing.JLabel wheatProbPrefTwoIcon;
    private javax.swing.JLabel wheatProbPrefTwoVar;
    // End of variables declaration//GEN-END:variables
}

/*
 * gameFrame.java
 *
 * Created on Sep 22, 2013, 4:58:17 AM
 */
package settlerstrailstorails;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import javax.swing.Timer;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.BevelBorder;

/**
 *
 * @author User 1
 */
class gameFrame extends javax.swing.JFrame{
    
    ArrayList<Integer> chits = new ArrayList<Integer>();
    ArrayList<Integer> cumulativeRolls=new ArrayList<Integer>();
    ImageIcon orangeHouse, greenHouse, redHouse, whiteHouse,orangeTrain,greenTrain,
            redTrain,whiteTrain,redSettler,greenSettler,
            whiteSettler,orangeSettler,trashBin;
    Player[] player,orderOfPlayers;
    Player redPlayer,greenPlayer,orangePlayer,whitePlayer,currPlayer,realCurrPlayer=null;
    CityHex[] citySpot = new CityHex[45];
    CityHex spokane,seattle, portland, boise, helena, idahoFalls,billings,
            casper,rapidCity,bismark,omaha,minneapolis,chicago,indianapolis,
            columbus,washingtonDC,albany,boston,newYork,sanFrancisco,reno,
            losAngeles,lasVegas,saltLakeCity,flagstaff,denver,santaFe,elPaso,
            amarillo,wichita,dallas,sanAntonio,shreveport,houston,littleRock,
            stLouis,jackson,newOrleans,nashville,lexington,atlanta,pensacola,
            raleigh,columbia,jacksonville;
    Hex[] hex = new Hex[71];
    Hex[] singleQHex = new Hex[12];
    Hex[] doubleQHex = new Hex[14];
    ArrayList<Hex> singleQHexes = new ArrayList<Hex>();
    Hex robberHex;
    CityInfoDialog cityInfoWindow;
    javax.swing.JButton[] railButton = new javax.swing.JButton[216];
    javax.swing.JButton[] cityButton = new javax.swing.JButton[45];
    String playerOneName,playerTwoName,playerThreeName,playerFourName;
    private boolean buildingSettlement=false,forwardSetupTurn=true,
            setupPhaseLoopSecondPlayer=false,currentlyDisplayingCityNames=false,
            ignorePurpleDuringBuild,fourthPlayerCityBuild,buildingRail,railCheat,
            ignoreResources=false,buildingSettler,movingSettler,movingTrain,
            buildingTrain,buildTwoRails=false,rolled,movingRobber,doubleGold,
            setupPhase=false,selectDiceRolls=false,doneRollingForFirst,
            ignoreExtraordinaryBuildingPhase,disableCityButtons;
    private AvaJButton optionsButton,buildButton,exitButton,buildCityButton,
            cancelButton,cheatSeeHandButton,redInfoButton,whiteInfoButton,
            greenInfoButton,orangeInfoButton,seeAllButton,endTurnButton,
            utilitiesButton,tradeButton,rollButton,developmentCardButton,
            buildingCostsButton;
    private int numberOfPlayers,fourthPlayerCities=0,extraordinaryBuildingPhaseNum=0;
    long timeAtBeggining;
    static int volumeInt=MainFrame.volumeInt;
    static ArrayList<String> allMessages = new ArrayList<String>();
    private static JLabel[] infoMessage = new JLabel[5];
    InGameOptionsMenu igom;
    Color backgroundColor;
    static Clip soundPlaying;
    PlayerInfoWindow piw;
    moreMessagesWindow mmw;
    SettlersConfirmDialog scd;
    buildWindow bw;
    UtilitiesWindow uw;
    Rail[] rail;
    //required variables for rolling for first
    int numberOfTimesRolledForFirst=0,reRollCounterHigh=0,
            reRollCounterLow;
    boolean rollingForFirst=false;
    int[] reRollsHigh, reRollsLow;
    int[] rollForFirst;
    ArrayList<Player> reRHigh, reRLow;
    //end required variables for rolling for first
    Intersection[] intersection;
    MouseAdapter buttonMouseAdapter;
    developmentCardWindow dpcw;
    TradeWindow tw;
    static final ActionListener taskPerformer = new ActionListener() {//<editor-fold>
        @Override
        public void actionPerformed(ActionEvent evt) {
            if (infoDialogOne.getBackground() == Color.WHITE) {
                infoDialogOne.setBackground(Color.RED);
            } else if (infoDialogOne.getBackground() == Color.RED) {
                infoDialogOne.setBackground(Color.WHITE);
            }
        }
    };//</editor-fold>
    static Timer redBlinkerTimer = new Timer(1000, taskPerformer);
    public gameFrame(int numberOfPlayers, int numOfHumanPlayers,
            Player.Color c, boolean dG, String p1n, String p2n, String p3n,
            String p4n, int startingGold, ArrayList<Integer> predeterminedChits) {//<editor-fold>
        timeAtBeggining = System.currentTimeMillis();
        allMessages = new ArrayList<String>();
        playerOneName = p1n;
        playerTwoName = p2n;
        playerThreeName = p3n;
        if (numberOfPlayers == 4) {
            playerFourName = p4n;
        }
        orderOfPlayers=new Player[numberOfPlayers];
        CityInfoDialog cIW=new CityInfoDialog(this);
        cIW.setColor(c);
        cityInfoWindow=cIW;
        this.doubleGold=dG;
        this.numberOfPlayers=numberOfPlayers;
        rollForFirst=new int[numberOfPlayers];
        player=new Player[4];
        
        //set transparent icons
        //<editor-fold>
        BufferedImage tempHouse=
        makeColorTransparent("/settlerstrailstorails/resources/orangeHouse.png", Color.WHITE);
        orangeHouse=new ImageIcon(tempHouse);
        tempHouse=
        makeColorTransparent("/settlerstrailstorails/resources/greenHouse.png", Color.WHITE);
        greenHouse=new ImageIcon(tempHouse);
        tempHouse=
        makeColorTransparent("/settlerstrailstorails/resources/redHouse.png", Color.WHITE);
        redHouse=new ImageIcon(tempHouse);
        tempHouse=
        makeColorTransparent("/settlerstrailstorails/resources/whiteHouse.png", Color.WHITE);
        whiteHouse=new ImageIcon(tempHouse);
        tempHouse=
        makeColorTransparent("/settlerstrailstorails/resources/redSettler.png", Color.WHITE);
        redSettler=new ImageIcon(tempHouse);    
        tempHouse=
        makeColorTransparent("/settlerstrailstorails/resources/whiteSettler.png", Color.WHITE);
        whiteSettler=new ImageIcon(tempHouse);    
        tempHouse=
        makeColorTransparent("/settlerstrailstorails/resources/greenSettler.png", Color.WHITE);
        greenSettler=new ImageIcon(tempHouse);    
        tempHouse=
        makeColorTransparent("/settlerstrailstorails/resources/orangeSettler.png", Color.WHITE);
        orangeSettler=new ImageIcon(tempHouse);
        tempHouse=
        makeColorTransparent("/settlerstrailstorails/resources/trashBin.png", Color.WHITE);
        trashBin=new ImageIcon(tempHouse);
        //</editor-fold>
        
        initComponents();
        //menu buttons
        buildButton = new AvaJButton(buildButton1);
        exitButton = new AvaJButton(exitButton1);
        buildCityButton = new AvaJButton(buildCityButton1);
        cancelButton = new AvaJButton(cancelButton1);
        redInfoButton = new AvaJButton(redInfoButton1);
        whiteInfoButton = new AvaJButton(whiteInfoButton1);
        greenInfoButton = new AvaJButton(greenInfoButton1);
        orangeInfoButton = new AvaJButton(orangeInfoButton1);
        seeAllButton = new AvaJButton(seeAllButton1);
        optionsButton = new AvaJButton(optionsButton1);
        endTurnButton = new AvaJButton(endTurnButton1);
        utilitiesButton = new AvaJButton(utilitiesButton1);
        tradeButton = new AvaJButton(tradeButton1);
        rollButton = new AvaJButton(rollButton1);
        buildingCostsButton = new AvaJButton(buildingCostButton1);
        try{
            setIconImage(ImageIO.read(getClass().getResource("/settlerstrailstorails/resources/icon.png")));
        }catch(Exception e){
            
        }
        
        //initialize players
        //<editor-fold>
        if(c==Player.Color.RED){
        redPlayer=player[0]=new Player(Player.Color.RED,false,p1n,numberOfPlayers,goodsSidePanel,false,doubleGold,this);
        greenPlayer=player[1]=new Player(Player.Color.GREEN,false,p2n,numberOfPlayers,goodsSidePanel,false,doubleGold,this);
        whitePlayer=player[2]=new Player(Player.Color.WHITE,false,p3n,numberOfPlayers,goodsSidePanel,false,doubleGold,this);
        orangePlayer=player[3]=new Player(Player.Color.ORANGE,false,p4n,numberOfPlayers,goodsSidePanel,true,doubleGold,this);
        }else if(c==Player.Color.GREEN && numberOfPlayers==3){
        greenPlayer=player[0]=new Player(Player.Color.GREEN,false,p1n,numberOfPlayers,goodsSidePanel,false,doubleGold,this);
        whitePlayer=player[1]=new Player(Player.Color.WHITE,false,p2n,numberOfPlayers,goodsSidePanel,false,doubleGold,this);
        orangePlayer=player[3]=new Player(Player.Color.ORANGE,false,p4n,numberOfPlayers,goodsSidePanel,true,doubleGold,this); 
        redPlayer=player[2]=new Player(Player.Color.RED,false,p3n,numberOfPlayers,goodsSidePanel,false,doubleGold,this);
        }else if(c==Player.Color.GREEN && numberOfPlayers==4){
        greenPlayer=player[0]=new Player(Player.Color.GREEN,false,p1n,numberOfPlayers,goodsSidePanel,false,doubleGold,this);
        whitePlayer=player[1]=new Player(Player.Color.WHITE,false,p2n,numberOfPlayers,goodsSidePanel,false,doubleGold,this);
        orangePlayer=player[2]=new Player(Player.Color.ORANGE,false,p3n,numberOfPlayers,goodsSidePanel,false,doubleGold,this); 
        redPlayer=player[3]=new Player(Player.Color.RED,false,p4n,numberOfPlayers,goodsSidePanel,true,doubleGold,this);
        }else if(c==Player.Color.WHITE && numberOfPlayers==3){
        whitePlayer=player[0]=new Player(Player.Color.WHITE,false,p1n,numberOfPlayers,goodsSidePanel,false,doubleGold,this);
        orangePlayer=player[3]=new Player(Player.Color.ORANGE,false,p4n,numberOfPlayers,goodsSidePanel,true,doubleGold,this); 
        redPlayer=player[2]=new Player(Player.Color.RED,false,p3n,numberOfPlayers,goodsSidePanel,false,doubleGold,this);
        greenPlayer=player[1]=new Player(Player.Color.GREEN,false,p2n,numberOfPlayers,goodsSidePanel,false,doubleGold,this);
        }else if(c==Player.Color.WHITE && numberOfPlayers==4){
        whitePlayer=player[0]=new Player(Player.Color.WHITE,false,p1n,numberOfPlayers,goodsSidePanel,false,doubleGold, this);
        orangePlayer=player[1]=new Player(Player.Color.ORANGE,false,p2n,numberOfPlayers,goodsSidePanel,false,doubleGold, this); 
        redPlayer=player[2]=new Player(Player.Color.RED,false,p3n,numberOfPlayers,goodsSidePanel,false,doubleGold, this);
        greenPlayer=player[3]=new Player(Player.Color.GREEN,false,p4n,numberOfPlayers,goodsSidePanel,true,doubleGold, this);
        }else if(numberOfPlayers==4){
        orangePlayer=player[0]=new Player(Player.Color.ORANGE,false,p1n,numberOfPlayers,goodsSidePanel,false,doubleGold, this);
        redPlayer=player[1]=new Player(Player.Color.RED,false,p2n,numberOfPlayers,goodsSidePanel,false,doubleGold, this); 
        greenPlayer=player[2]=new Player(Player.Color.GREEN,false,p3n,numberOfPlayers,goodsSidePanel,false,doubleGold, this);
        whitePlayer=player[3]=new Player(Player.Color.WHITE,false,p4n,numberOfPlayers,goodsSidePanel,true,doubleGold, this);
        }else if(numberOfPlayers==3){
        orangePlayer=player[0]=new Player(Player.Color.ORANGE,false,p1n,numberOfPlayers,goodsSidePanel,false,doubleGold, this);
        redPlayer=player[3]=new Player(Player.Color.RED,false,p4n,numberOfPlayers,goodsSidePanel,true,doubleGold, this); 
        greenPlayer=player[1]=new Player(Player.Color.GREEN,false,p2n,numberOfPlayers,goodsSidePanel,false,doubleGold, this);
        whitePlayer=player[2]=new Player(Player.Color.WHITE,false,p3n,numberOfPlayers,goodsSidePanel,false,doubleGold, this);
        }
        for(int i=0;i<numOfHumanPlayers;i++){
            player[i].setHuman(true);
        }
        //</editor-fold>
        
        //set track circles visible
        //<editor-fold>
        if(numberOfPlayers==3){
            if(player[3].getColor()==Player.Color.RED){
                redTextTrackCircleLabel.setVisible(false);
                redTrackCircleLabel.setVisible(false);
                redInfoButton.setVisible(false);
            }else if(player[3].getColor()==Player.Color.GREEN){
                greenTextTrackCircleLabel.setVisible(false);
                greenInfoButton.setVisible(false);
                greenTrackCircleLabel.setVisible(false);
            }else if(player[3].getColor()==Player.Color.WHITE){
                whiteTextTrackCircleLabel.setVisible(false);
                whiteTrackCircleLabel.setVisible(false);
                whiteInfoButton.setVisible(false);
            }else if(player[3].getColor()==Player.Color.ORANGE){
                orangeTextTrackCircleLabel.setVisible(false);
                orangeTrackCircleLabel.setVisible(false);
                orangeInfoButton.setVisible(false);
            }
        }//</editor-fold>
        
        //initialize chits
        //<editor-fold>
        chits.add(9);
        chits.add(9);
        chits.add(9);
        chits.add(9);
        chits.add(10);
        chits.add(10);
        chits.add(10);
        chits.add(10);
        chits.add(11);
        chits.add(11);
        chits.add(11);
        chits.add(11);
        //</editor-fold>
        
        //initialize city buttons
        //<editor-fold>
        cityButton[0]=seattleButton;
        cityButton[1]=spokaneButton;
        cityButton[2]=portlandButton;
        cityButton[3]=sanFranciscoButton;
        cityButton[4]=renoButton;
        cityButton[5]=losAngelesButton;
        cityButton[6]=lasVegasButton;
        cityButton[7]=boiseButton;
        cityButton[8]=helenaButton;
        cityButton[9]=idahoFallsButton;
        cityButton[10]=saltLakeCityButton;
        cityButton[11]=flagstaffButton;
        cityButton[12]=billingsButton;
        cityButton[13]=casperButton;
        cityButton[14]=rapidCityButton;
        cityButton[15]=denverButton;
        cityButton[16]=santaFeButton;
        cityButton[17]=elPasoButton;
        cityButton[18]=amarilloButton;
        cityButton[19]=bismarkButton;
        cityButton[20]=sanAntonioButton;
        cityButton[21]=dallasButton;
        cityButton[22]=wichitaButton;
        cityButton[23]=omahaButton;
        cityButton[24]=minneapolisButton;
        cityButton[25]=littleRockButton;
        cityButton[26]=shreveportButton;
        cityButton[27]=houstonButton;
        cityButton[28]=stLouisButton;
        cityButton[29]=jacksonButton;
        cityButton[30]=chicagoButton;
        cityButton[31]=indianapolisButton;
        cityButton[32]=nashvilleButton;
        cityButton[33]=newOrleansButton;
        cityButton[34]=columbusButton;
        cityButton[35]=lexingtonButton;
        cityButton[36]=atlantaButton;
        cityButton[37]=pensacolaButton;
        cityButton[38]=albanyButton;
        cityButton[39]=washingtonDCButton;
        cityButton[40]=raleighButton;
        cityButton[41]=columbiaButton;
        cityButton[42]=jacksonvilleButton;
        cityButton[43]=newYorkButton;
        cityButton[44]=bostonButton;
        //</editor-fold>
        
        //initialize hexes
        //<editor-fold>
        hex[0]=doubleQHex[0]=new Hex(0,0,false,true,Hex.Resource.LUMBER,jLabel2,robberLabel0,hexButton2,this);
        hex[1]=doubleQHex[1]=new Hex(1,0,false,true,Hex.Resource.CATTLE,jLabel3,robberLabel1,hexButton1,this);
        hex[2]=doubleQHex[2]=new Hex(2,0,false,true,Hex.Resource.ORE,jLabel4,robberLabel2,hexButton3,this);
        hex[3]=new Hex(3,5,false,false,Hex.Resource.WHEAT,robberLabel3,hexButton4,this);
        hex[4]=doubleQHex[3]=new Hex(4,0,false,true,Hex.Resource.CATTLE,jLabel5,robberLabel4,hexButton5,this);
        hex[5]=new Hex(5,4,false,false,Hex.Resource.WHEAT,robberLabel5,hexButton6,this);        
        hex[6]=doubleQHex[4]=new Hex(6,0,false,true,Hex.Resource.COAL,jLabel7,robberLabel6,hexButton7,this);
        hex[7]=new Hex(7,0,false,false,Hex.Resource.DESERT,robberStartLabel,hexButton8,this);
        hex[8]=new Hex(8,0,false,false,Hex.Resource.DESERT,robberLabel7,hexButton9,this);
        hex[9]=new Hex(9,0,false,false,Hex.Resource.DESERT,robberLabel8,hexButton10,this);
        hex[10]=new Hex(10,0,false,false,Hex.Resource.DESERT,robberLabel9,hexButton11,this);
        hex[11]=new Hex(11,0,false,false,Hex.Resource.DESERT,robberLabel10,hexButton12,this);
        hex[12]=new Hex(12,8,false,false,Hex.Resource.COAL,robberLabel11,hexButton13,this);
        hex[13]=doubleQHex[5]=new Hex(13,0,false,true,Hex.Resource.COAL,jLabel8,robberLabel12,hexButton14,this);
        hex[14]=new Hex(14,8,false,false,Hex.Resource.ORE,robberLabel14,hexButton15,this);
        hex[15]=doubleQHex[6]=new Hex(15,0,false,true,Hex.Resource.ORE,jLabel6,robberLabel13,hexButton16,this);
        hex[16]=new Hex(16,4,false,false,Hex.Resource.LUMBER,robberLabel19,hexButton17,this);
        hex[17]=new Hex(17,3,false,false,Hex.Resource.ORE,robberLabel18,hexButton18,this);
        hex[18]=new Hex(18,2,false,false,Hex.Resource.ORE,robberLabel17,hexButton19,this);
        hex[19]=doubleQHex[7]=new Hex(19,0,false,true,Hex.Resource.LUMBER,jLabel9,robberLabel16,hexButton20,this);
        hex[20]=new Hex(20,9,false,false,Hex.Resource.COAL,robberLabel15,hexButton21,this);
        hex[21]=doubleQHex[8]=new Hex(21,0,false,true,Hex.Resource.ORE,jLabel13,robberLabel25,hexButton22,this);
        hex[22]=doubleQHex[9]=new Hex(22,0,false,true,Hex.Resource.ORE,jLabel12,robberLabel24,hexButton23,this);
        hex[23]=new Hex(23,5,false,false,Hex.Resource.COAL,robberLabel23,hexButton24,this);
        hex[24]=doubleQHex[10]=new Hex(24,0,false,true,Hex.Resource.ORE,jLabel11,robberLabel22,hexButton25,this);
        hex[25]=new Hex(25,6,false,false,Hex.Resource.LUMBER,robberLabel21,hexButton26,this);
        hex[26]=doubleQHex[11]=new Hex(26,0,false,true,Hex.Resource.WHEAT,jLabel10,robberLabel20,hexButton27,this);
        hex[27]=new Hex(27,11,false,false,Hex.Resource.WHEAT,robberLabel31,hexButton28,this);
        hex[28]=doubleQHex[12]=new Hex(28,0,false,true,Hex.Resource.CATTLE,jLabel14,robberLabel30,hexButton29,this);
        hex[29]=new Hex(29,3,false,false,Hex.Resource.CATTLE,robberLabel29,hexButton30,this);
        hex[30]=new Hex(30,10,false,false,Hex.Resource.CATTLE,robberLabel28,hexButton31,this);
        hex[31]=new Hex(31,6,false,false,Hex.Resource.CATTLE,robberLabel27,hexButton32,this);
        hex[32]=new Hex(32,0,false,false,Hex.Resource.DESERT,robberLabel26,hexButton33,this);
        hex[33]=new Hex(33,11,false,false,Hex.Resource.CATTLE,robberLabel32,hexButton34,this);
        hex[34]=doubleQHex[13]=new Hex(34,0,false,true,Hex.Resource.WHEAT,jLabel15,robberLabel33,hexButton35,this);
        hex[35]=singleQHex[0]=new Hex(35,0,true,false,Hex.Resource.WHEAT,jLabel16,robberLabel34,hexButton36,this);
        hex[36]=singleQHex[1]=new Hex(36,0,true,false,Hex.Resource.WHEAT,jLabel17,robberLabel35,hexButton37,this);
        hex[37]=new Hex(37,6,false,false,Hex.Resource.WHEAT,robberLabel36,hexButton38,this);
        hex[38]=singleQHex[2]=new Hex(38,0,true,false,Hex.Resource.WHEAT,jLabel18,robberLabel37,hexButton39,this);
        hex[39]=new Hex(39,3,false,false,Hex.Resource.WHEAT,robberLabel43,hexButton40,this);
        hex[40]=new Hex(40,10,false,false,Hex.Resource.WHEAT,robberLabel42,hexButton41,this);
        hex[41]=new Hex(41,6,false,false,Hex.Resource.COAL,robberLabel41,hexButton42,this);
        hex[42]=new Hex(42,3,false,false,Hex.Resource.WHEAT,robberLabel40,hexButton43,this);
        hex[43]=singleQHex[3]=new Hex(43,0,true,false,Hex.Resource.LUMBER,jLabel19,robberLabel39,hexButton44,this);
        hex[44]=new Hex(44,5,false,false,Hex.Resource.CATTLE,robberLabel38,hexButton45,this);
        hex[45]=new Hex(45,12,false,false,Hex.Resource.LUMBER,robberLabel44,hexButton46,this);
        hex[46]=new Hex(46,8,false,false,Hex.Resource.LUMBER,robberLabel45,hexButton47,this);
        hex[47]=singleQHex[4]=new Hex(47,0,true,false,Hex.Resource.COAL,jLabel20,robberLabel46,hexButton48,this);
        hex[48]=new Hex(48,3,false,false,Hex.Resource.CATTLE,robberLabel47,hexButton49,this);
        hex[49]=singleQHex[5]=new Hex(49,0,true,false,Hex.Resource.WHEAT,jLabel21,robberLabel49,hexButton50,this);
        hex[50]=singleQHex[6]=new Hex(50,0,true,false,Hex.Resource.CATTLE,jLabel22,robberLabel53,hexButton51,this);
        hex[51]=new Hex(51,4,false,false,Hex.Resource.WHEAT,robberLabel52,hexButton52,this);
        hex[52]=new Hex(52,5,false,false,Hex.Resource.WHEAT,robberLabel51,hexButton53,this);
        hex[53]=singleQHex[7]=new Hex(53,0,true,false,Hex.Resource.LUMBER,jLabel23,robberLabel50,hexButton54,this);
        hex[54]=new Hex(54,4,false,false,Hex.Resource.LUMBER,robberLabel48,hexButton55,this);
        hex[55]=new Hex(55,5,false,false,Hex.Resource.LUMBER,robberLabel54,hexButton56,this);
        hex[56]=new Hex(56,6,false,false,Hex.Resource.ORE,robberLabel55,hexButton57,this);
        hex[57]=singleQHex[8]=new Hex(57,0,true,false,Hex.Resource.COAL,jLabel24,robberLabel56,hexButton58,this);
        hex[58]=new Hex(58,8,false,false,Hex.Resource.CATTLE,robberLabel57,hexButton59,this);
        hex[59]=singleQHex[9]=new Hex(59,0,true,false,Hex.Resource.LUMBER,jLabel26,robberLabel62,hexButton60,this);
        hex[60]=new Hex(60,5,false,false,Hex.Resource.COAL,robberLabel61,hexButton61,this);
        hex[61]=new Hex(61,4,false,false,Hex.Resource.ORE,robberLabel60,hexButton62,this);
        hex[62]=singleQHex[10]=new Hex(62,0,true,false,Hex.Resource.CATTLE,jLabel25,robberLabel59,hexButton63,this);
        hex[63]=new Hex(63,12,false,false,Hex.Resource.CATTLE,robberLabel58,hexButton64,this);
        hex[64]=new Hex(64,11,false,false,Hex.Resource.CATTLE,robberLabel63,hexButton65,this);
        hex[65]=new Hex(65,8,false,false,Hex.Resource.WHEAT,robberLabel64,hexButton66,this);
        hex[66]=singleQHex[11]=new Hex(66,0,true,false,Hex.Resource.CATTLE,jLabel27,robberLabel66,hexButton67,this);        
        hex[67]=new Hex(67,3,false,false,Hex.Resource.CATTLE,robberLabel65,hexButton68,this);
        hex[68]=new Hex(68,9,false,false,Hex.Resource.LUMBER,robberLabel67,hexButton69,this);
        hex[69]=new Hex(69,12,false,false,Hex.Resource.LUMBER,robberLabel68,hexButton70,this);
        hex[70]=new Hex(70,2,false,false,Hex.Resource.CATTLE,robberLabel69,hexButton71,this);
        robberHex=hex[7].setRobber(true);
        //</editor-fold>
        
        //initialize rail buttons
        //<editor-fold>
        railButton[0]=railButton0;
        railButton[1]=railButton1;
        railButton[2]=railButton2;
        railButton[3]=railButton3;
        railButton[4]=railButton4;
        railButton[5]=railButton5;
        railButton[6]=railButton6;
        railButton[7]=railButton7;
        railButton[8]=railButton8;
        railButton[9]=railButton9;
        railButton[10]=railButton10;
        railButton[11]=railButton11;
        railButton[12]=railButton12;
        railButton[13]=railButton13;
        railButton[14]=railButton14;
        railButton[15]=railButton15;
        railButton[16]=railButton16;
        railButton[17]=railButton17;
        railButton[18]=railButton18;
        railButton[19]=railButton19;
        railButton[20]=railButton20;
        railButton[21]=railButton21;
        railButton[22]=railButton22;
        railButton[23]=railButton23;
        railButton[24]=railButton24;
        railButton[25]=railButton25;
        railButton[26]=railButton26;
        railButton[27]=railButton27;
        railButton[28]=railButton28;
        railButton[29]=railButton29;
        railButton[30]=railButton30;
        railButton[31]=railButton31;
        railButton[32]=railButton32;
        railButton[33]=railButton33;
        railButton[34]=railButton34;
        railButton[35]=railButton35;
        railButton[36]=railButton36;
        railButton[37]=railButton37;
        railButton[38]=railButton38;
        railButton[39]=railButton39;
        railButton[40]=railButton40;
        railButton[41]=railButton41;
        railButton[42]=railButton42;
        railButton[43]=railButton43;
        railButton[44]=railButton44;
        railButton[45]=railButton45;
        railButton[46]=railButton46;
        railButton[47]=railButton47;
        railButton[48]=railButton48;
        railButton[49]=railButton49;
        railButton[50]=railButton50;
        railButton[51]=railButton51;
        railButton[52]=railButton52;
        railButton[53]=railButton53;
        railButton[54]=railButton54;
        railButton[55]=railButton55;
        railButton[56]=railButton56;
        railButton[57]=railButton57;
        railButton[58]=railButton58;
        railButton[59]=railButton59;
        railButton[60]=railButton60;
        railButton[61]=railButton61;
        railButton[62]=railButton62;
        railButton[63]=railButton63;
        railButton[64]=railButton64;
        railButton[65]=railButton65;
        railButton[66]=railButton66;
        railButton[67]=railButton67;
        railButton[68]=railButton68;
        railButton[69]=railButton69;
        railButton[70]=railButton70;
        railButton[71]=railButton71;
        railButton[72]=railButton72;
        railButton[73]=railButton73;
        railButton[74]=railButton74;
        railButton[75]=railButton75;
        railButton[76]=railButton76;
        railButton[77]=railButton77;
        railButton[78]=railButton78;
        railButton[79]=railButton79;
        railButton[80]=railButton80;
        railButton[81]=railButton81;
        railButton[82]=railButton82;
        railButton[83]=railButton83;
        railButton[84]=railButton84;
        railButton[85]=railButton85;
        railButton[86]=railButton86;
        railButton[87]=railButton87;
        railButton[88]=railButton88;
        railButton[89]=railButton89;
        railButton[90]=railButton90;
        railButton[91]=railButton91;
        railButton[92]=railButton92;
        railButton[93]=railButton93;
        railButton[94]=railButton94;
        railButton[95]=railButton95;
        railButton[96]=railButton96;
        railButton[97]=railButton97;
        railButton[98]=railButton98;
        railButton[99]=railButton99;
        railButton[100]=railButton100;
        railButton[101]=railButton101;
        railButton[102]=railButton102;
        railButton[103]=railButton103;
        railButton[104]=railButton104;
        railButton[105]=railButton105;
        railButton[106]=railButton106;
        railButton[107]=railButton107;
        railButton[108]=railButton108;
        railButton[109]=railButton109;
        railButton[110]=railButton110;
        railButton[111]=railButton111;
        railButton[112]=railButton112;
        railButton[113]=railButton113;
        railButton[114]=railButton114;
        railButton[115]=railButton115;
        railButton[116]=railButton116;
        railButton[117]=railButton117;
        railButton[118]=railButton118;
        railButton[119]=railButton119;
        railButton[120]=railButton120;
        railButton[121]=railButton121;
        railButton[122]=railButton122;
        railButton[123]=railButton123;
        railButton[124]=railButton124;
        railButton[125]=railButton125;
        railButton[126]=railButton126;
        railButton[127]=railButton127;
        railButton[128]=railButton128;
        railButton[129]=railButton129;
        railButton[130]=railButton130;
        railButton[131]=railButton131;
        railButton[132]=railButton132;
        railButton[133]=railButton133;
        railButton[134]=railButton134;
        railButton[135]=railButton135;
        railButton[136]=railButton136;
        railButton[137]=railButton137;
        railButton[138]=railButton138;
        railButton[139]=railButton139;
        railButton[140]=railButton140;
        railButton[141]=railButton141;
        railButton[142]=railButton142;
        railButton[143]=railButton143;
        railButton[144]=railButton144;
        railButton[145]=railButton145;
        railButton[146]=railButton146;
        railButton[147]=railButton147;
        railButton[148]=railButton148;
        railButton[149]=railButton149;
        railButton[150]=railButton150;
        railButton[151]=railButton151;
        railButton[152]=railButton152;
        railButton[153]=railButton153;
        railButton[154]=railButton154;
        railButton[155]=railButton155;
        railButton[156]=railButton156;
        railButton[157]=railButton157;
        railButton[158]=railButton158;
        railButton[159]=railButton159;
        railButton[160]=railButton160;
        railButton[161]=railButton161;
        railButton[162]=railButton162;
        railButton[163]=railButton163;
        railButton[164]=railButton164;
        railButton[165]=railButton165;
        railButton[166]=railButton166;
        railButton[167]=railButton167;
        railButton[168]=railButton168;
        railButton[169]=railButton169;
        railButton[170]=railButton170;
        railButton[171]=railButton171;
        railButton[172]=railButton172;
        railButton[173]=railButton173;
        railButton[174]=railButton174;
        railButton[175]=railButton175;
        railButton[176]=railButton176;
        railButton[177]=railButton177;
        railButton[178]=railButton178;
        railButton[179]=railButton179;
        railButton[180]=railButton180;
        railButton[181]=railButton181;
        railButton[182]=railButton182;
        railButton[183]=railButton183;
        railButton[184]=railButton184;
        railButton[185]=railButton185;
        railButton[186]=railButton186;
        railButton[187]=railButton187;
        railButton[188]=railButton188;
        railButton[189]=railButton189;
        railButton[190]=railButton190;
        railButton[191]=railButton191;
        railButton[192]=railButton192;
        railButton[193]=railButton193;
        railButton[194]=railButton194;
        railButton[195]=railButton195;
        railButton[196]=railButton196;
        railButton[197]=railButton197;
        railButton[198]=railButton198;
        railButton[199]=railButton199;
        railButton[200]=railButton200;
        railButton[201]=railButton201;
        railButton[202]=railButton202;
        railButton[203]=railButton203;
        railButton[204]=railButton204;
        railButton[205]=railButton205;
        railButton[206]=railButton206;
        railButton[207]=railButton207;
        railButton[208]=railButton208;
        railButton[209]=railButton209;
        railButton[210]=railButton210;
        railButton[211]=railButton211;
        railButton[212]=railButton212;
        railButton[213]=railButton213;
        railButton[214]=railButton214;
        railButton[215]=railButton215;
        //</editor-fold>
        
        //initialize rail objects
        //<editor-fold>
        Rail.Orientation LL = Rail.Orientation.LEANING_LEFT;
        Rail.Orientation LR = Rail.Orientation.LEANING_RIGHT;
        Rail.Orientation H = Rail.Orientation.HORIZONTAL;
        rail = new Rail[218];
        rail[0]=new Rail(railButton1,LL, trainSettlerPanel,this);
        rail[1]=new Rail(railButton74,LR, trainSettlerPanel,this);
        rail[2]=new Rail(railButton146,H, trainSettlerPanel,this);
        rail[0].setDownLeftRail(rail[1]);
        rail[0].setDownRightRail(rail[2]);
        //rail 0 done
        rail[3]=new Rail(railButton2,LL, trainSettlerPanel,this);
        rail[3].setUpLeftRail(rail[1]);
        rail[1].setUpRightRail(rail[2]);
        rail[1].setDownRightRail(rail[3]);
        rail[1].setUpLeftRail(rail[0]);
        //rail 1 done        
        rail[4]=new Rail(railButton0,LR, trainSettlerPanel,this);
        rail[5]=new Rail(railButton5,LL, trainSettlerPanel,this);
        rail[2].setUpLeftRail(rail[0]);
        rail[2].setDownLeftRail(rail[1]);
        rail[2].setUpRightRail(rail[4]);
        rail[2].setDownRightRail(rail[5]);
        //rail 2 done
        rail[6]=new Rail(railButton75,LR, trainSettlerPanel,this);
        rail[7]=new Rail(railButton147,H, trainSettlerPanel,this);
        rail[3].setUpRightRail(rail[1]);
        rail[3].setDownRightRail(rail[7]);
        rail[3].setDownLeftRail(rail[6]);
        //rail 3 done
        rail[8]=new Rail(railButton14,LL, trainSettlerPanel,this);
        rail[9]=new Rail(railButton150,H, trainSettlerPanel,this);
        rail[4].setDownLeftRail(rail[2]);
        rail[4].setUpLeftRail(rail[8]);
        rail[4].setDownRightRail(rail[5]);
        rail[4].setUpRightRail(rail[9]);
        //rail 4 done
        rail[10]=new Rail(railButton151,H, trainSettlerPanel,this);
        rail[11]=new Rail(railButton77,LR, trainSettlerPanel,this);
        rail[5].setUpLeftRail(rail[2]);
        rail[5].setUpRightRail(rail[4]);
        rail[5].setDownLeftRail(rail[11]);
        rail[5].setDownRightRail(rail[10]);
        //rail 5 done
        rail[12]=new Rail(railButton3,LL, trainSettlerPanel,this);
        rail[6].setUpLeftRail(rail[3]);
        rail[6].setUpRightRail(rail[7]);
        rail[6].setDownRightRail(rail[12]);
        //rail 6 done
        rail[13]=new Rail(railButton6,LL, trainSettlerPanel,this);
        rail[7].setUpLeftRail(rail[3]);
        rail[7].setUpRightRail(rail[11]);
        rail[7].setDownLeftRail(rail[6]);
        rail[7].setDownRightRail(rail[13]);
        //rail 7 done
        rail[8].setDownLeftRail(rail[4]);
        rail[8].setDownRightRail(rail[9]);
        //rail 8 done
        rail[14]=new Rail(railButton81,LR, trainSettlerPanel,this);
        rail[15]=new Rail(railButton9,LL, trainSettlerPanel,this);
        rail[9].setUpLeftRail(rail[8]);
        rail[9].setUpRightRail(rail[14]);
        rail[9].setDownLeftRail(rail[4]);
        rail[9].setDownRightRail(rail[15]);
        //rail 9 done        
        rail[16]=new Rail(railButton82,LR, trainSettlerPanel,this);
        rail[17]=new Rail(railButton10,LL, trainSettlerPanel,this);
        rail[10].setUpLeftRail(rail[5]);
        rail[10].setUpRightRail(rail[16]);
        rail[10].setDownLeftRail(rail[11]);
        rail[10].setDownRightRail(rail[17]);
        //rail 10 done
        rail[11].setUpLeftRail(rail[5]);
        rail[11].setUpRightRail(rail[10]);
        rail[11].setDownLeftRail(rail[7]);
        rail[11].setDownRightRail(rail[13]);
        //rail 11 done
        //RB3
        rail[18]=new Rail(railButton76,LR, trainSettlerPanel,this);
        rail[19]=new Rail(railButton148,H, trainSettlerPanel,this);
        rail[12].setUpRightRail(rail[6]);
        rail[12].setDownLeftRail(rail[18]);
        rail[12].setDownRightRail(rail[19]);
        //rail 12 done
        //RB6
        rail[20]=new Rail(railButton152,H, trainSettlerPanel,this);
        rail[21]=new Rail(railButton78,LR, trainSettlerPanel,this);
        rail[13].setUpLeftRail(rail[7]);
        rail[13].setUpRightRail(rail[11]);
        rail[13].setDownLeftRail(rail[21]);
        rail[13].setDownRightRail(rail[20]);
        //rail 13 done
        //RB81
        rail[14].setDownLeftRail(rail[9]);
        rail[14].setDownRightRail(rail[15]);
        //rail 14 done
        //RB9
        rail[22]=new Rail(railButton155,H, trainSettlerPanel,this);
        rail[23]=new Rail(railButton82,LR, trainSettlerPanel,this);
        rail[15].setUpLeftRail(rail[9]);
        rail[15].setUpRightRail(rail[14]);
        rail[15].setDownLeftRail(rail[23]);
        rail[15].setDownRightRail(rail[22]);
        //rail 15 done
        //RB82
        rail[24]=new Rail(railButton10,LL, trainSettlerPanel,this);
        rail[16].setUpLeftRail(rail[15]);
        rail[16].setUpRightRail(rail[22]);
        rail[16].setDownLeftRail(rail[10]);
        rail[16].setDownRightRail(rail[24]);
        //rail 16 done
        //RB10
        rail[25]=new Rail(railButton156,H, trainSettlerPanel,this);
        rail[26]=new Rail(railButton83,LR, trainSettlerPanel,this);
        rail[17].setUpLeftRail(rail[10]);
        rail[17].setUpRightRail(rail[16]);
        rail[17].setDownLeftRail(rail[26]);
        rail[17].setDownRightRail(rail[25]);
        //rail 17 done
        //RB76
        rail[27]=new Rail(railButton4,LL, trainSettlerPanel,this);
        rail[18].setUpLeftRail(rail[12]);
        rail[18].setUpRightRail(rail[19]);
        rail[18].setDownRightRail(rail[27]);
        //rail 18 done
        //RB148        
        rail[28]=new Rail(railButton7,LL, trainSettlerPanel,this);
        rail[19].setUpLeftRail(rail[12]);
        rail[19].setUpRightRail(rail[21]);
        rail[19].setDownLeftRail(rail[18]);
        rail[19].setDownRightRail(rail[28]);
        //rail 19 done
        //RB152
        rail[29]=new Rail(railButton11,LL, trainSettlerPanel,this);
        rail[20].setUpLeftRail(rail[13]);
        rail[20].setUpRightRail(rail[26]);
        rail[20].setDownLeftRail(rail[21]);
        rail[20].setDownRightRail(rail[29]);
        //rail 20 done
        //RB78        
        rail[21].setUpLeftRail(rail[13]);
        rail[21].setUpRightRail(rail[20]);
        rail[21].setDownLeftRail(rail[19]);
        rail[21].setDownRightRail(rail[28]);
        //rail 21 done
        //RB155
        rail[30]=new Rail(railButton92,LR, trainSettlerPanel,this);
        rail[31]=new Rail(railButton16,LL, trainSettlerPanel,this);
        rail[22].setUpLeftRail(rail[15]);
        rail[22].setUpRightRail(rail[30]);
        rail[22].setDownLeftRail(rail[16]);
        rail[22].setDownRightRail(rail[31]);
        //rail 22 done
        //RB82
        rail[23].setUpLeftRail(rail[15]);
        rail[23].setUpRightRail(rail[22]);
        rail[23].setDownLeftRail(rail[10]);
        rail[23].setDownRightRail(rail[24]);
        //rail 23 done
        //RB10
        rail[24].setUpLeftRail(rail[10]);
        rail[24].setUpRightRail(rail[23]);
        rail[24].setDownLeftRail(rail[26]);
        rail[24].setDownRightRail(rail[25]);
        //rail 24 done
        //RB156
        rail[32]=new Rail(railButton91,LR, trainSettlerPanel,this);
        rail[33]=new Rail(railButton17,LL, trainSettlerPanel,this);
        rail[25].setUpLeftRail(rail[24]);
        rail[25].setUpRightRail(rail[32]);
        rail[25].setDownLeftRail(rail[26]);
        rail[25].setDownRightRail(rail[33]);
        //rail 25 done
        //RB83
        rail[26].setUpLeftRail(rail[24]);
        rail[26].setUpRightRail(rail[25]);
        rail[26].setDownLeftRail(rail[20]);
        rail[26].setDownRightRail(rail[29]);
        //rail 26 done
        //RB4
        rail[34]=new Rail(railButton149,H, trainSettlerPanel,this);
        rail[27].setUpRightRail(rail[18]);
        rail[27].setDownRightRail(rail[34]);
        //rail 27 done
        //RB7
        rail[35]=new Rail(railButton79,LR, trainSettlerPanel,this);
        rail[36]=new Rail(railButton153,H, trainSettlerPanel,this);
        rail[28].setUpLeftRail(rail[19]);
        rail[28].setUpRightRail(rail[21]);
        rail[28].setDownLeftRail(rail[35]);
        rail[28].setDownRightRail(rail[36]);
        //rail 28 done
        //RB11
        rail[37]=new Rail(railButton84,LR, trainSettlerPanel,this);
        rail[38]=new Rail(railButton157,H, trainSettlerPanel,this);
        rail[29].setUpLeftRail(rail[20]);
        rail[29].setUpRightRail(rail[26]);
        rail[29].setDownLeftRail(rail[37]);
        rail[29].setDownRightRail(rail[38]);
        //rail 29 done
        //RB12
        rail[39]=new Rail(railButton85,LR, trainSettlerPanel,this);
        rail[40]=new Rail(railButton158,H, trainSettlerPanel,this);
        rail[30].setDownLeftRail(rail[22]);
        rail[30].setDownRightRail(rail[31]);
        //rail 30 done
        //RB16
        rail[41]=new Rail(railButton161,H, trainSettlerPanel,this);
        rail[31].setUpLeftRail(rail[22]);
        rail[31].setUpRightRail(rail[30]);
        rail[31].setDownLeftRail(rail[32]);
        rail[31].setDownRightRail(rail[41]);
        //rail 31 done
        //RB91
        rail[32].setUpLeftRail(rail[31]);
        rail[32].setUpRightRail(rail[41]);
        rail[32].setDownLeftRail(rail[25]);
        rail[32].setDownRightRail(rail[33]);
        //rail 32 done
        //RB17
        rail[42]=new Rail(railButton90,LR, trainSettlerPanel,this);
        rail[43]=new Rail(railButton162,H, trainSettlerPanel,this);
        rail[33].setUpLeftRail(rail[25]);
        rail[33].setUpRightRail(rail[32]);
        rail[33].setDownLeftRail(rail[42]);
        rail[33].setDownRightRail(rail[43]);
        //rail 33 done
        //RB149
        rail[44]=new Rail(railButton8,LL, trainSettlerPanel,this);
        rail[34].setUpLeftRail(rail[27]);
        rail[34].setUpRightRail(rail[35]);
        rail[34].setDownRightRail(rail[44]);
        //rail 34 done
        //RB79
        rail[35].setUpLeftRail(rail[28]);
        rail[35].setUpRightRail(rail[36]);
        rail[35].setDownLeftRail(rail[34]);
        rail[35].setDownRightRail(rail[44]);
        //rail 35 done
        //RB153
        rail[45]=new Rail(railButton12,LL, trainSettlerPanel,this);
        rail[36].setUpLeftRail(rail[28]);
        rail[36].setUpRightRail(rail[37]);
        rail[36].setDownLeftRail(rail[35]);
        rail[36].setDownRightRail(rail[45]);
        //rail 36 done
        //RB84
        rail[37].setUpLeftRail(rail[29]);
        rail[37].setUpRightRail(rail[38]);
        rail[37].setDownLeftRail(rail[36]);
        rail[37].setDownRightRail(rail[45]);
        //rail 37 done
        //RB157
        rail[46]=new Rail(railButton18,LL, trainSettlerPanel,this);
        rail[38].setUpLeftRail(rail[29]);
        rail[38].setUpRightRail(rail[42]);
        rail[38].setDownLeftRail(rail[37]);
        rail[38].setDownRightRail(rail[46]);
        //rail 38 done
        //RB85
        rail[47]=new Rail(railButton154,H, trainSettlerPanel,this);
        rail[48]=new Rail(railButton13,LL, trainSettlerPanel,this);
        rail[39].setUpLeftRail(rail[45]);
        rail[39].setUpRightRail(rail[40]);
        rail[39].setDownLeftRail(rail[47]);
        rail[39].setDownRightRail(rail[48]);
        //rail 39 done
        //RB158
        rail[49]=new Rail(railButton89,LR, trainSettlerPanel,this);
        rail[50]=new Rail(railButton19,LL, trainSettlerPanel,this);
        rail[40].setUpLeftRail(rail[45]);
        rail[40].setUpRightRail(rail[49]);
        rail[40].setDownLeftRail(rail[39]);
        rail[40].setDownRightRail(rail[50]);
        //rail 40 done
        //RB161
        rail[51]=new Rail(railButton94,LR, trainSettlerPanel,this);
        rail[52]=new Rail(railButton25,LL, trainSettlerPanel,this);
        rail[41].setUpLeftRail(rail[31]);
        rail[41].setUpRightRail(rail[51]);
        rail[41].setDownLeftRail(rail[32]);
        rail[41].setDownRightRail(rail[52]);
        //rail 41 done
        //RB90
        rail[42].setUpLeftRail(rail[33]);
        rail[42].setUpRightRail(rail[43]);
        rail[42].setDownLeftRail(rail[38]);
        rail[42].setDownRightRail(rail[46]);
        //rail 42 done
        //RB162
        rail[53]=new Rail(railButton95,LR, trainSettlerPanel,this);
        rail[54]=new Rail(railButton24,LL, trainSettlerPanel,this);
        rail[43].setUpLeftRail(rail[33]);
        rail[43].setUpRightRail(rail[53]);
        rail[43].setDownLeftRail(rail[42]);
        rail[43].setDownRightRail(rail[54]);
        //rail 43 done
        //RB8
        rail[55]=new Rail(railButton80,LR, trainSettlerPanel,this);
        rail[44].setUpLeftRail(rail[34]);
        rail[44].setUpRightRail(rail[35]);
        rail[44].setDownLeftRail(rail[55]);
        rail[44].setDownRightRail(rail[47]);
//rail 44 done
//RB12
        rail[45].setUpLeftRail(rail[36]);
        rail[45].setUpRightRail(rail[37]);
        rail[45].setDownLeftRail(rail[39]);
        rail[45].setDownRightRail(rail[40]);
//rail 45 done
//RB18
	rail[56]=new Rail(railButton163,H, trainSettlerPanel,this);
        rail[46].setUpLeftRail(rail[38]);
        rail[46].setUpRightRail(rail[42]);
        rail[46].setDownLeftRail(rail[49]);
        rail[46].setDownRightRail(rail[56]);
//rail 46 done
//RB154
        rail[47].setUpLeftRail(rail[44]);
        rail[47].setUpRightRail(rail[39]);
        rail[47].setDownLeftRail(rail[55]);
        rail[47].setDownRightRail(rail[48]);
//rail 47 done
//RB13
	rail[57]=new Rail(railButton86,LR, trainSettlerPanel,this);
	rail[58]=new Rail(railButton159,H, trainSettlerPanel,this);
        rail[48].setUpLeftRail(rail[47]);
        rail[48].setUpRightRail(rail[39]);
        rail[48].setDownLeftRail(rail[57]);
        rail[48].setDownRightRail(rail[58]);
//rail 48 done
//RB89
        rail[49].setUpLeftRail(rail[46]);
        rail[49].setUpRightRail(rail[56]);
        rail[49].setDownLeftRail(rail[40]);
        rail[49].setDownRightRail(rail[50]);
//rail 49 done
//RB19
	rail[59]=new Rail(railButton88,LR, trainSettlerPanel,this);
	rail[60]=new Rail(railButton164,H, trainSettlerPanel,this);
        rail[50].setUpLeftRail(rail[40]);
        rail[50].setUpRightRail(rail[49]);
        rail[50].setDownLeftRail(rail[59]);
        rail[50].setDownRightRail(rail[60]);
//rail 50 done
//RB94
	rail[61]=new Rail(railButton26,LL, trainSettlerPanel,this);
	rail[62]=new Rail(railButton166,H, trainSettlerPanel,this);
        rail[51].setUpLeftRail(rail[61]);
        rail[51].setUpRightRail(rail[62]);
        rail[51].setDownLeftRail(rail[41]);
        rail[51].setDownRightRail(rail[52]);
//rail 51 done
//RB25
	rail[63]=new Rail(railButton167,H, trainSettlerPanel,this);
        rail[52].setUpLeftRail(rail[41]);
        rail[52].setUpRightRail(rail[51]);
        rail[52].setDownLeftRail(rail[53]);
        rail[52].setDownRightRail(rail[63]);
//rail 52 done
//RB95
        rail[53].setUpLeftRail(rail[52]);
        rail[53].setUpRightRail(rail[63]);
        rail[53].setDownLeftRail(rail[43]);
        rail[53].setDownRightRail(rail[54]);
//rail 53 done
//RB24
	rail[64]=new Rail(railButton96,LR, trainSettlerPanel,this);
	rail[65]=new Rail(railButton168,H, trainSettlerPanel,this);
        rail[54].setUpLeftRail(rail[43]);
        rail[54].setUpRightRail(rail[53]);
        rail[54].setDownLeftRail(rail[64]);
        rail[54].setDownRightRail(rail[65]);
//rail 54 done
//RB80
        rail[55].setUpLeftRail(rail[44]);
        rail[55].setUpRightRail(rail[47]);
//rail 55 done
//RB163
	rail[66]=new Rail(railButton23,LL, trainSettlerPanel,this);
        rail[56].setUpLeftRail(rail[46]);
        rail[56].setUpRightRail(rail[64]);
        rail[56].setDownLeftRail(rail[49]);
        rail[56].setDownRightRail(rail[66]);
//rail 56 done
//RB86
        rail[57].setUpLeftRail(rail[48]);
        rail[57].setUpRightRail(rail[58]);
//rail 57 done
//RB159
	rail[67]=new Rail(railButton20,LL, trainSettlerPanel,this);
        rail[58].setUpLeftRail(rail[48]);
        rail[58].setUpRightRail(rail[59]);
        rail[58].setDownLeftRail(rail[57]);
        rail[58].setDownRightRail(rail[67]);
//rail 58 done
//RB88
        rail[59].setUpLeftRail(rail[50]);
        rail[59].setUpRightRail(rail[60]);
        rail[59].setDownLeftRail(rail[58]);
        rail[59].setDownRightRail(rail[67]);
//rail 59 done
//RB164
	rail[68]=new Rail(railButton97,LR, trainSettlerPanel,this);
	rail[69]=new Rail(railButton22,LL, trainSettlerPanel,this);
        rail[60].setUpLeftRail(rail[50]);
        rail[60].setUpRightRail(rail[68]);
        rail[60].setDownLeftRail(rail[59]);
        rail[60].setDownRightRail(rail[69]);
//rail 60 done
//RB26
	rail[70]=new Rail(railButton160,H, trainSettlerPanel,this);
	rail[71]=new Rail(railButton93,LR, trainSettlerPanel,this);
        rail[61].setUpLeftRail(rail[70]);
        rail[61].setUpRightRail(rail[71]);
        rail[61].setDownLeftRail(rail[51]);
        rail[61].setDownRightRail(rail[62]);
//rail 61 done
//RB166
	rail[72]=new Rail(railButton103,LR, trainSettlerPanel,this);
	rail[73]=new Rail(railButton28,LL, trainSettlerPanel,this);
        rail[62].setUpLeftRail(rail[61]);
        rail[62].setUpRightRail(rail[72]);
        rail[62].setDownLeftRail(rail[51]);
        rail[62].setDownRightRail(rail[73]);
//rail 62 done
//RB167
	rail[74]=new Rail(railButton102,LR, trainSettlerPanel,this);
	rail[75]=new Rail(railButton29,LL, trainSettlerPanel,this);
        rail[63].setUpLeftRail(rail[52]);
        rail[63].setUpRightRail(rail[74]);
        rail[63].setDownLeftRail(rail[53]);
        rail[63].setDownRightRail(rail[75]);
//rail 63 done
//RB96
        rail[64].setUpLeftRail(rail[54]);
        rail[64].setUpRightRail(rail[65]);
        rail[64].setDownLeftRail(rail[56]);
        rail[64].setDownRightRail(rail[66]);
//rail 64 done
//RB168
	rail[76]=new Rail(railButton100,LR, trainSettlerPanel,this);
	rail[77]=new Rail(railButton169,H, trainSettlerPanel,this);
	rail[78]=new Rail(railButton31,LL, trainSettlerPanel,this);
        rail[65].setDownLeftRail(rail[64]);
        rail[65].setUpLeftRail(rail[54]);
//rail 65 done
//RB23
        rail[66].setUpLeftRail(rail[56]);
        rail[66].setUpRightRail(rail[64]);
        rail[66].setDownLeftRail(rail[68]);
        rail[66].setDownRightRail(rail[77]);
//rail 66 done
//RB20
	rail[79]=new Rail(railButton87,LR, trainSettlerPanel,this);
	rail[80]=new Rail(railButton165,H, trainSettlerPanel,this);
        rail[67].setUpLeftRail(rail[58]);
        rail[67].setUpRightRail(rail[59]);
        rail[67].setDownLeftRail(rail[79]);
        rail[67].setDownRightRail(rail[80]);
//rail 67 done
//RB97
        rail[68].setUpLeftRail(rail[66]);
        rail[68].setUpRightRail(rail[77]);
        rail[68].setDownLeftRail(rail[60]);
        rail[68].setDownRightRail(rail[69]);
//rail 68 done
//RB22
	rail[81]=new Rail(railButton98,LR, trainSettlerPanel,this);
	rail[82]=new Rail(railButton170,H, trainSettlerPanel,this);
        rail[69].setUpLeftRail(rail[60]);
        rail[69].setUpRightRail(rail[68]);
        rail[69].setDownLeftRail(rail[81]);
        rail[69].setDownRightRail(rail[82]);
//rail 69 done
//RB160
	rail[83]=new Rail(railButton15,LL, trainSettlerPanel,this);
        rail[30].setUpLeftRail(rail[83]);
        rail[30].setUpRightRail(rail[70]);
        rail[70].setUpLeftRail(rail[83]);
        rail[70].setUpRightRail(rail[71]);
        rail[70].setDownLeftRail(rail[30]);
        rail[70].setDownRightRail(rail[61]);
//rail 70 done
//RB93
        rail[71].setDownLeftRail(rail[70]);
        rail[71].setDownRightRail(rail[61]);
//rail 71 done
//RB103
	rail[84]=new Rail(railButton27,LL, trainSettlerPanel,this);
	rail[85]=new Rail(railButton215,H, trainSettlerPanel,this);
        rail[72].setUpLeftRail(rail[84]);
        rail[72].setUpRightRail(rail[85]);
        rail[72].setDownLeftRail(rail[62]);
        rail[72].setDownRightRail(rail[73]);
//rail 72 done
//RB28
	rail[86]=new Rail(railButton214,H, trainSettlerPanel,this);
        rail[73].setUpLeftRail(rail[62]);
        rail[73].setUpRightRail(rail[72]);
        rail[73].setDownLeftRail(rail[74]);
        rail[73].setDownRightRail(rail[86]);
//rail 73 done
//RB102
        rail[74].setUpLeftRail(rail[73]);
        rail[74].setUpRightRail(rail[86]);
        rail[74].setDownLeftRail(rail[63]);
        rail[74].setDownRightRail(rail[75]);
//rail 74 done
//RB29
	rail[87]=new Rail(railButton101,LR, trainSettlerPanel,this);
	rail[88]=new Rail(railButton213,H, trainSettlerPanel,this);
        rail[75].setUpLeftRail(rail[63]);
        rail[75].setUpRightRail(rail[74]);
        rail[75].setDownLeftRail(rail[87]);
        rail[75].setDownRightRail(rail[88]);
//rail 75 done
//RB100
	rail[89]=new Rail(railButton30,LL, trainSettlerPanel,this);
	rail[90]=new Rail(railButton212,H, trainSettlerPanel,this);
        rail[76].setUpLeftRail(rail[89]);
        rail[65].setUpRightRail(rail[87]);
        rail[65].setDownRightRail(rail[89]);
        rail[76].setUpRightRail(rail[90]);
        rail[76].setDownLeftRail(rail[77]);
        rail[76].setDownRightRail(rail[78]);
//rail 76 done
//RB169
        rail[77].setUpLeftRail(rail[66]);
        rail[77].setUpRightRail(rail[76]);
        rail[77].setDownLeftRail(rail[68]);
        rail[77].setDownRightRail(rail[78]);
//rail 77 done
//RB31
	rail[91]=new Rail(railButton99,LR, trainSettlerPanel,this);
	rail[92]=new Rail(railButton178,H, trainSettlerPanel,this);
        rail[78].setUpLeftRail(rail[77]);
        rail[78].setUpRightRail(rail[76]);
        rail[78].setDownLeftRail(rail[91]);
        rail[78].setDownRightRail(rail[92]);
//rail 78 done
//RB87
        rail[79].setUpLeftRail(rail[67]);
        rail[79].setUpRightRail(rail[80]);
//rail 79 done
//RB165
	rail[93]=new Rail(railButton21,LL, trainSettlerPanel,this);
        rail[80].setUpLeftRail(rail[67]);
        rail[80].setUpRightRail(rail[81]);
        rail[80].setDownLeftRail(rail[79]);
        rail[80].setDownRightRail(rail[93]);
//rail 80 done
//RB98
        rail[81].setUpLeftRail(rail[69]);
        rail[81].setUpRightRail(rail[82]);
        rail[81].setDownLeftRail(rail[80]);
        rail[81].setDownRightRail(rail[93]);
//rail 81 done
//RB170
	rail[94]=new Rail(railButton32,LL, trainSettlerPanel,this);
        rail[82].setUpLeftRail(rail[69]);
        rail[82].setUpRightRail(rail[91]);
        rail[82].setDownLeftRail(rail[81]);
        rail[82].setDownRightRail(rail[94]);
//rail 82 done
//RB15
        rail[83].setDownLeftRail(rail[30]);
        rail[83].setDownRightRail(rail[70]);
//rail 83 done
//RB27
        rail[84].setDownLeftRail(rail[72]);
        rail[84].setDownRightRail(rail[85]);
//rail 84 done
//RB215
	rail[95]=new Rail(railButton109,LR, trainSettlerPanel,this);
	rail[96]=new Rail(railButton38,LL, trainSettlerPanel,this);
        rail[85].setUpLeftRail(rail[84]);
        rail[85].setUpRightRail(rail[95]);
        rail[85].setDownLeftRail(rail[72]);
        rail[85].setDownRightRail(rail[96]);
//rail 85 done
//RB214
	rail[97]=new Rail(railButton108,LR, trainSettlerPanel,this);
	rail[98]=new Rail(railButton37,LL, trainSettlerPanel,this);
        rail[86].setUpLeftRail(rail[73]);
        rail[86].setUpRightRail(rail[97]);
        rail[86].setDownLeftRail(rail[74]);
        rail[86].setDownRightRail(rail[98]);
//rail 86 done
//RB101
        rail[87].setUpLeftRail(rail[75]);
        rail[87].setUpRightRail(rail[88]);
        rail[87].setDownLeftRail(rail[65]);
        rail[87].setDownRightRail(rail[89]);
//rail 87 done
//RB213
	rail[99]=new Rail(railButton107,LR, trainSettlerPanel,this);
	rail[100]=new Rail(railButton36,LL, trainSettlerPanel,this);
        rail[88].setUpLeftRail(rail[75]);
        rail[88].setUpRightRail(rail[99]);
        rail[88].setDownLeftRail(rail[87]);
        rail[88].setDownRightRail(rail[100]);
//rail 88 done
//RB30
        rail[89].setUpLeftRail(rail[65]);
        rail[89].setUpRightRail(rail[87]);
        rail[89].setDownLeftRail(rail[76]);
        rail[89].setDownRightRail(rail[90]);
//rail 89 done
//RB212
	rail[101]=new Rail(railButton106,LR, trainSettlerPanel,this);
	rail[102]=new Rail(railButton35,LL, trainSettlerPanel,this);
        rail[90].setUpLeftRail(rail[89]);
        rail[90].setUpRightRail(rail[101]);
        rail[90].setDownLeftRail(rail[76]);
        rail[90].setDownRightRail(rail[102]);
//rail 90 done
//RB99
        rail[91].setUpLeftRail(rail[78]);
        rail[91].setUpRightRail(rail[92]);
        rail[91].setDownLeftRail(rail[82]);
        rail[91].setDownRightRail(rail[94]);
//rail 91 done
//RB178
	rail[103]=new Rail(railButton105,LR, trainSettlerPanel,this);
	rail[104]=new Rail(railButton34,LL, trainSettlerPanel,this);
        rail[92].setUpLeftRail(rail[78]);
        rail[92].setUpRightRail(rail[103]);
        rail[92].setDownLeftRail(rail[91]);
        rail[92].setDownRightRail(rail[104]);
//rail 92 done
//RB21
        rail[93].setUpLeftRail(rail[80]);
        rail[93].setUpRightRail(rail[81]);
//rail 93 done
//RB32
	rail[105]=new Rail(railButton177,H, trainSettlerPanel,this);
        rail[94].setUpLeftRail(rail[82]);
        rail[94].setUpRightRail(rail[91]);
        rail[94].setDownRightRail(rail[105]);
//rail 94 done
//RB109
	rail[106]=new Rail(railButton179,H, trainSettlerPanel,this);
        rail[95].setUpRightRail(rail[106]);
        rail[95].setDownLeftRail(rail[85]);
        rail[95].setDownRightRail(rail[96]);
//rail 95 done
//RB38
	rail[107]=new Rail(railButton171,H, trainSettlerPanel,this);
        rail[96].setUpLeftRail(rail[85]);
        rail[96].setUpRightRail(rail[95]);
        rail[96].setDownLeftRail(rail[97]);
        rail[96].setDownRightRail(rail[107]);
//rail 96 done
//RB108
        rail[97].setUpLeftRail(rail[96]);
        rail[97].setUpRightRail(rail[107]);
        rail[97].setDownLeftRail(rail[86]);
        rail[97].setDownRightRail(rail[98]);
//rail 97 done
//RB37
	rail[108]=new Rail(railButton172,H, trainSettlerPanel,this);
        rail[98].setUpLeftRail(rail[86]);
        rail[98].setUpRightRail(rail[97]);
        rail[98].setDownLeftRail(rail[99]);
        rail[98].setDownRightRail(rail[108]);
//rail 98 done
//RB107
        rail[99].setUpLeftRail(rail[98]);
        rail[99].setUpRightRail(rail[108]);
        rail[99].setDownLeftRail(rail[88]);
        rail[99].setDownRightRail(rail[100]);
//rail 99 done
//RB36
	rail[109]=new Rail(railButton173,H, trainSettlerPanel,this);
        rail[100].setUpLeftRail(rail[88]);
        rail[100].setUpRightRail(rail[99]);
        rail[100].setDownLeftRail(rail[101]);
        rail[100].setDownRightRail(rail[109]);
//rail 100 done
//RB106
        rail[101].setUpLeftRail(rail[100]);
        rail[101].setUpRightRail(rail[109]);
        rail[101].setDownLeftRail(rail[90]);
        rail[101].setDownRightRail(rail[102]);
//rail 101 done
//RB35
	rail[110]=new Rail(railButton174,H, trainSettlerPanel,this);
        rail[102].setUpLeftRail(rail[90]);
        rail[102].setUpRightRail(rail[101]);
        rail[102].setDownLeftRail(rail[103]);
        rail[102].setDownRightRail(rail[110]);
//rail 102 done
//RB105
        rail[103].setUpLeftRail(rail[102]);
        rail[103].setUpRightRail(rail[110]);
        rail[103].setDownLeftRail(rail[92]);
        rail[103].setDownRightRail(rail[104]);
//rail 103 done
//RB34
	rail[216]=new Rail(railButton104,LR, trainSettlerPanel,this);
	rail[111]=new Rail(railButton175,H, trainSettlerPanel,this);
        rail[104].setUpLeftRail(rail[92]);
        rail[104].setUpRightRail(rail[103]);
        rail[104].setDownLeftRail(rail[216]);
        rail[104].setDownRightRail(rail[111]);
//rail 104 done
//RB177
	rail[112]=new Rail(railButton33,LL, trainSettlerPanel,this);
        rail[105].setUpLeftRail(rail[94]);
        rail[105].setUpRightRail(rail[216]);
        rail[105].setDownRightRail(rail[112]);
//rail 105 done
//RB179
	rail[113]=new Rail(railButton39,LL, trainSettlerPanel,this);
        rail[106].setDownLeftRail(rail[95]);
        rail[106].setDownRightRail(rail[113]);
//rail 106 done
//RB171
	rail[114]=new Rail(railButton115,LR, trainSettlerPanel,this);
	rail[115]=new Rail(railButton40,LL, trainSettlerPanel,this);
        rail[107].setUpLeftRail(rail[96]);
        rail[107].setUpRightRail(rail[114]);
        rail[107].setDownLeftRail(rail[97]);
        rail[107].setDownRightRail(rail[115]);
//rail 107 done
//RB172
	rail[116]=new Rail(railButton114,LR, trainSettlerPanel,this);
	rail[117]=new Rail(railButton41,LL, trainSettlerPanel,this);
        rail[108].setUpLeftRail(rail[98]);
        rail[108].setUpRightRail(rail[116]);
        rail[108].setDownLeftRail(rail[99]);
        rail[108].setDownRightRail(rail[117]);
//rail 108 done
//RB173
	rail[118]=new Rail(railButton113,LR, trainSettlerPanel,this);
	rail[119]=new Rail(railButton42,LL, trainSettlerPanel,this);
        rail[109].setUpLeftRail(rail[100]);
        rail[109].setUpRightRail(rail[118]);
        rail[109].setDownLeftRail(rail[101]);
        rail[109].setDownRightRail(rail[119]);
//rail 109 done
//RB174
        rail[110].setUpLeftRail(rail[102]);
        rail[110].setDownLeftRail(rail[103]);
//rail 110 done
//RB175
	rail[120]=new Rail(railButton111,LR, trainSettlerPanel,this);
	rail[121]=new Rail(railButton44,LL, trainSettlerPanel,this);
        rail[111].setUpLeftRail(rail[104]);
        rail[111].setUpRightRail(rail[120]);
        rail[111].setDownLeftRail(rail[216]);
        rail[111].setDownRightRail(rail[121]);
//rail 111 done
//RB33
	rail[122]=new Rail(railButton176,H, trainSettlerPanel,this);
        rail[112].setUpLeftRail(rail[105]);
        rail[112].setUpRightRail(rail[216]);
        rail[112].setDownRightRail(rail[122]);
//rail 112 done
//RB39
	rail[123]=new Rail(railButton180,H, trainSettlerPanel,this);
        rail[113].setUpLeftRail(rail[106]);
        rail[113].setDownLeftRail(rail[114]);
        rail[113].setDownRightRail(rail[123]);
//rail 113 done
//RB115
        rail[114].setUpLeftRail(rail[113]);
        rail[114].setUpRightRail(rail[123]);
        rail[114].setDownLeftRail(rail[107]);
        rail[114].setDownRightRail(rail[115]);
//rail 114 done
//RB40
	rail[124]=new Rail(railButton181,H, trainSettlerPanel,this);
        rail[115].setUpLeftRail(rail[107]);
        rail[115].setUpRightRail(rail[114]);
        rail[115].setDownLeftRail(rail[116]);
        rail[115].setDownRightRail(rail[124]);
//rail 115 done
//RB114
        rail[116].setUpLeftRail(rail[115]);
        rail[116].setUpRightRail(rail[124]);
        rail[116].setDownLeftRail(rail[108]);
        rail[116].setDownRightRail(rail[117]);
//rail 116 done
//RB41
	rail[125]=new Rail(railButton182,H, trainSettlerPanel,this);
        rail[117].setUpLeftRail(rail[108]);
        rail[117].setUpRightRail(rail[116]);
        rail[117].setDownLeftRail(rail[118]);
        rail[117].setDownRightRail(rail[125]);
//rail 117 done
//RB113
        rail[118].setUpLeftRail(rail[117]);
        rail[118].setUpRightRail(rail[125]);
        rail[118].setDownLeftRail(rail[109]);
        rail[118].setDownRightRail(rail[119]);
//rail 118 done
//RB42
	rail[126]=new Rail(railButton112,LR, trainSettlerPanel,this);
        rail[110].setUpRightRail(rail[126]);
	rail[127]=new Rail(railButton183,H, trainSettlerPanel,this);
        rail[119].setUpLeftRail(rail[109]);
        rail[119].setUpRightRail(rail[118]);
        rail[119].setDownLeftRail(rail[126]);
        rail[119].setDownRightRail(rail[127]);
//rail 119 done
//RB111
	rail[128]=new Rail(railButton43,LL, trainSettlerPanel,this);
        rail[110].setDownRightRail(rail[128]);
	rail[129]=new Rail(railButton184,H, trainSettlerPanel,this);
        rail[120].setUpLeftRail(rail[128]);
        rail[120].setUpRightRail(rail[129]);
        rail[120].setDownLeftRail(rail[111]);
        rail[120].setDownRightRail(rail[121]);
//rail 120 done
//RB44
	rail[130]=new Rail(railButton110,LR, trainSettlerPanel,this);
	rail[131]=new Rail(railButton185,H, trainSettlerPanel,this);
        rail[121].setUpLeftRail(rail[111]);
        rail[121].setUpRightRail(rail[120]);
        rail[121].setDownLeftRail(rail[130]);
        rail[121].setDownRightRail(rail[131]);
//rail 121 done
//RB176
	rail[132]=new Rail(railButton45,LL, trainSettlerPanel,this);
        rail[122].setUpLeftRail(rail[112]);
        rail[122].setUpRightRail(rail[130]);
        rail[122].setDownRightRail(rail[132]);
//rail 122 done
//RB180
	rail[133]=new Rail(railButton122,LR, trainSettlerPanel,this);
	rail[134]=new Rail(railButton51,LL, trainSettlerPanel,this);
        rail[123].setUpLeftRail(rail[113]);
        rail[123].setUpRightRail(rail[133]);
        rail[123].setDownLeftRail(rail[114]);
        rail[123].setDownRightRail(rail[134]);
//rail 123 done
//RB181
	rail[135]=new Rail(railButton116,LR, trainSettlerPanel,this);
	rail[136]=new Rail(railButton50,LL, trainSettlerPanel,this);
        rail[124].setUpLeftRail(rail[115]);
        rail[124].setUpRightRail(rail[135]);
        rail[124].setDownLeftRail(rail[116]);
        rail[124].setDownRightRail(rail[136]);
//rail 124 done
//RB182
	rail[137]=new Rail(railButton117,LR, trainSettlerPanel,this);
	rail[138]=new Rail(railButton49,LL, trainSettlerPanel,this);
        rail[125].setUpLeftRail(rail[117]);
        rail[125].setUpRightRail(rail[137]);
        rail[125].setDownLeftRail(rail[118]);
        rail[125].setDownRightRail(rail[138]);
//rail 125 done
//RB112
        rail[126].setUpLeftRail(rail[119]);
        rail[126].setUpRightRail(rail[127]);
        rail[126].setDownLeftRail(rail[110]);
        rail[126].setDownRightRail(rail[128]);
//rail 126 done
//RB183
	rail[139]=new Rail(railButton118,LR, trainSettlerPanel,this);
	rail[140]=new Rail(railButton48,LL, trainSettlerPanel,this);
        rail[127].setUpLeftRail(rail[119]);
        rail[127].setUpRightRail(rail[139]);
        rail[127].setDownLeftRail(rail[126]);
        rail[127].setDownRightRail(rail[140]);
//rail 127 done
//RB43
        rail[128].setUpLeftRail(rail[110]);
        rail[128].setUpRightRail(rail[126]);
        rail[128].setDownLeftRail(rail[120]);
        rail[128].setDownRightRail(rail[129]);
//rail 128 done
//RB184
	rail[141]=new Rail(railButton119,LR, trainSettlerPanel,this);
	rail[142]=new Rail(railButton47,LL, trainSettlerPanel,this);
        rail[129].setUpLeftRail(rail[128]);
        rail[129].setUpRightRail(rail[141]);
        rail[129].setDownLeftRail(rail[120]);
        rail[129].setDownRightRail(rail[142]);
//rail 129 done
//RB110
        rail[130].setUpLeftRail(rail[121]);
        rail[130].setUpRightRail(rail[131]);
        rail[130].setDownLeftRail(rail[122]);
        rail[130].setDownRightRail(rail[132]);
//rail 130 done
//RB185
	rail[143]=new Rail(railButton120,LR, trainSettlerPanel,this);
	rail[144]=new Rail(railButton46,LL, trainSettlerPanel,this);
        rail[131].setUpLeftRail(rail[121]);
        rail[131].setUpRightRail(rail[143]);
        rail[131].setDownLeftRail(rail[130]);
        rail[131].setDownRightRail(rail[144]);
//rail 131 done
//RB45
	rail[145]=new Rail(railButton186,H, trainSettlerPanel,this);
        rail[132].setUpLeftRail(rail[122]);
        rail[132].setUpRightRail(rail[130]);
        rail[132].setDownRightRail(rail[145]);
//rail 132 done
//RB122
        rail[133].setDownLeftRail(rail[123]);
        rail[133].setDownRightRail(rail[134]);
//rail 133 done
//RB51
	rail[146]=new Rail(railButton187,H, trainSettlerPanel,this);
        rail[134].setUpLeftRail(rail[123]);
        rail[134].setUpRightRail(rail[133]);
        rail[134].setDownLeftRail(rail[135]);
        rail[134].setDownRightRail(rail[146]);
//rail 134 done
//RB116
        rail[135].setUpLeftRail(rail[134]);
        rail[135].setUpRightRail(rail[146]);
        rail[135].setDownLeftRail(rail[124]);
        rail[135].setDownRightRail(rail[136]);
//rail 135 done
//RB50
	rail[147]=new Rail(railButton188,H, trainSettlerPanel,this);
        rail[136].setUpLeftRail(rail[124]);
        rail[136].setUpRightRail(rail[135]);
        rail[136].setDownLeftRail(rail[137]);
        rail[136].setDownRightRail(rail[147]);
//rail 136 done
//RB117
        rail[137].setUpLeftRail(rail[136]);
        rail[137].setUpRightRail(rail[147]);
        rail[137].setDownLeftRail(rail[125]);
        rail[137].setDownRightRail(rail[138]);
//rail 137 done
//RB49
	rail[148]=new Rail(railButton189,H, trainSettlerPanel,this);
        rail[138].setUpLeftRail(rail[125]);
        rail[138].setUpRightRail(rail[137]);
        rail[138].setDownLeftRail(rail[139]);
        rail[138].setDownRightRail(rail[148]);
//rail 138 done
//RB118
        rail[139].setUpLeftRail(rail[138]);
        rail[139].setUpRightRail(rail[148]);
        rail[139].setDownLeftRail(rail[127]);
        rail[139].setDownRightRail(rail[140]);
//rail 139 done
//RB48
	rail[149]=new Rail(railButton190,H, trainSettlerPanel,this);
        rail[140].setUpLeftRail(rail[127]);
        rail[140].setUpRightRail(rail[139]);
        rail[140].setDownLeftRail(rail[141]);
        rail[140].setDownRightRail(rail[149]);
//rail 140 done
//RB119
        rail[141].setUpLeftRail(rail[140]);
        rail[141].setUpRightRail(rail[149]);
        rail[141].setDownLeftRail(rail[129]);
        rail[141].setDownRightRail(rail[142]);
//rail 141 done
//RB47
	rail[150]=new Rail(railButton191,H, trainSettlerPanel,this);
        rail[142].setUpLeftRail(rail[129]);
        rail[142].setUpRightRail(rail[141]);
        rail[142].setDownLeftRail(rail[143]);
        rail[142].setDownRightRail(rail[150]);
//rail 142 done
//RB120
        rail[143].setUpLeftRail(rail[142]);
        rail[143].setUpRightRail(rail[150]);
        rail[143].setDownLeftRail(rail[131]);
        rail[143].setDownRightRail(rail[144]);
//rail 143 done
//RB46
        rail[144].setUpLeftRail(rail[131]);
        rail[144].setUpRightRail(rail[143]);
//rail 144 done
//RB186
        rail[145].setUpLeftRail(rail[132]);
//rail 145 done
//RB187
	rail[151]=new Rail(railButton127,LR, trainSettlerPanel,this);
	rail[152]=new Rail(railButton56,LL, trainSettlerPanel,this);
        rail[146].setUpLeftRail(rail[134]);
        rail[146].setUpRightRail(rail[151]);
        rail[146].setDownLeftRail(rail[135]);
        rail[146].setDownRightRail(rail[152]);
//rail 146 done
//RB188
	rail[153]=new Rail(railButton126,LR, trainSettlerPanel,this);
	rail[154]=new Rail(railButton55,LL, trainSettlerPanel,this);
        rail[147].setUpLeftRail(rail[136]);
        rail[147].setUpRightRail(rail[153]);
        rail[147].setDownLeftRail(rail[137]);
        rail[147].setDownRightRail(rail[154]);
//rail 147 done
//RB189
	rail[155]=new Rail(railButton125,LR, trainSettlerPanel,this);
	rail[156]=new Rail(railButton54,LL, trainSettlerPanel,this);
        rail[148].setUpLeftRail(rail[138]);
        rail[148].setUpRightRail(rail[155]);
        rail[148].setDownLeftRail(rail[139]);
        rail[148].setDownRightRail(rail[156]);
//rail 148 done
//RB190
	rail[157]=new Rail(railButton124,LR, trainSettlerPanel,this);
	rail[158]=new Rail(railButton53,LL, trainSettlerPanel,this);
        rail[149].setUpLeftRail(rail[140]);
        rail[149].setUpRightRail(rail[157]);
        rail[149].setDownLeftRail(rail[141]);
        rail[149].setDownRightRail(rail[158]);
//rail 149 done
//RB191
	rail[159]=new Rail(railButton123,LR, trainSettlerPanel,this);
	rail[160]=new Rail(railButton52,LL, trainSettlerPanel,this);
        rail[150].setUpLeftRail(rail[142]);
        rail[150].setUpRightRail(rail[159]);
        rail[150].setDownLeftRail(rail[143]);
        rail[150].setDownRightRail(rail[160]);
//rail 150 done
//RB127
        rail[151].setDownLeftRail(rail[146]);
        rail[151].setDownRightRail(rail[152]);
//rail 151 done
//RB56
	rail[161]=new Rail(railButton192,H, trainSettlerPanel,this);
        rail[152].setUpLeftRail(rail[146]);
        rail[152].setUpRightRail(rail[151]);
        rail[152].setDownLeftRail(rail[153]);
        rail[152].setDownRightRail(rail[161]);
//rail 152 done
//RB126
        rail[153].setUpLeftRail(rail[152]);
        rail[153].setUpRightRail(rail[161]);
        rail[153].setDownLeftRail(rail[147]);
        rail[153].setDownRightRail(rail[154]);
//rail 153 done
//RB55
	rail[162]=new Rail(railButton193,H, trainSettlerPanel,this);
        rail[154].setUpLeftRail(rail[147]);
        rail[154].setUpRightRail(rail[153]);
        rail[154].setDownLeftRail(rail[155]);
        rail[154].setDownRightRail(rail[162]);
//rail 154 done
//RB125
        rail[155].setUpLeftRail(rail[154]);
        rail[155].setUpRightRail(rail[162]);
        rail[155].setDownLeftRail(rail[148]);
        rail[155].setDownRightRail(rail[156]);
//rail 155 done
//RB54
	rail[163]=new Rail(railButton194,H, trainSettlerPanel,this);
        rail[156].setUpLeftRail(rail[148]);
        rail[156].setUpRightRail(rail[155]);
        rail[156].setDownLeftRail(rail[157]);
        rail[156].setDownRightRail(rail[163]);
//rail 156 done
//RB124
        rail[157].setUpLeftRail(rail[156]);        		
        rail[157].setUpRightRail(rail[163]);
        rail[157].setDownLeftRail(rail[149]);
        rail[157].setDownRightRail(rail[158]);
//rail 157 done
//RB53
	rail[164]=new Rail(railButton195,H, trainSettlerPanel,this);
        rail[158].setUpLeftRail(rail[149]);
        rail[158].setUpRightRail(rail[157]);
        rail[158].setDownLeftRail(rail[159]);
        rail[158].setDownRightRail(rail[164]);
//rail 158 done
//RB123
        rail[159].setUpLeftRail(rail[158]);
        rail[159].setUpRightRail(rail[164]);
        rail[159].setDownLeftRail(rail[150]);
        rail[159].setDownRightRail(rail[160]);
//rail 159 done
//RB52
	rail[165]=new Rail(railButton121,LR, trainSettlerPanel,this);
	rail[166]=new Rail(railButton196,H, trainSettlerPanel,this);
        rail[160].setUpLeftRail(rail[150]);
        rail[160].setUpRightRail(rail[159]);
        rail[160].setDownLeftRail(rail[165]);
        rail[160].setDownRightRail(rail[166]);
//rail 160 done
//RB192
	rail[167]=new Rail(railButton132,LR, trainSettlerPanel,this);
	rail[168]=new Rail(railButton60,LL, trainSettlerPanel,this);
        rail[161].setUpLeftRail(rail[152]);
        rail[161].setUpRightRail(rail[167]);
        rail[161].setDownLeftRail(rail[153]);
        rail[161].setDownRightRail(rail[168]);
//rail 161 done
//RB193
	rail[169]=new Rail(railButton131,LR, trainSettlerPanel,this);
	rail[170]=new Rail(railButton59,LL, trainSettlerPanel,this);
        rail[162].setUpLeftRail(rail[154]);
        rail[162].setUpRightRail(rail[169]);
        rail[162].setDownLeftRail(rail[155]);
        rail[162].setDownRightRail(rail[170]);
//rail 162 done
//RB194
	rail[171]=new Rail(railButton130,LR, trainSettlerPanel,this);
	rail[172]=new Rail(railButton58,LL, trainSettlerPanel,this);
        rail[163].setUpLeftRail(rail[156]);
        rail[163].setUpRightRail(rail[171]);
        rail[163].setDownLeftRail(rail[157]);
        rail[163].setDownRightRail(rail[172]);
//rail 163 done
//RB195
	rail[173]=new Rail(railButton129,LR, trainSettlerPanel,this);
	rail[174]=new Rail(railButton57,LL, trainSettlerPanel,this);
        rail[164].setUpLeftRail(rail[158]);
        rail[164].setUpRightRail(rail[173]);
        rail[164].setDownLeftRail(rail[159]);
        rail[164].setDownRightRail(rail[174]);
//rail 164 done
//RB121
        rail[165].setUpLeftRail(rail[160]);
        rail[165].setUpRightRail(rail[166]);
//rail 165 done
//RB196
	rail[175]=new Rail(railButton128,LR, trainSettlerPanel,this);
        rail[166].setUpLeftRail(rail[160]);
        rail[166].setUpRightRail(rail[175]);
        rail[166].setDownLeftRail(rail[165]);
//rail 166 done
//RB132
	rail[176]=new Rail(railButton61,LL, trainSettlerPanel,this);
	rail[177]=new Rail(railButton197,H, trainSettlerPanel,this);
        rail[167].setUpLeftRail(rail[176]);
        rail[167].setUpRightRail(rail[177]);
        rail[167].setDownLeftRail(rail[161]);
        rail[167].setDownRightRail(rail[168]);
//rail 167 done
//RB60
	rail[178]=new Rail(railButton198,H, trainSettlerPanel,this);
        rail[168].setUpLeftRail(rail[161]);
        rail[168].setUpRightRail(rail[167]);
        rail[168].setDownLeftRail(rail[169]);
        rail[168].setDownRightRail(rail[178]);
//rail 168 done
//RB131
        rail[169].setUpLeftRail(rail[168]);
        rail[169].setUpRightRail(rail[178]);
        rail[169].setDownLeftRail(rail[162]);
        rail[169].setDownRightRail(rail[170]);
//rail 169 done
//RB59
	rail[179]=new Rail(railButton199,H, trainSettlerPanel,this);
        rail[170].setUpLeftRail(rail[162]);
        rail[170].setUpRightRail(rail[169]);
        rail[170].setDownLeftRail(rail[171]);
        rail[170].setDownRightRail(rail[179]);
//rail 170 done
//RB130
        rail[171].setUpLeftRail(rail[170]);
        rail[171].setUpRightRail(rail[179]);
        rail[171].setDownLeftRail(rail[163]);
        rail[171].setDownRightRail(rail[172]);
//rail 171 done
//RB58
	rail[180]=new Rail(railButton200,H, trainSettlerPanel,this);
        rail[172].setUpLeftRail(rail[163]);
        rail[172].setUpRightRail(rail[171]);
        rail[172].setDownLeftRail(rail[173]);
        rail[172].setDownRightRail(rail[180]);
//rail 172 done
//RB129
        rail[173].setUpLeftRail(rail[172]);
        rail[173].setUpRightRail(rail[180]);
        rail[173].setDownLeftRail(rail[164]);
        rail[173].setDownRightRail(rail[174]);
//rail 173 done
//RB57
	rail[181]=new Rail(railButton201,H, trainSettlerPanel,this);
        rail[174].setUpLeftRail(rail[164]);
        rail[174].setUpRightRail(rail[173]);
        rail[174].setDownLeftRail(rail[175]);
        rail[174].setDownRightRail(rail[181]);
//rail 174 done
//RB128
        rail[175].setUpLeftRail(rail[174]);
        rail[175].setUpRightRail(rail[181]);
        rail[175].setDownLeftRail(rail[166]);
//rail 175 done
//RB61
	rail[182]=new Rail(railButton145,LR, trainSettlerPanel,this);
        rail[176].setUpRightRail(rail[182]);
        rail[176].setDownLeftRail(rail[167]);
        rail[176].setDownRightRail(rail[177]);
//rail 176 done
//RB197
	rail[183]=new Rail(railButton62,LL, trainSettlerPanel,this);
        rail[177].setUpLeftRail(rail[176]);
        rail[177].setDownLeftRail(rail[167]);
        rail[177].setDownRightRail(rail[183]);
//rail 177 done
//RB198
	rail[184]=new Rail(railButton133,LR, trainSettlerPanel,this);
	rail[185]=new Rail(railButton63,LL, trainSettlerPanel,this);
        rail[178].setUpLeftRail(rail[168]);
        rail[178].setUpRightRail(rail[184]);
        rail[178].setDownLeftRail(rail[169]);
        rail[178].setDownRightRail(rail[185]);
//rail 178 done
//RB199
	rail[186]=new Rail(railButton134,LR, trainSettlerPanel,this);
	rail[187]=new Rail(railButton64,LL, trainSettlerPanel,this);
        rail[179].setUpLeftRail(rail[170]);
        rail[179].setUpRightRail(rail[186]);
        rail[179].setDownLeftRail(rail[171]);
        rail[179].setDownRightRail(rail[187]);
//rail 179 done
//RB200
	rail[188]=new Rail(railButton135,LR, trainSettlerPanel,this);
	rail[189]=new Rail(railButton65,LL, trainSettlerPanel,this);
        rail[180].setUpLeftRail(rail[172]);
        rail[180].setUpRightRail(rail[188]);
        rail[180].setDownLeftRail(rail[173]);
        rail[180].setDownRightRail(rail[189]);
//rail 180 done
//RB201
	rail[190]=new Rail(railButton136,LR, trainSettlerPanel,this);
        rail[181].setUpLeftRail(rail[174]);
        rail[181].setUpRightRail(rail[190]);
        rail[181].setDownLeftRail(rail[175]);
//rail 181 done
//RB145
        rail[182].setDownRightRail(rail[176]);
//rail 182 done
//RB62
	rail[191]=new Rail(railButton202,H, trainSettlerPanel,this);
        rail[183].setUpLeftRail(rail[177]);
        rail[183].setDownLeftRail(rail[184]);
        rail[183].setDownRightRail(rail[191]);
//rail 183 done
//RB133
        rail[184].setUpLeftRail(rail[183]);
        rail[184].setUpRightRail(rail[191]);
        rail[184].setDownLeftRail(rail[178]);
        rail[184].setDownRightRail(rail[185]);
//rail 184 done
//RB63
	rail[192]=new Rail(railButton203,H, trainSettlerPanel,this);
        rail[185].setUpLeftRail(rail[178]);
        rail[185].setUpRightRail(rail[184]);
        rail[185].setDownLeftRail(rail[186]);
        rail[185].setDownRightRail(rail[192]);
//rail 185 done
//RB134
        rail[186].setUpLeftRail(rail[185]);
        rail[186].setUpRightRail(rail[192]);
        rail[186].setDownLeftRail(rail[179]);
        rail[186].setDownRightRail(rail[187]);
//rail 186 done
//RB64
	rail[193]=new Rail(railButton204,H, trainSettlerPanel,this);
        rail[187].setUpLeftRail(rail[179]);
        rail[187].setUpRightRail(rail[186]);
        rail[187].setDownLeftRail(rail[188]);
        rail[187].setDownRightRail(rail[193]);
//rail 187 done
//RB135
        rail[188].setUpLeftRail(rail[187]);
        rail[188].setUpRightRail(rail[193]);
        rail[188].setDownLeftRail(rail[180]);
        rail[188].setDownRightRail(rail[189]);
//rail 188 done
//RB65
	rail[194]=new Rail(railButton205,H, trainSettlerPanel,this);
        rail[189].setUpLeftRail(rail[180]);
        rail[189].setUpRightRail(rail[188]);
        rail[189].setDownLeftRail(rail[190]);
        rail[189].setDownRightRail(rail[194]);
//rail 189 done
//RB136
        rail[190].setUpLeftRail(rail[189]);
        rail[190].setUpRightRail(rail[194]);
        rail[190].setDownLeftRail(rail[181]);
//rail 190 done
//RB202
	rail[195]=new Rail(railButton140,LR, trainSettlerPanel,this);
	rail[196]=new Rail(railButton69,LL, trainSettlerPanel,this);
        rail[191].setUpLeftRail(rail[183]);
        rail[191].setUpRightRail(rail[195]);
        rail[191].setDownLeftRail(rail[184]);
        rail[191].setDownRightRail(rail[196]);
//rail 191 done
//RB203
	rail[197]=new Rail(railButton139,LR, trainSettlerPanel,this);
	rail[198]=new Rail(railButton68,LL, trainSettlerPanel,this);
        rail[192].setUpLeftRail(rail[185]);
        rail[192].setUpRightRail(rail[197]);
        rail[192].setDownLeftRail(rail[186]);
        rail[192].setDownRightRail(rail[198]);
//rail 192 done
//RB204
	rail[199]=new Rail(railButton138,LR, trainSettlerPanel,this);
	rail[200]=new Rail(railButton67,LL, trainSettlerPanel,this);
        rail[193].setUpLeftRail(rail[187]);
        rail[193].setUpRightRail(rail[199]);
        rail[193].setDownLeftRail(rail[188]);
        rail[193].setDownRightRail(rail[200]);
//rail 193 done
//RB205
	rail[201]=new Rail(railButton137,LR, trainSettlerPanel,this);
	rail[202]=new Rail(railButton66,LL, trainSettlerPanel,this);
        rail[194].setUpLeftRail(rail[189]);
        rail[194].setUpRightRail(rail[201]);
        rail[194].setDownLeftRail(rail[190]);
        rail[194].setDownRightRail(rail[202]);
//rail 194 done
//RB140
	rail[203]=new Rail(railButton70,LL, trainSettlerPanel,this);
	rail[204]=new Rail(railButton207,H, trainSettlerPanel,this);
        rail[195].setUpLeftRail(rail[203]);
        rail[195].setUpRightRail(rail[204]);
        rail[195].setDownLeftRail(rail[191]);
        rail[195].setDownRightRail(rail[196]);
//rail 195 done
//RB69
	rail[205]=new Rail(railButton208,H, trainSettlerPanel,this);
        rail[196].setUpLeftRail(rail[191]);
        rail[196].setUpRightRail(rail[195]);
        rail[196].setDownLeftRail(rail[197]);
        rail[196].setDownRightRail(rail[205]);
//rail 196 done
//RB139
        rail[197].setUpLeftRail(rail[196]);
        rail[197].setUpRightRail(rail[205]);
        rail[197].setDownLeftRail(rail[192]);
        rail[197].setDownRightRail(rail[198]);
//rail 197 done
//RB68
	rail[206]=new Rail(railButton209,H, trainSettlerPanel,this);
        rail[198].setUpLeftRail(rail[192]);
        rail[198].setUpRightRail(rail[197]);
        rail[198].setDownLeftRail(rail[199]);
        rail[198].setDownRightRail(rail[206]);
//rail 198 done
//RB138
        rail[199].setUpLeftRail(rail[198]);
        rail[199].setUpRightRail(rail[206]);
        rail[199].setDownLeftRail(rail[193]);
        rail[199].setDownRightRail(rail[200]);
//rail 199 done
//RB67
        rail[200].setUpLeftRail(rail[193]);
        rail[200].setUpRightRail(rail[199]);
        rail[200].setDownLeftRail(rail[201]);
//rail 200 done
//RB137
        rail[201].setUpLeftRail(rail[200]);
        rail[201].setDownLeftRail(rail[194]);
        rail[201].setDownRightRail(rail[202]);
//rail 201 done
//RB66
        rail[202].setUpLeftRail(rail[194]);
        rail[202].setUpRightRail(rail[201]);
//rail 202 done
//RB70
        rail[203].setDownLeftRail(rail[195]);
        rail[203].setDownRightRail(rail[204]);
//rail 203 done
//RB207
	rail[207]=new Rail(railButton143,LR, trainSettlerPanel,this);
	rail[208]=new Rail(railButton72,LL, trainSettlerPanel,this);
        rail[204].setUpLeftRail(rail[203]);
        rail[204].setUpRightRail(rail[207]);
        rail[204].setDownLeftRail(rail[195]);
        rail[204].setDownRightRail(rail[208]);
//rail 204 done
//RB208
	rail[209]=new Rail(railButton142,LR, trainSettlerPanel,this);
	rail[210]=new Rail(railButton71,LL, trainSettlerPanel,this);
        rail[205].setUpLeftRail(rail[196]);
        rail[205].setUpRightRail(rail[209]);
        rail[205].setDownLeftRail(rail[197]);
        rail[205].setDownRightRail(rail[210]);
//rail 205 done
//RB209
	rail[211]=new Rail(railButton141,LR, trainSettlerPanel,this);
        rail[206].setUpLeftRail(rail[198]);
        rail[206].setUpRightRail(rail[211]);
        rail[206].setDownLeftRail(rail[199]);
//rail 206 done
//RB143
	rail[212]=new Rail(railButton73,LL, trainSettlerPanel,this);
	rail[213]=new Rail(railButton210,H, trainSettlerPanel,this);
        rail[207].setUpLeftRail(rail[212]);
        rail[207].setUpRightRail(rail[213]);
        rail[207].setDownLeftRail(rail[204]);
        rail[207].setDownRightRail(rail[208]);
//rail 207 done
//RB72
        rail[208].setUpLeftRail(rail[204]);
        rail[208].setUpRightRail(rail[207]);
        rail[208].setDownLeftRail(rail[209]);
//rail 208 done
//RB142
        rail[209].setUpLeftRail(rail[208]);
        rail[209].setDownLeftRail(rail[205]);
        rail[209].setDownRightRail(rail[210]);
//rail 209 done
//RB71
        rail[210].setUpLeftRail(rail[205]);
        rail[210].setUpRightRail(rail[209]);
        rail[210].setDownLeftRail(rail[211]);
//rail 210 done
//RB141
        rail[211].setUpLeftRail(rail[210]);
        rail[211].setDownLeftRail(rail[206]);
//rail 211 done
//RB73
	rail[217]=new Rail(railButton206,H, trainSettlerPanel,this);
	rail[214]=new Rail(railButton144,LR, trainSettlerPanel,this);
        rail[212].setUpLeftRail(rail[217]);
        rail[212].setUpRightRail(rail[214]);
        rail[212].setDownLeftRail(rail[207]);
        rail[212].setDownRightRail(rail[213]);
//rail 212 done
//RB206
        rail[213].setUpLeftRail(rail[212]);
        rail[213].setDownLeftRail(rail[207]);
//rail 213 done
//RB144
	rail[215]=new Rail(railButton211,H, trainSettlerPanel,this);
        rail[214].setUpRightRail(rail[215]);
        rail[214].setDownLeftRail(rail[217]);
        rail[214].setDownRightRail(rail[212]);
//rail 214 done
//RB211
        rail[215].setDownLeftRail(rail[214]);
//rail 215 done
//RB104
        rail[216].setUpLeftRail(rail[104]);
        rail[216].setUpRightRail(rail[111]);
        rail[216].setDownLeftRail(rail[105]);
        rail[216].setDownRightRail(rail[112]);
        //rail button 216 done
        //RB206
        rail[217].setUpRightRail(rail[214]);
        rail[217].setDownRightRail(rail[212]);
        //rail button 217 done
        //</editor-fold>
        
        //initialize citySpots
        //<editor-fold>
        spokane=citySpot[0]=new CityHex(null, "Spokane",hex[15],hex[5],spokaneButton,doubleGold,this);
        seattle=citySpot[1]=new CityHex(null, "Seattle",hex[0],seattleButton,doubleGold,this);
        portland=citySpot[2]=new CityHex(null, "Portland",hex[4],portlandButton,doubleGold,this);
        boise=citySpot[3]=new CityHex(null, "Boise",hex[5],hex[6],hex[14],boiseButton,doubleGold,this);
        helena=citySpot[4]=new CityHex(null, "Helena",hex[15],hex[14],hex[16],helenaButton,doubleGold,this);
        idahoFalls=citySpot[5]=new CityHex(null, "Idaho Falls",hex[14],hex[13],hex[17],idahoFallsButton,doubleGold,this);
        billings=citySpot[6]=new CityHex(null, "Billings",hex[16],hex[25],hex[26],billingsButton,doubleGold,this);        
        casper=citySpot[7]=new CityHex(null, "Casper",hex[25],hex[17],hex[24],casperButton,doubleGold,this);
        rapidCity=citySpot[8]=new CityHex(null, "Rapid City",hex[25],hex[27],hex[28],rapidCityButton,doubleGold,this);
        bismark=citySpot[9]=new CityHex(null, "Bismark",hex[27],hex[37],hex[38],bismarkButton,doubleGold,this);
        omaha=citySpot[10]=new CityHex(null, "Omaha",hex[36],hex[40],hex[41],omahaButton,doubleGold,this);
        minneapolis=citySpot[11]=new CityHex(null, "Minneapolis",hex[40],hex[39],hex[49],minneapolisButton,doubleGold,this);
        chicago=citySpot[12]=new CityHex(null, "Chicago",hex[51],hex[50],hex[58],chicagoButton,doubleGold,this);
        indianapolis=citySpot[13]=new CityHex(null, "Indianapolis",hex[51],hex[52],hex[57],indianapolisButton,doubleGold,this);
        columbus=citySpot[14]=new CityHex(null, "Columbus",hex[57],hex[58],hex[60],columbusButton,doubleGold,this);
        washingtonDC=citySpot[15]=new CityHex(null, "Washington D.C.",hex[60],hex[66],hex[67],washingtonDCButton,doubleGold,this);
        albany=citySpot[16]=new CityHex(null, "Albany",hex[67],hex[68],hex[59],albanyButton,doubleGold,this);
        boston=citySpot[17]=new CityHex(null, "Boston",hex[68],hex[69],hex[70],bostonButton,doubleGold,this);
        newYork=citySpot[18]=new CityHex(null, "New York",hex[67],hex[70],newYorkButton,doubleGold,this);
        sanFrancisco=citySpot[19]=new CityHex(null, "San Francisco",hex[3],sanFranciscoButton,doubleGold,this);
        reno=citySpot[20]=new CityHex(null, "Reno",hex[3],hex[2],hex[7],renoButton,doubleGold,this);        
        losAngeles=citySpot[21]=new CityHex(null, "Los Angeles",hex[4],hex[8],hex[9],losAngelesButton,doubleGold,this);
        lasVegas=citySpot[22]=new CityHex(null, "Las Vegas",hex[8],hex[11],hex[12],lasVegasButton,doubleGold,this);
        saltLakeCity=citySpot[23]=new CityHex(null, "Salt Lake City",hex[12],hex[13],hex[18],saltLakeCityButton,doubleGold,this);
        flagstaff=citySpot[24]=new CityHex(null, "Flagstaff",hex[11],hex[20],hex[19],flagstaffButton,doubleGold,this);
        denver=citySpot[25]=new CityHex(null, "Denver",hex[23],hex[24],hex[29],denverButton,doubleGold,this);
        santaFe=citySpot[26]=new CityHex(null, "Santa Fe",hex[23],hex[22],hex[30],santaFeButton,doubleGold,this);
        elPaso=citySpot[27]=new CityHex(null, "El Paso",hex[31],hex[21],hex[32],elPasoButton,doubleGold,this);
        amarillo=citySpot[28]=new CityHex(null, "Amarillo",hex[30],hex[31],hex[34],amarilloButton,doubleGold,this);
        wichita=citySpot[29]=new CityHex(null, "Wichita",hex[35],hex[39],hex[41],wichitaButton,doubleGold,this);
        dallas=citySpot[30]=new CityHex(null, "Dallas",hex[33],hex[34],hex[43],dallasButton,doubleGold,this);
        sanAntonio=citySpot[31]=new CityHex(null, "San Antonio",hex[33],hex[34],sanAntonioButton,doubleGold,this);
        shreveport=citySpot[32]=new CityHex(null, "Shreveport",hex[43],hex[45],hex[46],shreveportButton,doubleGold,this);
        houston=citySpot[33]=new CityHex(null, "Houston",hex[44],hex[45],houstonButton,doubleGold,this);
        littleRock=citySpot[34]=new CityHex(null, "Little Rock",hex[39],hex[46],hex[47],littleRockButton,doubleGold,this);
        stLouis=citySpot[35]=new CityHex(null, "St. Louis",hex[47],hex[48],hex[52],stLouisButton,doubleGold,this);
        jackson=citySpot[36]=new CityHex(null, "Jackson",hex[46],hex[53],hex[54],jacksonButton,doubleGold,this);
        newOrleans=citySpot[37]=new CityHex(null, "New Orleans",hex[54],newOrleansButton,doubleGold,this);
        nashville=citySpot[38]=new CityHex(null, "Nashville",hex[52],hex[53],hex[56],nashvilleButton,doubleGold,this);
        lexington=citySpot[39]=new CityHex(null, "Lexington",hex[56],hex[57],hex[61],lexingtonButton,doubleGold,this);
        atlanta=citySpot[40]=new CityHex(null, "Atlanta",hex[55],hex[56],hex[62],atlantaButton,doubleGold,this);
        pensacola=citySpot[41]=new CityHex(null, "Pensacola",hex[55],hex[63],pensacolaButton,doubleGold,this);
        raleigh=citySpot[42]=new CityHex(null, "Raleigh",hex[61],hex[65],hex[66],raleighButton,doubleGold,this);
        columbia=citySpot[43]=new CityHex(null, "Columbia",hex[65],hex[62],columbiaButton,doubleGold,this);
        jacksonville=citySpot[44]=new CityHex(null, "Jacksonville",hex[63],hex[64],jacksonvilleButton,doubleGold,this);
        spokane.setRails(rail[14],rail[9],rail[15]);
        rail[14].setCity(spokane);
        rail[9].setCity(spokane);
        rail[15].setCity(spokane);
        seattle.setRails(null,null,rail[0]);
        rail[0].setCity(seattle);
        portland.setRails(rail[1],null,rail[3]);
        rail[1].setCity(portland);
        rail[3].setCity(portland);
        boise.setRails(rail[23],rail[10],rail[24]);
        rail[23].setCity(boise);
        rail[10].setCity(boise);
        rail[24].setCity(boise);
        helena.setRails(rail[30],rail[22],rail[31]);
        rail[30].setCity(helena);
        rail[22].setCity(helena);
        rail[31].setCity(helena);
        idahoFalls.setRails(rail[32],rail[25],rail[33]);
        rail[32].setCity(idahoFalls);
        rail[25].setCity(idahoFalls);
        rail[33].setCity(idahoFalls);
        billings.setRails(rail[61],rail[62],rail[51]);
        rail[61].setCity(billings);
        rail[62].setCity(billings);
        rail[51].setCity(billings);
        casper.setRails(rail[52],rail[63],rail[53]);
        rail[52].setCity(casper);
        rail[63].setCity(casper);
        rail[53].setCity(casper);
        rapidCity.setRails(rail[73],rail[86],rail[74]);
        rail[73].setCity(rapidCity);
        rail[86].setCity(rapidCity);
        rail[74].setCity(rapidCity);
        bismark.setRails(rail[96],rail[107],rail[97]);
        rail[96].setCity(bismark);
        rail[107].setCity(bismark);
        rail[97].setCity(bismark);
        omaha.setRails(rail[117],rail[125],rail[118]);
        rail[117].setCity(omaha);
        rail[125].setCity(omaha);
        rail[118].setCity(omaha);
        minneapolis.setRails(rail[135],rail[124],rail[136]);
        rail[135].setCity(minneapolis);
        rail[124].setCity(minneapolis);
        rail[136].setCity(minneapolis);
        chicago.setRails(rail[167],rail[161],rail[168]);
        rail[167].setCity(chicago);
        rail[161].setCity(chicago);
        rail[168].setCity(chicago);
        indianapolis.setRails(rail[169],rail[162],rail[170]);
        rail[169].setCity(indianapolis);
        rail[162].setCity(indianapolis);
        rail[170].setCity(indianapolis);
        columbus.setRails(rail[184],rail[178],rail[185]);
        rail[184].setCity(columbus);
        rail[178].setCity(columbus);
        rail[185].setCity(columbus);
        washingtonDC.setRails(rail[196],rail[205],rail[197]);
        rail[196].setCity(washingtonDC);
        rail[205].setCity(washingtonDC);
        rail[197].setCity(washingtonDC);
        albany.setRails(rail[203],rail[204],rail[195]);
        rail[203].setCity(albany);
        rail[204].setCity(albany);
        rail[195].setCity(albany);
        boston.setRails(rail[212],rail[213],rail[207]);
        rail[212].setCity(boston);
        rail[213].setCity(boston);
        rail[207].setCity(boston);
        newYork.setRails(rail[208],null,rail[209]);
        rail[208].setCity(newYork);
        rail[209].setCity(newYork);
        sanFrancisco.setRails(rail[18],null,rail[27]);
        rail[18].setCity(sanFrancisco);
        rail[27].setCity(sanFrancisco);
        reno.setRails(rail[21],rail[19],rail[28]);
        rail[21].setCity(reno);
        rail[19].setCity(reno);
        rail[28].setCity(reno);
        losAngeles.setRails(rail[44],rail[47],rail[55]);
        rail[44].setCity(losAngeles);
        rail[47].setCity(losAngeles);
        rail[55].setCity(losAngeles);
        lasVegas.setRails(rail[45],rail[40],rail[39]);
        rail[45].setCity(lasVegas);
        rail[40].setCity(lasVegas);
        rail[39].setCity(lasVegas);
        saltLakeCity.setRails(rail[42],rail[38],rail[46]);
        rail[42].setCity(saltLakeCity);
        rail[38].setCity(saltLakeCity);
        rail[46].setCity(saltLakeCity);
        flagstaff.setRails(rail[50],rail[60],rail[59]);
        rail[50].setCity(flagstaff);
        rail[60].setCity(flagstaff);
        rail[59].setCity(flagstaff);
        denver.setRails(rail[87],rail[65],rail[89]);
        rail[87].setCity(denver);
        rail[65].setCity(denver);
        rail[89].setCity(denver);
        santaFe.setRails(rail[76],rail[77],rail[78]);
        rail[76].setCity(santaFe);
        rail[77].setCity(santaFe);
        rail[78].setCity(santaFe);
        elPaso.setRails(rail[94],rail[105],null);
        rail[94].setCity(elPaso);
        rail[105].setCity(elPaso);
        amarillo.setRails(rail[103],rail[92],rail[104]);
        rail[103].setCity(amarillo);
        rail[92].setCity(amarillo);
        rail[104].setCity(amarillo);
        wichita.setRails(rail[119],rail[127],rail[126]);
        rail[119].setCity(wichita);
        rail[127].setCity(wichita);
        rail[126].setCity(wichita);
        dallas.setRails(rail[120],rail[111],rail[121]);
        rail[120].setCity(dallas);
        rail[111].setCity(dallas);
        rail[121].setCity(dallas);
        sanAntonio.setRails(rail[130],rail[122],rail[132]);
        rail[130].setCity(sanAntonio);
        rail[122].setCity(sanAntonio);
        rail[132].setCity(sanAntonio);
        shreveport.setRails(rail[142],rail[150],rail[143]);
        rail[142].setCity(shreveport);
        rail[150].setCity(shreveport);
        rail[143].setCity(shreveport);
        houston.setRails(rail[144],null,null);
        rail[144].setCity(houston);
        littleRock.setRails(rail[140],rail[149],rail[141]);
        rail[140].setCity(littleRock);
        rail[149].setCity(littleRock);
        rail[141].setCity(littleRock);
        stLouis.setRails(rail[155],rail[148],rail[156]);
        rail[155].setCity(stLouis);
        rail[148].setCity(stLouis);
        rail[156].setCity(stLouis);
        jackson.setRails(rail[158],rail[164],rail[159]);
        rail[158].setCity(jackson);
        rail[164].setCity(jackson);
        rail[159].setCity(jackson);
        newOrleans.setRails(rail[175],rail[166],null);
        rail[175].setCity(newOrleans);
        rail[166].setCity(newOrleans);
        nashville.setRails(rail[171],rail[163],rail[172]);
        rail[171].setCity(nashville);
        rail[163].setCity(nashville);
        rail[172].setCity(nashville);
        lexington.setRails(rail[186],rail[179],rail[187]);
        rail[186].setCity(lexington);
        rail[179].setCity(lexington);
        rail[187].setCity(lexington);
        atlanta.setRails(rail[188],rail[180],rail[189]);
        rail[188].setCity(atlanta);
        rail[180].setCity(atlanta);
        rail[189].setCity(atlanta);
        pensacola.setRails(rail[190],rail[181],null);
        rail[190].setCity(pensacola);
        rail[181].setCity(pensacola);
        raleigh.setRails(rail[198],rail[206],rail[199]);
        rail[198].setCity(raleigh);
        rail[206].setCity(raleigh);
        rail[199].setCity(raleigh);
        columbia.setRails(rail[200],null,rail[201]);
        rail[200].setCity(columbia);
        rail[201].setCity(columbia);
        jacksonville.setRails(rail[202],null,null);
        rail[202].setCity(jacksonville);
        //</editor-fold>
        
        //set intersections
        //iterates downward and rightward
        //<editor-fold>
        intersection = new Intersection[149];
	intersection[34]=new Intersection(intersection34,this,singleQHexes);
	intersection[35]=new Intersection(flagstaff, intersection35,this,singleQHexes);
	flagstaff.setIntersection(intersection[35]);
	intersection[36]=new Intersection(intersection36,this,singleQHexes);
	intersection[37]=new Intersection(intersection37,this,singleQHexes);
	intersection[38]=new Intersection(intersection38,this,singleQHexes);
	intersection[39]=new Intersection(billings,intersection39,this,singleQHexes);
	billings.setIntersection(intersection[39]);
	intersection[40]=new Intersection(intersection40,this,singleQHexes);
	intersection[41]=new Intersection(casper, intersection41,this,singleQHexes);
	casper.setIntersection(intersection[41]);
	intersection[42]=new Intersection(intersection42,this,singleQHexes);
	intersection[43]=new Intersection(intersection43,this,singleQHexes);
	intersection[44]=new Intersection(intersection44,this,singleQHexes);
	intersection[45]=new Intersection(intersection45,this,singleQHexes);
	intersection[46]=new Intersection(intersection46,this,singleQHexes);
	intersection[47]=new Intersection(intersection47,this,singleQHexes);
	intersection[48]=new Intersection(intersection48,this,singleQHexes);
	intersection[49]=new Intersection(intersection49,this,singleQHexes);
	intersection[50]=new Intersection(intersection50,this,singleQHexes);
	intersection[51]=new Intersection(rapidCity, intersection51,this,singleQHexes);
	rapidCity.setIntersection(intersection[51]);
	intersection[52]=new Intersection(intersection52,this,singleQHexes);
	intersection[53]=new Intersection(intersection53,this,singleQHexes);
	intersection[54]=new Intersection(denver, intersection54,this,singleQHexes);
	denver.setIntersection(intersection[54]);
	intersection[55]=new Intersection(intersection55,this,singleQHexes);
	intersection[56]=new Intersection(santaFe, intersection56,this,singleQHexes);
	santaFe.setIntersection(intersection[56]);
	intersection[57]=new Intersection(intersection57,this,singleQHexes);
	intersection[58]=new Intersection(intersection58,this,singleQHexes);
	intersection[59]=new Intersection(elPaso, intersection59,this,singleQHexes);
	elPaso.setIntersection(intersection[59]);
	intersection[60]=new Intersection(intersection60,this,singleQHexes);
	intersection[61]=new Intersection(intersection61,this,singleQHexes);
	intersection[62]=new Intersection(bismark, intersection62,this,singleQHexes);
	bismark.setIntersection(intersection[62]);
	intersection[63]=new Intersection(intersection63,this,singleQHexes);
	intersection[64]=new Intersection(intersection64,this,singleQHexes);
	intersection[65]=new Intersection(intersection65,this,singleQHexes);
	intersection[66]=new Intersection(intersection66,this,singleQHexes);
	intersection[67]=new Intersection(intersection67,this,singleQHexes);
	intersection[68]=new Intersection(intersection68,this,singleQHexes);
	intersection[69]=new Intersection(amarillo, intersection69,this,singleQHexes);
	amarillo.setIntersection(intersection[69]);
	intersection[70]=new Intersection(intersection70,this,singleQHexes);
	intersection[71]=new Intersection(intersection71,this,singleQHexes);
	intersection[72]=new Intersection(intersection72,this,singleQHexes);
	intersection[73]=new Intersection(intersection73,this,singleQHexes);
	intersection[74]=new Intersection(intersection74,this,singleQHexes);
	intersection[75]=new Intersection(intersection75,this,singleQHexes);
	intersection[76]=new Intersection(intersection76,this,singleQHexes);
	intersection[77]=new Intersection(intersection77,this,singleQHexes);
	intersection[78]=new Intersection(omaha, intersection78,this,singleQHexes);
	omaha.setIntersection(intersection[78]);
	intersection[79]=new Intersection(intersection79,this,singleQHexes);
	intersection[80]=new Intersection(wichita, intersection80,this,singleQHexes);
	wichita.setIntersection(intersection[80]);
	intersection[81]=new Intersection(intersection81,this,singleQHexes);
	intersection[82]=new Intersection(intersection82,this,singleQHexes);
	intersection[83]=new Intersection(dallas, intersection83,this,singleQHexes);
	dallas.setIntersection(intersection[83]);
	intersection[84]=new Intersection(intersection84,this,singleQHexes);
	intersection[85]=new Intersection(sanAntonio, intersection85,this,singleQHexes);
	sanAntonio.setIntersection(intersection[85]);
	intersection[86]=new Intersection(intersection86,this,singleQHexes);
	intersection[87]=new Intersection(intersection87,this,singleQHexes);
	intersection[88]=new Intersection(intersection88,this,singleQHexes);
	intersection[89]=new Intersection(intersection89,this,singleQHexes);
	intersection[90]=new Intersection(minneapolis, intersection90,this,singleQHexes);
	minneapolis.setIntersection(intersection[90]);
	intersection[91]=new Intersection(intersection91,this,singleQHexes);
	intersection[92]=new Intersection(intersection92,this,singleQHexes);
	intersection[93]=new Intersection(intersection93,this,singleQHexes);
	intersection[94]=new Intersection(intersection94,this,singleQHexes);
	intersection[95]=new Intersection(littleRock, intersection95,this,singleQHexes);
	littleRock.setIntersection(intersection[95]);
	intersection[96]=new Intersection(intersection96,this,singleQHexes);
	intersection[97]=new Intersection(shreveport, intersection97,this,singleQHexes);
	shreveport.setIntersection(intersection[97]);
	intersection[98]=new Intersection(intersection98,this,singleQHexes);
	intersection[99]=new Intersection(houston, intersection99,this,singleQHexes);
	houston.setIntersection(intersection[99]);
	intersection[100]=new Intersection(intersection100,this,singleQHexes);
	intersection[101]=new Intersection(intersection101,this,singleQHexes);
	intersection[102]=new Intersection(intersection102,this,singleQHexes);
	intersection[103]=new Intersection(intersection103,this,singleQHexes);
	intersection[104]=new Intersection(intersection104,this,singleQHexes);
	intersection[105]=new Intersection(stLouis, intersection105,this,singleQHexes);
	stLouis.setIntersection(intersection[105]);
	intersection[106]=new Intersection(intersection106,this,singleQHexes);
	intersection[107]=new Intersection(intersection107,this,singleQHexes);
	intersection[108]=new Intersection(jackson, intersection108,this,singleQHexes);
	jackson.setIntersection(intersection[108]);
	intersection[109]=new Intersection(intersection109,this,singleQHexes);
	intersection[110]=new Intersection(intersection110,this,singleQHexes);
	intersection[111]=new Intersection(intersection111,this,singleQHexes);
	intersection[112]=new Intersection(intersection112,this,singleQHexes);
	intersection[113]=new Intersection(chicago, intersection113,this,singleQHexes);
	chicago.setIntersection(intersection[113]);
	intersection[114]=new Intersection(intersection114,this,singleQHexes);
	intersection[115]=new Intersection(indianapolis, intersection115,this,singleQHexes);
	indianapolis.setIntersection(intersection[115]);
	intersection[116]=new Intersection(intersection116,this,singleQHexes);
	intersection[117]=new Intersection(nashville, intersection117,this,singleQHexes);
	nashville.setIntersection(intersection[117]);
	intersection[118]=new Intersection(intersection118,this,singleQHexes);
	intersection[119]=new Intersection(intersection119,this,singleQHexes);
	intersection[120]=new Intersection(intersection120,this,singleQHexes);
	intersection[121]=new Intersection(newOrleans, intersection121,this,singleQHexes);
	newOrleans.setIntersection(intersection[121]);
	intersection[122]=new Intersection(intersection122,this,singleQHexes);
	intersection[123]=new Intersection(intersection123,this,singleQHexes);
	intersection[124]=new Intersection(columbus, intersection124,this,singleQHexes);
	columbus.setIntersection(intersection[124]);
	intersection[125]=new Intersection(intersection125,this,singleQHexes);
	intersection[126]=new Intersection(lexington, intersection126,this,singleQHexes);
	lexington.setIntersection(intersection[126]);
	intersection[127]=new Intersection(intersection127,this,singleQHexes);
	intersection[128]=new Intersection(atlanta, intersection128,this,singleQHexes);
	atlanta.setIntersection(intersection[128]);
	intersection[129]=new Intersection(intersection129,this,singleQHexes);
	intersection[130]=new Intersection(pensacola, intersection130,this,singleQHexes);
	pensacola.setIntersection(intersection[130]);
	intersection[131]=new Intersection(albany, intersection131,this,singleQHexes);
	albany.setIntersection(intersection[131]);
	intersection[132]=new Intersection(intersection132,this,singleQHexes);
	intersection[133]=new Intersection(washingtonDC, intersection133,this,singleQHexes);
	washingtonDC.setIntersection(intersection[133]);
	intersection[134]=new Intersection(intersection134,this,singleQHexes);
	intersection[135]=new Intersection(raleigh, intersection135,this,singleQHexes);
	raleigh.setIntersection(intersection[135]);
	intersection[136]=new Intersection(intersection136,this,singleQHexes);
	intersection[137]=new Intersection(columbia, intersection137,this,singleQHexes);
	columbia.setIntersection(intersection[137]);
	intersection[138]=new Intersection(intersection138,this,singleQHexes);
	intersection[139]=new Intersection(jacksonville, intersection139,this,singleQHexes);
	jacksonville.setIntersection(intersection[139]);
	intersection[140]=new Intersection(intersection140,this,singleQHexes);
	intersection[141]=new Intersection(intersection141,this,singleQHexes);
	intersection[142]=new Intersection(boston, intersection142,this,singleQHexes);
	boston.setIntersection(intersection[142]);
	intersection[143]=new Intersection(intersection143,this,singleQHexes);
	intersection[144]=new Intersection(newYork, intersection144,this,singleQHexes);
	newYork.setIntersection(intersection[144]);
	intersection[145]=new Intersection(intersection145,this,singleQHexes);
	intersection[146]=new Intersection(intersection146,this,singleQHexes);
	intersection[147]=new Intersection(intersection147,this,singleQHexes);
	intersection[148]=new Intersection(intersection148,this,singleQHexes);

        intersection[0]=new Intersection(seattle, intersection0,this,singleQHexes);
        seattle.setIntersection(intersection[0]);
        intersection[1]=new Intersection(intersection1,this,singleQHexes);
        intersection[0].setDownIntersection(intersection[1]);
        intersection[0].setUpIntersection(null);
        intersection[0].setHorIntersection(null);
        //intersection 0 done
	intersection[2]=new Intersection(portland, intersection2,this,singleQHexes);
	portland.setIntersection(intersection[2]);
	intersection[9]=new Intersection(intersection9,this,singleQHexes);
        intersection[1].setUpIntersection(intersection[0]);
	intersection[1].setDownIntersection(intersection[2]);
	intersection[1].setHorIntersection(intersection[9]);
	//intersection 1 done
	intersection[3]=new Intersection(intersection3,this,singleQHexes);
	intersection[2].setUpIntersection(intersection[1]);
	intersection[2].setHorIntersection(null);
	intersection[2].setDownIntersection(intersection[3]);
	//intersection 2 done
	intersection[4]=new Intersection(intersection4,this,singleQHexes);
	intersection[11]=new Intersection(intersection11,this,singleQHexes);
	intersection[3].setUpIntersection(intersection[2]);
	intersection[3].setDownIntersection(intersection[4]);
	intersection[3].setHorIntersection(intersection[11]);
	//intersection 3 done
	intersection[5]=new Intersection(intersection5,this,singleQHexes);
	intersection[4].setUpIntersection(intersection[3]);
	intersection[4].setHorIntersection(null);
	intersection[4].setDownIntersection(intersection[5]);
	//intersection 4 done
	intersection[6]=new Intersection(sanFrancisco, intersection6,this,singleQHexes);
	sanFrancisco.setIntersection(intersection[6]);
	intersection[13]=new Intersection(intersection13,this,singleQHexes);
	intersection[5].setUpIntersection(intersection[4]);
	intersection[5].setDownIntersection(intersection[6]);
	intersection[5].setHorIntersection(intersection[13]);
	//intersection 5 done
	intersection[7]=new Intersection(intersection7,this,singleQHexes);
	intersection[6].setUpIntersection(intersection[5]);
	intersection[6].setDownIntersection(intersection[7]);
	intersection[6].setHorIntersection(null);
	//intersection 6 done
	intersection[15]=new Intersection(intersection15,this,singleQHexes);
	intersection[7].setUpIntersection(intersection[6]);
	intersection[7].setHorIntersection(intersection[15]);
	intersection[7].setDownIntersection(null);
	//intersection 7 done
	intersection[8]=new Intersection(intersection8,this,singleQHexes);
	intersection[17]=new Intersection(spokane, intersection17,this,singleQHexes);
	spokane.setIntersection(intersection[17]);
	intersection[8].setUpIntersection(null);
	intersection[8].setHorIntersection(intersection[17]);
	intersection[8].setDownIntersection(intersection[9]);
	//intersection 8 done
	intersection[10]=new Intersection(intersection10,this,singleQHexes);
	intersection[9].setUpIntersection(intersection[8]);
	intersection[9].setHorIntersection(intersection[1]);
	intersection[9].setDownIntersection(intersection[10]);
	//intersection 9 done
	intersection[19]=new Intersection(boise, intersection19,this,singleQHexes);
	boise.setIntersection(intersection[19]);
	intersection[10].setUpIntersection(intersection[9]);
	intersection[10].setHorIntersection(intersection[19]);
	intersection[10].setDownIntersection(intersection[11]);
	//intersection 10 done
	intersection[12]=new Intersection(intersection12,this,singleQHexes);
	intersection[11].setUpIntersection(intersection[10]);
	intersection[11].setHorIntersection(intersection[3]);
	intersection[11].setDownIntersection(intersection[12]);
	//intersection 11 done
	intersection[21]=new Intersection(reno, intersection21,this,singleQHexes);
	reno.setIntersection(intersection[21]);
	intersection[12].setUpIntersection(intersection[11]);
	intersection[12].setHorIntersection(intersection[21]);
	intersection[12].setDownIntersection(intersection[13]);
	//intersection 12 done
	intersection[14]=new Intersection(intersection14,this,singleQHexes);
	intersection[13].setUpIntersection(intersection[12]);
	intersection[13].setHorIntersection(intersection[5]);
	intersection[13].setDownIntersection(intersection[14]);
	//intersection 13 done
	intersection[15]=new Intersection(intersection15,this,singleQHexes);
	intersection[23]=new Intersection(intersection23,this,singleQHexes);
	intersection[14].setUpIntersection(intersection[13]);
	intersection[14].setHorIntersection(intersection[23]);
	intersection[14].setDownIntersection(intersection[15]);
	//intersection 14 done
	intersection[16]=new Intersection(losAngeles, intersection16,this,singleQHexes);
	losAngeles.setIntersection(intersection[16]);
	intersection[15].setUpIntersection(intersection[14]);
	intersection[15].setHorIntersection(intersection[7]);
	intersection[15].setDownIntersection(intersection[16]);
	//intersection 15 done
	intersection[25]=new Intersection(intersection25,this,singleQHexes);
	intersection[16].setUpIntersection(intersection[15]);
	intersection[16].setHorIntersection(intersection[25]);
	intersection[16].setDownIntersection(null);
	//intersection 16 done
	intersection[18]=new Intersection(intersection18,this,singleQHexes);
	intersection[17].setUpIntersection(null);
	intersection[17].setHorIntersection(intersection[8]);
	intersection[17].setDownIntersection(intersection[18]);
	//intersection 17 done
	intersection[28]=new Intersection(helena, intersection28,this,singleQHexes);
	helena.setIntersection(intersection[28]);
	intersection[18].setUpIntersection(intersection[17]);
	intersection[18].setHorIntersection(intersection[28]);
	intersection[18].setDownIntersection(intersection[19]);
	//intersection 18 done
	intersection[20]=new Intersection(intersection20,this,singleQHexes);
	intersection[19].setUpIntersection(intersection[18]);
	intersection[19].setHorIntersection(intersection[10]);
	intersection[19].setDownIntersection(intersection[20]);
	//intersection 19 done
	intersection[30]=new Intersection(idahoFalls,intersection30,this,singleQHexes);
	idahoFalls.setIntersection(intersection[30]);
	intersection[20].setUpIntersection(intersection[19]);
	intersection[20].setHorIntersection(intersection[30]);
	intersection[20].setDownIntersection(intersection[21]);
	//intersection 20 done
	intersection[22]=new Intersection(intersection22,this,singleQHexes);
	intersection[21].setUpIntersection(intersection[20]);
	intersection[21].setHorIntersection(intersection[12]);
	intersection[21].setDownIntersection(intersection[22]);
	//intersection 21 done
	intersection[32]=new Intersection(saltLakeCity,intersection32,this,singleQHexes);
	saltLakeCity.setIntersection(intersection[32]);
	intersection[22].setUpIntersection(intersection[21]);
	intersection[22].setHorIntersection(intersection[32]);
	intersection[22].setDownIntersection(intersection[23]);
	//intersection 22 done
	intersection[24]=new Intersection(lasVegas,intersection24,this,singleQHexes);
	lasVegas.setIntersection(intersection[24]);
	intersection[23].setUpIntersection(intersection[22]);
	intersection[23].setHorIntersection(intersection[14]);
	intersection[23].setDownIntersection(intersection[24]);
	//intersection 23 done
	intersection[24].setUpIntersection(intersection[23]);
	intersection[24].setHorIntersection(intersection[34]);
	intersection[24].setDownIntersection(intersection[25]);
	//intersection 24 done
	intersection[26]=new Intersection(intersection26,this,singleQHexes);
	intersection[25].setUpIntersection(intersection[24]);
	intersection[25].setHorIntersection(intersection[16]);
	intersection[25].setDownIntersection(intersection[26]);
	//intersection 25 done	
	intersection[26].setUpIntersection(intersection[25]);
	intersection[26].setHorIntersection(intersection[36]);
	intersection[26].setDownIntersection(null);
	//intersection 26 done	
        intersection[27]=new Intersection(intersection27,this,singleQHexes);
	intersection[27].setUpIntersection(null);
	intersection[27].setHorIntersection(intersection[38]);
	intersection[27].setDownIntersection(intersection[28]);
	//intersection 27 done
	intersection[29]=new Intersection(intersection29,this,singleQHexes);
	intersection[28].setUpIntersection(intersection[27]);
	intersection[28].setHorIntersection(intersection[18]);
	intersection[28].setDownIntersection(intersection[29]);
	//intersection 28 done
	intersection[29].setUpIntersection(intersection[28]);
	intersection[29].setHorIntersection(intersection[40]);
	intersection[29].setDownIntersection(intersection[30]);
	//intersection 29 done
	intersection[31]=new Intersection(intersection31,this,singleQHexes);
	intersection[30].setUpIntersection(intersection[29]);
	intersection[30].setHorIntersection(intersection[20]);
	intersection[30].setDownIntersection(intersection[31]);
	//intersection 30 done
	intersection[31].setUpIntersection(intersection[30]);
	intersection[31].setHorIntersection(intersection[42]);
	intersection[31].setDownIntersection(intersection[32]);
	//intersection 31 done
	intersection[33]=new Intersection(intersection33,this,singleQHexes);
	intersection[32].setUpIntersection(intersection[31]);
	intersection[32].setHorIntersection(intersection[22]);
	intersection[32].setDownIntersection(intersection[33]);
	//intersection 32 done	
	intersection[33].setUpIntersection(intersection[32]);
	intersection[33].setHorIntersection(intersection[44]);
	intersection[33].setDownIntersection(intersection[34]);
	//intersection 33 done
	intersection[34].setUpIntersection(intersection[33]);
	intersection[34].setHorIntersection(intersection[24]);
	intersection[34].setDownIntersection(intersection[35]);
	//intersection 34 done	
	intersection[35].setUpIntersection(intersection[34]);
	intersection[35].setHorIntersection(intersection[46]);
	intersection[35].setDownIntersection(intersection[36]);
	//intersection 35 done
	intersection[36].setUpIntersection(intersection[35]);
	intersection[36].setHorIntersection(intersection[26]);
	intersection[36].setDownIntersection(intersection[37]);
	//intersection 36 done
	intersection[37].setUpIntersection(intersection[36]);
	intersection[37].setHorIntersection(intersection[48]);
	intersection[37].setDownIntersection(null);
	//intersection 37 done
	intersection[38].setUpIntersection(null);
	intersection[38].setHorIntersection(intersection[27]);
	intersection[38].setDownIntersection(intersection[39]);
	//intersection 38 done
	intersection[39].setUpIntersection(intersection[38]);
	intersection[39].setHorIntersection(intersection[50]);
	intersection[39].setDownIntersection(intersection[40]);
	//intersection 39 done
	intersection[40].setUpIntersection(intersection[39]);
	intersection[40].setHorIntersection(intersection[29]);
	intersection[40].setDownIntersection(intersection[41]);
	//intersection 40 done
	intersection[41].setUpIntersection(intersection[40]);
	intersection[41].setHorIntersection(intersection[52]);
	intersection[41].setDownIntersection(intersection[42]);
	//intersection 41 done
	intersection[42].setUpIntersection(intersection[41]);
	intersection[42].setHorIntersection(intersection[31]);
	intersection[42].setDownIntersection(intersection[43]);
	//intersection 42 done
	intersection[43].setUpIntersection(intersection[42]);
	intersection[43].setHorIntersection(intersection[54]);
	intersection[43].setDownIntersection(intersection[44]);
	//intersection 43 done
	intersection[44].setUpIntersection(intersection[43]);
	intersection[44].setHorIntersection(intersection[33]);
	intersection[44].setDownIntersection(intersection[45]);
	//intersection 44 done
	intersection[45].setUpIntersection(intersection[44]);
	intersection[45].setHorIntersection(intersection[56]);
	intersection[45].setDownIntersection(intersection[46]);
	//intersection 45 done
	intersection[46].setUpIntersection(intersection[45]);
	intersection[46].setHorIntersection(intersection[35]);
	intersection[46].setDownIntersection(intersection[47]);
	//intersection 46 done
	intersection[47].setUpIntersection(intersection[46]);
	intersection[47].setHorIntersection(intersection[58]);
	intersection[47].setDownIntersection(intersection[48]);
	//intersection 47 done
	intersection[48].setUpIntersection(intersection[47]);
	intersection[48].setHorIntersection(intersection[37]);
	intersection[48].setDownIntersection(null);
	//intersection 48 done
	intersection[49].setUpIntersection(null);
	intersection[49].setHorIntersection(intersection[61]);
	intersection[49].setDownIntersection(intersection[50]);
	//intersection  done
	intersection[50].setUpIntersection(intersection[49]);
	intersection[50].setHorIntersection(intersection[39]);
	intersection[50].setDownIntersection(intersection[51]);
	//intersection  done
	intersection[51].setUpIntersection(intersection[50]);
	intersection[51].setHorIntersection(intersection[63]);
	intersection[51].setDownIntersection(intersection[52]);
	//intersection  done
	intersection[52].setUpIntersection(intersection[51]);
	intersection[52].setHorIntersection(intersection[41]);
	intersection[52].setDownIntersection(intersection[53]);
	//intersection  done
	intersection[53].setUpIntersection(intersection[52]);
	intersection[53].setHorIntersection(intersection[65]);
	intersection[53].setDownIntersection(intersection[54]);
	//intersection  done
	intersection[54].setUpIntersection(intersection[53]);
	intersection[54].setHorIntersection(intersection[43]);
	intersection[54].setDownIntersection(intersection[55]);
	//intersection  done
	intersection[55].setUpIntersection(intersection[54]);
	intersection[55].setHorIntersection(intersection[67]);
	intersection[55].setDownIntersection(intersection[56]);
	//intersection  done
	intersection[56].setUpIntersection(intersection[55]);
	intersection[56].setHorIntersection(intersection[45]);
	intersection[56].setDownIntersection(intersection[57]);
	//intersection  done
	intersection[57].setUpIntersection(intersection[56]);
	intersection[57].setHorIntersection(intersection[69]);
	intersection[57].setDownIntersection(intersection[58]);
	//intersection  done
	intersection[58].setUpIntersection(intersection[57]);
	intersection[58].setHorIntersection(intersection[47]);
	intersection[58].setDownIntersection(intersection[59]);
	//intersection  done
	intersection[59].setUpIntersection(intersection[58]);
	intersection[59].setHorIntersection(intersection[71]);
	intersection[59].setDownIntersection(null);
	//intersection  done
	intersection[60].setUpIntersection(null);
	intersection[60].setHorIntersection(intersection[73]);
	intersection[60].setDownIntersection(intersection[61]);
	//intersection  done
	intersection[61].setUpIntersection(intersection[60]);
	intersection[61].setHorIntersection(intersection[49]);
	intersection[61].setDownIntersection(intersection[62]);
	//intersection  done
	intersection[62].setUpIntersection(intersection[61]);
	intersection[62].setHorIntersection(intersection[75]);
	intersection[62].setDownIntersection(intersection[63]);
	//intersection  done
	intersection[63].setUpIntersection(intersection[62]);
	intersection[63].setHorIntersection(intersection[51]);
	intersection[63].setDownIntersection(intersection[64]);
	//intersection  done
	intersection[64].setUpIntersection(intersection[63]);
	intersection[64].setHorIntersection(intersection[77]);
	intersection[64].setDownIntersection(intersection[65]);
	//intersection  done
	intersection[65].setUpIntersection(intersection[64]);
	intersection[65].setHorIntersection(intersection[53]);
	intersection[65].setDownIntersection(intersection[66]);
	//intersection  done
	intersection[66].setUpIntersection(intersection[65]);
	intersection[66].setHorIntersection(intersection[79]);
	intersection[66].setDownIntersection(intersection[67]);
	//intersection  done
	intersection[67].setUpIntersection(intersection[66]);
	intersection[67].setHorIntersection(intersection[55]);
	intersection[67].setDownIntersection(intersection[68]);
	//intersection  done
	intersection[68].setUpIntersection(intersection[67]);
	intersection[68].setHorIntersection(intersection[81]);
	intersection[68].setDownIntersection(intersection[69]);
	//intersection  done
	intersection[69].setUpIntersection(intersection[68]);
	intersection[69].setHorIntersection(intersection[57]);
	intersection[69].setDownIntersection(intersection[70]);
	//intersection  done
	intersection[70].setUpIntersection(intersection[69]);
	intersection[70].setHorIntersection(intersection[83]);
	intersection[70].setDownIntersection(intersection[71]);
	//intersection  done
	intersection[71].setUpIntersection(intersection[70]);
	intersection[71].setHorIntersection(intersection[59]);
	intersection[71].setDownIntersection(intersection[72]);
	//intersection  done
	intersection[72].setUpIntersection(intersection[71]);
	intersection[72].setHorIntersection(intersection[85]);
	intersection[72].setDownIntersection(null);
	//intersection  done
	intersection[73].setUpIntersection(null);
	intersection[73].setHorIntersection(intersection[60]);
	intersection[73].setDownIntersection(intersection[74]);
	//intersection  done
	intersection[74].setUpIntersection(intersection[73]);
	intersection[74].setHorIntersection(intersection[88]);
	intersection[74].setDownIntersection(intersection[75]);
	//intersection  done
	intersection[75].setUpIntersection(intersection[74]);
	intersection[75].setHorIntersection(intersection[62]);
	intersection[75].setDownIntersection(intersection[76]);
	//intersection  done
	intersection[76].setUpIntersection(intersection[75]);
	intersection[76].setHorIntersection(intersection[90]);
	intersection[76].setDownIntersection(intersection[77]);
	//intersection  done
	intersection[77].setUpIntersection(intersection[76]);
	intersection[77].setHorIntersection(intersection[64]);
	intersection[77].setDownIntersection(intersection[78]);
	//intersection  done
	intersection[78].setUpIntersection(intersection[77]);
	intersection[78].setHorIntersection(intersection[92]);
	intersection[78].setDownIntersection(intersection[79]);
	//intersection  done
	intersection[79].setUpIntersection(intersection[78]);
	intersection[79].setHorIntersection(intersection[66]);
	intersection[79].setDownIntersection(intersection[80]);
	//intersection  done
	intersection[80].setUpIntersection(intersection[79]);
	intersection[80].setHorIntersection(intersection[94]);
	intersection[80].setDownIntersection(intersection[81]);
	//intersection  done
	intersection[81].setUpIntersection(intersection[80]);
	intersection[81].setHorIntersection(intersection[68]);
	intersection[81].setDownIntersection(intersection[82]);
	//intersection  done
	intersection[82].setUpIntersection(intersection[81]);
	intersection[82].setHorIntersection(intersection[96]);
	intersection[82].setDownIntersection(intersection[83]);
	//intersection  done
	intersection[83].setUpIntersection(intersection[82]);
	intersection[83].setHorIntersection(intersection[70]);
	intersection[83].setDownIntersection(intersection[84]);
	//intersection  done
	intersection[84].setUpIntersection(intersection[83]);
	intersection[84].setHorIntersection(intersection[98]);
	intersection[84].setDownIntersection(intersection[85]);
	//intersection  done
	intersection[85].setUpIntersection(intersection[84]);
	intersection[85].setHorIntersection(intersection[72]);
	intersection[85].setDownIntersection(intersection[86]);
	//intersection  done
	intersection[86].setUpIntersection(intersection[85]);
	intersection[86].setHorIntersection(null);
	intersection[86].setDownIntersection(null);
	//intersection  done
	intersection[87].setUpIntersection(null);
	intersection[87].setHorIntersection(null);
	intersection[87].setDownIntersection(intersection[88]);
	//intersection  done
	intersection[88].setUpIntersection(intersection[87]);
	intersection[88].setHorIntersection(intersection[74]);
	intersection[88].setDownIntersection(intersection[89]);
	//intersection  done
	intersection[89].setUpIntersection(intersection[88]);
	intersection[89].setHorIntersection(intersection[101]);
	intersection[89].setDownIntersection(intersection[90]);
	//intersection  done
	intersection[90].setUpIntersection(intersection[89]);
	intersection[90].setHorIntersection(intersection[76]);
	intersection[90].setDownIntersection(intersection[91]);
	//intersection  done
	intersection[91].setUpIntersection(intersection[90]);
	intersection[91].setHorIntersection(intersection[103]);
	intersection[91].setDownIntersection(intersection[92]);
	//intersection  done
	intersection[92].setUpIntersection(intersection[91]);
	intersection[92].setHorIntersection(intersection[78]);
	intersection[92].setDownIntersection(intersection[93]);
	//intersection  done
	intersection[93].setUpIntersection(intersection[92]);
	intersection[93].setHorIntersection(intersection[105]);
	intersection[93].setDownIntersection(intersection[94]);
	//intersection  done
	intersection[94].setUpIntersection(intersection[93]);
	intersection[94].setHorIntersection(intersection[80]);
	intersection[94].setDownIntersection(intersection[95]);
	//intersection  done
	intersection[95].setUpIntersection(intersection[94]);
	intersection[95].setHorIntersection(intersection[107]);
	intersection[95].setDownIntersection(intersection[96]);
	//intersection  done
	intersection[96].setUpIntersection(intersection[95]);
	intersection[96].setHorIntersection(intersection[82]);
	intersection[96].setDownIntersection(intersection[97]);
	//intersection  done
	intersection[97].setUpIntersection(intersection[96]);
	intersection[97].setHorIntersection(intersection[109]);
	intersection[97].setDownIntersection(intersection[98]);
	//intersection  done
	intersection[98].setUpIntersection(intersection[97]);
	intersection[98].setHorIntersection(intersection[84]);
	intersection[98].setDownIntersection(intersection[99]);
	//intersection  done
	intersection[99].setUpIntersection(intersection[98]);
	intersection[99].setHorIntersection(null);
	intersection[99].setDownIntersection(null);
	//intersection  done
	intersection[100].setUpIntersection(null);
	intersection[100].setHorIntersection(null);
	intersection[100].setDownIntersection(intersection[101]);
	//intersection  done
	intersection[101].setUpIntersection(intersection[100]);
	intersection[101].setHorIntersection(intersection[89]);
	intersection[101].setDownIntersection(intersection[102]);
	//intersection  done
	intersection[102].setUpIntersection(intersection[101]);
	intersection[102].setHorIntersection(intersection[113]);
	intersection[102].setDownIntersection(intersection[103]);
	//intersection  done
	intersection[103].setUpIntersection(intersection[102]);
	intersection[103].setHorIntersection(intersection[91]);
	intersection[103].setDownIntersection(intersection[104]);
	//intersection  done
	intersection[104].setUpIntersection(intersection[103]);
	intersection[104].setHorIntersection(intersection[115]);
	intersection[104].setDownIntersection(intersection[105]);
	//intersection  done
	intersection[105].setUpIntersection(intersection[104]);
	intersection[105].setHorIntersection(intersection[93]);
	intersection[105].setDownIntersection(intersection[106]);
	//intersection  done
	intersection[106].setUpIntersection(intersection[105]);
	intersection[106].setHorIntersection(intersection[117]);
	intersection[106].setDownIntersection(intersection[107]);
	//intersection  done
	intersection[107].setUpIntersection(intersection[106]);
	intersection[107].setHorIntersection(intersection[95]);
	intersection[107].setDownIntersection(intersection[108]);
	//intersection  done
	intersection[108].setUpIntersection(intersection[107]);
	intersection[108].setHorIntersection(intersection[119]);
	intersection[108].setDownIntersection(intersection[109]);
	//intersection  done
	intersection[109].setUpIntersection(intersection[108]);
	intersection[109].setHorIntersection(intersection[97]);
	intersection[109].setDownIntersection(intersection[110]);
	//intersection  done
	intersection[110].setUpIntersection(intersection[109]);
	intersection[110].setHorIntersection(intersection[121]);
	intersection[110].setDownIntersection(null);
	//intersection  done
	intersection[111].setUpIntersection(null);
	intersection[111].setHorIntersection(null);
	intersection[111].setDownIntersection(intersection[112]);
	//intersection  done
	intersection[112].setUpIntersection(intersection[111]);
	intersection[112].setHorIntersection(intersection[122]);
	intersection[112].setDownIntersection(intersection[113]);
	//intersection  done
	intersection[113].setUpIntersection(intersection[112]);
	intersection[113].setHorIntersection(intersection[102]);
	intersection[113].setDownIntersection(intersection[114]);
	//intersection  done
	intersection[114].setUpIntersection(intersection[113]);
	intersection[114].setHorIntersection(intersection[124]);
	intersection[114].setDownIntersection(intersection[115]);
	//intersection  done
	intersection[115].setUpIntersection(intersection[114]);
	intersection[115].setHorIntersection(intersection[104]);
	intersection[115].setDownIntersection(intersection[116]);
	//intersection  done
	intersection[116].setUpIntersection(intersection[115]);
	intersection[116].setHorIntersection(intersection[126]);
	intersection[116].setDownIntersection(intersection[117]);
	//intersection  done
	intersection[117].setUpIntersection(intersection[116]);
	intersection[117].setHorIntersection(intersection[106]);
	intersection[117].setDownIntersection(intersection[118]);
	//intersection  done
	intersection[118].setUpIntersection(intersection[117]);
	intersection[118].setHorIntersection(intersection[128]);
	intersection[118].setDownIntersection(intersection[119]);
	//intersection  done
	intersection[119].setUpIntersection(intersection[118]);
	intersection[119].setHorIntersection(intersection[108]);
	intersection[119].setDownIntersection(intersection[120]);
	//intersection  done
	intersection[120].setUpIntersection(intersection[119]);
	intersection[120].setHorIntersection(intersection[130]);
	intersection[120].setDownIntersection(intersection[121]);
	//intersection  done
	intersection[121].setUpIntersection(intersection[120]);
	intersection[121].setHorIntersection(intersection[110]);
	intersection[121].setDownIntersection(null);
	//intersection  done
	intersection[122].setUpIntersection(null);
	intersection[122].setHorIntersection(intersection[112]);
	intersection[122].setDownIntersection(intersection[123]);
	//intersection  done
	intersection[123].setUpIntersection(intersection[122]);
	intersection[123].setHorIntersection(intersection[132]);
	intersection[123].setDownIntersection(intersection[124]);
	//intersection  done
	intersection[124].setUpIntersection(intersection[123]);
	intersection[124].setHorIntersection(intersection[114]);
	intersection[124].setDownIntersection(intersection[125]);
	//intersection  done
	intersection[125].setUpIntersection(intersection[124]);
	intersection[125].setHorIntersection(intersection[134]);
	intersection[125].setDownIntersection(intersection[126]);
	//intersection  done
	intersection[126].setUpIntersection(intersection[125]);
	intersection[126].setHorIntersection(intersection[116]);
	intersection[126].setDownIntersection(intersection[127]);
	//intersection  done
	intersection[127].setUpIntersection(intersection[126]);
	intersection[127].setHorIntersection(intersection[136]);
	intersection[127].setDownIntersection(intersection[128]);
	//intersection  done
	intersection[128].setUpIntersection(intersection[127]);
	intersection[128].setHorIntersection(intersection[118]);
	intersection[128].setDownIntersection(intersection[129]);
	//intersection  done
	intersection[129].setUpIntersection(intersection[128]);
	intersection[129].setHorIntersection(intersection[138]);
	intersection[129].setDownIntersection(intersection[130]);
	//intersection  done
	intersection[130].setUpIntersection(intersection[129]);
	intersection[130].setHorIntersection(intersection[120]);
	intersection[130].setDownIntersection(null);
	//intersection  done
	intersection[131].setUpIntersection(null);
	intersection[131].setHorIntersection(intersection[143]);
	intersection[131].setDownIntersection(intersection[132]);
	//intersection  done
	intersection[132].setUpIntersection(intersection[131]);
	intersection[132].setHorIntersection(intersection[123]);
	intersection[132].setDownIntersection(intersection[133]);
	//intersection  done
	intersection[133].setUpIntersection(intersection[132]);
	intersection[133].setHorIntersection(intersection[145]);
	intersection[133].setDownIntersection(intersection[134]);
	//intersection  done
	intersection[134].setUpIntersection(intersection[133]);
	intersection[134].setHorIntersection(intersection[125]);
	intersection[134].setDownIntersection(intersection[135]);
	//intersection  done
	intersection[135].setUpIntersection(intersection[134]);
	intersection[135].setHorIntersection(intersection[147]);
	intersection[135].setDownIntersection(intersection[136]);
	//intersection  done
	intersection[136].setUpIntersection(intersection[135]);
	intersection[136].setHorIntersection(intersection[127]);
	intersection[136].setDownIntersection(intersection[137]);
	//intersection  done
	intersection[137].setUpIntersection(intersection[136]);
	intersection[137].setHorIntersection(null);
	intersection[137].setDownIntersection(intersection[138]);
	//intersection  done
	intersection[138].setUpIntersection(intersection[137]);
	intersection[138].setHorIntersection(intersection[129]);
	intersection[138].setDownIntersection(intersection[139]);
	//intersection  done
	intersection[139].setUpIntersection(intersection[138]);
	intersection[139].setHorIntersection(null);
	intersection[139].setDownIntersection(null);
	//intersection  done
	intersection[140].setUpIntersection(null);
	intersection[140].setHorIntersection(intersection[148]);
	intersection[140].setDownIntersection(intersection[141]);
	//intersection  done
	intersection[141].setUpIntersection(intersection[140]);
	intersection[141].setHorIntersection(null);
	intersection[141].setDownIntersection(intersection[142]);
	//intersection  done
	intersection[142].setUpIntersection(intersection[141]);
	intersection[142].setHorIntersection(null);
	intersection[142].setDownIntersection(intersection[143]);
	//intersection  done
	intersection[143].setUpIntersection(intersection[142]);
	intersection[143].setHorIntersection(intersection[131]);
	intersection[143].setDownIntersection(intersection[144]);
	//intersection  done
	intersection[144].setUpIntersection(intersection[143]);
	intersection[144].setHorIntersection(null);
	intersection[144].setDownIntersection(intersection[145]);
	//intersection  done
	intersection[145].setUpIntersection(intersection[144]);
	intersection[145].setHorIntersection(intersection[133]);
	intersection[145].setDownIntersection(intersection[146]);
	//intersection  done
	intersection[146].setUpIntersection(intersection[145]);
	intersection[146].setHorIntersection(null);
	intersection[146].setDownIntersection(intersection[147]);
	//intersection  done
	intersection[147].setUpIntersection(intersection[146]);
	intersection[147].setHorIntersection(intersection[135]);
	intersection[147].setDownIntersection(null);
	//intersection  done
	intersection[148].setUpIntersection(null);
	intersection[148].setHorIntersection(intersection[140]);
	intersection[148].setDownIntersection(null);
        
        javax.swing.JButton intersectionButton[]=new javax.swing.JButton[149];
        for(int i=0;i<intersectionButton.length;i++){
            intersectionButton[i]=new javax.swing.JButton();
            intersection[i].setButton(intersectionButton[i],trainSettlerPanel);
        }
        //</editor-fold>
        goldCoinLabel.setIcon(new ImageIcon(makeColorTransparent(
                "/settlerstrailstorails/resources/gold.png", Color.WHITE)));
        backgroundColor=redInfoButton.getBackground();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(((dim.width/2)-(this.getSize().width/2)), ((dim.height/2)-((this.getSize().height/2)+20)));
        ActionListener taskPerformer2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                numberOfLumberLabel.setText(String.valueOf(currPlayer.getLumber()));
                numberOfOreLabel.setText(String.valueOf(currPlayer.getOre()));
                numberOfCoalLabel.setText(String.valueOf(currPlayer.getCoal()));
                numberOfCattleLabel.setText(String.valueOf(currPlayer.getCattle()));
                numberOfWheatLabel.setText(String.valueOf(currPlayer.getWheat()));
                numberOfGoldLabel.setText(String.valueOf(currPlayer.getGold()));
            }
        };
        new Timer(100, taskPerformer2).start(); 
        Random r = new Random();
        int qLabelMarker=0;
        Collections.shuffle(chits);
        if(predeterminedChits!=null){
            chits=predeterminedChits;
            Collections.reverse(chits);
            for(int i=0;i<chits.size();i++){
                singleQHex[i].setChit(chits.get(i));
            }
        }else {
            for (int i = 0; i < chits.size();) {
                int randomV = r.nextInt(chits.size());
                switch (chits.get(randomV)) {
                    case 9:
                        singleQHex[qLabelMarker++].setChit(9);
                        break;
                    case 10:
                        singleQHex[qLabelMarker++].setChit(10);
                        break;
                    case 11:
                        singleQHex[qLabelMarker++].setChit(11);
                        break;
                }
                chits.remove((int) randomV);
            }
        }
        //insert them in order from west to east, south to north
        singleQHexes.add(singleQHex[11]);
        singleQHexes.add(singleQHex[9]);
        singleQHexes.add(singleQHex[10]);
        singleQHexes.add(singleQHex[8]);
        singleQHexes.add(singleQHex[6]);
        singleQHexes.add(singleQHex[7]);
        singleQHexes.add(singleQHex[5]);
        singleQHexes.add(singleQHex[4]);
        singleQHexes.add(singleQHex[3]);
        singleQHexes.add(singleQHex[2]);
        singleQHexes.add(singleQHex[1]);
        singleQHexes.add(singleQHex[0]);
        for(int i=0;i<railButton.length;i++){
            railButton[i].setVisible(false);
        }
        infoMessage[0]=infoDialogOne;
        infoMessage[1]=infoDialogTwo;
        infoMessage[2]=infoDialogThree;
        infoMessage[3]=infoDialogFour;
        infoMessage[4]=infoDialogFive;
        buildButton.setVisible(false);
        currPlayer=player[0];
        if(currPlayer.getColor()==Player.Color.RED){
            redTextTrackCircleLabel.setBackground(Color.RED);
        }else if(currPlayer.getColor()==Player.Color.GREEN){
            greenTextTrackCircleLabel.setBackground(Color.RED);
        }else if(currPlayer.getColor()==Player.Color.WHITE){
            whiteTextTrackCircleLabel.setBackground(Color.RED);
        }else if(currPlayer.getColor()==Player.Color.ORANGE){
            orangeTextTrackCircleLabel.setBackground(Color.RED);
        }
        for(int i=0;i<player.length;i++){
            player[i].setGoldIgnoreStuff(startingGold);
        }
        trashBinButton.setIcon(trashBin);
        buildCityButton.setVisible(false);
        trashBinButton.setVisible(false);
        cancelButton.setVisible(false);
        rail[115].setFreeRail(true);
        rail[68].setFreeRail(true);
        rail[216].setFreeRail(true);
        rail[2].setFreeRail(true);
        rail[7].setFreeRail(true);
        rail[34].setFreeRail(true);
        rail[10].setFreeRail(true);
        rail[58].setFreeRail(true);
        rail[70].setFreeRail(true);
        rail[41].setFreeRail(true);
        rail[43].setFreeRail(true);
        rail[56].setFreeRail(true);
        rail[82].setFreeRail(true);
        rail[108].setFreeRail(true);
        rail[109].setFreeRail(true);
        rail[110].setFreeRail(true);
        rail[129].setFreeRail(true);
        rail[131].setFreeRail(true);
        rail[90].setFreeRail(true);
        rail[88].setFreeRail(true);
        rail[85].setFreeRail(true);   
        developmentCardButton = new AvaJButton(developmentCardButton1);
        developmentCardButton.setVisible(false);
        for(int i=0;i<hex.length;i++){
            hex[i].getHexButton().setVisible(false);
        }
        ImageIcon e = new ImageIcon(
        makeColorTransparent("/settlerstrailstorails/resources/error.png", Color.WHITE));
        errorRedLabel.setIcon(e);
        errorWhiteLabel.setIcon(e);
        errorOrangeLabel.setIcon(e);
        errorGreenLabel.setIcon(e);
        errorRedLabel.setVisible(false);
        errorWhiteLabel.setVisible(false);
        errorOrangeLabel.setVisible(false);
        errorGreenLabel.setVisible(false);
    }//</editor-fold>
    public gameFrame(){//<editor-fold>
        initComponents();
    }//</editor-fold>
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
    public static void playSound(String s) {//<editor-fold>
        if(s.equals("click")) {
            try {
                soundPlaying = AudioSystem.getClip();
                soundPlaying.open(AudioSystem.getAudioInputStream(
                        SettlersTrailsToRails.class.getResource(
                        "/settlerstrailstorails/resources/click.wav")));
                FloatControl volume = (FloatControl) soundPlaying.getControl(FloatControl.Type.MASTER_GAIN);
                if(volumeInt==0){
                    volume.setValue(volume.getMinimum());
                }else{
                    volume.setValue((volumeInt * 3.0f)-30.0f);
                }
                soundPlaying.start();
            } catch (Exception e) {
            }
        } else if (s.equals("beep")) {
            try {
                soundPlaying = AudioSystem.getClip();
                soundPlaying.open(AudioSystem.getAudioInputStream(
                        SettlersTrailsToRails.class.getResource(
                        "/settlerstrailstorails/resources/beep.wav")));
                FloatControl volume = (FloatControl) soundPlaying.getControl(FloatControl.Type.MASTER_GAIN);
                if(volumeInt==0){
                    volume.setValue(volume.getMinimum());
                }else{
                    volume.setValue((volumeInt * 3.0f)-30.0f);
                }
                soundPlaying.start();
            } catch (Exception e) {
            }
        } else if (s.equals("dice")) {
            try {                
                soundPlaying = AudioSystem.getClip();
                soundPlaying.open(AudioSystem.getAudioInputStream(
                        SettlersTrailsToRails.class.getResource(
                        "/settlerstrailstorails/resources/dice.wav")));
                FloatControl volume = (FloatControl) soundPlaying.getControl(FloatControl.Type.MASTER_GAIN);
                if(volumeInt==0){
                    volume.setValue(volume.getMinimum());
                }else{
                    volume.setValue((volumeInt * 3.0f)-30.0f);
                }
                soundPlaying.start();
            } catch (Exception e) {
            }
        }else if (s.equals("trash")) {
            try {                
                soundPlaying = AudioSystem.getClip();
                soundPlaying.open(AudioSystem.getAudioInputStream(
                        SettlersTrailsToRails.class.getResource(
                        "/settlerstrailstorails/resources/trash.wav")));
                FloatControl volume = (FloatControl) soundPlaying.getControl(FloatControl.Type.MASTER_GAIN);
                if(volumeInt==0){
                    volume.setValue(volume.getMinimum());
                }else{
                    volume.setValue((volumeInt * 3.0f)-30.0f);
                }
                soundPlaying.start();
            } catch (Exception e) {
            }
        }else if (s.equals("ching")) {
            try {                
                soundPlaying = AudioSystem.getClip();
                soundPlaying.open(AudioSystem.getAudioInputStream(
                        SettlersTrailsToRails.class.getResource(
                        "/settlerstrailstorails/resources/ching.wav")));
                FloatControl volume = (FloatControl) soundPlaying.getControl(FloatControl.Type.MASTER_GAIN);
                if(volumeInt==0){
                    volume.setValue(volume.getMinimum());
                }else{
                    volume.setValue((volumeInt * 3.0f)-30.0f);
                }
                soundPlaying.start();
            } catch (Exception e) {
            }
        }else if (s.equals("card")) {
            try {
                soundPlaying = AudioSystem.getClip();
                soundPlaying.open(AudioSystem.getAudioInputStream(
                        SettlersTrailsToRails.class.getResource(
                        "/settlerstrailstorails/resources/card.wav")));
                FloatControl volume = (FloatControl) soundPlaying.getControl(FloatControl.Type.MASTER_GAIN);
                if(volumeInt==0){
                    volume.setValue(volume.getMinimum());
                }else{
                    volume.setValue((volumeInt * 3.0f)-30.0f);
                }
                soundPlaying.start();
            } catch (Exception e) {
            }
        }
    }//</editor-fold>
    public final void setupPhase(){//<editor-fold>
        setupPhase=true;
        if(playerOneName!=null){
            addToInfo(playerOneName + " plays with " + player[0].getColor() + " pieces",false);
        }else{
            addToInfo("Player 1 plays with "+player[0].getColor()+" pieces",false);
        }
        if(playerTwoName!=null){
            addToInfo(playerTwoName + " plays with " + player[1].getColor() + " pieces",false);
        }else{
            addToInfo("Player 2 plays with "+player[1].getColor()+" pieces",false);
        }
        if(playerThreeName!=null){
            addToInfo(playerThreeName + " plays with " + player[2].getColor() + " pieces",false);
        }else{
            addToInfo("Player 3 plays with "+player[2].getColor()+" pieces",false);
        }
        if(numberOfPlayers == 4) {
            if (playerFourName != null) {
                addToInfo(playerFourName + " plays with " + player[3].getColor() + " pieces", false);
            } else {
                addToInfo("Player 4 plays with " + player[3].getColor() + " pieces", false);
            }
        }
        disableMenu();
        rollButton.setEnabled(true);
        optionsButton.setEnabled(true);
        exitButton.setEnabled(true);
        addToInfo("Roll to see who goes first",true);
    }//</editor-fold>
    public void getResources(int roll){//<editor-fold>
        boolean playedOnce=false;
        for(int k = 0; k < numberOfPlayers; k++) {
            int tOre=player[k].getOre();
            int tCattle=player[k].getCattle();
            int tCoal=player[k].getCoal();
            int tWheat=player[k].getWheat();
            int tLumber=player[k].getLumber();
            CityHex[] p1c = player[k].getOwnedCities();
            boolean getGold=true;
            Hex.Resource[] h;
            for (int i = 0; i < p1c.length; i++) {
                if (p1c[i] != null && !p1c[i].getBlockedByRobber()) {
                    h = p1c[i].cityAdjacentToNumber(roll);
                    for (int j = 0; j < h.length; j++) {
                        if (h[j] != null) {
                            player[k].plusResource(h[j]);
                            getGold=false;
                        }
                    }
                }else if(p1c[i]!=null && p1c[i].getBlockedByRobber()&&
                        (p1c[i].getHexOne().getChit()==roll||
                        (p1c[i].getHexTwo()!=null && p1c[i].getHexTwo().getChit()==roll)||
                        (p1c[i].getHexThree()!=null && p1c[i].getHexThree().getChit()==roll))){
                    addToInfo(player[k].getName()+" was robbed.",false);
                }
            }
            if (getGold&&!playedOnce) {
                player[k].plusGold();
                playedOnce=true;
            }else if(getGold){
                player[k].plusGoldIgnoreSound();
            }
            tOre=player[k].getOre()-tOre;
            tCattle=player[k].getCattle()-tCattle;
            tCoal=player[k].getCoal()-tCoal;
            tWheat=player[k].getWheat()-tWheat;
            tLumber=player[k].getLumber()-tLumber;
            if(currPlayer==player[k]){
            if(tOre!=0){
                    gameFrame.addToInfo("You got " + tOre + " ore.", false);
                }
                if (tCattle != 0) {
                    gameFrame.addToInfo("You got " + tCattle + " cattle.", false);
                }
                if (tCoal != 0) {
                    gameFrame.addToInfo("You got " + tCoal + " coal.", false);
                }
                if (tWheat != 0) {
                    gameFrame.addToInfo("You got " + tWheat + " wheat.", false);
                }
                if (tLumber != 0) {
                    gameFrame.addToInfo("You got " + tLumber + " lumber.", false);
                }
                if (tCattle != 0 || tOre != 0
                        || tWheat != 0 || tCoal != 0 || tLumber != 0) {
                    playSound("card");
                }
            }
        }        
        gameFrame.addToInfo(currPlayer.getName()+": Take your turn",false);
    }//</editor-fold>
    public void saveGame(){//<editor-fold>
        //ask for the name of the file        
        try{
        File f = new File("file.str");        
        if(!f.createNewFile()){
            //already exists
            f.delete();
            f.createNewFile();
        }
        PrintWriter fW= new PrintWriter(new FileWriter(f));
        //
        fW.print(String.valueOf(timeAtBeggining+" "));
        for(int i=0;i<allMessages.size();i++){
            fW.print("| "+allMessages.get(i)+" ");
        }
        fW.print("|| ");
        fW.print(numberOfPlayers);
        fW.print("|"+playerOneName);
        fW.print("|"+playerTwoName);
        fW.print("|"+playerThreeName);
        if (numberOfPlayers == 4) {
            fW.print("|"+playerFourName);
        }
        fW.print(doubleGold);
        fW.print(player[0].savePlayer());
        fW.print(player[1].savePlayer());
        fW.print(player[2].savePlayer());
        fW.print(player[3].savePlayer());
        for(int i=0;i<hex.length;i++){
            fW.print(hex[i].saveHex());
        }
        if(currPlayer != null) {
            fW.print("|currPlayer" + currPlayer.getColor());
        } else {
            fW.print("|currPlayerNULL");            
        }
        for(int i=0;i<citySpot.length;i++){
            fW.print(citySpot[i].saveCity());
        }
        fW.print("|buildingSettlement"+buildingSettlement);
        fW.print("|forwardSetupTurn"+forwardSetupTurn);
        fW.print("|setupPhaseLoopSecondPlayer"+setupPhaseLoopSecondPlayer);
        fW.print("|currentlyDisplayingCityNames"+currentlyDisplayingCityNames);
        fW.print("|ignorePurpleDuringBuild"+ignorePurpleDuringBuild);
        fW.print("|fourthPlayerCityBuild"+fourthPlayerCityBuild);
        fW.print("|buildingRail"+buildingRail);
        fW.print("|railCheat"+railCheat);
        fW.print("|ignoreResources"+ignoreResources);
        fW.print("|buildingSettler"+buildingSettler);
        fW.print("|movingSettler"+movingSettler);
        fW.print("|movingTrain"+movingTrain);
        fW.print("|buildingTrain"+buildingTrain);
        fW.print("|buildTwoRails"+buildTwoRails);
        fW.print("|rolled"+rolled);
        fW.print("|movingRobber"+movingRobber);
        fW.print("|doubleGold"+doubleGold);
        fW.print("|setupPhase"+setupPhase);
        fW.print("|selectDiceRolls"+selectDiceRolls);
        fW.print("|doneRollingForFirst"+doneRollingForFirst);
        fW.print("|ignoreExtraordinaryBuildingPhase"+ignoreExtraordinaryBuildingPhase);
        fW.print("|disableCityButtons"+disableCityButtons);
        fW.print("|fourthPlayerCities"+fourthPlayerCities);
        fW.print("|extraordinaryBuildingPhaseNum"+extraordinaryBuildingPhaseNum);
        fW.print("|volumeInt"+volumeInt);
        for(int i=0;i<rail.length;i++){
            fW.print(rail[i].saveRail());
        }        
        for(int i=0;i<intersection.length;i++){
            fW.print(intersection[i].saveIntersection());
        }        
        fW.close();
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }//</editor-fold>
    public void loadGame(String path){
        try {
            File f = new File("file.str");
            if (!f.exists()) {
                //does not exists
                
            }
            Scanner bR = new Scanner(new FileReader(f));
            timeAtBeggining = bR.nextLong();
            ArrayList<String> temp = new ArrayList<String>();
            String s="";
            String curr;
            bR.next();
            while (true) {
                curr=bR.next();
                if(curr.equals("|")){
                    temp.add(s);
                    s="";
                }else if(curr.equals("||")){
                    temp.add(s);
                    break;
                }else{
                    s+=curr+" ";
                }
                
            }
            allMessages=temp;
            /*
            for (int i = 0; i < allMessages.size(); i++) {
                fW.print("|" + allMessages.get(i));
            }
            fW.print("|numberOfPlayers" + numberOfPlayers);
            fW.print("|playerOneName" + playerOneName);
            fW.print("|playerTwoName" + playerTwoName);
            fW.print("|playerThreeName" + playerThreeName);
            if (numberOfPlayers == 4) {
                fW.print("|playerFourName" + playerFourName);
            }
            fW.print(doubleGold);
            fW.print(player[0].savePlayer());
            fW.print(player[1].savePlayer());
            fW.print(player[2].savePlayer());
            fW.print(player[3].savePlayer());
            for (int i = 0; i < hex.length; i++) {
                fW.print(hex[i].saveHex());
            }
            if (currPlayer != null) {
                fW.print("|currPlayer" + currPlayer.getColor());
            } else {
                fW.print("|currPlayerNULL");
            }
            for (int i = 0; i < citySpot.length; i++) {
                fW.print(citySpot[i].saveCity());
            }
            fW.print("|buildingSettlement" + buildingSettlement);
            fW.print("|forwardSetupTurn" + forwardSetupTurn);
            fW.print("|setupPhaseLoopSecondPlayer" + setupPhaseLoopSecondPlayer);
            fW.print("|currentlyDisplayingCityNames" + currentlyDisplayingCityNames);
            fW.print("|ignorePurpleDuringBuild" + ignorePurpleDuringBuild);
            fW.print("|fourthPlayerCityBuild" + fourthPlayerCityBuild);
            fW.print("|buildingRail" + buildingRail);
            fW.print("|railCheat" + railCheat);
            fW.print("|ignoreResources" + ignoreResources);
            fW.print("|buildingSettler" + buildingSettler);
            fW.print("|movingSettler" + movingSettler);
            fW.print("|movingTrain" + movingTrain);
            fW.print("|buildingTrain" + buildingTrain);
            fW.print("|buildTwoRails" + buildTwoRails);
            fW.print("|rolled" + rolled);
            fW.print("|movingRobber" + movingRobber);
            fW.print("|doubleGold" + doubleGold);
            fW.print("|setupPhase" + setupPhase);
            fW.print("|selectDiceRolls" + selectDiceRolls);
            fW.print("|doneRollingForFirst" + doneRollingForFirst);
            fW.print("|ignoreExtraordinaryBuildingPhase" + ignoreExtraordinaryBuildingPhase);
            fW.print("|disableCityButtons" + disableCityButtons);
            fW.print("|fourthPlayerCities" + fourthPlayerCities);
            fW.print("|extraordinaryBuildingPhaseNum" + extraordinaryBuildingPhaseNum);
            fW.print("|volumeInt" + volumeInt);
            for (int i = 0; i < rail.length;i++){
            fW.print(rail[i].saveRail());
        }        
        for(int i=0;i<intersection.length;i++){
            fW.print(intersection[i].saveIntersection());
        }        */
        bR.close();
        }catch(Exception e){
            //corrupt file
            System.out.println(e.toString());
        }
    }
    public JLabel getErrorRedLabel(){
        return errorRedLabel;
    }
    public JLabel getErrorGreenLabel(){
        return errorGreenLabel;
    }
    public JLabel getErrorOrangeLabel(){
        return errorOrangeLabel;
    }
    public JLabel getErrorWhiteLabel(){
        return errorWhiteLabel;
    }
    public boolean getRolled(){
        return rolled;
    }
    public void buildToRoll(){//<editor-fold>
        buildButton.setVisible(false);
        rollButton.setVisible(true);
    }//</editor-fold>
    public void rollToBuild(){//<editor-fold>
        buildButton.setVisible(true);
        rollButton.setVisible(false);
    }//</editor-fold>
    public int rollDice(){//<editor-fold>
        if(currPlayer.getHuman()){
            playSound("dice");
        }
        Random r = new Random();
        int r1,r2;
        if(!selectDiceRolls){
            r1 = r.nextInt(6)+1;
            r2 = r.nextInt(6)+1;   
        }else{
            r1=Integer.parseInt(JOptionPane.showInputDialog("choose dice1 value for "+currPlayer));
            r2=Integer.parseInt(JOptionPane.showInputDialog("choose dice2 value for "+currPlayer));            
        }
        switch(r1){
            case 1: diceOneLabel.setIcon(new ImageIcon(loadImage(
                    "/settlerstrailstorails/resources/redDiceOne.png")));
                break;
            case 2: diceOneLabel.setIcon(new ImageIcon(loadImage(
                    "/settlerstrailstorails/resources/redDiceTwo.png")));
                break;
            case 3: diceOneLabel.setIcon(new ImageIcon(loadImage(
                    "/settlerstrailstorails/resources/redDiceThree.png")));
                break;
            case 4: diceOneLabel.setIcon(new ImageIcon(loadImage(
                    "/settlerstrailstorails/resources/redDiceFour.png")));
                break;
            case 5: diceOneLabel.setIcon(new ImageIcon(loadImage(
                    "/settlerstrailstorails/resources/redDiceFive.png")));
                break;
            case 6: diceOneLabel.setIcon(new ImageIcon(loadImage(
                    "/settlerstrailstorails/resources/redDiceSix.png")));
                break;
        }
        switch(r2){
            case 1: diceTwoLabel.setIcon(new ImageIcon(loadImage(
                    "/settlerstrailstorails/resources/blueDiceOne.png")));
                break;
            case 2: diceTwoLabel.setIcon(new ImageIcon(loadImage(
                    "/settlerstrailstorails/resources/blueDiceTwo.png")));
                break;
            case 3: diceTwoLabel.setIcon(new ImageIcon(loadImage(
                    "/settlerstrailstorails/resources/blueDiceThree.png")));
                break;
            case 4: diceTwoLabel.setIcon(new ImageIcon(loadImage(
                    "/settlerstrailstorails/resources/blueDiceFour.png")));
                break;
            case 5: diceTwoLabel.setIcon(new ImageIcon(loadImage(
                    "/settlerstrailstorails/resources/blueDiceFive.png")));
                break;
            case 6: diceTwoLabel.setIcon(new ImageIcon(loadImage(
                    "/settlerstrailstorails/resources/blueDiceSix.png")));
                break;
        }
        cumulativeRolls.add(r1+r2);
        return r1+r2;
    }//</editor-fold>
    public void enableMenu(){//<editor-fold>
         buildButton.setEnabled(true);
         utilitiesButton.setEnabled(true);
         tradeButton.setEnabled(true);
         endTurnButton.setEnabled(true);
         optionsButton.setEnabled(true);
         exitButton.setEnabled(true);
         rollButton.setEnabled(true);
    }//</editor-fold>
    public void disableMenu(){//<editor-fold>
         buildButton.setEnabled(false);
         tradeButton.setEnabled(false);
         utilitiesButton.setEnabled(false);
         endTurnButton.setEnabled(false);
         optionsButton.setEnabled(false);
         exitButton.setEnabled(false);
         rollButton.setEnabled(false);
    }//</editor-fold>
    public static void addToInfo(String s, boolean redBlinker){//<editor-fold>
        allMessages.add(0,s);
        for(int i=infoMessage.length-1;i>-1;i--){
            if(i==0){
                infoMessage[i].setText(s);
            }else{
                infoMessage[i].setText(infoMessage[i-1].getText());
            }
        }
        if(redBlinker){
            redBlinkerTimer.start();
        }else{
            infoDialogOne.setBackground(Color.WHITE);
            redBlinkerTimer.stop();
        }
    }//</editor-fold>
    public void chooseOwnDiceRolls(boolean b){//<editor-fold>
        selectDiceRolls=true;
    }//</editor-fold>
    public void setStartingGold(boolean b){//<editor-fold>
        doubleGold=b;
    }//</editor-fold>
    //save game after
    public void onClose(){//<editor-fold>
        //display exit confirm dialog
        closeAllOpenWindows(); 
        saveGame();
        loadGame("file.str");
        scd = new SettlersConfirmDialog(this,1,null);
        scd.setVisible(true);
    }//</editor-fold>
    public void developmentCardVisible(){
        developmentCardButton.setVisible(true);
    }
    public void developmentCardNotVisible(){
        developmentCardButton.setVisible(false);
    }
    public void setRealPlayer(Player t){
        realCurrPlayer=t;
    }
    public String[] getNames(){//<editor-fold>
        String[] n = new String[numberOfPlayers];
        n[0]=playerOneName;
        n[1]=playerTwoName;
        n[2]=playerThreeName;
        if(numberOfPlayers==4){
            n[3]=playerFourName;
        }
        return n;
    }
    public void buildSettlement(){
        buildingSettlement=true;
    }
    public void buildRail(){
        closeAllOpenWindows();
        buildingRail=true;
        currPlayer.displayRailPossibilities();
        cancelButton.setVisible(true);
        disableMenu();
    }
    public void buildTwoRails(){
        for(int i=0;i<rail.length;i++){
            rail[i].setPlayingDevelopmentCard(true);
        }
        buildTwoRails=true;
        buildRail();
    }
    public void doneBuildingRail() {
        cancelButton.setVisible(false);
        buildingRail = false;
        currPlayer.destroyRailPossibilities();
        if (buildTwoRails) {
            buildTwoRails=false;
            currPlayer.minusDPCard(2);
            addToInfo(getCurrPlayer()+" played Engineer card",false);
            getCurrPlayer().setPlayedDPCardThisTurn(true);
            enableMenu();
            for (int i = 0; i < rail.length; i++) {
                rail[i].setPlayingDevelopmentCard(false);
            }
        }
        enableMenu();
        if(setupPhase){
            tradeButton.setEnabled(false);
            endTurnButton.setEnabled(false);
            buildButton.setEnabled(false);
        }
        if(extraordinaryBuildingPhaseNum!=0){
            currPlayer.extraordinaryBuildingPhase();
        }
    }
    public void deliverGoods(CityHex c, Player p){
        c.deliverGoods(p, buttonPanel);        
    }
    public void nextPlayer(){
        currPlayer.endTurn();
        if(!setupPhase) {
            if (currPlayer == orderOfPlayers[0]) {
                currPlayer = orderOfPlayers[1];
            } else if (currPlayer == orderOfPlayers[1]) {
                currPlayer =orderOfPlayers[2];
            } else if ( numberOfPlayers == 4&&currPlayer ==orderOfPlayers[2]) {
                currPlayer =orderOfPlayers[3];
            } else if ( numberOfPlayers == 4&&currPlayer ==orderOfPlayers[3]) {
                currPlayer =orderOfPlayers[0];
            } else if ( numberOfPlayers == 3 && currPlayer == orderOfPlayers[2]) {
                currPlayer = orderOfPlayers[0];
            }
            addToInfo(currPlayer.getName() + ": You must roll first", true);
            enableMenu();
            tradeButton.setEnabled(false);
            endTurnButton.setEnabled(false);
            buildToRoll();
        }else if(!doneRollingForFirst){
            if (currPlayer == player[0]) {
                currPlayer = player[1];
            } else if (currPlayer == player[1]) {
                currPlayer =player[2];
            } else if ( numberOfPlayers == 4&&currPlayer ==player[2]) {
                currPlayer =player[3];
            } else if ( numberOfPlayers == 4&&currPlayer ==player[3]) {
                currPlayer =player[0];
            } else if ( numberOfPlayers == 3&&currPlayer ==player[2]) {
                currPlayer =player[0];
            }
        }else if(forwardSetupTurn){
            if (currPlayer == orderOfPlayers[0]) {
                currPlayer = orderOfPlayers[1];
            } else if (currPlayer == orderOfPlayers[1]) {
                currPlayer = orderOfPlayers[2];
            } else if (currPlayer == orderOfPlayers[2] && numberOfPlayers == 4) {
                currPlayer = orderOfPlayers[3];
            } else if ( numberOfPlayers == 4&&currPlayer == orderOfPlayers[3] &&setupPhaseLoopSecondPlayer) {
                currPlayer = orderOfPlayers[2];
                forwardSetupTurn=false;
                setupPhaseLoopSecondPlayer=false;
            } else if ( numberOfPlayers == 3&&currPlayer == orderOfPlayers[2] &&setupPhaseLoopSecondPlayer) {
                currPlayer = orderOfPlayers[1];
                forwardSetupTurn=false;
                setupPhaseLoopSecondPlayer=false;
            } else if ( numberOfPlayers == 4&&currPlayer == orderOfPlayers[3] &&!setupPhaseLoopSecondPlayer) {
                setupPhaseLoopSecondPlayer=true;
            } else if ( numberOfPlayers == 3&&currPlayer == orderOfPlayers[2] &&!setupPhaseLoopSecondPlayer) {
                setupPhaseLoopSecondPlayer=true;
            }
        }else if(!forwardSetupTurn){
             if (currPlayer == orderOfPlayers[0]&&setupPhaseLoopSecondPlayer) {
                currPlayer = orderOfPlayers[1];
                forwardSetupTurn=true;
                setupPhaseLoopSecondPlayer=false;
            } else if (currPlayer == orderOfPlayers[1]) {
                currPlayer = orderOfPlayers[0];
            } else if (currPlayer == orderOfPlayers[2]) {
                currPlayer = orderOfPlayers[1];
            } else if (currPlayer == orderOfPlayers[0]&&!setupPhaseLoopSecondPlayer) {
                setupPhaseLoopSecondPlayer=true;
            } else if (currPlayer == orderOfPlayers[3]) {
                currPlayer = orderOfPlayers[2];
            }
        }
        currPlayer.startTurn();
        setBackgroundPlayer();
    }//</editor-fold>
    public Player getNextPlayer(){
        if(!setupPhase) {
            if (currPlayer == orderOfPlayers[0]) {
                return orderOfPlayers[1];
            } else if (currPlayer == orderOfPlayers[1]) {
                return orderOfPlayers[2];
            } else if ( numberOfPlayers == 4&&currPlayer ==orderOfPlayers[2]) {
                return orderOfPlayers[3];
            } else if ( numberOfPlayers == 4&&currPlayer ==orderOfPlayers[3]) {
                return orderOfPlayers[0];
            } else if ( numberOfPlayers == 3&&currPlayer ==orderOfPlayers[2]) {
                return orderOfPlayers[0];
            }
        }else if(!doneRollingForFirst){
            if (currPlayer == player[0]) {
                return player[1];
            } else if (currPlayer == player[1]) {
                return player[2];
            } else if ( numberOfPlayers == 4&&currPlayer ==player[2]) {
                return player[3];
            } else if ( numberOfPlayers == 4&&currPlayer ==player[3]) {
                return player[0];
            } else if ( numberOfPlayers == 3&&currPlayer ==player[2]) {
                return player[0];
            }
        }else if(forwardSetupTurn){
            if (currPlayer == orderOfPlayers[0]) {
                return orderOfPlayers[1];
            } else if (currPlayer == orderOfPlayers[1]) {
                return orderOfPlayers[2];
            } else if (currPlayer == orderOfPlayers[2] && numberOfPlayers == 4) {
                return orderOfPlayers[3];
            } else if ( numberOfPlayers == 4&&currPlayer == orderOfPlayers[3] &&setupPhaseLoopSecondPlayer) {
                return orderOfPlayers[2];
            } else if ( numberOfPlayers == 3&&currPlayer == orderOfPlayers[2] &&setupPhaseLoopSecondPlayer) {
                return orderOfPlayers[1];
            } else if ( numberOfPlayers == 4&&currPlayer == orderOfPlayers[3] &&!setupPhaseLoopSecondPlayer) {
                return currPlayer;
            } else if ( numberOfPlayers == 3&&currPlayer == orderOfPlayers[2] &&!setupPhaseLoopSecondPlayer) {
                return currPlayer;
            }
        }else if(!forwardSetupTurn){
             if (currPlayer == orderOfPlayers[0]&&setupPhaseLoopSecondPlayer) {
                return orderOfPlayers[1];
            } else if (currPlayer == orderOfPlayers[1]) {
                return orderOfPlayers[0];
            } else if (currPlayer == orderOfPlayers[2]) {
                return  orderOfPlayers[1];
            } else if (currPlayer == orderOfPlayers[0]&&!setupPhaseLoopSecondPlayer) {
                return currPlayer;
            } else if (currPlayer == orderOfPlayers[3]) {
                return orderOfPlayers[2];
            }
        }
        return null;
    }
    public void setBackgroundPlayer(){
        redTextTrackCircleLabel.setBackground(backgroundColor);
        greenTextTrackCircleLabel.setBackground(backgroundColor);
        whiteTextTrackCircleLabel.setBackground(backgroundColor);
        orangeTextTrackCircleLabel.setBackground(backgroundColor);
        
        if(currPlayer.getColor()==Player.Color.RED){
            redTextTrackCircleLabel.setBackground(Color.RED);
        }else if(currPlayer.getColor()==Player.Color.GREEN){
            greenTextTrackCircleLabel.setBackground(Color.RED);
        }else if(currPlayer.getColor()==Player.Color.WHITE){
            whiteTextTrackCircleLabel.setBackground(Color.RED);
        }else if(currPlayer.getColor()==Player.Color.ORANGE){
            orangeTextTrackCircleLabel.setBackground(Color.RED);
        }
    }
    public boolean hasRolled(){
        return rolled;
    }
    public void rollSeven(){
        disableMenu();
        ArrayList<Player> discardPlayers = new ArrayList<Player>();
        for(int i=0;i<player.length;i++){
            if(player[i].getResourceCards()>7){
                discardPlayers.add(player[i]);
            }
        }
        disableCityButtons=true;
        if(!discardPlayers.isEmpty()){
            movingRobber=true;
            realCurrPlayer=currPlayer;
            Player curr = discardPlayers.remove(0);
            curr.setTurn(true);
            currPlayer=curr;
            setBackgroundPlayer();
            curr.discardHalf(discardPlayers,this);
        }else{
            disableCityButtons=false;
            moveRobber();
        }
    }
    public void setCurrPlayer(Player p){
        currPlayer=p;
    }
    public Player getRealPlayer(){
        return realCurrPlayer;
    }
    public void resetRealPlayer(){
        realCurrPlayer=null;
    }
    public void moveRobber(){
        movingRobber=true;
        gameFrame.addToInfo("Select a hex to move the robber to.",true);
        for(int i=0;i<hex.length;i++){
            if(!hex[i].getRobber()){
                hex[i].getHexButton().setVisible(true);
            }
        }
    }
    public void doneMovingRobber(){
        movingRobber=false;
        for(int i=0;i<hex.length;i++){
            hex[i].getHexButton().setVisible(false);
        }
        stealCardsRobber();
    }
    public void stealCardsRobber(){
        Hex robberHexTemp=null;
        for(int i=0;i<hex.length;i++){
            if(hex[i].getRobber()){
                robberHexTemp=hex[i];
                break;
            }
        }
        ArrayList<Player> stealPlayers=new ArrayList<Player>();
        if(robberHexTemp!=null){
            for(int i=0;i<citySpot.length;i++){
                if(citySpot[i].getHexOne()==robberHexTemp && ((numberOfPlayers==3 && 
                        citySpot[i].getOwner()!=player[3]) || numberOfPlayers==4) &&
                        citySpot[i].getOwner()!=null&&
                        !stealPlayers.contains(citySpot[i].getOwner())&&currPlayer!=citySpot[i].getOwner()){
                    stealPlayers.add(citySpot[i].getOwner());
                    continue;
                }
                if(citySpot[i].getHexTwo()!=null && 
                        citySpot[i].getHexTwo()==robberHexTemp &&((numberOfPlayers==3 && 
                        citySpot[i].getOwner()!=player[3]) || numberOfPlayers==4) &&
                        citySpot[i].getOwner()!=null &&
                        !stealPlayers.contains(citySpot[i].getOwner())&&currPlayer!=citySpot[i].getOwner()){
                    stealPlayers.add(citySpot[i].getOwner());
                    continue;
                }
                if(citySpot[i].getHexThree()!=null && 
                        citySpot[i].getHexThree()==robberHexTemp && 
                        citySpot[i].getOwner()!=null && ((numberOfPlayers==3 && 
                        citySpot[i].getOwner()!=player[3]) || numberOfPlayers==4) &&
                        !stealPlayers.contains(citySpot[i].getOwner())&&currPlayer!=citySpot[i].getOwner()){
                    stealPlayers.add(citySpot[i].getOwner());
                    continue;
                }
            }
        }
        Player p=null;
        if(stealPlayers.size()>1){
            //ask what player you want to steal from
            closeAllOpenWindows();
            disableMenu();
            ChoosePlayerSteal cps = new ChoosePlayerSteal(stealPlayers, this);
            cps.setVisible(true);
            cps.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent we) {
                    new SettlersConfirmDialog(20).setVisible(true);
                }
            });
        } else if (stealPlayers.size() == 1) {
            p = stealPlayers.get(0);
        }else if(stealPlayers.isEmpty()){
            enableMenu();
        }
        if(p!=null){
            addToInfo(currPlayer.getName()+" stole a card from "+p.getName()+".",false);
            currPlayer.randomCardFrom(p);
            enableMenu();
        }
    }
    public Hex[] getHexes(){
        return hex;
    }
    public Player[] getOrderOfPlayers(){
        return orderOfPlayers;
    }
    public int getNumberOfPlayers(){
        return numberOfPlayers;
    }
    public boolean getIgnoreBuildingPhase(){
        return ignoreExtraordinaryBuildingPhase;
    }
    public void setRolled(boolean b){
        rolled=b;
    }
    public void setExtraordinaryBuildingPhaseNum(int i){
        extraordinaryBuildingPhaseNum=i;
    }
    public void setDisableCityButton(boolean b){
        disableCityButtons=b;
    }
    public boolean getDisableCityButton(){
        return disableCityButtons;
    }
    public void setIgnoreExtraordinaryBuildingPhase(boolean b){
        ignoreExtraordinaryBuildingPhase=b;
    }
    public void cancelBuildSettlement(){//<editor-fold>
        buildingSettlement=false;
        addToInfo(currPlayer+" has cancelled building",false);
        addToInfo(currPlayer+": it is your turn",true);
    }//</editor-fold>
    /**
     * accepts an int array of all the rolls for first
     * @param rolls 
     */
    public void rollAlgorithm(int[] rolls){//<editor-fold>
        int[] permanentRoll = rolls;
        ArrayList<Player> reRollers = new ArrayList<Player>();
        ArrayList<Player> reRollersLow = new ArrayList<Player>();
        int reRollNum = 13, reRollNumLow = 0;
        //all players roll and add the reuslts to the image dialog
        for (int i = 0; i < numberOfPlayers; i++) {
            //calculate the correct order based on the rolls
            for (int j = 0; j < permanentRoll.length; j++) {
                if (i == j) {
                    break;
                } else if (permanentRoll[j] == permanentRoll[i]
                        && (reRollers.isEmpty() || reRollNum == permanentRoll[i])) {
                    reRollNum = permanentRoll[i];
                    if (!reRollers.contains(player[j])) {
                        reRollers.add(player[j]);
                    }
                    if (!reRollers.contains(player[i])) {
                        reRollers.add(player[i]);
                    }
                    break;
                } else if (permanentRoll[j] == permanentRoll[i] && (reRollers.size() == 2 || reRollNumLow == permanentRoll[j])) {
                    reRollNumLow = permanentRoll[i];
                    if (!reRollersLow.contains(player[j])) {
                        reRollersLow.add(player[j]);
                    }
                    if (!reRollersLow.contains(player[i])) {
                        reRollersLow.add(player[i]);
                    }
                    break;
                }
            }
        }
        if (reRollNumLow > reRollNum) {
            reRollersLow.add(reRollers.remove(0));
            reRollersLow.add(reRollers.remove(0));
            reRollers.add(reRollersLow.remove(0));
            reRollers.add(reRollersLow.remove(0));
        }
        //arrange in order from greats to least
        ArrayList<Integer> largest = new ArrayList<Integer>();
        for (int i = 0; i < numberOfPlayers; i++) {
            largest.add(permanentRoll[i]);
        }
        Collections.sort(largest);
        Collections.reverse(largest);
        //set order ignoring reroll players
        int currOrderIndex = 0;
        for (int i = 0; i < numberOfPlayers; i++) {
            if (largest.get(i) != reRollNum && largest.get(i) != reRollNumLow) {
                for (int k = 0; k < numberOfPlayers; k++) {
                    if (largest.get(i) == permanentRoll[k]) {
                        orderOfPlayers[currOrderIndex++] = player[k];
                        break;
                    }
                }
            } else if (largest.get(i) == reRollNum) {
                currOrderIndex++;
            }
        }
        //add to info dialog people who need to reroll
        String addTI = "";
        if(!reRollers.isEmpty()) {
            for (int i = 0; i < reRollers.size(); i++) {
                if (i != 0) {
                    addTI += ", ";
                }
                if (i == reRollers.size() - 1) {
                    addTI += "and ";
                }
                addTI += reRollers.get(i);
            }
            addToInfo(addTI + " must reroll", false);
            currPlayer=reRollers.get(0);
            setBackgroundPlayer();
        }
        //add to info dialog people who need to reroll
        if (!reRollersLow.isEmpty()) {
            addTI = "";
            for (int i = 0; i < reRollersLow.size(); i++) {
                if (i != 0) {
                    addTI += ", ";
                }
                if (i == reRollersLow.size() - 1) {
                    addTI += "and ";
                }
                addTI += reRollersLow.get(i);
            }
            addToInfo(addTI + " must reroll", false);
        }
        if(!reRollers.isEmpty()){
            addToInfo(currPlayer + "'s turn to roll", true);
        }
        reRHigh = reRollers;
        reRLow=reRollersLow;
        reRollsHigh=new int[reRHigh.size()];
        reRollsLow=new int[reRLow.size()];
        if ((reRollers == null || reRollers.isEmpty())
                && (reRollersLow == null || reRollersLow.isEmpty())) {
            doneRollingForFirst();
        }
    }//</editor-fold>
        /**
         * requires an array list of re rollers high and low
         * and an int[] or the rolls of the high, and an int[] of the rolls
         * of the low
         * @param reRollers
         * @param reRollersLow 
         */
    public void reRoll(ArrayList<Player> reRollers, ArrayList<Player> reRollersLow,
            int[] rollsH, int[] rollsL) {//<editor-fold>
        //reroll high
        int[] permanentRoll = rollsH;
        ArrayList<Player> reRollersTwo = new ArrayList<Player>();
        ArrayList<Player> reRollersTwoLow = new ArrayList<Player>();
        int reRollNum = 13;
        int reRollNumLow = 0;
        //all players reroll and add the reuslts to the image dialog
        for (int i = 0; i < reRollers.size(); i++) {
            //calculate the correct order based on the rolls
            for (int j = 0; j < permanentRoll.length; j++) {
                if (i == j) {
                    break;
                } else if (permanentRoll[j] == permanentRoll[i]
                        && (reRollersTwo.isEmpty() || reRollNum == permanentRoll[i])) {
                    reRollNum = permanentRoll[i];
                    if (!reRollersTwo.contains(reRollers.get(j))) {
                        reRollersTwo.add(reRollers.get(j));
                    }
                    if (!reRollersTwo.contains(reRollers.get(i))) {
                        reRollersTwo.add(reRollers.get(i));
                    }
                    break;
                } else if (permanentRoll[j] == permanentRoll[i] && (reRollersTwo.size() == 2 || reRollNumLow == permanentRoll[j])) {
                    reRollNumLow = permanentRoll[i];
                    if (!reRollersTwoLow.contains(reRollers.get(j))) {
                        reRollersTwoLow.add(reRollers.get(j));
                    }
                    if (!reRollersTwoLow.contains(reRollers.get(i))) {
                        reRollersTwoLow.add(reRollers.get(i));
                    }
                    break;
                }
            }
        }
        if (reRollNumLow > reRollNum) {
            reRollersTwoLow.add(reRollersTwo.get(0));
            reRollersTwoLow.add(reRollersTwo.get(1));
            reRollersTwo.add(reRollersTwoLow.get(0));
            reRollersTwo.add(reRollersTwoLow.get(1));
        }
        //arrange in order from greats to least
        ArrayList<Integer> largest = new ArrayList<Integer>();
        for (int i = 0; i < reRollers.size(); i++) {
            largest.add(permanentRoll[i]);
        }
        Collections.sort(largest);
        Collections.reverse(largest);
        //set order ignoring reroll players
        int currOrderIndex = 0;
        for (int i = 0; i < orderOfPlayers.length; i++) {
            if (orderOfPlayers[i] != null) {
                currOrderIndex++;
            } else {
                break;
            }
        }
        for (int i = 0; i < reRollers.size(); i++) {
            if (largest.get(i) != reRollNum && largest.get(i) != reRollNumLow) {
                for (int k = 0; k < reRollers.size(); k++) {
                    if (largest.get(i) == permanentRoll[k]) {
                        orderOfPlayers[currOrderIndex++] = reRollers.get(k);
                    }
                }
            } else if (largest.get(i) == reRollNum) {
                currOrderIndex++;
            }
        }
        reRollers.clear();
        reRollers.addAll(reRollersTwo);
        reRollersTwo.clear();

        if (reRollersLow != null && !reRollersLow.isEmpty()) {
            //reroll low
            permanentRoll = rollsL;
            reRollersTwoLow = new ArrayList<Player>();
            reRollNum = 13;
            //all players reroll and add the reuslts to the image dialog
            for (int i = 0; i < reRollersLow.size(); i++) {
                permanentRoll = rollsL;
                //calculate the correct order based on the rolls
                if (i == 1 && permanentRoll[1] > permanentRoll[0]) {
                    orderOfPlayers[2] = reRollersLow.get(1);
                    orderOfPlayers[3] = reRollersLow.get(0);
                    reRollersLow.clear();
                } else if (i == 1 && permanentRoll[1] < permanentRoll[0]) {
                    orderOfPlayers[2] = reRollersLow.get(0);
                    orderOfPlayers[3] = reRollersLow.get(1);
                    reRollersLow.clear();
                }
            }
            if (!reRollersLow.isEmpty() && reRollers.isEmpty()) {
                reRollNum = permanentRoll[0];
                reRollers.addAll(reRollersLow);
                reRollersLow.clear();
            }
        }
        //add to info dialog people who need to reroll
        String addTI = "";
        if (!reRollers.isEmpty()) {
            for (int i = 0; i < reRollers.size(); i++) {
                if (i != 0) {
                    addTI += ", ";
                }
                if (i == reRollers.size() - 1) {
                    addTI += "and ";
                }
                addTI += reRollers.get(i);
            }
            addToInfo(addTI + " must reroll", false);
            currPlayer = reRollers.get(0);
            setBackgroundPlayer();
        }
        //add to info dialog people who need to reroll
        if (!reRollersLow.isEmpty()) {
            addTI = "";
            for (int i = 0; i < reRollersLow.size(); i++) {
                if (i != 0) {
                    addTI += ", ";
                }
                if (i == reRollersLow.size() - 1) {
                    addTI += "and ";
                }
                addTI += reRollersLow.get(i);
            }
            addToInfo(addTI + " must reroll", false);
        }
        if(!reRollers.isEmpty()){
            addToInfo(currPlayer + "'s turn to roll", true);
        }
        if ((reRollers == null || reRollers.isEmpty())
                && (reRollersLow == null || reRollersLow.isEmpty())) {
            doneRollingForFirst();
        }
    }//</editor-fold>
    public void doneRollingForFirst() {
        addToInfo(orderOfPlayers[0] + " will go first", false);
        addToInfo(orderOfPlayers[1] + " will go second", false);
        addToInfo(orderOfPlayers[2] + " will go third", false);
        if (numberOfPlayers == 4) {
            addToInfo(orderOfPlayers[3] + " will go fourth", false);
        }
        doneRollingForFirst=true;
        rollToBuild();
        enableMenu();
        currPlayer=orderOfPlayers[0];
        forwardSetupTurn=false;
        tradeButton.setEnabled(false);
        buildButton.setEnabled(false);
        endTurnButton.setEnabled(false);
        setupPhaseCityGuide();
    }
    /**
     * guides the setup phase city building process
     */
    public void setupPhaseCityGuide(){
        switch (getNextPlayer().getNumberOfCities()) {
            case 0:
                nextPlayer();
                addToInfo(currPlayer + ": build your first city", true);
                buildCityButton.setVisible(true);
                break;
            case 1:
                nextPlayer();
                addToInfo(currPlayer + ": build your second city", true);
                buildCityButton.setVisible(true);
                break;
            case 2:
                nextPlayer();
                addToInfo(currPlayer + ": build your third city", true);
                buildCityButton.setVisible(true);
                break;
            case 3:
                if (fourthPlayerCities != 3 && numberOfPlayers == 3) {
                    nextPlayer();
                    buildCityButton.setVisible(true);
                    //build 4th player cities
                    fourthPlayerCityBuild = true;
                    addToInfo(currPlayer + ": build the fourth player's city", true);
                } else if (getNextPlayer().getNumberOfRailsBuilt() == 0) {
                    for (int i = 0; i < railButton.length; i++) {
                        railButton[i].setVisible(true);
                    }
                    nextPlayer();
                    addToInfo(currPlayer + ": build your rail & train", true);
                    currPlayer.displayRailPossibilities();
                    buildingRail = true;
                } else {
                    //begin play
                    currPlayer.endTurn();
                    currPlayer=orderOfPlayers[0];
                    setBackgroundPlayer();
                    currPlayer.startTurn();
                    addToInfo("Setup Phase Complete", false);
                    addToInfo(currPlayer.getName()+": You must roll first", true);
                    setupPhase = false;
                    enableMenu();
                    buildToRoll();
                    tradeButton.setEnabled(false);
                    endTurnButton.setEnabled(false);
                }
                break;
        }
    }

    /**
     * this method returns how much the city is worth in gold for
     * settling. the value doubleGold automatically updates with respect
     * to if the "double gold" settings is true
     * @param dG 
     */
    public void setDoubleGold(boolean dG){
        doubleGold=dG;
        for(int i=0;i<player.length;i++){
            player[i].setDoubleGold(dG);
        }
        for(int i=0;i<citySpot.length;i++){
            citySpot[i].setDoubleGold(dG);
        }
    }
    public void closeAllOpenWindows(){//<editor-fold>
        if(scd != null && scd.isVisible()) {
            scd.setVisible(false);
        }
        if (cityInfoWindow != null && cityInfoWindow.isVisible()) {
            cityInfoWindow.setVisible(false);
        }
        if (igom != null && igom.isVisible()) {
            igom.setVisible(false);
        }
        if (piw != null && piw.isVisible()) {
            piw.setVisible(false);
        }
        if (mmw != null && mmw.isVisible()) {
            mmw.setVisible(false);
        }
        if (bw != null && bw.isVisible()) {
            bw.setVisible(false);
        }
        if (uw != null && uw.isVisible()) {
            uw.setVisible(false);
        }
        if (tw != null && tw.isVisible()) {
            tw.setVisible(false);
        }
    }//</editor-fold>
    public void setPurple(boolean b){
        ignorePurpleDuringBuild=b;
    }
    public void setIgnoreResources(boolean b){
        ignoreResources=b;
    }
    public boolean getIgnoreResources(){
        return ignoreResources;
    }
    public void setSeeHands(){
        cheatSeeHandButton.setVisible(true);
    }
    public Intersection[] getIntersections(){
        return intersection;
    }
    public void setMovingSettler(boolean b){
        movingSettler=b;
    }
    public void buildingSettler(boolean b, boolean refund){
        //refund resources
        if(refund){
            if (movingSettler) {
                currPlayer.plusWheat();
            } else {
                currPlayer.plusCattle();
                currPlayer.plusLumber();
                currPlayer.plusWheat();
            }
        }
        buildingSettler = b;
        if (b) {
            cancelButton.setVisible(true);
        } else {
            enableMenu();
            setMovingSettler(false);
            cancelButton.setVisible(false);
            for(int i=0;i<intersection.length;i++){
                intersection[i].getButton().setVisible(false);
                intersection[i].setMoveSettler(false);
                intersection[i].setNoMoveToCity(true);
                intersection[i].setBuildSettler(false);
                if(intersection[i].hasGhostSettler()){
                    intersection[i].removeSettler();
                }
                for(int j=0;j<player.length;j++){
                    if(player[j].getSettlerIntersections().size()>0&&
                            player[j].getSettlerIntersections().contains(intersection[i])&&
                            intersection[i].getOnTheCusp()){
                        intersection[i].removeSettler();
                        intersection[i].displaySettler(player[j]);
                        player[j].plusSettlers(intersection[i]);
                        intersection[i].setSettler(true);
                        intersection[i].setOnTheCusp(false);
                        intersection[i].setGhostSettler(false);
                    }
                }
            }
            for(int i=0;i<intersection.length;i++){
                for(int j=0;j<player.length;j++){
                    if(intersection[i].getOnTheCusp() && 
                            player[j].getSettlerIntersections().contains(intersection[i])){
                        player[j].getSettlerIntersections().remove(intersection[i]);
                        intersection[i].setOnTheCusp(false);
                    }
                }
            }
            if (extraordinaryBuildingPhaseNum != 0) {
                currPlayer.extraordinaryBuildingPhase();
            }
        }
    }
    public void setMovingTrain(boolean b){
        movingTrain=b;
    }
    public void buildingTrain(boolean b, boolean refund){
        //refund resources
        if(refund){
            if (movingTrain) {
                currPlayer.plusCoal();
            } else {
                currPlayer.plusCoal();
                currPlayer.plusLumber();
                currPlayer.plusOre();
            }
        }
        buildingTrain = b;
        if (b) {
            cancelButton.setVisible(true);
        } else {
            enableMenu();
            setMovingTrain(false);
            cancelButton.setVisible(false);
            for(int i=0;i<rail.length;i++){
                rail[i].setMoveTrain(false);
                rail[i].setNoMoveToCity(true);
                rail[i].setBuildTrain(false);
                rail[i].removeGhostTrain();
                if (currPlayer.getTrainRails().size() > 0
                        && currPlayer.getTrainRails().contains(rail[i])
                        && rail[i].getOnTheCusp()) {
                    rail[i].displayTrain(currPlayer);
                    currPlayer.plusTrains(rail[i]);
                    rail[i].setTrain(true);
                    rail[i].setOnTheCusp(false);
                    rail[i].setGhostTrain(false);
                }
            }
            for(int i=0;i<rail.length;i++){
                for(int j=0;j<player.length;j++){
                    if(rail[i].getOnTheCusp() && 
                            player[j].getTrainRails().contains(rail[i])) {
                        if (player[j].getTrainRails().size() > 1) {
                            if(player[j].getTrainRails().get(0)==rail[i]){
                                player[j].getTrainRails().remove(0);
                            }else{
                                player[j].getTrainRails().remove(1);
                            }
                        }else if(player[j].getTrainRails().size()>0 && !rail[i].hasSecondTrain()){                            
                            player[j].getTrainRails().remove(rail[i]);
                        }
                        rail[i].setOnTheCusp(false);
                    }
                }
            }
            if (extraordinaryBuildingPhaseNum != 0) {
                currPlayer.extraordinaryBuildingPhase();
            }
        }
    }
    public void setBuildingRail(boolean b){
        buildingRail=b;
    }
    public boolean getBuildingRail(){
        return buildingRail;
    }
    public Player getCurrPlayer(){
        return currPlayer;
    }
    public Player[] getPlayers(){
        return player;
    }
    public boolean getSetupPhase(){
        return setupPhase;
    }
    public Rail[] getRails(){
        return rail;
    }
    public void checkWinner(){
        for(int i=0;i<player.length;i++){
            if(player[i].hasWon()){
                //winner
                closeAllOpenWindows();
                if(scd!=null&&scd.isVisible()){
                    scd.setVisible(false);
                }
                scd=new SettlersConfirmDialog(currPlayer, this);
                scd.setVisible(true);
            }
        }
    }
    public boolean getBuildingSettlement(){
        return buildingSettlement;
    }
    public void setBuildingSettlement(boolean b){
        buildingSettlement=b;
    }
    public javax.swing.JButton getTrashBinButton(){
        return trashBinButton;
    }
    public boolean getFourthPlayerCityBuild(){
        return fourthPlayerCityBuild;
    }
    public void setFourthPlayerCities(int i){
        fourthPlayerCities=i;
    }
    public int getFourthPlayerCities(){
        return fourthPlayerCities;
    }
    public JLabel getMouseCityLabel(){
        return mouseCityLabel;
    }
    public ImageIcon getRedHouse(){
        return redHouse;
    }
    public ImageIcon getGreenHouse(){
        return greenHouse;
    }
    public ImageIcon getWhiteHouse(){
        return whiteHouse;
    }
    public ImageIcon getOrangeHouse(){
        return orangeHouse;
    }
    public int getExtraordinaryBuildingPhaseNum(){
        return extraordinaryBuildingPhaseNum;
    }
    public AvaJButton getCancelButton(){
        return cancelButton;
    }
    public ArrayList<Hex> getSingleQHexes(){
        return singleQHexes;
    }
    public void setSCD(SettlersConfirmDialog d){
        scd=d;
    }
    public SettlersConfirmDialog getSCD(){
        return scd;
    }
    public boolean getIgnorePurpleDuringBuild(){
        return ignorePurpleDuringBuild;
    }
    public CityInfoDialog getCIW(){
        return cityInfoWindow;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gamePanel = new javax.swing.JPanel();
        mouseCityLabel = new javax.swing.JLabel();
        mouseBuildingCostLabel = new javax.swing.JLabel();
        labelPanel = new javax.swing.JPanel();
        trainSettlerPanel = new javax.swing.JPanel();
        intersection0 = new javax.swing.JLabel();
        intersection1 = new javax.swing.JLabel();
        intersection2 = new javax.swing.JLabel();
        intersection3 = new javax.swing.JLabel();
        intersection4 = new javax.swing.JLabel();
        intersection5 = new javax.swing.JLabel();
        intersection6 = new javax.swing.JLabel();
        intersection7 = new javax.swing.JLabel();
        intersection8 = new javax.swing.JLabel();
        intersection9 = new javax.swing.JLabel();
        intersection10 = new javax.swing.JLabel();
        intersection11 = new javax.swing.JLabel();
        intersection12 = new javax.swing.JLabel();
        intersection13 = new javax.swing.JLabel();
        intersection14 = new javax.swing.JLabel();
        intersection15 = new javax.swing.JLabel();
        intersection16 = new javax.swing.JLabel();
        intersection17 = new javax.swing.JLabel();
        intersection18 = new javax.swing.JLabel();
        intersection19 = new javax.swing.JLabel();
        intersection20 = new javax.swing.JLabel();
        intersection21 = new javax.swing.JLabel();
        intersection22 = new javax.swing.JLabel();
        intersection23 = new javax.swing.JLabel();
        intersection24 = new javax.swing.JLabel();
        intersection25 = new javax.swing.JLabel();
        intersection26 = new javax.swing.JLabel();
        intersection27 = new javax.swing.JLabel();
        intersection28 = new javax.swing.JLabel();
        intersection29 = new javax.swing.JLabel();
        intersection30 = new javax.swing.JLabel();
        intersection31 = new javax.swing.JLabel();
        intersection32 = new javax.swing.JLabel();
        intersection33 = new javax.swing.JLabel();
        intersection34 = new javax.swing.JLabel();
        intersection35 = new javax.swing.JLabel();
        intersection36 = new javax.swing.JLabel();
        intersection37 = new javax.swing.JLabel();
        intersection38 = new javax.swing.JLabel();
        intersection39 = new javax.swing.JLabel();
        intersection40 = new javax.swing.JLabel();
        intersection41 = new javax.swing.JLabel();
        intersection42 = new javax.swing.JLabel();
        intersection43 = new javax.swing.JLabel();
        intersection44 = new javax.swing.JLabel();
        intersection45 = new javax.swing.JLabel();
        intersection46 = new javax.swing.JLabel();
        intersection47 = new javax.swing.JLabel();
        intersection48 = new javax.swing.JLabel();
        intersection49 = new javax.swing.JLabel();
        intersection50 = new javax.swing.JLabel();
        intersection51 = new javax.swing.JLabel();
        intersection52 = new javax.swing.JLabel();
        intersection53 = new javax.swing.JLabel();
        intersection54 = new javax.swing.JLabel();
        intersection55 = new javax.swing.JLabel();
        intersection56 = new javax.swing.JLabel();
        intersection57 = new javax.swing.JLabel();
        intersection58 = new javax.swing.JLabel();
        intersection59 = new javax.swing.JLabel();
        intersection60 = new javax.swing.JLabel();
        intersection61 = new javax.swing.JLabel();
        intersection62 = new javax.swing.JLabel();
        intersection63 = new javax.swing.JLabel();
        intersection64 = new javax.swing.JLabel();
        intersection65 = new javax.swing.JLabel();
        intersection66 = new javax.swing.JLabel();
        intersection67 = new javax.swing.JLabel();
        intersection68 = new javax.swing.JLabel();
        intersection69 = new javax.swing.JLabel();
        intersection70 = new javax.swing.JLabel();
        intersection71 = new javax.swing.JLabel();
        intersection72 = new javax.swing.JLabel();
        intersection73 = new javax.swing.JLabel();
        intersection74 = new javax.swing.JLabel();
        intersection75 = new javax.swing.JLabel();
        intersection76 = new javax.swing.JLabel();
        intersection77 = new javax.swing.JLabel();
        intersection78 = new javax.swing.JLabel();
        intersection79 = new javax.swing.JLabel();
        intersection80 = new javax.swing.JLabel();
        intersection81 = new javax.swing.JLabel();
        intersection82 = new javax.swing.JLabel();
        intersection83 = new javax.swing.JLabel();
        intersection84 = new javax.swing.JLabel();
        intersection85 = new javax.swing.JLabel();
        intersection86 = new javax.swing.JLabel();
        intersection87 = new javax.swing.JLabel();
        intersection88 = new javax.swing.JLabel();
        intersection89 = new javax.swing.JLabel();
        intersection90 = new javax.swing.JLabel();
        intersection91 = new javax.swing.JLabel();
        intersection92 = new javax.swing.JLabel();
        intersection93 = new javax.swing.JLabel();
        intersection94 = new javax.swing.JLabel();
        intersection95 = new javax.swing.JLabel();
        intersection96 = new javax.swing.JLabel();
        intersection97 = new javax.swing.JLabel();
        intersection98 = new javax.swing.JLabel();
        intersection99 = new javax.swing.JLabel();
        intersection100 = new javax.swing.JLabel();
        intersection101 = new javax.swing.JLabel();
        intersection102 = new javax.swing.JLabel();
        intersection103 = new javax.swing.JLabel();
        intersection104 = new javax.swing.JLabel();
        intersection105 = new javax.swing.JLabel();
        intersection106 = new javax.swing.JLabel();
        intersection107 = new javax.swing.JLabel();
        intersection108 = new javax.swing.JLabel();
        intersection109 = new javax.swing.JLabel();
        intersection110 = new javax.swing.JLabel();
        intersection111 = new javax.swing.JLabel();
        intersection112 = new javax.swing.JLabel();
        intersection113 = new javax.swing.JLabel();
        intersection114 = new javax.swing.JLabel();
        intersection115 = new javax.swing.JLabel();
        intersection116 = new javax.swing.JLabel();
        intersection117 = new javax.swing.JLabel();
        intersection118 = new javax.swing.JLabel();
        intersection119 = new javax.swing.JLabel();
        intersection120 = new javax.swing.JLabel();
        intersection121 = new javax.swing.JLabel();
        intersection122 = new javax.swing.JLabel();
        intersection123 = new javax.swing.JLabel();
        intersection124 = new javax.swing.JLabel();
        intersection125 = new javax.swing.JLabel();
        intersection126 = new javax.swing.JLabel();
        intersection127 = new javax.swing.JLabel();
        intersection128 = new javax.swing.JLabel();
        intersection129 = new javax.swing.JLabel();
        intersection130 = new javax.swing.JLabel();
        intersection131 = new javax.swing.JLabel();
        intersection132 = new javax.swing.JLabel();
        intersection133 = new javax.swing.JLabel();
        intersection134 = new javax.swing.JLabel();
        intersection135 = new javax.swing.JLabel();
        intersection136 = new javax.swing.JLabel();
        intersection137 = new javax.swing.JLabel();
        intersection138 = new javax.swing.JLabel();
        intersection139 = new javax.swing.JLabel();
        intersection140 = new javax.swing.JLabel();
        intersection141 = new javax.swing.JLabel();
        intersection142 = new javax.swing.JLabel();
        intersection143 = new javax.swing.JLabel();
        intersection144 = new javax.swing.JLabel();
        intersection145 = new javax.swing.JLabel();
        intersection146 = new javax.swing.JLabel();
        intersection147 = new javax.swing.JLabel();
        intersection148 = new javax.swing.JLabel();
        buttonPanel = new javax.swing.JPanel();
        spokaneButton = new javax.swing.JButton();
        seattleButton = new javax.swing.JButton();
        portlandButton = new javax.swing.JButton();
        sanFranciscoButton = new javax.swing.JButton();
        renoButton = new javax.swing.JButton();
        losAngelesButton = new javax.swing.JButton();
        lasVegasButton = new javax.swing.JButton();
        boiseButton = new javax.swing.JButton();
        helenaButton = new javax.swing.JButton();
        idahoFallsButton = new javax.swing.JButton();
        saltLakeCityButton = new javax.swing.JButton();
        flagstaffButton = new javax.swing.JButton();
        billingsButton = new javax.swing.JButton();
        casperButton = new javax.swing.JButton();
        rapidCityButton = new javax.swing.JButton();
        denverButton = new javax.swing.JButton();
        santaFeButton = new javax.swing.JButton();
        elPasoButton = new javax.swing.JButton();
        amarilloButton = new javax.swing.JButton();
        bismarkButton = new javax.swing.JButton();
        sanAntonioButton = new javax.swing.JButton();
        dallasButton = new javax.swing.JButton();
        wichitaButton = new javax.swing.JButton();
        omahaButton = new javax.swing.JButton();
        minneapolisButton = new javax.swing.JButton();
        littleRockButton = new javax.swing.JButton();
        shreveportButton = new javax.swing.JButton();
        houstonButton = new javax.swing.JButton();
        stLouisButton = new javax.swing.JButton();
        jacksonButton = new javax.swing.JButton();
        chicagoButton = new javax.swing.JButton();
        indianapolisButton = new javax.swing.JButton();
        nashvilleButton = new javax.swing.JButton();
        newOrleansButton = new javax.swing.JButton();
        columbusButton = new javax.swing.JButton();
        lexingtonButton = new javax.swing.JButton();
        atlantaButton = new javax.swing.JButton();
        pensacolaButton = new javax.swing.JButton();
        albanyButton = new javax.swing.JButton();
        washingtonDCButton = new javax.swing.JButton();
        raleighButton = new javax.swing.JButton();
        columbiaButton = new javax.swing.JButton();
        jacksonvilleButton = new javax.swing.JButton();
        newYorkButton = new javax.swing.JButton();
        bostonButton = new javax.swing.JButton();
        railButton0 = new javax.swing.JButton();
        railButton1 = new javax.swing.JButton();
        railButton2 = new javax.swing.JButton();
        railButton3 = new javax.swing.JButton();
        railButton4 = new javax.swing.JButton();
        railButton5 = new javax.swing.JButton();
        railButton6 = new javax.swing.JButton();
        railButton7 = new javax.swing.JButton();
        railButton8 = new javax.swing.JButton();
        railButton9 = new javax.swing.JButton();
        railButton10 = new javax.swing.JButton();
        railButton11 = new javax.swing.JButton();
        railButton12 = new javax.swing.JButton();
        railButton13 = new javax.swing.JButton();
        railButton14 = new javax.swing.JButton();
        railButton15 = new javax.swing.JButton();
        railButton16 = new javax.swing.JButton();
        railButton17 = new javax.swing.JButton();
        railButton18 = new javax.swing.JButton();
        railButton19 = new javax.swing.JButton();
        railButton20 = new javax.swing.JButton();
        railButton21 = new javax.swing.JButton();
        railButton22 = new javax.swing.JButton();
        railButton23 = new javax.swing.JButton();
        railButton24 = new javax.swing.JButton();
        railButton25 = new javax.swing.JButton();
        railButton26 = new javax.swing.JButton();
        railButton27 = new javax.swing.JButton();
        railButton28 = new javax.swing.JButton();
        railButton29 = new javax.swing.JButton();
        railButton30 = new javax.swing.JButton();
        railButton31 = new javax.swing.JButton();
        railButton32 = new javax.swing.JButton();
        railButton33 = new javax.swing.JButton();
        railButton34 = new javax.swing.JButton();
        railButton35 = new javax.swing.JButton();
        railButton36 = new javax.swing.JButton();
        railButton37 = new javax.swing.JButton();
        railButton38 = new javax.swing.JButton();
        railButton39 = new javax.swing.JButton();
        railButton40 = new javax.swing.JButton();
        railButton41 = new javax.swing.JButton();
        railButton42 = new javax.swing.JButton();
        railButton43 = new javax.swing.JButton();
        railButton44 = new javax.swing.JButton();
        railButton45 = new javax.swing.JButton();
        railButton46 = new javax.swing.JButton();
        railButton47 = new javax.swing.JButton();
        railButton48 = new javax.swing.JButton();
        railButton49 = new javax.swing.JButton();
        railButton50 = new javax.swing.JButton();
        railButton51 = new javax.swing.JButton();
        railButton52 = new javax.swing.JButton();
        railButton53 = new javax.swing.JButton();
        railButton54 = new javax.swing.JButton();
        railButton55 = new javax.swing.JButton();
        railButton56 = new javax.swing.JButton();
        railButton57 = new javax.swing.JButton();
        railButton58 = new javax.swing.JButton();
        railButton59 = new javax.swing.JButton();
        railButton60 = new javax.swing.JButton();
        railButton61 = new javax.swing.JButton();
        railButton62 = new javax.swing.JButton();
        railButton63 = new javax.swing.JButton();
        railButton64 = new javax.swing.JButton();
        railButton65 = new javax.swing.JButton();
        railButton66 = new javax.swing.JButton();
        railButton67 = new javax.swing.JButton();
        railButton68 = new javax.swing.JButton();
        railButton69 = new javax.swing.JButton();
        railButton70 = new javax.swing.JButton();
        railButton71 = new javax.swing.JButton();
        railButton72 = new javax.swing.JButton();
        railButton73 = new javax.swing.JButton();
        railButton74 = new javax.swing.JButton();
        railButton75 = new javax.swing.JButton();
        railButton76 = new javax.swing.JButton();
        railButton77 = new javax.swing.JButton();
        railButton78 = new javax.swing.JButton();
        railButton79 = new javax.swing.JButton();
        railButton80 = new javax.swing.JButton();
        railButton81 = new javax.swing.JButton();
        railButton82 = new javax.swing.JButton();
        railButton83 = new javax.swing.JButton();
        railButton84 = new javax.swing.JButton();
        railButton85 = new javax.swing.JButton();
        railButton86 = new javax.swing.JButton();
        railButton87 = new javax.swing.JButton();
        railButton88 = new javax.swing.JButton();
        railButton89 = new javax.swing.JButton();
        railButton90 = new javax.swing.JButton();
        railButton91 = new javax.swing.JButton();
        railButton92 = new javax.swing.JButton();
        railButton93 = new javax.swing.JButton();
        railButton94 = new javax.swing.JButton();
        railButton95 = new javax.swing.JButton();
        railButton96 = new javax.swing.JButton();
        railButton97 = new javax.swing.JButton();
        railButton98 = new javax.swing.JButton();
        railButton99 = new javax.swing.JButton();
        railButton100 = new javax.swing.JButton();
        railButton101 = new javax.swing.JButton();
        railButton102 = new javax.swing.JButton();
        railButton103 = new javax.swing.JButton();
        railButton104 = new javax.swing.JButton();
        railButton105 = new javax.swing.JButton();
        railButton106 = new javax.swing.JButton();
        railButton107 = new javax.swing.JButton();
        railButton108 = new javax.swing.JButton();
        railButton109 = new javax.swing.JButton();
        railButton110 = new javax.swing.JButton();
        railButton111 = new javax.swing.JButton();
        railButton112 = new javax.swing.JButton();
        railButton113 = new javax.swing.JButton();
        railButton114 = new javax.swing.JButton();
        railButton115 = new javax.swing.JButton();
        railButton116 = new javax.swing.JButton();
        railButton117 = new javax.swing.JButton();
        railButton118 = new javax.swing.JButton();
        railButton119 = new javax.swing.JButton();
        railButton120 = new javax.swing.JButton();
        railButton121 = new javax.swing.JButton();
        railButton122 = new javax.swing.JButton();
        railButton123 = new javax.swing.JButton();
        railButton124 = new javax.swing.JButton();
        railButton125 = new javax.swing.JButton();
        railButton126 = new javax.swing.JButton();
        railButton127 = new javax.swing.JButton();
        railButton128 = new javax.swing.JButton();
        railButton129 = new javax.swing.JButton();
        railButton130 = new javax.swing.JButton();
        railButton131 = new javax.swing.JButton();
        railButton132 = new javax.swing.JButton();
        railButton133 = new javax.swing.JButton();
        railButton134 = new javax.swing.JButton();
        railButton135 = new javax.swing.JButton();
        railButton136 = new javax.swing.JButton();
        railButton137 = new javax.swing.JButton();
        railButton138 = new javax.swing.JButton();
        railButton139 = new javax.swing.JButton();
        railButton140 = new javax.swing.JButton();
        railButton141 = new javax.swing.JButton();
        railButton142 = new javax.swing.JButton();
        railButton143 = new javax.swing.JButton();
        railButton144 = new javax.swing.JButton();
        railButton145 = new javax.swing.JButton();
        railButton146 = new javax.swing.JButton();
        railButton147 = new javax.swing.JButton();
        railButton148 = new javax.swing.JButton();
        railButton149 = new javax.swing.JButton();
        railButton150 = new javax.swing.JButton();
        railButton151 = new javax.swing.JButton();
        railButton152 = new javax.swing.JButton();
        railButton153 = new javax.swing.JButton();
        railButton154 = new javax.swing.JButton();
        railButton155 = new javax.swing.JButton();
        railButton156 = new javax.swing.JButton();
        railButton157 = new javax.swing.JButton();
        railButton158 = new javax.swing.JButton();
        railButton159 = new javax.swing.JButton();
        railButton160 = new javax.swing.JButton();
        railButton161 = new javax.swing.JButton();
        railButton162 = new javax.swing.JButton();
        railButton163 = new javax.swing.JButton();
        railButton164 = new javax.swing.JButton();
        railButton165 = new javax.swing.JButton();
        railButton166 = new javax.swing.JButton();
        railButton167 = new javax.swing.JButton();
        railButton168 = new javax.swing.JButton();
        railButton169 = new javax.swing.JButton();
        railButton170 = new javax.swing.JButton();
        railButton171 = new javax.swing.JButton();
        railButton172 = new javax.swing.JButton();
        railButton173 = new javax.swing.JButton();
        railButton174 = new javax.swing.JButton();
        railButton175 = new javax.swing.JButton();
        railButton176 = new javax.swing.JButton();
        railButton177 = new javax.swing.JButton();
        railButton178 = new javax.swing.JButton();
        railButton179 = new javax.swing.JButton();
        railButton180 = new javax.swing.JButton();
        railButton181 = new javax.swing.JButton();
        railButton182 = new javax.swing.JButton();
        railButton183 = new javax.swing.JButton();
        railButton184 = new javax.swing.JButton();
        railButton185 = new javax.swing.JButton();
        railButton186 = new javax.swing.JButton();
        railButton187 = new javax.swing.JButton();
        railButton188 = new javax.swing.JButton();
        railButton189 = new javax.swing.JButton();
        railButton190 = new javax.swing.JButton();
        railButton191 = new javax.swing.JButton();
        railButton192 = new javax.swing.JButton();
        railButton193 = new javax.swing.JButton();
        railButton195 = new javax.swing.JButton();
        railButton194 = new javax.swing.JButton();
        railButton196 = new javax.swing.JButton();
        railButton197 = new javax.swing.JButton();
        railButton198 = new javax.swing.JButton();
        railButton199 = new javax.swing.JButton();
        railButton200 = new javax.swing.JButton();
        railButton201 = new javax.swing.JButton();
        railButton202 = new javax.swing.JButton();
        railButton203 = new javax.swing.JButton();
        railButton204 = new javax.swing.JButton();
        railButton205 = new javax.swing.JButton();
        railButton206 = new javax.swing.JButton();
        railButton207 = new javax.swing.JButton();
        railButton208 = new javax.swing.JButton();
        railButton209 = new javax.swing.JButton();
        railButton210 = new javax.swing.JButton();
        railButton211 = new javax.swing.JButton();
        railButton212 = new javax.swing.JButton();
        railButton213 = new javax.swing.JButton();
        railButton214 = new javax.swing.JButton();
        railButton215 = new javax.swing.JButton();
        seeAllButton1 = new javax.swing.JButton();
        dicePanel = new javax.swing.JPanel();
        diceOneLabel = new javax.swing.JLabel();
        diceTwoLabel = new javax.swing.JLabel();
        infoDialog = new javax.swing.JPanel();
        infoDialogOne = new javax.swing.JLabel();
        infoDialogTwo = new javax.swing.JLabel();
        infoDialogThree = new javax.swing.JLabel();
        infoDialogFour = new javax.swing.JLabel();
        infoDialogFive = new javax.swing.JLabel();
        buildCityButton1 = new javax.swing.JButton();
        trashBinButton = new javax.swing.JButton();
        displayCityNamesToggleButton = new javax.swing.JToggleButton();
        refreshGameFrameLabel = new javax.swing.JLabel();
        cancelButton1 = new javax.swing.JButton();
        developmentCardButton1 = new javax.swing.JButton();
        hexButton1 = new javax.swing.JButton();
        hexButton2 = new javax.swing.JButton();
        hexButton3 = new javax.swing.JButton();
        hexButton4 = new javax.swing.JButton();
        hexButton5 = new javax.swing.JButton();
        hexButton6 = new javax.swing.JButton();
        hexButton7 = new javax.swing.JButton();
        hexButton8 = new javax.swing.JButton();
        hexButton9 = new javax.swing.JButton();
        hexButton10 = new javax.swing.JButton();
        hexButton11 = new javax.swing.JButton();
        hexButton12 = new javax.swing.JButton();
        hexButton13 = new javax.swing.JButton();
        hexButton14 = new javax.swing.JButton();
        hexButton15 = new javax.swing.JButton();
        hexButton16 = new javax.swing.JButton();
        hexButton17 = new javax.swing.JButton();
        hexButton18 = new javax.swing.JButton();
        hexButton19 = new javax.swing.JButton();
        hexButton20 = new javax.swing.JButton();
        hexButton21 = new javax.swing.JButton();
        hexButton22 = new javax.swing.JButton();
        hexButton23 = new javax.swing.JButton();
        hexButton24 = new javax.swing.JButton();
        hexButton25 = new javax.swing.JButton();
        hexButton26 = new javax.swing.JButton();
        hexButton27 = new javax.swing.JButton();
        hexButton28 = new javax.swing.JButton();
        hexButton29 = new javax.swing.JButton();
        hexButton30 = new javax.swing.JButton();
        hexButton31 = new javax.swing.JButton();
        hexButton32 = new javax.swing.JButton();
        hexButton33 = new javax.swing.JButton();
        hexButton34 = new javax.swing.JButton();
        hexButton35 = new javax.swing.JButton();
        hexButton36 = new javax.swing.JButton();
        hexButton37 = new javax.swing.JButton();
        hexButton38 = new javax.swing.JButton();
        hexButton39 = new javax.swing.JButton();
        hexButton40 = new javax.swing.JButton();
        hexButton41 = new javax.swing.JButton();
        hexButton42 = new javax.swing.JButton();
        hexButton43 = new javax.swing.JButton();
        hexButton44 = new javax.swing.JButton();
        hexButton45 = new javax.swing.JButton();
        hexButton46 = new javax.swing.JButton();
        hexButton47 = new javax.swing.JButton();
        hexButton48 = new javax.swing.JButton();
        hexButton49 = new javax.swing.JButton();
        hexButton50 = new javax.swing.JButton();
        hexButton51 = new javax.swing.JButton();
        hexButton52 = new javax.swing.JButton();
        hexButton53 = new javax.swing.JButton();
        hexButton54 = new javax.swing.JButton();
        hexButton55 = new javax.swing.JButton();
        hexButton56 = new javax.swing.JButton();
        hexButton57 = new javax.swing.JButton();
        hexButton58 = new javax.swing.JButton();
        hexButton59 = new javax.swing.JButton();
        hexButton60 = new javax.swing.JButton();
        hexButton61 = new javax.swing.JButton();
        hexButton62 = new javax.swing.JButton();
        hexButton63 = new javax.swing.JButton();
        hexButton64 = new javax.swing.JButton();
        hexButton65 = new javax.swing.JButton();
        hexButton66 = new javax.swing.JButton();
        hexButton67 = new javax.swing.JButton();
        hexButton68 = new javax.swing.JButton();
        hexButton69 = new javax.swing.JButton();
        hexButton70 = new javax.swing.JButton();
        hexButton71 = new javax.swing.JButton();
        buildingCostButton1 = new javax.swing.JButton();
        robberSpotPanel = new javax.swing.JPanel();
        robberLabel20 = new javax.swing.JLabel();
        robberLabel19 = new javax.swing.JLabel();
        robberLabel18 = new javax.swing.JLabel();
        robberLabel17 = new javax.swing.JLabel();
        robberLabel16 = new javax.swing.JLabel();
        robberLabel15 = new javax.swing.JLabel();
        robberLabel14 = new javax.swing.JLabel();
        robberLabel13 = new javax.swing.JLabel();
        robberLabel12 = new javax.swing.JLabel();
        robberLabel11 = new javax.swing.JLabel();
        robberLabel10 = new javax.swing.JLabel();
        robberLabel9 = new javax.swing.JLabel();
        robberLabel8 = new javax.swing.JLabel();
        robberLabel7 = new javax.swing.JLabel();
        robberLabel6 = new javax.swing.JLabel();
        robberLabel5 = new javax.swing.JLabel();
        robberLabel4 = new javax.swing.JLabel();
        robberLabel3 = new javax.swing.JLabel();
        robberLabel2 = new javax.swing.JLabel();
        robberLabel1 = new javax.swing.JLabel();
        robberLabel0 = new javax.swing.JLabel();
        robberStartLabel = new javax.swing.JLabel();
        robberLabel21 = new javax.swing.JLabel();
        robberLabel22 = new javax.swing.JLabel();
        robberLabel23 = new javax.swing.JLabel();
        robberLabel24 = new javax.swing.JLabel();
        robberLabel25 = new javax.swing.JLabel();
        robberLabel26 = new javax.swing.JLabel();
        robberLabel27 = new javax.swing.JLabel();
        robberLabel28 = new javax.swing.JLabel();
        robberLabel29 = new javax.swing.JLabel();
        robberLabel30 = new javax.swing.JLabel();
        robberLabel31 = new javax.swing.JLabel();
        robberLabel32 = new javax.swing.JLabel();
        robberLabel33 = new javax.swing.JLabel();
        robberLabel34 = new javax.swing.JLabel();
        robberLabel35 = new javax.swing.JLabel();
        robberLabel36 = new javax.swing.JLabel();
        robberLabel37 = new javax.swing.JLabel();
        robberLabel38 = new javax.swing.JLabel();
        robberLabel39 = new javax.swing.JLabel();
        robberLabel40 = new javax.swing.JLabel();
        robberLabel41 = new javax.swing.JLabel();
        robberLabel42 = new javax.swing.JLabel();
        robberLabel43 = new javax.swing.JLabel();
        robberLabel44 = new javax.swing.JLabel();
        robberLabel45 = new javax.swing.JLabel();
        robberLabel46 = new javax.swing.JLabel();
        robberLabel47 = new javax.swing.JLabel();
        robberLabel48 = new javax.swing.JLabel();
        robberLabel49 = new javax.swing.JLabel();
        robberLabel50 = new javax.swing.JLabel();
        robberLabel51 = new javax.swing.JLabel();
        robberLabel52 = new javax.swing.JLabel();
        robberLabel53 = new javax.swing.JLabel();
        robberLabel54 = new javax.swing.JLabel();
        robberLabel55 = new javax.swing.JLabel();
        robberLabel56 = new javax.swing.JLabel();
        robberLabel57 = new javax.swing.JLabel();
        robberLabel58 = new javax.swing.JLabel();
        robberLabel59 = new javax.swing.JLabel();
        robberLabel60 = new javax.swing.JLabel();
        robberLabel61 = new javax.swing.JLabel();
        robberLabel62 = new javax.swing.JLabel();
        robberLabel63 = new javax.swing.JLabel();
        robberLabel64 = new javax.swing.JLabel();
        robberLabel65 = new javax.swing.JLabel();
        robberLabel66 = new javax.swing.JLabel();
        robberLabel67 = new javax.swing.JLabel();
        robberLabel68 = new javax.swing.JLabel();
        robberLabel69 = new javax.swing.JLabel();
        oreCardLabel = new javax.swing.JLabel();
        lumberCardLabel = new javax.swing.JLabel();
        coalCardLabel = new javax.swing.JLabel();
        wheatCardLabel = new javax.swing.JLabel();
        cattleCardLabel = new javax.swing.JLabel();
        xLabel = new javax.swing.JLabel();
        xLabel1 = new javax.swing.JLabel();
        xLabel2 = new javax.swing.JLabel();
        xLabel3 = new javax.swing.JLabel();
        xLabel4 = new javax.swing.JLabel();
        xLabel5 = new javax.swing.JLabel();
        numberOfCoalLabel = new javax.swing.JLabel();
        numberOfLumberLabel = new javax.swing.JLabel();
        numberOfWheatLabel = new javax.swing.JLabel();
        numberOfCattleLabel = new javax.swing.JLabel();
        numberOfOreLabel = new javax.swing.JLabel();
        goldCoinLabel = new javax.swing.JLabel();
        numberOfGoldLabel = new javax.swing.JLabel();
        questionChitPanel = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        background = new javax.swing.JLabel();
        menuPanel = new javax.swing.JPanel();
        goodsSidePanel = new javax.swing.JPanel();
        redInfoButton1 = new javax.swing.JButton();
        greenInfoButton1 = new javax.swing.JButton();
        whiteInfoButton1 = new javax.swing.JButton();
        orangeInfoButton1 = new javax.swing.JButton();
        errorRedLabel = new javax.swing.JLabel();
        errorGreenLabel = new javax.swing.JLabel();
        errorWhiteLabel = new javax.swing.JLabel();
        errorOrangeLabel = new javax.swing.JLabel();
        redTextTrackCircleLabel = new javax.swing.JLabel();
        greenTextTrackCircleLabel = new javax.swing.JLabel();
        whiteTextTrackCircleLabel = new javax.swing.JLabel();
        orangeTextTrackCircleLabel = new javax.swing.JLabel();
        orangeTrackCircleLabel = new javax.swing.JLabel();
        whiteTrackCircleLabel = new javax.swing.JLabel();
        greenTrackCircleLabel = new javax.swing.JLabel();
        redTrackCircleLabel = new javax.swing.JLabel();
        buttonMenuPanel = new javax.swing.JPanel();
        buildButton1 = new javax.swing.JButton();
        rollButton1 = new javax.swing.JButton();
        tradeButton1 = new javax.swing.JButton();
        utilitiesButton1 = new javax.swing.JButton();
        endTurnButton1 = new javax.swing.JButton();
        optionsButton1 = new javax.swing.JButton();
        exitButton1 = new javax.swing.JButton();
        controlsLabel = new javax.swing.JLabel();
        backgroundSideMenu = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1025, 702));
        setResizable(false);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        gamePanel.setMinimumSize(new java.awt.Dimension(1025, 702));
        gamePanel.setPreferredSize(new java.awt.Dimension(1025, 702));
        gamePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        gamePanel.add(mouseCityLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(-100, 100, -1, -1));

        mouseBuildingCostLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/buildingCostsCard.png"))); // NOI18N
        mouseBuildingCostLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mouseBuildingCostLabelMouseExited(evt);
            }
        });
        gamePanel.add(mouseBuildingCostLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(-400, 100, -1, -1));

        labelPanel.setOpaque(false);
        labelPanel.setPreferredSize(new java.awt.Dimension(1025, 702));
        labelPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        gamePanel.add(labelPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        trainSettlerPanel.setOpaque(false);
        trainSettlerPanel.setPreferredSize(new java.awt.Dimension(1025, 702));
        trainSettlerPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        trainSettlerPanel.add(intersection0, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 153, -1, -1));
        trainSettlerPanel.add(intersection1, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 193, -1, -1));
        trainSettlerPanel.add(intersection2, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 232, -1, -1));
        trainSettlerPanel.add(intersection3, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 272, -1, -1));
        trainSettlerPanel.add(intersection4, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 311, -1, -1));
        trainSettlerPanel.add(intersection5, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 353, -1, -1));
        trainSettlerPanel.add(intersection6, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 392, -1, -1));
        trainSettlerPanel.add(intersection7, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 431, -1, -1));
        trainSettlerPanel.add(intersection8, new org.netbeans.lib.awtextra.AbsoluteConstraints(109, 150, -1, -1));
        trainSettlerPanel.add(intersection9, new org.netbeans.lib.awtextra.AbsoluteConstraints(87, 192, -1, -1));
        trainSettlerPanel.add(intersection10, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 230, -1, -1));
        trainSettlerPanel.add(intersection11, new org.netbeans.lib.awtextra.AbsoluteConstraints(87, 271, -1, -1));
        trainSettlerPanel.add(intersection12, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 312, -1, -1));
        trainSettlerPanel.add(intersection13, new org.netbeans.lib.awtextra.AbsoluteConstraints(97, 355, -1, -1));
        trainSettlerPanel.add(intersection14, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 393, -1, -1));
        trainSettlerPanel.add(intersection15, new org.netbeans.lib.awtextra.AbsoluteConstraints(84, 432, -1, -1));
        trainSettlerPanel.add(intersection16, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 473, -1, -1));
        trainSettlerPanel.add(intersection17, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 151, -1, -1));
        trainSettlerPanel.add(intersection18, new org.netbeans.lib.awtextra.AbsoluteConstraints(175, 189, -1, -1));
        trainSettlerPanel.add(intersection19, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 230, -1, -1));
        trainSettlerPanel.add(intersection20, new org.netbeans.lib.awtextra.AbsoluteConstraints(176, 270, -1, -1));
        trainSettlerPanel.add(intersection21, new org.netbeans.lib.awtextra.AbsoluteConstraints(154, 311, -1, -1));
        trainSettlerPanel.add(intersection22, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 352, -1, -1));
        trainSettlerPanel.add(intersection23, new org.netbeans.lib.awtextra.AbsoluteConstraints(152, 391, -1, -1));
        trainSettlerPanel.add(intersection24, new org.netbeans.lib.awtextra.AbsoluteConstraints(182, 433, -1, -1));
        trainSettlerPanel.add(intersection25, new org.netbeans.lib.awtextra.AbsoluteConstraints(149, 472, -1, -1));
        trainSettlerPanel.add(intersection26, new org.netbeans.lib.awtextra.AbsoluteConstraints(173, 514, -1, -1));
        trainSettlerPanel.add(intersection27, new org.netbeans.lib.awtextra.AbsoluteConstraints(244, 148, -1, -1));
        trainSettlerPanel.add(intersection28, new org.netbeans.lib.awtextra.AbsoluteConstraints(232, 190, -1, -1));
        trainSettlerPanel.add(intersection29, new org.netbeans.lib.awtextra.AbsoluteConstraints(243, 228, -1, -1));
        trainSettlerPanel.add(intersection30, new org.netbeans.lib.awtextra.AbsoluteConstraints(232, 272, -1, -1));
        trainSettlerPanel.add(intersection31, new org.netbeans.lib.awtextra.AbsoluteConstraints(244, 312, -1, -1));
        trainSettlerPanel.add(intersection32, new org.netbeans.lib.awtextra.AbsoluteConstraints(233, 352, -1, -1));
        trainSettlerPanel.add(intersection33, new org.netbeans.lib.awtextra.AbsoluteConstraints(242, 392, -1, -1));
        trainSettlerPanel.add(intersection34, new org.netbeans.lib.awtextra.AbsoluteConstraints(218, 432, -1, -1));
        trainSettlerPanel.add(intersection35, new org.netbeans.lib.awtextra.AbsoluteConstraints(249, 474, -1, -1));
        trainSettlerPanel.add(intersection36, new org.netbeans.lib.awtextra.AbsoluteConstraints(218, 514, -1, -1));
        trainSettlerPanel.add(intersection37, new org.netbeans.lib.awtextra.AbsoluteConstraints(239, 555, -1, -1));
        trainSettlerPanel.add(intersection38, new org.netbeans.lib.awtextra.AbsoluteConstraints(291, 148, -1, -1));
        trainSettlerPanel.add(intersection39, new org.netbeans.lib.awtextra.AbsoluteConstraints(322, 189, -1, -1));
        trainSettlerPanel.add(intersection40, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 228, -1, -1));
        trainSettlerPanel.add(intersection41, new org.netbeans.lib.awtextra.AbsoluteConstraints(321, 270, -1, -1));
        trainSettlerPanel.add(intersection42, new org.netbeans.lib.awtextra.AbsoluteConstraints(289, 310, -1, -1));
        trainSettlerPanel.add(intersection43, new org.netbeans.lib.awtextra.AbsoluteConstraints(313, 351, -1, -1));
        trainSettlerPanel.add(intersection44, new org.netbeans.lib.awtextra.AbsoluteConstraints(288, 391, -1, -1));
        trainSettlerPanel.add(intersection45, new org.netbeans.lib.awtextra.AbsoluteConstraints(312, 434, -1, -1));
        trainSettlerPanel.add(intersection46, new org.netbeans.lib.awtextra.AbsoluteConstraints(285, 473, -1, -1));
        trainSettlerPanel.add(intersection47, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 515, -1, -1));
        trainSettlerPanel.add(intersection48, new org.netbeans.lib.awtextra.AbsoluteConstraints(285, 556, -1, -1));
        trainSettlerPanel.add(intersection49, new org.netbeans.lib.awtextra.AbsoluteConstraints(383, 148, -1, -1));
        trainSettlerPanel.add(intersection50, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 189, -1, -1));
        trainSettlerPanel.add(intersection51, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 230, -1, -1));
        trainSettlerPanel.add(intersection52, new org.netbeans.lib.awtextra.AbsoluteConstraints(359, 270, -1, -1));
        trainSettlerPanel.add(intersection53, new org.netbeans.lib.awtextra.AbsoluteConstraints(381, 311, -1, -1));
        trainSettlerPanel.add(intersection54, new org.netbeans.lib.awtextra.AbsoluteConstraints(369, 351, -1, -1));
        trainSettlerPanel.add(intersection55, new org.netbeans.lib.awtextra.AbsoluteConstraints(382, 395, -1, -1));
        trainSettlerPanel.add(intersection56, new org.netbeans.lib.awtextra.AbsoluteConstraints(369, 435, -1, -1));
        trainSettlerPanel.add(intersection57, new org.netbeans.lib.awtextra.AbsoluteConstraints(379, 475, -1, -1));
        trainSettlerPanel.add(intersection58, new org.netbeans.lib.awtextra.AbsoluteConstraints(356, 516, -1, -1));
        trainSettlerPanel.add(intersection59, new org.netbeans.lib.awtextra.AbsoluteConstraints(388, 557, -1, -1));
        trainSettlerPanel.add(intersection60, new org.netbeans.lib.awtextra.AbsoluteConstraints(452, 111, -1, -1));
        trainSettlerPanel.add(intersection61, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 148, -1, -1));
        trainSettlerPanel.add(intersection62, new org.netbeans.lib.awtextra.AbsoluteConstraints(462, 190, -1, -1));
        trainSettlerPanel.add(intersection63, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 229, -1, -1));
        trainSettlerPanel.add(intersection64, new org.netbeans.lib.awtextra.AbsoluteConstraints(454, 272, -1, -1));
        trainSettlerPanel.add(intersection65, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 311, -1, -1));
        trainSettlerPanel.add(intersection66, new org.netbeans.lib.awtextra.AbsoluteConstraints(454, 355, -1, -1));
        trainSettlerPanel.add(intersection67, new org.netbeans.lib.awtextra.AbsoluteConstraints(429, 395, -1, -1));
        trainSettlerPanel.add(intersection68, new org.netbeans.lib.awtextra.AbsoluteConstraints(453, 436, -1, -1));
        trainSettlerPanel.add(intersection69, new org.netbeans.lib.awtextra.AbsoluteConstraints(442, 477, -1, -1));
        trainSettlerPanel.add(intersection70, new org.netbeans.lib.awtextra.AbsoluteConstraints(454, 516, -1, -1));
        trainSettlerPanel.add(intersection71, new org.netbeans.lib.awtextra.AbsoluteConstraints(428, 555, -1, -1));
        trainSettlerPanel.add(intersection72, new org.netbeans.lib.awtextra.AbsoluteConstraints(453, 597, -1, -1));
        trainSettlerPanel.add(intersection73, new org.netbeans.lib.awtextra.AbsoluteConstraints(503, 109, -1, -1));
        trainSettlerPanel.add(intersection74, new org.netbeans.lib.awtextra.AbsoluteConstraints(526, 149, -1, -1));
        trainSettlerPanel.add(intersection75, new org.netbeans.lib.awtextra.AbsoluteConstraints(503, 189, -1, -1));
        trainSettlerPanel.add(intersection76, new org.netbeans.lib.awtextra.AbsoluteConstraints(524, 231, -1, -1));
        trainSettlerPanel.add(intersection77, new org.netbeans.lib.awtextra.AbsoluteConstraints(502, 272, -1, -1));
        trainSettlerPanel.add(intersection78, new org.netbeans.lib.awtextra.AbsoluteConstraints(532, 311, -1, -1));
        trainSettlerPanel.add(intersection79, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 355, -1, -1));
        trainSettlerPanel.add(intersection80, new org.netbeans.lib.awtextra.AbsoluteConstraints(533, 393, -1, -1));
        trainSettlerPanel.add(intersection81, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 435, -1, -1));
        trainSettlerPanel.add(intersection82, new org.netbeans.lib.awtextra.AbsoluteConstraints(523, 474, -1, -1));
        trainSettlerPanel.add(intersection83, new org.netbeans.lib.awtextra.AbsoluteConstraints(512, 517, -1, -1));
        trainSettlerPanel.add(intersection84, new org.netbeans.lib.awtextra.AbsoluteConstraints(523, 557, -1, -1));
        trainSettlerPanel.add(intersection85, new org.netbeans.lib.awtextra.AbsoluteConstraints(512, 599, -1, -1));
        trainSettlerPanel.add(intersection86, new org.netbeans.lib.awtextra.AbsoluteConstraints(523, 639, -1, -1));
        trainSettlerPanel.add(intersection87, new org.netbeans.lib.awtextra.AbsoluteConstraints(593, 108, -1, -1));
        trainSettlerPanel.add(intersection88, new org.netbeans.lib.awtextra.AbsoluteConstraints(572, 149, -1, -1));
        trainSettlerPanel.add(intersection89, new org.netbeans.lib.awtextra.AbsoluteConstraints(598, 191, -1, -1));
        trainSettlerPanel.add(intersection90, new org.netbeans.lib.awtextra.AbsoluteConstraints(564, 230, -1, -1));
        trainSettlerPanel.add(intersection91, new org.netbeans.lib.awtextra.AbsoluteConstraints(594, 270, -1, -1));
        trainSettlerPanel.add(intersection92, new org.netbeans.lib.awtextra.AbsoluteConstraints(572, 310, -1, -1));
        trainSettlerPanel.add(intersection93, new org.netbeans.lib.awtextra.AbsoluteConstraints(594, 349, -1, -1));
        trainSettlerPanel.add(intersection94, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 392, -1, -1));
        trainSettlerPanel.add(intersection95, new org.netbeans.lib.awtextra.AbsoluteConstraints(582, 434, -1, -1));
        trainSettlerPanel.add(intersection96, new org.netbeans.lib.awtextra.AbsoluteConstraints(569, 473, -1, -1));
        trainSettlerPanel.add(intersection97, new org.netbeans.lib.awtextra.AbsoluteConstraints(581, 516, -1, -1));
        trainSettlerPanel.add(intersection98, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 556, -1, -1));
        trainSettlerPanel.add(intersection99, new org.netbeans.lib.awtextra.AbsoluteConstraints(583, 596, -1, -1));
        trainSettlerPanel.add(intersection100, new org.netbeans.lib.awtextra.AbsoluteConstraints(667, 147, -1, -1));
        trainSettlerPanel.add(intersection101, new org.netbeans.lib.awtextra.AbsoluteConstraints(642, 190, -1, -1));
        trainSettlerPanel.add(intersection102, new org.netbeans.lib.awtextra.AbsoluteConstraints(668, 231, -1, -1));
        trainSettlerPanel.add(intersection103, new org.netbeans.lib.awtextra.AbsoluteConstraints(643, 268, -1, -1));
        trainSettlerPanel.add(intersection104, new org.netbeans.lib.awtextra.AbsoluteConstraints(666, 311, -1, -1));
        trainSettlerPanel.add(intersection105, new org.netbeans.lib.awtextra.AbsoluteConstraints(632, 350, -1, -1));
        trainSettlerPanel.add(intersection106, new org.netbeans.lib.awtextra.AbsoluteConstraints(663, 390, -1, -1));
        trainSettlerPanel.add(intersection107, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 430, -1, -1));
        trainSettlerPanel.add(intersection108, new org.netbeans.lib.awtextra.AbsoluteConstraints(652, 474, -1, -1));
        trainSettlerPanel.add(intersection109, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 513, -1, -1));
        trainSettlerPanel.add(intersection110, new org.netbeans.lib.awtextra.AbsoluteConstraints(664, 555, -1, -1));
        trainSettlerPanel.add(intersection111, new org.netbeans.lib.awtextra.AbsoluteConstraints(711, 148, -1, -1));
        trainSettlerPanel.add(intersection112, new org.netbeans.lib.awtextra.AbsoluteConstraints(738, 189, -1, -1));
        trainSettlerPanel.add(intersection113, new org.netbeans.lib.awtextra.AbsoluteConstraints(702, 239, -1, -1));
        trainSettlerPanel.add(intersection114, new org.netbeans.lib.awtextra.AbsoluteConstraints(736, 270, -1, -1));
        trainSettlerPanel.add(intersection115, new org.netbeans.lib.awtextra.AbsoluteConstraints(704, 311, -1, -1));
        trainSettlerPanel.add(intersection116, new org.netbeans.lib.awtextra.AbsoluteConstraints(735, 353, -1, -1));
        trainSettlerPanel.add(intersection117, new org.netbeans.lib.awtextra.AbsoluteConstraints(703, 390, -1, -1));
        trainSettlerPanel.add(intersection118, new org.netbeans.lib.awtextra.AbsoluteConstraints(734, 431, -1, -1));
        trainSettlerPanel.add(intersection119, new org.netbeans.lib.awtextra.AbsoluteConstraints(711, 472, -1, -1));
        trainSettlerPanel.add(intersection120, new org.netbeans.lib.awtextra.AbsoluteConstraints(735, 513, -1, -1));
        trainSettlerPanel.add(intersection121, new org.netbeans.lib.awtextra.AbsoluteConstraints(702, 558, -1, -1));
        trainSettlerPanel.add(intersection122, new org.netbeans.lib.awtextra.AbsoluteConstraints(781, 188, -1, -1));
        trainSettlerPanel.add(intersection123, new org.netbeans.lib.awtextra.AbsoluteConstraints(806, 230, -1, -1));
        trainSettlerPanel.add(intersection124, new org.netbeans.lib.awtextra.AbsoluteConstraints(775, 270, -1, -1));
        trainSettlerPanel.add(intersection125, new org.netbeans.lib.awtextra.AbsoluteConstraints(806, 312, -1, -1));
        trainSettlerPanel.add(intersection126, new org.netbeans.lib.awtextra.AbsoluteConstraints(775, 352, -1, -1));
        trainSettlerPanel.add(intersection127, new org.netbeans.lib.awtextra.AbsoluteConstraints(805, 392, -1, -1));
        trainSettlerPanel.add(intersection128, new org.netbeans.lib.awtextra.AbsoluteConstraints(774, 431, -1, -1));
        trainSettlerPanel.add(intersection129, new org.netbeans.lib.awtextra.AbsoluteConstraints(806, 475, -1, -1));
        trainSettlerPanel.add(intersection130, new org.netbeans.lib.awtextra.AbsoluteConstraints(772, 514, -1, -1));
        trainSettlerPanel.add(intersection131, new org.netbeans.lib.awtextra.AbsoluteConstraints(864, 189, -1, -1));
        trainSettlerPanel.add(intersection132, new org.netbeans.lib.awtextra.AbsoluteConstraints(853, 229, -1, -1));
        trainSettlerPanel.add(intersection133, new org.netbeans.lib.awtextra.AbsoluteConstraints(866, 272, -1, -1));
        trainSettlerPanel.add(intersection134, new org.netbeans.lib.awtextra.AbsoluteConstraints(853, 311, -1, -1));
        trainSettlerPanel.add(intersection135, new org.netbeans.lib.awtextra.AbsoluteConstraints(866, 353, -1, -1));
        trainSettlerPanel.add(intersection136, new org.netbeans.lib.awtextra.AbsoluteConstraints(854, 391, -1, -1));
        trainSettlerPanel.add(intersection137, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 426, -1, -1));
        trainSettlerPanel.add(intersection138, new org.netbeans.lib.awtextra.AbsoluteConstraints(852, 474, -1, -1));
        trainSettlerPanel.add(intersection139, new org.netbeans.lib.awtextra.AbsoluteConstraints(867, 517, -1, -1));
        trainSettlerPanel.add(intersection140, new org.netbeans.lib.awtextra.AbsoluteConstraints(944, 62, -1, -1));
        trainSettlerPanel.add(intersection141, new org.netbeans.lib.awtextra.AbsoluteConstraints(921, 104, -1, -1));
        trainSettlerPanel.add(intersection142, new org.netbeans.lib.awtextra.AbsoluteConstraints(935, 146, -1, -1));
        trainSettlerPanel.add(intersection143, new org.netbeans.lib.awtextra.AbsoluteConstraints(922, 187, -1, -1));
        trainSettlerPanel.add(intersection144, new org.netbeans.lib.awtextra.AbsoluteConstraints(935, 230, -1, -1));
        trainSettlerPanel.add(intersection145, new org.netbeans.lib.awtextra.AbsoluteConstraints(923, 271, -1, -1));
        trainSettlerPanel.add(intersection146, new org.netbeans.lib.awtextra.AbsoluteConstraints(947, 312, -1, -1));
        trainSettlerPanel.add(intersection147, new org.netbeans.lib.awtextra.AbsoluteConstraints(924, 353, -1, -1));
        trainSettlerPanel.add(intersection148, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 61, -1, -1));

        gamePanel.add(trainSettlerPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        buttonPanel.setOpaque(false);
        buttonPanel.setPreferredSize(new java.awt.Dimension(1025, 702));
        buttonPanel.setRequestFocusEnabled(false);
        buttonPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        spokaneButton.setBackground(new java.awt.Color(255, 255, 255));
        spokaneButton.setBorder(null);
        spokaneButton.setBorderPainted(false);
        spokaneButton.setContentAreaFilled(false);
        spokaneButton.setFocusPainted(false);
        spokaneButton.setFocusable(false);
        spokaneButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                spokaneButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                spokaneButtonMouseExited(evt);
            }
        });
        spokaneButton.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                spokaneButtonMouseMoved(evt);
            }
        });
        buttonPanel.add(spokaneButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(149, 150, 25, 25));

        seattleButton.setBackground(new java.awt.Color(255, 255, 255));
        seattleButton.setBorder(null);
        seattleButton.setBorderPainted(false);
        seattleButton.setContentAreaFilled(false);
        seattleButton.setFocusPainted(false);
        seattleButton.setFocusable(false);
        seattleButton.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                spokaneButtonMouseMoved(evt);
            }
        });
        buttonPanel.add(seattleButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 147, 25, 25));

        portlandButton.setBackground(new java.awt.Color(255, 255, 255));
        portlandButton.setBorder(null);
        portlandButton.setBorderPainted(false);
        portlandButton.setContentAreaFilled(false);
        portlandButton.setFocusPainted(false);
        portlandButton.setFocusable(false);
        portlandButton.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                spokaneButtonMouseMoved(evt);
            }
        });
        buttonPanel.add(portlandButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 228, 25, 25));

        sanFranciscoButton.setBackground(new java.awt.Color(255, 255, 255));
        sanFranciscoButton.setBorder(null);
        sanFranciscoButton.setBorderPainted(false);
        sanFranciscoButton.setContentAreaFilled(false);
        sanFranciscoButton.setFocusPainted(false);
        sanFranciscoButton.setFocusable(false);
        sanFranciscoButton.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                spokaneButtonMouseMoved(evt);
            }
        });
        buttonPanel.add(sanFranciscoButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 388, 25, 25));

        renoButton.setBackground(new java.awt.Color(255, 255, 255));
        renoButton.setBorder(null);
        renoButton.setBorderPainted(false);
        renoButton.setContentAreaFilled(false);
        renoButton.setFocusPainted(false);
        renoButton.setFocusable(false);
        renoButton.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                spokaneButtonMouseMoved(evt);
            }
        });
        buttonPanel.add(renoButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(79, 352, 25, 25));

        losAngelesButton.setBackground(new java.awt.Color(255, 255, 255));
        losAngelesButton.setBorder(null);
        losAngelesButton.setBorderPainted(false);
        losAngelesButton.setContentAreaFilled(false);
        losAngelesButton.setFocusPainted(false);
        losAngelesButton.setFocusable(false);
        losAngelesButton.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                spokaneButtonMouseMoved(evt);
            }
        });
        buttonPanel.add(losAngelesButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(98, 473, 25, 25));

        lasVegasButton.setBackground(new java.awt.Color(255, 255, 255));
        lasVegasButton.setBorder(null);
        lasVegasButton.setBorderPainted(false);
        lasVegasButton.setContentAreaFilled(false);
        lasVegasButton.setFocusPainted(false);
        lasVegasButton.setFocusable(false);
        lasVegasButton.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                spokaneButtonMouseMoved(evt);
            }
        });
        buttonPanel.add(lasVegasButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(165, 433, 25, 25));

        boiseButton.setBackground(new java.awt.Color(255, 255, 255));
        boiseButton.setBorder(null);
        boiseButton.setBorderPainted(false);
        boiseButton.setContentAreaFilled(false);
        boiseButton.setFocusPainted(false);
        boiseButton.setFocusable(false);
        boiseButton.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                spokaneButtonMouseMoved(evt);
            }
        });
        buttonPanel.add(boiseButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 229, 25, 25));

        helenaButton.setBackground(new java.awt.Color(255, 255, 255));
        helenaButton.setBorder(null);
        helenaButton.setBorderPainted(false);
        helenaButton.setContentAreaFilled(false);
        helenaButton.setFocusPainted(false);
        helenaButton.setFocusable(false);
        helenaButton.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                spokaneButtonMouseMoved(evt);
            }
        });
        buttonPanel.add(helenaButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 189, 25, 25));

        idahoFallsButton.setBackground(new java.awt.Color(255, 255, 255));
        idahoFallsButton.setBorder(null);
        idahoFallsButton.setBorderPainted(false);
        idahoFallsButton.setContentAreaFilled(false);
        idahoFallsButton.setFocusPainted(false);
        idahoFallsButton.setFocusable(false);
        idahoFallsButton.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                spokaneButtonMouseMoved(evt);
            }
        });
        buttonPanel.add(idahoFallsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 270, 25, 25));

        saltLakeCityButton.setBackground(new java.awt.Color(255, 255, 255));
        saltLakeCityButton.setBorder(null);
        saltLakeCityButton.setBorderPainted(false);
        saltLakeCityButton.setContentAreaFilled(false);
        saltLakeCityButton.setFocusPainted(false);
        saltLakeCityButton.setFocusable(false);
        saltLakeCityButton.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                spokaneButtonMouseMoved(evt);
            }
        });
        buttonPanel.add(saltLakeCityButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(217, 351, 25, 25));

        flagstaffButton.setBackground(new java.awt.Color(255, 255, 255));
        flagstaffButton.setBorder(null);
        flagstaffButton.setBorderPainted(false);
        flagstaffButton.setContentAreaFilled(false);
        flagstaffButton.setFocusPainted(false);
        flagstaffButton.setFocusable(false);
        flagstaffButton.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                spokaneButtonMouseMoved(evt);
            }
        });
        buttonPanel.add(flagstaffButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(234, 473, 25, 25));

        billingsButton.setBackground(new java.awt.Color(255, 255, 255));
        billingsButton.setBorder(null);
        billingsButton.setBorderPainted(false);
        billingsButton.setContentAreaFilled(false);
        billingsButton.setFocusPainted(false);
        billingsButton.setFocusable(false);
        billingsButton.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                spokaneButtonMouseMoved(evt);
            }
        });
        buttonPanel.add(billingsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(306, 187, 25, 25));

        casperButton.setBackground(new java.awt.Color(255, 255, 255));
        casperButton.setBorder(null);
        casperButton.setBorderPainted(false);
        casperButton.setContentAreaFilled(false);
        casperButton.setFocusPainted(false);
        casperButton.setFocusable(false);
        casperButton.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                spokaneButtonMouseMoved(evt);
            }
        });
        buttonPanel.add(casperButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(306, 269, 25, 25));

        rapidCityButton.setBackground(new java.awt.Color(255, 255, 255));
        rapidCityButton.setBorder(null);
        rapidCityButton.setBorderPainted(false);
        rapidCityButton.setContentAreaFilled(false);
        rapidCityButton.setFocusPainted(false);
        rapidCityButton.setFocusable(false);
        rapidCityButton.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                spokaneButtonMouseMoved(evt);
            }
        });
        buttonPanel.add(rapidCityButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(376, 228, 25, 25));

        denverButton.setBackground(new java.awt.Color(255, 255, 255));
        denverButton.setBorder(null);
        denverButton.setBorderPainted(false);
        denverButton.setContentAreaFilled(false);
        denverButton.setFocusPainted(false);
        denverButton.setFocusable(false);
        denverButton.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                spokaneButtonMouseMoved(evt);
            }
        });
        buttonPanel.add(denverButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(353, 350, 25, 25));

        santaFeButton.setBackground(new java.awt.Color(255, 255, 255));
        santaFeButton.setBorder(null);
        santaFeButton.setBorderPainted(false);
        santaFeButton.setContentAreaFilled(false);
        santaFeButton.setFocusPainted(false);
        santaFeButton.setFocusable(false);
        santaFeButton.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                spokaneButtonMouseMoved(evt);
            }
        });
        buttonPanel.add(santaFeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(352, 434, 25, 25));

        elPasoButton.setBackground(new java.awt.Color(255, 255, 255));
        elPasoButton.setBorder(null);
        elPasoButton.setBorderPainted(false);
        elPasoButton.setContentAreaFilled(false);
        elPasoButton.setFocusPainted(false);
        elPasoButton.setFocusable(false);
        elPasoButton.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                spokaneButtonMouseMoved(evt);
            }
        });
        buttonPanel.add(elPasoButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(373, 555, 25, 25));

        amarilloButton.setBackground(new java.awt.Color(255, 255, 255));
        amarilloButton.setBorder(null);
        amarilloButton.setBorderPainted(false);
        amarilloButton.setContentAreaFilled(false);
        amarilloButton.setFocusPainted(false);
        amarilloButton.setFocusable(false);
        amarilloButton.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                spokaneButtonMouseMoved(evt);
            }
        });
        buttonPanel.add(amarilloButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(425, 475, 25, 25));

        bismarkButton.setBackground(new java.awt.Color(255, 255, 255));
        bismarkButton.setBorder(null);
        bismarkButton.setBorderPainted(false);
        bismarkButton.setContentAreaFilled(false);
        bismarkButton.setFocusPainted(false);
        bismarkButton.setFocusable(false);
        bismarkButton.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                spokaneButtonMouseMoved(evt);
            }
        });
        buttonPanel.add(bismarkButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(447, 187, 25, 25));

        sanAntonioButton.setBackground(new java.awt.Color(255, 255, 255));
        sanAntonioButton.setBorder(null);
        sanAntonioButton.setBorderPainted(false);
        sanAntonioButton.setContentAreaFilled(false);
        sanAntonioButton.setFocusPainted(false);
        sanAntonioButton.setFocusable(false);
        sanAntonioButton.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                spokaneButtonMouseMoved(evt);
            }
        });
        buttonPanel.add(sanAntonioButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(495, 596, 25, 25));

        dallasButton.setBackground(new java.awt.Color(255, 255, 255));
        dallasButton.setBorder(null);
        dallasButton.setBorderPainted(false);
        dallasButton.setContentAreaFilled(false);
        dallasButton.setFocusPainted(false);
        dallasButton.setFocusable(false);
        dallasButton.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                spokaneButtonMouseMoved(evt);
            }
        });
        buttonPanel.add(dallasButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(495, 514, 25, 25));

        wichitaButton.setBackground(new java.awt.Color(255, 255, 255));
        wichitaButton.setBorder(null);
        wichitaButton.setBorderPainted(false);
        wichitaButton.setContentAreaFilled(false);
        wichitaButton.setFocusPainted(false);
        wichitaButton.setFocusable(false);
        wichitaButton.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                spokaneButtonMouseMoved(evt);
            }
        });
        buttonPanel.add(wichitaButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(517, 393, 25, 25));

        omahaButton.setBackground(new java.awt.Color(255, 255, 255));
        omahaButton.setBorder(null);
        omahaButton.setBorderPainted(false);
        omahaButton.setContentAreaFilled(false);
        omahaButton.setFocusPainted(false);
        omahaButton.setFocusable(false);
        omahaButton.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                spokaneButtonMouseMoved(evt);
            }
        });
        buttonPanel.add(omahaButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(518, 309, 25, 25));

        minneapolisButton.setBackground(new java.awt.Color(255, 255, 255));
        minneapolisButton.setBorder(null);
        minneapolisButton.setBorderPainted(false);
        minneapolisButton.setContentAreaFilled(false);
        minneapolisButton.setFocusPainted(false);
        minneapolisButton.setFocusable(false);
        minneapolisButton.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                spokaneButtonMouseMoved(evt);
            }
        });
        buttonPanel.add(minneapolisButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(566, 228, 25, 25));

        littleRockButton.setBackground(new java.awt.Color(255, 255, 255));
        littleRockButton.setBorder(null);
        littleRockButton.setBorderPainted(false);
        littleRockButton.setContentAreaFilled(false);
        littleRockButton.setFocusPainted(false);
        littleRockButton.setFocusable(false);
        littleRockButton.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                spokaneButtonMouseMoved(evt);
            }
        });
        buttonPanel.add(littleRockButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(586, 429, 25, 25));

        shreveportButton.setBackground(new java.awt.Color(255, 255, 255));
        shreveportButton.setBorder(null);
        shreveportButton.setBorderPainted(false);
        shreveportButton.setContentAreaFilled(false);
        shreveportButton.setFocusPainted(false);
        shreveportButton.setFocusable(false);
        shreveportButton.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                spokaneButtonMouseMoved(evt);
            }
        });
        buttonPanel.add(shreveportButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(586, 512, 25, 25));

        houstonButton.setBackground(new java.awt.Color(255, 255, 255));
        houstonButton.setBorder(null);
        houstonButton.setBorderPainted(false);
        houstonButton.setContentAreaFilled(false);
        houstonButton.setFocusPainted(false);
        houstonButton.setFocusable(false);
        houstonButton.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                spokaneButtonMouseMoved(evt);
            }
        });
        buttonPanel.add(houstonButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(588, 592, 25, 25));

        stLouisButton.setBackground(new java.awt.Color(255, 255, 255));
        stLouisButton.setBorder(null);
        stLouisButton.setBorderPainted(false);
        stLouisButton.setContentAreaFilled(false);
        stLouisButton.setFocusPainted(false);
        stLouisButton.setFocusable(false);
        stLouisButton.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                spokaneButtonMouseMoved(evt);
            }
        });
        buttonPanel.add(stLouisButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(634, 349, 25, 25));

        jacksonButton.setBackground(new java.awt.Color(255, 255, 255));
        jacksonButton.setBorder(null);
        jacksonButton.setBorderPainted(false);
        jacksonButton.setContentAreaFilled(false);
        jacksonButton.setFocusPainted(false);
        jacksonButton.setFocusable(false);
        jacksonButton.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                spokaneButtonMouseMoved(evt);
            }
        });
        buttonPanel.add(jacksonButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(657, 471, 25, 25));

        chicagoButton.setBackground(new java.awt.Color(255, 255, 255));
        chicagoButton.setBorder(null);
        chicagoButton.setBorderPainted(false);
        chicagoButton.setContentAreaFilled(false);
        chicagoButton.setFocusPainted(false);
        chicagoButton.setFocusable(false);
        chicagoButton.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                spokaneButtonMouseMoved(evt);
            }
        });
        buttonPanel.add(chicagoButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(703, 235, 25, 25));

        indianapolisButton.setBackground(new java.awt.Color(255, 255, 255));
        indianapolisButton.setBorder(null);
        indianapolisButton.setBorderPainted(false);
        indianapolisButton.setContentAreaFilled(false);
        indianapolisButton.setFocusPainted(false);
        indianapolisButton.setFocusable(false);
        indianapolisButton.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                spokaneButtonMouseMoved(evt);
            }
        });
        buttonPanel.add(indianapolisButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(707, 309, 25, 25));

        nashvilleButton.setBackground(new java.awt.Color(255, 255, 255));
        nashvilleButton.setBorder(null);
        nashvilleButton.setBorderPainted(false);
        nashvilleButton.setContentAreaFilled(false);
        nashvilleButton.setFocusPainted(false);
        nashvilleButton.setFocusable(false);
        nashvilleButton.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                spokaneButtonMouseMoved(evt);
            }
        });
        buttonPanel.add(nashvilleButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(705, 389, 25, 25));

        newOrleansButton.setBackground(new java.awt.Color(255, 255, 255));
        newOrleansButton.setBorder(null);
        newOrleansButton.setBorderPainted(false);
        newOrleansButton.setContentAreaFilled(false);
        newOrleansButton.setFocusPainted(false);
        newOrleansButton.setFocusable(false);
        newOrleansButton.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                spokaneButtonMouseMoved(evt);
            }
        });
        buttonPanel.add(newOrleansButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(705, 554, 25, 25));

        columbusButton.setBackground(new java.awt.Color(255, 255, 255));
        columbusButton.setBorder(null);
        columbusButton.setBorderPainted(false);
        columbusButton.setContentAreaFilled(false);
        columbusButton.setFocusPainted(false);
        columbusButton.setFocusable(false);
        columbusButton.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                spokaneButtonMouseMoved(evt);
            }
        });
        buttonPanel.add(columbusButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(776, 268, 25, 25));

        lexingtonButton.setBackground(new java.awt.Color(255, 255, 255));
        lexingtonButton.setBorder(null);
        lexingtonButton.setBorderPainted(false);
        lexingtonButton.setContentAreaFilled(false);
        lexingtonButton.setFocusPainted(false);
        lexingtonButton.setFocusable(false);
        lexingtonButton.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                spokaneButtonMouseMoved(evt);
            }
        });
        buttonPanel.add(lexingtonButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(777, 350, 25, 25));

        atlantaButton.setBackground(new java.awt.Color(255, 255, 255));
        atlantaButton.setBorder(null);
        atlantaButton.setBorderPainted(false);
        atlantaButton.setContentAreaFilled(false);
        atlantaButton.setFocusPainted(false);
        atlantaButton.setFocusable(false);
        atlantaButton.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                spokaneButtonMouseMoved(evt);
            }
        });
        buttonPanel.add(atlantaButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(776, 430, 25, 25));

        pensacolaButton.setBackground(new java.awt.Color(255, 255, 255));
        pensacolaButton.setBorder(null);
        pensacolaButton.setBorderPainted(false);
        pensacolaButton.setContentAreaFilled(false);
        pensacolaButton.setFocusPainted(false);
        pensacolaButton.setFocusable(false);
        pensacolaButton.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                spokaneButtonMouseMoved(evt);
            }
        });
        buttonPanel.add(pensacolaButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(775, 512, 25, 25));

        albanyButton.setBackground(new java.awt.Color(255, 255, 255));
        albanyButton.setBorder(null);
        albanyButton.setBorderPainted(false);
        albanyButton.setContentAreaFilled(false);
        albanyButton.setFocusPainted(false);
        albanyButton.setFocusable(false);
        albanyButton.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                spokaneButtonMouseMoved(evt);
            }
        });
        buttonPanel.add(albanyButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(869, 186, 25, 25));

        washingtonDCButton.setBackground(new java.awt.Color(255, 255, 255));
        washingtonDCButton.setBorder(null);
        washingtonDCButton.setBorderPainted(false);
        washingtonDCButton.setContentAreaFilled(false);
        washingtonDCButton.setFocusPainted(false);
        washingtonDCButton.setFocusable(false);
        washingtonDCButton.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                spokaneButtonMouseMoved(evt);
            }
        });
        buttonPanel.add(washingtonDCButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 270, 25, 25));

        raleighButton.setBackground(new java.awt.Color(255, 255, 255));
        raleighButton.setBorder(null);
        raleighButton.setBorderPainted(false);
        raleighButton.setContentAreaFilled(false);
        raleighButton.setFocusPainted(false);
        raleighButton.setFocusable(false);
        raleighButton.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                spokaneButtonMouseMoved(evt);
            }
        });
        buttonPanel.add(raleighButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 351, 25, 25));

        columbiaButton.setBackground(new java.awt.Color(255, 255, 255));
        columbiaButton.setBorder(null);
        columbiaButton.setBorderPainted(false);
        columbiaButton.setContentAreaFilled(false);
        columbiaButton.setFocusPainted(false);
        columbiaButton.setFocusable(false);
        columbiaButton.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                spokaneButtonMouseMoved(evt);
            }
        });
        buttonPanel.add(columbiaButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(869, 432, 25, 25));

        jacksonvilleButton.setBackground(new java.awt.Color(255, 255, 255));
        jacksonvilleButton.setBorder(null);
        jacksonvilleButton.setBorderPainted(false);
        jacksonvilleButton.setContentAreaFilled(false);
        jacksonvilleButton.setFocusPainted(false);
        jacksonvilleButton.setFocusable(false);
        jacksonvilleButton.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                spokaneButtonMouseMoved(evt);
            }
        });
        buttonPanel.add(jacksonvilleButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 515, 25, 25));

        newYorkButton.setBackground(new java.awt.Color(255, 255, 255));
        newYorkButton.setBorder(null);
        newYorkButton.setBorderPainted(false);
        newYorkButton.setContentAreaFilled(false);
        newYorkButton.setFocusPainted(false);
        newYorkButton.setFocusable(false);
        newYorkButton.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                spokaneButtonMouseMoved(evt);
            }
        });
        buttonPanel.add(newYorkButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 227, 25, 25));

        bostonButton.setBackground(new java.awt.Color(255, 255, 255));
        bostonButton.setBorder(null);
        bostonButton.setBorderPainted(false);
        bostonButton.setContentAreaFilled(false);
        bostonButton.setFocusPainted(false);
        bostonButton.setFocusable(false);
        bostonButton.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                spokaneButtonMouseMoved(evt);
            }
        });
        buttonPanel.add(bostonButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(939, 144, 25, 25));

        railButton0.setBorderPainted(false);
        railButton0.setContentAreaFilled(false);
        railButton0.setFocusable(false);
        buttonPanel.add(railButton0, new org.netbeans.lib.awtextra.AbsoluteConstraints(87, 163, 30, 40));

        railButton1.setBorderPainted(false);
        railButton1.setContentAreaFilled(false);
        railButton1.setFocusable(false);
        buttonPanel.add(railButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 165, 30, 40));

        railButton2.setBorderPainted(false);
        railButton2.setContentAreaFilled(false);
        railButton2.setFocusable(false);
        buttonPanel.add(railButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 247, 30, 40));

        railButton3.setBorderPainted(false);
        railButton3.setContentAreaFilled(false);
        railButton3.setFocusable(false);
        buttonPanel.add(railButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 325, 30, 40));

        railButton4.setBorderPainted(false);
        railButton4.setContentAreaFilled(false);
        railButton4.setFocusable(false);
        buttonPanel.add(railButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 408, 30, 40));

        railButton5.setBorderPainted(false);
        railButton5.setContentAreaFilled(false);
        railButton5.setFocusable(false);
        buttonPanel.add(railButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(88, 203, 30, 40));

        railButton6.setBorderPainted(false);
        railButton6.setContentAreaFilled(false);
        railButton6.setFocusable(false);
        buttonPanel.add(railButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(87, 284, 30, 40));

        railButton7.setBorderPainted(false);
        railButton7.setContentAreaFilled(false);
        railButton7.setFocusable(false);
        buttonPanel.add(railButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(88, 368, 30, 40));

        railButton8.setBorderPainted(false);
        railButton8.setContentAreaFilled(false);
        railButton8.setFocusable(false);
        buttonPanel.add(railButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(84, 444, 30, 40));

        railButton9.setBorderPainted(false);
        railButton9.setContentAreaFilled(false);
        railButton9.setFocusable(false);
        buttonPanel.add(railButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 166, 30, 40));

        railButton10.setBorderPainted(false);
        railButton10.setContentAreaFilled(false);
        railButton10.setFocusable(false);
        buttonPanel.add(railButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 245, 30, 40));

        railButton11.setBorderPainted(false);
        railButton11.setContentAreaFilled(false);
        railButton11.setFocusable(false);
        buttonPanel.add(railButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(154, 324, 30, 40));

        railButton12.setBorderPainted(false);
        railButton12.setContentAreaFilled(false);
        railButton12.setFocusable(false);
        buttonPanel.add(railButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 404, 30, 40));

        railButton13.setBorderPainted(false);
        railButton13.setContentAreaFilled(false);
        railButton13.setFocusable(false);
        buttonPanel.add(railButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(152, 487, 30, 40));

        railButton14.setBorderPainted(false);
        railButton14.setContentAreaFilled(false);
        railButton14.setFocusable(false);
        buttonPanel.add(railButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(88, 123, 30, 40));

        railButton15.setBorderPainted(false);
        railButton15.setContentAreaFilled(false);
        railButton15.setFocusable(false);
        buttonPanel.add(railButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(222, 124, 30, 40));

        railButton16.setBorderPainted(false);
        railButton16.setContentAreaFilled(false);
        railButton16.setFocusable(false);
        buttonPanel.add(railButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(223, 205, 30, 40));

        railButton17.setBorderPainted(false);
        railButton17.setContentAreaFilled(false);
        railButton17.setFocusable(false);
        buttonPanel.add(railButton17, new org.netbeans.lib.awtextra.AbsoluteConstraints(225, 286, 30, 40));

        railButton18.setBorderPainted(false);
        railButton18.setContentAreaFilled(false);
        railButton18.setFocusable(false);
        buttonPanel.add(railButton18, new org.netbeans.lib.awtextra.AbsoluteConstraints(224, 368, 30, 40));

        railButton19.setBorderPainted(false);
        railButton19.setContentAreaFilled(false);
        railButton19.setFocusable(false);
        buttonPanel.add(railButton19, new org.netbeans.lib.awtextra.AbsoluteConstraints(218, 444, 30, 40));

        railButton20.setBorderPainted(false);
        railButton20.setContentAreaFilled(false);
        railButton20.setFocusable(false);
        buttonPanel.add(railButton20, new org.netbeans.lib.awtextra.AbsoluteConstraints(218, 528, 30, 40));

        railButton21.setBorderPainted(false);
        railButton21.setContentAreaFilled(false);
        railButton21.setFocusable(false);
        buttonPanel.add(railButton21, new org.netbeans.lib.awtextra.AbsoluteConstraints(286, 569, 30, 40));

        railButton22.setBorderPainted(false);
        railButton22.setContentAreaFilled(false);
        railButton22.setFocusable(false);
        buttonPanel.add(railButton22, new org.netbeans.lib.awtextra.AbsoluteConstraints(288, 487, 30, 40));

        railButton23.setBorderPainted(false);
        railButton23.setContentAreaFilled(false);
        railButton23.setFocusable(false);
        buttonPanel.add(railButton23, new org.netbeans.lib.awtextra.AbsoluteConstraints(289, 406, 30, 40));

        railButton24.setBorderPainted(false);
        railButton24.setContentAreaFilled(false);
        railButton24.setFocusable(false);
        buttonPanel.add(railButton24, new org.netbeans.lib.awtextra.AbsoluteConstraints(289, 324, 30, 40));

        railButton25.setBorderPainted(false);
        railButton25.setContentAreaFilled(false);
        railButton25.setFocusable(false);
        buttonPanel.add(railButton25, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 240, 30, 40));

        railButton26.setBorderPainted(false);
        railButton26.setContentAreaFilled(false);
        railButton26.setFocusable(false);
        buttonPanel.add(railButton26, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 158, 30, 40));

        railButton27.setBorderPainted(false);
        railButton27.setContentAreaFilled(false);
        railButton27.setFocusable(false);
        buttonPanel.add(railButton27, new org.netbeans.lib.awtextra.AbsoluteConstraints(363, 122, 30, 40));

        railButton28.setBorderPainted(false);
        railButton28.setContentAreaFilled(false);
        railButton28.setFocusable(false);
        buttonPanel.add(railButton28, new org.netbeans.lib.awtextra.AbsoluteConstraints(359, 199, 30, 40));

        railButton29.setBorderPainted(false);
        railButton29.setContentAreaFilled(false);
        railButton29.setFocusable(false);
        buttonPanel.add(railButton29, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 282, 30, 40));

        railButton30.setBorderPainted(false);
        railButton30.setContentAreaFilled(false);
        railButton30.setFocusable(false);
        buttonPanel.add(railButton30, new org.netbeans.lib.awtextra.AbsoluteConstraints(361, 366, 30, 40));

        railButton31.setBorderPainted(false);
        railButton31.setContentAreaFilled(false);
        railButton31.setFocusable(false);
        buttonPanel.add(railButton31, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 451, 30, 40));

        railButton32.setBorderPainted(false);
        railButton32.setContentAreaFilled(false);
        railButton32.setFocusable(false);
        buttonPanel.add(railButton32, new org.netbeans.lib.awtextra.AbsoluteConstraints(358, 528, 30, 40));

        railButton33.setBorderPainted(false);
        railButton33.setContentAreaFilled(false);
        railButton33.setFocusable(false);
        buttonPanel.add(railButton33, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 569, 30, 40));

        railButton34.setBorderPainted(false);
        railButton34.setContentAreaFilled(false);
        railButton34.setFocusable(false);
        buttonPanel.add(railButton34, new org.netbeans.lib.awtextra.AbsoluteConstraints(434, 492, 30, 40));

        railButton35.setBorderPainted(false);
        railButton35.setContentAreaFilled(false);
        railButton35.setFocusable(false);
        buttonPanel.add(railButton35, new org.netbeans.lib.awtextra.AbsoluteConstraints(432, 408, 30, 40));

        railButton36.setBorderPainted(false);
        railButton36.setContentAreaFilled(false);
        railButton36.setFocusable(false);
        buttonPanel.add(railButton36, new org.netbeans.lib.awtextra.AbsoluteConstraints(431, 324, 30, 40));

        railButton37.setBorderPainted(false);
        railButton37.setContentAreaFilled(false);
        railButton37.setFocusable(false);
        buttonPanel.add(railButton37, new org.netbeans.lib.awtextra.AbsoluteConstraints(431, 242, 30, 40));

        railButton38.setBorderPainted(false);
        railButton38.setContentAreaFilled(false);
        railButton38.setFocusable(false);
        buttonPanel.add(railButton38, new org.netbeans.lib.awtextra.AbsoluteConstraints(432, 158, 30, 40));

        railButton39.setBorderPainted(false);
        railButton39.setContentAreaFilled(false);
        railButton39.setFocusable(false);
        buttonPanel.add(railButton39, new org.netbeans.lib.awtextra.AbsoluteConstraints(504, 119, 30, 40));

        railButton40.setBorderPainted(false);
        railButton40.setContentAreaFilled(false);
        railButton40.setFocusable(false);
        buttonPanel.add(railButton40, new org.netbeans.lib.awtextra.AbsoluteConstraints(504, 203, 30, 40));

        railButton41.setBorderPainted(false);
        railButton41.setContentAreaFilled(false);
        railButton41.setFocusable(false);
        buttonPanel.add(railButton41, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 280, 30, 40));

        railButton42.setBorderPainted(false);
        railButton42.setContentAreaFilled(false);
        railButton42.setFocusable(false);
        buttonPanel.add(railButton42, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 364, 30, 40));

        railButton43.setBorderPainted(false);
        railButton43.setContentAreaFilled(false);
        railButton43.setFocusable(false);
        buttonPanel.add(railButton43, new org.netbeans.lib.awtextra.AbsoluteConstraints(501, 446, 30, 40));

        railButton44.setBorderPainted(false);
        railButton44.setContentAreaFilled(false);
        railButton44.setFocusable(false);
        buttonPanel.add(railButton44, new org.netbeans.lib.awtextra.AbsoluteConstraints(503, 532, 30, 40));

        railButton45.setBorderPainted(false);
        railButton45.setContentAreaFilled(false);
        railButton45.setFocusable(false);
        buttonPanel.add(railButton45, new org.netbeans.lib.awtextra.AbsoluteConstraints(504, 614, 30, 40));

        railButton46.setBorderPainted(false);
        railButton46.setContentAreaFilled(false);
        railButton46.setFocusable(false);
        buttonPanel.add(railButton46, new org.netbeans.lib.awtextra.AbsoluteConstraints(571, 566, 30, 40));

        railButton47.setBorderPainted(false);
        railButton47.setContentAreaFilled(false);
        railButton47.setFocusable(false);
        buttonPanel.add(railButton47, new org.netbeans.lib.awtextra.AbsoluteConstraints(571, 483, 30, 40));

        railButton48.setBorderPainted(false);
        railButton48.setContentAreaFilled(false);
        railButton48.setFocusable(false);
        buttonPanel.add(railButton48, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 401, 30, 40));

        railButton49.setBorderPainted(false);
        railButton49.setContentAreaFilled(false);
        railButton49.setFocusable(false);
        buttonPanel.add(railButton49, new org.netbeans.lib.awtextra.AbsoluteConstraints(573, 322, 30, 40));

        railButton50.setBorderPainted(false);
        railButton50.setContentAreaFilled(false);
        railButton50.setFocusable(false);
        buttonPanel.add(railButton50, new org.netbeans.lib.awtextra.AbsoluteConstraints(576, 244, 30, 40));

        railButton51.setBorderPainted(false);
        railButton51.setContentAreaFilled(false);
        railButton51.setFocusable(false);
        buttonPanel.add(railButton51, new org.netbeans.lib.awtextra.AbsoluteConstraints(576, 162, 30, 40));

        railButton52.setBorderPainted(false);
        railButton52.setContentAreaFilled(false);
        railButton52.setFocusable(false);
        buttonPanel.add(railButton52, new org.netbeans.lib.awtextra.AbsoluteConstraints(643, 526, 30, 40));

        railButton53.setBorderPainted(false);
        railButton53.setContentAreaFilled(false);
        railButton53.setFocusable(false);
        buttonPanel.add(railButton53, new org.netbeans.lib.awtextra.AbsoluteConstraints(641, 441, 30, 40));

        railButton54.setBorderPainted(false);
        railButton54.setContentAreaFilled(false);
        railButton54.setFocusable(false);
        buttonPanel.add(railButton54, new org.netbeans.lib.awtextra.AbsoluteConstraints(645, 365, 30, 40));

        railButton55.setBorderPainted(false);
        railButton55.setContentAreaFilled(false);
        railButton55.setFocusable(false);
        buttonPanel.add(railButton55, new org.netbeans.lib.awtextra.AbsoluteConstraints(645, 282, 30, 40));

        railButton56.setBorderPainted(false);
        railButton56.setContentAreaFilled(false);
        railButton56.setFocusable(false);
        buttonPanel.add(railButton56, new org.netbeans.lib.awtextra.AbsoluteConstraints(646, 203, 30, 40));

        railButton57.setBorderPainted(false);
        railButton57.setContentAreaFilled(false);
        railButton57.setFocusable(false);
        buttonPanel.add(railButton57, new org.netbeans.lib.awtextra.AbsoluteConstraints(714, 486, 30, 40));

        railButton58.setBorderPainted(false);
        railButton58.setContentAreaFilled(false);
        railButton58.setFocusable(false);
        buttonPanel.add(railButton58, new org.netbeans.lib.awtextra.AbsoluteConstraints(715, 405, 30, 40));

        railButton59.setBorderPainted(false);
        railButton59.setContentAreaFilled(false);
        railButton59.setFocusable(false);
        buttonPanel.add(railButton59, new org.netbeans.lib.awtextra.AbsoluteConstraints(715, 325, 30, 40));

        railButton60.setBorderPainted(false);
        railButton60.setContentAreaFilled(false);
        railButton60.setFocusable(false);
        buttonPanel.add(railButton60, new org.netbeans.lib.awtextra.AbsoluteConstraints(717, 247, 30, 40));

        railButton61.setBorderPainted(false);
        railButton61.setContentAreaFilled(false);
        railButton61.setFocusable(false);
        buttonPanel.add(railButton61, new org.netbeans.lib.awtextra.AbsoluteConstraints(715, 160, 30, 40));

        railButton62.setBorderPainted(false);
        railButton62.setContentAreaFilled(false);
        railButton62.setFocusable(false);
        buttonPanel.add(railButton62, new org.netbeans.lib.awtextra.AbsoluteConstraints(784, 202, 30, 40));

        railButton63.setBorderPainted(false);
        railButton63.setContentAreaFilled(false);
        railButton63.setFocusable(false);
        buttonPanel.add(railButton63, new org.netbeans.lib.awtextra.AbsoluteConstraints(786, 285, 30, 40));

        railButton64.setBorderPainted(false);
        railButton64.setContentAreaFilled(false);
        railButton64.setFocusable(false);
        buttonPanel.add(railButton64, new org.netbeans.lib.awtextra.AbsoluteConstraints(787, 366, 30, 40));

        railButton65.setBorderPainted(false);
        railButton65.setContentAreaFilled(false);
        railButton65.setFocusable(false);
        buttonPanel.add(railButton65, new org.netbeans.lib.awtextra.AbsoluteConstraints(786, 447, 30, 40));

        railButton66.setBorderPainted(false);
        railButton66.setContentAreaFilled(false);
        railButton66.setFocusable(false);
        buttonPanel.add(railButton66, new org.netbeans.lib.awtextra.AbsoluteConstraints(853, 486, 30, 40));

        railButton67.setBorderPainted(false);
        railButton67.setContentAreaFilled(false);
        railButton67.setFocusable(false);
        buttonPanel.add(railButton67, new org.netbeans.lib.awtextra.AbsoluteConstraints(853, 403, 30, 40));

        railButton68.setBorderPainted(false);
        railButton68.setContentAreaFilled(false);
        railButton68.setFocusable(false);
        buttonPanel.add(railButton68, new org.netbeans.lib.awtextra.AbsoluteConstraints(852, 321, 30, 40));

        railButton69.setBorderPainted(false);
        railButton69.setContentAreaFilled(false);
        railButton69.setFocusable(false);
        buttonPanel.add(railButton69, new org.netbeans.lib.awtextra.AbsoluteConstraints(853, 240, 30, 40));

        railButton70.setBorderPainted(false);
        railButton70.setContentAreaFilled(false);
        railButton70.setFocusable(false);
        buttonPanel.add(railButton70, new org.netbeans.lib.awtextra.AbsoluteConstraints(853, 157, 30, 40));

        railButton71.setBorderPainted(false);
        railButton71.setContentAreaFilled(false);
        railButton71.setFocusable(false);
        buttonPanel.add(railButton71, new org.netbeans.lib.awtextra.AbsoluteConstraints(925, 281, 30, 40));

        railButton72.setBorderPainted(false);
        railButton72.setContentAreaFilled(false);
        railButton72.setFocusable(false);
        buttonPanel.add(railButton72, new org.netbeans.lib.awtextra.AbsoluteConstraints(924, 198, 30, 40));

        railButton73.setBorderPainted(false);
        railButton73.setContentAreaFilled(false);
        railButton73.setFocusable(false);
        buttonPanel.add(railButton73, new org.netbeans.lib.awtextra.AbsoluteConstraints(922, 115, 30, 40));

        railButton74.setBorderPainted(false);
        railButton74.setContentAreaFilled(false);
        railButton74.setFocusable(false);
        buttonPanel.add(railButton74, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 200, 30, 40));

        railButton75.setBorderPainted(false);
        railButton75.setContentAreaFilled(false);
        railButton75.setFocusable(false);
        buttonPanel.add(railButton75, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 285, 30, 40));

        railButton76.setBorderPainted(false);
        railButton76.setContentAreaFilled(false);
        railButton76.setFocusable(false);
        buttonPanel.add(railButton76, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 361, 30, 40));

        railButton77.setBorderPainted(false);
        railButton77.setContentAreaFilled(false);
        railButton77.setFocusable(false);
        buttonPanel.add(railButton77, new org.netbeans.lib.awtextra.AbsoluteConstraints(87, 245, 30, 40));

        railButton78.setBorderPainted(false);
        railButton78.setContentAreaFilled(false);
        railButton78.setFocusable(false);
        buttonPanel.add(railButton78, new org.netbeans.lib.awtextra.AbsoluteConstraints(88, 323, 30, 40));

        railButton79.setBorderPainted(false);
        railButton79.setContentAreaFilled(false);
        railButton79.setFocusable(false);
        buttonPanel.add(railButton79, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 405, 30, 40));

        railButton80.setBorderPainted(false);
        railButton80.setContentAreaFilled(false);
        railButton80.setFocusable(false);
        buttonPanel.add(railButton80, new org.netbeans.lib.awtextra.AbsoluteConstraints(82, 490, 30, 40));

        railButton81.setBorderPainted(false);
        railButton81.setContentAreaFilled(false);
        railButton81.setFocusable(false);
        buttonPanel.add(railButton81, new org.netbeans.lib.awtextra.AbsoluteConstraints(157, 121, 30, 40));

        railButton82.setBorderPainted(false);
        railButton82.setContentAreaFilled(false);
        railButton82.setFocusable(false);
        buttonPanel.add(railButton82, new org.netbeans.lib.awtextra.AbsoluteConstraints(156, 200, 30, 40));

        railButton83.setBorderPainted(false);
        railButton83.setContentAreaFilled(false);
        railButton83.setFocusable(false);
        buttonPanel.add(railButton83, new org.netbeans.lib.awtextra.AbsoluteConstraints(154, 284, 30, 40));

        railButton84.setBorderPainted(false);
        railButton84.setContentAreaFilled(false);
        railButton84.setFocusable(false);
        buttonPanel.add(railButton84, new org.netbeans.lib.awtextra.AbsoluteConstraints(152, 365, 30, 40));

        railButton85.setBorderPainted(false);
        railButton85.setContentAreaFilled(false);
        railButton85.setFocusable(false);
        buttonPanel.add(railButton85, new org.netbeans.lib.awtextra.AbsoluteConstraints(147, 449, 30, 40));

        railButton86.setBorderPainted(false);
        railButton86.setContentAreaFilled(false);
        railButton86.setFocusable(false);
        buttonPanel.add(railButton86, new org.netbeans.lib.awtextra.AbsoluteConstraints(152, 528, 30, 40));

        railButton87.setBorderPainted(false);
        railButton87.setContentAreaFilled(false);
        railButton87.setFocusable(false);
        buttonPanel.add(railButton87, new org.netbeans.lib.awtextra.AbsoluteConstraints(218, 569, 30, 40));

        railButton88.setBorderPainted(false);
        railButton88.setContentAreaFilled(false);
        railButton88.setFocusable(false);
        buttonPanel.add(railButton88, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 489, 30, 40));

        railButton89.setBorderPainted(false);
        railButton89.setContentAreaFilled(false);
        railButton89.setFocusable(false);
        buttonPanel.add(railButton89, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 405, 30, 40));

        railButton90.setBorderPainted(false);
        railButton90.setContentAreaFilled(false);
        railButton90.setFocusable(false);
        buttonPanel.add(railButton90, new org.netbeans.lib.awtextra.AbsoluteConstraints(226, 322, 30, 40));

        railButton91.setBorderPainted(false);
        railButton91.setContentAreaFilled(false);
        railButton91.setFocusable(false);
        buttonPanel.add(railButton91, new org.netbeans.lib.awtextra.AbsoluteConstraints(223, 240, 30, 40));

        railButton92.setBorderPainted(false);
        railButton92.setContentAreaFilled(false);
        railButton92.setFocusable(false);
        buttonPanel.add(railButton92, new org.netbeans.lib.awtextra.AbsoluteConstraints(225, 160, 30, 40));

        railButton93.setBorderPainted(false);
        railButton93.setContentAreaFilled(false);
        railButton93.setFocusable(false);
        buttonPanel.add(railButton93, new org.netbeans.lib.awtextra.AbsoluteConstraints(291, 121, 30, 40));

        railButton94.setBorderPainted(false);
        railButton94.setContentAreaFilled(false);
        railButton94.setFocusable(false);
        buttonPanel.add(railButton94, new org.netbeans.lib.awtextra.AbsoluteConstraints(289, 204, 30, 40));

        railButton95.setBorderPainted(false);
        railButton95.setContentAreaFilled(false);
        railButton95.setFocusable(false);
        buttonPanel.add(railButton95, new org.netbeans.lib.awtextra.AbsoluteConstraints(288, 285, 30, 40));

        railButton96.setBorderPainted(false);
        railButton96.setContentAreaFilled(false);
        railButton96.setFocusable(false);
        buttonPanel.add(railButton96, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 365, 30, 40));

        railButton97.setBorderPainted(false);
        railButton97.setContentAreaFilled(false);
        railButton97.setFocusable(false);
        buttonPanel.add(railButton97, new org.netbeans.lib.awtextra.AbsoluteConstraints(289, 447, 30, 40));

        railButton98.setBorderPainted(false);
        railButton98.setContentAreaFilled(false);
        railButton98.setFocusable(false);
        buttonPanel.add(railButton98, new org.netbeans.lib.awtextra.AbsoluteConstraints(287, 528, 30, 40));

        railButton99.setBorderPainted(false);
        railButton99.setContentAreaFilled(false);
        railButton99.setFocusable(false);
        buttonPanel.add(railButton99, new org.netbeans.lib.awtextra.AbsoluteConstraints(358, 489, 30, 40));

        railButton100.setBorderPainted(false);
        railButton100.setContentAreaFilled(false);
        railButton100.setFocusable(false);
        buttonPanel.add(railButton100, new org.netbeans.lib.awtextra.AbsoluteConstraints(362, 405, 30, 40));

        railButton101.setBorderPainted(false);
        railButton101.setContentAreaFilled(false);
        railButton101.setFocusable(false);
        buttonPanel.add(railButton101, new org.netbeans.lib.awtextra.AbsoluteConstraints(362, 322, 30, 40));

        railButton102.setBorderPainted(false);
        railButton102.setContentAreaFilled(false);
        railButton102.setFocusable(false);
        buttonPanel.add(railButton102, new org.netbeans.lib.awtextra.AbsoluteConstraints(358, 245, 30, 40));

        railButton103.setBorderPainted(false);
        railButton103.setContentAreaFilled(false);
        railButton103.setFocusable(false);
        buttonPanel.add(railButton103, new org.netbeans.lib.awtextra.AbsoluteConstraints(362, 161, 30, 40));

        railButton104.setBorderPainted(false);
        railButton104.setContentAreaFilled(false);
        railButton104.setFocusable(false);
        buttonPanel.add(railButton104, new org.netbeans.lib.awtextra.AbsoluteConstraints(431, 530, 30, 40));

        railButton105.setBorderPainted(false);
        railButton105.setContentAreaFilled(false);
        railButton105.setFocusable(false);
        buttonPanel.add(railButton105, new org.netbeans.lib.awtextra.AbsoluteConstraints(434, 447, 30, 40));

        railButton106.setBorderPainted(false);
        railButton106.setContentAreaFilled(false);
        railButton106.setFocusable(false);
        buttonPanel.add(railButton106, new org.netbeans.lib.awtextra.AbsoluteConstraints(433, 366, 30, 40));

        railButton107.setBorderPainted(false);
        railButton107.setContentAreaFilled(false);
        railButton107.setFocusable(false);
        buttonPanel.add(railButton107, new org.netbeans.lib.awtextra.AbsoluteConstraints(433, 283, 30, 40));

        railButton108.setBorderPainted(false);
        railButton108.setContentAreaFilled(false);
        railButton108.setFocusable(false);
        buttonPanel.add(railButton108, new org.netbeans.lib.awtextra.AbsoluteConstraints(429, 203, 30, 40));

        railButton109.setBorderPainted(false);
        railButton109.setContentAreaFilled(false);
        railButton109.setFocusable(false);
        buttonPanel.add(railButton109, new org.netbeans.lib.awtextra.AbsoluteConstraints(431, 121, 30, 40));

        railButton110.setBorderPainted(false);
        railButton110.setContentAreaFilled(false);
        railButton110.setFocusable(false);
        buttonPanel.add(railButton110, new org.netbeans.lib.awtextra.AbsoluteConstraints(504, 567, 30, 40));

        railButton111.setBorderPainted(false);
        railButton111.setContentAreaFilled(false);
        railButton111.setFocusable(false);
        buttonPanel.add(railButton111, new org.netbeans.lib.awtextra.AbsoluteConstraints(505, 485, 30, 40));

        railButton112.setBorderPainted(false);
        railButton112.setContentAreaFilled(false);
        railButton112.setFocusable(false);
        buttonPanel.add(railButton112, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 408, 30, 40));

        railButton113.setBorderPainted(false);
        railButton113.setContentAreaFilled(false);
        railButton113.setFocusable(false);
        buttonPanel.add(railButton113, new org.netbeans.lib.awtextra.AbsoluteConstraints(499, 326, 30, 40));

        railButton114.setBorderPainted(false);
        railButton114.setContentAreaFilled(false);
        railButton114.setFocusable(false);
        buttonPanel.add(railButton114, new org.netbeans.lib.awtextra.AbsoluteConstraints(503, 242, 30, 40));

        railButton115.setBorderPainted(false);
        railButton115.setContentAreaFilled(false);
        railButton115.setFocusable(false);
        buttonPanel.add(railButton115, new org.netbeans.lib.awtextra.AbsoluteConstraints(504, 161, 30, 40));

        railButton116.setBorderPainted(false);
        railButton116.setContentAreaFilled(false);
        railButton116.setFocusable(false);
        buttonPanel.add(railButton116, new org.netbeans.lib.awtextra.AbsoluteConstraints(578, 201, 30, 40));

        railButton117.setBorderPainted(false);
        railButton117.setContentAreaFilled(false);
        railButton117.setFocusable(false);
        buttonPanel.add(railButton117, new org.netbeans.lib.awtextra.AbsoluteConstraints(573, 283, 30, 40));

        railButton118.setBorderPainted(false);
        railButton118.setContentAreaFilled(false);
        railButton118.setFocusable(false);
        buttonPanel.add(railButton118, new org.netbeans.lib.awtextra.AbsoluteConstraints(572, 363, 30, 40));

        railButton119.setBorderPainted(false);
        railButton119.setContentAreaFilled(false);
        railButton119.setFocusable(false);
        buttonPanel.add(railButton119, new org.netbeans.lib.awtextra.AbsoluteConstraints(571, 446, 30, 40));

        railButton120.setBorderPainted(false);
        railButton120.setContentAreaFilled(false);
        railButton120.setFocusable(false);
        buttonPanel.add(railButton120, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 529, 30, 40));

        railButton121.setBorderPainted(false);
        railButton121.setContentAreaFilled(false);
        railButton121.setFocusable(false);
        buttonPanel.add(railButton121, new org.netbeans.lib.awtextra.AbsoluteConstraints(642, 569, 30, 40));

        railButton122.setBorderPainted(false);
        railButton122.setContentAreaFilled(false);
        railButton122.setFocusable(false);
        buttonPanel.add(railButton122, new org.netbeans.lib.awtextra.AbsoluteConstraints(574, 119, 30, 40));

        railButton123.setBorderPainted(false);
        railButton123.setContentAreaFilled(false);
        railButton123.setFocusable(false);
        buttonPanel.add(railButton123, new org.netbeans.lib.awtextra.AbsoluteConstraints(641, 487, 30, 40));

        railButton124.setBorderPainted(false);
        railButton124.setContentAreaFilled(false);
        railButton124.setFocusable(false);
        buttonPanel.add(railButton124, new org.netbeans.lib.awtextra.AbsoluteConstraints(643, 403, 30, 40));

        railButton125.setBorderPainted(false);
        railButton125.setContentAreaFilled(false);
        railButton125.setFocusable(false);
        buttonPanel.add(railButton125, new org.netbeans.lib.awtextra.AbsoluteConstraints(645, 321, 30, 40));

        railButton126.setBorderPainted(false);
        railButton126.setContentAreaFilled(false);
        railButton126.setFocusable(false);
        buttonPanel.add(railButton126, new org.netbeans.lib.awtextra.AbsoluteConstraints(645, 243, 30, 40));

        railButton127.setBorderPainted(false);
        railButton127.setContentAreaFilled(false);
        railButton127.setFocusable(false);
        buttonPanel.add(railButton127, new org.netbeans.lib.awtextra.AbsoluteConstraints(646, 159, 30, 40));

        railButton128.setBorderPainted(false);
        railButton128.setContentAreaFilled(false);
        railButton128.setFocusable(false);
        buttonPanel.add(railButton128, new org.netbeans.lib.awtextra.AbsoluteConstraints(715, 525, 30, 40));

        railButton129.setBorderPainted(false);
        railButton129.setContentAreaFilled(false);
        railButton129.setFocusable(false);
        buttonPanel.add(railButton129, new org.netbeans.lib.awtextra.AbsoluteConstraints(713, 444, 30, 40));

        railButton130.setBorderPainted(false);
        railButton130.setContentAreaFilled(false);
        railButton130.setFocusable(false);
        buttonPanel.add(railButton130, new org.netbeans.lib.awtextra.AbsoluteConstraints(715, 361, 30, 40));

        railButton131.setBorderPainted(false);
        railButton131.setContentAreaFilled(false);
        railButton131.setFocusable(false);
        buttonPanel.add(railButton131, new org.netbeans.lib.awtextra.AbsoluteConstraints(716, 281, 30, 40));

        railButton132.setBorderPainted(false);
        railButton132.setContentAreaFilled(false);
        railButton132.setFocusable(false);
        buttonPanel.add(railButton132, new org.netbeans.lib.awtextra.AbsoluteConstraints(716, 200, 30, 40));

        railButton133.setBorderPainted(false);
        railButton133.setContentAreaFilled(false);
        railButton133.setFocusable(false);
        buttonPanel.add(railButton133, new org.netbeans.lib.awtextra.AbsoluteConstraints(788, 240, 30, 40));

        railButton134.setBorderPainted(false);
        railButton134.setContentAreaFilled(false);
        railButton134.setFocusable(false);
        buttonPanel.add(railButton134, new org.netbeans.lib.awtextra.AbsoluteConstraints(788, 322, 30, 40));

        railButton135.setBorderPainted(false);
        railButton135.setContentAreaFilled(false);
        railButton135.setFocusable(false);
        buttonPanel.add(railButton135, new org.netbeans.lib.awtextra.AbsoluteConstraints(787, 403, 30, 40));

        railButton136.setBorderPainted(false);
        railButton136.setContentAreaFilled(false);
        railButton136.setFocusable(false);
        buttonPanel.add(railButton136, new org.netbeans.lib.awtextra.AbsoluteConstraints(785, 486, 30, 40));

        railButton137.setBorderPainted(false);
        railButton137.setContentAreaFilled(false);
        railButton137.setFocusable(false);
        buttonPanel.add(railButton137, new org.netbeans.lib.awtextra.AbsoluteConstraints(852, 450, 30, 40));

        railButton138.setBorderPainted(false);
        railButton138.setContentAreaFilled(false);
        railButton138.setFocusable(false);
        buttonPanel.add(railButton138, new org.netbeans.lib.awtextra.AbsoluteConstraints(854, 367, 30, 40));

        railButton139.setBorderPainted(false);
        railButton139.setContentAreaFilled(false);
        railButton139.setFocusable(false);
        buttonPanel.add(railButton139, new org.netbeans.lib.awtextra.AbsoluteConstraints(854, 285, 30, 40));

        railButton140.setBorderPainted(false);
        railButton140.setContentAreaFilled(false);
        railButton140.setFocusable(false);
        buttonPanel.add(railButton140, new org.netbeans.lib.awtextra.AbsoluteConstraints(853, 203, 30, 40));

        railButton141.setBorderPainted(false);
        railButton141.setContentAreaFilled(false);
        railButton141.setFocusable(false);
        buttonPanel.add(railButton141, new org.netbeans.lib.awtextra.AbsoluteConstraints(926, 325, 30, 40));

        railButton142.setBorderPainted(false);
        railButton142.setContentAreaFilled(false);
        railButton142.setFocusable(false);
        buttonPanel.add(railButton142, new org.netbeans.lib.awtextra.AbsoluteConstraints(924, 244, 30, 40));

        railButton143.setBorderPainted(false);
        railButton143.setContentAreaFilled(false);
        railButton143.setFocusable(false);
        buttonPanel.add(railButton143, new org.netbeans.lib.awtextra.AbsoluteConstraints(922, 160, 30, 40));

        railButton144.setBorderPainted(false);
        railButton144.setContentAreaFilled(false);
        railButton144.setFocusable(false);
        buttonPanel.add(railButton144, new org.netbeans.lib.awtextra.AbsoluteConstraints(924, 75, 30, 40));

        railButton145.setBorderPainted(false);
        railButton145.setContentAreaFilled(false);
        railButton145.setFocusable(false);
        buttonPanel.add(railButton145, new org.netbeans.lib.awtextra.AbsoluteConstraints(713, 120, 30, 40));

        railButton146.setBorderPainted(false);
        railButton146.setContentAreaFilled(false);
        railButton146.setFocusable(false);
        buttonPanel.add(railButton146, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 193, 40, 20));

        railButton147.setBorderPainted(false);
        railButton147.setContentAreaFilled(false);
        railButton147.setFocusable(false);
        buttonPanel.add(railButton147, new org.netbeans.lib.awtextra.AbsoluteConstraints(48, 274, 40, 20));

        railButton148.setBorderPainted(false);
        railButton148.setContentAreaFilled(false);
        railButton148.setFocusable(false);
        buttonPanel.add(railButton148, new org.netbeans.lib.awtextra.AbsoluteConstraints(46, 354, 40, 20));

        railButton149.setBorderPainted(false);
        railButton149.setContentAreaFilled(false);
        railButton149.setFocusable(false);
        buttonPanel.add(railButton149, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 434, 40, 20));

        railButton150.setBorderPainted(false);
        railButton150.setContentAreaFilled(false);
        railButton150.setFocusable(false);
        buttonPanel.add(railButton150, new org.netbeans.lib.awtextra.AbsoluteConstraints(116, 153, 40, 20));

        railButton151.setBorderPainted(false);
        railButton151.setContentAreaFilled(false);
        railButton151.setFocusable(false);
        buttonPanel.add(railButton151, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 233, 40, 20));

        railButton152.setBorderPainted(false);
        railButton152.setContentAreaFilled(false);
        railButton152.setFocusable(false);
        buttonPanel.add(railButton152, new org.netbeans.lib.awtextra.AbsoluteConstraints(116, 313, 40, 20));

        railButton153.setBorderPainted(false);
        railButton153.setContentAreaFilled(false);
        railButton153.setFocusable(false);
        buttonPanel.add(railButton153, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 395, 40, 20));

        railButton154.setBorderPainted(false);
        railButton154.setContentAreaFilled(false);
        railButton154.setFocusable(false);
        buttonPanel.add(railButton154, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 477, 40, 20));

        railButton155.setBorderPainted(false);
        railButton155.setContentAreaFilled(false);
        railButton155.setFocusable(false);
        buttonPanel.add(railButton155, new org.netbeans.lib.awtextra.AbsoluteConstraints(183, 192, 40, 20));

        railButton156.setBorderPainted(false);
        railButton156.setContentAreaFilled(false);
        railButton156.setFocusable(false);
        buttonPanel.add(railButton156, new org.netbeans.lib.awtextra.AbsoluteConstraints(183, 273, 40, 20));

        railButton157.setBorderPainted(false);
        railButton157.setContentAreaFilled(false);
        railButton157.setFocusable(false);
        buttonPanel.add(railButton157, new org.netbeans.lib.awtextra.AbsoluteConstraints(182, 354, 40, 20));

        railButton158.setBorderPainted(false);
        railButton158.setContentAreaFilled(false);
        railButton158.setFocusable(false);
        buttonPanel.add(railButton158, new org.netbeans.lib.awtextra.AbsoluteConstraints(183, 436, 40, 20));

        railButton159.setBorderPainted(false);
        railButton159.setContentAreaFilled(false);
        railButton159.setFocusable(false);
        buttonPanel.add(railButton159, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 517, 40, 20));

        railButton160.setBorderPainted(false);
        railButton160.setContentAreaFilled(false);
        railButton160.setFocusable(false);
        buttonPanel.add(railButton160, new org.netbeans.lib.awtextra.AbsoluteConstraints(252, 152, 40, 20));

        railButton161.setBorderPainted(false);
        railButton161.setContentAreaFilled(false);
        railButton161.setFocusable(false);
        buttonPanel.add(railButton161, new org.netbeans.lib.awtextra.AbsoluteConstraints(252, 233, 40, 20));

        railButton162.setBorderPainted(false);
        railButton162.setContentAreaFilled(false);
        railButton162.setFocusable(false);
        buttonPanel.add(railButton162, new org.netbeans.lib.awtextra.AbsoluteConstraints(252, 313, 40, 20));

        railButton163.setBorderPainted(false);
        railButton163.setContentAreaFilled(false);
        railButton163.setFocusable(false);
        buttonPanel.add(railButton163, new org.netbeans.lib.awtextra.AbsoluteConstraints(251, 394, 40, 20));

        railButton164.setBorderPainted(false);
        railButton164.setContentAreaFilled(false);
        railButton164.setFocusable(false);
        buttonPanel.add(railButton164, new org.netbeans.lib.awtextra.AbsoluteConstraints(252, 475, 40, 20));

        railButton165.setBorderPainted(false);
        railButton165.setContentAreaFilled(false);
        railButton165.setFocusable(false);
        buttonPanel.add(railButton165, new org.netbeans.lib.awtextra.AbsoluteConstraints(248, 557, 40, 20));

        railButton166.setBorderPainted(false);
        railButton166.setContentAreaFilled(false);
        railButton166.setFocusable(false);
        buttonPanel.add(railButton166, new org.netbeans.lib.awtextra.AbsoluteConstraints(323, 192, 40, 20));

        railButton167.setBorderPainted(false);
        railButton167.setContentAreaFilled(false);
        railButton167.setFocusable(false);
        buttonPanel.add(railButton167, new org.netbeans.lib.awtextra.AbsoluteConstraints(323, 273, 40, 20));

        railButton168.setBorderPainted(false);
        railButton168.setContentAreaFilled(false);
        railButton168.setFocusable(false);
        buttonPanel.add(railButton168, new org.netbeans.lib.awtextra.AbsoluteConstraints(318, 353, 40, 20));

        railButton169.setBorderPainted(false);
        railButton169.setContentAreaFilled(false);
        railButton169.setFocusable(false);
        buttonPanel.add(railButton169, new org.netbeans.lib.awtextra.AbsoluteConstraints(318, 437, 40, 20));

        railButton170.setBorderPainted(false);
        railButton170.setContentAreaFilled(false);
        railButton170.setFocusable(false);
        buttonPanel.add(railButton170, new org.netbeans.lib.awtextra.AbsoluteConstraints(318, 517, 40, 20));

        railButton171.setBorderPainted(false);
        railButton171.setContentAreaFilled(false);
        railButton171.setFocusable(false);
        buttonPanel.add(railButton171, new org.netbeans.lib.awtextra.AbsoluteConstraints(465, 192, 40, 20));

        railButton172.setBorderPainted(false);
        railButton172.setContentAreaFilled(false);
        railButton172.setFocusable(false);
        buttonPanel.add(railButton172, new org.netbeans.lib.awtextra.AbsoluteConstraints(463, 273, 40, 20));

        railButton173.setBorderPainted(false);
        railButton173.setContentAreaFilled(false);
        railButton173.setFocusable(false);
        buttonPanel.add(railButton173, new org.netbeans.lib.awtextra.AbsoluteConstraints(463, 357, 40, 20));

        railButton174.setBorderPainted(false);
        railButton174.setContentAreaFilled(false);
        railButton174.setFocusable(false);
        buttonPanel.add(railButton174, new org.netbeans.lib.awtextra.AbsoluteConstraints(463, 438, 40, 20));

        railButton175.setBorderPainted(false);
        railButton175.setContentAreaFilled(false);
        railButton175.setFocusable(false);
        buttonPanel.add(railButton175, new org.netbeans.lib.awtextra.AbsoluteConstraints(461, 518, 40, 20));

        railButton176.setBorderPainted(false);
        railButton176.setContentAreaFilled(false);
        railButton176.setFocusable(false);
        buttonPanel.add(railButton176, new org.netbeans.lib.awtextra.AbsoluteConstraints(461, 600, 40, 20));

        railButton177.setBorderPainted(false);
        railButton177.setContentAreaFilled(false);
        railButton177.setFocusable(false);
        buttonPanel.add(railButton177, new org.netbeans.lib.awtextra.AbsoluteConstraints(392, 558, 40, 20));

        railButton178.setBorderPainted(false);
        railButton178.setContentAreaFilled(false);
        railButton178.setFocusable(false);
        buttonPanel.add(railButton178, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 479, 40, 20));

        railButton179.setBorderPainted(false);
        railButton179.setContentAreaFilled(false);
        railButton179.setFocusable(false);
        buttonPanel.add(railButton179, new org.netbeans.lib.awtextra.AbsoluteConstraints(464, 112, 40, 20));

        railButton180.setBorderPainted(false);
        railButton180.setContentAreaFilled(false);
        railButton180.setFocusable(false);
        buttonPanel.add(railButton180, new org.netbeans.lib.awtextra.AbsoluteConstraints(535, 151, 40, 20));

        railButton181.setBorderPainted(false);
        railButton181.setContentAreaFilled(false);
        railButton181.setFocusable(false);
        buttonPanel.add(railButton181, new org.netbeans.lib.awtextra.AbsoluteConstraints(533, 232, 40, 20));

        railButton182.setBorderPainted(false);
        railButton182.setContentAreaFilled(false);
        railButton182.setFocusable(false);
        buttonPanel.add(railButton182, new org.netbeans.lib.awtextra.AbsoluteConstraints(535, 313, 40, 20));

        railButton183.setBorderPainted(false);
        railButton183.setContentAreaFilled(false);
        railButton183.setFocusable(false);
        buttonPanel.add(railButton183, new org.netbeans.lib.awtextra.AbsoluteConstraints(535, 395, 40, 20));

        railButton184.setBorderPainted(false);
        railButton184.setContentAreaFilled(false);
        railButton184.setFocusable(false);
        buttonPanel.add(railButton184, new org.netbeans.lib.awtextra.AbsoluteConstraints(533, 476, 40, 20));

        railButton185.setBorderPainted(false);
        railButton185.setContentAreaFilled(false);
        railButton185.setFocusable(false);
        buttonPanel.add(railButton185, new org.netbeans.lib.awtextra.AbsoluteConstraints(533, 559, 40, 20));

        railButton186.setBorderPainted(false);
        railButton186.setContentAreaFilled(false);
        railButton186.setFocusable(false);
        buttonPanel.add(railButton186, new org.netbeans.lib.awtextra.AbsoluteConstraints(532, 641, 40, 20));

        railButton187.setBorderPainted(false);
        railButton187.setContentAreaFilled(false);
        railButton187.setFocusable(false);
        buttonPanel.add(railButton187, new org.netbeans.lib.awtextra.AbsoluteConstraints(606, 192, 40, 20));

        railButton188.setBorderPainted(false);
        railButton188.setContentAreaFilled(false);
        railButton188.setFocusable(false);
        buttonPanel.add(railButton188, new org.netbeans.lib.awtextra.AbsoluteConstraints(605, 272, 40, 20));

        railButton189.setBorderPainted(false);
        railButton189.setContentAreaFilled(false);
        railButton189.setFocusable(false);
        buttonPanel.add(railButton189, new org.netbeans.lib.awtextra.AbsoluteConstraints(602, 352, 40, 20));

        railButton190.setBorderPainted(false);
        railButton190.setContentAreaFilled(false);
        railButton190.setFocusable(false);
        buttonPanel.add(railButton190, new org.netbeans.lib.awtextra.AbsoluteConstraints(605, 433, 40, 20));

        railButton191.setBorderPainted(false);
        railButton191.setContentAreaFilled(false);
        railButton191.setFocusable(false);
        buttonPanel.add(railButton191, new org.netbeans.lib.awtextra.AbsoluteConstraints(604, 515, 40, 20));

        railButton192.setBorderPainted(false);
        railButton192.setContentAreaFilled(false);
        railButton192.setFocusable(false);
        buttonPanel.add(railButton192, new org.netbeans.lib.awtextra.AbsoluteConstraints(673, 234, 40, 20));

        railButton193.setBorderPainted(false);
        railButton193.setContentAreaFilled(false);
        railButton193.setFocusable(false);
        buttonPanel.add(railButton193, new org.netbeans.lib.awtextra.AbsoluteConstraints(674, 313, 40, 20));

        railButton195.setBorderPainted(false);
        railButton195.setContentAreaFilled(false);
        railButton195.setFocusable(false);
        buttonPanel.add(railButton195, new org.netbeans.lib.awtextra.AbsoluteConstraints(676, 475, 40, 20));

        railButton194.setBorderPainted(false);
        railButton194.setContentAreaFilled(false);
        railButton194.setFocusable(false);
        buttonPanel.add(railButton194, new org.netbeans.lib.awtextra.AbsoluteConstraints(672, 393, 40, 20));

        railButton196.setBorderPainted(false);
        railButton196.setContentAreaFilled(false);
        railButton196.setFocusable(false);
        buttonPanel.add(railButton196, new org.netbeans.lib.awtextra.AbsoluteConstraints(671, 556, 40, 20));

        railButton197.setBorderPainted(false);
        railButton197.setContentAreaFilled(false);
        railButton197.setFocusable(false);
        buttonPanel.add(railButton197, new org.netbeans.lib.awtextra.AbsoluteConstraints(744, 191, 40, 20));

        railButton198.setBorderPainted(false);
        railButton198.setContentAreaFilled(false);
        railButton198.setFocusable(false);
        buttonPanel.add(railButton198, new org.netbeans.lib.awtextra.AbsoluteConstraints(744, 272, 40, 20));

        railButton199.setBorderPainted(false);
        railButton199.setContentAreaFilled(false);
        railButton199.setFocusable(false);
        buttonPanel.add(railButton199, new org.netbeans.lib.awtextra.AbsoluteConstraints(744, 353, 40, 20));

        railButton200.setBorderPainted(false);
        railButton200.setContentAreaFilled(false);
        railButton200.setFocusable(false);
        buttonPanel.add(railButton200, new org.netbeans.lib.awtextra.AbsoluteConstraints(743, 434, 40, 20));

        railButton201.setBorderPainted(false);
        railButton201.setContentAreaFilled(false);
        railButton201.setFocusable(false);
        buttonPanel.add(railButton201, new org.netbeans.lib.awtextra.AbsoluteConstraints(742, 515, 40, 20));

        railButton202.setBorderPainted(false);
        railButton202.setContentAreaFilled(false);
        railButton202.setFocusable(false);
        buttonPanel.add(railButton202, new org.netbeans.lib.awtextra.AbsoluteConstraints(814, 232, 40, 20));

        railButton203.setBorderPainted(false);
        railButton203.setContentAreaFilled(false);
        railButton203.setFocusable(false);
        buttonPanel.add(railButton203, new org.netbeans.lib.awtextra.AbsoluteConstraints(816, 315, 40, 20));

        railButton204.setBorderPainted(false);
        railButton204.setContentAreaFilled(false);
        railButton204.setFocusable(false);
        buttonPanel.add(railButton204, new org.netbeans.lib.awtextra.AbsoluteConstraints(815, 395, 40, 20));

        railButton205.setBorderPainted(false);
        railButton205.setContentAreaFilled(false);
        railButton205.setFocusable(false);
        buttonPanel.add(railButton205, new org.netbeans.lib.awtextra.AbsoluteConstraints(815, 477, 40, 20));

        railButton206.setBorderPainted(false);
        railButton206.setContentAreaFilled(false);
        railButton206.setFocusable(false);
        buttonPanel.add(railButton206, new org.netbeans.lib.awtextra.AbsoluteConstraints(884, 107, 40, 20));

        railButton207.setBorderPainted(false);
        railButton207.setContentAreaFilled(false);
        railButton207.setFocusable(false);
        buttonPanel.add(railButton207, new org.netbeans.lib.awtextra.AbsoluteConstraints(888, 189, 40, 20));

        railButton208.setBorderPainted(false);
        railButton208.setContentAreaFilled(false);
        railButton208.setFocusable(false);
        buttonPanel.add(railButton208, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 274, 40, 20));

        railButton209.setBorderPainted(false);
        railButton209.setContentAreaFilled(false);
        railButton209.setFocusable(false);
        buttonPanel.add(railButton209, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 354, 40, 20));

        railButton210.setBorderPainted(false);
        railButton210.setContentAreaFilled(false);
        railButton210.setFocusable(false);
        buttonPanel.add(railButton210, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 148, 40, 20));

        railButton211.setBorderPainted(false);
        railButton211.setContentAreaFilled(false);
        railButton211.setFocusable(false);
        buttonPanel.add(railButton211, new org.netbeans.lib.awtextra.AbsoluteConstraints(955, 64, 40, 20));

        railButton212.setBorderPainted(false);
        railButton212.setContentAreaFilled(false);
        railButton212.setFocusable(false);
        buttonPanel.add(railButton212, new org.netbeans.lib.awtextra.AbsoluteConstraints(393, 397, 40, 20));

        railButton213.setBorderPainted(false);
        railButton213.setContentAreaFilled(false);
        railButton213.setFocusable(false);
        buttonPanel.add(railButton213, new org.netbeans.lib.awtextra.AbsoluteConstraints(393, 314, 40, 20));

        railButton214.setBorderPainted(false);
        railButton214.setContentAreaFilled(false);
        railButton214.setFocusable(false);
        buttonPanel.add(railButton214, new org.netbeans.lib.awtextra.AbsoluteConstraints(394, 231, 40, 20));

        railButton215.setBorderPainted(false);
        railButton215.setContentAreaFilled(false);
        railButton215.setFocusable(false);
        buttonPanel.add(railButton215, new org.netbeans.lib.awtextra.AbsoluteConstraints(394, 151, 40, 20));

        seeAllButton1.setBackground(new java.awt.Color(239, 228, 176));
        seeAllButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        seeAllButton1.setText("See All");
        seeAllButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        seeAllButton1.setContentAreaFilled(false);
        seeAllButton1.setFocusable(false);
        seeAllButton1.setOpaque(true);
        seeAllButton1.setPreferredSize(new java.awt.Dimension(75, 25));
        seeAllButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                seeAllButton1MouseReleased(evt);
            }
        });
        buttonPanel.add(seeAllButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(772, 122, 100, -1));

        dicePanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        dicePanel.setOpaque(false);
        dicePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        dicePanel.add(diceOneLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));
        dicePanel.add(diceTwoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        buttonPanel.add(dicePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 410, 70, 130));

        infoDialog.setBackground(new java.awt.Color(255, 255, 255));
        infoDialog.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        infoDialogOne.setBackground(Color.WHITE);
        infoDialogOne.setOpaque(true);
        infoDialog.add(infoDialogOne, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 6, 230, -1));

        infoDialogTwo.setBackground(new java.awt.Color(255, 255, 255));
        infoDialog.add(infoDialogTwo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 27, 230, -1));

        infoDialogThree.setBackground(new java.awt.Color(255, 255, 255));
        infoDialog.add(infoDialogThree, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 48, 230, -1));

        infoDialogFour.setBackground(new java.awt.Color(255, 255, 255));
        infoDialog.add(infoDialogFour, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 69, 230, -1));

        infoDialogFive.setBackground(new java.awt.Color(255, 255, 255));
        infoDialog.add(infoDialogFive, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 230, -1));

        buttonPanel.add(infoDialog, new org.netbeans.lib.awtextra.AbsoluteConstraints(622, 5, 250, 110));

        buildCityButton1.setBackground(new java.awt.Color(239, 228, 176));
        buildCityButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        buildCityButton1.setText("Build City");
        buildCityButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buildCityButton1.setContentAreaFilled(false);
        buildCityButton1.setFocusable(false);
        buildCityButton1.setOpaque(true);
        buildCityButton1.setPreferredSize(new java.awt.Dimension(75, 25));
        buildCityButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                buildCityButton1MouseReleased(evt);
            }
        });
        buttonPanel.add(buildCityButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 660, 120, -1));

        trashBinButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/trashBin.png"))); // NOI18N
        trashBinButton.setBorderPainted(false);
        trashBinButton.setContentAreaFilled(false);
        trashBinButton.setFocusPainted(false);
        trashBinButton.setFocusable(false);
        trashBinButton.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                spokaneButtonMouseMoved(evt);
            }
        });
        trashBinButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trashBinButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(trashBinButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 550, 77, 98));

        displayCityNamesToggleButton.setBackground(new java.awt.Color(239, 228, 176));
        displayCityNamesToggleButton.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        displayCityNamesToggleButton.setText("Display City Names");
        displayCityNamesToggleButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        displayCityNamesToggleButton.setContentAreaFilled(false);
        displayCityNamesToggleButton.setFocusable(false);
        displayCityNamesToggleButton.setOpaque(true);
        displayCityNamesToggleButton.setPreferredSize(new java.awt.Dimension(111, 25));
        displayCityNamesToggleButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                displayCityNamesToggleButtonMousePressed(evt);
            }
        });
        buttonPanel.add(displayCityNamesToggleButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 660, 150, -1));
        buttonPanel.add(refreshGameFrameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 680, -1, -1));

        cancelButton1.setBackground(new java.awt.Color(239, 228, 176));
        cancelButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        cancelButton1.setText("Cancel");
        cancelButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cancelButton1.setContentAreaFilled(false);
        cancelButton1.setFocusable(false);
        cancelButton1.setOpaque(true);
        cancelButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cancelButton1MouseReleased(evt);
            }
        });
        buttonPanel.add(cancelButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(675, 620, 75, 25));

        developmentCardButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/developmentCard.png"))); // NOI18N
        developmentCardButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                developmentCardButton1MouseReleased(evt);
            }
        });
        buttonPanel.add(developmentCardButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 530, 60, 90));

        hexButton1.setBorder(null);
        hexButton1.setBorderPainted(false);
        hexButton1.setContentAreaFilled(false);
        hexButton1.setFocusPainted(false);
        hexButton1.setFocusable(false);
        buttonPanel.add(hexButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 210, 68, 70));

        hexButton2.setBorder(null);
        hexButton2.setBorderPainted(false);
        hexButton2.setContentAreaFilled(false);
        hexButton2.setFocusPainted(false);
        hexButton2.setFocusable(false);
        buttonPanel.add(hexButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 127, 68, 70));

        hexButton3.setBorder(null);
        hexButton3.setBorderPainted(false);
        hexButton3.setContentAreaFilled(false);
        hexButton3.setFocusPainted(false);
        hexButton3.setFocusable(false);
        buttonPanel.add(hexButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 290, 68, 70));

        hexButton4.setBorder(null);
        hexButton4.setBorderPainted(false);
        hexButton4.setContentAreaFilled(false);
        hexButton4.setFocusPainted(false);
        hexButton4.setFocusable(false);
        buttonPanel.add(hexButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 370, 60, 70));

        hexButton5.setBorder(null);
        hexButton5.setBorderPainted(false);
        hexButton5.setContentAreaFilled(false);
        hexButton5.setFocusPainted(false);
        hexButton5.setFocusable(false);
        buttonPanel.add(hexButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 450, 60, 70));

        hexButton6.setBorder(null);
        hexButton6.setBorderPainted(false);
        hexButton6.setContentAreaFilled(false);
        hexButton6.setFocusPainted(false);
        hexButton6.setFocusable(false);
        buttonPanel.add(hexButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(103, 170, 68, 65));

        hexButton7.setBorder(null);
        hexButton7.setBorderPainted(false);
        hexButton7.setContentAreaFilled(false);
        hexButton7.setFocusPainted(false);
        hexButton7.setFocusable(false);
        buttonPanel.add(hexButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(103, 251, 68, 70));

        hexButton8.setBorder(null);
        hexButton8.setBorderPainted(false);
        hexButton8.setContentAreaFilled(false);
        hexButton8.setFocusPainted(false);
        hexButton8.setFocusable(false);
        buttonPanel.add(hexButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(103, 331, 68, 70));

        hexButton9.setBorder(null);
        hexButton9.setBorderPainted(false);
        hexButton9.setContentAreaFilled(false);
        hexButton9.setFocusPainted(false);
        hexButton9.setFocusable(false);
        buttonPanel.add(hexButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(101, 410, 68, 70));

        hexButton10.setBorder(null);
        hexButton10.setBorderPainted(false);
        hexButton10.setContentAreaFilled(false);
        hexButton10.setFocusPainted(false);
        hexButton10.setFocusable(false);
        buttonPanel.add(hexButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(101, 493, 66, 67));

        hexButton11.setBorder(null);
        hexButton11.setBorderPainted(false);
        hexButton11.setContentAreaFilled(false);
        hexButton11.setFocusPainted(false);
        hexButton11.setFocusable(false);
        buttonPanel.add(hexButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(165, 533, 68, 59));

        hexButton12.setBorder(null);
        hexButton12.setBorderPainted(false);
        hexButton12.setContentAreaFilled(false);
        hexButton12.setFocusPainted(false);
        hexButton12.setFocusable(false);
        buttonPanel.add(hexButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 452, 63, 70));

        hexButton13.setBorder(null);
        hexButton13.setBorderPainted(false);
        hexButton13.setContentAreaFilled(false);
        hexButton13.setFocusPainted(false);
        hexButton13.setFocusable(false);
        buttonPanel.add(hexButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(171, 370, 63, 70));

        hexButton14.setBorder(null);
        hexButton14.setBorderPainted(false);
        hexButton14.setContentAreaFilled(false);
        hexButton14.setFocusPainted(false);
        hexButton14.setFocusable(false);
        buttonPanel.add(hexButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 290, 63, 70));

        hexButton15.setBorder(null);
        hexButton15.setBorderPainted(false);
        hexButton15.setContentAreaFilled(false);
        hexButton15.setFocusPainted(false);
        hexButton15.setFocusable(false);
        buttonPanel.add(hexButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 208, 63, 70));

        hexButton16.setBorder(null);
        hexButton16.setBorderPainted(false);
        hexButton16.setContentAreaFilled(false);
        hexButton16.setFocusPainted(false);
        hexButton16.setFocusable(false);
        buttonPanel.add(hexButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(172, 136, 67, 60));

        hexButton17.setBorder(null);
        hexButton17.setBorderPainted(false);
        hexButton17.setContentAreaFilled(false);
        hexButton17.setFocusPainted(false);
        hexButton17.setFocusable(false);
        buttonPanel.add(hexButton17, new org.netbeans.lib.awtextra.AbsoluteConstraints(238, 168, 68, 70));

        hexButton18.setBorder(null);
        hexButton18.setBorderPainted(false);
        hexButton18.setContentAreaFilled(false);
        hexButton18.setFocusPainted(false);
        hexButton18.setFocusable(false);
        buttonPanel.add(hexButton18, new org.netbeans.lib.awtextra.AbsoluteConstraints(238, 248, 68, 70));

        hexButton19.setBorder(null);
        hexButton19.setBorderPainted(false);
        hexButton19.setContentAreaFilled(false);
        hexButton19.setFocusPainted(false);
        hexButton19.setFocusable(false);
        buttonPanel.add(hexButton19, new org.netbeans.lib.awtextra.AbsoluteConstraints(238, 330, 68, 70));

        hexButton20.setBorder(null);
        hexButton20.setBorderPainted(false);
        hexButton20.setContentAreaFilled(false);
        hexButton20.setFocusPainted(false);
        hexButton20.setFocusable(false);
        buttonPanel.add(hexButton20, new org.netbeans.lib.awtextra.AbsoluteConstraints(236, 411, 68, 70));

        hexButton21.setBorder(null);
        hexButton21.setBorderPainted(false);
        hexButton21.setContentAreaFilled(false);
        hexButton21.setFocusPainted(false);
        hexButton21.setFocusable(false);
        buttonPanel.add(hexButton21, new org.netbeans.lib.awtextra.AbsoluteConstraints(234, 494, 68, 70));

        hexButton22.setBorder(null);
        hexButton22.setBorderPainted(false);
        hexButton22.setContentAreaFilled(false);
        hexButton22.setFocusPainted(false);
        hexButton22.setFocusable(false);
        buttonPanel.add(hexButton22, new org.netbeans.lib.awtextra.AbsoluteConstraints(303, 534, 68, 65));

        hexButton23.setBorder(null);
        hexButton23.setBorderPainted(false);
        hexButton23.setContentAreaFilled(false);
        hexButton23.setFocusPainted(false);
        hexButton23.setFocusable(false);
        buttonPanel.add(hexButton23, new org.netbeans.lib.awtextra.AbsoluteConstraints(303, 456, 68, 66));

        hexButton24.setBorder(null);
        hexButton24.setBorderPainted(false);
        hexButton24.setContentAreaFilled(false);
        hexButton24.setFocusPainted(false);
        hexButton24.setFocusable(false);
        buttonPanel.add(hexButton24, new org.netbeans.lib.awtextra.AbsoluteConstraints(306, 372, 68, 66));

        hexButton25.setBorder(null);
        hexButton25.setBorderPainted(false);
        hexButton25.setContentAreaFilled(false);
        hexButton25.setFocusPainted(false);
        hexButton25.setFocusable(false);
        buttonPanel.add(hexButton25, new org.netbeans.lib.awtextra.AbsoluteConstraints(306, 290, 68, 66));

        hexButton26.setBorder(null);
        hexButton26.setBorderPainted(false);
        hexButton26.setContentAreaFilled(false);
        hexButton26.setFocusPainted(false);
        hexButton26.setFocusable(false);
        buttonPanel.add(hexButton26, new org.netbeans.lib.awtextra.AbsoluteConstraints(309, 209, 68, 66));

        hexButton27.setBorder(null);
        hexButton27.setBorderPainted(false);
        hexButton27.setContentAreaFilled(false);
        hexButton27.setFocusPainted(false);
        hexButton27.setFocusable(false);
        buttonPanel.add(hexButton27, new org.netbeans.lib.awtextra.AbsoluteConstraints(308, 134, 68, 60));

        hexButton28.setBorder(null);
        hexButton28.setBorderPainted(false);
        hexButton28.setContentAreaFilled(false);
        hexButton28.setFocusPainted(false);
        hexButton28.setFocusable(false);
        buttonPanel.add(hexButton28, new org.netbeans.lib.awtextra.AbsoluteConstraints(379, 168, 68, 66));

        hexButton29.setBorder(null);
        hexButton29.setBorderPainted(false);
        hexButton29.setContentAreaFilled(false);
        hexButton29.setFocusPainted(false);
        hexButton29.setFocusable(false);
        buttonPanel.add(hexButton29, new org.netbeans.lib.awtextra.AbsoluteConstraints(379, 250, 68, 66));

        hexButton30.setBorder(null);
        hexButton30.setBorderPainted(false);
        hexButton30.setContentAreaFilled(false);
        hexButton30.setFocusPainted(false);
        hexButton30.setFocusable(false);
        buttonPanel.add(hexButton30, new org.netbeans.lib.awtextra.AbsoluteConstraints(379, 330, 68, 70));

        hexButton31.setBorder(null);
        hexButton31.setBorderPainted(false);
        hexButton31.setContentAreaFilled(false);
        hexButton31.setFocusPainted(false);
        hexButton31.setFocusable(false);
        buttonPanel.add(hexButton31, new org.netbeans.lib.awtextra.AbsoluteConstraints(378, 416, 68, 66));

        hexButton32.setBorder(null);
        hexButton32.setBorderPainted(false);
        hexButton32.setContentAreaFilled(false);
        hexButton32.setFocusPainted(false);
        hexButton32.setFocusable(false);
        buttonPanel.add(hexButton32, new org.netbeans.lib.awtextra.AbsoluteConstraints(378, 496, 68, 66));

        hexButton33.setBorder(null);
        hexButton33.setBorderPainted(false);
        hexButton33.setContentAreaFilled(false);
        hexButton33.setFocusPainted(false);
        hexButton33.setFocusable(false);
        buttonPanel.add(hexButton33, new org.netbeans.lib.awtextra.AbsoluteConstraints(378, 576, 68, 66));

        hexButton34.setBorder(null);
        hexButton34.setBorderPainted(false);
        hexButton34.setContentAreaFilled(false);
        hexButton34.setFocusPainted(false);
        hexButton34.setFocusable(false);
        buttonPanel.add(hexButton34, new org.netbeans.lib.awtextra.AbsoluteConstraints(448, 537, 68, 65));

        hexButton35.setBorder(null);
        hexButton35.setBorderPainted(false);
        hexButton35.setContentAreaFilled(false);
        hexButton35.setFocusPainted(false);
        hexButton35.setFocusable(false);
        buttonPanel.add(hexButton35, new org.netbeans.lib.awtextra.AbsoluteConstraints(448, 455, 68, 65));

        hexButton36.setBorder(null);
        hexButton36.setBorderPainted(false);
        hexButton36.setContentAreaFilled(false);
        hexButton36.setFocusPainted(false);
        hexButton36.setFocusable(false);
        buttonPanel.add(hexButton36, new org.netbeans.lib.awtextra.AbsoluteConstraints(448, 376, 68, 65));

        hexButton37.setBorder(null);
        hexButton37.setBorderPainted(false);
        hexButton37.setContentAreaFilled(false);
        hexButton37.setFocusPainted(false);
        hexButton37.setFocusable(false);
        buttonPanel.add(hexButton37, new org.netbeans.lib.awtextra.AbsoluteConstraints(448, 293, 68, 65));

        hexButton38.setBorder(null);
        hexButton38.setBorderPainted(false);
        hexButton38.setContentAreaFilled(false);
        hexButton38.setFocusPainted(false);
        hexButton38.setFocusable(false);
        buttonPanel.add(hexButton38, new org.netbeans.lib.awtextra.AbsoluteConstraints(448, 210, 68, 65));

        hexButton39.setBorder(null);
        hexButton39.setBorderPainted(false);
        hexButton39.setContentAreaFilled(false);
        hexButton39.setFocusPainted(false);
        hexButton39.setFocusable(false);
        buttonPanel.add(hexButton39, new org.netbeans.lib.awtextra.AbsoluteConstraints(448, 128, 68, 65));

        hexButton40.setBorder(null);
        hexButton40.setBorderPainted(false);
        hexButton40.setContentAreaFilled(false);
        hexButton40.setFocusPainted(false);
        hexButton40.setFocusable(false);
        buttonPanel.add(hexButton40, new org.netbeans.lib.awtextra.AbsoluteConstraints(522, 169, 68, 65));

        hexButton41.setBorder(null);
        hexButton41.setBorderPainted(false);
        hexButton41.setContentAreaFilled(false);
        hexButton41.setFocusPainted(false);
        hexButton41.setFocusable(false);
        buttonPanel.add(hexButton41, new org.netbeans.lib.awtextra.AbsoluteConstraints(522, 250, 68, 65));

        hexButton42.setBorder(null);
        hexButton42.setBorderPainted(false);
        hexButton42.setContentAreaFilled(false);
        hexButton42.setFocusPainted(false);
        hexButton42.setFocusable(false);
        buttonPanel.add(hexButton42, new org.netbeans.lib.awtextra.AbsoluteConstraints(517, 330, 69, 68));

        hexButton43.setBorder(null);
        hexButton43.setBorderPainted(false);
        hexButton43.setContentAreaFilled(false);
        hexButton43.setFocusPainted(false);
        hexButton43.setFocusable(false);
        buttonPanel.add(hexButton43, new org.netbeans.lib.awtextra.AbsoluteConstraints(517, 412, 69, 68));

        hexButton44.setBorder(null);
        hexButton44.setBorderPainted(false);
        hexButton44.setContentAreaFilled(false);
        hexButton44.setFocusPainted(false);
        hexButton44.setFocusable(false);
        buttonPanel.add(hexButton44, new org.netbeans.lib.awtextra.AbsoluteConstraints(517, 494, 70, 68));

        hexButton45.setBorder(null);
        hexButton45.setBorderPainted(false);
        hexButton45.setContentAreaFilled(false);
        hexButton45.setFocusPainted(false);
        hexButton45.setFocusable(false);
        buttonPanel.add(hexButton45, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 577, 69, 68));

        hexButton46.setBorder(null);
        hexButton46.setBorderPainted(false);
        hexButton46.setContentAreaFilled(false);
        hexButton46.setFocusPainted(false);
        hexButton46.setFocusable(false);
        buttonPanel.add(hexButton46, new org.netbeans.lib.awtextra.AbsoluteConstraints(589, 534, 69, 63));

        hexButton47.setBorder(null);
        hexButton47.setBorderPainted(false);
        hexButton47.setContentAreaFilled(false);
        hexButton47.setFocusPainted(false);
        hexButton47.setFocusable(false);
        buttonPanel.add(hexButton47, new org.netbeans.lib.awtextra.AbsoluteConstraints(589, 451, 68, 67));

        hexButton48.setBorder(null);
        hexButton48.setBorderPainted(false);
        hexButton48.setContentAreaFilled(false);
        hexButton48.setFocusPainted(false);
        hexButton48.setFocusable(false);
        buttonPanel.add(hexButton48, new org.netbeans.lib.awtextra.AbsoluteConstraints(589, 370, 68, 67));

        hexButton49.setBorder(null);
        hexButton49.setBorderPainted(false);
        hexButton49.setContentAreaFilled(false);
        hexButton49.setFocusPainted(false);
        hexButton49.setFocusable(false);
        buttonPanel.add(hexButton49, new org.netbeans.lib.awtextra.AbsoluteConstraints(592, 289, 66, 67));

        hexButton50.setBorder(null);
        hexButton50.setBorderPainted(false);
        hexButton50.setContentAreaFilled(false);
        hexButton50.setFocusPainted(false);
        hexButton50.setFocusable(false);
        buttonPanel.add(hexButton50, new org.netbeans.lib.awtextra.AbsoluteConstraints(594, 210, 66, 67));

        hexButton51.setBorder(null);
        hexButton51.setBorderPainted(false);
        hexButton51.setContentAreaFilled(false);
        hexButton51.setFocusPainted(false);
        hexButton51.setFocusable(false);
        buttonPanel.add(hexButton51, new org.netbeans.lib.awtextra.AbsoluteConstraints(662, 170, 66, 67));

        hexButton52.setBorder(null);
        hexButton52.setBorderPainted(false);
        hexButton52.setContentAreaFilled(false);
        hexButton52.setFocusPainted(false);
        hexButton52.setFocusable(false);
        buttonPanel.add(hexButton52, new org.netbeans.lib.awtextra.AbsoluteConstraints(663, 250, 66, 67));

        hexButton53.setBorder(null);
        hexButton53.setBorderPainted(false);
        hexButton53.setContentAreaFilled(false);
        hexButton53.setFocusPainted(false);
        hexButton53.setFocusable(false);
        buttonPanel.add(hexButton53, new org.netbeans.lib.awtextra.AbsoluteConstraints(663, 330, 66, 67));

        hexButton54.setBorder(null);
        hexButton54.setBorderPainted(false);
        hexButton54.setContentAreaFilled(false);
        hexButton54.setFocusPainted(false);
        hexButton54.setFocusable(false);
        buttonPanel.add(hexButton54, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 410, 66, 67));

        hexButton55.setBorder(null);
        hexButton55.setBorderPainted(false);
        hexButton55.setContentAreaFilled(false);
        hexButton55.setFocusPainted(false);
        hexButton55.setFocusable(false);
        buttonPanel.add(hexButton55, new org.netbeans.lib.awtextra.AbsoluteConstraints(661, 493, 66, 67));

        hexButton56.setBorder(null);
        hexButton56.setBorderPainted(false);
        hexButton56.setContentAreaFilled(false);
        hexButton56.setFocusPainted(false);
        hexButton56.setFocusable(false);
        buttonPanel.add(hexButton56, new org.netbeans.lib.awtextra.AbsoluteConstraints(732, 451, 66, 67));

        hexButton57.setBorder(null);
        hexButton57.setBorderPainted(false);
        hexButton57.setContentAreaFilled(false);
        hexButton57.setFocusPainted(false);
        hexButton57.setFocusable(false);
        buttonPanel.add(hexButton57, new org.netbeans.lib.awtextra.AbsoluteConstraints(732, 371, 66, 67));

        hexButton58.setBorder(null);
        hexButton58.setBorderPainted(false);
        hexButton58.setContentAreaFilled(false);
        hexButton58.setFocusPainted(false);
        hexButton58.setFocusable(false);
        buttonPanel.add(hexButton58, new org.netbeans.lib.awtextra.AbsoluteConstraints(732, 290, 66, 67));

        hexButton59.setBorder(null);
        hexButton59.setBorderPainted(false);
        hexButton59.setContentAreaFilled(false);
        hexButton59.setFocusPainted(false);
        hexButton59.setFocusable(false);
        buttonPanel.add(hexButton59, new org.netbeans.lib.awtextra.AbsoluteConstraints(733, 208, 66, 68));

        hexButton60.setBorder(null);
        hexButton60.setBorderPainted(false);
        hexButton60.setContentAreaFilled(false);
        hexButton60.setFocusPainted(false);
        hexButton60.setFocusable(false);
        buttonPanel.add(hexButton60, new org.netbeans.lib.awtextra.AbsoluteConstraints(803, 170, 66, 68));

        hexButton61.setBorder(null);
        hexButton61.setBorderPainted(false);
        hexButton61.setContentAreaFilled(false);
        hexButton61.setFocusPainted(false);
        hexButton61.setFocusable(false);
        buttonPanel.add(hexButton61, new org.netbeans.lib.awtextra.AbsoluteConstraints(803, 250, 66, 68));

        hexButton62.setBorder(null);
        hexButton62.setBorderPainted(false);
        hexButton62.setContentAreaFilled(false);
        hexButton62.setFocusPainted(false);
        hexButton62.setFocusable(false);
        buttonPanel.add(hexButton62, new org.netbeans.lib.awtextra.AbsoluteConstraints(803, 330, 66, 68));

        hexButton63.setBorder(null);
        hexButton63.setBorderPainted(false);
        hexButton63.setContentAreaFilled(false);
        hexButton63.setFocusPainted(false);
        hexButton63.setFocusable(false);
        buttonPanel.add(hexButton63, new org.netbeans.lib.awtextra.AbsoluteConstraints(803, 411, 66, 68));

        hexButton64.setBorder(null);
        hexButton64.setBorderPainted(false);
        hexButton64.setContentAreaFilled(false);
        hexButton64.setFocusPainted(false);
        hexButton64.setFocusable(false);
        buttonPanel.add(hexButton64, new org.netbeans.lib.awtextra.AbsoluteConstraints(803, 493, 66, 53));

        hexButton65.setBorder(null);
        hexButton65.setBorderPainted(false);
        hexButton65.setContentAreaFilled(false);
        hexButton65.setFocusPainted(false);
        hexButton65.setFocusable(false);
        buttonPanel.add(hexButton65, new org.netbeans.lib.awtextra.AbsoluteConstraints(881, 537, 60, 68));

        hexButton66.setBorder(null);
        hexButton66.setBorderPainted(false);
        hexButton66.setContentAreaFilled(false);
        hexButton66.setFocusPainted(false);
        hexButton66.setFocusable(false);
        buttonPanel.add(hexButton66, new org.netbeans.lib.awtextra.AbsoluteConstraints(875, 373, 64, 68));

        hexButton67.setBorder(null);
        hexButton67.setBorderPainted(false);
        hexButton67.setContentAreaFilled(false);
        hexButton67.setFocusPainted(false);
        hexButton67.setFocusable(false);
        buttonPanel.add(hexButton67, new org.netbeans.lib.awtextra.AbsoluteConstraints(875, 291, 65, 68));

        hexButton68.setBorder(null);
        hexButton68.setBorderPainted(false);
        hexButton68.setContentAreaFilled(false);
        hexButton68.setFocusPainted(false);
        hexButton68.setFocusable(false);
        buttonPanel.add(hexButton68, new org.netbeans.lib.awtextra.AbsoluteConstraints(875, 208, 65, 68));

        hexButton69.setBorder(null);
        hexButton69.setBorderPainted(false);
        hexButton69.setContentAreaFilled(false);
        hexButton69.setFocusPainted(false);
        hexButton69.setFocusable(false);
        buttonPanel.add(hexButton69, new org.netbeans.lib.awtextra.AbsoluteConstraints(872, 124, 67, 68));

        hexButton70.setBorder(null);
        hexButton70.setBorderPainted(false);
        hexButton70.setContentAreaFilled(false);
        hexButton70.setFocusPainted(false);
        hexButton70.setFocusable(false);
        buttonPanel.add(hexButton70, new org.netbeans.lib.awtextra.AbsoluteConstraints(943, 81, 55, 68));

        hexButton71.setBorder(null);
        hexButton71.setBorderPainted(false);
        hexButton71.setContentAreaFilled(false);
        hexButton71.setFocusPainted(false);
        hexButton71.setFocusable(false);
        buttonPanel.add(hexButton71, new org.netbeans.lib.awtextra.AbsoluteConstraints(945, 167, 50, 65));

        buildingCostButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/buildingCostsCardSmall.png"))); // NOI18N
        buildingCostButton1.setBorder(null);
        buildingCostButton1.setBorderPainted(false);
        buildingCostButton1.setContentAreaFilled(false);
        buildingCostButton1.setFocusPainted(false);
        buildingCostButton1.setFocusable(false);
        buildingCostButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                buildingCostButton1MouseReleased(evt);
            }
        });
        buildingCostButton1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                buildingCostButton1MouseMoved(evt);
            }
        });
        buttonPanel.add(buildingCostButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 575, -1, -1));

        gamePanel.add(buttonPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        robberSpotPanel.setOpaque(false);
        robberSpotPanel.setPreferredSize(new java.awt.Dimension(1025, 702));
        robberSpotPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        robberSpotPanel.add(robberLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(347, 150, -1, -1));
        robberSpotPanel.add(robberLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 188, -1, -1));
        robberSpotPanel.add(robberLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(239, 269, -1, -1));
        robberSpotPanel.add(robberLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(277, 352, -1, -1));
        robberSpotPanel.add(robberLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(275, 432, -1, -1));
        robberSpotPanel.add(robberLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(274, 515, -1, -1));
        robberSpotPanel.add(robberLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(211, 229, -1, -1));
        robberSpotPanel.add(robberLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(207, 150, -1, -1));
        robberSpotPanel.add(robberLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(208, 311, -1, -1));
        robberSpotPanel.add(robberLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(207, 394, -1, -1));
        robberSpotPanel.add(robberLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(188, 473, -1, -1));
        robberSpotPanel.add(robberLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 550, -1, -1));
        robberSpotPanel.add(robberLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(121, 513, -1, -1));
        robberSpotPanel.add(robberLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(122, 433, -1, -1));
        robberSpotPanel.add(robberLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(141, 271, -1, -1));
        robberSpotPanel.add(robberLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(142, 190, -1, -1));
        robberSpotPanel.add(robberLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(67, 473, -1, -1));
        robberSpotPanel.add(robberLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(74, 391, -1, -1));
        robberSpotPanel.add(robberLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(73, 311, -1, -1));
        robberSpotPanel.add(robberLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(73, 230, -1, -1));
        robberSpotPanel.add(robberLabel0, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 150, -1, -1));

        robberStartLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/robber.png"))); // NOI18N
        robberSpotPanel.add(robberStartLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(123, 353, -1, -1));
        robberSpotPanel.add(robberLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 228, -1, -1));
        robberSpotPanel.add(robberLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(345, 311, -1, -1));
        robberSpotPanel.add(robberLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(345, 391, -1, -1));
        robberSpotPanel.add(robberLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(343, 475, -1, -1));
        robberSpotPanel.add(robberLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(341, 554, -1, -1));
        robberSpotPanel.add(robberLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 590, -1, -1));
        robberSpotPanel.add(robberLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(415, 516, -1, -1));
        robberSpotPanel.add(robberLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(419, 435, -1, -1));
        robberSpotPanel.add(robberLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(418, 351, -1, -1));
        robberSpotPanel.add(robberLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(418, 271, -1, -1));
        robberSpotPanel.add(robberLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 188, -1, -1));
        robberSpotPanel.add(robberLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(489, 555, -1, -1));
        robberSpotPanel.add(robberLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(487, 476, -1, -1));
        robberSpotPanel.add(robberLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(488, 395, -1, -1));
        robberSpotPanel.add(robberLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(488, 313, -1, -1));
        robberSpotPanel.add(robberLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(488, 228, -1, -1));
        robberSpotPanel.add(robberLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(488, 148, -1, -1));
        robberSpotPanel.add(robberLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(561, 596, -1, -1));
        robberSpotPanel.add(robberLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(556, 514, -1, -1));
        robberSpotPanel.add(robberLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 432, -1, -1));
        robberSpotPanel.add(robberLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(559, 350, -1, -1));
        robberSpotPanel.add(robberLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(562, 271, -1, -1));
        robberSpotPanel.add(robberLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(561, 190, -1, -1));
        robberSpotPanel.add(robberLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 553, -1, -1));
        robberSpotPanel.add(robberLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(629, 471, -1, -1));
        robberSpotPanel.add(robberLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(627, 390, -1, -1));
        robberSpotPanel.add(robberLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(631, 310, -1, -1));
        robberSpotPanel.add(robberLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 512, -1, -1));
        robberSpotPanel.add(robberLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(633, 231, -1, -1));
        robberSpotPanel.add(robberLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(699, 429, -1, -1));
        robberSpotPanel.add(robberLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(701, 350, -1, -1));
        robberSpotPanel.add(robberLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(703, 270, -1, -1));
        robberSpotPanel.add(robberLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 190, -1, -1));
        robberSpotPanel.add(robberLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(771, 471, -1, -1));
        robberSpotPanel.add(robberLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(771, 391, -1, -1));
        robberSpotPanel.add(robberLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 310, -1, -1));
        robberSpotPanel.add(robberLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(772, 227, -1, -1));
        robberSpotPanel.add(robberLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(844, 514, -1, -1));
        robberSpotPanel.add(robberLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(841, 432, -1, -1));
        robberSpotPanel.add(robberLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(843, 350, -1, -1));
        robberSpotPanel.add(robberLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(843, 269, -1, -1));
        robberSpotPanel.add(robberLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 188, -1, -1));
        robberSpotPanel.add(robberLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(917, 558, -1, -1));
        robberSpotPanel.add(robberLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(873, 393, -1, -1));
        robberSpotPanel.add(robberLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(915, 230, -1, -1));
        robberSpotPanel.add(robberLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(913, 312, -1, -1));
        robberSpotPanel.add(robberLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(912, 143, -1, -1));
        robberSpotPanel.add(robberLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(942, 101, -1, -1));
        robberSpotPanel.add(robberLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(939, 185, -1, -1));

        oreCardLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/oreHalfSize.jpg"))); // NOI18N
        robberSpotPanel.add(oreCardLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 10, -1, -1));

        lumberCardLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/lumberHalfSize.jpg"))); // NOI18N
        robberSpotPanel.add(lumberCardLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        coalCardLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/coalHalfSize.jpg"))); // NOI18N
        robberSpotPanel.add(coalCardLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, -1, -1));

        wheatCardLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/wheatHalfSize.jpg"))); // NOI18N
        robberSpotPanel.add(wheatCardLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, -1, -1));

        cattleCardLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/cattleHalfSize.jpg"))); // NOI18N
        robberSpotPanel.add(cattleCardLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, -1, -1));

        xLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        xLabel.setText("X");
        robberSpotPanel.add(xLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(577, 66, 10, -1));

        xLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        xLabel1.setText("X");
        robberSpotPanel.add(xLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 66, 10, -1));

        xLabel2.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        xLabel2.setText("X");
        robberSpotPanel.add(xLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 66, 10, -1));

        xLabel3.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        xLabel3.setText("X");
        robberSpotPanel.add(xLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 66, 10, -1));

        xLabel4.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        xLabel4.setText("X");
        robberSpotPanel.add(xLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 66, 10, -1));

        xLabel5.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        xLabel5.setText("X");
        robberSpotPanel.add(xLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 66, 10, -1));

        numberOfCoalLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        robberSpotPanel.add(numberOfCoalLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(187, 61, -1, -1));

        numberOfLumberLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        robberSpotPanel.add(numberOfLumberLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(77, 61, -1, -1));

        numberOfWheatLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        robberSpotPanel.add(numberOfWheatLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(297, 61, -1, -1));

        numberOfCattleLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        robberSpotPanel.add(numberOfCattleLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(407, 61, -1, -1));

        numberOfOreLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        robberSpotPanel.add(numberOfOreLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(517, 61, -1, -1));

        goldCoinLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/gold.png"))); // NOI18N
        robberSpotPanel.add(goldCoinLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 66, -1, -1));

        numberOfGoldLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        robberSpotPanel.add(numberOfGoldLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 61, -1, -1));

        gamePanel.add(robberSpotPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        questionChitPanel.setMinimumSize(new java.awt.Dimension(1025, 702));
        questionChitPanel.setOpaque(false);
        questionChitPanel.setPreferredSize(new java.awt.Dimension(1025, 702));
        questionChitPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/singleQuestionChit.png"))); // NOI18N
        questionChitPanel.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(902, 317, -1, -1));

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/singleQuestionChit.png"))); // NOI18N
        questionChitPanel.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(829, 193, -1, -1));

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/singleQuestionChit.png"))); // NOI18N
        questionChitPanel.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 437, -1, -1));

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/singleQuestionChit.png"))); // NOI18N
        questionChitPanel.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(759, 315, -1, -1));

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/singleQuestionChit.png"))); // NOI18N
        questionChitPanel.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(688, 434, -1, -1));

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/singleQuestionChit.png"))); // NOI18N
        questionChitPanel.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(689, 195, -1, -1));

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/singleQuestionChit.png"))); // NOI18N
        questionChitPanel.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(622, 236, -1, -1));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/singleQuestionChit.png"))); // NOI18N
        questionChitPanel.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(616, 395, -1, -1));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/singleQuestionChit.png"))); // NOI18N
        questionChitPanel.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(545, 519, -1, -1));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/singleQuestionChit.png"))); // NOI18N
        questionChitPanel.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(477, 153, -1, -1));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/singleQuestionChit.png"))); // NOI18N
        questionChitPanel.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(477, 318, -1, -1));

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/singleQuestionChit.png"))); // NOI18N
        questionChitPanel.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(477, 400, -1, -1));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/doubleQuestionChit.png"))); // NOI18N
        questionChitPanel.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(476, 481, -1, -1));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/doubleQuestionChit.png"))); // NOI18N
        questionChitPanel.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(407, 276, -1, -1));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/doubleQuestionChit.png"))); // NOI18N
        questionChitPanel.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 559, -1, -1));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/doubleQuestionChit.png"))); // NOI18N
        questionChitPanel.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(332, 480, -1, -1));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/doubleQuestionChit.png"))); // NOI18N
        questionChitPanel.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(334, 316, -1, -1));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/doubleQuestionChit.png"))); // NOI18N
        questionChitPanel.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(336, 155, -1, -1));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/doubleQuestionChit.png"))); // NOI18N
        questionChitPanel.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(264, 437, -1, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/doubleQuestionChit.png"))); // NOI18N
        questionChitPanel.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(197, 316, -1, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/doubleQuestionChit.png"))); // NOI18N
        questionChitPanel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 276, -1, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/doubleQuestionChit.png"))); // NOI18N
        questionChitPanel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(196, 155, -1, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/doubleQuestionChit.png"))); // NOI18N
        questionChitPanel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 478, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/doubleQuestionChit.png"))); // NOI18N
        questionChitPanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 316, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/doubleQuestionChit.png"))); // NOI18N
        questionChitPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 235, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/doubleQuestionChit.png"))); // NOI18N
        questionChitPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(64, 155, -1, -1));

        gamePanel.add(questionChitPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/blankBoardDark.png"))); // NOI18N
        background.setOpaque(true);
        gamePanel.add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(gamePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 700));

        menuPanel.setOpaque(false);
        menuPanel.setPreferredSize(new java.awt.Dimension(50, 50));
        menuPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        goodsSidePanel.setOpaque(false);
        goodsSidePanel.setPreferredSize(new java.awt.Dimension(200, 697));
        goodsSidePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        redInfoButton1.setBackground(new java.awt.Color(239, 228, 176));
        redInfoButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        redInfoButton1.setText("Info");
        redInfoButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        redInfoButton1.setContentAreaFilled(false);
        redInfoButton1.setFocusable(false);
        redInfoButton1.setOpaque(true);
        redInfoButton1.setPreferredSize(new java.awt.Dimension(75, 25));
        redInfoButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                redInfoButton1MouseReleased(evt);
            }
        });
        redInfoButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                redInfoButton1ActionPerformed(evt);
            }
        });
        goodsSidePanel.add(redInfoButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 40, 50, -1));

        greenInfoButton1.setBackground(new java.awt.Color(239, 228, 176));
        greenInfoButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        greenInfoButton1.setText("Info");
        greenInfoButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        greenInfoButton1.setContentAreaFilled(false);
        greenInfoButton1.setFocusable(false);
        greenInfoButton1.setOpaque(true);
        greenInfoButton1.setPreferredSize(new java.awt.Dimension(75, 25));
        greenInfoButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                greenInfoButton1MouseReleased(evt);
            }
        });
        greenInfoButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                greenInfoButton1ActionPerformed(evt);
            }
        });
        goodsSidePanel.add(greenInfoButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 148, 50, -1));

        whiteInfoButton1.setBackground(new java.awt.Color(239, 228, 176));
        whiteInfoButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        whiteInfoButton1.setText("Info");
        whiteInfoButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        whiteInfoButton1.setContentAreaFilled(false);
        whiteInfoButton1.setFocusable(false);
        whiteInfoButton1.setOpaque(true);
        whiteInfoButton1.setPreferredSize(new java.awt.Dimension(75, 25));
        whiteInfoButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                whiteInfoButton1MouseReleased(evt);
            }
        });
        whiteInfoButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                whiteInfoButton1ActionPerformed(evt);
            }
        });
        goodsSidePanel.add(whiteInfoButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 256, 50, -1));

        orangeInfoButton1.setBackground(new java.awt.Color(239, 228, 176));
        orangeInfoButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        orangeInfoButton1.setText("Info");
        orangeInfoButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        orangeInfoButton1.setContentAreaFilled(false);
        orangeInfoButton1.setFocusable(false);
        orangeInfoButton1.setOpaque(true);
        orangeInfoButton1.setPreferredSize(new java.awt.Dimension(75, 25));
        orangeInfoButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                orangeInfoButton1MouseReleased(evt);
            }
        });
        goodsSidePanel.add(orangeInfoButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 364, 50, -1));

        errorRedLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/error.png"))); // NOI18N
        goodsSidePanel.add(errorRedLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 70, -1, -1));

        errorGreenLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/error.png"))); // NOI18N
        goodsSidePanel.add(errorGreenLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 178, -1, -1));

        errorWhiteLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/error.png"))); // NOI18N
        goodsSidePanel.add(errorWhiteLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 286, -1, -1));

        errorOrangeLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/error.png"))); // NOI18N
        goodsSidePanel.add(errorOrangeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 394, -1, -1));

        menuPanel.add(goodsSidePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 450));

        redTextTrackCircleLabel.setBackground(new java.awt.Color(239, 228, 176));
        redTextTrackCircleLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        redTextTrackCircleLabel.setText("Red");
        redTextTrackCircleLabel.setOpaque(true);
        menuPanel.add(redTextTrackCircleLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 15, -1, -1));

        greenTextTrackCircleLabel.setBackground(new java.awt.Color(239, 228, 176));
        greenTextTrackCircleLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        greenTextTrackCircleLabel.setText("Green");
        greenTextTrackCircleLabel.setOpaque(true);
        menuPanel.add(greenTextTrackCircleLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 123, -1, -1));

        whiteTextTrackCircleLabel.setBackground(new java.awt.Color(239, 228, 176));
        whiteTextTrackCircleLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        whiteTextTrackCircleLabel.setText("White");
        whiteTextTrackCircleLabel.setOpaque(true);
        menuPanel.add(whiteTextTrackCircleLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 231, -1, -1));

        orangeTextTrackCircleLabel.setBackground(new java.awt.Color(239, 228, 176));
        orangeTextTrackCircleLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        orangeTextTrackCircleLabel.setText("Orange");
        orangeTextTrackCircleLabel.setOpaque(true);
        menuPanel.add(orangeTextTrackCircleLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 339, -1, -1));

        orangeTrackCircleLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/trackCircleTwo.png"))); // NOI18N
        menuPanel.add(orangeTrackCircleLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 340, -1, -1));

        whiteTrackCircleLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/trackCircleTwo.png"))); // NOI18N
        menuPanel.add(whiteTrackCircleLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 231, -1, -1));

        greenTrackCircleLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/trackCircleTwo.png"))); // NOI18N
        menuPanel.add(greenTrackCircleLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 122, -1, -1));

        redTrackCircleLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/trackCircleTwo.png"))); // NOI18N
        menuPanel.add(redTrackCircleLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 13, -1, -1));

        buttonMenuPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        buttonMenuPanel.setOpaque(false);
        buttonMenuPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buildButton1.setBackground(new java.awt.Color(239, 228, 176));
        buildButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        buildButton1.setText("Build");
        buildButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buildButton1.setContentAreaFilled(false);
        buildButton1.setEnabled(false);
        buildButton1.setFocusable(false);
        buildButton1.setOpaque(true);
        buildButton1.setPreferredSize(new java.awt.Dimension(75, 25));
        buildButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                buildButton1MouseReleased(evt);
            }
        });
        buttonMenuPanel.add(buildButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 33, 90, -1));

        rollButton1.setBackground(new java.awt.Color(239, 228, 176));
        rollButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        rollButton1.setText("Roll");
        rollButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        rollButton1.setContentAreaFilled(false);
        rollButton1.setFocusable(false);
        rollButton1.setOpaque(true);
        rollButton1.setPreferredSize(new java.awt.Dimension(75, 25));
        rollButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                rollButton1MouseReleased(evt);
            }
        });
        buttonMenuPanel.add(rollButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 33, 90, -1));

        tradeButton1.setBackground(new java.awt.Color(239, 228, 176));
        tradeButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        tradeButton1.setText("Trade");
        tradeButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tradeButton1.setContentAreaFilled(false);
        tradeButton1.setFocusable(false);
        tradeButton1.setOpaque(true);
        tradeButton1.setPreferredSize(new java.awt.Dimension(75, 25));
        tradeButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tradeButton1MouseReleased(evt);
            }
        });
        tradeButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tradeButton1ActionPerformed(evt);
            }
        });
        buttonMenuPanel.add(tradeButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 99, 90, -1));

        utilitiesButton1.setBackground(new java.awt.Color(239, 228, 176));
        utilitiesButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        utilitiesButton1.setText("City Search");
        utilitiesButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        utilitiesButton1.setContentAreaFilled(false);
        utilitiesButton1.setFocusable(false);
        utilitiesButton1.setOpaque(true);
        utilitiesButton1.setPreferredSize(new java.awt.Dimension(75, 25));
        utilitiesButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                utilitiesButton1MouseReleased(evt);
            }
        });
        utilitiesButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                utilitiesButton1ActionPerformed(evt);
            }
        });
        buttonMenuPanel.add(utilitiesButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 66, 90, -1));

        endTurnButton1.setBackground(new java.awt.Color(239, 228, 176));
        endTurnButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        endTurnButton1.setText("End Turn");
        endTurnButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        endTurnButton1.setContentAreaFilled(false);
        endTurnButton1.setFocusable(false);
        endTurnButton1.setOpaque(true);
        endTurnButton1.setPreferredSize(new java.awt.Dimension(75, 25));
        endTurnButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                endTurnButton1MouseReleased(evt);
            }
        });
        buttonMenuPanel.add(endTurnButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 132, 90, -1));

        optionsButton1.setBackground(new java.awt.Color(239, 228, 176));
        optionsButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        optionsButton1.setText("Options");
        optionsButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        optionsButton1.setContentAreaFilled(false);
        optionsButton1.setFocusable(false);
        optionsButton1.setOpaque(true);
        optionsButton1.setPreferredSize(new java.awt.Dimension(75, 25));
        optionsButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                optionsButton1MouseReleased(evt);
            }
        });
        optionsButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optionsButton1ActionPerformed(evt);
            }
        });
        buttonMenuPanel.add(optionsButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 165, 90, -1));

        exitButton1.setBackground(new java.awt.Color(239, 228, 176));
        exitButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        exitButton1.setText("Main Menu");
        exitButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        exitButton1.setContentAreaFilled(false);
        exitButton1.setFocusable(false);
        exitButton1.setOpaque(true);
        exitButton1.setPreferredSize(new java.awt.Dimension(75, 25));
        exitButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                exitButton1MouseReleased(evt);
            }
        });
        buttonMenuPanel.add(exitButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 198, 90, -1));

        controlsLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        controlsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        controlsLabel.setText("Controls");
        buttonMenuPanel.add(controlsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 6, 150, -1));

        menuPanel.add(buttonMenuPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 450, 170, 232));

        backgroundSideMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/backgroundSideMenu.png"))); // NOI18N
        menuPanel.add(backgroundSideMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(menuPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1026, 0, 200, 700));

        pack();
    }// </editor-fold>//GEN-END:initComponents
/**/
    private void tradeButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tradeButton1ActionPerformed
        railCheat=!railCheat;
    }//GEN-LAST:event_tradeButton1ActionPerformed
    private void optionsButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optionsButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_optionsButton1ActionPerformed
    private void exitButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButton1MouseReleased
        if(exitButton.isAble()) {
            onClose();
        }
    }//GEN-LAST:event_exitButton1MouseReleased
    private void buildButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buildButton1MouseReleased
        //<editor-fold>
        if(buildButton.isAble()) {
            closeAllOpenWindows();
            disableMenu();
            bw=new buildWindow(currPlayer,this);
            bw.setVisible(true);
        }
        //</editor-fold>
    }//GEN-LAST:event_buildButton1MouseReleased
    private void tradeButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tradeButton1MouseReleased
        if(tradeButton.isAble()) {
            closeAllOpenWindows();
            tw = new TradeWindow(currPlayer,this);
            tw.setVisible(true);
        }        
    }//GEN-LAST:event_tradeButton1MouseReleased
    private void endTurnButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_endTurnButton1MouseReleased
        if(endTurnButton.isAble()) {
            closeAllOpenWindows();
            if (getExtraordinaryBuildingPhaseNum() != getNumberOfPlayers() - 1) {
                if (!getIgnoreBuildingPhase()) {
                    //move backwards
                    if (currPlayer == getOrderOfPlayers()[0] && getNumberOfPlayers() == 4) {
                        currPlayer = getOrderOfPlayers()[3];
                        currPlayer.setTurn(true);
                    } else if (getNumberOfPlayers() == 3 && currPlayer == getOrderOfPlayers()[0]) {
                        currPlayer = getOrderOfPlayers()[2];
                        currPlayer.setTurn(true);
                    } else if (currPlayer == getOrderOfPlayers()[1]) {
                        currPlayer = getOrderOfPlayers()[0];
                        currPlayer.setTurn(true);
                    } else if (currPlayer == getOrderOfPlayers()[2]) {
                        currPlayer = getOrderOfPlayers()[1];
                        currPlayer.setTurn(true);
                    } else if (currPlayer == getOrderOfPlayers()[3]) {
                        currPlayer = getOrderOfPlayers()[2];
                        currPlayer.setTurn(true);
                    }
                    setBackgroundPlayer();
                    currPlayer.extraordinaryBuildingPhase();
                    setExtraordinaryBuildingPhaseNum(getExtraordinaryBuildingPhaseNum() + 1);
                } else {
                    nextPlayer();
                    setRolled(false);
                }
            } else {
                setExtraordinaryBuildingPhaseNum(0);
                if (currPlayer == getOrderOfPlayers()[0] && getNumberOfPlayers() == 4) {
                    currPlayer = getOrderOfPlayers()[3];
                    currPlayer.setTurn(true);
                } else if (getNumberOfPlayers() == 3 && currPlayer == getOrderOfPlayers()[0]) {
                    currPlayer = getOrderOfPlayers()[2];
                    currPlayer.setTurn(true);
                } else if (currPlayer == getOrderOfPlayers()[1]) {
                    currPlayer = getOrderOfPlayers()[0];
                    currPlayer.setTurn(true);
                } else if (currPlayer == getOrderOfPlayers()[2]) {
                    currPlayer = getOrderOfPlayers()[1];
                    currPlayer.setTurn(true);
                } else if (currPlayer == getOrderOfPlayers()[3]) {
                    currPlayer = getOrderOfPlayers()[2];
                    currPlayer.setTurn(true);
                }
                nextPlayer();
                setRolled(false);
            }

        }
    }//GEN-LAST:event_endTurnButton1MouseReleased
    private void rollButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rollButton1MouseReleased
        //<editor-fold>
        if(rollButton.isAble()) {
            if(!setupPhase){
                rolled=true;
                int r=rollDice();
                if(r!= 7) {
                    addToInfo(currPlayer.getName()+" rolled a "+r+".",false);
                    getResources(r);
                } else {
                    addToInfo(currPlayer.getName()+" rolled a 7.",false);
                    rollSeven();
                }
                rollToBuild();
                if(!movingRobber){
                    enableMenu();
                }
            }else{
                if(!rollingForFirst){
                    rollForFirst[numberOfTimesRolledForFirst]=rollDice();
                    addToInfo(currPlayer + " rolled a " + String.valueOf(rollForFirst[numberOfTimesRolledForFirst++]), false);
                    if(numberOfTimesRolledForFirst != numberOfPlayers) {
                        nextPlayer();
                        addToInfo(currPlayer + "'s turn to roll", true);
                    }else {
                        rollAlgorithm(rollForFirst);
                        rollingForFirst=true;
                    }
                }else if(reRHigh !=null){
                    if(reRollCounterHigh!=reRHigh.size()){
                         reRollsHigh[reRollCounterHigh]=rollDice();
                         addToInfo(currPlayer + " rolled a " + String.valueOf(reRollsHigh[reRollCounterHigh++]), false);
                         if(reRollCounterHigh == reRHigh.size()&&(reRLow==null||reRLow.isEmpty())) {
                                 reRoll(reRHigh, reRLow, reRollsHigh, reRollsLow);
                                 reRollsHigh=new int[reRHigh.size()];
                                 reRollsLow=new int[reRLow.size()];
                                 reRollCounterHigh=0;
                                 reRollCounterLow=0;                             
                         }else if(reRollCounterHigh!=reRHigh.size()){                   
                            forLoop:
                            for(int i=0;i<reRHigh.size();i++){
                                if(currPlayer==reRHigh.get(i)){
                                    if((i+1)<reRHigh.size()){
                                        currPlayer=reRHigh.get(i+1);
                                        setBackgroundPlayer();
                                        break forLoop;
                                    }else{
                                        currPlayer=reRHigh.get(0);
                                        setBackgroundPlayer();
                                        break forLoop;
                                    }
                                }
                            }
                            addToInfo(currPlayer + "'s turn to roll", true);
                         }else if(reRollCounterHigh == reRHigh.size() && (reRLow != null && !reRLow.isEmpty())) {                             
                                String addTI = "now ";
                                 for (int i = 0; i < reRLow.size(); i++) {
                                     if (i != 0) {
                                         addTI += ", ";
                                     }
                                     if (i == reRLow.size() - 1) {
                                         addTI += "and ";
                                     }
                                     addTI += reRLow.get(i);
                                 }
                                 addToInfo(addTI + " must reroll", false);
                                 currPlayer=reRLow.get(0);
                                 setBackgroundPlayer();   
                                 addToInfo(currPlayer + "'s turn to roll", true);
                         }
                    }else if(reRLow !=null&&reRollCounterLow!=reRLow.size()){
                         reRollsLow[reRollCounterLow]=rollDice();
                         addToInfo(currPlayer + " rolled a " + String.valueOf(reRollsLow[reRollCounterLow++]), false);
                         if(reRollCounterLow == reRLow.size()) {
                                 reRoll(reRHigh, reRLow, reRollsHigh, reRollsLow);
                                 reRollsHigh=new int[reRHigh.size()];
                                 reRollsLow=new int[reRLow.size()];
                                 reRollCounterHigh=0;
                                 reRollCounterLow=0;       
                         }else{     
                             forLoop:
                             for(int i=0;i<reRLow.size();i++){
                                if(currPlayer==reRLow.get(i)){
                                    if((i+1)<reRLow.size()){
                                        currPlayer=reRLow.get(i+1);
                                        setBackgroundPlayer();
                                        break forLoop;
                                    }else{
                                        currPlayer=reRLow.get(0);
                                        setBackgroundPlayer();
                                        break forLoop;
                                    }
                                }
                            }
                            addToInfo(currPlayer + "'s turn to roll", true);
                        }
                    }
                }
            }
        }
        //</editor-fold>
    }//GEN-LAST:event_rollButton1MouseReleased
    private void seeAllButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seeAllButton1MouseReleased
        if(seeAllButton.isAble()) {
            closeAllOpenWindows(); 
            mmw = new moreMessagesWindow(allMessages);
            mmw.setVisible(true);
        }
    }//GEN-LAST:event_seeAllButton1MouseReleased
    private void redInfoButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_redInfoButton1MouseReleased
        if(redInfoButton.isAble()) {
            closeAllOpenWindows(); 
            piw=new PlayerInfoWindow(redPlayer);
            piw.setVisible(true);
        }
    }//GEN-LAST:event_redInfoButton1MouseReleased
    private void redInfoButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_redInfoButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_redInfoButton1ActionPerformed
    private void greenInfoButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_greenInfoButton1MouseReleased
        if(greenInfoButton.isAble()) {
            closeAllOpenWindows(); 
            piw=new PlayerInfoWindow(greenPlayer);
            piw.setVisible(true);
        }
    }//GEN-LAST:event_greenInfoButton1MouseReleased
    private void greenInfoButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_greenInfoButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_greenInfoButton1ActionPerformed
    private void whiteInfoButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_whiteInfoButton1MouseReleased
        if(whiteInfoButton.isAble()) {
            closeAllOpenWindows(); 
            piw=new PlayerInfoWindow(whitePlayer);
            piw.setVisible(true);
        }
    }//GEN-LAST:event_whiteInfoButton1MouseReleased
    private void whiteInfoButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_whiteInfoButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_whiteInfoButton1ActionPerformed
    private void orangeInfoButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_orangeInfoButton1MouseReleased
        if(orangeInfoButton.isAble()) {
            closeAllOpenWindows(); 
            piw=new PlayerInfoWindow(orangePlayer);
            piw.setVisible(true);
        }
    }//GEN-LAST:event_orangeInfoButton1MouseReleased

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        if(buildingSettlement) {
            if (!fourthPlayerCityBuild) {
                if (currPlayer.getColor() == Player.Color.RED) {
                    mouseCityLabel.setIcon(redHouse);
                } else if (currPlayer.getColor() == Player.Color.GREEN) {
                    mouseCityLabel.setIcon(greenHouse);
                } else if (currPlayer.getColor() == Player.Color.WHITE) {
                    mouseCityLabel.setIcon(whiteHouse);
                } else if (currPlayer.getColor() == Player.Color.ORANGE) {
                    mouseCityLabel.setIcon(orangeHouse);
                }
            } else {
                if (player[3].getColor() == Player.Color.RED) {
                    mouseCityLabel.setIcon(redHouse);
                } else if (player[3].getColor() == Player.Color.GREEN) {
                    mouseCityLabel.setIcon(greenHouse);
                } else if (player[3].getColor() == Player.Color.WHITE) {
                    mouseCityLabel.setIcon(whiteHouse);
                } else if (player[3].getColor() == Player.Color.ORANGE) {
                    mouseCityLabel.setIcon(orangeHouse);
                }
            }
            PointerInfo a = MouseInfo.getPointerInfo();
            Point b = a.getLocation();
            int x = (int) b.getX();
            int y = (int) b.getY();
            mouseCityLabel.setLocation(x-34,y-50);
        }
    }//GEN-LAST:event_formMouseMoved
    private void spokaneButtonMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_spokaneButtonMouseMoved
        if(buildingSettlement) {
            if (!fourthPlayerCityBuild) {
                if (currPlayer.getColor() == Player.Color.RED) {
                    mouseCityLabel.setIcon(redHouse);
                } else if (currPlayer.getColor() == Player.Color.GREEN) {
                    mouseCityLabel.setIcon(greenHouse);
                } else if (currPlayer.getColor() == Player.Color.WHITE) {
                    mouseCityLabel.setIcon(whiteHouse);
                } else if (currPlayer.getColor() == Player.Color.ORANGE) {
                    mouseCityLabel.setIcon(orangeHouse);
                }
            } else {
                if (player[3].getColor() == Player.Color.RED) {
                    mouseCityLabel.setIcon(redHouse);
                } else if (player[3].getColor() == Player.Color.GREEN) {
                    mouseCityLabel.setIcon(greenHouse);
                } else if (player[3].getColor() == Player.Color.WHITE) {
                    mouseCityLabel.setIcon(whiteHouse);
                } else if (player[3].getColor() == Player.Color.ORANGE) {
                    mouseCityLabel.setIcon(orangeHouse);
                }
            }        
            PointerInfo a = MouseInfo.getPointerInfo();
            Point b = a.getLocation();
            int x = (int) b.getX();
            int y = (int) b.getY();
            mouseCityLabel.setLocation(x-34,y-50);
        }
    }//GEN-LAST:event_spokaneButtonMouseMoved
    private void spokaneButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_spokaneButtonMouseEntered

    }//GEN-LAST:event_spokaneButtonMouseEntered
    private void spokaneButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_spokaneButtonMouseExited

    }//GEN-LAST:event_spokaneButtonMouseExited
    private void buildCityButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buildCityButton1MouseReleased
        if(buildCityButton.isAble()) {
            buildSettlement();
            closeAllOpenWindows();
            if(!fourthPlayerCityBuild){
                if (currPlayer.getColor() == Player.Color.RED) {
                    mouseCityLabel.setIcon(redHouse);
                } else if (currPlayer.getColor() == Player.Color.GREEN) {
                    mouseCityLabel.setIcon(greenHouse);
                } else if (currPlayer.getColor() == Player.Color.WHITE) {
                    mouseCityLabel.setIcon(whiteHouse);
                } else if (currPlayer.getColor() == Player.Color.ORANGE) {
                    mouseCityLabel.setIcon(orangeHouse);
                }
            }else{
                if(player[3].getColor()==Player.Color.RED){                    
                    mouseCityLabel.setIcon(redHouse);
                } else if (player[3].getColor() == Player.Color.GREEN) {
                    mouseCityLabel.setIcon(greenHouse);
                } else if (player[3].getColor() == Player.Color.WHITE) {
                    mouseCityLabel.setIcon(whiteHouse);
                } else if (player[3].getColor() == Player.Color.ORANGE) {
                    mouseCityLabel.setIcon(orangeHouse);
                }
            }
            PointerInfo a = MouseInfo.getPointerInfo();
            Point b = a.getLocation();
            int x = (int) b.getX();
            int y = (int) b.getY();
            mouseCityLabel.setLocation(x - 34, y - 50);
            buildCityButton.setVisible(false);
            trashBinButton.setVisible(true);
        }
    }//GEN-LAST:event_buildCityButton1MouseReleased
    private void trashBinButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trashBinButtonActionPerformed
        trashBinButton.setVisible(false);
        buildingSettlement=false;
        buildCityButton.setVisible(true);
        playSound("trash");
    }//GEN-LAST:event_trashBinButtonActionPerformed
    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased

    }//GEN-LAST:event_formMouseReleased
    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        closeAllOpenWindows();
    }//GEN-LAST:event_formMouseClicked
    private void utilitiesButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_utilitiesButton1MouseReleased
        if(utilitiesButton.isAble()) {
            closeAllOpenWindows();
            uw=new UtilitiesWindow(citySpot);
            uw.setVisible(true);
        }
    }//GEN-LAST:event_utilitiesButton1MouseReleased
    private void utilitiesButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_utilitiesButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_utilitiesButton1ActionPerformed

    private void cancelButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelButton1MouseReleased
        if (cancelButton.isAble()) {
            if (extraordinaryBuildingPhaseNum == 0) {
                if (buildingRail) {
                    currPlayer.plusOre();
                    currPlayer.plusLumber();
                    buildTwoRails = false;
                    for (int i = 0; i < rail.length; i++) {
                        rail[i].setPlayingDevelopmentCard(false);
                    }
                    doneBuildingRail();
                    gameFrame.addToInfo(currPlayer.getName() + ": Take your turn", false);
                }
                if (buildingSettler) {
                    for (int i = 0; i < intersection.length; i++) {
                        intersection[i].setPlayingDevelopmentCard(false);
                    }
                    buildingSettler(false, true);
                    gameFrame.addToInfo(currPlayer.getName() + ": Take your turn", false);
                }
                if (buildingTrain) {
                    buildingTrain(false, true);
                    gameFrame.addToInfo(currPlayer.getName() + ": Take your turn", false);
                }
            }else{
                if (buildingRail) {
                    currPlayer.plusOre();
                    currPlayer.plusLumber();
                    buildTwoRails = false;
                    for (int i = 0; i < rail.length; i++) {
                        rail[i].setPlayingDevelopmentCard(false);
                    }
                    doneBuildingRail();
                }
                if (buildingSettler) {
                    for (int i = 0; i < intersection.length; i++) {
                        intersection[i].setPlayingDevelopmentCard(false);
                    }
                    buildingSettler(false, true);
                }
                if (buildingTrain) {
                    buildingTrain(false, true);
                }
            }
        }
    }//GEN-LAST:event_cancelButton1MouseReleased
    private void optionsButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_optionsButton1MouseReleased
        if (optionsButton.isAble()) {
            closeAllOpenWindows();
            igom = new InGameOptionsMenu(allMessages, this, volumeInt, doubleGold, cumulativeRolls,
                    timeAtBeggining, player, numberOfPlayers, currPlayer);
            igom.setVisible(true);
        }
    }//GEN-LAST:event_optionsButton1MouseReleased
    private void displayCityNamesToggleButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_displayCityNamesToggleButtonMousePressed
        if(currentlyDisplayingCityNames) {
            gameFrame.playSound("click");
            displayCityNamesToggleButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            displayCityNamesToggleButton.setLocation(displayCityNamesToggleButton.getX() + 1, displayCityNamesToggleButton.getY());
            for (int i = 0; i < citySpot.length; i++) {
                citySpot[i].notDisplayNameLabel();
            }
            refreshGameFrameLabel.setText("kdjfkd");
            refreshGameFrameLabel.setText("");
            currentlyDisplayingCityNames=false;
        } else {
            gameFrame.playSound("click");
            displayCityNamesToggleButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            displayCityNamesToggleButton.setLocation(displayCityNamesToggleButton.getX() - 1, displayCityNamesToggleButton.getY());
            for (int i = 0; i < citySpot.length; i++) {
                citySpot[i].displayNameLabel(labelPanel);
            }
            refreshGameFrameLabel.setText("kdjfkd");
            refreshGameFrameLabel.setText("");
            currentlyDisplayingCityNames=true;
        }
    }//GEN-LAST:event_displayCityNamesToggleButtonMousePressed
    private void developmentCardButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_developmentCardButton1MouseReleased
        if(developmentCardButton.isAble()){
            closeAllOpenWindows();
            if (dpcw != null && dpcw.isVisible()) {
                dpcw.closeWindows();
                dpcw.setVisible(false);
            }
            dpcw = new developmentCardWindow(currPlayer.getDPCards(),this,bw,null);
            dpcw.setVisible(true);
            disableMenu();
        }
    }//GEN-LAST:event_developmentCardButton1MouseReleased
    private void buildingCostButton1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buildingCostButton1MouseMoved
            PointerInfo a = MouseInfo.getPointerInfo();
            Point b = a.getLocation();
            int x = (int) b.getX();
            int y = (int) b.getY();
            mouseBuildingCostLabel.setLocation(x-20,y-430);
    }//GEN-LAST:event_buildingCostButton1MouseMoved
    private void buildingCostButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buildingCostButton1MouseReleased
        if(buildingCostsButton.isAble()) {
            if (buildButton.isEnabled()) {
                closeAllOpenWindows();
                disableMenu();
                bw = new buildWindow(currPlayer, this);
                bw.setVisible(true);
            }
        }
    }//GEN-LAST:event_buildingCostButton1MouseReleased
    private void mouseBuildingCostLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mouseBuildingCostLabelMouseExited
        mouseBuildingCostLabel.setLocation(-400,0);
    }//GEN-LAST:event_mouseBuildingCostLabelMouseExited
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new gameFrame().setVisible(true);
            }
        });
    }
    //<editor-fold>
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton albanyButton;
    private javax.swing.JButton amarilloButton;
    private javax.swing.JButton atlantaButton;
    private javax.swing.JLabel background;
    private javax.swing.JLabel backgroundSideMenu;
    private javax.swing.JButton billingsButton;
    private javax.swing.JButton bismarkButton;
    private javax.swing.JButton boiseButton;
    private javax.swing.JButton bostonButton;
    private javax.swing.JButton buildButton1;
    private javax.swing.JButton buildCityButton1;
    private javax.swing.JButton buildingCostButton1;
    private javax.swing.JPanel buttonMenuPanel;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton cancelButton1;
    private javax.swing.JButton casperButton;
    private javax.swing.JLabel cattleCardLabel;
    private javax.swing.JButton chicagoButton;
    private javax.swing.JLabel coalCardLabel;
    private javax.swing.JButton columbiaButton;
    private javax.swing.JButton columbusButton;
    private javax.swing.JLabel controlsLabel;
    private javax.swing.JButton dallasButton;
    private javax.swing.JButton denverButton;
    private javax.swing.JButton developmentCardButton1;
    private javax.swing.JLabel diceOneLabel;
    private javax.swing.JPanel dicePanel;
    private javax.swing.JLabel diceTwoLabel;
    private javax.swing.JToggleButton displayCityNamesToggleButton;
    private javax.swing.JButton elPasoButton;
    private javax.swing.JButton endTurnButton1;
    private javax.swing.JLabel errorGreenLabel;
    private javax.swing.JLabel errorOrangeLabel;
    private javax.swing.JLabel errorRedLabel;
    private javax.swing.JLabel errorWhiteLabel;
    private javax.swing.JButton exitButton1;
    private javax.swing.JButton flagstaffButton;
    private javax.swing.JPanel gamePanel;
    private javax.swing.JLabel goldCoinLabel;
    private javax.swing.JPanel goodsSidePanel;
    private javax.swing.JButton greenInfoButton1;
    private javax.swing.JLabel greenTextTrackCircleLabel;
    private javax.swing.JLabel greenTrackCircleLabel;
    private javax.swing.JButton helenaButton;
    private javax.swing.JButton hexButton1;
    private javax.swing.JButton hexButton10;
    private javax.swing.JButton hexButton11;
    private javax.swing.JButton hexButton12;
    private javax.swing.JButton hexButton13;
    private javax.swing.JButton hexButton14;
    private javax.swing.JButton hexButton15;
    private javax.swing.JButton hexButton16;
    private javax.swing.JButton hexButton17;
    private javax.swing.JButton hexButton18;
    private javax.swing.JButton hexButton19;
    private javax.swing.JButton hexButton2;
    private javax.swing.JButton hexButton20;
    private javax.swing.JButton hexButton21;
    private javax.swing.JButton hexButton22;
    private javax.swing.JButton hexButton23;
    private javax.swing.JButton hexButton24;
    private javax.swing.JButton hexButton25;
    private javax.swing.JButton hexButton26;
    private javax.swing.JButton hexButton27;
    private javax.swing.JButton hexButton28;
    private javax.swing.JButton hexButton29;
    private javax.swing.JButton hexButton3;
    private javax.swing.JButton hexButton30;
    private javax.swing.JButton hexButton31;
    private javax.swing.JButton hexButton32;
    private javax.swing.JButton hexButton33;
    private javax.swing.JButton hexButton34;
    private javax.swing.JButton hexButton35;
    private javax.swing.JButton hexButton36;
    private javax.swing.JButton hexButton37;
    private javax.swing.JButton hexButton38;
    private javax.swing.JButton hexButton39;
    private javax.swing.JButton hexButton4;
    private javax.swing.JButton hexButton40;
    private javax.swing.JButton hexButton41;
    private javax.swing.JButton hexButton42;
    private javax.swing.JButton hexButton43;
    private javax.swing.JButton hexButton44;
    private javax.swing.JButton hexButton45;
    private javax.swing.JButton hexButton46;
    private javax.swing.JButton hexButton47;
    private javax.swing.JButton hexButton48;
    private javax.swing.JButton hexButton49;
    private javax.swing.JButton hexButton5;
    private javax.swing.JButton hexButton50;
    private javax.swing.JButton hexButton51;
    private javax.swing.JButton hexButton52;
    private javax.swing.JButton hexButton53;
    private javax.swing.JButton hexButton54;
    private javax.swing.JButton hexButton55;
    private javax.swing.JButton hexButton56;
    private javax.swing.JButton hexButton57;
    private javax.swing.JButton hexButton58;
    private javax.swing.JButton hexButton59;
    private javax.swing.JButton hexButton6;
    private javax.swing.JButton hexButton60;
    private javax.swing.JButton hexButton61;
    private javax.swing.JButton hexButton62;
    private javax.swing.JButton hexButton63;
    private javax.swing.JButton hexButton64;
    private javax.swing.JButton hexButton65;
    private javax.swing.JButton hexButton66;
    private javax.swing.JButton hexButton67;
    private javax.swing.JButton hexButton68;
    private javax.swing.JButton hexButton69;
    private javax.swing.JButton hexButton7;
    private javax.swing.JButton hexButton70;
    private javax.swing.JButton hexButton71;
    private javax.swing.JButton hexButton8;
    private javax.swing.JButton hexButton9;
    private javax.swing.JButton houstonButton;
    private javax.swing.JButton idahoFallsButton;
    private javax.swing.JButton indianapolisButton;
    private javax.swing.JPanel infoDialog;
    private static javax.swing.JLabel infoDialogFive;
    private static javax.swing.JLabel infoDialogFour;
    private static javax.swing.JLabel infoDialogOne;
    private static javax.swing.JLabel infoDialogThree;
    private static javax.swing.JLabel infoDialogTwo;
    private javax.swing.JLabel intersection0;
    private javax.swing.JLabel intersection1;
    private javax.swing.JLabel intersection10;
    private javax.swing.JLabel intersection100;
    private javax.swing.JLabel intersection101;
    private javax.swing.JLabel intersection102;
    private javax.swing.JLabel intersection103;
    private javax.swing.JLabel intersection104;
    private javax.swing.JLabel intersection105;
    private javax.swing.JLabel intersection106;
    private javax.swing.JLabel intersection107;
    private javax.swing.JLabel intersection108;
    private javax.swing.JLabel intersection109;
    private javax.swing.JLabel intersection11;
    private javax.swing.JLabel intersection110;
    private javax.swing.JLabel intersection111;
    private javax.swing.JLabel intersection112;
    private javax.swing.JLabel intersection113;
    private javax.swing.JLabel intersection114;
    private javax.swing.JLabel intersection115;
    private javax.swing.JLabel intersection116;
    private javax.swing.JLabel intersection117;
    private javax.swing.JLabel intersection118;
    private javax.swing.JLabel intersection119;
    private javax.swing.JLabel intersection12;
    private javax.swing.JLabel intersection120;
    private javax.swing.JLabel intersection121;
    private javax.swing.JLabel intersection122;
    private javax.swing.JLabel intersection123;
    private javax.swing.JLabel intersection124;
    private javax.swing.JLabel intersection125;
    private javax.swing.JLabel intersection126;
    private javax.swing.JLabel intersection127;
    private javax.swing.JLabel intersection128;
    private javax.swing.JLabel intersection129;
    private javax.swing.JLabel intersection13;
    private javax.swing.JLabel intersection130;
    private javax.swing.JLabel intersection131;
    private javax.swing.JLabel intersection132;
    private javax.swing.JLabel intersection133;
    private javax.swing.JLabel intersection134;
    private javax.swing.JLabel intersection135;
    private javax.swing.JLabel intersection136;
    private javax.swing.JLabel intersection137;
    private javax.swing.JLabel intersection138;
    private javax.swing.JLabel intersection139;
    private javax.swing.JLabel intersection14;
    private javax.swing.JLabel intersection140;
    private javax.swing.JLabel intersection141;
    private javax.swing.JLabel intersection142;
    private javax.swing.JLabel intersection143;
    private javax.swing.JLabel intersection144;
    private javax.swing.JLabel intersection145;
    private javax.swing.JLabel intersection146;
    private javax.swing.JLabel intersection147;
    private javax.swing.JLabel intersection148;
    private javax.swing.JLabel intersection15;
    private javax.swing.JLabel intersection16;
    private javax.swing.JLabel intersection17;
    private javax.swing.JLabel intersection18;
    private javax.swing.JLabel intersection19;
    private javax.swing.JLabel intersection2;
    private javax.swing.JLabel intersection20;
    private javax.swing.JLabel intersection21;
    private javax.swing.JLabel intersection22;
    private javax.swing.JLabel intersection23;
    private javax.swing.JLabel intersection24;
    private javax.swing.JLabel intersection25;
    private javax.swing.JLabel intersection26;
    private javax.swing.JLabel intersection27;
    private javax.swing.JLabel intersection28;
    private javax.swing.JLabel intersection29;
    private javax.swing.JLabel intersection3;
    private javax.swing.JLabel intersection30;
    private javax.swing.JLabel intersection31;
    private javax.swing.JLabel intersection32;
    private javax.swing.JLabel intersection33;
    private javax.swing.JLabel intersection34;
    private javax.swing.JLabel intersection35;
    private javax.swing.JLabel intersection36;
    private javax.swing.JLabel intersection37;
    private javax.swing.JLabel intersection38;
    private javax.swing.JLabel intersection39;
    private javax.swing.JLabel intersection4;
    private javax.swing.JLabel intersection40;
    private javax.swing.JLabel intersection41;
    private javax.swing.JLabel intersection42;
    private javax.swing.JLabel intersection43;
    private javax.swing.JLabel intersection44;
    private javax.swing.JLabel intersection45;
    private javax.swing.JLabel intersection46;
    private javax.swing.JLabel intersection47;
    private javax.swing.JLabel intersection48;
    private javax.swing.JLabel intersection49;
    private javax.swing.JLabel intersection5;
    private javax.swing.JLabel intersection50;
    private javax.swing.JLabel intersection51;
    private javax.swing.JLabel intersection52;
    private javax.swing.JLabel intersection53;
    private javax.swing.JLabel intersection54;
    private javax.swing.JLabel intersection55;
    private javax.swing.JLabel intersection56;
    private javax.swing.JLabel intersection57;
    private javax.swing.JLabel intersection58;
    private javax.swing.JLabel intersection59;
    private javax.swing.JLabel intersection6;
    private javax.swing.JLabel intersection60;
    private javax.swing.JLabel intersection61;
    private javax.swing.JLabel intersection62;
    private javax.swing.JLabel intersection63;
    private javax.swing.JLabel intersection64;
    private javax.swing.JLabel intersection65;
    private javax.swing.JLabel intersection66;
    private javax.swing.JLabel intersection67;
    private javax.swing.JLabel intersection68;
    private javax.swing.JLabel intersection69;
    private javax.swing.JLabel intersection7;
    private javax.swing.JLabel intersection70;
    private javax.swing.JLabel intersection71;
    private javax.swing.JLabel intersection72;
    private javax.swing.JLabel intersection73;
    private javax.swing.JLabel intersection74;
    private javax.swing.JLabel intersection75;
    private javax.swing.JLabel intersection76;
    private javax.swing.JLabel intersection77;
    private javax.swing.JLabel intersection78;
    private javax.swing.JLabel intersection79;
    private javax.swing.JLabel intersection8;
    private javax.swing.JLabel intersection80;
    private javax.swing.JLabel intersection81;
    private javax.swing.JLabel intersection82;
    private javax.swing.JLabel intersection83;
    private javax.swing.JLabel intersection84;
    private javax.swing.JLabel intersection85;
    private javax.swing.JLabel intersection86;
    private javax.swing.JLabel intersection87;
    private javax.swing.JLabel intersection88;
    private javax.swing.JLabel intersection89;
    private javax.swing.JLabel intersection9;
    private javax.swing.JLabel intersection90;
    private javax.swing.JLabel intersection91;
    private javax.swing.JLabel intersection92;
    private javax.swing.JLabel intersection93;
    private javax.swing.JLabel intersection94;
    private javax.swing.JLabel intersection95;
    private javax.swing.JLabel intersection96;
    private javax.swing.JLabel intersection97;
    private javax.swing.JLabel intersection98;
    private javax.swing.JLabel intersection99;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JButton jacksonButton;
    private javax.swing.JButton jacksonvilleButton;
    private javax.swing.JPanel labelPanel;
    private javax.swing.JButton lasVegasButton;
    private javax.swing.JButton lexingtonButton;
    private javax.swing.JButton littleRockButton;
    private javax.swing.JButton losAngelesButton;
    private javax.swing.JLabel lumberCardLabel;
    private javax.swing.JPanel menuPanel;
    private javax.swing.JButton minneapolisButton;
    private javax.swing.JLabel mouseBuildingCostLabel;
    private javax.swing.JLabel mouseCityLabel;
    private javax.swing.JButton nashvilleButton;
    private javax.swing.JButton newOrleansButton;
    private javax.swing.JButton newYorkButton;
    private javax.swing.JLabel numberOfCattleLabel;
    private javax.swing.JLabel numberOfCoalLabel;
    private javax.swing.JLabel numberOfGoldLabel;
    private javax.swing.JLabel numberOfLumberLabel;
    private javax.swing.JLabel numberOfOreLabel;
    private javax.swing.JLabel numberOfWheatLabel;
    private javax.swing.JButton omahaButton;
    private javax.swing.JButton optionsButton1;
    private javax.swing.JButton orangeInfoButton1;
    private javax.swing.JLabel orangeTextTrackCircleLabel;
    private javax.swing.JLabel orangeTrackCircleLabel;
    private javax.swing.JLabel oreCardLabel;
    private javax.swing.JButton pensacolaButton;
    private javax.swing.JButton portlandButton;
    private javax.swing.JPanel questionChitPanel;
    private javax.swing.JButton railButton0;
    private javax.swing.JButton railButton1;
    private javax.swing.JButton railButton10;
    private javax.swing.JButton railButton100;
    private javax.swing.JButton railButton101;
    private javax.swing.JButton railButton102;
    private javax.swing.JButton railButton103;
    private javax.swing.JButton railButton104;
    private javax.swing.JButton railButton105;
    private javax.swing.JButton railButton106;
    private javax.swing.JButton railButton107;
    private javax.swing.JButton railButton108;
    private javax.swing.JButton railButton109;
    private javax.swing.JButton railButton11;
    private javax.swing.JButton railButton110;
    private javax.swing.JButton railButton111;
    private javax.swing.JButton railButton112;
    private javax.swing.JButton railButton113;
    private javax.swing.JButton railButton114;
    private javax.swing.JButton railButton115;
    private javax.swing.JButton railButton116;
    private javax.swing.JButton railButton117;
    private javax.swing.JButton railButton118;
    private javax.swing.JButton railButton119;
    private javax.swing.JButton railButton12;
    private javax.swing.JButton railButton120;
    private javax.swing.JButton railButton121;
    private javax.swing.JButton railButton122;
    private javax.swing.JButton railButton123;
    private javax.swing.JButton railButton124;
    private javax.swing.JButton railButton125;
    private javax.swing.JButton railButton126;
    private javax.swing.JButton railButton127;
    private javax.swing.JButton railButton128;
    private javax.swing.JButton railButton129;
    private javax.swing.JButton railButton13;
    private javax.swing.JButton railButton130;
    private javax.swing.JButton railButton131;
    private javax.swing.JButton railButton132;
    private javax.swing.JButton railButton133;
    private javax.swing.JButton railButton134;
    private javax.swing.JButton railButton135;
    private javax.swing.JButton railButton136;
    private javax.swing.JButton railButton137;
    private javax.swing.JButton railButton138;
    private javax.swing.JButton railButton139;
    private javax.swing.JButton railButton14;
    private javax.swing.JButton railButton140;
    private javax.swing.JButton railButton141;
    private javax.swing.JButton railButton142;
    private javax.swing.JButton railButton143;
    private javax.swing.JButton railButton144;
    private javax.swing.JButton railButton145;
    private javax.swing.JButton railButton146;
    private javax.swing.JButton railButton147;
    private javax.swing.JButton railButton148;
    private javax.swing.JButton railButton149;
    private javax.swing.JButton railButton15;
    private javax.swing.JButton railButton150;
    private javax.swing.JButton railButton151;
    private javax.swing.JButton railButton152;
    private javax.swing.JButton railButton153;
    private javax.swing.JButton railButton154;
    private javax.swing.JButton railButton155;
    private javax.swing.JButton railButton156;
    private javax.swing.JButton railButton157;
    private javax.swing.JButton railButton158;
    private javax.swing.JButton railButton159;
    private javax.swing.JButton railButton16;
    private javax.swing.JButton railButton160;
    private javax.swing.JButton railButton161;
    private javax.swing.JButton railButton162;
    private javax.swing.JButton railButton163;
    private javax.swing.JButton railButton164;
    private javax.swing.JButton railButton165;
    private javax.swing.JButton railButton166;
    private javax.swing.JButton railButton167;
    private javax.swing.JButton railButton168;
    private javax.swing.JButton railButton169;
    private javax.swing.JButton railButton17;
    private javax.swing.JButton railButton170;
    private javax.swing.JButton railButton171;
    private javax.swing.JButton railButton172;
    private javax.swing.JButton railButton173;
    private javax.swing.JButton railButton174;
    private javax.swing.JButton railButton175;
    private javax.swing.JButton railButton176;
    private javax.swing.JButton railButton177;
    private javax.swing.JButton railButton178;
    private javax.swing.JButton railButton179;
    private javax.swing.JButton railButton18;
    private javax.swing.JButton railButton180;
    private javax.swing.JButton railButton181;
    private javax.swing.JButton railButton182;
    private javax.swing.JButton railButton183;
    private javax.swing.JButton railButton184;
    private javax.swing.JButton railButton185;
    private javax.swing.JButton railButton186;
    private javax.swing.JButton railButton187;
    private javax.swing.JButton railButton188;
    private javax.swing.JButton railButton189;
    private javax.swing.JButton railButton19;
    private javax.swing.JButton railButton190;
    private javax.swing.JButton railButton191;
    private javax.swing.JButton railButton192;
    private javax.swing.JButton railButton193;
    private javax.swing.JButton railButton194;
    private javax.swing.JButton railButton195;
    private javax.swing.JButton railButton196;
    private javax.swing.JButton railButton197;
    private javax.swing.JButton railButton198;
    private javax.swing.JButton railButton199;
    private javax.swing.JButton railButton2;
    private javax.swing.JButton railButton20;
    private javax.swing.JButton railButton200;
    private javax.swing.JButton railButton201;
    private javax.swing.JButton railButton202;
    private javax.swing.JButton railButton203;
    private javax.swing.JButton railButton204;
    private javax.swing.JButton railButton205;
    private javax.swing.JButton railButton206;
    private javax.swing.JButton railButton207;
    private javax.swing.JButton railButton208;
    private javax.swing.JButton railButton209;
    private javax.swing.JButton railButton21;
    private javax.swing.JButton railButton210;
    private javax.swing.JButton railButton211;
    private javax.swing.JButton railButton212;
    private javax.swing.JButton railButton213;
    private javax.swing.JButton railButton214;
    private javax.swing.JButton railButton215;
    private javax.swing.JButton railButton22;
    private javax.swing.JButton railButton23;
    private javax.swing.JButton railButton24;
    private javax.swing.JButton railButton25;
    private javax.swing.JButton railButton26;
    private javax.swing.JButton railButton27;
    private javax.swing.JButton railButton28;
    private javax.swing.JButton railButton29;
    private javax.swing.JButton railButton3;
    private javax.swing.JButton railButton30;
    private javax.swing.JButton railButton31;
    private javax.swing.JButton railButton32;
    private javax.swing.JButton railButton33;
    private javax.swing.JButton railButton34;
    private javax.swing.JButton railButton35;
    private javax.swing.JButton railButton36;
    private javax.swing.JButton railButton37;
    private javax.swing.JButton railButton38;
    private javax.swing.JButton railButton39;
    private javax.swing.JButton railButton4;
    private javax.swing.JButton railButton40;
    private javax.swing.JButton railButton41;
    private javax.swing.JButton railButton42;
    private javax.swing.JButton railButton43;
    private javax.swing.JButton railButton44;
    private javax.swing.JButton railButton45;
    private javax.swing.JButton railButton46;
    private javax.swing.JButton railButton47;
    private javax.swing.JButton railButton48;
    private javax.swing.JButton railButton49;
    private javax.swing.JButton railButton5;
    private javax.swing.JButton railButton50;
    private javax.swing.JButton railButton51;
    private javax.swing.JButton railButton52;
    private javax.swing.JButton railButton53;
    private javax.swing.JButton railButton54;
    private javax.swing.JButton railButton55;
    private javax.swing.JButton railButton56;
    private javax.swing.JButton railButton57;
    private javax.swing.JButton railButton58;
    private javax.swing.JButton railButton59;
    private javax.swing.JButton railButton6;
    private javax.swing.JButton railButton60;
    private javax.swing.JButton railButton61;
    private javax.swing.JButton railButton62;
    private javax.swing.JButton railButton63;
    private javax.swing.JButton railButton64;
    private javax.swing.JButton railButton65;
    private javax.swing.JButton railButton66;
    private javax.swing.JButton railButton67;
    private javax.swing.JButton railButton68;
    private javax.swing.JButton railButton69;
    private javax.swing.JButton railButton7;
    private javax.swing.JButton railButton70;
    private javax.swing.JButton railButton71;
    private javax.swing.JButton railButton72;
    private javax.swing.JButton railButton73;
    private javax.swing.JButton railButton74;
    private javax.swing.JButton railButton75;
    private javax.swing.JButton railButton76;
    private javax.swing.JButton railButton77;
    private javax.swing.JButton railButton78;
    private javax.swing.JButton railButton79;
    private javax.swing.JButton railButton8;
    private javax.swing.JButton railButton80;
    private javax.swing.JButton railButton81;
    private javax.swing.JButton railButton82;
    private javax.swing.JButton railButton83;
    private javax.swing.JButton railButton84;
    private javax.swing.JButton railButton85;
    private javax.swing.JButton railButton86;
    private javax.swing.JButton railButton87;
    private javax.swing.JButton railButton88;
    private javax.swing.JButton railButton89;
    private javax.swing.JButton railButton9;
    private javax.swing.JButton railButton90;
    private javax.swing.JButton railButton91;
    private javax.swing.JButton railButton92;
    private javax.swing.JButton railButton93;
    private javax.swing.JButton railButton94;
    private javax.swing.JButton railButton95;
    private javax.swing.JButton railButton96;
    private javax.swing.JButton railButton97;
    private javax.swing.JButton railButton98;
    private javax.swing.JButton railButton99;
    private javax.swing.JButton raleighButton;
    private javax.swing.JButton rapidCityButton;
    private javax.swing.JButton redInfoButton1;
    private javax.swing.JLabel redTextTrackCircleLabel;
    private javax.swing.JLabel redTrackCircleLabel;
    private javax.swing.JLabel refreshGameFrameLabel;
    private javax.swing.JButton renoButton;
    private javax.swing.JLabel robberLabel0;
    private javax.swing.JLabel robberLabel1;
    private javax.swing.JLabel robberLabel10;
    private javax.swing.JLabel robberLabel11;
    private javax.swing.JLabel robberLabel12;
    private javax.swing.JLabel robberLabel13;
    private javax.swing.JLabel robberLabel14;
    private javax.swing.JLabel robberLabel15;
    private javax.swing.JLabel robberLabel16;
    private javax.swing.JLabel robberLabel17;
    private javax.swing.JLabel robberLabel18;
    private javax.swing.JLabel robberLabel19;
    private javax.swing.JLabel robberLabel2;
    private javax.swing.JLabel robberLabel20;
    private javax.swing.JLabel robberLabel21;
    private javax.swing.JLabel robberLabel22;
    private javax.swing.JLabel robberLabel23;
    private javax.swing.JLabel robberLabel24;
    private javax.swing.JLabel robberLabel25;
    private javax.swing.JLabel robberLabel26;
    private javax.swing.JLabel robberLabel27;
    private javax.swing.JLabel robberLabel28;
    private javax.swing.JLabel robberLabel29;
    private javax.swing.JLabel robberLabel3;
    private javax.swing.JLabel robberLabel30;
    private javax.swing.JLabel robberLabel31;
    private javax.swing.JLabel robberLabel32;
    private javax.swing.JLabel robberLabel33;
    private javax.swing.JLabel robberLabel34;
    private javax.swing.JLabel robberLabel35;
    private javax.swing.JLabel robberLabel36;
    private javax.swing.JLabel robberLabel37;
    private javax.swing.JLabel robberLabel38;
    private javax.swing.JLabel robberLabel39;
    private javax.swing.JLabel robberLabel4;
    private javax.swing.JLabel robberLabel40;
    private javax.swing.JLabel robberLabel41;
    private javax.swing.JLabel robberLabel42;
    private javax.swing.JLabel robberLabel43;
    private javax.swing.JLabel robberLabel44;
    private javax.swing.JLabel robberLabel45;
    private javax.swing.JLabel robberLabel46;
    private javax.swing.JLabel robberLabel47;
    private javax.swing.JLabel robberLabel48;
    private javax.swing.JLabel robberLabel49;
    private javax.swing.JLabel robberLabel5;
    private javax.swing.JLabel robberLabel50;
    private javax.swing.JLabel robberLabel51;
    private javax.swing.JLabel robberLabel52;
    private javax.swing.JLabel robberLabel53;
    private javax.swing.JLabel robberLabel54;
    private javax.swing.JLabel robberLabel55;
    private javax.swing.JLabel robberLabel56;
    private javax.swing.JLabel robberLabel57;
    private javax.swing.JLabel robberLabel58;
    private javax.swing.JLabel robberLabel59;
    private javax.swing.JLabel robberLabel6;
    private javax.swing.JLabel robberLabel60;
    private javax.swing.JLabel robberLabel61;
    private javax.swing.JLabel robberLabel62;
    private javax.swing.JLabel robberLabel63;
    private javax.swing.JLabel robberLabel64;
    private javax.swing.JLabel robberLabel65;
    private javax.swing.JLabel robberLabel66;
    private javax.swing.JLabel robberLabel67;
    private javax.swing.JLabel robberLabel68;
    private javax.swing.JLabel robberLabel69;
    private javax.swing.JLabel robberLabel7;
    private javax.swing.JLabel robberLabel8;
    private javax.swing.JLabel robberLabel9;
    private javax.swing.JPanel robberSpotPanel;
    private javax.swing.JLabel robberStartLabel;
    private javax.swing.JButton rollButton1;
    private javax.swing.JButton saltLakeCityButton;
    private javax.swing.JButton sanAntonioButton;
    private javax.swing.JButton sanFranciscoButton;
    private javax.swing.JButton santaFeButton;
    private javax.swing.JButton seattleButton;
    private javax.swing.JButton seeAllButton1;
    private javax.swing.JButton shreveportButton;
    private javax.swing.JButton spokaneButton;
    private javax.swing.JButton stLouisButton;
    private javax.swing.JButton tradeButton1;
    private javax.swing.JPanel trainSettlerPanel;
    private javax.swing.JButton trashBinButton;
    private javax.swing.JButton utilitiesButton1;
    private javax.swing.JButton washingtonDCButton;
    private javax.swing.JLabel wheatCardLabel;
    private javax.swing.JButton whiteInfoButton1;
    private javax.swing.JLabel whiteTextTrackCircleLabel;
    private javax.swing.JLabel whiteTrackCircleLabel;
    private javax.swing.JButton wichitaButton;
    private javax.swing.JLabel xLabel;
    private javax.swing.JLabel xLabel1;
    private javax.swing.JLabel xLabel2;
    private javax.swing.JLabel xLabel3;
    private javax.swing.JLabel xLabel4;
    private javax.swing.JLabel xLabel5;
    // End of variables declaration//GEN-END:variables
//</editor-fold>
}
package settlerstrailstorails;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 *
 * @author Avery Thorn
 */
class Player {
    enum Color {WHITE,ORANGE,RED,GREEN;
    
    @Override
    public String toString(){
        switch(this){
            case WHITE: return "White";
            case ORANGE: return "Orange";
            case RED: return "Red";
            default: return "Green";
        }
    }};
    private Player.Color color;
    private int lumber,ore,wheat,cattle,coal,gold,colorYConstant;
    private double lumberProb,oreProb,wheatProb,
            cattleProb,coalProb,anyProb,noneProb;
    private boolean[] lumberProbNums,oreProbNums,wheatProbNums,cattleProbNums,
            coalProbNums,anyProbNums;
    private Good[] goods;
    private ArrayList<JLabel> cities=new ArrayList<JLabel>();
    private ArrayList<Intersection> settlerIntersections;
    private ArrayList<Rail> trainRails;
    boolean human, maxNumberOfCitiesBuilt=false;
    private String name;
    private int MAX_NUM_OF_CITIES_POSSIBLE_TO_OWN;
    private CityHex[] ownedCities;
    private int numOfSettlers=0, numOfTrains=0,tradeGoldBank=0;
    private JPanel goodsPanel;
    private boolean doubleGold, turn, paidRailRed, paidRailGreen, 
            paidRailWhite, paidRailOrange, playedDPCardThisTurn,beenPaidRightOfWayThisTurn,
            discardedThisTurn;
    private final int MAX_NUM_OF_DPCARDS=24, MAX_NUM_OF_RAILS=30;
    private Rail[] ownedRails;
    private ArrayList<DevelopmentCard> dpCards= new ArrayList<DevelopmentCard>();
    private Rail movingFromRail, movingToRail;
    private gameFrame game_Frame;
    
    Player(Player.Color p, boolean human, String name, int numOfPlayers, 
            JPanel jp, boolean fourthPlayer, boolean dG, gameFrame g) {
        settlerIntersections=new ArrayList<Intersection>();
        this.game_Frame=g;
        trainRails=new ArrayList<Rail>();
        lumberProbNums=new boolean[13];
        wheatProbNums=new boolean[13];
        cattleProbNums=new boolean[13];
        coalProbNums=new boolean[13];
        oreProbNums=new boolean[13];
        anyProbNums=new boolean[13];
        doubleGold=dG;
        ownedRails = new Rail[MAX_NUM_OF_RAILS];
        this.goodsPanel=jp;
        color = p;
        this.human = human;
        this.name = name;
        switch (numOfPlayers) {
            case 3:
                goods = new Good[10];
                MAX_NUM_OF_CITIES_POSSIBLE_TO_OWN = 12;
                ownedCities = new CityHex[MAX_NUM_OF_CITIES_POSSIBLE_TO_OWN];
                break;
            case 4:
                goods = new Good[8];
                MAX_NUM_OF_CITIES_POSSIBLE_TO_OWN = 10;
                ownedCities = new CityHex[MAX_NUM_OF_CITIES_POSSIBLE_TO_OWN];
                break;
        }
        for(int i=0;i<goods.length;i++){
            goods[i] = new Good(color);
        }
        if ((numOfPlayers == 3 && !fourthPlayer)||numOfPlayers==4) {
            if (color == Player.Color.RED) {
                colorYConstant = 0;
                try {
                    for (int i = 0; i < goods.length; i++) {
                        JLabel r=new JLabel();
                        r.setIcon(new ImageIcon(ImageIO.read(getClass().getResource(
                                "/settlerstrailstorails/resources/redGoods.png"))));
                        goods[i].setGoodLabel(r);
                    }
                    for(int i=0;i<MAX_NUM_OF_CITIES_POSSIBLE_TO_OWN-3;i++){
                        JLabel l=new JLabel();
                        l.setIcon(new ImageIcon(
        makeColorTransparent("/settlerstrailstorails/resources/redHouse.png", java.awt.Color.WHITE)));
                        cities.add(l);
                    }
                } catch (Exception e) {
                }
            } else if (color == Player.Color.GREEN) {
                colorYConstant = 1;
                try {
                    for (int i = 0; i < goods.length; i++) {
                        JLabel r = new JLabel();
                        r.setIcon(new ImageIcon(ImageIO.read(getClass().getResource(
                                "/settlerstrailstorails/resources/greenGoods.png"))));
                        goods[i].setGoodLabel(r);
                    }
                    for(int i=0;i<MAX_NUM_OF_CITIES_POSSIBLE_TO_OWN-3;i++){
                        JLabel l=new JLabel();
                        l.setIcon(new ImageIcon(
        makeColorTransparent("/settlerstrailstorails/resources/greenHouse.png", java.awt.Color.WHITE)));
                        cities.add(l);
                    }
                } catch (Exception e) {
                }
            } else if (color == Player.Color.WHITE) {
                colorYConstant = 2;
                try {
                    for (int i = 0; i < goods.length; i++) {
                        JLabel r= new JLabel();
                        r.setIcon(new ImageIcon(ImageIO.read(getClass().getResource(
                                "/settlerstrailstorails/resources/whiteGoods.png"))));
                        goods[i].setGoodLabel(r);
                    }
                    for(int i=0;i<MAX_NUM_OF_CITIES_POSSIBLE_TO_OWN-3;i++){
                        JLabel l=new JLabel();
                        l.setIcon(new ImageIcon(
        makeColorTransparent("/settlerstrailstorails/resources/whiteHouse.png", java.awt.Color.WHITE)));
                        cities.add(l);
                    }
                } catch (Exception e) {
                }
            } else if (color == Player.Color.ORANGE) {
                colorYConstant = 3;
                try {
                    for (int i = 0; i < goods.length; i++) {
                        JLabel r= new JLabel();
                        r.setIcon(new ImageIcon(ImageIO.read(getClass().getResource(
                                "/settlerstrailstorails/resources/orangeGoods.png"))));
                        goods[i].setGoodLabel(r);
                    }
                    for(int i=0;i<MAX_NUM_OF_CITIES_POSSIBLE_TO_OWN-3;i++){
                        JLabel l=new JLabel();
                        l.setIcon(new ImageIcon(
        makeColorTransparent("/settlerstrailstorails/resources/orangeHouse.png", java.awt.Color.WHITE)));
                        cities.add(l);
                    }
                } catch (Exception e) {
                }
            }
        }
        if(numOfPlayers == 4) {
            //goods
            if(goods[6]!=null && goods[6].getGoodLabel()!=null&&!(goods[6].getReadyForDelivery())){
            goodsPanel.add(goods[6].getGoodLabel(),new org.netbeans.lib.awtextra.AbsoluteConstraints(
                    97, 51 + (109 * colorYConstant)));
            }
            if(goods[5]!=null && goods[5].getGoodLabel()!=null&&!goods[5].getReadyForDelivery()){
            goodsPanel.add(goods[5].getGoodLabel(),new org.netbeans.lib.awtextra.AbsoluteConstraints(
                    97, 68 + (109 * colorYConstant)));
            }
            if(goods[4]!=null && goods[4].getGoodLabel()!=null&&!goods[4].getReadyForDelivery()){
            goodsPanel.add(goods[4].getGoodLabel(),new org.netbeans.lib.awtextra.AbsoluteConstraints(
                    105, 82 + (109 * colorYConstant)));
            }
            if(goods[3]!=null && goods[3].getGoodLabel()!=null&&!goods[3].getReadyForDelivery()){
            goodsPanel.add(goods[3].getGoodLabel(),new org.netbeans.lib.awtextra.AbsoluteConstraints(
                    124, 87 + (109 * colorYConstant)));
            }
            if(goods[2]!=null && goods[2].getGoodLabel()!=null&&!goods[2].getReadyForDelivery()){
            goodsPanel.add(goods[2].getGoodLabel(),new org.netbeans.lib.awtextra.AbsoluteConstraints(
                    143, 82 + (109 * colorYConstant)));
            }
            if(goods[1]!=null && goods[1].getGoodLabel()!=null&&!goods[1].getReadyForDelivery()){
            goodsPanel.add(goods[1].getGoodLabel(),new org.netbeans.lib.awtextra.AbsoluteConstraints(
                    151, 68 + (109 * colorYConstant)));
            }
            if(goods[0]!=null && goods[0].getGoodLabel()!=null&&!goods[0].getReadyForDelivery()){
            goodsPanel.add(goods[0].getGoodLabel(),new org.netbeans.lib.awtextra.AbsoluteConstraints(
                    151, 51 + (109 * colorYConstant)));
            }
            if (goods[7] != null && goods[7].getGoodLabel()!=null) {
                goodsPanel.add(goods[7].getGoodLabel(), new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 50 + (109 * colorYConstant), -1, -1));
                goods[7].setReadyForDelivery(true);
            }

            //cities
            goodsPanel.add(cities.get(0),new org.netbeans.lib.awtextra.AbsoluteConstraints(77,42+(109*colorYConstant),-1,-1));
            goodsPanel.add(cities.get(1),new org.netbeans.lib.awtextra.AbsoluteConstraints(77,66+(109*colorYConstant),-1,-1));
            goodsPanel.add(cities.get(2),new org.netbeans.lib.awtextra.AbsoluteConstraints(90,95+(109*colorYConstant),-1,-1));
            goodsPanel.add(cities.get(3),new org.netbeans.lib.awtextra.AbsoluteConstraints(120,100+(109*colorYConstant),-1,-1));
            goodsPanel.add(cities.get(4),new org.netbeans.lib.awtextra.AbsoluteConstraints(150,95+(109*colorYConstant),-1,-1));
            goodsPanel.add(cities.get(5),new org.netbeans.lib.awtextra.AbsoluteConstraints(163,67+(109*colorYConstant),-1,-1));
            goodsPanel.add(cities.get(6),new org.netbeans.lib.awtextra.AbsoluteConstraints(163,43+(109*colorYConstant),-1,-1));
            
        }else{           
            //goods
            if(goods[8]!=null && goods[8].getGoodLabel()!=null&&!goods[8].getReadyForDelivery()){
                goodsPanel.add(goods[8].getGoodLabel(),new org.netbeans.lib.awtextra.AbsoluteConstraints(
                    105, 38 + (109 * colorYConstant)));
            }            
            if(goods[7]!=null && goods[7].getGoodLabel()!=null&&!goods[7].getReadyForDelivery()){
            goodsPanel.add(goods[7].getGoodLabel(),new org.netbeans.lib.awtextra.AbsoluteConstraints(
                    97, 51 + (109 * colorYConstant)));
            }
            if(goods[6]!=null && goods[6].getGoodLabel()!=null&&!goods[6].getReadyForDelivery()){
            goodsPanel.add(goods[6].getGoodLabel(),new org.netbeans.lib.awtextra.AbsoluteConstraints(
                    97, 68 + (109 * colorYConstant)));
            }
            if(goods[5]!=null && goods[5].getGoodLabel()!=null&&!goods[5].getReadyForDelivery()){
            goodsPanel.add(goods[5].getGoodLabel(),new org.netbeans.lib.awtextra.AbsoluteConstraints(
                    105, 82 + (109 * colorYConstant)));
            }
            if(goods[4]!=null && goods[4].getGoodLabel()!=null&&!goods[4].getReadyForDelivery()){
            goodsPanel.add(goods[4].getGoodLabel(),new org.netbeans.lib.awtextra.AbsoluteConstraints(
                    124, 87 + (109 * colorYConstant)));
            }
            if(goods[3]!=null && goods[3].getGoodLabel()!=null&&!goods[3].getReadyForDelivery()){
            goodsPanel.add(goods[3].getGoodLabel(),new org.netbeans.lib.awtextra.AbsoluteConstraints(
                    143, 82 + (109 * colorYConstant)));
            }
            if(goods[2]!=null && goods[2].getGoodLabel()!=null&&!goods[2].getReadyForDelivery()){
            goodsPanel.add(goods[2].getGoodLabel(),new org.netbeans.lib.awtextra.AbsoluteConstraints(
                    151, 68 + (109 * colorYConstant)));
            }
            if(goods[1]!=null && goods[1].getGoodLabel()!=null&&!goods[1].getReadyForDelivery()){
            goodsPanel.add(goods[1].getGoodLabel(),new org.netbeans.lib.awtextra.AbsoluteConstraints(
                    151, 51 + (109 * colorYConstant)));
            }
            if(goods[0]!=null && goods[0].getGoodLabel()!=null&&!goods[0].getReadyForDelivery()){
            goodsPanel.add(goods[0].getGoodLabel(),new org.netbeans.lib.awtextra.AbsoluteConstraints(
                    143, 38 + (109 * colorYConstant)));
            }
            if(goods[9] != null && goods[9].getGoodLabel()!=null) {
                goodsPanel.add(goods[9].getGoodLabel(), new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 50 + (109 * colorYConstant), -1, -1));
                goods[9].setReadyForDelivery(true);
            }
            
            //cities       
            if(!fourthPlayer) {
                goodsPanel.add(cities.get(0), new org.netbeans.lib.awtextra.AbsoluteConstraints(96, 15 + (109 * colorYConstant), -1, -1));
                goodsPanel.add(cities.get(1), new org.netbeans.lib.awtextra.AbsoluteConstraints(77, 42 + (109 * colorYConstant), -1, -1));
                goodsPanel.add(cities.get(2), new org.netbeans.lib.awtextra.AbsoluteConstraints(77, 66 + (109 * colorYConstant), -1, -1));
                goodsPanel.add(cities.get(3), new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 95 + (109 * colorYConstant), -1, -1));
                goodsPanel.add(cities.get(4), new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100 + (109 * colorYConstant), -1, -1));
                goodsPanel.add(cities.get(5), new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 95 + (109 * colorYConstant), -1, -1));
                goodsPanel.add(cities.get(6), new org.netbeans.lib.awtextra.AbsoluteConstraints(163, 67 + (109 * colorYConstant), -1, -1));
                goodsPanel.add(cities.get(7), new org.netbeans.lib.awtextra.AbsoluteConstraints(163, 43 + (109 * colorYConstant), -1, -1));
                goodsPanel.add(cities.get(8), new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 15 + (109 * colorYConstant), -1, -1));
            }
        }
        resetGoodsDisplay();
        setAnyProbNums();
    }
    //unfinished
    //need INTERSECTION
    //need RAILS
    String savePlayer(){
        String retS="|--"+name+"--";
        retS+="|lumberProbNums";
        for(int i=0;i<lumberProbNums.length;i++){
            retS+= lumberProbNums[i] ? "1" : "0";
        }
        retS+="|wheatProbNums";
        for(int i=0;i<wheatProbNums.length;i++){
            retS+= wheatProbNums[i] ? "1" : "0";
        }
        retS+="|cattleProbNums";
        for(int i=0;i<cattleProbNums.length;i++){
            retS+= cattleProbNums[i] ? "1" : "0";
        }
        retS+="|coalProbNums";
        for(int i=0;i<coalProbNums.length;i++){
            retS+= coalProbNums[i] ? "1" : "0";
        }
        retS+="|oreProbNums";
        for(int i=0;i<oreProbNums.length;i++){
            retS+= oreProbNums[i] ? "1" : "0";
        }
        retS+="|anyProbNums";
        for(int i=0;i<anyProbNums.length;i++){
            retS+= anyProbNums[i] ? "1" : "0";
        }
        retS+="|dG"+doubleGold;
        retS+="|color"+color;
        retS+="|human"+human;
        retS+="|lumber"+lumber;
        retS+="|ore"+ore;
        retS+="|wheat"+wheat;
        retS+="|cattle"+cattle;
        retS+="|coal"+coal;
        retS+="|gold"+gold;
        retS+="|colorYConstant"+colorYConstant;
        retS+="|lumberProb"+lumberProb;
        retS+="|oreProb"+oreProb;
        retS+="|wheatProb"+wheatProb;
        retS+="|cattleProb"+cattleProb;
        retS+="|coalProb"+coalProb;
        retS+="|anyProb"+anyProb;
        retS+="|noneProb"+noneProb;
        retS+="|maxNumberOfCitiesBuilt"+maxNumberOfCitiesBuilt;
        retS+="|MAX_NUM_OF_CITIES_POSSIBLE_TO_OWN"+MAX_NUM_OF_CITIES_POSSIBLE_TO_OWN;
        retS+="|numOfSettlers"+numOfSettlers;
        retS+="|numOfTrains"+numOfTrains;
        retS+="|tradeGoldBank"+tradeGoldBank;
        retS+="|turn"+turn;
        retS+="|paidRailRed"+paidRailRed;
        retS+="|paidRailGreen"+paidRailGreen;
        retS+="|paidRailWhite"+paidRailWhite;
        retS+="|paidRailOrange"+paidRailOrange;
        retS+="|playedDPCardThisTurn"+playedDPCardThisTurn;
        retS+="|beenPaidRightOfWayThisTurn"+beenPaidRightOfWayThisTurn;
        retS+="|discardedThisTurn"+discardedThisTurn;
        for(int i=0;i<goods.length;i++){
            retS+=goods[i].saveGood();
        }
        for(int i=0;i<dpCards.size();i++){
            retS+=dpCards.get(i).saveCard();
        }
        retS+="|--end "+name+"--";
        return retS;
    }
    void loadPlayer(String s){
        
    }
    void setLumber(int l){
        lumber=l;
        setErrorIcon();
    }
    void plusLumber(){
        lumber++;
        setErrorIcon();
    }
    void minusLumber(){
        lumber--;
        setErrorIcon();
    }
    int getLumber(){
        return lumber;
    }
    void setOre(int o){
        setErrorIcon();
        ore=o;
    }
    void plusOre(){
        ore++;
        setErrorIcon();
    }
    void minusOre(){
        ore--;
        setErrorIcon();
    }
    int getOre(){
        return ore;
    }
    void setGold(int g){        
        if(g>gold){
            gameFrame.addToInfo(getName()+" got "+(g-gold)+" gold.", false);
        }else if(g<gold){
            gameFrame.addToInfo(getName()+" paid "+(gold-g)+" gold.", false);
        }        
        gold=g;
    }
    void setGoldIgnoreStuff(int g){
        gold=g;
    }
    void plusGold(){
        gameFrame.playSound("ching");
        if(doubleGold){
            gold+=2;
            gameFrame.addToInfo(getName()+" got "+gold+" gold.",false);
        }else{
            gold++;
            gameFrame.addToInfo(getName()+" got gold",false);
        }
    }
    void plusGoldIgnoreSound(){
        if(doubleGold){
            gold+=2;
            gameFrame.addToInfo(getName()+" got "+gold+" gold.",false);
        }else{
            gold++;
            gameFrame.addToInfo(getName()+" got gold",false);
        }
    }
    void minusGold(){
        gold--;
    }
    int getGold(){
        return gold;
    }
    void setWheat(int w){
        wheat=w;
        setErrorIcon();
    }
    void plusWheat(){
        wheat++;
        setErrorIcon();
    }
    void minusWheat(){
        wheat--;
        setErrorIcon();
    }
    int getWheat(){
        return wheat;
    }
    void setCattle(int c){
        cattle=c;
        setErrorIcon();
    }
    void plusCattle(){
        cattle++;
        setErrorIcon();
    }
    void minusCattle(){
        cattle--;
        setErrorIcon();
    }
    int getCattle(){
        return cattle;
    }
    void setCoal(int c){
        coal=c;
        setErrorIcon();
    }
    void plusCoal(){
        coal++;
        setErrorIcon();
    }
    void minusCoal(){
        coal--;
        setErrorIcon();
    }
    int getCoal(){
        return coal;
    }
    void plusResource(Hex.Resource r){
        if(r==Hex.Resource.CATTLE){
            cattle++;
        }else if(r==Hex.Resource.COAL){
            coal++;
        }else if(r==Hex.Resource.LUMBER){
            lumber++;
        }else if(r==Hex.Resource.WHEAT){
            wheat++;
        }else if(r==Hex.Resource.ORE){
            ore++;
        }
        setErrorIcon();
    }
    void minusResource(Hex.Resource r){
        if(r==Hex.Resource.CATTLE){
            cattle--;
        }else if(r==Hex.Resource.COAL){
            coal--;
        }else if(r==Hex.Resource.LUMBER){
            lumber--;
        }else if(r==Hex.Resource.WHEAT){
            wheat--;
        }else if(r==Hex.Resource.ORE){
            ore--;
        }
        setErrorIcon();
    }
    boolean getHuman(){
        return human;
    }
    void setHuman(boolean b){
        human=b;
    }
    void getResourcesThirdCity(){
        if(getNumberOfCities() > 2 && ownedCities[2]!=null) {
            if(ownedCities[2].getHexOne().getChit() != 0 && ownedCities[2].getHexOne().getResource()!=Hex.Resource.DESERT) {
                if (ownedCities[2].getHexOne().getResource() == Hex.Resource.CATTLE) {
                    plusCattle();
                } else if (ownedCities[2].getHexOne().getResource() == Hex.Resource.COAL) {
                    plusCoal();
                } else if (ownedCities[2].getHexOne().getResource() == Hex.Resource.ORE) {
                    plusOre();
                } else if (ownedCities[2].getHexOne().getResource() == Hex.Resource.WHEAT) {
                    plusWheat();
                } else if (ownedCities[2].getHexOne().getResource() == Hex.Resource.LUMBER) {
                    plusLumber();
                }
            }
            if(ownedCities[2].getHexTwo()!=null && 
                    ownedCities[2].getHexTwo().getChit()!=0 && 
                    ownedCities[2].getHexTwo().getResource()!=Hex.Resource.DESERT){
                if (ownedCities[2].getHexTwo().getResource() == Hex.Resource.CATTLE) {
                    plusCattle();
                } else if (ownedCities[2].getHexTwo().getResource() == Hex.Resource.COAL) {
                    plusCoal();
                } else if (ownedCities[2].getHexTwo().getResource() == Hex.Resource.ORE) {
                    plusOre();
                } else if (ownedCities[2].getHexTwo().getResource() == Hex.Resource.WHEAT) {
                    plusWheat();
                } else if (ownedCities[2].getHexTwo().getResource() == Hex.Resource.LUMBER) {
                    plusLumber();
                }
            }
            if(ownedCities[2].getHexThree()!=null && 
                    ownedCities[2].getHexThree().getChit()!=0 && 
                    ownedCities[2].getHexThree().getResource()!=Hex.Resource.DESERT){
                if (ownedCities[2].getHexThree().getResource() == Hex.Resource.CATTLE) {
                    plusCattle();
                } else if (ownedCities[2].getHexThree().getResource() == Hex.Resource.COAL) {
                    plusCoal();
                } else if (ownedCities[2].getHexThree().getResource() == Hex.Resource.ORE) {
                    plusOre();
                } else if (ownedCities[2].getHexThree().getResource() == Hex.Resource.WHEAT) {
                    plusWheat();
                } else if (ownedCities[2].getHexThree().getResource() == Hex.Resource.LUMBER) {
                    plusLumber();
                }
            }
        }else{
            return;
        }
    }
    void setErrorIcon() {
        if (color == Player.Color.RED) {
            if (getResourceCards() > 7) {
                game_Frame.getErrorRedLabel().setVisible(true);
            } else {
                game_Frame.getErrorRedLabel().setVisible(false);
            }
        }else if (color == Player.Color.GREEN) {
            if (getResourceCards() > 7) {
                game_Frame.getErrorGreenLabel().setVisible(true);
            } else {
                game_Frame.getErrorGreenLabel().setVisible(false);
            }
        }else if (color == Player.Color.WHITE) {
            if (getResourceCards() > 7) {
                game_Frame.getErrorWhiteLabel().setVisible(true);
            } else {
                game_Frame.getErrorWhiteLabel().setVisible(false);
            }
        }else if (color == Player.Color.ORANGE) {
            if (getResourceCards() > 7) {
                game_Frame.getErrorOrangeLabel().setVisible(true);
            } else {
                game_Frame.getErrorOrangeLabel().setVisible(false);
            }
        }
    }
    void discardHalf(ArrayList<Player> discardPlayers, gameFrame g){
        int discardNum = (int)Math.floor((lumber+ore+wheat+cattle+coal)/2);
        DiscardHalfWindow dhw = new DiscardHalfWindow(this, discardNum,discardPlayers,g);
        dhw.setVisible(true);
        dhw.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent we) {
                    new SettlersConfirmDialog(20).setVisible(true);
                }
            });
        discardedThisTurn=true;
    }
    boolean getDiscardedThisTurn(){
        return discardedThisTurn;
    }
    void setDiscardedThisTurn(boolean b){
        discardedThisTurn=b;
    }
    /**
     * this method assumes delivering of goods is possible
     * @param p
     * @param jp 
     */
    JLabel deliverGood(){
        JLabel r=null;
        for(int i=goods.length-1;i>-1;i--){
            if(goods[i].getReadyForDelivery()&&!goods[i].getDelivered()){
                r = goods[i].getGoodLabel();
                goods[i].setDelivered(true);
                goods[i].setReadyForDelivery(false);
                break;
            }
        }
        goodsPanel.remove(r);
        resetGoodsDisplay();
        return r;
    }
    public void randomCardFrom(Player p){
        if(p==null)return;
        if(p.getResourceCards()==0)return;
        Hex.Resource[] cards = new Hex.Resource[p.getResourceCards()];
        for(int i=0;i<p.getLumber();i++){
            cards[i]=Hex.Resource.LUMBER;
        }
        for(int i=0;i<p.getCoal();i++){
            cards[i]=Hex.Resource.COAL;
        }
        for(int i=0;i<p.getCattle();i++){
            cards[i]=Hex.Resource.CATTLE;
        }
        for(int i=0;i<p.getWheat();i++){
            cards[i]=Hex.Resource.WHEAT;
        }
        for(int i=0;i<p.getOre();i++){
            cards[i]=Hex.Resource.ORE;
        }
        Random r = new Random();
        int choice = r.nextInt(p.getResourceCards());
        if(cards[choice]==Hex.Resource.LUMBER){
            p.minusLumber();
            plusLumber();
        }else if(cards[choice]==Hex.Resource.COAL){
            p.minusCoal();
            plusCoal();
        }else if(cards[choice]==Hex.Resource.CATTLE){
            p.minusCattle();
            plusCattle();
        }else if(cards[choice]==Hex.Resource.WHEAT){
            p.minusWheat();
            plusWheat();
        }else if(cards[choice]==Hex.Resource.ORE){
            p.minusOre();
            plusOre();
        }
    }
    public boolean canDeliver(){
        if(goods[0].getNumberOfGoodsReady()==0){
            return false;
        }
        return true;
    }
    int getReadyGoods(){
        return goods[0].getNumberOfGoodsReady();
    }
    void addNumberOfGoodsReady(){
        for(int i=goods.length-1;i>-1;i--){
            if(!goods[i].getReadyForDelivery()&&!goods[i].getDelivered()){
                goods[i].setReadyForDelivery(true);
                break;
            }
        }
    }
    Good getNextGoodReady(){
        for(int i=goods.length-1;i>-1;i--){
            if(goods[i].getReadyForDelivery()){
                return goods[i];
            }
        }
        return null;
    }
    String getName(){
        if(name==null){
            return color.toString()+" Player";
        }else{
            return name; 
        }
    }
    void setName(String n){
        name=n;
    }
    Player.Color getColor(){
        return color;
    }
    CityHex[] getOwnedCities(){
        return ownedCities;
    }
    boolean buildSettlement(CityHex c){
        if(getNumberOfCities()>=3){
            for(int i=0;i<ownedCities.length;i++){
            if(ownedCities[i] == null) {
                    ownedCities[i] = c;
                    setGold(gold + c.getGoldWorth());
                    addNumberOfGoodsReady();
                    resetGoodsDisplay();
                    gameFrame.addToInfo(toString() + " settled in " + c.getName(), false);
                    if (c.getGoldWorth() != 0) {
                        gameFrame.addToInfo(toString() + " received " + c.getGoldWorth() + " gold", false);
                    }
                    if (i == ownedCities.length - 1) {
                        maxNumberOfCitiesBuilt = true;
                        gameFrame.addToInfo(toString() + " has built all his cities!", false);
                    }
                    cities.remove(0).setIcon(null);
                    return true;
                }
            }
        }else{
            for(int i = 0; i < ownedCities.length; i++) {
                if (ownedCities[i] == null) {
                    ownedCities[i] = c;
                    setGold(gold + c.getGoldWorth());
                    forLoop:
                    gameFrame.addToInfo(toString() + " settled in " + c.getName(), false);
                    if (c.getGoldWorth() != 0) {
                        gameFrame.addToInfo(toString() + " received " + c.getGoldWorth() + " gold", false);
                    }
                    if (i == ownedCities.length - 1) {
                        maxNumberOfCitiesBuilt = true;
                        gameFrame.addToInfo(toString() + " has built all his cities!", false);
                    }
                    return true;
                }
            }
        }
        return false;
    }
    boolean buildGhostSettlement(CityHex c){
        for(int i=0;i<ownedCities.length;i++){
            if(ownedCities[i]==null){
                ownedCities[i] = c;
                gameFrame.playSound("click");
                return true;
            }
        }
        return false;
    }
    boolean maxNumberOfCitiesBuilt(){
        return maxNumberOfCitiesBuilt;
    }
    @Override
    public String toString(){
        if(name==null){
            return color.toString()+" Player";
        }else{
            return name; 
        }
    }
    private void resetGoodsDisplay() {
        ArrayList<Good> goodsToDisplay=new ArrayList<Good>();
        for(int i=goods.length-1;i>-1;i--){
            if(goods[i].getReadyForDelivery() && !goods[i].getDelivered()){
                goodsToDisplay.add(goods[i]);
            }
        }
        if(goodsToDisplay.size()>0 && goodsToDisplay.get(0) != null){
          goodsPanel.add(goodsToDisplay.get(0).getGoodLabel(),new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 50 + (109 * colorYConstant),-1,-1));
        }
        if (goodsToDisplay.size()>1 && goodsToDisplay.get(1) != null) {
          goodsPanel.add(goodsToDisplay.get(1).getGoodLabel(),new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 60 + (109 * colorYConstant),-1,-1));
        }
        if (goodsToDisplay.size()>2 && goodsToDisplay.get(2) != null) {
          goodsPanel.add(goodsToDisplay.get(2).getGoodLabel(),new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 70 + (109 * colorYConstant),-1,-1));
        }
        if (goodsToDisplay.size()>3 && goodsToDisplay.get(3) != null) {
          goodsPanel.add(goodsToDisplay.get(3).getGoodLabel(),new org.netbeans.lib.awtextra.AbsoluteConstraints(124, 50 + (109 * colorYConstant),-1,-1));
        }
        if (goodsToDisplay.size()>4 && goodsToDisplay.get(4) != null) {
          goodsPanel.add(goodsToDisplay.get(4).getGoodLabel(),new org.netbeans.lib.awtextra.AbsoluteConstraints(124, 60 + (109 * colorYConstant),-1,-1));
        }
        if (goodsToDisplay.size()>5 && goodsToDisplay.get(5) != null) {
          goodsPanel.add(goodsToDisplay.get(5).getGoodLabel(),new org.netbeans.lib.awtextra.AbsoluteConstraints(124, 70 + (109 * colorYConstant),-1,-1));
        }
        if (goodsToDisplay.size()>6 && goodsToDisplay.get(6) != null) {
          goodsPanel.add(goodsToDisplay.get(6).getGoodLabel(),new org.netbeans.lib.awtextra.AbsoluteConstraints(134, 50 + (109 * colorYConstant),-1,-1));
        }
        if (goodsToDisplay.size()>7 && goodsToDisplay.get(7) != null) {
          goodsPanel.add(goodsToDisplay.get(7).getGoodLabel(),new org.netbeans.lib.awtextra.AbsoluteConstraints(134, 60 + (109 * colorYConstant),-1,-1));
        }
        if (goodsToDisplay.size()>8 && goodsToDisplay.get(8) != null) {
          goodsPanel.add(goodsToDisplay.get(8).getGoodLabel(),new org.netbeans.lib.awtextra.AbsoluteConstraints(134, 70 + (109 * colorYConstant),-1,-1));
        }
        if (goodsToDisplay.size()>9 && goodsToDisplay.get(9) != null) {
          goodsPanel.add(goodsToDisplay.get(9).getGoodLabel(),new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60 + (109 * colorYConstant),-1,-1));
        }
        goodsPanel.setVisible(false);
        goodsPanel.setVisible(true);
    }
    /**
     * returns the number of cities owned by the player
     * @return 
     */
    public int getNumberOfCities(){//<editor-fold>
        int returnVal=0;
        for(int i=0;i<ownedCities.length;i++){
            if(ownedCities[i]!=null)returnVal++;
        }
        return returnVal;
    }//</editor-fold>
    public int getGoodsDelivered(){//<editor-fold>
        return goods.length-(goods[0].getNumberOfGoodsUndelivered());
    }//</editor-fold>
    public int getNumberOfGoodsUndelivered(){
        return goods[0].getNumberOfGoodsUndelivered();
    }
    public int getResourceCards(){//<editor-fold>
        return lumber+coal+ore+wheat+cattle;
    }//</editor-fold>
    public ArrayList<DevelopmentCard> getDPCards(){
        return dpCards;
    }
    /**
     * returns false if this player already has the maximum
     * number of dp cards a player can hold (default = 24)
     * @param d - development card to be added
     */
    public boolean plusDPCard(DevelopmentCard d){//<editor-fold>
        if(game_Frame.getExtraordinaryBuildingPhaseNum()==0){
            game_Frame.developmentCardVisible();
        }
        if(dpCards.size()==MAX_NUM_OF_DPCARDS){
            return false;
        }else{
            dpCards.add(d);
            return true;
        }
    }//</editor-fold>
        /**
     * returns false if this player already has no dp cards
     * @param d - development card to be added
     */
    public boolean minusDPCard(int num){//<editor-fold>
        for(int i=0;i<dpCards.size();i++){
            if(dpCards.get(i).getCard()==num){
                dpCards.remove(i);
                   if(dpCards.isEmpty()){
                    game_Frame.developmentCardNotVisible();
                }
                return true;
            }
        }
        return false;
    }//</editor-fold>
    /**
     * returns number of DP cards held by player
     */
    public int getNumberOfDP(){//<editor-fold>
        return dpCards.size();
    }//</editor-fold>
    /**
     * run this method at the start of a players turn
     * sets all held DP cards' playable value to true
     */
    public void startTurn(){//<editor-fold>
        for(int i=0;i<dpCards.size();i++){
            dpCards.get(i).setPlayable(true);
        }
        Player[] players = game_Frame.getPlayers();
        for(int i=0;i<players.length;i++){
            players[i].setDiscardedThisTurn(false);
        }
        tradeGoldBank=0;
        paidRailRed=false;
        paidRailOrange=false;
        paidRailWhite=false;
        paidRailGreen=false;
        if(dpCards.size()>0){
            game_Frame.developmentCardVisible();
        }
        setPlayedDPCardThisTurn(false);
        game_Frame.setRolled(false);
        setTurn(true);
        //uninitiated
        if(!human){
            compFindBestMove();
        }
    }//</editor-fold>
    public void endTurn(){
        setTurn(false);
        Player[] players = game_Frame.getPlayers();
        for(int i=0;i<players.length;i++){
            players[i].setBeenPaidRightOfWayThisTurn(false);
        }
        game_Frame.developmentCardNotVisible();
    }
    public String compFindBestMove(){
        //find best possible move
        //compare to resources and judge what the best move is
        
        /*
         * firstly and most importantly, check to see if
         * the delivering of goods is possible within this turn
         * if so, deliver goods
         * 
         * deliver goods IF
         * 1. at least 1 good is ready to be delivered
         * 2. train within numberOfCoal*3 rails from a city
         *    able to accept a good with a rail path leading to that city
         */
        if(goods[0].getNumberOfGoodsReady()>0){
            //loop through every rail that has a train on it
            //owned by this player
            for(int i=0;i<trainRails.size();i++){
                //if the rail is adjacent to a city that is owned
                //by someone else and it has not been delivered to
                if(trainRails.get(i).getCity()!=null && 
                        trainRails.get(i).getCity().isOwned() &&
                        trainRails.get(i).getCity().getOwner() != this
                        && canDeliver() && !trainRails.get(i).getCity().getBeenDelivered()) {
                    //best move is to deliver goods to trainRails.get(i).getCity()
                    /**
                     *  game_Frame.deliverGoods(trainRails.get(i).getCity(), this);
                     *  game_Frame.checkWinner();
                     */
                    return "Deliver goods to "+trainRails.get(i).getCity().getName();
                }
            }
            //if no trains are owned on a rail next to a city that can
            //be delivered to. check to see if a train can be moved
            //to a place in which the goods can be delivered
            
            //loop through every rail and find rails that are open
            //if that rail is next to a city that can be delivered
            //by this player, find out if there exists a built path
            //between the trains i own and this rail
            
            //if the length is more than 6 and you can buy a train
            //on a closer city, do that.
            Rail[] r = game_Frame.getRails();
            Path[] paths = new Path[50];
            int[] rGoldP = new int[50];
            int count = 0;
            railLoop:
            for(int i=0;i<r.length; i++) {
                if (r[i].getCity() != null && !r[i].getCity().getBeenDelivered()&&
                        r[i].getCity().isOwned()&&r[i].getCity().getOwner()!=this) {
                    trainLoop:
                    for (int j = 0; j < trainRails.size(); j++) {
                        Path p = findBuiltPathExists(trainRails.get(j), r[i],/*max length to check for*/ 6);
                        //find out how much right of way would cost
                        if (p == null) {
                            continue trainLoop;
                        }
                        int requiredGold = 0;
                        boolean owesRed = false, owesWhite = false, owesGreen = false, owesOrange = false;
                        for (int k = 0; k < p.getPath().length; k++) {
                            if (!owesRed && p.getPath()[k].getColor() == Player.Color.RED && color != Player.Color.RED
                                    && !game_Frame.redPlayer.getBeenPaidRightOfWayThisTurn()) {
                                requiredGold++;
                                owesRed = true;
                            } else if (!owesGreen && p.getPath()[k].getColor() == Player.Color.GREEN && color != Player.Color.GREEN
                                    && !game_Frame.greenPlayer.getBeenPaidRightOfWayThisTurn()) {
                                requiredGold++;
                                owesGreen = true;
                            } else if (!owesWhite && p.getPath()[k].getColor() == Player.Color.WHITE && color != Player.Color.WHITE
                                    && !game_Frame.whitePlayer.getBeenPaidRightOfWayThisTurn()) {
                                requiredGold++;
                                owesWhite = true;
                            } else if (!owesOrange && p.getPath()[k].getColor() == Player.Color.ORANGE && color != Player.Color.ORANGE
                                    && !game_Frame.orangePlayer.getBeenPaidRightOfWayThisTurn()) {
                                requiredGold++;
                                owesOrange = true;
                            }
                        }
                        if (!r[i].hasSecondTrain() && !trainRails.contains(r[i]) && p != null
                                && p.getPath().length < (coal * 3) && getGold() >= requiredGold) {
                            //move train to r[i]
                            paths[count]=p;
                            rGoldP[count++]=requiredGold;
                        }
                    }
                }
            }
            Path best = null;
            int rGoldBest = 20;
            for(int i=0;i<paths.length;i++){
                if(best==null){
                    best=paths[i];
                    rGoldBest=rGoldP[i];
                    continue;
                }
                if(paths[i]==null)continue;
                //if length is shorter but not required to pay 2 more gold
                if(paths[i].getPath().length < best.getPath().length &&
                        rGoldP[i]-2<=rGoldBest){                    
                    best=paths[i];
                    rGoldBest=rGoldP[i];
                    continue;
                }
                //if it's cheaper, make sure the length isn't more than
                //6 away
                if(rGoldP[i]<rGoldBest && paths[i].getPath().length-6 <= best.getPath().length){                                        
                    best=paths[i];
                    rGoldBest=rGoldP[i];
                    continue;
                }
            }
            if(best!=null){
                return "Move your train to "+best.getLast().getCity().getName();
            }

            //if the i can buy a train
        } else {
            
        }
        
        /*
         * check to see if moving the settler is the best option
         * move settler IF
         * 1. numOfSettlers>0
         * 2. wheat > 0
         * 3. find best city to settle and build a settler at the closest
         *      intersection and begin moving the settler toward the city.
         */
        
        /*
         * check to see if build settler is best move
         * 
         * best move = build settler IF
         * 1.have less than or equal to 1 settler
         * 2. have more than numOfSettlers+1 house able to build
         * 3. have less than 2 goods ready to deliver
         * 
         */
        if(!(numOfSettlers>1)){
            
        }
        return null;
    }
    /**
     * returns the path from r1 to r2 but does NOT include r1
     * searches for paths of length < MAX_LENGTH
     * @param r1
     * @param r2
     * @param MAX_LENGTH
     * @return 
     */
    public Path findBuiltPathExists(Rail r1, Rail r2,int MAX_LENGTH){
        boolean endLoop = false;
        LinkedList<Rail> q = new LinkedList<Rail>();
        ArrayList<Rail> visited = new ArrayList<Rail>();
        Path[] returnPaths = new Path[(int)(Math.pow(2,MAX_LENGTH)*2)];
        for(int i=0;i<returnPaths.length;i++){
            returnPaths[i] = new Path();
        }
        if(!r1.isBuilt() || !r2.isBuilt() || MAX_LENGTH==0){
            return null;
        }
        q.add(r1);
        r1.setTestDistance(0);
        whileLoop:
        while (!q.isEmpty()) {
            Rail curr = q.pop();
            int count;
            if (curr.getTestDistance() == 0) {
                count = (int) ((Math.pow(2, MAX_LENGTH)) * 2);
            } else {
                count = (int) ((Math.pow(2, MAX_LENGTH - curr.getTestDistance())));
            }
            for (int i = 0; i < returnPaths.length;i++){
                if(count>0&&!returnPaths[i].contains(curr)&&
                        (returnPaths[i].getLast()==null ||
                        (returnPaths[i].getLast().getTestDistance()==curr.getTestDistance()-1)&&
                        (returnPaths[i].contains(curr.getUpLeftRail())||returnPaths[i].contains(curr.getUpRightRail())||
                        returnPaths[i].contains(curr.getDownLeftRail())||returnPaths[i].contains(curr.getDownRightRail())))){
                    returnPaths[i].add(curr);
                    count--;
                }
            }
            if(curr.getTestDistance()==MAX_LENGTH){
                endLoop=true;
            }
            if(curr==r2){
                break whileLoop;
            }
            visited.add(curr);
            if (!endLoop && curr.getUpLeftRail() != null && !visited.contains(curr.getUpLeftRail())
                    && curr.getUpLeftRail().isBuilt() && !curr.getUpLeftRail().isGhost()
                    && !q.contains(curr.getUpLeftRail())) {
                q.add(curr.getUpLeftRail());
                curr.getUpLeftRail().setTestDistance(curr.getTestDistance() + 1);
            }
            if (!endLoop && curr.getUpRightRail() != null && !visited.contains(curr.getUpRightRail())
                    && curr.getUpRightRail().isBuilt() && !curr.getUpRightRail().isGhost()
                    && !q.contains(curr.getUpRightRail())) {
                q.add(curr.getUpRightRail());
                curr.getUpRightRail().setTestDistance(curr.getTestDistance() + 1);
            }
            if (!endLoop && curr.getDownLeftRail() != null && !visited.contains(curr.getDownLeftRail())
                    && curr.getDownLeftRail().isBuilt() && !curr.getDownLeftRail().isGhost()
                    && !q.contains(curr.getDownLeftRail())) {
                q.add(curr.getDownLeftRail());
                curr.getDownLeftRail().setTestDistance(curr.getTestDistance() + 1);
            }
            if (!endLoop && curr.getDownRightRail() != null && !visited.contains(curr.getDownRightRail())
                    && curr.getDownRightRail().isBuilt() && !curr.getDownRightRail().isGhost()
                    && !q.contains(curr.getDownRightRail())) {
                q.add(curr.getDownRightRail());
                curr.getDownRightRail().setTestDistance(curr.getTestDistance() + 1);
            }
        }
        for(int i=0;i<visited.size();i++){
            visited.get(i).setTestDistance(-1);
        }
        for(int i=0;i<returnPaths.length;i++){
            if(returnPaths[i].contains(r2) && returnPaths[i].contains(r1)){
                returnPaths[i].removeFirst();
                return returnPaths[i];
            }
        }
        return null;
    }
    public void plusGoldTrade(){
        tradeGoldBank++;
    }
    public int getGoldTrade(){
        return tradeGoldBank;
    }
    public void setBeenPaidRightOfWayThisTurn(boolean b){
        beenPaidRightOfWayThisTurn=b;
    }
    public boolean getBeenPaidRightOfWayThisTurn(){
        return beenPaidRightOfWayThisTurn;
    }
    public double getLumberProb(){
        return lumberProb;
    }
    public double getOreProb(){
        return oreProb;
    }
    public double getWheatProb(){
        return wheatProb;
    }
    public double getCattleProb(){
        return cattleProb;
    }
    public double getCoalProb(){
        return coalProb;
    }
    public double getAnyProb(){
        return anyProb;
    }
    public boolean[] getLumberProbNums(){
        return lumberProbNums;
    }
    public boolean[] getOreProbNums(){
        return oreProbNums;
    }
    public boolean[] getWheatProbNums(){
        return wheatProbNums;
    }
    public boolean[] getCoalProbNums(){
        return coalProbNums;
    }
    public boolean[] getCattleProbNums(){
        return cattleProbNums;
    }
    public boolean[] getAnyProbNums(){
        return anyProbNums;
    }
    public void setLumberProb(double i){
        lumberProb = i;
    }
    public void setOreProb(double i){
        oreProb = i;
    }
    public void setWheatProb(double i){
        wheatProb = i;
    }
    public void setCoalProb(double i){
        coalProb = i;
    }
    public void setCattleProb(double i){
        cattleProb = i;
    }
    public void setAnyProb(double i){
        anyProb = i;
    }
    public void setLumberProbNums(boolean[] b){
        lumberProbNums = b;
    }
    public void setOreProbNums(boolean[] b){
        oreProbNums = b;
    }
    public void setWheatProbNums(boolean[] b){
        wheatProbNums = b;
    }
    public void setCoalProbNums(boolean[] b){
        coalProbNums = b;
    }
    public void setCattleProbNums(boolean[] b){
        cattleProbNums = b;
    }
    public final void setAnyProbNums(){
        anyProb=0;
        for(int i=0;i < 13; i++) {
            anyProbNums[i] = lumberProbNums[i] || oreProbNums[i] || wheatProbNums[i] ||
                    cattleProbNums[i] || coalProbNums[i];
            if (anyProbNums[i]) {
                switch (i) {
                    case 2:
                        anyProb += 0.0278;
                        break;
                    case 3:
                        anyProb += 0.0556;
                        break;
                    case 4:
                        anyProb += 0.0833;
                        break;
                    case 5:
                        anyProb += 0.1111;
                        break;
                    case 6:
                        anyProb += 0.1389;
                        break;
                    case 8:
                        anyProb += 0.1389;
                        break;
                    case 9:
                        anyProb += 0.1111;
                        break;
                    case 10:
                        anyProb += 0.0833;
                        break;
                    case 11:
                        anyProb += 0.0556;
                        break;
                    case 12:
                        anyProb += 0.0278;
                        break;
                    default:
                        anyProb += 0;
                        break;
                }
            }
        }
        noneProb=1-anyProb;
    }
    public double getNoneProb(){
        return noneProb;
    }
    public void setDoubleGold(boolean b){
        doubleGold=b;
    }
    public boolean getBlockedByRobber(){
        if(ownedCities != null) {
            for (int i = 0; i < ownedCities.length; i++) {
                if (ownedCities[i] != null && ownedCities[i].getBlockedByRobber()) {
                    return true;
                }
            }
        }
        return false;
    }
    public Rail[] getOwnedRails(){
        return ownedRails;
    }
    public boolean buildRail(Rail r, Player[] playerA){
        if(r!=null && r.isGhost()) {
            r.demolishRail();
            ArrayList<Path> paths = r.buildRail(color);
            if(paths!=null&&paths.size()>1){
                gameFrame.addToInfo(getName()+" built a rail.",false);
                //ask user to choose which path
                choosePathWindow cpw = new choosePathWindow(paths, playerA);
                cpw.setVisible(true);
                cpw.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent we) {
                        new SettlersConfirmDialog(20).setVisible(true);
                    }
                });
            } else if (paths != null && paths.size() == 1) {
                gameFrame.addToInfo(getName()+" built a rail.",false);
                Player redPlayer=null,greenPlayer=null,orangePlayer=null,whitePlayer=null;
                for(int i = 0; i < playerA.length; i++) {
                    if (playerA[i].getColor() == Player.Color.RED) {
                        redPlayer = playerA[i];
                    }else if (playerA[i].getColor() == Player.Color.GREEN) {
                        greenPlayer = playerA[i];
                    }else if (playerA[i].getColor() == Player.Color.WHITE) {
                        whitePlayer = playerA[i];
                    }else{
                        orangePlayer = playerA[i];
                    }
                }
                Rail[] currR = paths.get(0).getPath();
                int redGold=0,whiteGold=0,orangeGold=0,greenGold=0;
                for (int j = 0; j < currR.length; j++) {
                    if (currR[j].getColor() == Player.Color.RED && redPlayer!=null) {
                        redGold++;
                    } else if (currR[j].getColor() == Player.Color.GREEN && greenPlayer!=null) {
                        greenGold++;
                    } else if (currR[j].getColor() == Player.Color.WHITE && whitePlayer!=null) {
                        whiteGold++;
                    } else if (currR[j].getColor() == Player.Color.ORANGE && orangePlayer!=null) {
                        orangeGold++;
                    }
                }
                if(redGold!=0){
                    redPlayer.setGold(redPlayer.getGold()+redGold);
                }
                if(greenGold!=0){
                    greenPlayer.setGold(greenPlayer.getGold()+greenGold);
                }
                if(whiteGold!=0){
                    whitePlayer.setGold(whitePlayer.getGold()+whiteGold);
                }
                if(orangeGold!=0){
                    orangePlayer.setGold(orangePlayer.getGold()+orangeGold);
                }
                gameFrame.playSound("ching");
            }
            for (int i = 0; i < ownedRails.length; i++) {
                if (ownedRails[i] == null) {
                    ownedRails[i] = r;
                    break;
                }
            }
            if (ownedRails[1] == null) {
                r.setTrain(true);
            }
            return true;
        }else{
            return false;
        }
    }
    public void displayRailPossibilities(){
        //<editor-fold>
        for(int i=0;i<ownedRails.length;i++){
            if(ownedRails[i] != null) {
                Rail ulR = ownedRails[i].getUpLeftRail();
                Rail urR = ownedRails[i].getUpRightRail();
                Rail dlR = ownedRails[i].getDownLeftRail();
                Rail drR = ownedRails[i].getDownRightRail();
                if (ulR != null && !ulR.isBuilt() && ulR.getColor() == null) {
                    ulR.buildRail(null);
                }
                if (urR != null && !urR.isBuilt() && urR.getColor() == null) {
                    urR.buildRail(null);
                }
                if (dlR != null && !dlR.isBuilt() && dlR.getColor() == null) {
                    dlR.buildRail(null);
                }
                if (drR != null && !drR.isBuilt() && drR.getColor() == null) {
                    drR.buildRail(null);
                }
            }
        }
        for(int i=0;i<ownedCities.length;i++){
            if(ownedCities[i]!=null){
                ownedCities[i].displayAdjacentGhostRails();
            }
        }
        //</editor-fold>
    }
    public void destroyRailPossibilities(){
        //<editor-fold>
        for(int i=0;i<ownedRails.length; i++) {
            if (ownedRails[i] != null) {
                Rail ulR = ownedRails[i].getUpLeftRail();
                Rail urR = ownedRails[i].getUpRightRail();
                Rail dlR = ownedRails[i].getDownLeftRail();
                Rail drR = ownedRails[i].getDownRightRail();
                if (ulR != null && ulR.isGhost()) {
                    ulR.demolishRail();
                }
                if (urR != null && urR.isGhost()) {
                    urR.demolishRail();
                }
                if (dlR != null && dlR.isGhost()) {
                    dlR.demolishRail();
                }
                if (drR != null && drR.isGhost()) {
                    drR.demolishRail();
                }
            }
        }        
        for(int i=0;i<ownedCities.length;i++){
            if(ownedCities[i]!=null){
                ownedCities[i].demolishAdjacentGhostRails();
            }
        }
        //</editor-fold>
    }
    boolean maxRailsBuilt(){
        return (ownedRails[MAX_NUM_OF_RAILS-1]!=null) ? true : false;
    }
    public int getNumberOfRailsBuilt(){
        int retVal=0;
        for(int i=0;i<ownedRails.length;i++){
            if(ownedRails[i]!=null)retVal++;
        }
        return retVal;
    }
    public boolean isTurn(){
        return turn;
    }
    public void setTurn(boolean b){
        turn =b;
    }
    public void plusSettlers(Intersection i){
        numOfSettlers++;
        if (!settlerIntersections.contains(i)) {
            settlerIntersections.add(i);
        }
    }
    public void minusSettlers(){
        numOfSettlers--;
    }
    public void plusTrains(Rail i){
        numOfTrains++;
        if (!trainRails.contains(i)) {
            trainRails.add(i);
        }
    }
    public void minusTrains(){
        numOfTrains--;
    }
    public int getNumOfTrains(){
        return numOfTrains;
    }
    public void removeIntersection(Intersection i){    
        settlerIntersections.remove(i);
    }
    public int getSettlers(){
        return numOfSettlers;
    }
    public ArrayList<Intersection> getSettlerIntersections(){
        return settlerIntersections;
    }
    public ArrayList<Rail> getTrainRails(){
        return trainRails;
    }
    public int getMaxNumOfRails() {
        return MAX_NUM_OF_RAILS;
    }
    public boolean hasWon() {
        if (goods[0].getNumberOfGoodsUndelivered() == 0) {
            return true;
        }
        return false;
    }
    public boolean getPaidRailRed(){
        return paidRailRed;
    }
    public boolean getPaidRailGreen() {
        return paidRailGreen;
    }
    public boolean getPaidRailWhite() {
        return paidRailWhite;
    }
    public boolean getPaidRailOrange() {
        return paidRailOrange;
    }
    public void setPaidRailRed(boolean b){
        paidRailRed=b;
    }
    public void setPaidRailGreen(boolean b){
        paidRailGreen=b;
    }
    public void setPaidRailWhite(boolean b){
        paidRailWhite=b;
    }
    public void setPaidRailOrange(boolean b){
        paidRailOrange=b;
    }
    public boolean getPlayedDPCardThisTurn(){
        return playedDPCardThisTurn;
    }
    public void setPlayedDPCardThisTurn(boolean b){
        playedDPCardThisTurn=b;
    }
    public void setMovingFromRail(Rail r){
        movingFromRail=r;
    }
    public void setMovingToRail(Rail r){
        movingToRail=r;
    }
    public Rail getMovingFromRail(){
        return movingFromRail;
    }
    public Rail getMovingToRail(){
        return movingToRail;
    }
    public void resetMovingRails(){
        movingFromRail=null;
        movingToRail=null;
    }
    public void extraordinaryBuildingPhase() {
        game_Frame.developmentCardNotVisible();
        game_Frame.disableMenu();
        buildWindow bw = new buildWindow(this, game_Frame, true);
        bw.setVisible(true);
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
    public final BufferedImage makeColorTransparent(String ref, java.awt.Color c) {//<editor-fold>
        BufferedImage image = loadImage(ref);
        BufferedImage dimg = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);  

    Graphics2D g = dimg.createGraphics();  
    g.setComposite(AlphaComposite.Src);  
    g.drawImage(image, null, 0, 0);  
    g.dispose();  
    for(int i = 0; i < dimg.getHeight(); i++) {  
        for(int j = 0; j < dimg.getWidth(); j++) {  
            if(dimg.getRGB(j, i) == c.getRGB()) {  
            dimg.setRGB(j, i, 0x8F1C1C);  
            }  
        }  
    }  
    return dimg;  
}//</editor-fold>
}

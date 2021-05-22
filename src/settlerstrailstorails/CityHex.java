
package settlerstrailstorails;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

final class CityHex{
    private Player owns;
    private String name;
    private Hex h, h2, h3;
    private int goldWorth;
    private boolean purpleCity,blockedByRobber,beenDelivered=false,isolated=true;
    private Player playerWhoDelivered;
    private javax.swing.JButton cityButton;
    private javax.swing.JLabel goods,nameLabel;
    private boolean doubleGold;
    private Rail upRail, horizontalRail,downRail;
    private Intersection intersection;
    private gameFrame game_Frame;
    private final CityHex thisC=this;
    private MouseAdapter m = new MouseAdapter(){
            @Override
            public void mouseMoved(MouseEvent evt) {
            }
            @Override
            public void mouseEntered(MouseEvent evt) {
            }
            @Override
            public void mouseExited(MouseEvent evt){
            }
            @Override
        public void mouseReleased(MouseEvent evt) {
            if (!game_Frame.getDisableCityButton()) {
                if (game_Frame.getBuildingSettlement()) {
                    if (game_Frame.getExtraordinaryBuildingPhaseNum() == 0) {
                        if (!game_Frame.getFourthPlayerCityBuild()) {
                            if (!buildSettlement(game_Frame.getCurrPlayer(),
                                    game_Frame.getSingleQHexes(),
                                    game_Frame.getIgnorePurpleDuringBuild())) {
                                game_Frame.setSCD(new SettlersConfirmDialog(game_Frame, 3, null));
                                game_Frame.getSCD().setVisible(true);
                                return;
                            } else {
                                if (game_Frame.getCurrPlayer().getNumberOfCities() == 3) {
                                    game_Frame.getCurrPlayer().getResourcesThirdCity();
                                }
                                game_Frame.setBuildingSettlement(false);
                                game_Frame.getTrashBinButton().setVisible(false);
                                game_Frame.setupPhaseCityGuide();
                            }
                        } else {
                            if (!buildGhostSettlement(game_Frame.getPlayers()[3],
                                    game_Frame.getSingleQHexes(),
                                    game_Frame.getIgnorePurpleDuringBuild())) {
                                game_Frame.setSCD(new SettlersConfirmDialog(game_Frame, 3, null));
                                game_Frame.getSCD().setVisible(true);
                                return;
                            } else {
                                game_Frame.setFourthPlayerCities(game_Frame.getFourthPlayerCities() + 1);
                                game_Frame.setBuildingSettlement(false);
                                game_Frame.getTrashBinButton().setVisible(false);
                                game_Frame.setupPhaseCityGuide();
                            }
                        }
                    }
                    game_Frame.getCIW().changeCity(thisC);
                    game_Frame.getCIW().setVisible(true);
                }
                if (game_Frame.getExtraordinaryBuildingPhaseNum() == 0) {
                    game_Frame.getCIW().changeCity(thisC);
                    game_Frame.getCIW().setVisible(true);
                }
            }
        }
    };
    
    CityHex(Player p, String n, Hex h,javax.swing.JButton cityButton,boolean dG,gameFrame g){
        name=n;
        owns=p;
        this.h=h;
        doubleGold=dG;
        setCity(n);
        game_Frame=g;
        this.cityButton=cityButton;
        this.cityButton.addMouseListener(m);
    }
    CityHex(Player p, String n, Hex h, Hex h2,javax.swing.JButton cityButton, boolean dG,gameFrame g){
        name=n;
        owns=p;
        this.h=h;
        this.h2=h2;
        doubleGold=dG;
        setCity(n);
        game_Frame=g;
        this.cityButton=cityButton;
        this.cityButton.addMouseListener(m);
    }
    CityHex(Player p, String n, Hex h, Hex h2, Hex h3,javax.swing.JButton cityButton, boolean dG,gameFrame g){
        name=n;
        owns=p;
        this.h=h;
        this.h2=h2;
        this.h3=h3;
        doubleGold=dG;
        setCity(n);
        game_Frame=g;
        this.cityButton=cityButton;
        this.cityButton.addMouseListener(m);
    }
    String saveCity(){
        String retS="";
        if(owns!=null){
            retS+="|owns"+owns.getColor();
        }else{
            retS+="|ownsNULL";
        }
        retS+="|name" + name;
        if (h != null) {
            retS += "|h" + h.getNumber();
        } else {
            retS += "|hNULL";
        }        
        if (h2 != null) {
            retS += "|h2" + h2.getNumber();
        } else {
            retS += "|h2NULL";
        }
        if (h3 != null) {
            retS += "|h3" + h3.getNumber();
        } else {
            retS += "|h3NULL";
        }
        retS+="|goldWorth"+goldWorth;
        retS+="|purpleCity"+purpleCity;
        retS+="|blockedByRobber"+blockedByRobber;
        retS+="|beenDelivered"+beenDelivered;
        retS+="|isolated"+isolated;
        if(playerWhoDelivered != null) {
            retS += "|playerWhoDelivered" + playerWhoDelivered.getColor();
        } else {
            retS += "|playerWhoDeliveredNULL";
        }
        retS+="|doubleGold"+doubleGold;        
        return retS;
    }
    void setRails(Rail upR, Rail hR, Rail dR){
        upRail=upR;
        horizontalRail=hR;
        downRail=dR;
    }
    void setOwner(Player p){
        owns=p;
    }
    Player getOwner(){
        return owns;
    }
    void setName(String s){
        name=s;
    }
    String getName(){
        return name;
    }
    Hex getHexOne(){
        return h;
    }
    Hex getHexTwo(){
        return h2;
    }
    Hex getHexThree(){
        return h3;
    }
    int getGoldWorth(){
        return goldWorth;
    }
    void setCity(String c){
        if(c.equals("Minneapolis") || c.equals("Little Rock") || 
                c.equals("Shreveport") || c.equals("Houston") || 
                c.equals("New Orleans") || c.equals("Jackson") || 
                c.equals("St. Louis") || c.equals("Chicago") || 
                c.equals("Indianapolis") || c.equals("Nashville") || 
                c.equals("Columbus") || c.equals("Lexington") || 
                c.equals("Atlanta") || c.equals("Pensacola") || 
                c.equals("Jacksonville") || c.equals("Columbia") || 
                c.equals("Raleigh") || c.equals("Washington D.C.") || 
                c.equals("Albany") || c.equals("New York") || c.equals("Boston")){
            purpleCity=true;
        }else{
            purpleCity=false;
        }
        if(c.equals("Seattle")||c.equals("Portland")||c.equals("San Francisco")
                ||c.equals("Los Angeles")){
            goldWorth=3;
        }else if(c.equals("Houston")||c.equals("New Orleans")||
                c.equals("Jacksonville")||c.equals("New York")||c.equals("Boston")){
            goldWorth=2;
        }else if(c.equals("Pensacola")||c.equals("Columbia")){
            goldWorth=1;
        }else{
            goldWorth=0;
        }
        if(doubleGold){
            goldWorth*=2;            
        }
        getBlockedByRobber();
    }
    boolean isIsolated(){
        return isolated;
    }
    void setIsolated(boolean b){
        isolated=b;
    }
    JButton getCityButton(){
        return cityButton;
    }
    /**
     * sets blocked by robber for this city
     * and returns that value
     */
    boolean getBlockedByRobber(){
        if(h!=null && h.getResource()!=Hex.Resource.DESERT && h.getChit()!=0 && h.getRobber()){
            blockedByRobber=true;
        }else if(h2!=null && h2.getResource()!=Hex.Resource.DESERT && h2.getChit()!=0&& h2.getRobber()){
            blockedByRobber=true;
        }else if(h3!=null && h3.getResource()!=Hex.Resource.DESERT && h3.getChit()!=0&& h3.getRobber()){
            blockedByRobber=true;
        }else{
            blockedByRobber=false;
        }
        return blockedByRobber;
    }
    boolean getBeenDelivered(){
        return beenDelivered;
    }
    Player getPlayerDelivered(){
        return playerWhoDelivered;
    }
    /**
     * returns false if delivery not successful
     * @param p
     * @param jp
     * @return
     */
    boolean deliverGoods(Player p, JPanel jp){
        if(owns!=null && owns.getColor()!=p.getColor() && p.canDeliver() &&
                !beenDelivered){
        playerWhoDelivered=p;
        beenDelivered = true;
        goods = p.deliverGood();
        if(name.equals("Seattle")||name.equals("Portland")||name.equals("San Francisco")||name.equals("Reno")
                ||name.equals("Boise")||name.equals("Spokane")||name.equals("Helena")||
                name.equals("Idaho Falls")||name.equals("Salt Lake City")||
                name.equals("Denver")||name.equals("Santa Fe")||name.equals("Amarillo")||
                name.equals("Dallas")||name.equals("San Antonio")){
        jp.add(goods, new org.netbeans.lib.awtextra.AbsoluteConstraints(
                    cityButton.getX()+1, cityButton.getY()+19, -1, -1));
        }else if(name.equals("Los Angeles")||name.equals("Las Vegas")||name.equals("Flagstaff")||name.equals("El Paso")||
                name.equals("Casper")||name.equals("Billings")||name.equals("Rapid City")||name.equals("Bismark")||
                name.equals("Omaha")||name.equals("Wichita")){
        jp.add(goods, new org.netbeans.lib.awtextra.AbsoluteConstraints(
                    cityButton.getX()-3, cityButton.getY()+11, -1, -1));
        }else if(name.equals("Minneapolis")||name.equals("St. Louis")||name.equals("New Orleans")||name.equals("Nashville")||
                name.equals("Indianapolis")||name.equals("Chicago")||name.equals("Pensacola")||name.equals("Atlanta")||
                name.equals("Lexington")||name.equals("Columbus")){            
        jp.add(goods, new org.netbeans.lib.awtextra.AbsoluteConstraints(
                    cityButton.getX()+21, cityButton.getY()+11, -1, -1));
        }else if(name.equals("Houston")||name.equals("Shreveport")||name.equals("Little Rock")||name.equals("Jackson")
                ||name.equals("Jacksonville")||name.equals("Columbia")||name.equals("Raleigh")||
                name.equals("Washington D.C.")||name.equals("Albany")||
                name.equals("New York")||name.equals("Boston")||name.equals("Amarillo")||
                name.equals("Dallas")||name.equals("San Antonio")){
        jp.add(goods, new org.netbeans.lib.awtextra.AbsoluteConstraints(
                    cityButton.getX()+17, cityButton.getY()+19, -1, -1));
        }
        jp.setVisible(false);
        jp.setVisible(true);
        gameFrame.addToInfo(p.getName() + " delivered to "+name, false);
        return true;
        }
        return false;
    }
    /**
     * returns the resource of the adjacent hex that has n as a chit
     * @return 
     */
    Hex.Resource[] cityAdjacentToNumber(int n){
        Hex.Resource[] returnResource = new Hex.Resource[3];
        if(h!=null && h.getChit()==n && !h.getRobber()){
            if(returnResource[0]==null){
                returnResource[0]=h.getResource();
            }else if(returnResource[1]==null){
                returnResource[1]=h.getResource();
            }else if(returnResource[2]==null){
                returnResource[2]=h.getResource();
            }
        }
        if(h2!=null && h2.getChit()==n && !h2.getRobber()){
            if(returnResource[0]==null){
                returnResource[0]=h2.getResource();
            }else if(returnResource[1]==null){
                returnResource[1]=h2.getResource();
            }else if(returnResource[2]==null){
                returnResource[2]=h2.getResource();
            }
        }
        if(h3!=null && h3.getChit()==n && !h3.getRobber()){
            if(returnResource[0]==null){
                returnResource[0]=h3.getResource();
            }else if(returnResource[1]==null){
                returnResource[1]=h3.getResource();
            }else if(returnResource[2]==null){
                returnResource[2]=h3.getResource();
            }
        }
        return returnResource;
    }
    boolean buildSettlement(Player p, ArrayList<Hex> chits,boolean ignorePC) {
        if (!p.maxNumberOfCitiesBuilt() && !this.isOwned()) {
            //if it's the setup phase and the city is not a purple
            //city space
            if(p.getNumberOfCities()<3&&!purpleCity&&!ignorePC){
                return false;
            }
            if(p.buildSettlement(this)) {
                if (p.getColor() == Player.Color.RED) {
                    cityButton.setIcon(new ImageIcon(
                            makeColorTransparent(
                            "/settlerstrailstorails/resources/redHouse.png", Color.WHITE)));
                } else if (p.getColor() == Player.Color.GREEN) {
                    cityButton.setIcon(new ImageIcon(
                            makeColorTransparent(
                            "/settlerstrailstorails/resources/greenHouse.png", Color.WHITE)));
                } else if (p.getColor() == Player.Color.WHITE) {
                    cityButton.setIcon(new ImageIcon(
                            makeColorTransparent(
                            "/settlerstrailstorails/resources/whiteHouse.png", Color.WHITE)));
                } else if (p.getColor() == Player.Color.ORANGE) {
                    cityButton.setIcon(new ImageIcon(
                            makeColorTransparent(
                            "/settlerstrailstorails/resources/orangeHouse.png", Color.WHITE)));
                }
                owns = p;
                if (h!=null&&h.getChit()==0&&h.isDoubleQ()&&!chits.isEmpty()) {
                    h.setChit(chits.get(0).getChit());
                    chits.remove(0).setChit(0);
                }else if (h2!=null&&h2.getChit()==0&&h2.isDoubleQ()&&!chits.isEmpty()) {
                    h2.setChit(chits.get(0).getChit());
                    chits.remove(0).setChit(0);
                }else if (h3!=null&&h3.getChit()==0&&h3.isDoubleQ()&&!chits.isEmpty()) {
                    h3.setChit(chits.get(0).getChit());
                    chits.remove(0).setChit(0);
                }
                
                //calculating probabilities and adding it
                //to already existing probabilities
                boolean[] lumberProbNums=p.getLumberProbNums();
                boolean[] oreProbNums=p.getOreProbNums();
                boolean[] wheatProbNums=p.getWheatProbNums();
                boolean[] cattleProbNums=p.getCattleProbNums();
                boolean[] coalProbNums=p.getCoalProbNums();   
                
                if(getHexOne()!=null){
                    if(getHexOne().getResource()==Hex.Resource.LUMBER&&
                            getHexOne().getChit()!=0&&
                            !lumberProbNums[getHexOne().getChit()]){
                        lumberProbNums[getHexOne().getChit()]=true;
                        p.setLumberProb(p.getLumberProb()+getHexOne().getProbability());
                    }else if(getHexOne().getResource()==Hex.Resource.COAL&&
                            getHexOne().getChit()!=0&&
                            !coalProbNums[getHexOne().getChit()]){
                        coalProbNums[getHexOne().getChit()]=true;
                        p.setCoalProb(p.getCoalProb()+getHexOne().getProbability());                        
                    }else if(getHexOne().getResource()==Hex.Resource.CATTLE&&
                            getHexOne().getChit()!=0&&
                            !cattleProbNums[getHexOne().getChit()]){
                        cattleProbNums[getHexOne().getChit()]=true;
                        p.setCattleProb(p.getCattleProb()+getHexOne().getProbability());                        
                    }else if(getHexOne().getResource()==Hex.Resource.WHEAT&&
                            getHexOne().getChit()!=0&&
                            !wheatProbNums[getHexOne().getChit()]){
                        wheatProbNums[getHexOne().getChit()]=true;
                        p.setWheatProb(p.getWheatProb()+getHexOne().getProbability());                        
                    }else if(getHexOne().getResource()==Hex.Resource.ORE&&
                            getHexOne().getChit()!=0&&
                            !oreProbNums[getHexOne().getChit()]){
                        oreProbNums[getHexOne().getChit()]=true;
                        p.setOreProb(p.getOreProb()+getHexOne().getProbability());                        
                    }
                }
                if(getHexTwo()!=null){
                    if(getHexTwo().getResource()==Hex.Resource.LUMBER&&
                            getHexTwo().getChit()!=0&&
                            !lumberProbNums[getHexTwo().getChit()]){
                        lumberProbNums[getHexTwo().getChit()]=true;
                        p.setLumberProb(p.getLumberProb()+getHexTwo().getProbability());
                    }else if(getHexTwo().getResource()==Hex.Resource.COAL&&
                            getHexTwo().getChit()!=0&&
                            !coalProbNums[getHexTwo().getChit()]){
                        coalProbNums[getHexTwo().getChit()]=true;
                        p.setCoalProb(p.getCoalProb()+getHexTwo().getProbability());                        
                    }else if(getHexTwo().getResource()==Hex.Resource.CATTLE&&
                            getHexTwo().getChit()!=0&&
                            !cattleProbNums[getHexTwo().getChit()]){
                        cattleProbNums[getHexTwo().getChit()]=true;
                        p.setCattleProb(p.getCattleProb()+getHexTwo().getProbability());                        
                    }else if(getHexTwo().getResource()==Hex.Resource.WHEAT&&
                            getHexTwo().getChit()!=0&&
                            !wheatProbNums[getHexTwo().getChit()]){
                        wheatProbNums[getHexTwo().getChit()]=true;
                        p.setWheatProb(p.getWheatProb()+getHexTwo().getProbability());                        
                    }else if(getHexTwo().getResource()==Hex.Resource.ORE&&
                            getHexTwo().getChit()!=0&&
                            !oreProbNums[getHexTwo().getChit()]){
                        oreProbNums[getHexTwo().getChit()]=true;
                        p.setOreProb(p.getOreProb()+getHexTwo().getProbability());                        
                    }
                }
                if(getHexThree()!=null){
                    if(getHexThree().getResource()==Hex.Resource.LUMBER&&
                            getHexThree().getChit()!=0&&
                            !lumberProbNums[getHexThree().getChit()]){
                        lumberProbNums[getHexThree().getChit()]=true;
                        p.setLumberProb(p.getLumberProb()+getHexThree().getProbability());
                    }else if(getHexThree().getResource()==Hex.Resource.COAL&&
                            getHexThree().getChit()!=0&&
                            !coalProbNums[getHexThree().getChit()]){
                        coalProbNums[getHexThree().getChit()]=true;
                        p.setCoalProb(p.getCoalProb()+getHexThree().getProbability());                        
                    }else if(getHexThree().getResource()==Hex.Resource.CATTLE&&
                            getHexThree().getChit()!=0&&
                            !cattleProbNums[getHexThree().getChit()]){
                        cattleProbNums[getHexThree().getChit()]=true;
                        p.setCattleProb(p.getCattleProb()+getHexThree().getProbability());                        
                    }else if(getHexThree().getResource()==Hex.Resource.WHEAT&&
                            getHexThree().getChit()!=0&&
                            !wheatProbNums[getHexThree().getChit()]){
                        wheatProbNums[getHexThree().getChit()]=true;
                        p.setWheatProb(p.getWheatProb()+getHexThree().getProbability());                        
                    }else if(getHexThree().getResource()==Hex.Resource.ORE&&
                            getHexThree().getChit()!=0&&
                            !oreProbNums[getHexThree().getChit()]){
                        oreProbNums[getHexThree().getChit()]=true;
                        p.setOreProb(p.getOreProb()+getHexThree().getProbability());                        
                    }
                }
                p.setAnyProbNums();
                getBlockedByRobber();
                p.getBlockedByRobber();
                if(p.isTurn()&&goldWorth!=0){
                    gameFrame.playSound("ching");
                }else{
                    gameFrame.playSound("click");
                }
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    public boolean buildGhostSettlement(Player p, ArrayList<Hex> chits, boolean ignorePC){
        if (!this.isOwned()) {
            //if it's the setup phase and the city is not a purple
            //city space
            if(p.getNumberOfCities()<3&&!purpleCity&&!ignorePC){
                return false;
            }
            if(p.buildGhostSettlement(this)){
                if (p.getColor() == Player.Color.RED) {
                    cityButton.setIcon(new ImageIcon(
                            makeColorTransparent(
                            "/settlerstrailstorails/resources/redHouse.png", Color.WHITE)));
                } else if (p.getColor() == Player.Color.GREEN) {
                    cityButton.setIcon(new ImageIcon(
                            makeColorTransparent(
                            "/settlerstrailstorails/resources/greenHouse.png", Color.WHITE)));                    
                }else if(p.getColor() == Player.Color.WHITE){
                    cityButton.setIcon(new ImageIcon(
                            makeColorTransparent(
                            "/settlerstrailstorails/resources/whiteHouse.png", Color.WHITE)));                    
                }else if(p.getColor() == Player.Color.ORANGE){
                    cityButton.setIcon(new ImageIcon(
                            makeColorTransparent(
                            "/settlerstrailstorails/resources/orangeHouse.png", Color.WHITE)));                    
                }
                owns = p;
                if (h!=null&&h.getChit()==0&&h.isDoubleQ()&&!chits.isEmpty()) {
                    h.setChit(chits.get(0).getChit());
                    chits.remove(0).setChit(0);
                }else if (h2!=null&&h2.getChit()==0&&h2.isDoubleQ()&&!chits.isEmpty()) {
                    h2.setChit(chits.get(0).getChit());
                    chits.remove(0).setChit(0);
                }else if (h3!=null&&h3.getChit()==0&&h3.isDoubleQ()&&!chits.isEmpty()) {
                    h3.setChit(chits.get(0).getChit());
                    chits.remove(0).setChit(0);
                }
                return true;
            }else{
                return false;
            }        
        }else{
            return false;
        }
    }
    public boolean isOwned(){
        return owns==null ? false : true;
    }
    public void setDoubleGold(boolean b){
        if(!doubleGold && b){
            goldWorth*=2;
        }else if(doubleGold && !b){
            goldWorth/=2;
        }
        doubleGold=b;
    }
    public void displayNameLabel(JPanel p){
        nameLabel=new JLabel();
        nameLabel.setOpaque(true);
        nameLabel.setText(name);
        nameLabel.setBackground(Color.WHITE);
        nameLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        nameLabel.setSize(10+(5*name.length()), 16);
        if(name.equals("San Francisco")){            
        p.add(nameLabel,new org.netbeans.lib.awtextra.AbsoluteConstraints(
                    cityButton.getX(), 
                (cityButton.getY()+(cityButton.getHeight()/2))-(nameLabel.getHeight()/2)));
        }else if(name.equals("Portland")){            
        p.add(nameLabel,new org.netbeans.lib.awtextra.AbsoluteConstraints(
                    (cityButton.getX()+(cityButton.getWidth()/2))-(nameLabel.getWidth()/2)+10, 
                (cityButton.getY()+(cityButton.getHeight()/2))-(nameLabel.getHeight()/2)));
        }else{
        p.add(nameLabel,new org.netbeans.lib.awtextra.AbsoluteConstraints(
                    (cityButton.getX()+(cityButton.getWidth()/2))-(nameLabel.getWidth()/2), 
                (cityButton.getY()+(cityButton.getHeight()/2))-(nameLabel.getHeight()/2)));
        } 
    }
    public void notDisplayNameLabel(){
        nameLabel.setVisible(false);
        nameLabel=null;
    }
    public double getProbabilityOfResource(Hex.Resource r){
        double returnVal=0;
        if(h.getResource()==r){
            returnVal+=h.getProbability();
        }
        if(h2!=null && h2.getResource()==r){
            returnVal+=h2.getProbability();
        }
        if(h3!=null && h3.getResource()==r){
            returnVal+=h3.getProbability();
        }
        return returnVal;
    }
    public boolean getPurpleCity(){
        return purpleCity;
    }
    public Rail getUpRail(){
        return upRail;
    }
    public Rail getHorRail(){
        return horizontalRail;
    }
    public Rail getDownRail(){
        return downRail;
    }
    public void displayAdjacentGhostRails(){
        if(upRail!=null && !upRail.isBuilt()){
            upRail.buildRail(null);
        }
        if(horizontalRail!=null && !horizontalRail.isBuilt()){
            horizontalRail.buildRail(null);
        }
        if(downRail!=null && !downRail.isBuilt()){
            downRail.buildRail(null);
        }
    }
    public void demolishAdjacentGhostRails(){
        if(upRail!=null && upRail.isGhost()){
            upRail.demolishRail();
        }
        if(horizontalRail!=null && horizontalRail.isGhost()){
            horizontalRail.demolishRail();
        }
        if(downRail!=null && downRail.isGhost()){
            downRail.demolishRail();
        }
    }
    public void setIntersection(Intersection i){
        intersection=i;
    }
    public Intersection getIntersection(){
        return intersection;
    }
    /**
     * returns null if the city can't be delivered
     * returns the rails that contains the train if it can be delivered
     * @return 
     */
    public final BufferedImage loadImage(String ref) {
        BufferedImage bimg = null;
        try {
            bimg = ImageIO.read(getClass().getResource(ref));
        } catch (Exception e) {
            System.err.print(e);
        }
        return bimg;
    }
    public final BufferedImage makeColorTransparent(String ref, Color color) {
        BufferedImage image = loadImage(ref);
        BufferedImage dimg = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = dimg.createGraphics();
        g.setComposite(AlphaComposite.Src);
        g.drawImage(image, null, 0, 0);
        g.dispose();
        for (int i = 0; i < dimg.getHeight(); i++) {
            for (int j = 0; j < dimg.getWidth(); j++) {
                if (dimg.getRGB(j, i) == color.getRGB()) {
                    dimg.setRGB(j, i, 0x8F1C1C);
                }
            }
        }
        return dimg;
    }  
}
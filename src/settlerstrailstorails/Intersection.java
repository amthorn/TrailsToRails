/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package settlerstrailstorails;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author User 1
 */
public class Intersection {
    
    private boolean hasSettler, buildingSettler,hasGhostSettler,moveSettler,
            noMoveToCity,onTheCusp, mouseOver,playingDevelopmentCard;
    private CityHex city;
    private Intersection upIntersection, downIntersection, horIntersection;
    private JLabel intersectionLabel;
    private ImageIcon whiteSettler, greenSettler, redSettler, orangeSettler, 
            graySettler;
    private int testDistance=-1;
    private JButton intersectionButton;
    private Player currPlayer;
    private gameFrame game_Frame;
    private ArrayList<Hex> singleQHexes = new ArrayList<Hex>();
    
    Intersection(Intersection upIntersection,Intersection downIntersection,
            Intersection horIntersection,CityHex city){
        this.upIntersection=upIntersection;
        this.downIntersection=downIntersection;
        this.horIntersection=horIntersection;
        this.city=city;
    }
    Intersection(JLabel l, gameFrame g, ArrayList<Hex> sQ){
        singleQHexes=sQ;
        intersectionLabel=l;        
        orangeSettler=new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/orangeSettler.png", Color.WHITE));
        redSettler=new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/redSettler.png", Color.WHITE));
        whiteSettler=new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/whiteSettler.png", Color.WHITE));
        greenSettler=new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/greenSettler.png", Color.WHITE));
        graySettler=new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/graySettler.png", Color.WHITE));
        game_Frame=g;
    }
    Intersection(CityHex c, JLabel l, gameFrame g, ArrayList<Hex> sQ){
        singleQHexes=sQ;
        orangeSettler=new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/orangeSettler.png", Color.WHITE));
        redSettler=new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/redSettler.png", Color.WHITE));
        whiteSettler=new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/whiteSettler.png", Color.WHITE));
        greenSettler=new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/greenSettler.png", Color.WHITE));
        graySettler=new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/graySettler.png", Color.WHITE));
        city=c;
        intersectionLabel=l;
        game_Frame=g;
    }
    void setButton(JButton b,JPanel jP){
        intersectionButton=b;
        intersectionButton.setVisible(false);
        intersectionButton.setOpaque(false);
        intersectionButton.setBorderPainted(false);
        intersectionButton.setContentAreaFilled(false);
        intersectionButton.setFocusPainted(false);
        intersectionButton.setFocusable(false);
        intersectionButton.setPreferredSize(new Dimension(30, 30));
        final Intersection l = this;
        intersectionButton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseEntered(MouseEvent evt){
                if(!mouseOver&&hasGhostSettler){
                    mouseOver = true;
                    if (currPlayer.getColor() == Player.Color.RED) {
                        intersectionLabel.setIcon(redSettler);
                    } else if (currPlayer.getColor() == Player.Color.GREEN) {
                        intersectionLabel.setIcon(greenSettler);
                    } else if (currPlayer.getColor() == Player.Color.WHITE) {
                        intersectionLabel.setIcon(whiteSettler);
                    } else if (currPlayer.getColor() == Player.Color.ORANGE) {
                        intersectionLabel.setIcon(orangeSettler);
                    }
                }
            }
            @Override
            public void mouseExited(MouseEvent evt) {
                if (mouseOver&&hasGhostSettler) {
                    intersectionLabel.setIcon(graySettler);
                    mouseOver=false;
                }
            }
            @Override
            public void mouseReleased(MouseEvent evt) {
                if(buildingSettler) {
                    if (currPlayer.getColor() == Player.Color.RED) {
                        hasGhostSettler=false;
                        intersectionLabel.setIcon(redSettler);
                        currPlayer.plusSettlers(l);
                        setOnTheCusp(false);
                        setOnTheCuspAll();
                        if (!moveSettler) {
                            gameFrame.addToInfo(currPlayer + " built a settler in " + city.getName(), false);
                        } else {
                            gameFrame.addToInfo(currPlayer + " moved his settler.", false);
                            setMoveSettler(false);
                        }
                    } else if (currPlayer.getColor() == Player.Color.GREEN) {
                        hasGhostSettler=false;
                        intersectionLabel.setIcon(greenSettler);
                        currPlayer.plusSettlers(l);
                        setOnTheCusp(false);
                        setOnTheCuspAll();
                        if (!moveSettler) {
                            gameFrame.addToInfo(currPlayer + " built a settler in " + city.getName(), false);
                        } else {
                            gameFrame.addToInfo(currPlayer + " moved his settler.", false);
                            setMoveSettler(false);
                        }
                    } else if (currPlayer.getColor() == Player.Color.WHITE) {
                        hasGhostSettler=false;
                        intersectionLabel.setIcon(whiteSettler);
                        currPlayer.plusSettlers(l);
                        setOnTheCusp(false);
                        setOnTheCuspAll();
                        if (!moveSettler) {
                            gameFrame.addToInfo(currPlayer + " built a settler in " + city.getName(), false);
                        } else {
                            gameFrame.addToInfo(currPlayer + " moved his settler.", false);
                            setMoveSettler(false);
                        }
                    } else if (currPlayer.getColor() == Player.Color.ORANGE) {
                        hasGhostSettler=false;
                        intersectionLabel.setIcon(orangeSettler);
                        currPlayer.plusSettlers(l);
                        setOnTheCusp(false);
                        setOnTheCuspAll();
                        if (!moveSettler) {
                            gameFrame.addToInfo(currPlayer + " built a settler in " + city.getName(), false);
                        } else {
                            gameFrame.addToInfo(currPlayer + " moved his settler.", false);
                            setMoveSettler(false);
                        }
                    } else {
                        intersectionLabel.setIcon(graySettler);
                        hasGhostSettler = true;
                    }
                    hasSettler = true;
                    buildCity();
                    getButton().setBorderPainted(false);
                    game_Frame.buildingSettler(false, false);
                    setAllCityBuildSettler(false, currPlayer);
                    if(playingDevelopmentCard){
                        game_Frame.getCurrPlayer().minusDPCard(3);
                        gameFrame.addToInfo(game_Frame.getCurrPlayer()+" played Scout card",false);
                        game_Frame.enableMenu();
                        game_Frame.getCurrPlayer().setPlayedDPCardThisTurn(true);
                        playingDevelopmentCard = false;
                        Intersection[] in = game_Frame.getIntersections();
                        for (int i = 0; i < in.length; i++) {
                            in[i].setPlayingDevelopmentCard(false);
                        }
                    }
                }else if(moveSettler){
                    if(!noMoveToCity){
                        setAllIntersectionsInvisible();
                        removeSettler();
                        setSettler(true);
                        currPlayer.minusSettlers();
                        gameFrame.addToInfo("Select the city to which you'd like to move.",true);
                        onTheCusp=true;
                        setAllCityBuildSettler(true,currPlayer);
                        intersectionButton.setVisible(false);
                    }else{
                        setAllIntersectionsInvisible();
                        setNoMoveToCity(false);
                        gameFrame.addToInfo("Select the intersection to which you'd like to move.",true);
                        if(!playingDevelopmentCard){
                            setAllBuildSettlerWithin(true, 3,currPlayer);
                        }else{
                            setAllBuildSettlerWithin(true, 5,currPlayer);                            
                        }
                        removeSettler();
                        onTheCusp=true;
                        currPlayer.minusSettlers();
                        intersectionButton.setVisible(false);
                    }
                }
            }
        });
        if(city==null){
        jP.add(intersectionButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(
                intersectionLabel.getX()-9, intersectionLabel.getY()-6));
        }else{
            jP.add(intersectionButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(
                city.getCityButton().getX()-2, city.getCityButton().getY()));            
        }
    }
    String saveIntersection(){
        String retS="--Intersection--";
        retS+="|hasSettler"+hasSettler;
        retS+="|buildingSettler"+buildingSettler;
        if (intersectionLabel.getIcon() == redSettler) {
            retS += "|settlerColorRed";                
        }else if (intersectionLabel.getIcon() == greenSettler) {
            retS += "|settlerColorGreen";                
        }else if (intersectionLabel.getIcon() == whiteSettler) {
            retS += "|settlerColorWhite";                
        }else if (intersectionLabel.getIcon() == orangeSettler) {
            retS += "|settlerColorOrange";                
        }else{
            retS += "|settlerColorNULL";                
        }
        retS+="|hasGhostSettler"+hasGhostSettler;
        retS+="|moveSettler"+moveSettler;
        retS+="|noMoveToCity"+noMoveToCity;
        retS+="|onTheCusp"+onTheCusp;
        retS+="|mouseOver"+mouseOver;
        retS+="|playingDevelopmentCard"+playingDevelopmentCard;
        retS+="|testDistance"+testDistance;
        retS+="--end Intersection--";
        return retS;
    }
    CityHex getCity(){
        return city;
    }
    boolean hasSettler(){
        return hasSettler;
    }
    void setSettler(boolean b){
        hasSettler=b;
    }
    Intersection getUpIntersection(){
        return upIntersection;
    }
    Intersection getDownIntersection(){
        return downIntersection;
    }
    Intersection getHorIntersection(){
        return horIntersection;
    }
    void setUpIntersection(Intersection i){
        upIntersection=i;
    }
    void setDownIntersection(Intersection i){
        downIntersection=i;
    }
    void setHorIntersection(Intersection i){
        horIntersection=i;
    }
    void setCity(CityHex c){
        city=c;
    }
    JLabel getLabel(){
        return intersectionLabel;
    }
    JButton getButton(){
        return intersectionButton;
    }
    void setBuildSettler(boolean b){
        buildingSettler=b;
    }
    boolean getBuildSettler(){
        return buildingSettler;
    }
    int getTestDistance(){
        return testDistance;
    }
    void setTestDistance(int i){
        testDistance=i;
    }
    void buildSettler(Player p){
        setBuildSettler(true);
        currPlayer=p;
    }
    void buildGhostSettler() {
        intersectionLabel.setIcon(graySettler);
        hasGhostSettler = true;
        hasSettler=true;
    }
    void removeSettler(){
        intersectionLabel.setIcon(null);
        hasGhostSettler=false;
        hasSettler=false;
        setBuildSettler(false);
    }
    void displaySettler(Player p){
        if(p.getColor()==Player.Color.RED){
            intersectionLabel.setIcon(redSettler);
        }else if(p.getColor()==Player.Color.GREEN){
            intersectionLabel.setIcon(greenSettler);
        }else if(p.getColor()==Player.Color.WHITE){
            intersectionLabel.setIcon(whiteSettler);
        }else if(p.getColor()==Player.Color.ORANGE){
            intersectionLabel.setIcon(orangeSettler);
        }
        hasGhostSettler=true;
        hasSettler=true;
    }
    boolean hasGhostSettler(){
        return hasGhostSettler;
    }
    void setGhostSettler(boolean b){
        hasGhostSettler=b;
    }
    void setPlayer(Player p){
        currPlayer=p;
    }
    /**
     * sets all intersections to ghost settlers that are < or = i intersections
     * away
     * @param i 
     */
    void setGhostSettlers(int i){
        LinkedList<Intersection> q = new LinkedList<Intersection>();
        ArrayList<Intersection> visited = new ArrayList<Intersection>();
        boolean saveHasSettler=hasSettler;
        hasSettler=true;
        q.add(this);
        boolean endLoop=false;
        while (!q.isEmpty()) {
            Intersection curr = q.pop();
            if(!curr.hasSettler && curr.getTestDistance()<=i){
                curr.buildSettler(null);
            }else if(curr.getTestDistance()>i){
                endLoop=true;
            }
            visited.add(curr);
            if (!endLoop && curr.getUpIntersection() != null && !visited.contains(curr.getUpIntersection())
                    && !q.contains(curr.getUpIntersection())) {
                q.add(curr.getUpIntersection());
                curr.getUpIntersection().setTestDistance(curr.getTestDistance()+1);
            }
            if (!endLoop && curr.getHorIntersection() != null && !visited.contains(curr.getHorIntersection())
                    && !q.contains(curr.getHorIntersection())) {
                q.add(curr.getHorIntersection());
                curr.getHorIntersection().setTestDistance(curr.getTestDistance()+1);
            }
            if (!endLoop && curr.getDownIntersection() != null && !visited.contains(curr.getDownIntersection())
                    && !q.contains(curr.getDownIntersection())) {
                q.add(curr.getDownIntersection());
                curr.getDownIntersection().setTestDistance(curr.getTestDistance()+1);
            }
        }
        hasSettler=saveHasSettler;
    }
    void setMoveSettler(boolean b){
        moveSettler=b;
    }
    boolean getMoveSettler(){
        return moveSettler;
    }
    /**
     * sets all cities that the owner P owns to a ghost settler
     * @param b
     * @param p 
     */
    void setAllCityBuildSettler(boolean b, Player p){
        currPlayer=p;
        LinkedList<Intersection> q = new LinkedList<Intersection>();
        ArrayList<Intersection> visited = new ArrayList<Intersection>();
        q.add(this);
        while (!q.isEmpty()) {
            Intersection curr = q.pop();
            if(curr!=null && curr.getButton()!=null&&curr.getCity()!=null&&
                    curr.getCity().getOwner()==p && !curr.hasSettler()&&b&&!curr.getOnTheCusp()){
                curr.setBuildSettler(b);
                curr.buildSettler(null);
                if(p.getSettlers()<2){
                    curr.getButton().setBorderPainted(false);
                }else{
                    curr.getButton().setBorderPainted(true);
                }
                curr.buildGhostSettler();
                curr.getButton().setVisible(b);
                curr.setPlayer(p);
            }else if(curr!=null && curr.getButton()!=null &&!b&&curr.hasGhostSettler()){
                curr.setBuildSettler(b);
                curr.removeSettler();
                curr.getButton().setVisible(b);
                getButton().setBorderPainted(false);
            }else if(curr!=null && curr.getButton()!=null &&!b){
                curr.setBuildSettler(b);
                curr.getButton().setVisible(b);
                getButton().setBorderPainted(false);
            }
            visited.add(curr);
            if (curr.getUpIntersection() != null && !visited.contains(curr.getUpIntersection())
                    && !q.contains(curr.getUpIntersection())) {
                q.add(curr.getUpIntersection());
            }
            if (curr.getHorIntersection() != null && !visited.contains(curr.getHorIntersection())
                    && !q.contains(curr.getHorIntersection())) {
                q.add(curr.getHorIntersection());
            }
            if (curr.getDownIntersection() != null && !visited.contains(curr.getDownIntersection())
                    && !q.contains(curr.getDownIntersection())) {
                q.add(curr.getDownIntersection());
            }
        }
    }    
    void setAllCityBuildSettlerOnlySettlerVisible(Player p){
        //<editor-fold>
        currPlayer=p;
        LinkedList<Intersection> q = new LinkedList<Intersection>();
        ArrayList<Intersection> visited = new ArrayList<Intersection>();
        q.add(this);
        while (!q.isEmpty()) {
            Intersection curr = q.pop();
            if(curr!=null && curr.getButton()!=null&&curr.getCity()!=null&&
                    curr.getCity().getOwner()==p && curr.getCity().getIntersection().hasSettler()){
                curr.setMoveSettler(true);
                curr.getButton().setVisible(true);
                curr.getButton().setBorderPainted(true);
                curr.setPlayer(p);
            }
            visited.add(curr);
            if (curr.getUpIntersection() != null && !visited.contains(curr.getUpIntersection())
                    && !q.contains(curr.getUpIntersection())) {
                q.add(curr.getUpIntersection());
            }
            if (curr.getHorIntersection() != null && !visited.contains(curr.getHorIntersection())
                    && !q.contains(curr.getHorIntersection())) {
                q.add(curr.getHorIntersection());
            }
            if (curr.getDownIntersection() != null && !visited.contains(curr.getDownIntersection())
                    && !q.contains(curr.getDownIntersection())) {
                q.add(curr.getDownIntersection());
            }
        }
    }    
    //</editor-fold>
    void setAllBuildSettlerWithin(boolean b, int distance, Player p){
        LinkedList<Intersection> q = new LinkedList<Intersection>();
        ArrayList<Intersection> visited = new ArrayList<Intersection>();
        q.add(this);
        setTestDistance(0);
        boolean endLoop=false;
        while (!q.isEmpty()) {
            Intersection curr = q.pop();
            if(curr!=null&&!curr.hasSettler && curr.getTestDistance()<=distance
                    && curr.getButton() != null) {
                if (b) {
                    curr.buildGhostSettler();
                    curr.setMoveSettler(true);
                    curr.getButton().setVisible(b);
                    curr.getButton().setBorderPainted(false);
                    curr.buildSettler(p);
                } else if (curr.hasGhostSettler()) {
                    curr.removeSettler();
                }
                
            }else if(curr.getTestDistance()>distance){
                endLoop=true;
            }
            visited.add(curr);
            if (!endLoop && curr.getUpIntersection() != null && !visited.contains(curr.getUpIntersection())
                    && !q.contains(curr.getUpIntersection())) {
                q.add(curr.getUpIntersection());
                curr.getUpIntersection().setTestDistance(curr.getTestDistance()+1);
            }
            if (!endLoop && curr.getHorIntersection() != null && !visited.contains(curr.getHorIntersection())
                    && !q.contains(curr.getHorIntersection())) {
                q.add(curr.getHorIntersection());
                curr.getHorIntersection().setTestDistance(curr.getTestDistance()+1);
            }
            if (!endLoop && curr.getDownIntersection() != null && !visited.contains(curr.getDownIntersection())
                    && !q.contains(curr.getDownIntersection())) {
                q.add(curr.getDownIntersection());
                curr.getDownIntersection().setTestDistance(curr.getTestDistance()+1);
            }
        }
        for(int i=0;i<visited.size();i++){
            if(visited.get(i)!=null){
                visited.get(i).setTestDistance(-1);
            }
        }
    }    
    void setAllIntersectionsInvisible(){
        //<editor-fold>
        LinkedList<Intersection> q = new LinkedList<Intersection>();
        ArrayList<Intersection> visited = new ArrayList<Intersection>();
        q.add(this);
        while (!q.isEmpty()) {
            Intersection curr = q.pop();
            if(curr!=null && curr.getButton()!=null){
                curr.getButton().setVisible(false);
                if(curr.getMoveSettler()){
                    curr.setMoveSettler(false);
                }
                if(curr.getBuildSettler()){
                    curr.setBuildSettler(false);
                }
            }
            visited.add(curr);
            if (curr.getUpIntersection() != null && !visited.contains(curr.getUpIntersection())
                    && !q.contains(curr.getUpIntersection())) {
                q.add(curr.getUpIntersection());
            }
            if (curr.getHorIntersection() != null && !visited.contains(curr.getHorIntersection())
                    && !q.contains(curr.getHorIntersection())) {
                q.add(curr.getHorIntersection());
            }
            if (curr.getDownIntersection() != null && !visited.contains(curr.getDownIntersection())
                    && !q.contains(curr.getDownIntersection())) {
                q.add(curr.getDownIntersection());
            }
        }
    }
    //</editor-fold>
    void setNoMoveToCity(boolean b){
        noMoveToCity=b;
    }
    boolean getNoMoveToCity(){
        return noMoveToCity;
    }
    public void setOnTheCuspAll(){
        LinkedList<Intersection> q = new LinkedList<Intersection>();
        ArrayList<Intersection> visited = new ArrayList<Intersection>();
        q.add(this);
        while (!q.isEmpty()) {
            Intersection curr = q.pop();
            if(curr!=null){
                if(currPlayer.getSettlerIntersections().contains(curr)&&
                        curr.getOnTheCusp()){
                    currPlayer.getSettlerIntersections().remove(curr);
                }
                curr.setOnTheCusp(false);
            }
            visited.add(curr);
            if (curr.getUpIntersection() != null && !visited.contains(curr.getUpIntersection())
                    && !q.contains(curr.getUpIntersection())) {
                q.add(curr.getUpIntersection());
            }
            if (curr.getHorIntersection() != null && !visited.contains(curr.getHorIntersection())
                    && !q.contains(curr.getHorIntersection())) {
                q.add(curr.getHorIntersection());
            }
            if (curr.getDownIntersection() != null && !visited.contains(curr.getDownIntersection())
                    && !q.contains(curr.getDownIntersection())) {
                q.add(curr.getDownIntersection());
            }
        }    
    }
    void setOnTheCusp(boolean b){
        onTheCusp=b;
    }
    boolean getOnTheCusp(){
        return onTheCusp;
    }
    //returns true if a city is built
    boolean buildCity(){
        if(city!=null && !city.isOwned() && hasSettler){
            city.buildSettlement(currPlayer, singleQHexes, false);
            setSettler(false);
            setOnTheCusp(false);
            setBuildSettler(false);
            setGhostSettler(false);
            setMoveSettler(false);
            setNoMoveToCity(false);
            intersectionLabel.setIcon(null);
            currPlayer.minusSettlers();
            return true;
        }
        return false;
    }
    boolean getPlayingDevelopmentCard(){
        return playingDevelopmentCard;
    }
    public void setPlayingDevelopmentCard(boolean b){
        playingDevelopmentCard=b;
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
    Player.Color getSettlerColor(){
        //<editor-fold>
        if(intersectionLabel.getIcon()==whiteSettler){
            return Player.Color.WHITE;
        }else if(intersectionLabel.getIcon()==redSettler){
            return Player.Color.RED;
        }else if(intersectionLabel.getIcon()==greenSettler){
            return Player.Color.GREEN;
        }else if(intersectionLabel.getIcon()==orangeSettler){
            return Player.Color.ORANGE;
        }else{
            return null;
        }
        //</editor-fold>
    }
}

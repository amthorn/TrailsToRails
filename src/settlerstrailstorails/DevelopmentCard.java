/**
 * there are 7 possibilities (24 total)
 * 
 * #of each
 * 
 * case 1: CALVARY (6 total)
 * move the outlaw take 2 gold from supply take 1 random resource from 1 city
 * adjacent to outlaw
 * 
 * case 2: ENGINEER (4 total)
 * build 2 rails for free
 * 
 * case 3: SCOUT (2 total)
 * move 1 settler up to 5 intersections for free OR take 3 gold
 * 
 * case 4: MINERAL RIGHTS (3 total)
 * take 2 resources of your choice for free
 * 
 * case 5: NATIVE SUPPORT (2 total)
 * select dice result of your choice instead of rolling
 * 
 * case 6: RIGHT OF WAY (4 total)
 * move 1 of your trains up to 5 rails for free. 
 * you do not have to pay for a right of way
 * 
 * case 7: CATTLE DRIVE (3 total)
 * discard 1 cattle resource card. take 5 gold
 */

package settlerstrailstorails;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

class DevelopmentCard{
    
    private int card;
    private ImageIcon img;
    boolean playable;
    private gameFrame game_Frame;
    private static int deckRemaining=24,calvaryDeck=6,engineerDeck=4,scoutDeck=2,mineralRightsDeck=3,
            nativeSupportDeck=2,rightOfWayDeck=4,cattleDriveDeck=3;
    private SettlersConfirmDialog scd;
    
    DevelopmentCard(gameFrame g){
        if(deckRemaining==0)return;
        deckRemaining--;
        game_Frame=g;
        Random r = new Random();
        int choice;
        boolean breakLoop=false;
        playable=false;
        while(!breakLoop) {
            choice = r.nextInt(7);
            switch (choice) {
                case 0: 
                    if(calvaryDeck > 0) {
                        calvaryDeck--;
                        card=choice;
                        breakLoop=true;
                    }
                    break;
                case 1: 
                    if(engineerDeck > 0) {
                        engineerDeck--;
                        card=choice;
                        breakLoop=true;
                    }
                    break;
                case 2: 
                    if(scoutDeck > 0) {
                        scoutDeck--;
                        card=choice;
                        breakLoop=true;
                    }
                    break;
                case 3: 
                    if(mineralRightsDeck > 0) {
                        mineralRightsDeck--;
                        card=choice;
                        breakLoop=true;
                    }
                    break;
                case 4: 
                    if(nativeSupportDeck > 0) {
                        nativeSupportDeck--;
                        card=choice;
                        breakLoop=true;
                    }
                    break;
                case 5: 
                    if(rightOfWayDeck > 0) {
                        rightOfWayDeck--;
                        card=choice;
                        breakLoop=true;
                    }
                    break;
                case 6: 
                    if(cattleDriveDeck > 0) {
                        cattleDriveDeck--;
                        card=choice;
                        breakLoop=true;
                    }
                    break;
            }
        }
        card++;
        switch(card){
            case 1: img=new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/cardCalvary.png", Color.YELLOW));
                break;
            case 2: img=new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/cardEngineer.png", Color.YELLOW));
                break;
            case 3: img=new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/cardScout.png", Color.YELLOW));
                break;
            case 4: img=new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/cardMineralRights.png", Color.YELLOW));
                break;
            case 5: img=new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/cardNativeSupport.png", Color.YELLOW));
                break;
            case 6: img=new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/cardRightOfWay.png", Color.YELLOW));
                break;
            case 7: img=new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/cardCattleDrive.png", Color.YELLOW));
                break;
            default: 
                break;
        }
    }
    String saveCard(){
        String retS="--DP Card--";
        retS+="|card"+card;
        retS+="|playable"+playable;
        retS+="|deckRemaining"+deckRemaining;
        retS+="|calvaryDeck"+calvaryDeck;
        retS+="|engineerDeck"+engineerDeck;
        retS+="|scoutDeck"+scoutDeck;
        retS+="|mineralRightsDeck"+mineralRightsDeck;
        retS+="|nativeSupportDeck"+nativeSupportDeck;
        retS+="|rightOfWayDeck"+rightOfWayDeck;
        retS+="|cattleDriveDeck"+cattleDriveDeck;
        retS+="--end DP Card--";
        return retS;
    }
    public void setCard(int card){
        this.card=card;
    }
    public int getCard(){
        return card;
    }
    public boolean getPlayable(){
        return playable;
    }
    public ImageIcon getIcon(){
        return img;
    }
    /**
     * sets the playable value to the value b
     */
    @Override
    public String toString(){
        if(card==1){
            return "Calvary";
        }else if(card==2){
            return "Engineer";
        }else if(card==3){
            return "Scout";
        }else if(card==4){
            return "Mineral Rights";
        }else if(card==5){
            return "Native Support";
        }else if(card==6){
            return "Right of Way";
        }else{
            return "Cattle Drive";
        }
    }
    public void setPlayable(boolean b){
        playable=b;
    }
    /**
     * assumes playing of the card is possible
     */
    public void playCard(){
        switch(card){
            case 1: //calvary
                gameFrame.addToInfo(game_Frame.getCurrPlayer()+" played Calvary card.",false);
                game_Frame.getCurrPlayer().setGold(game_Frame.getCurrPlayer().getGold()+2);
                game_Frame.getCurrPlayer().minusDPCard(1);
                game_Frame.getCurrPlayer().setPlayedDPCardThisTurn(true);
                gameFrame.playSound("ching");
                game_Frame.moveRobber();
                break;
            case 2: //engineer
                game_Frame.buildTwoRails();
                break;
            case 3: //scout
                Intersection[] in = game_Frame.getIntersections();
                for(int i=0;i<in.length;i++){
                    in[i].setPlayingDevelopmentCard(true);
                }
                //make sure you have at least one settler
                if(game_Frame.getCurrPlayer().getSettlers() > 0) {
                    if(game_Frame.getCurrPlayer().getSettlers()==2){
                        //choose settler
                        game_Frame.setMovingSettler(true);
                        game_Frame.buildingSettler(true, false);
                        gameFrame.addToInfo("Select the settler you'd like to move.",true);
                        Intersection[] intersections = game_Frame.getIntersections();
                        for(int i=0;i<intersections.length;i++){
                            if(intersections[i].getSettlerColor()==game_Frame.getCurrPlayer().getColor()){
                                intersections[i].getButton().setVisible(true);
                                intersections[i].getButton().setBorderPainted(true);
                                intersections[i].setPlayer(game_Frame.getCurrPlayer());
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
                            if(intersections[i].getSettlerColor()==game_Frame.getCurrPlayer().getColor()){
                                intersections[i].setAllBuildSettlerWithin(true, 5, game_Frame.getCurrPlayer());
                                intersections[i].buildSettler(game_Frame.getCurrPlayer());
                                intersections[i].removeSettler();
                                game_Frame.getCurrPlayer().minusSettlers();
                                intersections[i].setOnTheCusp(true);
                            }
                        }
                    }
                }else{
                    //you don't have a settler to move
                    for (int i = 0; i < in.length; i++) {
                        in[i].setPlayingDevelopmentCard(false);
                    }
                    game_Frame.buildingSettler(false, false);
                    if (scd != null && scd.isVisible()) {
                        scd.setVisible(false);
                    }
                    scd = new SettlersConfirmDialog(13);
                    scd.setVisible(true);
                }
                break;
            case 4: //mineral rights
                MineralRightsWindow mrw = new MineralRightsWindow(game_Frame);
                mrw.setVisible(true);
                mrw.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent we) {
                        new SettlersConfirmDialog(20).setVisible(true);
                    }
                });
                game_Frame.getCurrPlayer().minusDPCard(4);
                game_Frame.getCurrPlayer().setPlayedDPCardThisTurn(true);
                gameFrame.addToInfo(game_Frame.getCurrPlayer()+" played Mineral Rights card",false);
                break;
            case 5: //native support
                NativeSupportWindow nsw = new NativeSupportWindow(game_Frame);
                nsw.setVisible(true);
                nsw.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent we) {
                        new SettlersConfirmDialog(20).setVisible(true);
                    }
                });
                game_Frame.getCurrPlayer().minusDPCard(5);
                game_Frame.getCurrPlayer().setPlayedDPCardThisTurn(true);
                gameFrame.addToInfo(game_Frame.getCurrPlayer()+" played Native Support card",false);
                break;
            case 6: //right of way                
                Rail[] ra = game_Frame.getRails();
                for(int i=0;i<ra.length;i++){
                    ra[i].setPlayingDPRightOfWay(true);
                }
                if (game_Frame.getCurrPlayer().getNumOfTrains() == 2) {
                    //choose train
                    game_Frame.setMovingTrain(true);
                    game_Frame.buildingTrain(true, false);
                    game_Frame.getRails()[0].setAllTrainsInvisible();
                    gameFrame.addToInfo("Select the train you'd like to move.", true);
                    ArrayList<Rail> trainRails = game_Frame.getCurrPlayer().getTrainRails();
                    for (int i = 0; i < trainRails.size(); i++) {
                        if (trainRails.get(i).isBuilt() && trainRails.get(i).getTrainOneColor() == game_Frame.getCurrPlayer().getColor()
                                || trainRails.get(i).getTrainTwoColor() == game_Frame.getCurrPlayer().getColor()) {
                            trainRails.get(i).setTrainHighlight(game_Frame.getCurrPlayer().getColor());
                            trainRails.get(i).setMoveTrain(true);
                            trainRails.get(i).setNoMoveToCity(true);
                        }
                    }
                } else {
                    //where to move
                    game_Frame.setMovingTrain(true);
                    game_Frame.buildingTrain(true, false);
                    gameFrame.addToInfo("Select the rail to which you'd like to move.", true);
                    Rail[] trainRails = game_Frame.getRails();
                    for (int i = 0; i < trainRails.length; i++) {
                        if (trainRails[i].isBuilt() && (trainRails[i].getTrainOneColor() == game_Frame.getCurrPlayer().getColor()
                                || trainRails[i].getTrainTwoColor() == game_Frame.getCurrPlayer().getColor())) {
                            trainRails[i].setAllBuildTrainWithin(true, 5);
                            trainRails[i].buildTrain();
                            trainRails[i].removeTrain(game_Frame.getCurrPlayer().getColor());
                            game_Frame.getCurrPlayer().minusTrains();
                            game_Frame.getCurrPlayer().setMovingFromRail(trainRails[i]);
                            trainRails[i].setOnTheCusp(true);
                        }
                    }
                }
                break;
            case 7: //cattle drive
                game_Frame.getCurrPlayer().minusCattle();
                game_Frame.getCurrPlayer().setGold(game_Frame.getCurrPlayer().getGold()+5);
                game_Frame.getCurrPlayer().minusDPCard(7);
                game_Frame.getCurrPlayer().setPlayedDPCardThisTurn(true);
                game_Frame.enableMenu();
                gameFrame.playSound("ching");
                gameFrame.addToInfo(game_Frame.getCurrPlayer()+" played Cattle Drive card",false);
                game_Frame.enableMenu();
                break;
            default: 
                break;
        }
    }
    /**
     * returns true if the deck is not empty
     * @return 
     */
    public static boolean cardsRemaining(){
        return deckRemaining>0 ? true : false;
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
}
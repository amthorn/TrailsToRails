package settlerstrailstorails;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
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

public class Rail{
    enum Orientation{LEANING_LEFT,HORIZONTAL,LEANING_RIGHT};
    
    private Rail upLeftRail,upRightRail,downRightRail,downLeftRail;
    //isolatedRail is only true if it can only be connected (through rails)
    //to one city
    private boolean train,secondTrain,isBuilt, isGhost, isolatedRail=true,
            buildingTrain,hasGhostTrain,moveTrain,noMoveToCity,onTheCusp,freeRail,
            playingDevelopmentCard,playingDPRightOfWay;
    private Rail.Orientation orientation;
    private Player.Color color;
    private JButton railButton;
    private JLabel trainOneLabel, trainTwoLabel;
    private JPanel panel;
    private ImageIcon orangeRailRight,orangeRailLeft,greenRailRight,greenRailLeft,
            redRailRight,redRailLeft,whiteRailRight,whiteRailLeft,
            orangeRailHorizontal,redRailHorizontal,whiteRailHorizontal,grayRailRight,
            grayRailLeft,grayRailHorizontal,greenRailHorizontal,orangeTrain,
            redTrain,greenTrain,whiteTrain,grayTrain,greenTrainHighlight,redTrainHighlight,
            whiteTrainHighlight,orangeTrainHighlight;
    private CityHex city;
    private int railTestDistance=-1;
    private gameFrame game_Frame;
    Rail(){
        
    }
    Rail(JButton railButton, Rail.Orientation orientation, JPanel p, gameFrame g){
        game_Frame = g;
        orangeRailRight=new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/orangeRailRight.png", Color.WHITE));
        greenRailRight=new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/greenRailRight.png", Color.WHITE));
        redRailRight=new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/redRailRight.png", Color.WHITE));
        whiteRailRight=new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/whiteRailRight.png", Color.WHITE));
        grayRailRight=new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/grayRailRight.png", Color.WHITE));
        orangeRailLeft=new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/orangeRailLeft.png", Color.WHITE));
        greenRailLeft=new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/greenRailLeft.png", Color.WHITE));
        redRailLeft=new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/redRailLeft.png", Color.WHITE));
        whiteRailLeft=new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/whiteRailLeft.png", Color.WHITE));
        grayRailLeft=new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/grayRailLeft.png", Color.WHITE));
        orangeTrain=new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/orangeTrain.png", Color.WHITE));
        grayTrain=new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/grayTrain.png", Color.WHITE));
        greenTrain=new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/greenTrain.png", Color.WHITE));
        redTrain=new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/redTrain.png", Color.WHITE));
        whiteTrain=new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/whiteTrain.png", Color.WHITE));   
        greenTrainHighlight = new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/greenTrainHighlight.png", Color.WHITE)); 
        redTrainHighlight = new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/redTrainHighlight.png", Color.WHITE));  
        whiteTrainHighlight = new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/whiteTrainHighlight.png", Color.WHITE));  
        orangeTrainHighlight = new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/orangeTrainHighlight.png", Color.WHITE));    
        orangeRailHorizontal=new ImageIcon(loadImage("/settlerstrailstorails/resources/orangeRailHorizontal.png"));
        redRailHorizontal=new ImageIcon(loadImage("/settlerstrailstorails/resources/redRailHorizontal.png"));
        whiteRailHorizontal=new ImageIcon(loadImage("/settlerstrailstorails/resources/whiteRailHorizontal.png"));
        greenRailHorizontal=new ImageIcon(loadImage("/settlerstrailstorails/resources/greenRailHorizontal.png"));
        grayRailHorizontal=new ImageIcon(loadImage("/settlerstrailstorails/resources/grayRailHorizontal.png"));
        this.railButton=railButton;
        this.orientation=orientation;
        trainOneLabel=new JLabel();
        trainTwoLabel=new JLabel();
        if(orientation == Rail.Orientation.HORIZONTAL){
            trainOneLabel.setLocation(railButton.getX()+7, railButton.getY()-15);
        }else if(orientation == Rail.Orientation.LEANING_LEFT){
            trainOneLabel.setLocation(railButton.getX()+12, railButton.getY()+2);
        }else if(orientation == Rail.Orientation.LEANING_RIGHT){
            trainOneLabel.setLocation(railButton.getX()-7, railButton.getY());
        }
        if(orientation == Rail.Orientation.HORIZONTAL){
            trainTwoLabel.setLocation(railButton.getX()+7, railButton.getY()+5);
        }else if(orientation == Rail.Orientation.LEANING_LEFT){
            trainTwoLabel.setLocation(railButton.getX()-8, railButton.getY()+10);
        }else if(orientation == Rail.Orientation.LEANING_RIGHT){
            trainTwoLabel.setLocation(railButton.getX()+13, railButton.getY()+12);
        }
        panel=p;
        panel.add(trainOneLabel,new org.netbeans.lib.awtextra.AbsoluteConstraints(
                    trainOneLabel.getX(),trainOneLabel.getY()));
        panel.add(trainTwoLabel,new org.netbeans.lib.awtextra.AbsoluteConstraints(
                    trainTwoLabel.getX(),trainTwoLabel.getY()));
        final Rail r = this;
        railButton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseMoved(MouseEvent evt) {
                Player[] player = game_Frame.getPlayers();
                if (game_Frame.getBuildingSettlement()) {
                    if (!game_Frame.getFourthPlayerCityBuild()) {
                        if (game_Frame.getCurrPlayer().getColor() == Player.Color.RED) {
                            game_Frame.getMouseCityLabel().setIcon(game_Frame.getRedHouse());
                        } else if (game_Frame.getCurrPlayer().getColor() == Player.Color.GREEN) {
                            game_Frame.getMouseCityLabel().setIcon(game_Frame.getGreenHouse());
                        } else if (game_Frame.getCurrPlayer().getColor() == Player.Color.WHITE) {
                            game_Frame.getMouseCityLabel().setIcon(game_Frame.getWhiteHouse());
                        } else if (game_Frame.getCurrPlayer().getColor() == Player.Color.ORANGE) {
                            game_Frame.getMouseCityLabel().setIcon(game_Frame.getOrangeHouse());
                        }
                    } else {
                        if (player[3].getColor() == Player.Color.RED) {
                            game_Frame.getMouseCityLabel().setIcon(game_Frame.getRedHouse());
                        } else if (player[3].getColor() == Player.Color.GREEN) {
                            game_Frame.getMouseCityLabel().setIcon(game_Frame.getGreenHouse());
                        } else if (player[3].getColor() == Player.Color.WHITE) {
                            game_Frame.getMouseCityLabel().setIcon(game_Frame.getWhiteHouse());
                        } else if (player[3].getColor() == Player.Color.ORANGE) {
                            game_Frame.getMouseCityLabel().setIcon(game_Frame.getOrangeHouse());
                        }
                    }
                    PointerInfo a = MouseInfo.getPointerInfo();
                    Point b = a.getLocation();
                    int x = (int) b.getX();
                    int y = (int) b.getY();
                    game_Frame.getMouseCityLabel().setLocation(x - 34, y - 50);
                }
            }
            @Override
            public void mouseEntered(MouseEvent evt) {
                if (game_Frame.getBuildingRail()&&isGhost()&&!secondTrain) {
                    if (r.getOrientation() == Rail.Orientation.LEANING_LEFT) {
                        if (game_Frame.getCurrPlayer().getColor() == Player.Color.RED) {
                            r.getRailButton().setIcon(redRailLeft);
                        } else if (game_Frame.getCurrPlayer().getColor() == Player.Color.GREEN) {
                            r.getRailButton().setIcon(greenRailLeft);
                        } else if (game_Frame.getCurrPlayer().getColor() == Player.Color.WHITE) {
                            r.getRailButton().setIcon(whiteRailLeft);
                        } else if (game_Frame.getCurrPlayer().getColor() == Player.Color.ORANGE) {
                            r.getRailButton().setIcon(orangeRailLeft);
                        } else {
                            r.getRailButton().setIcon(grayRailLeft);
                        }
                    } else if (r.getOrientation() == Rail.Orientation.HORIZONTAL) {
                        if (game_Frame.getCurrPlayer().getColor() == Player.Color.RED) {
                            r.getRailButton().setIcon(redRailHorizontal);
                        } else if (game_Frame.getCurrPlayer().getColor() == Player.Color.GREEN) {
                            r.getRailButton().setIcon(greenRailHorizontal);
                        } else if (game_Frame.getCurrPlayer().getColor() == Player.Color.WHITE) {
                            r.getRailButton().setIcon(whiteRailHorizontal);
                        } else if (game_Frame.getCurrPlayer().getColor() == Player.Color.ORANGE) {
                            r.getRailButton().setIcon(orangeRailHorizontal);
                        } else {
                            r.getRailButton().setIcon(grayRailHorizontal);
                        }
                    } else if (r.getOrientation() == Rail.Orientation.LEANING_RIGHT) {
                        if (game_Frame.getCurrPlayer().getColor() == Player.Color.RED) {
                            r.getRailButton().setIcon(redRailRight);
                        } else if (game_Frame.getCurrPlayer().getColor() == Player.Color.GREEN) {
                            r.getRailButton().setIcon(greenRailRight);
                        } else if (game_Frame.getCurrPlayer().getColor() == Player.Color.WHITE) {
                            r.getRailButton().setIcon(whiteRailRight);
                        } else if (game_Frame.getCurrPlayer().getColor() == Player.Color.ORANGE) {
                            r.getRailButton().setIcon(orangeRailRight);
                        } else {
                            r.getRailButton().setIcon(grayRailRight);
                        }
                    }
                }
                if(hasGhostTrain) {
                    if (game_Frame.getCurrPlayer().getColor() == Player.Color.RED) {
                        if (trainOneLabel.getIcon() == grayTrain) {
                            trainOneLabel.setIcon(redTrain);
                        } else if (trainTwoLabel.getIcon() == grayTrain) {
                            trainTwoLabel.setIcon(redTrain);
                        }
                    } else if (game_Frame.getCurrPlayer().getColor() == Player.Color.GREEN) {
                        if (trainOneLabel.getIcon() == grayTrain) {
                            trainOneLabel.setIcon(greenTrain);
                        } else if (trainTwoLabel.getIcon() == grayTrain) {
                            trainTwoLabel.setIcon(greenTrain);
                        }
                    } else if (game_Frame.getCurrPlayer().getColor() == Player.Color.WHITE) {
                        if (trainOneLabel.getIcon() == grayTrain) {
                            trainOneLabel.setIcon(whiteTrain);
                        } else if (trainTwoLabel.getIcon() == grayTrain) {
                            trainTwoLabel.setIcon(whiteTrain);
                        }
                    } else if (game_Frame.getCurrPlayer().getColor() == Player.Color.ORANGE) {
                        if (trainOneLabel.getIcon() == grayTrain) {
                            trainOneLabel.setIcon(orangeTrain);
                        } else if (trainTwoLabel.getIcon() == grayTrain) {
                            trainTwoLabel.setIcon(orangeTrain);
                        }
                    }
                }
            }
            @Override
            public void mouseExited(MouseEvent evt){
                if(game_Frame.getBuildingRail() && isGhost&&!secondTrain) {
                    if (r.getOrientation() == Rail.Orientation.LEANING_RIGHT) {
                        r.getRailButton().setIcon(grayRailRight);
                    }else if (r.getOrientation() == Rail.Orientation.LEANING_LEFT) {
                        r.getRailButton().setIcon(grayRailLeft);
                    }else if (r.getOrientation() == Rail.Orientation.HORIZONTAL) {
                        r.getRailButton().setIcon(grayRailHorizontal);
                    }
                }
                if(hasGhostTrain){
                        if (trainTwoLabel.getIcon() == null) {
                            trainOneLabel.setIcon(grayTrain);
                        } else {
                            trainTwoLabel.setIcon(grayTrain);
                        }
                }
            }
            @Override
            public void mouseReleased(MouseEvent evt) {
                if (buildingTrain) {
                    if (!secondTrain) {
                        //find what the 0, 1, or 2 rails colors are
                        //and figure out what gold needs to be paid to them
                        //use game_Frame.getCurrPlayer().getMovingFromRail()
                        //between this rail.
                        Path p = null;
                        if(!playingDPRightOfWay&&moveTrain) {
                            p = findPathBetween(game_Frame.getCurrPlayer().getMovingFromRail(), false);
                        }
                        if (!playingDPRightOfWay&&(payRightOfWay(p)||!moveTrain)) {
                            hasGhostTrain = false;
                            if (train) {
                                secondTrain = true;
                            } else {
                                train = true;
                            }
                            game_Frame.getCurrPlayer().plusTrains(r);
                            setOnTheCuspAll();
                            if (!moveTrain) {
                                gameFrame.addToInfo(game_Frame.getCurrPlayer() + " built a train in " + city.getName(), false);
                            } else {
                                gameFrame.addToInfo(game_Frame.getCurrPlayer() + " moved his train.", false);
                                setMoveTrain(false);
                            }
                            game_Frame.buildingTrain(false, false);
                            setAllCityBuildTrain(false);
                        }else if(playingDPRightOfWay){
                            hasGhostTrain = false;
                            if (train) {
                                secondTrain = true;
                            } else {
                                train = true;
                            }
                            game_Frame.getCurrPlayer().plusTrains(r);
                            setOnTheCuspAll();
                            if (!moveTrain) {
                                gameFrame.addToInfo(game_Frame.getCurrPlayer() + " built a train in " + city.getName(), false);
                            } else {
                                gameFrame.addToInfo(game_Frame.getCurrPlayer() + " moved his train.", false);
                                setMoveTrain(false);
                            }
                            game_Frame.buildingTrain(false, false);
                            setAllCityBuildTrain(false);
                            Rail[] railArray = game_Frame.getRails();
                            for(int i=0;i<railArray.length;i++){
                                railArray[i].setPlayingDPRightOfWay(false);
                            }
                            game_Frame.getCurrPlayer().minusDPCard(6);
                            gameFrame.addToInfo(game_Frame.getCurrPlayer()+" played Right Of Way card",false);
                            game_Frame.getCurrPlayer().setPlayedDPCardThisTurn(true);
                            game_Frame.enableMenu();
                        }
                    }
                } else if (moveTrain) {
                    if (!noMoveToCity) {
                        Rail[] tempRails = game_Frame.getRails();
                        for(int i = 0; i < tempRails.length; i++) {
                            tempRails[i].removeTrainHighlight();
                        }
                        setAllTrainsInvisible();
                        removeTrain(game_Frame.getCurrPlayer().getColor());
                        game_Frame.getCurrPlayer().minusTrains();
                        gameFrame.addToInfo("Select the rail to which you'd like to move.", true);
                        onTheCusp = true;
                        setAllCityBuildTrain(true);
                    } else {
                        if (!playingDPRightOfWay) {
                            Rail[] tempRails = game_Frame.getRails();
                            for (int i = 0; i < tempRails.length; i++) {
                                tempRails[i].removeTrainHighlight();
                            }
                            setAllTrainsInvisible();
                            setNoMoveToCity(false);
                            game_Frame.getCurrPlayer().setMovingFromRail(r);
                            gameFrame.addToInfo("Select the rail to which you'd like to move.", true);
                            setAllBuildTrainWithin(true, 3);
                            removeTrain(game_Frame.getCurrPlayer().getColor());
                            onTheCusp = true;
                            game_Frame.getCurrPlayer().minusTrains();
                        } else {
                            Rail[] tempRails = game_Frame.getRails();
                            for (int i = 0; i < tempRails.length; i++) {
                                tempRails[i].removeTrainHighlight();
                            }
                            setAllTrainsInvisible();
                            setNoMoveToCity(false);
                            gameFrame.addToInfo("Select the rail to which you'd like to move.", true);
                            setAllBuildTrainWithin(true, 5);
                            removeTrain(game_Frame.getCurrPlayer().getColor());
                            onTheCusp = true;
                            game_Frame.getCurrPlayer().minusTrains();
                        }
                    }
                } else if (game_Frame.getBuildingRail()
                        && game_Frame.getCurrPlayer().getNumberOfRailsBuilt() > 0 && isGhost()
                        && !game_Frame.getCurrPlayer().maxRailsBuilt()) {
                    game_Frame.getCurrPlayer().buildRail(r, game_Frame.getPlayers());
                    if(!game_Frame.getCurrPlayer().maxRailsBuilt()){
                    if (freeRail) {
                        if (playingDevelopmentCard) {
                            game_Frame.doneBuildingRail();
                            Rail[] rail = game_Frame.getRails();
                            for (int i = 0; i < rail.length; i++) {
                                rail[i].setPlayingDevelopmentCard(true);
                            }
                            game_Frame.buildRail();
                            game_Frame.getCancelButton().setVisible(false);
                            gameFrame.addToInfo("You get a free rail.", false);
                            gameFrame.addToInfo("Select a location for your free rail.", true);
                        } else {
                            game_Frame.doneBuildingRail();
                            game_Frame.buildRail();
                            gameFrame.addToInfo("You get a free rail.", false);
                            gameFrame.addToInfo("Select a location for your free rail.", true);
                        }
                    } else {
                        if (playingDevelopmentCard) {
                            game_Frame.doneBuildingRail();
                            game_Frame.buildRail();
                            game_Frame.getCancelButton().setVisible(false);
                            gameFrame.addToInfo("Select a location for your second rail.", true);
                        } else {
                            game_Frame.doneBuildingRail();
                        }
                    }
                    } else {
                        gameFrame.addToInfo("You have run out of rails!",false);
                            game_Frame.doneBuildingRail();
                        }
                } else if (game_Frame.getBuildingRail()
                        && game_Frame.getCurrPlayer().getNumberOfRailsBuilt() == 0
                        && isGhost) { //first rail
                    game_Frame.getCurrPlayer().buildRail(r,game_Frame.getPlayers());
                    game_Frame.getCurrPlayer().plusTrains(r);
                    hasGhostTrain = false;
                    train = true;
                    displayTrain(game_Frame.getCurrPlayer());
                    game_Frame.doneBuildingRail();
                    hasGhostTrain = false;
                    if (game_Frame.getSetupPhase()) {
                        game_Frame.setupPhaseCityGuide();
                    }
                }
            }
        });
    }
    String saveRail(){
        String retS;
        if(city!=null){
            retS="--Rail "+orientation+"-"+city+"--";
        }else{
            retS="--Rail "+orientation+"-NULL--";            
        }
        retS+="|train"+train;
        retS+="|secondTrain" + secondTrain;
        if (trainOneLabel.getIcon() == redTrain) {
            retS += "|trainOneColorRed";                
        }else if (trainOneLabel.getIcon() == greenTrain) {
            retS += "|trainOneColorGreen";                
        }else if (trainOneLabel.getIcon() == whiteTrain) {
            retS += "|trainOneColorWhite";                
        }else if (trainOneLabel.getIcon() == orangeTrain) {
            retS += "|trainOneColorOrange";                
        }else{
            retS += "|trainOneColorNULL";                
        }
        if (trainTwoLabel.getIcon() == redTrain) {
            retS += "|trainTwoColorRed";                
        }else if (trainTwoLabel.getIcon() == greenTrain) {
            retS += "|trainTwoColorGreen";                
        }else if (trainTwoLabel.getIcon() == whiteTrain) {
            retS += "|trainTwoColorWhite";                
        }else if (trainTwoLabel.getIcon() == orangeTrain) {
            retS += "|trainTwoColorOrange";       
        }else{
            retS += "|trainTwoColorNULL";                
        }
        retS += "|isBuilt"+isBuilt;
        retS+="|isGhost"+isGhost;
        retS+="|isolatedRail"+isolatedRail;
        retS+="|buildingTrain"+buildingTrain;
        retS+="|hasGhostTrain"+hasGhostTrain;
        retS+="|moveTrain"+moveTrain;
        retS+="|noMoveToCity"+noMoveToCity;
        retS+="|onTheCusp"+onTheCusp;
        retS+="|freeRail"+freeRail;
        retS+="|playingDevelopmentCard"+playingDevelopmentCard;
        retS+="|playingDPRightOfWay"+playingDPRightOfWay;
        retS+="|orientation"+orientation;
        if(color!=null) {
            retS += "|color" + color;
        } else {
            retS += "|colorNULL";
        }
        if(city!=null){
            retS+="|city"+city.getName();
        }else{
            retS+="|cityNULL";
        }
        retS+="|railTestDistance"+railTestDistance;
        retS+="--end Rail--";
        return retS;
    }
    public Rail getUpLeftRail(){
        return upLeftRail;
    }
    public Rail getUpRightRail(){
        return upRightRail;
    }
    public Rail getDownRightRail(){
        return downRightRail;
    }
    public Rail getDownLeftRail(){
        return downLeftRail;
    }
    public void setUpLeftRail(Rail upLeftRail){
        this.upLeftRail=upLeftRail;
    }
    public void setUpRightRail(Rail upRightRail){
        this.upRightRail=upRightRail;
    }
    public void setDownLeftRail(Rail downLeftRail){
        this.downLeftRail=downLeftRail;
    }
    public void setDownRightRail(Rail downRightRail){
        this.downRightRail=downRightRail;
    }
    public boolean getFreeRail(){
        return freeRail;
    }
    public void setFreeRail(boolean b){
        freeRail=b;
    }
    public boolean hasTrain(){
        return train;
    }
    public void setTrain(boolean b){
        train=b;
    }
    public boolean hasSecondTrain(){
        return secondTrain;
    }
    public void setSecondTrain(boolean b){
        secondTrain=b;
    }
    Player.Color getColor(){
        return color;
    }
    public void setTestDistance(int d){
        railTestDistance=d;
    }
    public int getTestDistance(){
        return railTestDistance;
    }
    ArrayList<Path> buildRail(Player.Color c){
        ArrayList<Path> returnPaths = new ArrayList<Path>();
        color=c;
        if(orientation==Rail.Orientation.LEANING_LEFT) {
            if (c == Player.Color.RED) {
                railButton.setIcon(redRailLeft);
            }else if (c == Player.Color.GREEN) {
                railButton.setIcon(greenRailLeft);
            }else if (c == Player.Color.WHITE) {
                railButton.setIcon(whiteRailLeft);
            }else if (c == Player.Color.ORANGE) {
                railButton.setIcon(orangeRailLeft);
            }else{
                isGhost=true;
                railButton.setIcon(grayRailLeft);
            }
        }else if(orientation==Rail.Orientation.HORIZONTAL){
            if (c == Player.Color.RED) {
                railButton.setIcon(redRailHorizontal);
            }else if (c == Player.Color.GREEN) {
                railButton.setIcon(greenRailHorizontal);
            }else if (c == Player.Color.WHITE) {
                railButton.setIcon(whiteRailHorizontal);
            }else if (c == Player.Color.ORANGE) {
                railButton.setIcon(orangeRailHorizontal);
            }else{
                isGhost=true;
                railButton.setIcon(grayRailHorizontal);
            }
        }else if(orientation==Rail.Orientation.LEANING_RIGHT){
            if (c == Player.Color.RED) {
                railButton.setIcon(redRailRight);
            }else if (c == Player.Color.GREEN) {
                railButton.setIcon(greenRailRight);
            }else if (c == Player.Color.WHITE) {
                railButton.setIcon(whiteRailRight);
            }else if (c == Player.Color.ORANGE) {
                railButton.setIcon(orangeRailRight);
            }else{
                isGhost=true;
                railButton.setIcon(grayRailRight);
            }
        }
        if(railButton.getIcon()!=grayRailRight && railButton.getIcon()!=grayRailHorizontal&&
                railButton.getIcon()!=grayRailLeft){
            isGhost=false;
        }
        if(c!=null){
            isBuilt=true;
            gameFrame.playSound("click");
            //breadth-first search for 2 or 3 different cities
            CityHex cityOne = null;
            CityHex cityTwo = null;
            int distanceCityOne=0;
            boolean endLoop=false;
            LinkedList<Rail> q = new LinkedList<Rail>();
            ArrayList<Rail> visited = new ArrayList<Rail>();
            q.add(this);
            this.setTestDistance(0);
            int shortestDistance=500;
            while (!q.isEmpty()) {
                Rail curr = q.pop();
                if (!endLoop&&curr.getCity() != null && cityOne == null) {
                    cityOne = curr.getCity();  
                    distanceCityOne=curr.getTestDistance();
                } else if (!endLoop&&curr.getCity() != null && cityOne != curr.getCity() &&
                        (cityOne.isIsolated() || curr.getCity().isIsolated())) {
                    //found two cities
                    if (this.getCity() != cityOne && this.getCity() != curr.getCity()) {
                        shortestDistance = distanceCityOne + curr.getTestDistance() + 1;
                    } else {
                        shortestDistance = curr.getTestDistance() + 1;
                    }
                    isolatedRail=false;
                    cityTwo = curr.getCity();
                    //make sure the isolated city is always cityOne
                    if(cityTwo.isIsolated() && !cityOne.isIsolated()){
                        CityHex temp = cityOne;
                        cityOne=cityTwo;
                        cityTwo=temp;
                    }
                    endLoop=true;
                }
                visited.add(curr);
                if (!endLoop&&curr.getUpLeftRail() != null && !visited.contains(curr.getUpLeftRail())
                        && curr.getUpLeftRail().isBuilt() && !curr.getUpLeftRail().isGhost()
                        && !q.contains(curr.getUpLeftRail())) {
                    q.add(curr.getUpLeftRail());
                    curr.getUpLeftRail().setTestDistance(curr.getTestDistance()+1);
                }
                if (!endLoop&&curr.getUpRightRail() != null && !visited.contains(curr.getUpRightRail())
                        && curr.getUpRightRail().isBuilt() && !curr.getUpRightRail().isGhost()
                        && !q.contains(curr.getUpRightRail())) {
                    q.add(curr.getUpRightRail());
                    curr.getUpRightRail().setTestDistance(curr.getTestDistance()+1);
                }
                if (!endLoop&&curr.getDownLeftRail() != null && !visited.contains(curr.getDownLeftRail())
                        && curr.getDownLeftRail().isBuilt() && !curr.getDownLeftRail().isGhost()
                        && !q.contains(curr.getDownLeftRail())) {
                    q.add(curr.getDownLeftRail());
                    curr.getDownLeftRail().setTestDistance(curr.getTestDistance()+1);
                }
                if (!endLoop&&curr.getDownRightRail() != null && !visited.contains(curr.getDownRightRail())
                        && curr.getDownRightRail().isBuilt() && !curr.getDownRightRail().isGhost()
                        && !q.contains(curr.getDownRightRail())) {
                    q.add(curr.getDownRightRail());
                    curr.getDownRightRail().setTestDistance(curr.getTestDistance()+1);
                }
            }
            returnPaths=findPaths(cityOne, shortestDistance);
            if(!isolatedRail){
                setAllConnected();
            }
            //check to make sure the paths aren't the same
            if (returnPaths != null) {
                int[] pathWorths = new int[returnPaths.size()];
                for (int i = 0; i < pathWorths.length; i++) {
                    pathWorths[i] = 0;
                }
                for (int i = 0; i < returnPaths.size(); i++) {
                    Rail[] currR = returnPaths.get(i).getPath();
                    for (int j = 0; j < currR.length; j++) {
                        if (currR[j].getColor() == Player.Color.RED) {
                            pathWorths[i] += 1000;
                        } else if (currR[j].getColor() == Player.Color.GREEN) {
                            pathWorths[i] += 100;
                        } else if (currR[j].getColor() == Player.Color.WHITE) {
                            pathWorths[i] += 10;
                        } else if (currR[j].getColor() == Player.Color.ORANGE) {
                            pathWorths[i] += 1;
                        }
                    }
                }
                for (int i = 0; i < pathWorths.length; i++) {
                    for (int j = 0; j < pathWorths.length; j++) {
                        if (i != j && pathWorths[i] == pathWorths[j]) {
                            returnPaths.remove(j);
                            pathWorths[j]=0;
                        }
                    }
                }
            }
        }
        return returnPaths;
    }
    /**
     * returns a path between this rail and rail r, 
     * INCLUDING this rail but NOT rail r.
     * maximum length of 4
     * @param r
     * @return 
     */
    public Path findPathBetween(Rail r, boolean developmentCard) {
        boolean endLoop = false;
        LinkedList<Rail> q = new LinkedList<Rail>();
        ArrayList<Rail> visited = new ArrayList<Rail>();
        Path[] returnPaths = new Path[16];
        for(int i=0;i<returnPaths.length;i++){
            returnPaths[i] = new Path();
        }
        q.add(r);
        r.setTestDistance(0);
        whileLoop:
        while (!q.isEmpty()) {
            Rail curr = q.pop();
            //add rail to number of paths = 2^(length-curr.getTestDistance())
            int count;
            if(curr.getTestDistance()==5){
                count=1;
            }else if(curr.getTestDistance()==4){
                count=2;
            }else if(!developmentCard&&curr.getTestDistance()==3){
                count=1;
            }else if(developmentCard&&curr.getTestDistance()==3){
                count=4;
            }else if(!developmentCard&&curr.getTestDistance()==2){
                count=2;
            }else if(developmentCard&&curr.getTestDistance()==2){
                count=8;
            }else if(!developmentCard&&curr.getTestDistance()==1){
                count=4;
            }else if(developmentCard&&curr.getTestDistance()==1){
                count=16;
            }else if(!developmentCard){
                count=16;
            }else{
                count=64;
            }
            for(int i=0;i<returnPaths.length;i++){
                if(count>0&&!returnPaths[i].contains(curr)&&
                        (returnPaths[i].getLast()==null ||
                        (returnPaths[i].getLast().getTestDistance()==curr.getTestDistance()-1)&&
                        (returnPaths[i].contains(curr.getUpLeftRail())||returnPaths[i].contains(curr.getUpRightRail())||
                        returnPaths[i].contains(curr.getDownLeftRail())||returnPaths[i].contains(curr.getDownRightRail())))){
                    returnPaths[i].add(curr);
                    count--;
                }
            }
            if(curr==this){
                break whileLoop;
            }
            if((!developmentCard&&curr.getTestDistance()==3) || (developmentCard&&curr.getTestDistance()==5)){
                endLoop=true;
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
            if(returnPaths[i].contains(this) && returnPaths[i].contains(r)){
                returnPaths[i].removeFirst();
                return returnPaths[i];
            }
        }
        return null;
    }
    /**
     * makes current player pay right of way to owner of
     * every rail in path p. also makes user pay right of way to THIS rail
     * returns false if the user doesn't have enough, and true if the user paid
     * @param p 
     */
    boolean payRightOfWay(Path p) {
        //find how much gold is owed
        if(p==null)return false;
        int requiredGold = 0;
        boolean owesRed=false,owesWhite=false,owesGreen=false,owesOrange=false;
        for (int i = 0; i < p.getPath().length; i++) {
            if (!owesRed&&p.getPath()[i].getColor()==Player.Color.RED && game_Frame.getCurrPlayer().getColor()!=Player.Color.RED&&
                    !game_Frame.redPlayer.getBeenPaidRightOfWayThisTurn()) {
                requiredGold++;
                owesRed=true;
            }else if (!owesGreen&&p.getPath()[i].getColor()==Player.Color.GREEN && game_Frame.getCurrPlayer().getColor()!=Player.Color.GREEN&&
                    !game_Frame.greenPlayer.getBeenPaidRightOfWayThisTurn()) {
                requiredGold++;
                owesGreen=true;
            }else if (!owesWhite&&p.getPath()[i].getColor()==Player.Color.WHITE && game_Frame.getCurrPlayer().getColor()!=Player.Color.WHITE&&
                    !game_Frame.whitePlayer.getBeenPaidRightOfWayThisTurn()) {
                requiredGold++;
                owesWhite=true;
            }else if (!owesOrange&&p.getPath()[i].getColor()==Player.Color.ORANGE && game_Frame.getCurrPlayer().getColor()!=Player.Color.ORANGE&&
                    !game_Frame.orangePlayer.getBeenPaidRightOfWayThisTurn()) {
                requiredGold++;
                owesOrange=true;
            }
        }
        if(game_Frame.getCurrPlayer().getGold()>=requiredGold){
            //pay right of way
            if(owesRed) {
                gameFrame.addToInfo(game_Frame.getCurrPlayer().getName() + " paid right of way to " + game_Frame.redPlayer.getName(), false);
                game_Frame.getCurrPlayer().minusGold();
                game_Frame.redPlayer.plusGold();
                game_Frame.redPlayer.setBeenPaidRightOfWayThisTurn(true);
            }
            if (owesGreen) {
                gameFrame.addToInfo(game_Frame.getCurrPlayer().getName() + " paid right of way to " + game_Frame.greenPlayer.getName(), false);
                game_Frame.getCurrPlayer().minusGold();
                game_Frame.greenPlayer.plusGold();
                game_Frame.greenPlayer.setBeenPaidRightOfWayThisTurn(true);
            }
            if (owesWhite) {
                gameFrame.addToInfo(game_Frame.getCurrPlayer().getName() + " paid right of way to " + game_Frame.whitePlayer.getName(), false);
                game_Frame.getCurrPlayer().minusGold();
                game_Frame.whitePlayer.plusGold();
                game_Frame.whitePlayer.setBeenPaidRightOfWayThisTurn(true);
            }
            if (owesOrange) {
                gameFrame.addToInfo(game_Frame.getCurrPlayer().getName() + " paid right of way to " + game_Frame.orangePlayer.getName(), false);
                game_Frame.getCurrPlayer().minusGold();
                game_Frame.orangePlayer.plusGold();
                game_Frame.orangePlayer.setBeenPaidRightOfWayThisTurn(true);
            }
            return true;
        } else {
            //not enough gold
            new SettlersConfirmDialog(14).setVisible(true);
            game_Frame.buildingTrain(false, true);
            removeTrain(game_Frame.currPlayer.getColor());
            gameFrame.addToInfo(game_Frame.currPlayer.getName() + ": Take your turn", false);
            return false;
        }
    }
    void setTrainHighlight(Player.Color c){
        if(c== Player.Color.WHITE) {
            if (trainOneLabel.getIcon() == whiteTrain) {
                trainOneLabel.setIcon(whiteTrainHighlight);
            }
            if (trainTwoLabel.getIcon() == whiteTrain) {
                trainTwoLabel.setIcon(whiteTrainHighlight);
            }
        }
        if(c== Player.Color.RED) {
            if (trainOneLabel.getIcon() == redTrain) {
                trainOneLabel.setIcon(redTrainHighlight);
            }
            if (trainTwoLabel.getIcon() == redTrain) {
                trainTwoLabel.setIcon(redTrainHighlight);
            }
        }
        if(c== Player.Color.GREEN) {
            if (trainOneLabel.getIcon() == greenTrain) {
                trainOneLabel.setIcon(greenTrainHighlight);
            }
            if (trainTwoLabel.getIcon() == greenTrain) {
                trainTwoLabel.setIcon(greenTrainHighlight);
            }
        }
        if(c== Player.Color.ORANGE) {
            if (trainOneLabel.getIcon() == orangeTrain) {
                trainOneLabel.setIcon(orangeTrainHighlight);
            }
            if (trainTwoLabel.getIcon() == orangeTrain) {
                trainTwoLabel.setIcon(orangeTrainHighlight);
            }
        }
    }
    void removeTrainHighlight(){
        if (trainOneLabel.getIcon() == whiteTrainHighlight) {
            trainOneLabel.setIcon(whiteTrain);
        }
        if (trainTwoLabel.getIcon() == whiteTrainHighlight) {
            trainTwoLabel.setIcon(whiteTrain);
        }

        if (trainOneLabel.getIcon() == redTrainHighlight) {
            trainOneLabel.setIcon(redTrain);
        }
        if (trainTwoLabel.getIcon() == redTrainHighlight) {
            trainTwoLabel.setIcon(redTrain);
        }

        if (trainOneLabel.getIcon() == greenTrainHighlight) {
            trainOneLabel.setIcon(greenTrain);
        }
        if (trainTwoLabel.getIcon() == greenTrainHighlight) {
            trainTwoLabel.setIcon(greenTrain);
        }
        if (trainOneLabel.getIcon() == orangeTrainHighlight) {
            trainOneLabel.setIcon(orangeTrain);
        }
        if (trainTwoLabel.getIcon() == orangeTrainHighlight) {
            trainTwoLabel.setIcon(orangeTrain);
        }
    }
    void demolishRail(){
        railButton.setIcon(null);
        color=null;
        isBuilt=false;
        isGhost=false;
    }
    boolean isIsolated(){
        return isolatedRail;
    }
    void setIsolated(boolean b){
        isolatedRail=b;
    }
    boolean isBuilt(){
        return isBuilt;
    }
    public JButton getRailLabel(){
        return railButton;
    }    
    void setCity(CityHex c){
        city=c;
    }
    CityHex getCity(){
        return city;
    }
    boolean isGhost(){
        return isGhost;
    }
    /**
     * finds all paths of length l, that extend from city c and connect
     * to another city space
     * @param c
     * @param length
     * @return 
     */
    ArrayList<Path> findPaths(CityHex c, int length){
        if(length>499)return null;
        Path[] returnPaths=new Path[((int)Math.pow(2,(length-1)))*3];
        for(int i=0;i<returnPaths.length;i++){
            returnPaths[i] = new Path();
        }
        LinkedList<Rail> q = new LinkedList<Rail>();
        LinkedList<Rail> visited = new LinkedList<Rail>();
        if(c.getUpRail() != null && c.getUpRail().isBuilt() && !c.getUpRail().isGhost()) {
            q.add(c.getUpRail());
            c.getUpRail().setTestDistance(1);
        }
        if (c.getHorRail() != null && c.getHorRail().isBuilt() && !c.getHorRail().isGhost()) {
            q.add(c.getHorRail());
            c.getHorRail().setTestDistance(1);
        }
        if (c.getDownRail() != null && c.getDownRail().isBuilt() && !c.getDownRail().isGhost()) {
            q.add(c.getDownRail());
            c.getDownRail().setTestDistance(1);
        }
        while(!q.isEmpty()) {
            Rail curr = q.pop();
            visited.add(curr);
            //add rail to number of paths = 2^(length-curr.getTestDistance())
            int count=(int)Math.pow(2, length-curr.getTestDistance());
            for(int i=0;i<returnPaths.length;i++){
                if(count>0&&!returnPaths[i].contains(curr)&&
                        (returnPaths[i].getLast()==null ||
                        (returnPaths[i].getLast().getTestDistance()==curr.getTestDistance()-1)&&
                        (returnPaths[i].contains(curr.getUpLeftRail())||returnPaths[i].contains(curr.getUpRightRail())||
                        returnPaths[i].contains(curr.getDownLeftRail())||returnPaths[i].contains(curr.getDownRightRail())))){
                    returnPaths[i].add(curr);
                    count--;
                }
            }
            if (curr.getUpLeftRail() != null && !visited.contains(curr.getUpLeftRail())
                    && curr.getUpLeftRail().isBuilt() && !curr.getUpLeftRail().isGhost()
                    && !q.contains(curr.getUpLeftRail())&&curr.getTestDistance()!=length) {
                q.add(curr.getUpLeftRail());
                curr.getUpLeftRail().setTestDistance(curr.getTestDistance() + 1);
            }
            if (curr.getUpRightRail() != null && !visited.contains(curr.getUpRightRail())
                    && curr.getUpRightRail().isBuilt() && !curr.getUpRightRail().isGhost()
                    && !q.contains(curr.getUpRightRail())&&curr.getTestDistance()!=length) {
                q.add(curr.getUpRightRail());
                curr.getUpRightRail().setTestDistance(curr.getTestDistance() + 1);
            }
            if (curr.getDownLeftRail() != null && !visited.contains(curr.getDownLeftRail())
                    && curr.getDownLeftRail().isBuilt() && !curr.getDownLeftRail().isGhost()
                    && !q.contains(curr.getDownLeftRail())&&curr.getTestDistance()!=length) {
                q.add(curr.getDownLeftRail());
                curr.getDownLeftRail().setTestDistance(curr.getTestDistance() + 1);
            }
            if (curr.getDownRightRail() != null && !visited.contains(curr.getDownRightRail())
                    && curr.getDownRightRail().isBuilt() && !curr.getDownRightRail().isGhost()
                    && !q.contains(curr.getDownRightRail())&&curr.getTestDistance()!=length) {
                q.add(curr.getDownRightRail());
                curr.getDownRightRail().setTestDistance(curr.getTestDistance() + 1);
            }
        }
        for(int i=0;i<returnPaths.length;i++){
            if(returnPaths[i]!=null&&returnPaths[i].getLast()!=null&&
                    returnPaths[i].getLast().getCity()==null){
                returnPaths[i]=null;
            }
        }
        ArrayList<Path> tempAL = new ArrayList<Path>();
        for(int i=0;i<returnPaths.length;i++){
            if(returnPaths[i]==null || (returnPaths[i]!=null&&returnPaths[i].getPath().length<length)){
                continue;
            }
            tempAL.add(returnPaths[i]);
        }
        return tempAL;
    }
    /**
     * sets all the paths and cities connected to this rail to not isolated.
     */
    boolean getMoveTrain(){
        return moveTrain;
    }
    void setMoveTrain(boolean b){
        moveTrain=b;
    }
    void setGhostTrain(boolean b){
        hasGhostTrain=b;
    }    
    void displayTrain(Player p){
        if(p.getColor()==Player.Color.RED){
            if(trainOneLabel.getIcon()==null){
                trainOneLabel.setIcon(redTrain);
            }else if(trainTwoLabel.getIcon()==null){
                trainTwoLabel.setIcon(redTrain);                
            }
        }else if(p.getColor()==Player.Color.GREEN){
            if(trainOneLabel.getIcon()==null){
                trainOneLabel.setIcon(greenTrain);
            }else if(trainTwoLabel.getIcon()==null){
                trainTwoLabel.setIcon(greenTrain);                
            }
        }else if(p.getColor()==Player.Color.WHITE){
            if(trainOneLabel.getIcon()==null){
                trainOneLabel.setIcon(whiteTrain);
            }else if(trainTwoLabel.getIcon()==null){
                trainTwoLabel.setIcon(whiteTrain);                
            }
        }else if(p.getColor()==Player.Color.ORANGE){
            if(trainOneLabel.getIcon()==null){
                trainOneLabel.setIcon(orangeTrain);
            }else if(trainTwoLabel.getIcon()==null){
                trainTwoLabel.setIcon(orangeTrain);                
            }
        }
        hasGhostTrain=true;
        train=true;
    }
    boolean getBuildTrain(){
        return buildingTrain;
    }
    void setBuildTrain(boolean b){
        buildingTrain=b;
    }
    boolean hasGhostTrain(){
        return hasGhostTrain;
    }
    boolean getNoMoveToCity(){
        return noMoveToCity;
    }
    void setNoMoveToCity(boolean b){
        noMoveToCity=b;
    }    
    Player.Color getTrainOneColor(){
        if(trainOneLabel.getIcon()==whiteTrain){
            return Player.Color.WHITE;
        }
        if(trainOneLabel.getIcon()==greenTrain){
            return Player.Color.GREEN;
        }
        if(trainOneLabel.getIcon()==orangeTrain){
            return Player.Color.ORANGE;
        }
        if(trainOneLabel.getIcon()==redTrain){
            return Player.Color.RED;
        }
        return null;
    }
    Player.Color getTrainTwoColor(){
        if(trainTwoLabel.getIcon()==whiteTrain){
            return Player.Color.WHITE;
        }
        if(trainTwoLabel.getIcon()==greenTrain){
            return Player.Color.GREEN;
        }
        if(trainTwoLabel.getIcon()==orangeTrain){
            return Player.Color.ORANGE;
        }
        if(trainTwoLabel.getIcon()==redTrain){
            return Player.Color.RED;
        }
        return null;
    }
    void buildGhostTrain() {
        if(trainOneLabel.getIcon() == null) {
            trainOneLabel.setIcon(grayTrain);
        } else if (trainTwoLabel.getIcon()==null){
            trainTwoLabel.setIcon(grayTrain);
        }
        hasGhostTrain = true;
    }    
    void buildTrain(){
        setBuildTrain(true);
    }
    void removeGhostTrain(){
        if(trainTwoLabel.getIcon()==grayTrain){
            removeTrainTwo();
        }
        if(trainOneLabel.getIcon()==grayTrain){
            removeTrainOne();
        }
        hasGhostTrain=false;
    }
    void removeTrain(Player.Color c) {
        if (c == Player.Color.RED) {
            if (trainTwoLabel.getIcon() == redTrain) {
                removeTrainTwo();
            }else if (trainOneLabel.getIcon() == redTrain) {
                removeTrainOne();
            }
        }
        if (c == Player.Color.GREEN) {
            if (trainTwoLabel.getIcon() == greenTrain) {
                removeTrainTwo();
            }else if (trainOneLabel.getIcon() == greenTrain) {
                removeTrainOne();
            }
        }
        if (c == Player.Color.WHITE) {
            if (trainTwoLabel.getIcon() == whiteTrain) {
                removeTrainTwo();
            }else if (trainOneLabel.getIcon() == whiteTrain) {
                removeTrainOne();
            }
        }
        if (c == Player.Color.ORANGE) {
            if (trainTwoLabel.getIcon() == orangeTrain) {
                removeTrainTwo();
            }else if (trainOneLabel.getIcon() == orangeTrain) {
                removeTrainOne();
            }
        }
    }
    JLabel getTrainOneLabel(){
        return trainOneLabel;
    }
    JLabel getTrainTwoLabel(){
        return trainTwoLabel;
    }
    void removeTrainOne(){
        trainOneLabel.setIcon(null);
        if(secondTrain) {
            trainOneLabel.setIcon(trainTwoLabel.getIcon());
            train = true;
            secondTrain = false;
            trainTwoLabel.setIcon(null);
            hasGhostTrain=false;
        } else {
            hasGhostTrain = false;
            train = false;
            setBuildTrain(false);
        }
    }
    void removeTrainTwo(){
        trainTwoLabel.setIcon(null);
        hasGhostTrain=false;
        secondTrain=false;
        setBuildTrain(false);
    }
    boolean getOnTheCusp(){
        return onTheCusp;
    }
    void setOnTheCusp(boolean b){
        onTheCusp=b;
    }
       public void setOnTheCuspAll(){
        LinkedList<Rail> q = new LinkedList<Rail>();
        ArrayList<Rail> visited = new ArrayList<Rail>();
        q.add(this);
        while (!q.isEmpty()) {
            Rail curr = q.pop();
            if(curr!=null){
                if (game_Frame.getCurrPlayer().getTrainRails().contains(curr)
                        && curr.getOnTheCusp()&&!curr.hasSecondTrain()) {
                    if (game_Frame.getCurrPlayer().getTrainRails().size() > 1) {
                        if (game_Frame.getCurrPlayer().getTrainRails().get(0) == curr) {                            
                            game_Frame.getCurrPlayer().getTrainRails().remove(0);
                        } else {
                            game_Frame.getCurrPlayer().getTrainRails().remove(1);
                        }
                    } else if (game_Frame.getCurrPlayer().getTrainRails().size() > 0) {
                        game_Frame.getCurrPlayer().getTrainRails().remove(curr);
                    }
                }else if(game_Frame.getCurrPlayer().getTrainRails().contains(curr)
                        && curr.getOnTheCusp()&&curr.hasSecondTrain()){
                    curr.setSecondTrain(false);
                }
                curr.setOnTheCusp(false);
            }
            visited.add(curr);
            if (curr.getUpLeftRail() != null && !visited.contains(curr.getUpLeftRail())
                    && !q.contains(curr.getUpLeftRail())) {
                q.add(curr.getUpLeftRail());
            }
            if (curr.getUpRightRail() != null && !visited.contains(curr.getUpRightRail())
                    && !q.contains(curr.getUpRightRail())) {
                q.add(curr.getUpRightRail());
            }
            if (curr.getDownLeftRail() != null && !visited.contains(curr.getDownLeftRail())
                    && !q.contains(curr.getDownLeftRail())) {
                q.add(curr.getDownLeftRail());
            }
            if (curr.getDownRightRail() != null && !visited.contains(curr.getDownRightRail())
                    && !q.contains(curr.getDownRightRail())) {
                q.add(curr.getDownRightRail());
            }
        }    
    }
    void setAllTrainsInvisible() {
        //<editor-fold>
        LinkedList<Rail> q = new LinkedList<Rail>();
        ArrayList<Rail> visited = new ArrayList<Rail>();
        q.add(this);
        while (!q.isEmpty()) {
            Rail curr = q.pop();
            if (curr != null) {
                if (curr.getMoveTrain()) {
                    curr.setMoveTrain(false);
                }
                if (curr.getBuildTrain()) {
                    curr.setBuildTrain(false);
                }
                if (curr.hasGhostTrain()) {
                    curr.setGhostTrain(false);
                }
            }
            visited.add(curr);
            if (curr.getUpLeftRail() != null && !visited.contains(curr.getUpLeftRail())
                    && !q.contains(curr.getUpLeftRail())) {
                q.add(curr.getUpLeftRail());
            }
            if (curr.getUpRightRail() != null && !visited.contains(curr.getUpRightRail())
                    && !q.contains(curr.getUpRightRail())) {
                q.add(curr.getUpRightRail());
            }
            if (curr.getDownLeftRail() != null && !visited.contains(curr.getDownLeftRail())
                    && !q.contains(curr.getDownLeftRail())) {
                q.add(curr.getDownLeftRail());
            }
            if (curr.getDownRightRail() != null && !visited.contains(curr.getDownRightRail())
                    && !q.contains(curr.getDownRightRail())) {
                q.add(curr.getDownRightRail());
            }
        }
    }
    void setAllBuildTrainWithin(boolean b, int distance){
        LinkedList<Rail> q = new LinkedList<Rail>();
        ArrayList<Rail> visited = new ArrayList<Rail>();
        q.add(this);
        setTestDistance(0);
        boolean endLoop=false;
        while (!q.isEmpty()) {
            Rail curr = q.pop();
            if(curr!=null&&!curr.hasSecondTrain() && curr.getTestDistance()<=distance&&
                    curr.isBuilt()&&curr.getTestDistance()!=0) {
                if (b) {
                    curr.buildGhostTrain();
                    curr.setMoveTrain(true);
                    curr.buildTrain();
                } else if (curr.hasGhostTrain()) {
                    curr.removeGhostTrain();
                }              
            }else if(curr.getTestDistance()>distance){
                endLoop=true;
            }
            visited.add(curr);
            if (!endLoop && curr.getUpRightRail() != null && !visited.contains(curr.getUpRightRail())
                    && !q.contains(curr.getUpRightRail())&&curr.getUpRightRail().isBuilt()) {
                q.add(curr.getUpRightRail());
                curr.getUpRightRail().setTestDistance(curr.getTestDistance()+1);
            }
            if (!endLoop && curr.getUpLeftRail() != null && !visited.contains(curr.getUpLeftRail())
                    && !q.contains(curr.getUpLeftRail())&&curr.getUpLeftRail().isBuilt()) {
                q.add(curr.getUpLeftRail());
                curr.getUpLeftRail().setTestDistance(curr.getTestDistance()+1);
            }
            if (!endLoop && curr.getDownRightRail() != null && !visited.contains(curr.getDownRightRail())
                    && !q.contains(curr.getDownRightRail())&&curr.getDownRightRail().isBuilt()) {
                q.add(curr.getDownRightRail());
                curr.getDownRightRail().setTestDistance(curr.getTestDistance()+1);
            }
            if (!endLoop && curr.getDownLeftRail() != null && !visited.contains(curr.getDownLeftRail())
                    && !q.contains(curr.getDownLeftRail())&&curr.getDownLeftRail().isBuilt()) {
                q.add(curr.getDownLeftRail());
                curr.getDownLeftRail().setTestDistance(curr.getTestDistance()+1);
            }
        }
        for(int i=0;i<visited.size();i++){
            if(visited.get(i)!=null){
                visited.get(i).setTestDistance(-1);
            }
        }
    }  
    //</editor-fold>
    void setAllCityBuildTrain(boolean b){
        LinkedList<Rail> q = new LinkedList<Rail>();
        ArrayList<Rail> visited = new ArrayList<Rail>();
        q.add(this);
        while (!q.isEmpty()) {
            Rail curr = q.pop();
            if(curr!=null &&curr.getCity()!=null&&
                    curr.getCity().getOwner()==game_Frame.getCurrPlayer() && 
                    !curr.hasSecondTrain()&&b&&!curr.getOnTheCusp()&&curr.isBuilt()){
                curr.setBuildTrain(b);
                curr.buildGhostTrain();
            }else if(curr!=null &&!b&&curr.hasGhostTrain()){
                curr.setBuildTrain(b);                
                curr.removeGhostTrain();
            }else if(curr!=null&&!b){
                curr.setBuildTrain(b);
            }
            visited.add(curr);
            if (curr.getUpLeftRail() != null && !visited.contains(curr.getUpLeftRail())
                    && !q.contains(curr.getUpLeftRail())) {
                q.add(curr.getUpLeftRail());
            }
            if (curr.getUpRightRail() != null && !visited.contains(curr.getUpRightRail())
                    && !q.contains(curr.getUpRightRail())) {
                q.add(curr.getUpRightRail());
            }
            if (curr.getDownLeftRail() != null && !visited.contains(curr.getDownLeftRail())
                    && !q.contains(curr.getDownLeftRail())) {
                q.add(curr.getDownLeftRail());
            }
            if (curr.getDownRightRail() != null && !visited.contains(curr.getDownRightRail())
                    && !q.contains(curr.getDownRightRail())) {
                q.add(curr.getDownRightRail());
            }
        }
    }    
    Rail.Orientation getOrientation(){
        return orientation;
    }
    public void setPlayingDevelopmentCard(boolean b){
        playingDevelopmentCard=b;
    }
    JButton getRailButton(){
        return railButton;
    }
    void setAllConnected() {
        LinkedList<Rail> q = new LinkedList<Rail>();
        ArrayList<Rail> visited = new ArrayList<Rail>();
        q.add(this);
        while (!q.isEmpty()) {
            Rail curr = q.pop();
            curr.setIsolated(false);
            if(curr.getCity()!=null)curr.getCity().setIsolated(false);
            visited.add(curr);
            if (curr.getUpLeftRail() != null && !visited.contains(curr.getUpLeftRail())
                    && curr.getUpLeftRail().isBuilt() && !curr.getUpLeftRail().isGhost()) {
                q.add(curr.getUpLeftRail());
            }
            if (curr.getUpRightRail() != null && !visited.contains(curr.getUpRightRail())
                    && curr.getUpRightRail().isBuilt() && !curr.getUpRightRail().isGhost()) {
                q.add(curr.getUpRightRail());
            }
            if (curr.getDownLeftRail() != null && !visited.contains(curr.getDownLeftRail())
                    && curr.getDownLeftRail().isBuilt() && !curr.getDownLeftRail().isGhost()) {
                q.add(curr.getDownLeftRail());
            }
            if (curr.getDownRightRail() != null && !visited.contains(curr.getDownRightRail())
                    && curr.getDownRightRail().isBuilt() && !curr.getDownRightRail().isGhost()) {
                q.add(curr.getDownRightRail());
            }
        }
    }
    void setPlayingDPRightOfWay(boolean b){
        playingDPRightOfWay=b;
    }
    boolean getPlayingDPRightOfWay(){
        return playingDPRightOfWay;
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
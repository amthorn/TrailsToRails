package settlerstrailstorails;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseListener;

final class Hex{
    private int number, chit;
    private double probability;
    private boolean robber,singleQuestion,doubleQuestion;
    private javax.swing.JLabel chitLabel,robberLabel;
    private JButton button;
    private gameFrame game_Frame;
    Hex.Resource resource;
    private MouseListener m = new MouseAdapter(){
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
                for(int i=0;i<game_Frame.getHexes().length;i++){
                    game_Frame.getHexes()[i].setRobber(false);
                }
                gameFrame.addToInfo(game_Frame.getCurrPlayer().getName()+" moved the robber.",false);
                setRobber(true);
                game_Frame.doneMovingRobber();
        }
    };

    enum Resource {CATTLE, WHEAT, COAL, ORE, LUMBER, DESERT;
    
    @Override
    public String toString(){
        switch(this){
            case CATTLE: return "Cattle";
            case WHEAT: return "Wheat";
            case COAL: return "Coal";
            case ORE: return "Ore";
            case LUMBER: return "Lumber";
            default: return "Desert";
        }
    }};
    Hex(int number,int chit,boolean singleQuestion,boolean doubleQuestion, 
            Hex.Resource r, javax.swing.JLabel l,javax.swing.JLabel rl,JButton b,
            gameFrame g){
        game_Frame=g;
        this.number=number;
        this.singleQuestion=singleQuestion;
        this.doubleQuestion=doubleQuestion;  
        this.chit=chit;
        calculateProbability();
        resource=r;
        chitLabel=l;
        setChit(chit);
        robberLabel=rl;
        button=b;
        button.addMouseListener(m);
    }
    Hex(int number,int chit,boolean singleQuestion,boolean doubleQuestion, 
            Hex.Resource r,javax.swing.JLabel rl,JButton b, gameFrame g){
        game_Frame=g;
        this.number=number;
        this.singleQuestion=singleQuestion;
        this.doubleQuestion=doubleQuestion;  
        this.chit=chit;
        calculateProbability();
        resource=r;
        robberLabel=rl;
        button=b;
        button.addMouseListener(m);
    }
    String saveHex(){
        String retS="";
        retS+="--Hex "+number+"--";
        retS+="|chit"+chit;
        retS+="|probability"+probability;
        retS+="|robber"+robber;
        retS+="|singleQuestion"+singleQuestion;
        retS+="|doubleQuestion"+doubleQuestion;
        retS += "|resource" + resource.toString();
        retS+="--end Hex "+number+"--";
        return retS;
    }
    void calculateProbability(){
        switch(chit){
            case 2:probability=0.0278;
                break;
            case 3:probability=0.0556;
                break;
            case 4:probability=0.0833;
                break;
            case 5:probability=0.1111;
                break;
            case 6:probability=0.1389;
                break;
            case 8:probability=0.1389;
                break;
            case 9:probability=0.1111;
                break;
            case 10:probability=0.0833;
                break;
            case 11:probability=0.0556;
                break;
            case 12:probability=0.0278;
                break;
            default: probability=0;
                break;
        }
    }
    void setChit(int c){
        chit=c;
        switch(c){
            case 9: chitLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource(
                        "/settlerstrailstorails/resources/9Chit.png")));
                break;
            case 10: chitLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource(
                        "/settlerstrailstorails/resources/10Chit.png")));
                break;
            case 11: chitLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource(
                        "/settlerstrailstorails/resources/11Chit.png")));
                break;
            default: 
                if(singleQuestion){
                chitLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource(
                        "/settlerstrailstorails/resources/singleQuestionChit.png")));
            }else if(doubleQuestion){
                chitLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource(
                        "/settlerstrailstorails/resources/doubleQuestionChit.png")));
            }
                break;
        }
        calculateProbability();
    }
    Hex setRobber(boolean b){
        robber=b;
        if(robber){
            try{
            robberLabel.setIcon(new ImageIcon(getClass().getResource("/settlerstrailstorails/resources/robber.png")));
            }catch(Exception e){
                
            }
        }else{
            robberLabel.setIcon(null);
        }
        return this;
    }
    int getNumber(){
        return number;
    }
    int getChit(){
        return chit;
    }
    double getProbability(){
        return probability;
    }
    boolean getRobber(){
        return robber;
    }
    Hex.Resource getResource(){
        return resource;
    }
    boolean isDoubleQ(){
        return doubleQuestion;
    }
    boolean isSingleQ(){
        return singleQuestion;
    }
    void setDoubleQ(boolean b){
        doubleQuestion=b;
    }
    void setSingleQ(boolean b){
        singleQuestion=b;
    }
    javax.swing.JLabel getChitLabel(){
        return chitLabel;
    }
    /**
     * returns true if the chit and resource are the same
     * @param Hex h
     * @return boolean
     */
    boolean probEquals(Hex h){
        if(h.chit==this.chit && h.resource==this.resource){
            return true;
        }else{
            return false;
        }
    }
    JButton getHexButton(){
        return button;
    }
}
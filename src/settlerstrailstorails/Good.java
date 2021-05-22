package settlerstrailstorails;

import javax.swing.JLabel;

public class Good{
    
    JLabel goodLabel;
    Player.Color goodColor;
    boolean readyForDelivery=false, delivered=false;
    private static int numberOfRedGoodsReady = 0, numberOfRedGoodsUndelivered = 0,
            numberOfGreenGoodsReady = 0, numberOfGreenGoodsUndelivered = 0,
            numberOfWhiteGoodsReady = 0, numberOfWhiteGoodsUndelivered = 0,
            numberOfOrangeGoodsReady = 0, numberOfOrangeGoodsUndelivered = 0;

    Good(Player.Color c) {
        goodColor = c;
        if(c==Player.Color.RED){
            numberOfRedGoodsUndelivered++;            
        }else if(c==Player.Color.GREEN){
            numberOfGreenGoodsUndelivered++;            
        }else if(c==Player.Color.WHITE){
            numberOfWhiteGoodsUndelivered++;            
        }else if(c==Player.Color.ORANGE){
            numberOfOrangeGoodsUndelivered++;            
        }
    }
    String saveGood(){
        String retS="--Good("+goodColor+")--";
        retS+="|readyForDelivery"+readyForDelivery;
        retS+="|delivered"+delivered;
        if (goodColor==Player.Color.RED) {
            retS += "|numberOfRedGoodsReady"+numberOfRedGoodsReady;  
            retS += "|numberOfRedGoodsUndelivered"+numberOfRedGoodsUndelivered;                
        }else if (goodColor==Player.Color.GREEN) {
            retS += "|numberOfGreenGoodsReady"+numberOfGreenGoodsReady;  
            retS += "|numberOfGreenGoodsUndelivered"+numberOfGreenGoodsUndelivered;                  
        }else if (goodColor==Player.Color.WHITE) {
            retS += "|numberOfWhiteGoodsReady"+numberOfWhiteGoodsReady;  
            retS += "|numberOfWhiteGoodsUndelivered"+numberOfWhiteGoodsUndelivered;   
        }else{
            retS += "|numberOfOrangeGoodsReady"+numberOfOrangeGoodsReady;  
            retS += "|numberOfOrangeGoodsUndelivered"+numberOfOrangeGoodsUndelivered;   
        }
        retS+="--end good("+goodColor+")--";
        return retS;
    }
    boolean getReadyForDelivery(){
        return readyForDelivery;
    }
    void setReadyForDelivery(boolean b){
        readyForDelivery=b;
        if(b) {
            if (goodColor == Player.Color.RED) {
                numberOfRedGoodsReady++;
            } else if (goodColor == Player.Color.GREEN) {
                numberOfGreenGoodsReady++;
            } else if (goodColor == Player.Color.WHITE) {
                numberOfWhiteGoodsReady++;
            } else if (goodColor == Player.Color.ORANGE) {
                numberOfOrangeGoodsReady++;
            }
        }else{
            if (goodColor == Player.Color.RED) {
                numberOfRedGoodsReady--;
            } else if (goodColor == Player.Color.GREEN) {
                numberOfGreenGoodsReady--;
            } else if (goodColor == Player.Color.WHITE) {
                numberOfWhiteGoodsReady--;
            } else if (goodColor == Player.Color.ORANGE) {
                numberOfOrangeGoodsReady--;
            }
        }
    }
    boolean getDelivered(){
        return delivered;
    }
    void setDelivered(boolean b){
        delivered=b;
        if(!b){
            if (goodColor == Player.Color.RED) {
                numberOfRedGoodsUndelivered++;
            } else if (goodColor == Player.Color.GREEN) {
                numberOfGreenGoodsUndelivered++;
            } else if (goodColor == Player.Color.WHITE) {
                numberOfWhiteGoodsUndelivered++;
            } else if (goodColor == Player.Color.ORANGE) {
                numberOfOrangeGoodsUndelivered++;
            }
        }else{            
            if (goodColor == Player.Color.RED) {
                numberOfRedGoodsUndelivered--;
            } else if (goodColor == Player.Color.GREEN) {
                numberOfGreenGoodsUndelivered--;
            } else if (goodColor == Player.Color.WHITE) {
                numberOfWhiteGoodsUndelivered--;
            } else if (goodColor == Player.Color.ORANGE) {
                numberOfOrangeGoodsUndelivered--;
            }
        }
    }   
    void setGoodLabel(JLabel j){
        goodLabel=j;
    }
    JLabel getGoodLabel(){
        return goodLabel;
    }
    int getNumberOfGoodsReady() {
        if (goodColor == Player.Color.RED) {
            return numberOfRedGoodsReady;
        } else if (goodColor == Player.Color.GREEN) {
            return numberOfGreenGoodsReady;
        } else if (goodColor == Player.Color.WHITE) {
            return numberOfWhiteGoodsReady;
        } else if (goodColor == Player.Color.ORANGE) {
            return numberOfOrangeGoodsReady;
        }
        return -1;
    }
    int getNumberOfGoodsUndelivered(){
        if (goodColor == Player.Color.RED) {
            return numberOfRedGoodsUndelivered;
        } else if (goodColor == Player.Color.GREEN) {
            return numberOfGreenGoodsUndelivered;
        } else if (goodColor == Player.Color.WHITE) {
            return numberOfWhiteGoodsUndelivered;
        } else if (goodColor == Player.Color.ORANGE) {
            return numberOfOrangeGoodsUndelivered;
        }
        return -1;
    }
}
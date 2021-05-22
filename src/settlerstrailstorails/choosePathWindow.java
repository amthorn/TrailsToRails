/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * choosePathWindow.java
 *
 * Created on Oct 9, 2013, 11:52:14 AM
 */
package settlerstrailstorails;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;

/**
 *
 * @author User 1
 */
public class choosePathWindow extends javax.swing.JFrame {

    ArrayList<Path> p;
    Path choice=null,selection=null;
    boolean mouseOnOkPathButton, buttonPressed, pathOneSelected,
            pathTwoSelected,pathThreeSelected,pathFourSelected,pathFiveSelected;
    Player[] playerA;
    /** Creates new form choosePathWindow */
    public choosePathWindow() {
        initComponents();
    }
    choosePathWindow(ArrayList<Path> p, Player[] playerA) {
        initComponents();
        this.playerA=playerA;
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(((dim.width/2)-(this.getSize().width/2)), ((dim.height/2)-((this.getSize().height/2)+20)));
        try{
            setIconImage(ImageIO.read(getClass().getResource("/settlerstrailstorails/resources/icon.png")));
        }catch(Exception e){
            
        }
        pathThreeChoicePanel.setVisible(false);
        pathFourChoicePanel.setVisible(false);
        pathFiveChoicePanel.setVisible(false);
        this.p = p;
        Player currPlayer;
        if(playerA[0].isTurn()){
            currPlayer=playerA[0];
        }else if(playerA[1].isTurn()){
            currPlayer=playerA[1];
        }else if(playerA[2].isTurn()){
            currPlayer=playerA[2];
        }else{
            currPlayer=playerA[3];
        }
        ArrayList<String> choiceOneLabels = new ArrayList<String>();
        ArrayList<String> choiceTwoLabels = new ArrayList<String>();
        ArrayList<String> choiceThreeLabels = new ArrayList<String>();
        ArrayList<String> choiceFourLabels = new ArrayList<String>();
        ArrayList<String> choiceFiveLabels = new ArrayList<String>();
        for(int i=0;i<p.size();i++){
            Rail[] currR = p.get(i).getPath();
            int redPlayerGold=0,greenPlayerGold=0,orangePlayerGold=0,
                    whitePlayerGold=0;
            for(int j=0;j<currR.length;j++){
                if(currR[j].getColor()==Player.Color.RED){
                    redPlayerGold++;
                }else if(currR[j].getColor()==Player.Color.GREEN){
                    greenPlayerGold++;                    
                }else if(currR[j].getColor()==Player.Color.WHITE){
                    whitePlayerGold++;                    
                }else if(currR[j].getColor()==Player.Color.ORANGE){
                    orangePlayerGold++;                    
                }
            }
            if(i==0){
                if(currPlayer.getColor()==Player.Color.RED && redPlayerGold!=0){
                    choiceOneLabels.add("You: "+redPlayerGold+" gold.");
                }else if(currPlayer.getColor()==Player.Color.GREEN && greenPlayerGold!=0){
                    choiceOneLabels.add("You: "+greenPlayerGold+" gold.");
                }else if(currPlayer.getColor()==Player.Color.WHITE && whitePlayerGold!=0){
                    choiceOneLabels.add("You: "+whitePlayerGold+" gold.");
                }else if(currPlayer.getColor()==Player.Color.ORANGE && orangePlayerGold!=0){
                    choiceOneLabels.add("You: " + orangePlayerGold + " gold.");
                }
                if(playerA[0] != currPlayer) {
                    if (playerA[0].getColor() == Player.Color.RED && redPlayerGold!=0) {
                        choiceOneLabels.add(playerA[0].getColor() + ": " + redPlayerGold + " gold.");
                    } else if (playerA[0].getColor() == Player.Color.GREEN && greenPlayerGold!=0) {
                        choiceOneLabels.add(playerA[0].getColor() + ": " + greenPlayerGold + " gold.");
                    } else if (playerA[0].getColor() == Player.Color.WHITE && whitePlayerGold!=0) {
                        choiceOneLabels.add(playerA[0].getColor() + ": " + whitePlayerGold + " gold.");
                    } else if (playerA[0].getColor() == Player.Color.ORANGE && orangePlayerGold!=0) {
                        choiceOneLabels.add(playerA[0].getColor() + ": " + orangePlayerGold + " gold.");
                    }
                }
                if(playerA[1] != currPlayer) {
                    if (playerA[1].getColor() == Player.Color.RED && redPlayerGold!=0) {
                        choiceOneLabels.add(playerA[1].getColor() + ": " + redPlayerGold + " gold.");
                    } else if (playerA[1].getColor() == Player.Color.GREEN && greenPlayerGold!=0) {
                        choiceOneLabels.add(playerA[1].getColor() + ": " + greenPlayerGold + " gold.");
                    } else if (playerA[1].getColor() == Player.Color.WHITE && whitePlayerGold!=0) {
                        choiceOneLabels.add(playerA[1].getColor() + ": " + whitePlayerGold + " gold.");
                    } else if (playerA[1].getColor() == Player.Color.ORANGE && orangePlayerGold!=0) {
                        choiceOneLabels.add(playerA[1].getColor() + ": " + orangePlayerGold + " gold.");
                    }
                }
                if(playerA[2] != currPlayer) {
                    if (playerA[2].getColor() == Player.Color.RED && redPlayerGold!=0) {
                        choiceOneLabels.add(playerA[2].getColor() + ": " + redPlayerGold + " gold.");
                    } else if (playerA[2].getColor() == Player.Color.GREEN && greenPlayerGold!=0) {
                        choiceOneLabels.add(playerA[2].getColor() + ": " + greenPlayerGold + " gold.");
                    } else if (playerA[2].getColor() == Player.Color.WHITE && whitePlayerGold!=0) {
                        choiceOneLabels.add(playerA[2].getColor() + ": " + whitePlayerGold + " gold.");
                    } else if (playerA[2].getColor() == Player.Color.ORANGE && orangePlayerGold!=0) {
                        choiceOneLabels.add(playerA[2].getColor() + ": " + orangePlayerGold + " gold.");
                    }
                }
                if(playerA[3] != currPlayer) {
                    if (playerA[3].getColor() == Player.Color.RED && redPlayerGold!=0) {
                        choiceOneLabels.add(playerA[3].getColor() + ": " + redPlayerGold + " gold.");
                    } else if (playerA[3].getColor() == Player.Color.GREEN && greenPlayerGold!=0) {
                        choiceOneLabels.add(playerA[3].getColor() + ": " + greenPlayerGold + " gold.");
                    } else if (playerA[3].getColor() == Player.Color.WHITE && whitePlayerGold!=0) {
                        choiceOneLabels.add(playerA[3].getColor() + ": " + whitePlayerGold + " gold.");
                    } else if (playerA[3].getColor() == Player.Color.ORANGE && orangePlayerGold!=0) {
                        choiceOneLabels.add(playerA[3].getColor() + ": " + orangePlayerGold + " gold.");
                    }
                }
            }
            if(i==1){
                if(currPlayer.getColor()==Player.Color.RED && redPlayerGold!=0){
                    choiceTwoLabels.add("You: "+redPlayerGold+" gold.");
                }else if(currPlayer.getColor()==Player.Color.GREEN && greenPlayerGold!=0){
                    choiceTwoLabels.add("You: "+greenPlayerGold+" gold.");
                }else if(currPlayer.getColor()==Player.Color.WHITE && whitePlayerGold!=0){
                    choiceTwoLabels.add("You: "+whitePlayerGold+" gold.");
                }else if(currPlayer.getColor()==Player.Color.ORANGE && orangePlayerGold!=0){
                    choiceTwoLabels.add("You: " + orangePlayerGold + " gold.");
                }
                if(playerA[0] != currPlayer) {
                    if (playerA[0].getColor() == Player.Color.RED && redPlayerGold!=0) {
                        choiceTwoLabels.add(playerA[0].getColor() + ": " + redPlayerGold + " gold.");
                    } else if (playerA[0].getColor() == Player.Color.GREEN && greenPlayerGold!=0) {
                        choiceTwoLabels.add(playerA[0].getColor() + ": " + greenPlayerGold + " gold.");
                    } else if (playerA[0].getColor() == Player.Color.WHITE && whitePlayerGold!=0) {
                        choiceTwoLabels.add(playerA[0].getColor() + ": " + whitePlayerGold + " gold.");
                    } else if (playerA[0].getColor() == Player.Color.ORANGE && orangePlayerGold!=0) {
                        choiceTwoLabels.add(playerA[0].getColor() + ": " + orangePlayerGold + " gold.");
                    }
                }
                if(playerA[1] != currPlayer) {
                    if (playerA[1].getColor() == Player.Color.RED && redPlayerGold!=0) {
                        choiceTwoLabels.add(playerA[1].getColor() + ": " + redPlayerGold + " gold.");
                    } else if (playerA[1].getColor() == Player.Color.GREEN && greenPlayerGold!=0) {
                        choiceTwoLabels.add(playerA[1].getColor() + ": " + greenPlayerGold + " gold.");
                    } else if (playerA[1].getColor() == Player.Color.WHITE && whitePlayerGold!=0) {
                        choiceTwoLabels.add(playerA[1].getColor() + ": " + whitePlayerGold + " gold.");
                    } else if (playerA[1].getColor() == Player.Color.ORANGE && orangePlayerGold!=0) {
                        choiceTwoLabels.add(playerA[1].getColor() + ": " + orangePlayerGold + " gold.");
                    }
                }
                if(playerA[2] != currPlayer) {
                    if (playerA[2].getColor() == Player.Color.RED && redPlayerGold!=0) {
                        choiceTwoLabels.add(playerA[2].getColor() + ": " + redPlayerGold + " gold.");
                    } else if (playerA[2].getColor() == Player.Color.GREEN && greenPlayerGold!=0) {
                        choiceTwoLabels.add(playerA[2].getColor() + ": " + greenPlayerGold + " gold.");
                    } else if (playerA[2].getColor() == Player.Color.WHITE && whitePlayerGold!=0) {
                        choiceTwoLabels.add(playerA[2].getColor() + ": " + whitePlayerGold + " gold.");
                    } else if (playerA[2].getColor() == Player.Color.ORANGE && orangePlayerGold!=0) {
                        choiceTwoLabels.add(playerA[2].getColor() + ": " + orangePlayerGold + " gold.");
                    }
                }
                if(playerA[3] != currPlayer) {
                    if (playerA[3].getColor() == Player.Color.RED && redPlayerGold!=0) {
                        choiceTwoLabels.add(playerA[3].getColor() + ": " + redPlayerGold + " gold.");
                    } else if (playerA[3].getColor() == Player.Color.GREEN && greenPlayerGold!=0) {
                        choiceTwoLabels.add(playerA[3].getColor() + ": " + greenPlayerGold + " gold.");
                    } else if (playerA[3].getColor() == Player.Color.WHITE && whitePlayerGold!=0) {
                        choiceTwoLabels.add(playerA[3].getColor() + ": " + whitePlayerGold + " gold.");
                    } else if (playerA[3].getColor() == Player.Color.ORANGE && orangePlayerGold!=0) {
                        choiceTwoLabels.add(playerA[3].getColor() + ": " + orangePlayerGold + " gold.");
                    }
                }
            }
            if(i==2){
                if(currPlayer.getColor()==Player.Color.RED && redPlayerGold!=0){
                    choiceThreeLabels.add("You: "+redPlayerGold+" gold.");
                }else if(currPlayer.getColor()==Player.Color.GREEN && greenPlayerGold!=0){
                    choiceThreeLabels.add("You: "+greenPlayerGold+" gold.");
                }else if(currPlayer.getColor()==Player.Color.WHITE && whitePlayerGold!=0){
                    choiceThreeLabels.add("You: "+whitePlayerGold+" gold.");
                }else if(currPlayer.getColor()==Player.Color.ORANGE && orangePlayerGold!=0){
                    choiceThreeLabels.add("You: " + orangePlayerGold + " gold.");
                }
                if(playerA[0] != currPlayer) {
                    if (playerA[0].getColor() == Player.Color.RED && redPlayerGold!=0) {
                        choiceThreeLabels.add(playerA[0].getColor() + ": " + redPlayerGold + " gold.");
                    } else if (playerA[0].getColor() == Player.Color.GREEN && greenPlayerGold!=0) {
                        choiceThreeLabels.add(playerA[0].getColor() + ": " + greenPlayerGold + " gold.");
                    } else if (playerA[0].getColor() == Player.Color.WHITE && whitePlayerGold!=0) {
                        choiceThreeLabels.add(playerA[0].getColor() + ": " + whitePlayerGold + " gold.");
                    } else if (playerA[0].getColor() == Player.Color.ORANGE && orangePlayerGold!=0) {
                        choiceThreeLabels.add(playerA[0].getColor() + ": " + orangePlayerGold + " gold.");
                    }
                }
                if(playerA[1] != currPlayer) {
                    if (playerA[1].getColor() == Player.Color.RED && redPlayerGold!=0) {
                        choiceThreeLabels.add(playerA[1].getColor() + ": " + redPlayerGold + " gold.");
                    } else if (playerA[1].getColor() == Player.Color.GREEN && greenPlayerGold!=0) {
                        choiceThreeLabels.add(playerA[1].getColor() + ": " + greenPlayerGold + " gold.");
                    } else if (playerA[1].getColor() == Player.Color.WHITE && whitePlayerGold!=0) {
                        choiceThreeLabels.add(playerA[1].getColor() + ": " + whitePlayerGold + " gold.");
                    } else if (playerA[1].getColor() == Player.Color.ORANGE && orangePlayerGold!=0) {
                        choiceThreeLabels.add(playerA[1].getColor() + ": " + orangePlayerGold + " gold.");
                    }
                }
                if(playerA[2] != currPlayer) {
                    if (playerA[2].getColor() == Player.Color.RED && redPlayerGold!=0) {
                        choiceThreeLabels.add(playerA[2].getColor() + ": " + redPlayerGold + " gold.");
                    } else if (playerA[2].getColor() == Player.Color.GREEN && greenPlayerGold!=0) {
                        choiceThreeLabels.add(playerA[2].getColor() + ": " + greenPlayerGold + " gold.");
                    } else if (playerA[2].getColor() == Player.Color.WHITE && whitePlayerGold!=0) {
                        choiceThreeLabels.add(playerA[2].getColor() + ": " + whitePlayerGold + " gold.");
                    } else if (playerA[2].getColor() == Player.Color.ORANGE && orangePlayerGold!=0) {
                        choiceThreeLabels.add(playerA[2].getColor() + ": " + orangePlayerGold + " gold.");
                    }
                }
                if(playerA[3] != currPlayer) {
                    if (playerA[3].getColor() == Player.Color.RED && redPlayerGold!=0) {
                        choiceThreeLabels.add(playerA[3].getColor() + ": " + redPlayerGold + " gold.");
                    } else if (playerA[3].getColor() == Player.Color.GREEN && greenPlayerGold!=0) {
                        choiceThreeLabels.add(playerA[3].getColor() + ": " + greenPlayerGold + " gold.");
                    } else if (playerA[3].getColor() == Player.Color.WHITE && whitePlayerGold!=0) {
                        choiceThreeLabels.add(playerA[3].getColor() + ": " + whitePlayerGold + " gold.");
                    } else if (playerA[3].getColor() == Player.Color.ORANGE && orangePlayerGold!=0) {
                        choiceThreeLabels.add(playerA[3].getColor() + ": " + orangePlayerGold + " gold.");
                    }
                }
            }
            if(i==3){
                if(currPlayer.getColor()==Player.Color.RED && redPlayerGold!=0){
                    choiceFourLabels.add("You: "+redPlayerGold+" gold.");
                }else if(currPlayer.getColor()==Player.Color.GREEN && greenPlayerGold!=0){
                    choiceFourLabels.add("You: "+greenPlayerGold+" gold.");
                }else if(currPlayer.getColor()==Player.Color.WHITE && whitePlayerGold!=0){
                    choiceFourLabels.add("You: "+whitePlayerGold+" gold.");
                }else if(currPlayer.getColor()==Player.Color.ORANGE && orangePlayerGold!=0){
                    choiceFourLabels.add("You: " + orangePlayerGold + " gold.");
                }
                if(playerA[0] != currPlayer) {
                    if (playerA[0].getColor() == Player.Color.RED && redPlayerGold!=0) {
                        choiceFourLabels.add(playerA[0].getColor() + ": " + redPlayerGold + " gold.");
                    } else if (playerA[0].getColor() == Player.Color.GREEN && greenPlayerGold!=0) {
                        choiceFourLabels.add(playerA[0].getColor() + ": " + greenPlayerGold + " gold.");
                    } else if (playerA[0].getColor() == Player.Color.WHITE && whitePlayerGold!=0) {
                        choiceFourLabels.add(playerA[0].getColor() + ": " + whitePlayerGold + " gold.");
                    } else if (playerA[0].getColor() == Player.Color.ORANGE && orangePlayerGold!=0) {
                        choiceFourLabels.add(playerA[0].getColor() + ": " + orangePlayerGold + " gold.");
                    }
                }
                if(playerA[1] != currPlayer) {
                    if (playerA[1].getColor() == Player.Color.RED && redPlayerGold!=0) {
                        choiceFourLabels.add(playerA[1].getColor() + ": " + redPlayerGold + " gold.");
                    } else if (playerA[1].getColor() == Player.Color.GREEN && greenPlayerGold!=0) {
                        choiceFourLabels.add(playerA[1].getColor() + ": " + greenPlayerGold + " gold.");
                    } else if (playerA[1].getColor() == Player.Color.WHITE && whitePlayerGold!=0) {
                        choiceFourLabels.add(playerA[1].getColor() + ": " + whitePlayerGold + " gold.");
                    } else if (playerA[1].getColor() == Player.Color.ORANGE && orangePlayerGold!=0) {
                        choiceFourLabels.add(playerA[1].getColor() + ": " + orangePlayerGold + " gold.");
                    }
                }
                if(playerA[2] != currPlayer) {
                    if (playerA[2].getColor() == Player.Color.RED && redPlayerGold!=0) {
                        choiceFourLabels.add(playerA[2].getColor() + ": " + redPlayerGold + " gold.");
                    } else if (playerA[2].getColor() == Player.Color.GREEN && greenPlayerGold!=0) {
                        choiceFourLabels.add(playerA[2].getColor() + ": " + greenPlayerGold + " gold.");
                    } else if (playerA[2].getColor() == Player.Color.WHITE && whitePlayerGold!=0) {
                        choiceFourLabels.add(playerA[2].getColor() + ": " + whitePlayerGold + " gold.");
                    } else if (playerA[2].getColor() == Player.Color.ORANGE && orangePlayerGold!=0) {
                        choiceFourLabels.add(playerA[2].getColor() + ": " + orangePlayerGold + " gold.");
                    }
                }
                if(playerA[3] != currPlayer) {
                    if (playerA[3].getColor() == Player.Color.RED && redPlayerGold!=0) {
                        choiceFourLabels.add(playerA[3].getColor() + ": " + redPlayerGold + " gold.");
                    } else if (playerA[3].getColor() == Player.Color.GREEN && greenPlayerGold!=0) {
                        choiceFourLabels.add(playerA[3].getColor() + ": " + greenPlayerGold + " gold.");
                    } else if (playerA[3].getColor() == Player.Color.WHITE && whitePlayerGold!=0) {
                        choiceFourLabels.add(playerA[3].getColor() + ": " + whitePlayerGold + " gold.");
                    } else if (playerA[3].getColor() == Player.Color.ORANGE && orangePlayerGold!=0) {
                        choiceFourLabels.add(playerA[3].getColor() + ": " + orangePlayerGold + " gold.");
                    }
                }
            }
            if(i==4){
                if(currPlayer.getColor()==Player.Color.RED && redPlayerGold!=0){
                    choiceFiveLabels.add("You: "+redPlayerGold+" gold.");
                }else if(currPlayer.getColor()==Player.Color.GREEN && greenPlayerGold!=0){
                    choiceFiveLabels.add("You: "+greenPlayerGold+" gold.");
                }else if(currPlayer.getColor()==Player.Color.WHITE && whitePlayerGold!=0){
                    choiceFiveLabels.add("You: "+whitePlayerGold+" gold.");
                }else if(currPlayer.getColor()==Player.Color.ORANGE && orangePlayerGold!=0){
                    choiceFiveLabels.add("You: " + orangePlayerGold + " gold.");
                }
                if(playerA[0] != currPlayer) {
                    if (playerA[0].getColor() == Player.Color.RED && redPlayerGold!=0) {
                        choiceFiveLabels.add(playerA[0].getColor() + ": " + redPlayerGold + " gold.");
                    } else if (playerA[0].getColor() == Player.Color.GREEN && greenPlayerGold!=0) {
                        choiceFiveLabels.add(playerA[0].getColor() + ": " + greenPlayerGold + " gold.");
                    } else if (playerA[0].getColor() == Player.Color.WHITE && whitePlayerGold!=0) {
                        choiceFiveLabels.add(playerA[0].getColor() + ": " + whitePlayerGold + " gold.");
                    } else if (playerA[0].getColor() == Player.Color.ORANGE && orangePlayerGold!=0) {
                        choiceFiveLabels.add(playerA[0].getColor() + ": " + orangePlayerGold + " gold.");
                    }
                }
                if(playerA[1] != currPlayer) {
                    if (playerA[1].getColor() == Player.Color.RED && redPlayerGold!=0) {
                        choiceFiveLabels.add(playerA[1].getColor() + ": " + redPlayerGold + " gold.");
                    } else if (playerA[1].getColor() == Player.Color.GREEN && greenPlayerGold!=0) {
                        choiceFiveLabels.add(playerA[1].getColor() + ": " + greenPlayerGold + " gold.");
                    } else if (playerA[1].getColor() == Player.Color.WHITE && whitePlayerGold!=0) {
                        choiceFiveLabels.add(playerA[1].getColor() + ": " + whitePlayerGold + " gold.");
                    } else if (playerA[1].getColor() == Player.Color.ORANGE && orangePlayerGold!=0) {
                        choiceFiveLabels.add(playerA[1].getColor() + ": " + orangePlayerGold + " gold.");
                    }
                }
                if(playerA[2] != currPlayer) {
                    if (playerA[2].getColor() == Player.Color.RED && redPlayerGold!=0) {
                        choiceFiveLabels.add(playerA[2].getColor() + ": " + redPlayerGold + " gold.");
                    } else if (playerA[2].getColor() == Player.Color.GREEN && greenPlayerGold!=0) {
                        choiceFiveLabels.add(playerA[2].getColor() + ": " + greenPlayerGold + " gold.");
                    } else if (playerA[2].getColor() == Player.Color.WHITE && whitePlayerGold!=0) {
                        choiceFiveLabels.add(playerA[2].getColor() + ": " + whitePlayerGold + " gold.");
                    } else if (playerA[2].getColor() == Player.Color.ORANGE && orangePlayerGold!=0) {
                        choiceFiveLabels.add(playerA[2].getColor() + ": " + orangePlayerGold + " gold.");
                    }
                }
                if(playerA[3] != currPlayer) {
                    if (playerA[3].getColor() == Player.Color.RED && redPlayerGold!=0) {
                        choiceFiveLabels.add(playerA[3].getColor() + ": " + redPlayerGold + " gold.");
                    } else if (playerA[3].getColor() == Player.Color.GREEN && greenPlayerGold!=0) {
                        choiceFiveLabels.add(playerA[3].getColor() + ": " + greenPlayerGold + " gold.");
                    } else if (playerA[3].getColor() == Player.Color.WHITE && whitePlayerGold!=0) {
                        choiceFiveLabels.add(playerA[3].getColor() + ": " + whitePlayerGold + " gold.");
                    } else if (playerA[3].getColor() == Player.Color.ORANGE && orangePlayerGold!=0) {
                        choiceFiveLabels.add(playerA[3].getColor() + ": " + orangePlayerGold + " gold.");
                    }
                }
            }
        }
        if (choiceOneLabels.size()>0 && choiceOneLabels.get(0) != null) {
            pathOneYouGetLabel.setText(choiceOneLabels.get(0));
        }
        if (choiceOneLabels.size()>1 && choiceOneLabels.get(1) != null) {
            pathOnePlayerTwoGetsLabel.setText(choiceOneLabels.get(1));
        }
        if (choiceOneLabels.size()>2 && choiceOneLabels.get(2) != null) {
            pathOnePlayerThreeGetsLabel.setText(choiceOneLabels.get(2));
        }
        if (choiceOneLabels.size()>3 && choiceOneLabels.get(3) != null) {
            pathOnePlayerFourGetsLabel.setText(choiceOneLabels.get(3));
        }
        if (choiceTwoLabels.size()>0 && choiceTwoLabels.get(0) != null) {
            pathTwoYouGetLabel.setText(choiceTwoLabels.get(0));
        }
        if (choiceTwoLabels.size()>1 && choiceTwoLabels.get(1) != null) {
            pathTwoPlayerTwoGetsLabel.setText(choiceTwoLabels.get(1));
        }
        if (choiceTwoLabels.size()>2 && choiceTwoLabels.get(2) != null) {
            pathTwoPlayerThreeGetsLabel.setText(choiceTwoLabels.get(2));
        }
        if (choiceTwoLabels.size()>3 && choiceTwoLabels.get(3) != null) {
            pathTwoPlayerFourGetsLabel.setText(choiceTwoLabels.get(3));
        }
        if (choiceThreeLabels.size()>0 && choiceThreeLabels.get(0) != null) {
            pathThreeYouGetLabel.setText(choiceThreeLabels.get(0));
        }
        if (choiceThreeLabels.size()>1 && choiceThreeLabels.get(1) != null) {
            pathThreePlayerTwoGetsLabel.setText(choiceThreeLabels.get(1));
        }
        if (choiceThreeLabels.size()>2 && choiceThreeLabels.get(2) != null) {
            pathThreePlayerThreeGetsLabel.setText(choiceThreeLabels.get(2));
        }
        if (choiceThreeLabels.size()>3 && choiceThreeLabels.get(3) != null) {
            pathThreePlayerFourGetsLabel.setText(choiceThreeLabels.get(3));
        }
        if (choiceFourLabels.size()>0 && choiceFourLabels.get(0) != null) {
            pathFourYouGetLabel.setText(choiceFourLabels.get(0));
        }
        if (choiceFourLabels.size()>1 && choiceFourLabels.get(1) != null) {
            pathFourPlayerTwoGetsLabel.setText(choiceFourLabels.get(1));
        }
        if (choiceFourLabels.size()>2 && choiceFourLabels.get(2) != null) {
            pathFourPlayerThreeGetsLabel.setText(choiceFourLabels.get(2));
        }
        if (choiceFourLabels.size()>3 && choiceFourLabels.get(3) != null) {
            pathFourPlayerFourGetsLabel.setText(choiceFourLabels.get(3));
        }
        if (choiceFiveLabels.size()>0 && choiceFiveLabels.get(0) != null) {
            pathFiveYouGetLabel.setText(choiceFiveLabels.get(0));
        }
        if (choiceFiveLabels.size()>1 && choiceFiveLabels.get(1) != null) {
            pathFivePlayerTwoGetsLabel.setText(choiceFiveLabels.get(1));
        }
        if (choiceFiveLabels.size()>2 && choiceFiveLabels.get(2) != null) {
            pathFivePlayerThreeGetsLabel.setText(choiceFiveLabels.get(2));
        }
        if (choiceFiveLabels.size()>3 && choiceFiveLabels.get(3) != null) {
            pathFivePlayerFourGetsLabel.setText(choiceFiveLabels.get(3));
        }
        
        
        if(p.size()==3)pathThreeChoicePanel.setVisible(true);
        if(p.size()==4)pathFourChoicePanel.setVisible(true);
        if(p.size()==5)pathFiveChoicePanel.setVisible(true);
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
        topLabel = new javax.swing.JLabel();
        pathFiveChoicePanel = new javax.swing.JPanel();
        pathFiveSelectButton = new javax.swing.JToggleButton();
        pathFiveYouGetLabel = new javax.swing.JLabel();
        pathFivePlayerTwoGetsLabel = new javax.swing.JLabel();
        pathFivePlayerThreeGetsLabel = new javax.swing.JLabel();
        pathFivePlayerFourGetsLabel = new javax.swing.JLabel();
        pathFourChoicePanel = new javax.swing.JPanel();
        pathFourSelectButton = new javax.swing.JToggleButton();
        pathFourYouGetLabel = new javax.swing.JLabel();
        pathFourPlayerTwoGetsLabel = new javax.swing.JLabel();
        pathFourPlayerThreeGetsLabel = new javax.swing.JLabel();
        pathFourPlayerFourGetsLabel = new javax.swing.JLabel();
        pathThreeChoicePanel = new javax.swing.JPanel();
        pathThreeSelectButton = new javax.swing.JToggleButton();
        pathThreeYouGetLabel = new javax.swing.JLabel();
        pathThreePlayerTwoGetsLabel = new javax.swing.JLabel();
        pathThreePlayerThreeGetsLabel = new javax.swing.JLabel();
        pathThreePlayerFourGetsLabel = new javax.swing.JLabel();
        pathTwoChoicePanel = new javax.swing.JPanel();
        pathTwoSelectButton = new javax.swing.JToggleButton();
        pathTwoYouGetLabel = new javax.swing.JLabel();
        pathTwoPlayerTwoGetsLabel = new javax.swing.JLabel();
        pathTwoPlayerThreeGetsLabel = new javax.swing.JLabel();
        pathTwoPlayerFourGetsLabel = new javax.swing.JLabel();
        pathOneChoicePanel = new javax.swing.JPanel();
        pathOneSelectButton = new javax.swing.JToggleButton();
        pathOneYouGetLabel = new javax.swing.JLabel();
        pathOnePlayerTwoGetsLabel = new javax.swing.JLabel();
        pathOnePlayerThreeGetsLabel = new javax.swing.JLabel();
        pathOnePlayerFourGetsLabel = new javax.swing.JLabel();
        okPathButton = new javax.swing.JButton();
        backgroundLabel = new javax.swing.JLabel();

        setAlwaysOnTop(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        mainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        topLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        topLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        topLabel.setText("Select an option:");
        mainPanel.add(topLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 360, -1));

        pathFiveChoicePanel.setBackground(new java.awt.Color(227, 216, 168));
        pathFiveChoicePanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pathFiveChoicePanel.setPreferredSize(new java.awt.Dimension(104, 104));
        pathFiveChoicePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pathFiveSelectButton.setBackground(new java.awt.Color(239, 228, 176));
        pathFiveSelectButton.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        pathFiveSelectButton.setText("Select");
        pathFiveSelectButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pathFiveSelectButton.setContentAreaFilled(false);
        pathFiveSelectButton.setFocusable(false);
        pathFiveSelectButton.setOpaque(true);
        pathFiveSelectButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pathFiveSelectButtonMousePressed(evt);
            }
        });
        pathFiveChoicePanel.add(pathFiveSelectButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 22, 60, 25));

        pathFiveYouGetLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        pathFiveChoicePanel.add(pathFiveYouGetLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        pathFivePlayerTwoGetsLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        pathFiveChoicePanel.add(pathFivePlayerTwoGetsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        pathFivePlayerThreeGetsLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        pathFiveChoicePanel.add(pathFivePlayerThreeGetsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, -1, -1));

        pathFivePlayerFourGetsLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        pathFiveChoicePanel.add(pathFivePlayerFourGetsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, -1, -1));

        mainPanel.add(pathFiveChoicePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 340, 70));

        pathFourChoicePanel.setBackground(new java.awt.Color(227, 216, 168));
        pathFourChoicePanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pathFourChoicePanel.setPreferredSize(new java.awt.Dimension(104, 104));
        pathFourChoicePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pathFourSelectButton.setBackground(new java.awt.Color(239, 228, 176));
        pathFourSelectButton.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        pathFourSelectButton.setText("Select");
        pathFourSelectButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pathFourSelectButton.setContentAreaFilled(false);
        pathFourSelectButton.setFocusable(false);
        pathFourSelectButton.setOpaque(true);
        pathFourSelectButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pathFourSelectButtonMousePressed(evt);
            }
        });
        pathFourChoicePanel.add(pathFourSelectButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 22, 60, 25));

        pathFourYouGetLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        pathFourChoicePanel.add(pathFourYouGetLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        pathFourPlayerTwoGetsLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        pathFourChoicePanel.add(pathFourPlayerTwoGetsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        pathFourPlayerThreeGetsLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        pathFourChoicePanel.add(pathFourPlayerThreeGetsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, -1, -1));

        pathFourPlayerFourGetsLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        pathFourChoicePanel.add(pathFourPlayerFourGetsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, -1, -1));

        mainPanel.add(pathFourChoicePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 340, 70));

        pathThreeChoicePanel.setBackground(new java.awt.Color(227, 216, 168));
        pathThreeChoicePanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pathThreeChoicePanel.setPreferredSize(new java.awt.Dimension(104, 104));
        pathThreeChoicePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pathThreeSelectButton.setBackground(new java.awt.Color(239, 228, 176));
        pathThreeSelectButton.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        pathThreeSelectButton.setText("Select");
        pathThreeSelectButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pathThreeSelectButton.setContentAreaFilled(false);
        pathThreeSelectButton.setFocusable(false);
        pathThreeSelectButton.setOpaque(true);
        pathThreeSelectButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pathThreeSelectButtonMousePressed(evt);
            }
        });
        pathThreeChoicePanel.add(pathThreeSelectButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 22, 60, 25));

        pathThreeYouGetLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        pathThreeChoicePanel.add(pathThreeYouGetLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        pathThreePlayerTwoGetsLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        pathThreeChoicePanel.add(pathThreePlayerTwoGetsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        pathThreePlayerThreeGetsLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        pathThreeChoicePanel.add(pathThreePlayerThreeGetsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, -1, -1));

        pathThreePlayerFourGetsLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        pathThreeChoicePanel.add(pathThreePlayerFourGetsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, -1, -1));

        mainPanel.add(pathThreeChoicePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 340, 70));

        pathTwoChoicePanel.setBackground(new java.awt.Color(227, 216, 168));
        pathTwoChoicePanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pathTwoChoicePanel.setPreferredSize(new java.awt.Dimension(104, 104));
        pathTwoChoicePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pathTwoSelectButton.setBackground(new java.awt.Color(239, 228, 176));
        pathTwoSelectButton.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        pathTwoSelectButton.setText("Select");
        pathTwoSelectButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pathTwoSelectButton.setContentAreaFilled(false);
        pathTwoSelectButton.setFocusable(false);
        pathTwoSelectButton.setOpaque(true);
        pathTwoSelectButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pathTwoSelectButtonMousePressed(evt);
            }
        });
        pathTwoChoicePanel.add(pathTwoSelectButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 22, 60, 25));

        pathTwoYouGetLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        pathTwoChoicePanel.add(pathTwoYouGetLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        pathTwoPlayerTwoGetsLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        pathTwoChoicePanel.add(pathTwoPlayerTwoGetsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        pathTwoPlayerThreeGetsLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        pathTwoChoicePanel.add(pathTwoPlayerThreeGetsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, -1, -1));

        pathTwoPlayerFourGetsLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        pathTwoChoicePanel.add(pathTwoPlayerFourGetsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, -1, -1));

        mainPanel.add(pathTwoChoicePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 340, 70));

        pathOneChoicePanel.setBackground(new java.awt.Color(227, 216, 168));
        pathOneChoicePanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pathOneChoicePanel.setPreferredSize(new java.awt.Dimension(104, 104));
        pathOneChoicePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pathOneSelectButton.setBackground(new java.awt.Color(239, 228, 176));
        pathOneSelectButton.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        pathOneSelectButton.setText("Select");
        pathOneSelectButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pathOneSelectButton.setContentAreaFilled(false);
        pathOneSelectButton.setFocusable(false);
        pathOneSelectButton.setOpaque(true);
        pathOneSelectButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pathOneSelectButtonMousePressed(evt);
            }
        });
        pathOneChoicePanel.add(pathOneSelectButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 22, 60, 25));

        pathOneYouGetLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        pathOneChoicePanel.add(pathOneYouGetLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        pathOnePlayerTwoGetsLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        pathOneChoicePanel.add(pathOnePlayerTwoGetsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        pathOnePlayerThreeGetsLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        pathOneChoicePanel.add(pathOnePlayerThreeGetsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, -1, -1));

        pathOnePlayerFourGetsLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14));
        pathOneChoicePanel.add(pathOnePlayerFourGetsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, -1, -1));
        pathOnePlayerFourGetsLabel.getAccessibleContext().setAccessibleName("You get 60 gold.");

        mainPanel.add(pathOneChoicePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 340, 70));

        okPathButton.setBackground(new java.awt.Color(239, 228, 176));
        okPathButton.setFont(new java.awt.Font("Tahoma", 1, 11));
        okPathButton.setText("OK");
        okPathButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        okPathButton.setContentAreaFilled(false);
        okPathButton.setFocusable(false);
        okPathButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                okPathButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                okPathButtonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                okPathButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                okPathButtonMouseReleased(evt);
            }
        });
        mainPanel.add(okPathButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(142, 465, 110, 30));

        backgroundLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/MainMenuBackground.png"))); // NOI18N
        mainPanel.add(backgroundLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(mainPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, 520));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okPathButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okPathButtonMouseEntered
        mouseOnOkPathButton=true;
}//GEN-LAST:event_okPathButtonMouseEntered
    private void okPathButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okPathButtonMouseExited
        if(mouseOnOkPathButton&&buttonPressed){
            okPathButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            okPathButton.setLocation(okPathButton.getX() + 1, okPathButton.getY());
        }
        mouseOnOkPathButton=false;
        buttonPressed=false;
}//GEN-LAST:event_okPathButtonMouseExited
    private void okPathButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okPathButtonMousePressed
        gameFrame.playSound("click");
        okPathButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        okPathButton.setLocation(okPathButton.getX()-1, okPathButton.getY());
        buttonPressed=true;
}//GEN-LAST:event_okPathButtonMousePressed
    private void okPathButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okPathButtonMouseReleased
        buttonPressed=false;
        if(mouseOnOkPathButton) {
            okPathButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            okPathButton.setLocation(okPathButton.getX() + 1, okPathButton.getY());
            if(selection != null) {
                this.setVisible(false);
                choice = selection;
                Rail[] currR = selection.getPath();
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
        }
}//GEN-LAST:event_okPathButtonMouseReleased
    private void pathOneSelectButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pathOneSelectButtonMousePressed
        if (!pathOneSelected) {
            selection=p.get(0);
            gameFrame.playSound("click");
                pathOneSelectButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
                pathOneSelectButton.setLocation(pathOneSelectButton.getX() - 1, pathOneSelectButton.getY());
            if(pathTwoSelected){
                pathTwoSelectButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                pathTwoSelectButton.setLocation(pathTwoSelectButton.getX() + 1, pathTwoSelectButton.getY());
            }else if(pathThreeSelected){
                pathThreeSelectButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                pathThreeSelectButton.setLocation(pathThreeSelectButton.getX() + 1, pathThreeSelectButton.getY());
            }else if(pathFourSelected){
                pathFourSelectButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                pathFourSelectButton.setLocation(pathFourSelectButton.getX() + 1, pathFourSelectButton.getY());
            }else if(pathFiveSelected){
                pathFiveSelectButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                pathFiveSelectButton.setLocation(pathFiveSelectButton.getX() + 1, pathFiveSelectButton.getY());
            }
            pathOneSelected = true;
            pathTwoSelected = false;
            pathThreeSelected = false;
            pathFourSelected = false;
            pathFiveSelected = false;
        }
}//GEN-LAST:event_pathOneSelectButtonMousePressed
    private void pathTwoSelectButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pathTwoSelectButtonMousePressed
        if (!pathTwoSelected) {
            selection=p.get(1);
            gameFrame.playSound("click");
                pathTwoSelectButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
                pathTwoSelectButton.setLocation(pathTwoSelectButton.getX() - 1, pathTwoSelectButton.getY());
            if(pathOneSelected){
                pathOneSelectButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                pathOneSelectButton.setLocation(pathOneSelectButton.getX() + 1, pathOneSelectButton.getY());
            }else if(pathThreeSelected){
                pathThreeSelectButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                pathThreeSelectButton.setLocation(pathThreeSelectButton.getX() + 1, pathThreeSelectButton.getY());
            }else if(pathFourSelected){
                pathFourSelectButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                pathFourSelectButton.setLocation(pathFourSelectButton.getX() + 1, pathFourSelectButton.getY());
            }else if(pathFiveSelected){
                pathFiveSelectButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                pathFiveSelectButton.setLocation(pathFiveSelectButton.getX() + 1, pathFiveSelectButton.getY());
            }
            pathOneSelected = false;
            pathTwoSelected = true;
            pathThreeSelected = false;
            pathFourSelected = false;
            pathFiveSelected = false;
        }
    }//GEN-LAST:event_pathTwoSelectButtonMousePressed
    private void pathThreeSelectButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pathThreeSelectButtonMousePressed
        if (!pathThreeSelected) {
            selection=p.get(2);
            gameFrame.playSound("click");
                pathThreeSelectButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
                pathThreeSelectButton.setLocation(pathThreeSelectButton.getX() - 1, pathThreeSelectButton.getY());
            if(pathTwoSelected){
                pathTwoSelectButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                pathTwoSelectButton.setLocation(pathTwoSelectButton.getX() + 1, pathTwoSelectButton.getY());
            }else if(pathOneSelected){
                pathOneSelectButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                pathOneSelectButton.setLocation(pathOneSelectButton.getX() + 1, pathOneSelectButton.getY());
            }else if(pathFourSelected){
                pathFourSelectButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                pathFourSelectButton.setLocation(pathFourSelectButton.getX() + 1, pathFourSelectButton.getY());
            }else if(pathFiveSelected){
                pathFiveSelectButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                pathFiveSelectButton.setLocation(pathFiveSelectButton.getX() + 1, pathFiveSelectButton.getY());
            }
            pathOneSelected = false;
            pathTwoSelected = false;
            pathThreeSelected = true;
            pathFourSelected = false;
            pathFiveSelected = false;
        }
    }//GEN-LAST:event_pathThreeSelectButtonMousePressed
    private void pathFourSelectButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pathFourSelectButtonMousePressed
        if (!pathFourSelected) {
            selection=p.get(3);
            gameFrame.playSound("click");
                pathFourSelectButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
                pathFourSelectButton.setLocation(pathFourSelectButton.getX() - 1, pathFourSelectButton.getY());
            if(pathTwoSelected){
                pathTwoSelectButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                pathTwoSelectButton.setLocation(pathTwoSelectButton.getX() + 1, pathTwoSelectButton.getY());
            }else if(pathThreeSelected){
                pathThreeSelectButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                pathThreeSelectButton.setLocation(pathThreeSelectButton.getX() + 1, pathThreeSelectButton.getY());
            }else if(pathOneSelected){
                pathOneSelectButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                pathOneSelectButton.setLocation(pathOneSelectButton.getX() + 1, pathOneSelectButton.getY());
            }else if(pathFiveSelected){
                pathFiveSelectButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                pathFiveSelectButton.setLocation(pathFiveSelectButton.getX() + 1, pathFiveSelectButton.getY());
            }
            pathOneSelected = false;
            pathTwoSelected = false;
            pathThreeSelected = false;
            pathFourSelected = true;
            pathFiveSelected = false;
        }
    }//GEN-LAST:event_pathFourSelectButtonMousePressed
    private void pathFiveSelectButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pathFiveSelectButtonMousePressed
        if (!pathFiveSelected) {
            selection=p.get(4);
            gameFrame.playSound("click");
                pathFiveSelectButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
                pathFiveSelectButton.setLocation(pathFiveSelectButton.getX() - 1, pathFiveSelectButton.getY());
            if(pathTwoSelected){
                pathTwoSelectButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                pathTwoSelectButton.setLocation(pathTwoSelectButton.getX() + 1, pathTwoSelectButton.getY());
            }else if(pathThreeSelected){
                pathThreeSelectButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                pathThreeSelectButton.setLocation(pathThreeSelectButton.getX() + 1, pathThreeSelectButton.getY());
            }else if(pathFourSelected){
                pathFourSelectButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                pathFourSelectButton.setLocation(pathFourSelectButton.getX() + 1, pathFourSelectButton.getY());
            }else if(pathOneSelected){
                pathOneSelectButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                pathOneSelectButton.setLocation(pathOneSelectButton.getX() + 1, pathOneSelectButton.getY());
            }
            pathOneSelected = false;
            pathTwoSelected = false;
            pathThreeSelected = false;
            pathFourSelected = false;
            pathFiveSelected = true;
        }
    }//GEN-LAST:event_pathFiveSelectButtonMousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new choosePathWindow().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel backgroundLabel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton okPathButton;
    private javax.swing.JPanel pathFiveChoicePanel;
    private javax.swing.JLabel pathFivePlayerFourGetsLabel;
    private javax.swing.JLabel pathFivePlayerThreeGetsLabel;
    private javax.swing.JLabel pathFivePlayerTwoGetsLabel;
    private javax.swing.JToggleButton pathFiveSelectButton;
    private javax.swing.JLabel pathFiveYouGetLabel;
    private javax.swing.JPanel pathFourChoicePanel;
    private javax.swing.JLabel pathFourPlayerFourGetsLabel;
    private javax.swing.JLabel pathFourPlayerThreeGetsLabel;
    private javax.swing.JLabel pathFourPlayerTwoGetsLabel;
    private javax.swing.JToggleButton pathFourSelectButton;
    private javax.swing.JLabel pathFourYouGetLabel;
    private javax.swing.JPanel pathOneChoicePanel;
    private javax.swing.JLabel pathOnePlayerFourGetsLabel;
    private javax.swing.JLabel pathOnePlayerThreeGetsLabel;
    private javax.swing.JLabel pathOnePlayerTwoGetsLabel;
    private javax.swing.JToggleButton pathOneSelectButton;
    private javax.swing.JLabel pathOneYouGetLabel;
    private javax.swing.JPanel pathThreeChoicePanel;
    private javax.swing.JLabel pathThreePlayerFourGetsLabel;
    private javax.swing.JLabel pathThreePlayerThreeGetsLabel;
    private javax.swing.JLabel pathThreePlayerTwoGetsLabel;
    private javax.swing.JToggleButton pathThreeSelectButton;
    private javax.swing.JLabel pathThreeYouGetLabel;
    private javax.swing.JPanel pathTwoChoicePanel;
    private javax.swing.JLabel pathTwoPlayerFourGetsLabel;
    private javax.swing.JLabel pathTwoPlayerThreeGetsLabel;
    private javax.swing.JLabel pathTwoPlayerTwoGetsLabel;
    private javax.swing.JToggleButton pathTwoSelectButton;
    private javax.swing.JLabel pathTwoYouGetLabel;
    private javax.swing.JLabel topLabel;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SettlersConfirmDialog.java
 *
 * Created on Sep 28, 2013, 4:28:53 PM
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
public class SettlersConfirmDialog extends javax.swing.JFrame {

    boolean mouseOnYesButton,mouseOnNoButton,buttonPressed,mouseOnApplyButton,
            mouseOnCancelButton,mouseOnCantBuildSettlementButton,
            mouseOnCheatSuccessButton,mouseOnCheatFailButton,mouseOnNoButtonCustomMap,
            mouseOnYesButtonCustomMap,mouseOnNineChitButton,mouseOnTenChitButton,
            mouseOnElevenChitButton,mouseOnCannotChitButton,
            mouseOnNotEnoughResourcesButton, mouseOnNoSettlersButton,
            mouseOnYesSettlersButton,mouseOnTooManyCitiesButton,
            mouseOnTooManyRailsButton,mouseOnNoSettlerButton;
    gameFrame game_Frame;
    String currName;
    int currNameIndex;
    MainFrame mf;
    ArrayList<Integer> chits;
    customMapWindow cmw;
    Player currPlayer,winnerPlayer;
    buildWindow bw;
    AvaJButton yesButtonTrain,noButtonTrain,okayButtonWinner,
            notEnoughGoldButton,dpCardButton,noCardsButton,alreadyPlayedDPButton,
            scoutChoiceSettlerButton,scoutChoiceGoldButton,nativeSupportButton,
            mustRollButton,cannotCloseButton,playerAcceptedTradeButton,
            noAcceptedTradeButton,selectResourceGetButton,
            selectResourceGiveButton;
    DevelopmentCard dCard;
    developmentCardWindow dcw;
    SettlersConfirmDialog(gameFrame g, int i,MainFrame mf) {
        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        try{
            setIconImage(ImageIO.read(getClass().getResource("/settlerstrailstorails/resources/icon.png")));
        }catch(Exception e){
            
        }
        this.mf=mf;
        
        //set all panels' visibility to false initially
        exitDialogPanel.setVisible(false);
        nameChangePanel.setVisible(false);
        cantBuildSettlementPanel.setVisible(false);
        cheatSuccessPanel.setVisible(false);
        cheatFailPanel.setVisible(false);
        saveCustomMapPanel.setVisible(false);
        cannotClosePanel.setVisible(false);
        customMapChitPanel.setVisible(false);
        customMapCannotChit.setVisible(false);
        notEnoughResourcesPanel.setVisible(false);
        alreadyHaveSettlersPanel.setVisible(false);
        scoutCardChoicePanel.setVisible(false);
        selectResourceGivePanel.setVisible(false);
        selectResourceGetPanel.setVisible(false);
        nativeSupportPanel.setVisible(false);
        alreadyHaveRailsPanel.setVisible(false);
        tooManyCitiesPanel.setVisible(false);
        noSettlerPanel.setVisible(false);
        alreadyHaveTrainsPanel.setVisible(false);
        winnerPanel.setVisible(false);
        notEnoughGoldPanel.setVisible(false);
        dpCardPanel.setVisible(false);
        noCardsPanel.setVisible(false);
        alreadyPlayedDPPanel.setVisible(false);
        mustRollPanel.setVisible(false);
        noAcceptTradePanel.setVisible(false);
        playerAcceptedTradePanel.setVisible(false);
        
        //choose a case to display
        switch(i){
            //exit dialog case
            case 1: exitDialogPanel.setVisible(true);
                break;
            case 2: nameChangePanel.setVisible(true);
                break;
            case 3: cantBuildSettlementPanel.setVisible(true);
                break;
            case 4: cheatSuccessPanel.setVisible(true);
                break;
            case 5: cheatFailPanel.setVisible(true);
                break;
            case 6:saveCustomMapPanel.setVisible(true);
                break;
            case 7: customMapChitPanel.setVisible(true);
                break;
            case 8: customMapCannotChit.setVisible(true);
                break;
            default:
                break;
        }
        game_Frame=g;
    }
    public SettlersConfirmDialog(int i){        
        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        try{
            setIconImage(ImageIO.read(getClass().getResource("/settlerstrailstorails/resources/icon.png")));
        }catch(Exception e){
            
        }
        
        //set all panels' visibility to false initially
        exitDialogPanel.setVisible(false);
        nameChangePanel.setVisible(false);
        cantBuildSettlementPanel.setVisible(false);
        cheatSuccessPanel.setVisible(false);
        cheatFailPanel.setVisible(false);
        saveCustomMapPanel.setVisible(false);
        customMapChitPanel.setVisible(false);
        customMapCannotChit.setVisible(false);
        notEnoughResourcesPanel.setVisible(false);
        selectResourceGivePanel.setVisible(false);
        selectResourceGetPanel.setVisible(false);
        alreadyHaveSettlersPanel.setVisible(false);
        alreadyHaveRailsPanel.setVisible(false);
        tooManyCitiesPanel.setVisible(false);
        noSettlerPanel.setVisible(false);
        alreadyHaveTrainsPanel.setVisible(false);
        winnerPanel.setVisible(false);
        notEnoughGoldPanel.setVisible(false);
        dpCardPanel.setVisible(false);
        scoutCardChoicePanel.setVisible(false);
        noCardsPanel.setVisible(false);
        alreadyPlayedDPPanel.setVisible(false);
        nativeSupportPanel.setVisible(false);
        noAcceptTradePanel.setVisible(false);
        playerAcceptedTradePanel.setVisible(false);
        mustRollPanel.setVisible(false);
        cannotClosePanel.setVisible(false);
        noAcceptedTradeButton = new AvaJButton(noAcceptedTradeButton1);
        alreadyPlayedDPButton = new AvaJButton(alreadyPlayedDPButton1);
        yesButtonTrain = new AvaJButton(yesButtonTrain1);
        noButtonTrain = new AvaJButton(noButtonTrain1);
        notEnoughGoldButton = new AvaJButton(notEnoughGoldButton1);
        dpCardButton = new AvaJButton(okayButtonDPCard1);
        noCardsButton = new AvaJButton(noCardsButton1);
        nativeSupportButton = new AvaJButton(nativeSupportButton1);
        mustRollButton = new AvaJButton(mustRollButton1);
        cannotCloseButton = new AvaJButton(cannotCloseButton1);
        selectResourceGiveButton = new AvaJButton(selectResourceGiveButton1);
        selectResourceGetButton = new AvaJButton(selectResourceGetButton1);
        
        //choose a case to display
        switch(i){
            //exit dialog case
            case 1: exitDialogPanel.setVisible(true);
                break;
            case 2: nameChangePanel.setVisible(true);
                break;
            case 3: cantBuildSettlementPanel.setVisible(true);
                break;
            case 4: cheatSuccessPanel.setVisible(true);
                break;
            case 5: cheatFailPanel.setVisible(true);
                break;
            case 6: saveCustomMapPanel.setVisible(true);
                break;
            case 7: customMapChitPanel.setVisible(true);
                break;
            case 8: customMapCannotChit.setVisible(true);
                break;
            case 9: notEnoughResourcesPanel.setVisible(true);
                break;
            case 10: alreadyHaveSettlersPanel.setVisible(true);
                break;
            case 11: tooManyCitiesPanel.setVisible(true);
                break;
            case 12: alreadyHaveRailsPanel.setVisible(true);
                break;
            case 13: noSettlerPanel.setVisible(true);
                break;
            case 14: notEnoughGoldPanel.setVisible(true);
                break;
            case 15: dpCardPanel.setVisible(true);
                break;
            case 16: noCardsPanel.setVisible(true);
                break;
            case 17: alreadyPlayedDPPanel.setVisible(true);
                break;
            case 18: nativeSupportPanel.setVisible(true);
                break;
            case 19: mustRollPanel.setVisible(true);
                break;
            case 20: cannotClosePanel.setVisible(true);
                break;
            case 21: noAcceptTradePanel.setVisible(true);
                break;
            case 22: selectResourceGetPanel.setVisible(true);
                break;
            case 23: selectResourceGivePanel.setVisible(true);
                break;
            default:
                break;
        }
    }
    public SettlersConfirmDialog(ArrayList<Integer> chits, MainFrame mf, int i, customMapWindow cmw) {
        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        try{
            setIconImage(ImageIO.read(getClass().getResource("/settlerstrailstorails/resources/icon.png")));
        }catch(Exception e){
            
        }
        this.cmw=cmw;
        
        //set all panels' visibility to false initially
        exitDialogPanel.setVisible(false);
        nameChangePanel.setVisible(false);
        cantBuildSettlementPanel.setVisible(false);
        cheatSuccessPanel.setVisible(false);
        cannotClosePanel.setVisible(false);
        cheatFailPanel.setVisible(false);
        saveCustomMapPanel.setVisible(false);
        mustRollPanel.setVisible(false);
        customMapChitPanel.setVisible(false);
        selectResourceGivePanel.setVisible(false);
        selectResourceGetPanel.setVisible(false);
        customMapCannotChit.setVisible(false);
        nativeSupportPanel.setVisible(false);
        noAcceptTradePanel.setVisible(false);
        playerAcceptedTradePanel.setVisible(false);
        scoutCardChoicePanel.setVisible(false);
        notEnoughResourcesPanel.setVisible(false);
        alreadyHaveSettlersPanel.setVisible(false);
        alreadyHaveRailsPanel.setVisible(false);
        tooManyCitiesPanel.setVisible(false);
        noSettlerPanel.setVisible(false);
        alreadyHaveTrainsPanel.setVisible(false);
        winnerPanel.setVisible(false);
        notEnoughGoldPanel.setVisible(false);
        dpCardPanel.setVisible(false);
        noCardsPanel.setVisible(false);
        alreadyPlayedDPPanel.setVisible(false);
        
        //choose a case to display
        switch(i){
            //exit dialog case
            case 1: exitDialogPanel.setVisible(true);
                break;
            case 2: nameChangePanel.setVisible(true);
                break;
            case 3: cantBuildSettlementPanel.setVisible(true);
                break;
            case 4: cheatSuccessPanel.setVisible(true);
                break;
            case 5: cheatFailPanel.setVisible(true);
                break;
            case 6: saveCustomMapPanel.setVisible(true);
                break;
            case 7: customMapChitPanel.setVisible(true);
                break;
            case 8: customMapCannotChit.setVisible(true);
                break;
            default:
                break;
        }
        this.chits=chits;
        this.mf=mf;
    }
    public SettlersConfirmDialog(int i, customMapWindow cmw){
        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        try{
            setIconImage(ImageIO.read(getClass().getResource("/settlerstrailstorails/resources/icon.png")));
        }catch(Exception e){
            
        }
        this.cmw=cmw;
        
        //set all panels' visibility to false initially
        exitDialogPanel.setVisible(false);
        cannotClosePanel.setVisible(false);
        nameChangePanel.setVisible(false);
        cantBuildSettlementPanel.setVisible(false);
        cheatSuccessPanel.setVisible(false);
        cheatFailPanel.setVisible(false);
        nativeSupportPanel.setVisible(false);
        saveCustomMapPanel.setVisible(false);
        mustRollPanel.setVisible(false);
        noAcceptTradePanel.setVisible(false);
        playerAcceptedTradePanel.setVisible(false);
        customMapChitPanel.setVisible(false);
        scoutCardChoicePanel.setVisible(false);
        customMapCannotChit.setVisible(false);
        notEnoughResourcesPanel.setVisible(false);
        alreadyHaveSettlersPanel.setVisible(false);
        alreadyHaveRailsPanel.setVisible(false);
        tooManyCitiesPanel.setVisible(false);
        selectResourceGivePanel.setVisible(false);
        selectResourceGetPanel.setVisible(false);
        noSettlerPanel.setVisible(false);
        alreadyHaveTrainsPanel.setVisible(false);
        winnerPanel.setVisible(false);
        notEnoughGoldPanel.setVisible(false);
        dpCardPanel.setVisible(false);
        noCardsPanel.setVisible(false);
        alreadyPlayedDPPanel.setVisible(false);
        
        //choose a case to display
        switch(i){
            //exit dialog case
            case 1: exitDialogPanel.setVisible(true);
                break;
            case 2: nameChangePanel.setVisible(true);
                break;
            case 3: cantBuildSettlementPanel.setVisible(true);
                break;
            case 4: cheatSuccessPanel.setVisible(true);
                break;
            case 5: cheatFailPanel.setVisible(true);
                break;
            case 6: saveCustomMapPanel.setVisible(true);
                break;
            case 7: customMapChitPanel.setVisible(true);
                break;
            case 8: customMapCannotChit.setVisible(true);
                break;
            default:
                break;
        }
    }
    SettlersConfirmDialog(int i, Player p, gameFrame g, buildWindow b) {
        initComponents();
        bw = b;
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        try{
            setIconImage(ImageIO.read(getClass().getResource("/settlerstrailstorails/resources/icon.png")));
        }catch(Exception e){
            
        }
        currPlayer = p;
        game_Frame = g;
        //set all panels' visibility to false initially
        exitDialogPanel.setVisible(false);
        mustRollPanel.setVisible(false);
        cannotClosePanel.setVisible(false);
        nameChangePanel.setVisible(false);
        noAcceptTradePanel.setVisible(false);
        playerAcceptedTradePanel.setVisible(false);
        cantBuildSettlementPanel.setVisible(false);
        cheatSuccessPanel.setVisible(false);
        cheatFailPanel.setVisible(false);
        selectResourceGivePanel.setVisible(false);
        selectResourceGetPanel.setVisible(false);
        saveCustomMapPanel.setVisible(false);
        customMapChitPanel.setVisible(false);
        nativeSupportPanel.setVisible(false);
        scoutCardChoicePanel.setVisible(false);
        alreadyPlayedDPPanel.setVisible(false);
        customMapCannotChit.setVisible(false);
        notEnoughResourcesPanel.setVisible(false);
        alreadyHaveSettlersPanel.setVisible(false);
        alreadyHaveRailsPanel.setVisible(false);
        tooManyCitiesPanel.setVisible(false);
        noSettlerPanel.setVisible(false);
        alreadyHaveTrainsPanel.setVisible(false);
        winnerPanel.setVisible(false);
        notEnoughGoldPanel.setVisible(false);
        dpCardPanel.setVisible(false);
        noCardsPanel.setVisible(false);
        yesButtonTrain = new AvaJButton(yesButtonTrain1);
        noButtonTrain = new AvaJButton(noButtonTrain1);

        //choose a case to display
        switch (i) {
            //exit dialog case
            case 1:
                exitDialogPanel.setVisible(true);
                break;
            case 2:
                nameChangePanel.setVisible(true);
                break;
            case 3:
                cantBuildSettlementPanel.setVisible(true);
                break;
            case 4:
                cheatSuccessPanel.setVisible(true);
                break;
            case 5:
                cheatFailPanel.setVisible(true);
                break;
            case 6:
                saveCustomMapPanel.setVisible(true);
                break;
            case 7:
                customMapChitPanel.setVisible(true);
                break;
            case 8:
                customMapCannotChit.setVisible(true);
                break;
            case 9:
                notEnoughResourcesPanel.setVisible(true);
                break;
            case 10:
                alreadyHaveSettlersPanel.setVisible(true);
                break;
            case 11:
                tooManyCitiesPanel.setVisible(true);
                break;
            case 12:
                alreadyHaveRailsPanel.setVisible(true);
                break;
            case 14:
                alreadyHaveTrainsPanel.setVisible(true);
                break;
            default:
                break;
        }
    }
    SettlersConfirmDialog(Player winnerPlayer, gameFrame g) {
        this.winnerPlayer = winnerPlayer;
        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        try{
            setIconImage(ImageIO.read(getClass().getResource("/settlerstrailstorails/resources/icon.png")));
        }catch(Exception e){
            
        }
        game_Frame=g;
        exitDialogPanel.setVisible(false);
        nameChangePanel.setVisible(false);
        selectResourceGivePanel.setVisible(false);
        selectResourceGetPanel.setVisible(false);
        cannotClosePanel.setVisible(false);
        cantBuildSettlementPanel.setVisible(false);
        cheatSuccessPanel.setVisible(false);
        cheatFailPanel.setVisible(false);
        mustRollPanel.setVisible(false);
        noAcceptTradePanel.setVisible(false);
        playerAcceptedTradePanel.setVisible(false);
        saveCustomMapPanel.setVisible(false);
        customMapChitPanel.setVisible(false);
        nativeSupportPanel.setVisible(false);
        customMapCannotChit.setVisible(false);
        alreadyPlayedDPPanel.setVisible(false);
        notEnoughResourcesPanel.setVisible(false);
        alreadyHaveSettlersPanel.setVisible(false);
        alreadyHaveRailsPanel.setVisible(false);
        tooManyCitiesPanel.setVisible(false);
        noSettlerPanel.setVisible(false);
        alreadyHaveTrainsPanel.setVisible(false);
        scoutCardChoicePanel.setVisible(false);
        winnerPanel.setVisible(true);
        notEnoughGoldPanel.setVisible(false);
        dpCardPanel.setVisible(false);
        noCardsPanel.setVisible(false);
        okayButtonWinner = new AvaJButton(okayButtonWinner1);
    }
    SettlersConfirmDialog(developmentCardWindow dcw, buildWindow bw, DevelopmentCard d, gameFrame g) {
        this.bw=bw;
        this.dcw=dcw;
        dCard=d;
        game_Frame=g;
        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        try{
            setIconImage(ImageIO.read(getClass().getResource("/settlerstrailstorails/resources/icon.png")));
        }catch(Exception e){
            
        }
        exitDialogPanel.setVisible(false);
        nameChangePanel.setVisible(false);
        selectResourceGivePanel.setVisible(false);
        selectResourceGetPanel.setVisible(false);
        cantBuildSettlementPanel.setVisible(false);
        nativeSupportPanel.setVisible(false);
        cannotClosePanel.setVisible(false);
        mustRollPanel.setVisible(false);
        cheatSuccessPanel.setVisible(false);
        cheatFailPanel.setVisible(false);
        noAcceptTradePanel.setVisible(false);
        playerAcceptedTradePanel.setVisible(false);
        saveCustomMapPanel.setVisible(false);
        customMapChitPanel.setVisible(false);
        customMapCannotChit.setVisible(false);
        alreadyPlayedDPPanel.setVisible(false);
        notEnoughResourcesPanel.setVisible(false);
        alreadyHaveSettlersPanel.setVisible(false);
        alreadyHaveRailsPanel.setVisible(false);
        tooManyCitiesPanel.setVisible(false);
        noSettlerPanel.setVisible(false);
        alreadyHaveTrainsPanel.setVisible(false);
        winnerPanel.setVisible(false);
        notEnoughGoldPanel.setVisible(false);
        dpCardPanel.setVisible(false);
        noCardsPanel.setVisible(false);
        scoutCardChoicePanel.setVisible(true);
        scoutChoiceSettlerButton = new AvaJButton(scoutChoiceSettlerButton1);
        scoutChoiceGoldButton = new AvaJButton(scoutChoiceGoldButton1);
        okayButtonWinner = new AvaJButton(okayButtonWinner1);
    }
    SettlersConfirmDialog(Player p){       
        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        try{
            setIconImage(ImageIO.read(getClass().getResource("/settlerstrailstorails/resources/icon.png")));
        }catch(Exception e){
            
        }
        exitDialogPanel.setVisible(false);
        nameChangePanel.setVisible(false);
        cantBuildSettlementPanel.setVisible(false);
        nativeSupportPanel.setVisible(false);
        cannotClosePanel.setVisible(false);
        mustRollPanel.setVisible(false);
        cheatSuccessPanel.setVisible(false);
        cheatFailPanel.setVisible(false);
        noAcceptTradePanel.setVisible(false);
        playerAcceptedTradePanel.setVisible(true);
        saveCustomMapPanel.setVisible(false);
        customMapChitPanel.setVisible(false);
        customMapCannotChit.setVisible(false);
        alreadyPlayedDPPanel.setVisible(false);
        notEnoughResourcesPanel.setVisible(false);
        alreadyHaveSettlersPanel.setVisible(false);
        alreadyHaveRailsPanel.setVisible(false);
        tooManyCitiesPanel.setVisible(false);
        noSettlerPanel.setVisible(false);
        alreadyHaveTrainsPanel.setVisible(false);
        winnerPanel.setVisible(false);
        notEnoughGoldPanel.setVisible(false);
        dpCardPanel.setVisible(false);
        noCardsPanel.setVisible(false);
        scoutCardChoicePanel.setVisible(false);
        selectResourceGivePanel.setVisible(false);
        selectResourceGetPanel.setVisible(false);
        playerAcceptedTradeButton = new AvaJButton(playerAcceptedTradeButton1);
        playerAcceptedTradeLabel.setText(p.getName()+" has accepted your trade");
    }
    public SettlersConfirmDialog() {
        initComponents();
    }

    public void changeNameOfPlayer(int playerNum, String s, int index){
        playerNumberLabel.setText(String.valueOf(playerNum));
        currName=s;
        currNameIndex=index;
        nameTextField.setText(s);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        selectResourceGetPanel = new javax.swing.JPanel();
        selectResourceGetLabel = new javax.swing.JLabel();
        selectResourceGetButton1 = new javax.swing.JButton();
        selectResourceGivePanel = new javax.swing.JPanel();
        selectResourceGiveLabel = new javax.swing.JLabel();
        selectResourceGiveButton1 = new javax.swing.JButton();
        noAcceptTradePanel = new javax.swing.JPanel();
        noAcceptedTradeLabel = new javax.swing.JLabel();
        noAcceptedTradeButton1 = new javax.swing.JButton();
        playerAcceptedTradePanel = new javax.swing.JPanel();
        playerAcceptedTradeLabel = new javax.swing.JLabel();
        playerAcceptedTradeButton1 = new javax.swing.JButton();
        cannotClosePanel = new javax.swing.JPanel();
        cannotCloseLabel = new javax.swing.JLabel();
        cannotCloseButton1 = new javax.swing.JButton();
        mustRollPanel = new javax.swing.JPanel();
        mustRollLabel = new javax.swing.JLabel();
        mustRollLabel1 = new javax.swing.JLabel();
        mustRollButton1 = new javax.swing.JButton();
        nativeSupportPanel = new javax.swing.JPanel();
        nativeSupportLabel = new javax.swing.JLabel();
        nativeSupportLabel1 = new javax.swing.JLabel();
        nativeSupportButton1 = new javax.swing.JButton();
        scoutCardChoicePanel = new javax.swing.JPanel();
        scoutCardChoiceLabel = new javax.swing.JLabel();
        scoutCardChoiceLabel2 = new javax.swing.JLabel();
        scoutChoiceGoldButton1 = new javax.swing.JButton();
        scoutChoiceSettlerButton1 = new javax.swing.JButton();
        alreadyPlayedDPPanel = new javax.swing.JPanel();
        alreadyPlayedDPButton1 = new javax.swing.JButton();
        alreadyPlayedDPPanelLabel = new javax.swing.JLabel();
        alreadyPlayedDPPanelLabel1 = new javax.swing.JLabel();
        noCardsPanel = new javax.swing.JPanel();
        noCardsButton1 = new javax.swing.JButton();
        noCardsLabel = new javax.swing.JLabel();
        noCardsLabel1 = new javax.swing.JLabel();
        dpCardPanel = new javax.swing.JPanel();
        okayButtonDPCard1 = new javax.swing.JButton();
        DPCardLabel = new javax.swing.JLabel();
        DPCardLabel1 = new javax.swing.JLabel();
        winnerPanel = new javax.swing.JPanel();
        okayButtonWinner1 = new javax.swing.JButton();
        winnerPanelLabel = new javax.swing.JLabel();
        notEnoughGoldPanel = new javax.swing.JPanel();
        notEnoughGoldButton1 = new javax.swing.JButton();
        notEnoughGoldLabel = new javax.swing.JLabel();
        notEnoughGoldLabel1 = new javax.swing.JLabel();
        alreadyHaveTrainsPanel = new javax.swing.JPanel();
        noButtonTrain1 = new javax.swing.JButton();
        yesButtonTrain1 = new javax.swing.JButton();
        alreadyHaveTrainLabel = new javax.swing.JLabel();
        alreadyHaveTrainLabel1 = new javax.swing.JLabel();
        noSettlerPanel = new javax.swing.JPanel();
        noSettlerLabel = new javax.swing.JLabel();
        noSettlerButton = new javax.swing.JButton();
        alreadyHaveRailsPanel = new javax.swing.JPanel();
        alreadyHaveRailsLabel = new javax.swing.JLabel();
        alreadyHaveRailsLabel1 = new javax.swing.JLabel();
        alreadyHaveRailsButton = new javax.swing.JButton();
        alreadyHaveSettlersPanel = new javax.swing.JPanel();
        noButtonSettlers = new javax.swing.JButton();
        yesButtonSettlers = new javax.swing.JButton();
        alreadyHaveSettlersLabel = new javax.swing.JLabel();
        alreadyHaveSettlersLabel1 = new javax.swing.JLabel();
        tooManyCitiesPanel = new javax.swing.JPanel();
        tooManyCitiesLabel = new javax.swing.JLabel();
        tooManyCitiesLabel1 = new javax.swing.JLabel();
        tooManyCitiesButton = new javax.swing.JButton();
        notEnoughResourcesPanel = new javax.swing.JPanel();
        notEnoughResourcesLabel = new javax.swing.JLabel();
        notEnoughResourcesButton = new javax.swing.JButton();
        cheatSuccessPanel = new javax.swing.JPanel();
        cheatSuccessLabel = new javax.swing.JLabel();
        okayCheatSuccessButton = new javax.swing.JButton();
        customMapCannotChit = new javax.swing.JPanel();
        chitLabel2 = new javax.swing.JLabel();
        chitLabel3 = new javax.swing.JLabel();
        okayButtonCannotChit = new javax.swing.JButton();
        customMapChitPanel = new javax.swing.JPanel();
        chitLabel = new javax.swing.JLabel();
        chitLabel1 = new javax.swing.JLabel();
        nineChitButton = new javax.swing.JButton();
        tenChitButton = new javax.swing.JButton();
        elevenChitButton = new javax.swing.JButton();
        saveCustomMapPanel = new javax.swing.JPanel();
        saveMapLabel = new javax.swing.JLabel();
        noButtonCustomMap = new javax.swing.JButton();
        yesButtonCustomMap = new javax.swing.JButton();
        cheatFailPanel = new javax.swing.JPanel();
        cheatFailLabel = new javax.swing.JLabel();
        okayCheatFailButton = new javax.swing.JButton();
        cantBuildSettlementPanel = new javax.swing.JPanel();
        cantBuildSettlementLabel = new javax.swing.JLabel();
        cantBuildSettlementLabel2 = new javax.swing.JLabel();
        okayButtonCantBuildSettlement = new javax.swing.JButton();
        nameChangePanel = new javax.swing.JPanel();
        playerNumberLabel = new javax.swing.JLabel();
        changeNameLabel = new javax.swing.JLabel();
        cancelButton = new javax.swing.JButton();
        applyButton = new javax.swing.JButton();
        nameTextField = new javax.swing.JTextField();
        exitDialogPanel = new javax.swing.JPanel();
        exitDialogQuestion = new javax.swing.JLabel();
        exitDialogQuestion2 = new javax.swing.JLabel();
        noButton = new javax.swing.JButton();
        yesButton = new javax.swing.JButton();
        defaultPanel = new javax.swing.JPanel();
        backgroundLabel = new javax.swing.JLabel();

        setAlwaysOnTop(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        selectResourceGetPanel.setOpaque(false);
        selectResourceGetPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        selectResourceGetLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        selectResourceGetLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        selectResourceGetLabel.setText("Please select a resource to receive.");
        selectResourceGetPanel.add(selectResourceGetLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 15, 380, -1));

        selectResourceGetButton1.setBackground(new java.awt.Color(239, 228, 176));
        selectResourceGetButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        selectResourceGetButton1.setText("OK");
        selectResourceGetButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        selectResourceGetButton1.setContentAreaFilled(false);
        selectResourceGetButton1.setFocusable(false);
        selectResourceGetButton1.setOpaque(true);
        selectResourceGetButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                selectResourceGetButton1MouseReleased(evt);
            }
        });
        selectResourceGetButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectResourceGetButton1ActionPerformed(evt);
            }
        });
        selectResourceGetPanel.add(selectResourceGetButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 55, 110, 30));

        getContentPane().add(selectResourceGetPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, 100));

        selectResourceGivePanel.setOpaque(false);
        selectResourceGivePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        selectResourceGiveLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        selectResourceGiveLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        selectResourceGiveLabel.setText("You must select a resource to give.");
        selectResourceGivePanel.add(selectResourceGiveLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 15, 380, -1));

        selectResourceGiveButton1.setBackground(new java.awt.Color(239, 228, 176));
        selectResourceGiveButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        selectResourceGiveButton1.setText("OK");
        selectResourceGiveButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        selectResourceGiveButton1.setContentAreaFilled(false);
        selectResourceGiveButton1.setFocusable(false);
        selectResourceGiveButton1.setOpaque(true);
        selectResourceGiveButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                selectResourceGiveButton1MouseReleased(evt);
            }
        });
        selectResourceGiveButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectResourceGiveButton1ActionPerformed(evt);
            }
        });
        selectResourceGivePanel.add(selectResourceGiveButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 55, 110, 30));

        getContentPane().add(selectResourceGivePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, 100));

        noAcceptTradePanel.setOpaque(false);
        noAcceptTradePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        noAcceptedTradeLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        noAcceptedTradeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        noAcceptedTradeLabel.setText("No one accepted your trade.");
        noAcceptTradePanel.add(noAcceptedTradeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 15, 380, -1));

        noAcceptedTradeButton1.setBackground(new java.awt.Color(239, 228, 176));
        noAcceptedTradeButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        noAcceptedTradeButton1.setText("OK");
        noAcceptedTradeButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        noAcceptedTradeButton1.setContentAreaFilled(false);
        noAcceptedTradeButton1.setFocusable(false);
        noAcceptedTradeButton1.setOpaque(true);
        noAcceptedTradeButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                noAcceptedTradeButton1MouseReleased(evt);
            }
        });
        noAcceptedTradeButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noAcceptedTradeButton1ActionPerformed(evt);
            }
        });
        noAcceptTradePanel.add(noAcceptedTradeButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 55, 110, 30));

        getContentPane().add(noAcceptTradePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, 100));

        playerAcceptedTradePanel.setOpaque(false);
        playerAcceptedTradePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        playerAcceptedTradeLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        playerAcceptedTradeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        playerAcceptedTradeLabel.setText("Orange Player accepted your trade.");
        playerAcceptedTradePanel.add(playerAcceptedTradeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 15, 380, -1));

        playerAcceptedTradeButton1.setBackground(new java.awt.Color(239, 228, 176));
        playerAcceptedTradeButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        playerAcceptedTradeButton1.setText("OK");
        playerAcceptedTradeButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        playerAcceptedTradeButton1.setContentAreaFilled(false);
        playerAcceptedTradeButton1.setFocusable(false);
        playerAcceptedTradeButton1.setOpaque(true);
        playerAcceptedTradeButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                playerAcceptedTradeButton1MouseReleased(evt);
            }
        });
        playerAcceptedTradeButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playerAcceptedTradeButton1ActionPerformed(evt);
            }
        });
        playerAcceptedTradePanel.add(playerAcceptedTradeButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 55, 110, 30));

        getContentPane().add(playerAcceptedTradePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, 100));

        cannotClosePanel.setOpaque(false);
        cannotClosePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cannotCloseLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        cannotCloseLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cannotCloseLabel.setText("You cannot close this window.");
        cannotClosePanel.add(cannotCloseLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 15, 380, -1));

        cannotCloseButton1.setBackground(new java.awt.Color(239, 228, 176));
        cannotCloseButton1.setFont(new java.awt.Font("Tahoma", 1, 11));
        cannotCloseButton1.setText("OK");
        cannotCloseButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cannotCloseButton1.setContentAreaFilled(false);
        cannotCloseButton1.setFocusable(false);
        cannotCloseButton1.setOpaque(true);
        cannotCloseButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cannotCloseButton1MouseReleased(evt);
            }
        });
        cannotCloseButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cannotCloseButton1ActionPerformed(evt);
            }
        });
        cannotClosePanel.add(cannotCloseButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 55, 110, 30));

        getContentPane().add(cannotClosePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, 100));

        mustRollPanel.setOpaque(false);
        mustRollPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        mustRollLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        mustRollLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mustRollLabel.setText("You must roll before you can");
        mustRollPanel.add(mustRollLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 5, 380, -1));

        mustRollLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        mustRollLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mustRollLabel1.setText("play this development card.");
        mustRollPanel.add(mustRollLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 25, 380, -1));

        mustRollButton1.setBackground(new java.awt.Color(239, 228, 176));
        mustRollButton1.setFont(new java.awt.Font("Tahoma", 1, 11));
        mustRollButton1.setText("OK");
        mustRollButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        mustRollButton1.setContentAreaFilled(false);
        mustRollButton1.setFocusable(false);
        mustRollButton1.setOpaque(true);
        mustRollButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                mustRollButton1MouseReleased(evt);
            }
        });
        mustRollButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mustRollButton1ActionPerformed(evt);
            }
        });
        mustRollPanel.add(mustRollButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 55, 110, 30));

        getContentPane().add(mustRollPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, 100));

        nativeSupportPanel.setOpaque(false);
        nativeSupportPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        nativeSupportLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        nativeSupportLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nativeSupportLabel.setText("You can only play Native Support");
        nativeSupportPanel.add(nativeSupportLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 5, 380, -1));

        nativeSupportLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        nativeSupportLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nativeSupportLabel1.setText("before you roll the dice for your turn.");
        nativeSupportPanel.add(nativeSupportLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 25, 380, -1));

        nativeSupportButton1.setBackground(new java.awt.Color(239, 228, 176));
        nativeSupportButton1.setFont(new java.awt.Font("Tahoma", 1, 11));
        nativeSupportButton1.setText("OK");
        nativeSupportButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        nativeSupportButton1.setContentAreaFilled(false);
        nativeSupportButton1.setFocusable(false);
        nativeSupportButton1.setOpaque(true);
        nativeSupportButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                nativeSupportButton1MouseReleased(evt);
            }
        });
        nativeSupportButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nativeSupportButton1ActionPerformed(evt);
            }
        });
        nativeSupportPanel.add(nativeSupportButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 55, 110, 30));

        getContentPane().add(nativeSupportPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, 100));

        scoutCardChoicePanel.setOpaque(false);
        scoutCardChoicePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        scoutCardChoiceLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        scoutCardChoiceLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        scoutCardChoiceLabel.setText("Would you like to move your settler");
        scoutCardChoicePanel.add(scoutCardChoiceLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 5, 380, -1));

        scoutCardChoiceLabel2.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        scoutCardChoiceLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        scoutCardChoiceLabel2.setText("5 intersections? or take 3 gold?");
        scoutCardChoicePanel.add(scoutCardChoiceLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 25, 380, -1));

        scoutChoiceGoldButton1.setBackground(new java.awt.Color(239, 228, 176));
        scoutChoiceGoldButton1.setFont(new java.awt.Font("Tahoma", 1, 11));
        scoutChoiceGoldButton1.setText("Take 3 Gold");
        scoutChoiceGoldButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        scoutChoiceGoldButton1.setContentAreaFilled(false);
        scoutChoiceGoldButton1.setFocusable(false);
        scoutChoiceGoldButton1.setOpaque(true);
        scoutChoiceGoldButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                scoutChoiceGoldButton1MouseReleased(evt);
            }
        });
        scoutCardChoicePanel.add(scoutChoiceGoldButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 55, 110, 30));

        scoutChoiceSettlerButton1.setBackground(new java.awt.Color(239, 228, 176));
        scoutChoiceSettlerButton1.setFont(new java.awt.Font("Tahoma", 1, 11));
        scoutChoiceSettlerButton1.setText("Move Settler");
        scoutChoiceSettlerButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        scoutChoiceSettlerButton1.setContentAreaFilled(false);
        scoutChoiceSettlerButton1.setFocusable(false);
        scoutChoiceSettlerButton1.setOpaque(true);
        scoutChoiceSettlerButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                scoutChoiceSettlerButton1MouseReleased(evt);
            }
        });
        scoutChoiceSettlerButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scoutChoiceSettlerButton1ActionPerformed(evt);
            }
        });
        scoutCardChoicePanel.add(scoutChoiceSettlerButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 55, 110, 30));

        getContentPane().add(scoutCardChoicePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, 100));

        alreadyPlayedDPPanel.setOpaque(false);
        alreadyPlayedDPPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        alreadyPlayedDPButton1.setBackground(new java.awt.Color(239, 228, 176));
        alreadyPlayedDPButton1.setFont(new java.awt.Font("Tahoma", 1, 11));
        alreadyPlayedDPButton1.setText("OK");
        alreadyPlayedDPButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        alreadyPlayedDPButton1.setContentAreaFilled(false);
        alreadyPlayedDPButton1.setFocusable(false);
        alreadyPlayedDPButton1.setOpaque(true);
        alreadyPlayedDPButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                alreadyPlayedDPButton1MouseReleased(evt);
            }
        });
        alreadyPlayedDPPanel.add(alreadyPlayedDPButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 55, 110, 30));

        alreadyPlayedDPPanelLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        alreadyPlayedDPPanelLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        alreadyPlayedDPPanelLabel.setText("You can only play one development");
        alreadyPlayedDPPanel.add(alreadyPlayedDPPanelLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 5, 380, -1));

        alreadyPlayedDPPanelLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        alreadyPlayedDPPanelLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        alreadyPlayedDPPanelLabel1.setText("card per turn.");
        alreadyPlayedDPPanel.add(alreadyPlayedDPPanelLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 25, 380, -1));

        getContentPane().add(alreadyPlayedDPPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, 100));

        noCardsPanel.setOpaque(false);
        noCardsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        noCardsButton1.setBackground(new java.awt.Color(239, 228, 176));
        noCardsButton1.setFont(new java.awt.Font("Tahoma", 1, 11));
        noCardsButton1.setText("OK");
        noCardsButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        noCardsButton1.setContentAreaFilled(false);
        noCardsButton1.setFocusable(false);
        noCardsButton1.setOpaque(true);
        noCardsButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                noCardsButton1MouseReleased(evt);
            }
        });
        noCardsPanel.add(noCardsButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 55, 110, 30));

        noCardsLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        noCardsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        noCardsLabel.setText("You can't play a development card");
        noCardsPanel.add(noCardsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 5, 380, -1));

        noCardsLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        noCardsLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        noCardsLabel1.setText("on the same turn you bought it.");
        noCardsPanel.add(noCardsLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 25, 380, -1));

        getContentPane().add(noCardsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, 100));

        dpCardPanel.setOpaque(false);
        dpCardPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        okayButtonDPCard1.setBackground(new java.awt.Color(239, 228, 176));
        okayButtonDPCard1.setFont(new java.awt.Font("Tahoma", 1, 11));
        okayButtonDPCard1.setText("OK");
        okayButtonDPCard1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        okayButtonDPCard1.setContentAreaFilled(false);
        okayButtonDPCard1.setFocusable(false);
        okayButtonDPCard1.setOpaque(true);
        okayButtonDPCard1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                okayButtonDPCard1MouseReleased(evt);
            }
        });
        dpCardPanel.add(okayButtonDPCard1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 55, 110, 30));

        DPCardLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        DPCardLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DPCardLabel.setText("There are no development cards");
        dpCardPanel.add(DPCardLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 5, 380, -1));

        DPCardLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        DPCardLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DPCardLabel1.setText("remaining.");
        dpCardPanel.add(DPCardLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 25, 380, -1));

        getContentPane().add(dpCardPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, 100));

        winnerPanel.setOpaque(false);
        winnerPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        okayButtonWinner1.setBackground(new java.awt.Color(239, 228, 176));
        okayButtonWinner1.setFont(new java.awt.Font("Tahoma", 1, 11));
        okayButtonWinner1.setText("OK");
        okayButtonWinner1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        okayButtonWinner1.setContentAreaFilled(false);
        okayButtonWinner1.setFocusable(false);
        okayButtonWinner1.setOpaque(true);
        okayButtonWinner1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                okayButtonWinner1MouseReleased(evt);
            }
        });
        okayButtonWinner1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okayButtonWinner1ActionPerformed(evt);
            }
        });
        winnerPanel.add(okayButtonWinner1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 55, 110, 30));

        winnerPanelLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        winnerPanelLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        winnerPanelLabel.setText(winnerPlayer + " has won!");
        winnerPanel.add(winnerPanelLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 15, 380, -1));

        getContentPane().add(winnerPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, 100));

        notEnoughGoldPanel.setOpaque(false);
        notEnoughGoldPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        notEnoughGoldButton1.setBackground(new java.awt.Color(239, 228, 176));
        notEnoughGoldButton1.setFont(new java.awt.Font("Tahoma", 1, 11));
        notEnoughGoldButton1.setText("OK");
        notEnoughGoldButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        notEnoughGoldButton1.setContentAreaFilled(false);
        notEnoughGoldButton1.setFocusable(false);
        notEnoughGoldButton1.setOpaque(true);
        notEnoughGoldButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                notEnoughGoldButton1MouseReleased(evt);
            }
        });
        notEnoughGoldButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                notEnoughGoldButton1ActionPerformed(evt);
            }
        });
        notEnoughGoldPanel.add(notEnoughGoldButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 55, 110, 30));

        notEnoughGoldLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        notEnoughGoldLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        notEnoughGoldLabel.setText("You don't have enough gold to pay");
        notEnoughGoldPanel.add(notEnoughGoldLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 5, 380, -1));

        notEnoughGoldLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        notEnoughGoldLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        notEnoughGoldLabel1.setText("the right of way.");
        notEnoughGoldPanel.add(notEnoughGoldLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 25, 380, -1));

        getContentPane().add(notEnoughGoldPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, 100));

        alreadyHaveTrainsPanel.setOpaque(false);
        alreadyHaveTrainsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        noButtonTrain1.setBackground(new java.awt.Color(239, 228, 176));
        noButtonTrain1.setFont(new java.awt.Font("Tahoma", 1, 11));
        noButtonTrain1.setText("No");
        noButtonTrain1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        noButtonTrain1.setContentAreaFilled(false);
        noButtonTrain1.setFocusable(false);
        noButtonTrain1.setOpaque(true);
        noButtonTrain1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                noButtonTrain1MouseReleased(evt);
            }
        });
        noButtonTrain1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noButtonTrain1ActionPerformed(evt);
            }
        });
        alreadyHaveTrainsPanel.add(noButtonTrain1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 55, 110, 30));

        yesButtonTrain1.setBackground(new java.awt.Color(239, 228, 176));
        yesButtonTrain1.setFont(new java.awt.Font("Tahoma", 1, 11));
        yesButtonTrain1.setText("Yes");
        yesButtonTrain1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        yesButtonTrain1.setContentAreaFilled(false);
        yesButtonTrain1.setFocusable(false);
        yesButtonTrain1.setOpaque(true);
        yesButtonTrain1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                yesButtonTrain1MouseReleased(evt);
            }
        });
        alreadyHaveTrainsPanel.add(yesButtonTrain1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 55, 110, 30));

        alreadyHaveTrainLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        alreadyHaveTrainLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        alreadyHaveTrainLabel.setText("You already have 2 trains.");
        alreadyHaveTrainsPanel.add(alreadyHaveTrainLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 5, 380, -1));

        alreadyHaveTrainLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        alreadyHaveTrainLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        alreadyHaveTrainLabel1.setText("Would you like to pay and move one?");
        alreadyHaveTrainsPanel.add(alreadyHaveTrainLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 25, 380, -1));

        getContentPane().add(alreadyHaveTrainsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, 100));

        noSettlerPanel.setOpaque(false);
        noSettlerPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        noSettlerLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        noSettlerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        noSettlerLabel.setText("You don't have a settler.");
        noSettlerPanel.add(noSettlerLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 15, 380, -1));

        noSettlerButton.setBackground(new java.awt.Color(239, 228, 176));
        noSettlerButton.setFont(new java.awt.Font("Tahoma", 1, 11));
        noSettlerButton.setText("OK");
        noSettlerButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        noSettlerButton.setContentAreaFilled(false);
        noSettlerButton.setFocusable(false);
        noSettlerButton.setOpaque(true);
        noSettlerButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                noSettlerButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                noSettlerButtonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                noSettlerButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                noSettlerButtonMouseReleased(evt);
            }
        });
        noSettlerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noSettlerButtonActionPerformed(evt);
            }
        });
        noSettlerPanel.add(noSettlerButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 55, 110, 30));

        getContentPane().add(noSettlerPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, 100));

        alreadyHaveRailsPanel.setOpaque(false);
        alreadyHaveRailsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        alreadyHaveRailsLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        alreadyHaveRailsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        alreadyHaveRailsLabel.setText("You have built the maximum number");
        alreadyHaveRailsPanel.add(alreadyHaveRailsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 5, 380, -1));

        alreadyHaveRailsLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        alreadyHaveRailsLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        alreadyHaveRailsLabel1.setText("of rails that is allowed.");
        alreadyHaveRailsPanel.add(alreadyHaveRailsLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 25, 380, -1));

        alreadyHaveRailsButton.setBackground(new java.awt.Color(239, 228, 176));
        alreadyHaveRailsButton.setFont(new java.awt.Font("Tahoma", 1, 11));
        alreadyHaveRailsButton.setText("OK");
        alreadyHaveRailsButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        alreadyHaveRailsButton.setContentAreaFilled(false);
        alreadyHaveRailsButton.setFocusable(false);
        alreadyHaveRailsButton.setOpaque(true);
        alreadyHaveRailsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                alreadyHaveRailsButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                alreadyHaveRailsButtonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                alreadyHaveRailsButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                alreadyHaveRailsButtonMouseReleased(evt);
            }
        });
        alreadyHaveRailsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alreadyHaveRailsButtonActionPerformed(evt);
            }
        });
        alreadyHaveRailsPanel.add(alreadyHaveRailsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 55, 110, 30));

        getContentPane().add(alreadyHaveRailsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, 100));

        alreadyHaveSettlersPanel.setOpaque(false);
        alreadyHaveSettlersPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        noButtonSettlers.setBackground(new java.awt.Color(239, 228, 176));
        noButtonSettlers.setFont(new java.awt.Font("Tahoma", 1, 11));
        noButtonSettlers.setText("No");
        noButtonSettlers.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        noButtonSettlers.setContentAreaFilled(false);
        noButtonSettlers.setFocusable(false);
        noButtonSettlers.setOpaque(true);
        noButtonSettlers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                noButtonSettlersMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                noButtonSettlersMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                noButtonSettlersMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                noButtonSettlersMouseReleased(evt);
            }
        });
        noButtonSettlers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noButtonSettlersActionPerformed(evt);
            }
        });
        alreadyHaveSettlersPanel.add(noButtonSettlers, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 55, 110, 30));

        yesButtonSettlers.setBackground(new java.awt.Color(239, 228, 176));
        yesButtonSettlers.setFont(new java.awt.Font("Tahoma", 1, 11));
        yesButtonSettlers.setText("Yes");
        yesButtonSettlers.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        yesButtonSettlers.setContentAreaFilled(false);
        yesButtonSettlers.setFocusable(false);
        yesButtonSettlers.setOpaque(true);
        yesButtonSettlers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                yesButtonSettlersMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                yesButtonSettlersMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                yesButtonSettlersMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                yesButtonSettlersMouseReleased(evt);
            }
        });
        yesButtonSettlers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yesButtonSettlersActionPerformed(evt);
            }
        });
        alreadyHaveSettlersPanel.add(yesButtonSettlers, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 55, 110, 30));

        alreadyHaveSettlersLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        alreadyHaveSettlersLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        alreadyHaveSettlersLabel.setText("You already have 2 settlers.");
        alreadyHaveSettlersPanel.add(alreadyHaveSettlersLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 5, 380, -1));

        alreadyHaveSettlersLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        alreadyHaveSettlersLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        alreadyHaveSettlersLabel1.setText("Would you like to pay and move one?");
        alreadyHaveSettlersPanel.add(alreadyHaveSettlersLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 25, 380, -1));

        getContentPane().add(alreadyHaveSettlersPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, 100));

        tooManyCitiesPanel.setOpaque(false);
        tooManyCitiesPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tooManyCitiesLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        tooManyCitiesLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tooManyCitiesLabel.setText("You have built the maximum number");
        tooManyCitiesPanel.add(tooManyCitiesLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 5, 380, -1));

        tooManyCitiesLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        tooManyCitiesLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tooManyCitiesLabel1.setText("of cities that is allowed");
        tooManyCitiesPanel.add(tooManyCitiesLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 25, 380, -1));

        tooManyCitiesButton.setBackground(new java.awt.Color(239, 228, 176));
        tooManyCitiesButton.setFont(new java.awt.Font("Tahoma", 1, 11));
        tooManyCitiesButton.setText("OK");
        tooManyCitiesButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tooManyCitiesButton.setContentAreaFilled(false);
        tooManyCitiesButton.setFocusable(false);
        tooManyCitiesButton.setOpaque(true);
        tooManyCitiesButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tooManyCitiesButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tooManyCitiesButtonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tooManyCitiesButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tooManyCitiesButtonMouseReleased(evt);
            }
        });
        tooManyCitiesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tooManyCitiesButtonActionPerformed(evt);
            }
        });
        tooManyCitiesPanel.add(tooManyCitiesButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 55, 110, 30));

        getContentPane().add(tooManyCitiesPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, 100));

        notEnoughResourcesPanel.setOpaque(false);
        notEnoughResourcesPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        notEnoughResourcesLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        notEnoughResourcesLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        notEnoughResourcesLabel.setText("You don't have enough resources.");
        notEnoughResourcesPanel.add(notEnoughResourcesLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 15, 380, -1));

        notEnoughResourcesButton.setBackground(new java.awt.Color(239, 228, 176));
        notEnoughResourcesButton.setFont(new java.awt.Font("Tahoma", 1, 11));
        notEnoughResourcesButton.setText("OK");
        notEnoughResourcesButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        notEnoughResourcesButton.setContentAreaFilled(false);
        notEnoughResourcesButton.setFocusable(false);
        notEnoughResourcesButton.setOpaque(true);
        notEnoughResourcesButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                notEnoughResourcesButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                notEnoughResourcesButtonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                notEnoughResourcesButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                notEnoughResourcesButtonMouseReleased(evt);
            }
        });
        notEnoughResourcesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                notEnoughResourcesButtonActionPerformed(evt);
            }
        });
        notEnoughResourcesPanel.add(notEnoughResourcesButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 55, 110, 30));

        getContentPane().add(notEnoughResourcesPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, 100));

        cheatSuccessPanel.setOpaque(false);
        cheatSuccessPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cheatSuccessLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        cheatSuccessLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cheatSuccessLabel.setText("Cheat Successful");
        cheatSuccessPanel.add(cheatSuccessLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 15, 380, -1));

        okayCheatSuccessButton.setBackground(new java.awt.Color(239, 228, 176));
        okayCheatSuccessButton.setFont(new java.awt.Font("Tahoma", 1, 11));
        okayCheatSuccessButton.setText("OK");
        okayCheatSuccessButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        okayCheatSuccessButton.setContentAreaFilled(false);
        okayCheatSuccessButton.setFocusable(false);
        okayCheatSuccessButton.setOpaque(true);
        okayCheatSuccessButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                okayCheatSuccessButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                okayCheatSuccessButtonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                okayCheatSuccessButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                okayCheatSuccessButtonMouseReleased(evt);
            }
        });
        okayCheatSuccessButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okayCheatSuccessButtonActionPerformed(evt);
            }
        });
        cheatSuccessPanel.add(okayCheatSuccessButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 55, 110, 30));

        getContentPane().add(cheatSuccessPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, 100));

        customMapCannotChit.setOpaque(false);
        customMapCannotChit.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        chitLabel2.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        chitLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chitLabel2.setText("You cannot have more than 4");
        customMapCannotChit.add(chitLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 5, 380, -1));

        chitLabel3.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        chitLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chitLabel3.setText("of the same chit");
        customMapCannotChit.add(chitLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 27, 380, -1));

        okayButtonCannotChit.setBackground(new java.awt.Color(239, 228, 176));
        okayButtonCannotChit.setFont(new java.awt.Font("Tahoma", 1, 11));
        okayButtonCannotChit.setText("OK");
        okayButtonCannotChit.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        okayButtonCannotChit.setContentAreaFilled(false);
        okayButtonCannotChit.setFocusable(false);
        okayButtonCannotChit.setOpaque(true);
        okayButtonCannotChit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                okayButtonCannotChitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                okayButtonCannotChitMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                okayButtonCannotChitMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                okayButtonCannotChitMouseReleased(evt);
            }
        });
        okayButtonCannotChit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okayButtonCannotChitActionPerformed(evt);
            }
        });
        customMapCannotChit.add(okayButtonCannotChit, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 60, 90, 30));

        getContentPane().add(customMapCannotChit, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, 100));

        customMapChitPanel.setOpaque(false);
        customMapChitPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        chitLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        chitLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chitLabel.setText("What number would you like to set");
        customMapChitPanel.add(chitLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 5, 380, -1));

        chitLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        chitLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chitLabel1.setText("to this hex?");
        customMapChitPanel.add(chitLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 27, 380, -1));

        nineChitButton.setBackground(new java.awt.Color(239, 228, 176));
        nineChitButton.setFont(new java.awt.Font("Tahoma", 1, 11));
        nineChitButton.setText("9");
        nineChitButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        nineChitButton.setContentAreaFilled(false);
        nineChitButton.setFocusable(false);
        nineChitButton.setOpaque(true);
        nineChitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                nineChitButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                nineChitButtonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                nineChitButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                nineChitButtonMouseReleased(evt);
            }
        });
        nineChitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nineChitButtonActionPerformed(evt);
            }
        });
        customMapChitPanel.add(nineChitButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 90, 30));

        tenChitButton.setBackground(new java.awt.Color(239, 228, 176));
        tenChitButton.setFont(new java.awt.Font("Tahoma", 1, 11));
        tenChitButton.setText("10");
        tenChitButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tenChitButton.setContentAreaFilled(false);
        tenChitButton.setFocusable(false);
        tenChitButton.setOpaque(true);
        tenChitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tenChitButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tenChitButtonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tenChitButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tenChitButtonMouseReleased(evt);
            }
        });
        tenChitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tenChitButtonActionPerformed(evt);
            }
        });
        customMapChitPanel.add(tenChitButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 60, 90, 30));

        elevenChitButton.setBackground(new java.awt.Color(239, 228, 176));
        elevenChitButton.setFont(new java.awt.Font("Tahoma", 1, 11));
        elevenChitButton.setText("11");
        elevenChitButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        elevenChitButton.setContentAreaFilled(false);
        elevenChitButton.setFocusable(false);
        elevenChitButton.setOpaque(true);
        elevenChitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                elevenChitButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                elevenChitButtonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                elevenChitButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                elevenChitButtonMouseReleased(evt);
            }
        });
        elevenChitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                elevenChitButtonActionPerformed(evt);
            }
        });
        customMapChitPanel.add(elevenChitButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 60, 90, 30));

        getContentPane().add(customMapChitPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, 100));

        saveCustomMapPanel.setOpaque(false);
        saveCustomMapPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        saveMapLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        saveMapLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        saveMapLabel.setText("Would you like to save this map?");
        saveCustomMapPanel.add(saveMapLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 15, 380, -1));

        noButtonCustomMap.setBackground(new java.awt.Color(239, 228, 176));
        noButtonCustomMap.setFont(new java.awt.Font("Tahoma", 1, 11));
        noButtonCustomMap.setText("No");
        noButtonCustomMap.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        noButtonCustomMap.setContentAreaFilled(false);
        noButtonCustomMap.setFocusable(false);
        noButtonCustomMap.setOpaque(true);
        noButtonCustomMap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                noButtonCustomMapMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                noButtonCustomMapMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                noButtonCustomMapMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                noButtonCustomMapMouseReleased(evt);
            }
        });
        noButtonCustomMap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noButtonCustomMapActionPerformed(evt);
            }
        });
        saveCustomMapPanel.add(noButtonCustomMap, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 55, 110, 30));

        yesButtonCustomMap.setBackground(new java.awt.Color(239, 228, 176));
        yesButtonCustomMap.setFont(new java.awt.Font("Tahoma", 1, 11));
        yesButtonCustomMap.setText("Yes");
        yesButtonCustomMap.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        yesButtonCustomMap.setContentAreaFilled(false);
        yesButtonCustomMap.setFocusable(false);
        yesButtonCustomMap.setOpaque(true);
        yesButtonCustomMap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                yesButtonCustomMapMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                yesButtonCustomMapMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                yesButtonCustomMapMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                yesButtonCustomMapMouseReleased(evt);
            }
        });
        yesButtonCustomMap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yesButtonCustomMapActionPerformed(evt);
            }
        });
        saveCustomMapPanel.add(yesButtonCustomMap, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 55, 110, 30));

        getContentPane().add(saveCustomMapPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, 100));

        cheatFailPanel.setOpaque(false);
        cheatFailPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cheatFailLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        cheatFailLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cheatFailLabel.setText("Cheat Failed");
        cheatFailPanel.add(cheatFailLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 15, 380, -1));

        okayCheatFailButton.setBackground(new java.awt.Color(239, 228, 176));
        okayCheatFailButton.setFont(new java.awt.Font("Tahoma", 1, 11));
        okayCheatFailButton.setText("OK");
        okayCheatFailButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        okayCheatFailButton.setContentAreaFilled(false);
        okayCheatFailButton.setFocusable(false);
        okayCheatFailButton.setOpaque(true);
        okayCheatFailButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                okayCheatFailButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                okayCheatFailButtonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                okayCheatFailButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                okayCheatFailButtonMouseReleased(evt);
            }
        });
        okayCheatFailButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okayCheatFailButtonActionPerformed(evt);
            }
        });
        cheatFailPanel.add(okayCheatFailButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 55, 110, 30));

        getContentPane().add(cheatFailPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, 100));

        cantBuildSettlementPanel.setOpaque(false);
        cantBuildSettlementPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cantBuildSettlementLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        cantBuildSettlementLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cantBuildSettlementLabel.setText("You can only build on purple");
        cantBuildSettlementPanel.add(cantBuildSettlementLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 5, 380, -1));

        cantBuildSettlementLabel2.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        cantBuildSettlementLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cantBuildSettlementLabel2.setText("city spaces!");
        cantBuildSettlementPanel.add(cantBuildSettlementLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 25, 380, -1));

        okayButtonCantBuildSettlement.setBackground(new java.awt.Color(239, 228, 176));
        okayButtonCantBuildSettlement.setFont(new java.awt.Font("Tahoma", 1, 11));
        okayButtonCantBuildSettlement.setText("OK");
        okayButtonCantBuildSettlement.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        okayButtonCantBuildSettlement.setContentAreaFilled(false);
        okayButtonCantBuildSettlement.setFocusable(false);
        okayButtonCantBuildSettlement.setOpaque(true);
        okayButtonCantBuildSettlement.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                okayButtonCantBuildSettlementMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                okayButtonCantBuildSettlementMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                okayButtonCantBuildSettlementMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                okayButtonCantBuildSettlementMouseReleased(evt);
            }
        });
        okayButtonCantBuildSettlement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okayButtonCantBuildSettlementActionPerformed(evt);
            }
        });
        cantBuildSettlementPanel.add(okayButtonCantBuildSettlement, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 110, 30));

        getContentPane().add(cantBuildSettlementPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, 100));

        nameChangePanel.setOpaque(false);
        nameChangePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        playerNumberLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        playerNumberLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nameChangePanel.add(playerNumberLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, 50, -1));

        changeNameLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        changeNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        changeNameLabel.setText("Change the name of player ");
        changeNameLabel.setFocusable(false);
        nameChangePanel.add(changeNameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 380, -1));

        cancelButton.setBackground(new java.awt.Color(239, 228, 176));
        cancelButton.setFont(new java.awt.Font("Tahoma", 1, 11));
        cancelButton.setText("Cancel");
        cancelButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cancelButton.setContentAreaFilled(false);
        cancelButton.setOpaque(true);
        cancelButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cancelButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cancelButtonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cancelButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cancelButtonMouseReleased(evt);
            }
        });
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        nameChangePanel.add(cancelButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 58, 110, 30));

        applyButton.setBackground(new java.awt.Color(239, 228, 176));
        applyButton.setFont(new java.awt.Font("Tahoma", 1, 11));
        applyButton.setText("Apply");
        applyButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        applyButton.setContentAreaFilled(false);
        applyButton.setFocusable(false);
        applyButton.setOpaque(true);
        applyButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                applyButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                applyButtonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                applyButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                applyButtonMouseReleased(evt);
            }
        });
        applyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                applyButtonActionPerformed(evt);
            }
        });
        nameChangePanel.add(applyButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 58, 110, 30));
        nameChangePanel.add(nameTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(71, 35, 248, -1));

        getContentPane().add(nameChangePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, 100));

        exitDialogPanel.setOpaque(false);
        exitDialogPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        exitDialogQuestion.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        exitDialogQuestion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        exitDialogQuestion.setText("Are you sure you'd like to exit?");
        exitDialogPanel.add(exitDialogQuestion, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 380, -1));

        exitDialogQuestion2.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        exitDialogQuestion2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        exitDialogQuestion2.setText("All unsaved data may be lost.");
        exitDialogPanel.add(exitDialogQuestion2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 380, -1));

        noButton.setBackground(new java.awt.Color(239, 228, 176));
        noButton.setFont(new java.awt.Font("Tahoma", 1, 11));
        noButton.setText("No");
        noButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        noButton.setContentAreaFilled(false);
        noButton.setFocusable(false);
        noButton.setOpaque(true);
        noButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                noButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                noButtonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                noButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                noButtonMouseReleased(evt);
            }
        });
        noButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noButtonActionPerformed(evt);
            }
        });
        exitDialogPanel.add(noButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 55, 110, 30));

        yesButton.setBackground(new java.awt.Color(239, 228, 176));
        yesButton.setFont(new java.awt.Font("Tahoma", 1, 11));
        yesButton.setText("Yes");
        yesButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        yesButton.setContentAreaFilled(false);
        yesButton.setFocusable(false);
        yesButton.setOpaque(true);
        yesButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                yesButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                yesButtonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                yesButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                yesButtonMouseReleased(evt);
            }
        });
        yesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yesButtonActionPerformed(evt);
            }
        });
        exitDialogPanel.add(yesButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 55, 110, 30));

        getContentPane().add(exitDialogPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, 100));

        defaultPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        backgroundLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/questionDialogBackground.png"))); // NOI18N
        defaultPanel.add(backgroundLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(defaultPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void noButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_noButtonMouseEntered
        mouseOnNoButton=true;
}//GEN-LAST:event_noButtonMouseEntered
    private void noButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_noButtonMouseExited
        if(mouseOnNoButton&&buttonPressed){
            noButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            noButton.setLocation(noButton.getX() + 1, noButton.getY());
        }
        mouseOnNoButton=false;
        buttonPressed=false;
}//GEN-LAST:event_noButtonMouseExited
    private void noButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_noButtonMousePressed
        gameFrame.playSound("click");
        noButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        noButton.setLocation(noButton.getX()-1, noButton.getY());
        buttonPressed=true;
}//GEN-LAST:event_noButtonMousePressed
    private void noButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_noButtonMouseReleased
        buttonPressed=false;
        if(mouseOnNoButton) {
            noButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            noButton.setLocation(noButton.getX() + 1, noButton.getY());
            this.setVisible(false);
        }
    }//GEN-LAST:event_noButtonMouseReleased
    private void noButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noButtonActionPerformed
        
}//GEN-LAST:event_noButtonActionPerformed
    private void yesButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_yesButtonMouseEntered
        mouseOnYesButton=true;
    }//GEN-LAST:event_yesButtonMouseEntered
    private void yesButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_yesButtonMouseExited
        if(mouseOnYesButton&&buttonPressed){
            yesButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            yesButton.setLocation(yesButton.getX() + 1, yesButton.getY());
        }
        mouseOnYesButton=false;
        buttonPressed=false;
    }//GEN-LAST:event_yesButtonMouseExited
    private void yesButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_yesButtonMousePressed
        gameFrame.playSound("click");
        yesButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        yesButton.setLocation(yesButton.getX()-1, yesButton.getY());
        buttonPressed=true;
    }//GEN-LAST:event_yesButtonMousePressed
    private void yesButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_yesButtonMouseReleased
        buttonPressed=false;
        if(mouseOnYesButton) {
            yesButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            yesButton.setLocation(yesButton.getX() + 1, yesButton.getY());
            this.setVisible(false);
            game_Frame.setVisible(false);
            new MainFrame().setVisible(true);
        }
    }//GEN-LAST:event_yesButtonMouseReleased
    private void yesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yesButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_yesButtonActionPerformed
    private void cancelButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelButtonMouseEntered
        mouseOnCancelButton=true;
    }//GEN-LAST:event_cancelButtonMouseEntered
    private void cancelButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelButtonMouseExited
        if(mouseOnCancelButton&&buttonPressed){
            cancelButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            cancelButton.setLocation(cancelButton.getX() + 1, cancelButton.getY());
        }
        mouseOnCancelButton=false;
        buttonPressed=false;
    }//GEN-LAST:event_cancelButtonMouseExited
    private void cancelButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelButtonMousePressed
        gameFrame.playSound("click");
        cancelButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        cancelButton.setLocation(cancelButton.getX()-1, cancelButton.getY());
        buttonPressed=true;
    }//GEN-LAST:event_cancelButtonMousePressed
    private void cancelButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelButtonMouseReleased
        buttonPressed=false;
        if(mouseOnCancelButton) {
            cancelButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            cancelButton.setLocation(cancelButton.getX() + 1, cancelButton.getY());            
            this.setVisible(false);
        }
    }//GEN-LAST:event_cancelButtonMouseReleased
    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cancelButtonActionPerformed
    private void applyButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_applyButtonMouseEntered
        mouseOnApplyButton=true;
    }//GEN-LAST:event_applyButtonMouseEntered
    private void applyButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_applyButtonMouseExited
        if(mouseOnApplyButton&&buttonPressed){
            applyButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            applyButton.setLocation(applyButton.getX() + 1, applyButton.getY());
        }
        mouseOnApplyButton=false;
        buttonPressed=false;
    }//GEN-LAST:event_applyButtonMouseExited
    private void applyButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_applyButtonMousePressed
        gameFrame.playSound("click");
        applyButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        applyButton.setLocation(applyButton.getX()-1, applyButton.getY());
        buttonPressed=true;
    }//GEN-LAST:event_applyButtonMousePressed
    private void applyButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_applyButtonMouseReleased
        buttonPressed=false;
        if(mouseOnApplyButton) {
            applyButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            applyButton.setLocation(applyButton.getX() + 1, applyButton.getY());
            String s = nameTextField.getText();
            mf.name[currNameIndex]=s;
            this.setVisible(false);
        }
    }//GEN-LAST:event_applyButtonMouseReleased
    private void applyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applyButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_applyButtonActionPerformed
    private void okayButtonCantBuildSettlementMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okayButtonCantBuildSettlementMouseEntered
        mouseOnCantBuildSettlementButton=true;
    }//GEN-LAST:event_okayButtonCantBuildSettlementMouseEntered
    private void okayButtonCantBuildSettlementMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okayButtonCantBuildSettlementMouseExited
        if(mouseOnCantBuildSettlementButton&&buttonPressed){
            okayButtonCantBuildSettlement.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            okayButtonCantBuildSettlement.setLocation(okayButtonCantBuildSettlement.getX() + 1, okayButtonCantBuildSettlement.getY());
        }
        mouseOnCantBuildSettlementButton=false;
        buttonPressed=false;
    }//GEN-LAST:event_okayButtonCantBuildSettlementMouseExited
    private void okayButtonCantBuildSettlementMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okayButtonCantBuildSettlementMousePressed
        gameFrame.playSound("click");
        okayButtonCantBuildSettlement.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        okayButtonCantBuildSettlement.setLocation(okayButtonCantBuildSettlement.getX()-1, okayButtonCantBuildSettlement.getY());
        buttonPressed=true;
    }//GEN-LAST:event_okayButtonCantBuildSettlementMousePressed
    private void okayButtonCantBuildSettlementMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okayButtonCantBuildSettlementMouseReleased
        buttonPressed=false;
        if(mouseOnCantBuildSettlementButton) {
            okayButtonCantBuildSettlement.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            okayButtonCantBuildSettlement.setLocation(okayButtonCantBuildSettlement.getX() + 1, okayButtonCantBuildSettlement.getY());
            this.setVisible(false);
        }
    }//GEN-LAST:event_okayButtonCantBuildSettlementMouseReleased
    private void okayButtonCantBuildSettlementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okayButtonCantBuildSettlementActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_okayButtonCantBuildSettlementActionPerformed
    private void okayCheatSuccessButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okayCheatSuccessButtonMouseEntered
        mouseOnCheatSuccessButton=true;
    }//GEN-LAST:event_okayCheatSuccessButtonMouseEntered
    private void okayCheatSuccessButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okayCheatSuccessButtonMouseExited
        if(mouseOnCheatSuccessButton&&buttonPressed){
            okayCheatSuccessButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            okayCheatSuccessButton.setLocation(okayCheatSuccessButton.getX() + 1, okayCheatSuccessButton.getY());
        }
        mouseOnCheatSuccessButton=false;
        buttonPressed=false;
    }//GEN-LAST:event_okayCheatSuccessButtonMouseExited
    private void okayCheatSuccessButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okayCheatSuccessButtonMousePressed
        gameFrame.playSound("click");
        okayCheatSuccessButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        okayCheatSuccessButton.setLocation(okayCheatSuccessButton.getX()-1, okayCheatSuccessButton.getY());
        buttonPressed=true;
    }//GEN-LAST:event_okayCheatSuccessButtonMousePressed
    private void okayCheatSuccessButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okayCheatSuccessButtonMouseReleased
        buttonPressed=false;
        if(mouseOnCheatSuccessButton) {
            okayCheatSuccessButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            okayCheatSuccessButton.setLocation(okayCheatSuccessButton.getX() + 1, okayCheatSuccessButton.getY());
            this.setVisible(false);
        }
    }//GEN-LAST:event_okayCheatSuccessButtonMouseReleased
    private void okayCheatSuccessButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okayCheatSuccessButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_okayCheatSuccessButtonActionPerformed
    private void okayCheatFailButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okayCheatFailButtonMouseEntered
        mouseOnCheatFailButton=true;
    }//GEN-LAST:event_okayCheatFailButtonMouseEntered
    private void okayCheatFailButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okayCheatFailButtonMouseExited
        if(mouseOnCheatFailButton&&buttonPressed){
            okayCheatFailButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            okayCheatFailButton.setLocation(okayCheatFailButton.getX() + 1, okayCheatFailButton.getY());
        }
        mouseOnCheatFailButton=false;
        buttonPressed=false;
    }//GEN-LAST:event_okayCheatFailButtonMouseExited
    private void okayCheatFailButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okayCheatFailButtonMousePressed
        gameFrame.playSound("click");
        okayCheatFailButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        okayCheatFailButton.setLocation(okayCheatFailButton.getX()-1, okayCheatFailButton.getY());
        buttonPressed=true;
    }//GEN-LAST:event_okayCheatFailButtonMousePressed
    private void okayCheatFailButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okayCheatFailButtonMouseReleased
        buttonPressed=false;
        if(mouseOnCheatFailButton) {
            okayCheatFailButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            okayCheatFailButton.setLocation(okayCheatFailButton.getX() + 1, okayCheatFailButton.getY());
            this.setVisible(false);
        }
    }//GEN-LAST:event_okayCheatFailButtonMouseReleased
    private void okayCheatFailButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okayCheatFailButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_okayCheatFailButtonActionPerformed

    private void noButtonCustomMapMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_noButtonCustomMapMouseEntered
        mouseOnNoButtonCustomMap=true;
    }//GEN-LAST:event_noButtonCustomMapMouseEntered
    private void noButtonCustomMapMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_noButtonCustomMapMouseExited
        if(mouseOnNoButtonCustomMap&&buttonPressed){
            noButtonCustomMap.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            noButtonCustomMap.setLocation(noButtonCustomMap.getX() + 1, noButtonCustomMap.getY());
        }
        mouseOnNoButtonCustomMap=false;
        buttonPressed=false;
    }//GEN-LAST:event_noButtonCustomMapMouseExited
    private void noButtonCustomMapMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_noButtonCustomMapMousePressed
        gameFrame.playSound("click");
        noButtonCustomMap.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        noButtonCustomMap.setLocation(noButtonCustomMap.getX()-1, noButtonCustomMap.getY());
        buttonPressed=true;
    }//GEN-LAST:event_noButtonCustomMapMousePressed
    private void noButtonCustomMapMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_noButtonCustomMapMouseReleased
        buttonPressed=false;
        if(mouseOnNoButtonCustomMap) {
            noButtonCustomMap.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            noButtonCustomMap.setLocation(noButtonCustomMap.getX() + 1, noButtonCustomMap.getY());
            this.setVisible(false);
        }
    }//GEN-LAST:event_noButtonCustomMapMouseReleased
    private void noButtonCustomMapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noButtonCustomMapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noButtonCustomMapActionPerformed
    private void yesButtonCustomMapMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_yesButtonCustomMapMouseEntered
        mouseOnYesButtonCustomMap=true;
    }//GEN-LAST:event_yesButtonCustomMapMouseEntered
    private void yesButtonCustomMapMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_yesButtonCustomMapMouseExited
        if(mouseOnYesButtonCustomMap&&buttonPressed){
            yesButtonCustomMap.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            yesButtonCustomMap.setLocation(yesButtonCustomMap.getX() + 1, yesButtonCustomMap.getY());
        }
        mouseOnYesButtonCustomMap=false;
        buttonPressed=false;
    }//GEN-LAST:event_yesButtonCustomMapMouseExited
    private void yesButtonCustomMapMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_yesButtonCustomMapMousePressed
        gameFrame.playSound("click");
        yesButtonCustomMap.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        yesButtonCustomMap.setLocation(yesButtonCustomMap.getX()-1, yesButtonCustomMap.getY());
        buttonPressed=true;
    }//GEN-LAST:event_yesButtonCustomMapMousePressed
    private void yesButtonCustomMapMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_yesButtonCustomMapMouseReleased
        buttonPressed=false;
        if(mouseOnYesButtonCustomMap) {
            yesButtonCustomMap.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            yesButtonCustomMap.setLocation(yesButtonCustomMap.getX() + 1, yesButtonCustomMap.getY());
            this.setVisible(false);
            mf.setChits(chits);
            mf.setVisible(true);
            cmw.setVisible(false);
        }
    }//GEN-LAST:event_yesButtonCustomMapMouseReleased
    private void yesButtonCustomMapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yesButtonCustomMapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_yesButtonCustomMapActionPerformed
    private void nineChitButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nineChitButtonMouseEntered
        mouseOnNineChitButton=true;
    }//GEN-LAST:event_nineChitButtonMouseEntered
    private void nineChitButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nineChitButtonMouseExited
        if(mouseOnNineChitButton&&buttonPressed){
            nineChitButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            nineChitButton.setLocation(nineChitButton.getX() + 1, nineChitButton.getY());
        }
        mouseOnNineChitButton=false;
        buttonPressed=false;
    }//GEN-LAST:event_nineChitButtonMouseExited
    private void nineChitButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nineChitButtonMousePressed
        gameFrame.playSound("click");
        nineChitButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        nineChitButton.setLocation(nineChitButton.getX()-1, nineChitButton.getY());
        buttonPressed=true;
    }//GEN-LAST:event_nineChitButtonMousePressed
    private void nineChitButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nineChitButtonMouseReleased
        buttonPressed=false;
        if(mouseOnNineChitButton) {
            nineChitButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            nineChitButton.setLocation(nineChitButton.getX() + 1, nineChitButton.getY());
            this.setVisible(false);
            cmw.addChit(9);
        }
    }//GEN-LAST:event_nineChitButtonMouseReleased
    private void nineChitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nineChitButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nineChitButtonActionPerformed
    private void tenChitButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tenChitButtonMouseEntered
        mouseOnTenChitButton=true;
    }//GEN-LAST:event_tenChitButtonMouseEntered
    private void tenChitButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tenChitButtonMouseExited
        if(mouseOnTenChitButton&&buttonPressed){
            tenChitButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            tenChitButton.setLocation(tenChitButton.getX() + 1, tenChitButton.getY());
        }
        mouseOnTenChitButton=false;
        buttonPressed=false;
    }//GEN-LAST:event_tenChitButtonMouseExited
    private void tenChitButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tenChitButtonMousePressed
        gameFrame.playSound("click");
        tenChitButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        tenChitButton.setLocation(tenChitButton.getX()-1, tenChitButton.getY());
        buttonPressed=true;
    }//GEN-LAST:event_tenChitButtonMousePressed
    private void tenChitButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tenChitButtonMouseReleased
        buttonPressed=false;
        if(mouseOnTenChitButton) {
            tenChitButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            tenChitButton.setLocation(tenChitButton.getX() + 1, tenChitButton.getY());
            this.setVisible(false);
            cmw.addChit(10);
        }
    }//GEN-LAST:event_tenChitButtonMouseReleased
    private void tenChitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tenChitButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tenChitButtonActionPerformed
    private void elevenChitButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_elevenChitButtonMouseEntered
        mouseOnElevenChitButton=true;
    }//GEN-LAST:event_elevenChitButtonMouseEntered
    private void elevenChitButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_elevenChitButtonMouseExited
        if(mouseOnElevenChitButton&&buttonPressed){
            elevenChitButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            elevenChitButton.setLocation(elevenChitButton.getX() + 1, elevenChitButton.getY());
        }
        mouseOnElevenChitButton=false;
        buttonPressed=false;
    }//GEN-LAST:event_elevenChitButtonMouseExited
    private void elevenChitButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_elevenChitButtonMousePressed
        gameFrame.playSound("click");
        elevenChitButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        elevenChitButton.setLocation(elevenChitButton.getX()-1, elevenChitButton.getY());
        buttonPressed=true;
    }//GEN-LAST:event_elevenChitButtonMousePressed
    private void elevenChitButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_elevenChitButtonMouseReleased
        buttonPressed=false;
        if(mouseOnElevenChitButton) {
            elevenChitButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            elevenChitButton.setLocation(elevenChitButton.getX() + 1, elevenChitButton.getY());
            this.setVisible(false);
            cmw.addChit(11);
        }
    }//GEN-LAST:event_elevenChitButtonMouseReleased
    private void elevenChitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_elevenChitButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_elevenChitButtonActionPerformed
    private void okayButtonCannotChitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okayButtonCannotChitMouseEntered
        mouseOnCannotChitButton=true;
    }//GEN-LAST:event_okayButtonCannotChitMouseEntered
    private void okayButtonCannotChitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okayButtonCannotChitMouseExited
        if(mouseOnCannotChitButton&&buttonPressed){
            okayButtonCannotChit.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            okayButtonCannotChit.setLocation(okayButtonCannotChit.getX() + 1, okayButtonCannotChit.getY());
        }
        mouseOnCannotChitButton=false;
        buttonPressed=false;
    }//GEN-LAST:event_okayButtonCannotChitMouseExited
    private void okayButtonCannotChitMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okayButtonCannotChitMousePressed
        gameFrame.playSound("click");
        okayButtonCannotChit.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        okayButtonCannotChit.setLocation(okayButtonCannotChit.getX()-1, okayButtonCannotChit.getY());
        buttonPressed=true;
    }//GEN-LAST:event_okayButtonCannotChitMousePressed
    private void okayButtonCannotChitMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okayButtonCannotChitMouseReleased
        buttonPressed=false;
        if(mouseOnCannotChitButton) {
            okayButtonCannotChit.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            okayButtonCannotChit.setLocation(okayButtonCannotChit.getX() + 1, okayButtonCannotChit.getY());
            this.setVisible(false);
        }
    }//GEN-LAST:event_okayButtonCannotChitMouseReleased
    private void okayButtonCannotChitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okayButtonCannotChitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_okayButtonCannotChitActionPerformed
    private void notEnoughResourcesButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_notEnoughResourcesButtonMouseEntered
        mouseOnNotEnoughResourcesButton=true;
    }//GEN-LAST:event_notEnoughResourcesButtonMouseEntered
    private void notEnoughResourcesButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_notEnoughResourcesButtonMouseExited
        if(mouseOnNotEnoughResourcesButton&&buttonPressed){
            notEnoughResourcesButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            notEnoughResourcesButton.setLocation(notEnoughResourcesButton.getX() + 1, notEnoughResourcesButton.getY());
        }
        mouseOnNotEnoughResourcesButton=false;
        buttonPressed=false;
    }//GEN-LAST:event_notEnoughResourcesButtonMouseExited
    private void notEnoughResourcesButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_notEnoughResourcesButtonMousePressed
        gameFrame.playSound("click");
        notEnoughResourcesButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        notEnoughResourcesButton.setLocation(notEnoughResourcesButton.getX()-1, notEnoughResourcesButton.getY());
        buttonPressed=true;
    }//GEN-LAST:event_notEnoughResourcesButtonMousePressed
    private void notEnoughResourcesButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_notEnoughResourcesButtonMouseReleased
        buttonPressed=false;
        if(mouseOnNotEnoughResourcesButton) {
            notEnoughResourcesButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            notEnoughResourcesButton.setLocation(notEnoughResourcesButton.getX() + 1, notEnoughResourcesButton.getY());
            this.setVisible(false);
        }
    }//GEN-LAST:event_notEnoughResourcesButtonMouseReleased
    private void notEnoughResourcesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_notEnoughResourcesButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_notEnoughResourcesButtonActionPerformed
    private void tooManyCitiesButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tooManyCitiesButtonMouseEntered
        mouseOnTooManyCitiesButton=true;
    }//GEN-LAST:event_tooManyCitiesButtonMouseEntered
    private void tooManyCitiesButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tooManyCitiesButtonMouseExited
        if(mouseOnTooManyCitiesButton&&buttonPressed){
            tooManyCitiesButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            tooManyCitiesButton.setLocation(tooManyCitiesButton.getX() + 1, tooManyCitiesButton.getY());
        }
        mouseOnTooManyCitiesButton=false;
        buttonPressed=false;
    }//GEN-LAST:event_tooManyCitiesButtonMouseExited
    private void tooManyCitiesButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tooManyCitiesButtonMousePressed
        gameFrame.playSound("click");
        tooManyCitiesButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        tooManyCitiesButton.setLocation(tooManyCitiesButton.getX()-1, tooManyCitiesButton.getY());
        buttonPressed=true;
    }//GEN-LAST:event_tooManyCitiesButtonMousePressed
    private void tooManyCitiesButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tooManyCitiesButtonMouseReleased
        buttonPressed=false;
        if(mouseOnTooManyCitiesButton) {
            tooManyCitiesButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            tooManyCitiesButton.setLocation(tooManyCitiesButton.getX() + 1, tooManyCitiesButton.getY());
            this.setVisible(false);
        }
    }//GEN-LAST:event_tooManyCitiesButtonMouseReleased
    private void tooManyCitiesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tooManyCitiesButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tooManyCitiesButtonActionPerformed
    private void alreadyHaveRailsButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_alreadyHaveRailsButtonMouseEntered
        mouseOnTooManyRailsButton=true;
    }//GEN-LAST:event_alreadyHaveRailsButtonMouseEntered
    private void alreadyHaveRailsButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_alreadyHaveRailsButtonMouseExited
        if(mouseOnTooManyRailsButton&&buttonPressed){
            alreadyHaveRailsButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            alreadyHaveRailsButton.setLocation(alreadyHaveRailsButton.getX() + 1, alreadyHaveRailsButton.getY());
        }
        mouseOnTooManyRailsButton=false;
        buttonPressed=false;
    }//GEN-LAST:event_alreadyHaveRailsButtonMouseExited
    private void alreadyHaveRailsButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_alreadyHaveRailsButtonMousePressed
        gameFrame.playSound("click");
        alreadyHaveRailsButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        alreadyHaveRailsButton.setLocation(alreadyHaveRailsButton.getX()-1, alreadyHaveRailsButton.getY());
        buttonPressed=true;
    }//GEN-LAST:event_alreadyHaveRailsButtonMousePressed
    private void alreadyHaveRailsButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_alreadyHaveRailsButtonMouseReleased
        buttonPressed=false;
        if(mouseOnTooManyRailsButton) {
            alreadyHaveRailsButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            alreadyHaveRailsButton.setLocation(alreadyHaveRailsButton.getX() + 1, alreadyHaveRailsButton.getY());
            this.setVisible(false);
        }
    }//GEN-LAST:event_alreadyHaveRailsButtonMouseReleased
    private void alreadyHaveRailsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alreadyHaveRailsButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_alreadyHaveRailsButtonActionPerformed
    private void noButtonSettlersMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_noButtonSettlersMouseEntered
        mouseOnNoSettlersButton=true;
    }//GEN-LAST:event_noButtonSettlersMouseEntered
    private void noButtonSettlersMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_noButtonSettlersMouseExited
        if(mouseOnNoSettlersButton&&buttonPressed){
            noButtonSettlers.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            noButtonSettlers.setLocation(noButtonSettlers.getX() + 1, noButtonSettlers.getY());
        }
        mouseOnNoSettlersButton=false;
        buttonPressed=false;
    }//GEN-LAST:event_noButtonSettlersMouseExited
    private void noButtonSettlersMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_noButtonSettlersMousePressed
        gameFrame.playSound("click");
        noButtonSettlers.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        noButtonSettlers.setLocation(noButtonSettlers.getX()-1, noButtonSettlers.getY());
        buttonPressed=true;
    }//GEN-LAST:event_noButtonSettlersMousePressed
    private void noButtonSettlersMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_noButtonSettlersMouseReleased
        buttonPressed=false;
        if(mouseOnNoSettlersButton) {
            noButtonSettlers.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            noButtonSettlers.setLocation(noButtonSettlers.getX() + 1, noButtonSettlers.getY());
            this.setVisible(false);
        }
    }//GEN-LAST:event_noButtonSettlersMouseReleased
    private void noButtonSettlersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noButtonSettlersActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noButtonSettlersActionPerformed
    private void yesButtonSettlersMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_yesButtonSettlersMouseEntered
        mouseOnYesSettlersButton=true;
    }//GEN-LAST:event_yesButtonSettlersMouseEntered
    private void yesButtonSettlersMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_yesButtonSettlersMouseExited
        if(mouseOnYesSettlersButton&&buttonPressed){
            yesButtonSettlers.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            yesButtonSettlers.setLocation(yesButtonSettlers.getX() + 1, yesButtonSettlers.getY());
        }
        mouseOnYesSettlersButton=false;
        buttonPressed=false;
    }//GEN-LAST:event_yesButtonSettlersMouseExited
    private void yesButtonSettlersMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_yesButtonSettlersMousePressed
        gameFrame.playSound("click");
        yesButtonSettlers.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        yesButtonSettlers.setLocation(yesButtonSettlers.getX()-1, yesButtonSettlers.getY());
        buttonPressed=true;
    }//GEN-LAST:event_yesButtonSettlersMousePressed
    private void yesButtonSettlersMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_yesButtonSettlersMouseReleased
        buttonPressed=false;
        if(mouseOnYesSettlersButton) {
            yesButtonSettlers.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            yesButtonSettlers.setLocation(yesButtonSettlers.getX() + 1, yesButtonSettlers.getY());
            if(!game_Frame.getIgnoreResources() && currPlayer.getWheat() > 0 && currPlayer.getLumber() > 0
                    && currPlayer.getCattle() > 0) {
                currPlayer.minusWheat();
                currPlayer.minusCattle();
                currPlayer.minusLumber();
                game_Frame.buildingSettler(true, false);
                game_Frame.spokane.getIntersection().setAllIntersectionsInvisible();
                game_Frame.spokane.getIntersection().setAllCityBuildSettlerOnlySettlerVisible(currPlayer);
                gameFrame.addToInfo("Select the settler you'd like to move.", true);
                Intersection[] intersections = game_Frame.getIntersections();
                for (int i = 0; i < intersections.length; i++) {
                    if (intersections[i].getSettlerColor() == currPlayer.getColor()) {
                        intersections[i].getButton().setVisible(true);
                        intersections[i].getButton().setBorderPainted(true);
                        intersections[i].setPlayer(currPlayer);
                        intersections[i].setMoveSettler(true);
                        intersections[i].setNoMoveToCity(false);
                    }
                }
                bw.setVisible(false);
                this.setVisible(false);
            }else if(game_Frame.getIgnoreResources()){
                game_Frame.spokane.getIntersection().setAllIntersectionsInvisible();
                game_Frame.spokane.getIntersection().setAllCityBuildSettlerOnlySettlerVisible(currPlayer);
                gameFrame.addToInfo("Select the settler you'd like to move.", true);
                game_Frame.buildingSettler(true, false);
                Intersection[] intersections = game_Frame.getIntersections();
                for (int i = 0; i < intersections.length; i++) {
                    if (intersections[i].getSettlerColor() == currPlayer.getColor()) {
                        intersections[i].getButton().setVisible(true);
                        intersections[i].setPlayer(currPlayer);
                        intersections[i].setMoveSettler(true);
                        intersections[i].setNoMoveToCity(false);
                    }
                }
                bw.setVisible(false);
                this.setVisible(false);
            }else{
                //you don't have enough resources
                new SettlersConfirmDialog(9).setVisible(true);
            }
        }
    }//GEN-LAST:event_yesButtonSettlersMouseReleased
    private void yesButtonSettlersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yesButtonSettlersActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_yesButtonSettlersActionPerformed
    private void noSettlerButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_noSettlerButtonMouseEntered
        mouseOnNoSettlerButton=true;
    }//GEN-LAST:event_noSettlerButtonMouseEntered
    private void noSettlerButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_noSettlerButtonMouseExited
        if(mouseOnNoSettlerButton&&buttonPressed){
            noSettlerButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            noSettlerButton.setLocation(noSettlerButton.getX() + 1, noSettlerButton.getY());
        }
        mouseOnNoSettlerButton=false;
        buttonPressed=false;
    }//GEN-LAST:event_noSettlerButtonMouseExited
    private void noSettlerButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_noSettlerButtonMousePressed
        gameFrame.playSound("click");
        noSettlerButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        noSettlerButton.setLocation(noSettlerButton.getX()-1, noSettlerButton.getY());
        buttonPressed=true;
    }//GEN-LAST:event_noSettlerButtonMousePressed
    private void noSettlerButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_noSettlerButtonMouseReleased
        buttonPressed=false;
        if(mouseOnNoSettlerButton) {
            noSettlerButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            noSettlerButton.setLocation(noSettlerButton.getX() + 1, noSettlerButton.getY());
            this.setVisible(false);
        }
    }//GEN-LAST:event_noSettlerButtonMouseReleased
    private void noSettlerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noSettlerButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noSettlerButtonActionPerformed
    private void noButtonTrain1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_noButtonTrain1MouseReleased
        this.setVisible(false);
    }//GEN-LAST:event_noButtonTrain1MouseReleased
    private void noButtonTrain1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noButtonTrain1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noButtonTrain1ActionPerformed
    private void yesButtonTrain1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_yesButtonTrain1MouseReleased
        if (!game_Frame.getIgnoreResources() && currPlayer.getLumber() > 0
                && currPlayer.getOre() > 0
                && currPlayer.getCoal() > 0) {
            currPlayer.minusOre();
            currPlayer.minusCoal();
            currPlayer.minusLumber();
            game_Frame.buildingTrain(true, false);
            game_Frame.getRails()[0].setAllTrainsInvisible();
            gameFrame.addToInfo("Select the train you'd like to move.", true);
            ArrayList<Rail> trainRails =game_Frame.getCurrPlayer().getTrainRails();
            for (int i = 0; i < trainRails.size(); i++) {
                if (trainRails.get(i).getTrainOneColor() == currPlayer.getColor()||
                        trainRails.get(i).getTrainTwoColor()==currPlayer.getColor()) {
                    trainRails.get(i).setTrainHighlight(currPlayer.getColor());
                    trainRails.get(i).setMoveTrain(true);
                    trainRails.get(i).setNoMoveToCity(false);
                }
            }
            bw.setVisible(false);
            this.setVisible(false);
        } else if (game_Frame.getIgnoreResources()) {
            game_Frame.buildingTrain(true, false);
            game_Frame.getRails()[0].setAllTrainsInvisible();
            gameFrame.addToInfo("Select the train you'd like to move.", true);
            ArrayList<Rail> trainRails =game_Frame.getCurrPlayer().getTrainRails();
            for (int i = 0; i < trainRails.size(); i++) {
                if (trainRails.get(i).getTrainOneColor() == currPlayer.getColor()||
                        trainRails.get(i).getTrainTwoColor()==currPlayer.getColor()) {
                    trainRails.get(i).setTrainHighlight(currPlayer.getColor());
                    trainRails.get(i).setMoveTrain(true);
                    trainRails.get(i).setNoMoveToCity(false);
                }
            }
            bw.setVisible(false);
            this.setVisible(false);
        } else {
            //you don't have enough resources
            new SettlersConfirmDialog(9).setVisible(true);
        }
    }//GEN-LAST:event_yesButtonTrain1MouseReleased
    private void okayButtonWinner1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okayButtonWinner1MouseReleased
         this.setVisible(false);
         game_Frame.setVisible(false);
         new MainFrame().setVisible(true);
    }//GEN-LAST:event_okayButtonWinner1MouseReleased
    private void okayButtonWinner1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okayButtonWinner1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_okayButtonWinner1ActionPerformed
    private void notEnoughGoldButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_notEnoughGoldButton1MouseReleased
        this.setVisible(false);
    }//GEN-LAST:event_notEnoughGoldButton1MouseReleased
    private void notEnoughGoldButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_notEnoughGoldButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_notEnoughGoldButton1ActionPerformed
    private void okayButtonDPCard1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okayButtonDPCard1MouseReleased
        if(dpCardButton.isAble()){
            this.setVisible(false);
        }
    }//GEN-LAST:event_okayButtonDPCard1MouseReleased
    private void noCardsButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_noCardsButton1MouseReleased
        if(noCardsButton.isAble()){
            this.setVisible(false);
        }
    }//GEN-LAST:event_noCardsButton1MouseReleased
    private void alreadyPlayedDPButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_alreadyPlayedDPButton1MouseReleased
        if(alreadyPlayedDPButton.isAble()){
            this.setVisible(false);
        }
    }//GEN-LAST:event_alreadyPlayedDPButton1MouseReleased
    private void scoutChoiceGoldButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_scoutChoiceGoldButton1MouseReleased
        if(scoutChoiceGoldButton.isAble()){
            game_Frame.getCurrPlayer().setGold(game_Frame.getCurrPlayer().getGold()+3);
            dcw.closeWindows();
            if (bw != null && bw.isVisible()) {
                bw.setVisible(false);
            }
            dcw.setVisible(false);
            this.setVisible(false);
            gameFrame.playSound("ching");
            game_Frame.getCurrPlayer().setPlayedDPCardThisTurn(true);
            game_Frame.enableMenu();
            game_Frame.getCurrPlayer().minusDPCard(3);
            gameFrame.addToInfo(game_Frame.getCurrPlayer() + " played Scout card", false);
        }
    }//GEN-LAST:event_scoutChoiceGoldButton1MouseReleased
    private void scoutChoiceSettlerButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_scoutChoiceSettlerButton1MouseReleased
        if(scoutChoiceSettlerButton.isAble()) {
            dcw.closeWindows();
            if (bw != null && bw.isVisible()) {
                bw.setVisible(false);
            }
            dCard.playCard();
            dcw.setVisible(false);
            this.setVisible(false);
        }
    }//GEN-LAST:event_scoutChoiceSettlerButton1MouseReleased
    private void scoutChoiceSettlerButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scoutChoiceSettlerButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_scoutChoiceSettlerButton1ActionPerformed

    private void nativeSupportButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nativeSupportButton1MouseReleased
        if(nativeSupportButton.isAble()){
            this.setVisible(false);
        }
    }//GEN-LAST:event_nativeSupportButton1MouseReleased

    private void nativeSupportButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nativeSupportButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nativeSupportButton1ActionPerformed

    private void mustRollButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mustRollButton1MouseReleased
        if(mustRollButton.isAble()){
            this.setVisible(false);
        }
    }//GEN-LAST:event_mustRollButton1MouseReleased

    private void mustRollButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mustRollButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mustRollButton1ActionPerformed

    private void cannotCloseButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cannotCloseButton1MouseReleased
        if(cannotCloseButton.isAble()){
            this.setVisible(false);
        }
    }//GEN-LAST:event_cannotCloseButton1MouseReleased

    private void cannotCloseButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cannotCloseButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cannotCloseButton1ActionPerformed

    private void playerAcceptedTradeButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playerAcceptedTradeButton1MouseReleased
        if(playerAcceptedTradeButton.isAble()){
            this.setVisible(false);
        }
    }//GEN-LAST:event_playerAcceptedTradeButton1MouseReleased

    private void playerAcceptedTradeButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playerAcceptedTradeButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_playerAcceptedTradeButton1ActionPerformed

    private void noAcceptedTradeButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_noAcceptedTradeButton1MouseReleased
        if(noAcceptedTradeButton.isAble()){
            this.setVisible(false);
        }
    }//GEN-LAST:event_noAcceptedTradeButton1MouseReleased

    private void noAcceptedTradeButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noAcceptedTradeButton1ActionPerformed

    }//GEN-LAST:event_noAcceptedTradeButton1ActionPerformed

    private void selectResourceGiveButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectResourceGiveButton1MouseReleased
        if(selectResourceGiveButton.isAble()){
            this.setVisible(false);
        }
    }//GEN-LAST:event_selectResourceGiveButton1MouseReleased

    private void selectResourceGiveButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectResourceGiveButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectResourceGiveButton1ActionPerformed

    private void selectResourceGetButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectResourceGetButton1MouseReleased
        if(selectResourceGetButton.isAble()){
            this.setVisible(false);
        }
    }//GEN-LAST:event_selectResourceGetButton1MouseReleased

    private void selectResourceGetButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectResourceGetButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectResourceGetButton1ActionPerformed
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new SettlersConfirmDialog().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel DPCardLabel;
    private javax.swing.JLabel DPCardLabel1;
    private javax.swing.JButton alreadyHaveRailsButton;
    private javax.swing.JLabel alreadyHaveRailsLabel;
    private javax.swing.JLabel alreadyHaveRailsLabel1;
    private javax.swing.JPanel alreadyHaveRailsPanel;
    private javax.swing.JLabel alreadyHaveSettlersLabel;
    private javax.swing.JLabel alreadyHaveSettlersLabel1;
    private javax.swing.JPanel alreadyHaveSettlersPanel;
    private javax.swing.JLabel alreadyHaveTrainLabel;
    private javax.swing.JLabel alreadyHaveTrainLabel1;
    private javax.swing.JPanel alreadyHaveTrainsPanel;
    private javax.swing.JButton alreadyPlayedDPButton1;
    private javax.swing.JPanel alreadyPlayedDPPanel;
    private javax.swing.JLabel alreadyPlayedDPPanelLabel;
    private javax.swing.JLabel alreadyPlayedDPPanelLabel1;
    private javax.swing.JButton applyButton;
    private javax.swing.JLabel backgroundLabel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton cannotCloseButton1;
    private javax.swing.JLabel cannotCloseLabel;
    private javax.swing.JPanel cannotClosePanel;
    private javax.swing.JLabel cantBuildSettlementLabel;
    private javax.swing.JLabel cantBuildSettlementLabel2;
    private javax.swing.JPanel cantBuildSettlementPanel;
    private javax.swing.JLabel changeNameLabel;
    private javax.swing.JLabel cheatFailLabel;
    private javax.swing.JPanel cheatFailPanel;
    private javax.swing.JLabel cheatSuccessLabel;
    private javax.swing.JPanel cheatSuccessPanel;
    private javax.swing.JLabel chitLabel;
    private javax.swing.JLabel chitLabel1;
    private javax.swing.JLabel chitLabel2;
    private javax.swing.JLabel chitLabel3;
    private javax.swing.JPanel customMapCannotChit;
    private javax.swing.JPanel customMapChitPanel;
    private javax.swing.JPanel defaultPanel;
    private javax.swing.JPanel dpCardPanel;
    private javax.swing.JButton elevenChitButton;
    private javax.swing.JPanel exitDialogPanel;
    private javax.swing.JLabel exitDialogQuestion;
    private javax.swing.JLabel exitDialogQuestion2;
    private javax.swing.JButton mustRollButton1;
    private javax.swing.JLabel mustRollLabel;
    private javax.swing.JLabel mustRollLabel1;
    private javax.swing.JPanel mustRollPanel;
    private javax.swing.JPanel nameChangePanel;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JButton nativeSupportButton1;
    private javax.swing.JLabel nativeSupportLabel;
    private javax.swing.JLabel nativeSupportLabel1;
    private javax.swing.JPanel nativeSupportPanel;
    private javax.swing.JButton nineChitButton;
    private javax.swing.JPanel noAcceptTradePanel;
    private javax.swing.JButton noAcceptedTradeButton1;
    private javax.swing.JLabel noAcceptedTradeLabel;
    private javax.swing.JButton noButton;
    private javax.swing.JButton noButtonCustomMap;
    private javax.swing.JButton noButtonSettlers;
    private javax.swing.JButton noButtonTrain1;
    private javax.swing.JButton noCardsButton1;
    private javax.swing.JLabel noCardsLabel;
    private javax.swing.JLabel noCardsLabel1;
    private javax.swing.JPanel noCardsPanel;
    private javax.swing.JButton noSettlerButton;
    private javax.swing.JLabel noSettlerLabel;
    private javax.swing.JPanel noSettlerPanel;
    private javax.swing.JButton notEnoughGoldButton1;
    private javax.swing.JLabel notEnoughGoldLabel;
    private javax.swing.JLabel notEnoughGoldLabel1;
    private javax.swing.JPanel notEnoughGoldPanel;
    private javax.swing.JButton notEnoughResourcesButton;
    private javax.swing.JLabel notEnoughResourcesLabel;
    private javax.swing.JPanel notEnoughResourcesPanel;
    private javax.swing.JButton okayButtonCannotChit;
    private javax.swing.JButton okayButtonCantBuildSettlement;
    private javax.swing.JButton okayButtonDPCard1;
    private javax.swing.JButton okayButtonWinner1;
    private javax.swing.JButton okayCheatFailButton;
    private javax.swing.JButton okayCheatSuccessButton;
    private javax.swing.JButton playerAcceptedTradeButton1;
    private javax.swing.JLabel playerAcceptedTradeLabel;
    private javax.swing.JPanel playerAcceptedTradePanel;
    private javax.swing.JLabel playerNumberLabel;
    private javax.swing.JPanel saveCustomMapPanel;
    private javax.swing.JLabel saveMapLabel;
    private javax.swing.JLabel scoutCardChoiceLabel;
    private javax.swing.JLabel scoutCardChoiceLabel2;
    private javax.swing.JPanel scoutCardChoicePanel;
    private javax.swing.JButton scoutChoiceGoldButton1;
    private javax.swing.JButton scoutChoiceSettlerButton1;
    private javax.swing.JButton selectResourceGetButton1;
    private javax.swing.JLabel selectResourceGetLabel;
    private javax.swing.JPanel selectResourceGetPanel;
    private javax.swing.JButton selectResourceGiveButton1;
    private javax.swing.JLabel selectResourceGiveLabel;
    private javax.swing.JPanel selectResourceGivePanel;
    private javax.swing.JButton tenChitButton;
    private javax.swing.JButton tooManyCitiesButton;
    private javax.swing.JLabel tooManyCitiesLabel;
    private javax.swing.JLabel tooManyCitiesLabel1;
    private javax.swing.JPanel tooManyCitiesPanel;
    private javax.swing.JPanel winnerPanel;
    private javax.swing.JLabel winnerPanelLabel;
    private javax.swing.JButton yesButton;
    private javax.swing.JButton yesButtonCustomMap;
    private javax.swing.JButton yesButtonSettlers;
    private javax.swing.JButton yesButtonTrain1;
    // End of variables declaration//GEN-END:variables
}

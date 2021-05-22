/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MainFrame.java
 *
 * Created on Sep 21, 2013, 10:23:32 PM
 */
package settlerstrailstorails;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;

/**
 *
 * @author User 1
 */
public class MainFrame extends javax.swing.JFrame {

    private gameFrame game_Frame;
    private int numOfPlayers=3,numberOfHumanPlayers=4,startingGold=3;
    public static int volumeInt=7;
    private ButtonGroup optionsPlayerButtonGroup= new ButtonGroup();
    private Player.Color colorOfFirstPlayer=Player.Color.RED;
    private boolean doubleGold=false;
    private String name1,name2,name3,name4;
    String[] name=new String[4];
    boolean mouseOnBuildButton=false,mouseOnOptionsButton,MouseOnExitButton,
            buttonPressed,mouseOnPlayButton,mouseOnSetupBackButton, 
            mouseOnOptionsBackButton,mouseOnExitButton,mouseOnFourPlayerToggleButton,
            mouseOnThreePlayerToggleButton,mouseOnChangeNameButton,
            mouseOnCustomMapButton;
    CityInfoDialog cIW;
    ArrayList<Integer> chits;
    static ActionListener gameLoader;
    Timer t;
    
    /** Creates new form MainFrame */
    public MainFrame() {
        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(((dim.width/2)-(this.getSize().width/2)), ((dim.height/2)-(this.getSize().height/2)));
        try{
            setIconImage(ImageIO.read(getClass().getResource("/settlerstrailstorails/resources/icon.png")));
        }catch(Exception e){
            
        }
        catanHistoriesLabel.setIcon(new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/catanHistories.png",Color.WHITE)));
        redHouseExampleLabel.setIcon(new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/redHouse.png",Color.WHITE)));
        greenHouseExampleLabel.setIcon(new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/greenHouse.png",Color.WHITE)));
        whiteHouseExampleLabel.setIcon(new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/whiteHouse.png",Color.WHITE)));
        orangeHouseExampleLabel.setIcon(new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/orangeHouse.png",Color.WHITE)));
        redRailExampleLabel.setIcon(new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/redRailHorizontal.png",Color.WHITE)));
        greenRailExampleLabel.setIcon(new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/greenRailHorizontal.png",Color.WHITE)));
        whiteRailExampleLabel.setIcon(new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/whiteRailHorizontal.png",Color.WHITE)));
        orangeRailExampleLabel.setIcon(new ImageIcon(makeColorTransparent("/settlerstrailstorails/resources/orangeRailHorizontal.png",Color.WHITE)));
        optionsPlayerButtonGroup.add(threePlayerToggleButton);
        optionsPlayerButtonGroup.add(fourPlayerToggleButton);
        mainPanel.setVisible(true);
        optionsPanel.setVisible(false);
        setupPanel.setVisible(false);
        name[0]=name1;
        name[1]=name2;
        name[2]=name3;
        name[3]=name4;
    }
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
    for(int i = 0; i < dimg.getHeight(); i++) {  
        for(int j = 0; j < dimg.getWidth(); j++) {  
            if(dimg.getRGB(j, i) == color.getRGB()) {  
            dimg.setRGB(j, i, 0x8F1C1C);  
            }  
        }  
    }  
    return dimg;  
}  
    public static void playSound(String s) {
        if(s.equals("click")) {
            try {
                Clip clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(
                        SettlersTrailsToRails.class.getResource(
                        "/settlerstrailstorails/resources/click.wav")));
                FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                volume.setValue((volumeInt * 3.0f)-30.0f);
                clip.start();
            } catch (Exception e) {
            }
        } else if (s.equals("beep")) {
            try {
                Clip clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(
                        SettlersTrailsToRails.class.getResource(
                        "/settlerstrailstorails/resources/beep.wav")));
                FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                volume.setValue((volumeInt * 3.0f)-30.0f);
                clip.start();
            } catch (Exception e) {
            }
        }
    }
    public void setChits(ArrayList<Integer> c){
        chits=c;
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        colorChooseButtonGroup = new javax.swing.ButtonGroup();
        setupPanel = new javax.swing.JPanel();
        checkLabel = new javax.swing.JLabel();
        redRailExampleLabel = new javax.swing.JLabel();
        greenRailExampleLabel = new javax.swing.JLabel();
        orangeRailExampleLabel = new javax.swing.JLabel();
        whiteRailExampleLabel = new javax.swing.JLabel();
        whiteHouseExampleLabel = new javax.swing.JLabel();
        orangeHouseExampleLabel = new javax.swing.JLabel();
        redHouseExampleLabel = new javax.swing.JLabel();
        greenHouseExampleLabel = new javax.swing.JLabel();
        redRadioButton = new javax.swing.JRadioButton();
        whiteRadioButton = new javax.swing.JRadioButton();
        orangeRadioButton = new javax.swing.JRadioButton();
        greenRadioButton = new javax.swing.JRadioButton();
        setupTitleLabel = new javax.swing.JLabel();
        howManyPlayersLabel = new javax.swing.JLabel();
        namesPanel = new javax.swing.JPanel();
        changeNameButton = new javax.swing.JButton();
        playerNameComboBox = new javax.swing.JComboBox();
        customMapButton = new javax.swing.JButton();
        setupBackButton = new javax.swing.JButton();
        playButton = new javax.swing.JButton();
        threePlayerToggleButton = new javax.swing.JToggleButton();
        fourPlayerToggleButton = new javax.swing.JToggleButton();
        borderPanel2 = new javax.swing.JPanel();
        chooseYourColorLabel = new javax.swing.JLabel();
        redPlayerNumberLabel = new javax.swing.JLabel();
        greenPlayerNumberLabel = new javax.swing.JLabel();
        whitePlayerNumberLabel = new javax.swing.JLabel();
        orangePlayerNumberLabel = new javax.swing.JLabel();
        playerNumberTitleLabel = new javax.swing.JLabel();
        borderPanel1 = new javax.swing.JPanel();
        backGroundChooseYourPlayer = new javax.swing.JLabel();
        mainPanel = new javax.swing.JPanel();
        newGameButton = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        optionsButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        catanHistoriesLabel = new javax.swing.JLabel();
        trailsToRailsLabel = new javax.swing.JLabel();
        settlersOfAmericaLabel = new javax.swing.JLabel();
        backgroundMain = new javax.swing.JLabel();
        optionsPanel = new javax.swing.JPanel();
        optionsTitleLabel = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        borderPanel4 = new javax.swing.JPanel();
        playerOptionsLabel = new javax.swing.JLabel();
        numberOfHumanPlayersLabel = new javax.swing.JLabel();
        startingGoldLabel = new javax.swing.JLabel();
        numberOfHumanPlayersComboBox = new javax.swing.JComboBox();
        numberOfStartingGoldComboBox = new javax.swing.JComboBox();
        borderPanel3 = new javax.swing.JPanel();
        doubleGoldCheckBox = new javax.swing.JCheckBox();
        settingsLabel = new javax.swing.JLabel();
        borderPanel5 = new javax.swing.JPanel();
        technicalOptionsLabel = new javax.swing.JLabel();
        volumeSlider = new javax.swing.JSlider();
        softToLoudLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        backGroundOptions = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        setupPanel.setPreferredSize(new java.awt.Dimension(383, 520));
        setupPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        setupPanel.add(checkLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, -1, -1));
        setupPanel.add(redRailExampleLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(275, 213, -1, -1));
        setupPanel.add(greenRailExampleLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(275, 240, -1, -1));
        setupPanel.add(orangeRailExampleLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(275, 294, -1, -1));
        setupPanel.add(whiteRailExampleLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(275, 267, -1, -1));
        setupPanel.add(whiteHouseExampleLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 267, -1, -1));
        setupPanel.add(orangeHouseExampleLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 294, -1, -1));
        setupPanel.add(redHouseExampleLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 213, -1, -1));
        setupPanel.add(greenHouseExampleLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 240, -1, -1));

        redRadioButton.setBackground(new java.awt.Color(239, 228, 176));
        colorChooseButtonGroup.add(redRadioButton);
        redRadioButton.setSelected(true);
        redRadioButton.setText("Red");
        redRadioButton.setFocusable(false);
        redRadioButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioButtonClicked(evt);
            }
        });
        redRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                redRadioButtonActionPerformed(evt);
            }
        });
        setupPanel.add(redRadioButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, -1, -1));

        whiteRadioButton.setBackground(new java.awt.Color(239, 228, 176));
        colorChooseButtonGroup.add(whiteRadioButton);
        whiteRadioButton.setText("White");
        whiteRadioButton.setFocusable(false);
        whiteRadioButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioButtonClicked(evt);
            }
        });
        whiteRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                whiteRadioButtonActionPerformed(evt);
            }
        });
        setupPanel.add(whiteRadioButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 264, -1, -1));

        orangeRadioButton.setBackground(new java.awt.Color(239, 228, 176));
        colorChooseButtonGroup.add(orangeRadioButton);
        orangeRadioButton.setText("Orange");
        orangeRadioButton.setFocusable(false);
        orangeRadioButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioButtonClicked(evt);
            }
        });
        orangeRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orangeRadioButtonActionPerformed(evt);
            }
        });
        setupPanel.add(orangeRadioButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 291, -1, -1));

        greenRadioButton.setBackground(new java.awt.Color(239, 228, 176));
        colorChooseButtonGroup.add(greenRadioButton);
        greenRadioButton.setText("Green");
        greenRadioButton.setFocusable(false);
        greenRadioButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioButtonClicked(evt);
            }
        });
        greenRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                greenRadioButtonActionPerformed(evt);
            }
        });
        setupPanel.add(greenRadioButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 237, -1, -1));

        setupTitleLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 24));
        setupTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        setupTitleLabel.setText("How many Players?");
        setupTitleLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        setupPanel.add(setupTitleLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 380, 40));

        howManyPlayersLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 24));
        howManyPlayersLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        howManyPlayersLabel.setText("Setup");
        howManyPlayersLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        setupPanel.add(howManyPlayersLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 380, 40));

        namesPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        namesPanel.setOpaque(false);
        namesPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        changeNameButton.setBackground(new java.awt.Color(239, 228, 176));
        changeNameButton.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18));
        changeNameButton.setText("Change Name of Player:");
        changeNameButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        changeNameButton.setContentAreaFilled(false);
        changeNameButton.setFocusable(false);
        changeNameButton.setOpaque(true);
        changeNameButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                changeNameButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                changeNameButtonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                changeNameButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                changeNameButtonMouseReleased(evt);
            }
        });
        namesPanel.add(changeNameButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 230, 30));

        playerNameComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3" }));
        namesPanel.add(playerNameComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 25, 80, -1));

        setupPanel.add(namesPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 340, 70));

        customMapButton.setBackground(new java.awt.Color(239, 228, 176));
        customMapButton.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 24)); // NOI18N
        customMapButton.setText("Customize Map");
        customMapButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        customMapButton.setContentAreaFilled(false);
        customMapButton.setFocusable(false);
        customMapButton.setOpaque(true);
        customMapButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                customMapButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                customMapButtonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                customMapButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                customMapButtonMouseReleased(evt);
            }
        });
        setupPanel.add(customMapButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 415, 340, 35));

        setupBackButton.setBackground(new java.awt.Color(239, 228, 176));
        setupBackButton.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 24)); // NOI18N
        setupBackButton.setText("Back");
        setupBackButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setupBackButton.setContentAreaFilled(false);
        setupBackButton.setFocusable(false);
        setupBackButton.setOpaque(true);
        setupBackButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setupBackButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                setupBackButtonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                setupBackButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setupBackButtonMouseReleased(evt);
            }
        });
        setupPanel.add(setupBackButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, 160, 40));

        playButton.setBackground(new java.awt.Color(239, 228, 176));
        playButton.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 24)); // NOI18N
        playButton.setText("Play");
        playButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        playButton.setContentAreaFilled(false);
        playButton.setFocusable(false);
        playButton.setOpaque(true);
        playButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                playButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                playButtonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                playButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                playButtonMouseReleased(evt);
            }
        });
        setupPanel.add(playButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 460, 160, 40));

        threePlayerToggleButton.setBackground(new java.awt.Color(239, 228, 176));
        threePlayerToggleButton.setFont(new java.awt.Font("Tahoma", 1, 11));
        threePlayerToggleButton.setSelected(true);
        threePlayerToggleButton.setText("3 Players");
        threePlayerToggleButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        threePlayerToggleButton.setContentAreaFilled(false);
        threePlayerToggleButton.setFocusable(false);
        threePlayerToggleButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                threePlayerToggleButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                threePlayerToggleButtonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                threePlayerToggleButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                threePlayerToggleButtonMouseReleased(evt);
            }
        });
        setupPanel.add(threePlayerToggleButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 110, 130, 30));

        fourPlayerToggleButton.setBackground(new java.awt.Color(239, 228, 176));
        fourPlayerToggleButton.setFont(new java.awt.Font("Tahoma", 1, 11));
        fourPlayerToggleButton.setText("4 Players");
        fourPlayerToggleButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        fourPlayerToggleButton.setContentAreaFilled(false);
        fourPlayerToggleButton.setFocusable(false);
        fourPlayerToggleButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fourPlayerToggleButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                fourPlayerToggleButtonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                fourPlayerToggleButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                fourPlayerToggleButtonMouseReleased(evt);
            }
        });
        setupPanel.add(fourPlayerToggleButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, 130, 30));

        borderPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        borderPanel2.setOpaque(false);
        borderPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        chooseYourColorLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 24));
        chooseYourColorLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chooseYourColorLabel.setText("Choose your Color");
        chooseYourColorLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        borderPanel2.add(chooseYourColorLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 380, 40));

        redPlayerNumberLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        redPlayerNumberLabel.setText("1");
        borderPanel2.add(redPlayerNumberLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 53, -1, -1));

        greenPlayerNumberLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        greenPlayerNumberLabel.setText("2");
        borderPanel2.add(greenPlayerNumberLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        whitePlayerNumberLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        whitePlayerNumberLabel.setText("3");
        borderPanel2.add(whitePlayerNumberLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 108, -1, -1));

        orangePlayerNumberLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        borderPanel2.add(orangePlayerNumberLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 135, -1, -1));

        playerNumberTitleLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12));
        playerNumberTitleLabel.setText("player #");
        borderPanel2.add(playerNumberTitleLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 35, -1, -1));

        setupPanel.add(borderPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 340, 170));

        borderPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        borderPanel1.setOpaque(false);
        borderPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        setupPanel.add(borderPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 340, 100));

        backGroundChooseYourPlayer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/MainMenuBackground.png"))); // NOI18N
        setupPanel.add(backGroundChooseYourPlayer, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(setupPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        mainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        newGameButton.setBackground(new java.awt.Color(239, 228, 176));
        newGameButton.setFont(new java.awt.Font("Tahoma", 1, 11));
        newGameButton.setText("New Game");
        newGameButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        newGameButton.setContentAreaFilled(false);
        newGameButton.setFocusable(false);
        newGameButton.setOpaque(true);
        newGameButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                newGameButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                newGameButtonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                newGameButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                newGameButtonMouseReleased(evt);
            }
        });
        mainPanel.add(newGameButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 270, 110, 30));

        jButton4.setBackground(new java.awt.Color(239, 228, 176));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 11));
        jButton4.setText("button333232");
        jButton4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton4.setFocusable(false);
        mainPanel.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 320, 110, 30));

        jButton3.setBackground(new java.awt.Color(239, 228, 176));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 11));
        jButton3.setText("button333232");
        jButton3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton3.setFocusable(false);
        mainPanel.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 370, 110, 30));

        optionsButton.setBackground(new java.awt.Color(239, 228, 176));
        optionsButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        optionsButton.setText("Options");
        optionsButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        optionsButton.setContentAreaFilled(false);
        optionsButton.setFocusable(false);
        optionsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                optionsButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                optionsButtonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                optionsButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                optionsButtonMouseReleased(evt);
            }
        });
        mainPanel.add(optionsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 420, 110, 30));

        exitButton.setBackground(new java.awt.Color(239, 228, 176));
        exitButton.setFont(new java.awt.Font("Tahoma", 1, 11));
        exitButton.setText("Exit");
        exitButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        exitButton.setContentAreaFilled(false);
        exitButton.setFocusable(false);
        exitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exitButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                exitButtonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                exitButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                exitButtonMouseReleased(evt);
            }
        });
        mainPanel.add(exitButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 470, 110, 30));

        catanHistoriesLabel.setFont(new java.awt.Font("SF Movie Poster", 1, 48));
        catanHistoriesLabel.setForeground(new java.awt.Color(255, 255, 0));
        catanHistoriesLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        catanHistoriesLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/catanHistories.png"))); // NOI18N
        mainPanel.add(catanHistoriesLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 131, 380, 130));

        trailsToRailsLabel.setFont(new java.awt.Font("Tahoma", 2, 24));
        trailsToRailsLabel.setForeground(new java.awt.Color(255, 255, 0));
        trailsToRailsLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/trailsToRailsText.png"))); // NOI18N
        mainPanel.add(trailsToRailsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 70, 260, 40));

        settlersOfAmericaLabel.setBackground(new java.awt.Color(255, 255, 255));
        settlersOfAmericaLabel.setFont(new java.awt.Font("Tahoma", 1, 36));
        settlersOfAmericaLabel.setForeground(new java.awt.Color(255, 0, 0));
        settlersOfAmericaLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        settlersOfAmericaLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/settlersOfAmericaText.png"))); // NOI18N
        mainPanel.add(settlersOfAmericaLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 360, 40));

        backgroundMain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/MainMenuBackground.png"))); // NOI18N
        mainPanel.add(backgroundMain, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(mainPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, 520));

        optionsPanel.setPreferredSize(new java.awt.Dimension(381, 520));
        optionsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        optionsTitleLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 36));
        optionsTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        optionsTitleLabel.setText("Options");
        optionsPanel.add(optionsTitleLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 10, 380, -1));

        backButton.setBackground(new java.awt.Color(239, 228, 176));
        backButton.setFont(new java.awt.Font("Tahoma", 1, 11));
        backButton.setText("Back");
        backButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        backButton.setContentAreaFilled(false);
        backButton.setFocusable(false);
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                backButtonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                backButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                backButtonMouseReleased(evt);
            }
        });
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        optionsPanel.add(backButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 470, 110, 30));

        borderPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        borderPanel4.setFocusable(false);
        borderPanel4.setOpaque(false);
        borderPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        playerOptionsLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 24));
        playerOptionsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        playerOptionsLabel.setText("Player Options");
        borderPanel4.add(playerOptionsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 5, 340, -1));

        numberOfHumanPlayersLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14));
        numberOfHumanPlayersLabel.setText("Number of Human Players: ");
        borderPanel4.add(numberOfHumanPlayersLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        startingGoldLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14));
        startingGoldLabel.setText("Starting Gold:");
        borderPanel4.add(startingGoldLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(108, 70, 100, -1));

        numberOfHumanPlayersComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4" }));
        numberOfHumanPlayersComboBox.setSelectedIndex(3);
        numberOfHumanPlayersComboBox.setFocusable(false);
        numberOfHumanPlayersComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numberOfHumanPlayersComboBoxActionPerformed(evt);
            }
        });
        borderPanel4.add(numberOfHumanPlayersComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, -1, -1));

        numberOfStartingGoldComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "3", "6", "9", "12", "15", "18", "21", "24", "27", "30" }));
        numberOfStartingGoldComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numberOfStartingGoldComboBoxActionPerformed(evt);
            }
        });
        borderPanel4.add(numberOfStartingGoldComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 70, -1, -1));

        optionsPanel.add(borderPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 340, 110));

        borderPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        borderPanel3.setOpaque(false);
        borderPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        doubleGoldCheckBox.setBackground(new java.awt.Color(239, 228, 176));
        doubleGoldCheckBox.setFont(new java.awt.Font("Tahoma", 1, 11));
        doubleGoldCheckBox.setText("Double Gold for Port Cities");
        doubleGoldCheckBox.setFocusable(false);
        doubleGoldCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doubleGoldCheckBoxActionPerformed(evt);
            }
        });
        borderPanel3.add(doubleGoldCheckBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 45, -1, -1));

        settingsLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 24));
        settingsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        settingsLabel.setText("Game Settings");
        borderPanel3.add(settingsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 5, 340, -1));

        optionsPanel.add(borderPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 340, 130));

        borderPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        borderPanel5.setOpaque(false);
        borderPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        technicalOptionsLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 24));
        technicalOptionsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        technicalOptionsLabel.setText("Technical Options");
        borderPanel5.add(technicalOptionsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 5, 340, -1));

        volumeSlider.setBackground(new java.awt.Color(239, 228, 176));
        volumeSlider.setMaximum(10);
        volumeSlider.setSnapToTicks(true);
        volumeSlider.setValue(volumeInt);
        volumeSlider.setFocusable(false);
        volumeSlider.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                volumeSliderMouseReleased(evt);
            }
        });
        borderPanel5.add(volumeSlider, new org.netbeans.lib.awtextra.AbsoluteConstraints(66, 60, -1, -1));

        softToLoudLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/softToLoudLabel.png"))); // NOI18N
        borderPanel5.add(softToLoudLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(58, 90, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel1.setText("Sound Effect Volume");
        borderPanel5.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(71, 40, -1, -1));

        optionsPanel.add(borderPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 340, 130));

        backGroundOptions.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settlerstrailstorails/resources/MainMenuBackground.png"))); // NOI18N
        optionsPanel.add(backGroundOptions, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(optionsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        
    }//GEN-LAST:event_backButtonActionPerformed
    private void whiteRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_whiteRadioButtonActionPerformed
        colorOfFirstPlayer=Player.Color.WHITE;
        if(numOfPlayers==3){
            whitePlayerNumberLabel.setText("1");
            greenPlayerNumberLabel.setText("3");
            redPlayerNumberLabel.setText("2");
            orangePlayerNumberLabel.setText("");
        }else if(numOfPlayers==4){
            whitePlayerNumberLabel.setText("1");
            greenPlayerNumberLabel.setText("4");
            redPlayerNumberLabel.setText("3");
            orangePlayerNumberLabel.setText("2");            
        }
    }//GEN-LAST:event_whiteRadioButtonActionPerformed
    private void redRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_redRadioButtonActionPerformed
        colorOfFirstPlayer=Player.Color.RED;
        if(numOfPlayers==3){
            redPlayerNumberLabel.setText("1");
            greenPlayerNumberLabel.setText("2");
            whitePlayerNumberLabel.setText("3");
            orangePlayerNumberLabel.setText("");
        }else if(numOfPlayers==4){
            redPlayerNumberLabel.setText("1");
            greenPlayerNumberLabel.setText("2");
            whitePlayerNumberLabel.setText("3");
            orangePlayerNumberLabel.setText("4");            
        }
    }//GEN-LAST:event_redRadioButtonActionPerformed
    private void greenRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_greenRadioButtonActionPerformed
        colorOfFirstPlayer=Player.Color.GREEN;
        if(numOfPlayers==3){
            greenPlayerNumberLabel.setText("1");
            redPlayerNumberLabel.setText("3");
            whitePlayerNumberLabel.setText("2");
            orangePlayerNumberLabel.setText("");
        }else if(numOfPlayers==4){
            greenPlayerNumberLabel.setText("1");
            redPlayerNumberLabel.setText("4");
            whitePlayerNumberLabel.setText("2");
            orangePlayerNumberLabel.setText("3");            
        }
    }//GEN-LAST:event_greenRadioButtonActionPerformed
    private void orangeRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orangeRadioButtonActionPerformed
        colorOfFirstPlayer=Player.Color.ORANGE;
        if(numOfPlayers==3){
            orangePlayerNumberLabel.setText("1");
            greenPlayerNumberLabel.setText("2");
            whitePlayerNumberLabel.setText("3");
            redPlayerNumberLabel.setText("");
        }else if(numOfPlayers==4){
            orangePlayerNumberLabel.setText("1");
            greenPlayerNumberLabel.setText("3");
            whitePlayerNumberLabel.setText("4");
            redPlayerNumberLabel.setText("2");            
        }
    }//GEN-LAST:event_orangeRadioButtonActionPerformed
    private void doubleGoldCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doubleGoldCheckBoxActionPerformed
        playSound("click");
        doubleGold = !doubleGold;
    }//GEN-LAST:event_doubleGoldCheckBoxActionPerformed
    private void numberOfHumanPlayersComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numberOfHumanPlayersComboBoxActionPerformed
        numberOfHumanPlayers = Integer.parseInt((String)numberOfHumanPlayersComboBox.getSelectedItem());
    }//GEN-LAST:event_numberOfHumanPlayersComboBoxActionPerformed
    private void newGameButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_newGameButtonMouseEntered
        mouseOnBuildButton=true;
}//GEN-LAST:event_newGameButtonMouseEntered
    private void newGameButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_newGameButtonMouseExited
        if(mouseOnBuildButton&&buttonPressed){
            newGameButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            newGameButton.setLocation(newGameButton.getX() + 1, newGameButton.getY());
        }
        mouseOnBuildButton=false;
        buttonPressed=false;
}//GEN-LAST:event_newGameButtonMouseExited
    private void newGameButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_newGameButtonMousePressed
        playSound("click");
        newGameButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        newGameButton.setLocation(newGameButton.getX()-1, newGameButton.getY());
        buttonPressed=true;
}//GEN-LAST:event_newGameButtonMousePressed
    private void newGameButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_newGameButtonMouseReleased
        buttonPressed=false;
        if(mouseOnBuildButton) {
            newGameButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            newGameButton.setLocation(newGameButton.getX() + 1, newGameButton.getY());
            setupPanel.setVisible(true);
            mainPanel.setVisible(false);
        }
}//GEN-LAST:event_newGameButtonMouseReleased
    private void playButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playButtonMouseEntered
        mouseOnPlayButton=true;
    }//GEN-LAST:event_playButtonMouseEntered
    private void playButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playButtonMouseExited
        if(mouseOnPlayButton&&buttonPressed){
            playButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            playButton.setLocation(playButton.getX() + 1, playButton.getY());
        }
        mouseOnPlayButton=false;
        buttonPressed=false;
    }//GEN-LAST:event_playButtonMouseExited
    private void playButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playButtonMousePressed
        playSound("click");
        playButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        playButton.setLocation(playButton.getX() - 1, playButton.getY());
        buttonPressed = true;
    }//GEN-LAST:event_playButtonMousePressed
    private void playButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playButtonMouseReleased
        buttonPressed = false;
        if (mouseOnPlayButton) {
            playButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            playButton.setLocation(playButton.getX() + 1, playButton.getY());
            this.setVisible(false);
            final ProgressBar j = new ProgressBar();           
            gameLoader = new ActionListener() {//<editor-fold>

                @Override
                public void actionPerformed(ActionEvent evt) {
                    game_Frame = new gameFrame(numOfPlayers, numberOfHumanPlayers,
                            colorOfFirstPlayer, doubleGold, name[0], name[1], name[2],
                            name[3], startingGold, chits);

                    game_Frame.addWindowListener(
                            new WindowAdapter() {

                                @Override
                                public void windowClosing(WindowEvent we) {
                                    new SettlersConfirmDialog(game_Frame, 1, null).setVisible(true);
                                }
                            });
                    game_Frame.setVisible(
                            true);
                    game_Frame.setupPhase();
                    t.stop();
                    j.setVisible(false);
                }
            };
            t = new Timer(200, gameLoader);
            t.start();
        }
    }//GEN-LAST:event_playButtonMouseReleased
    private void setupBackButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_setupBackButtonMouseEntered
        mouseOnSetupBackButton=true;
    }//GEN-LAST:event_setupBackButtonMouseEntered
    private void setupBackButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_setupBackButtonMouseExited
        if(mouseOnSetupBackButton&&buttonPressed){
            setupBackButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            setupBackButton.setLocation(setupBackButton.getX() + 1, setupBackButton.getY());
        }
        mouseOnSetupBackButton=false;
        buttonPressed=false;
    }//GEN-LAST:event_setupBackButtonMouseExited
    private void setupBackButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_setupBackButtonMousePressed
        playSound("click");
        setupBackButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        setupBackButton.setLocation(setupBackButton.getX()-1, setupBackButton.getY());
        buttonPressed=true;
    }//GEN-LAST:event_setupBackButtonMousePressed
    private void setupBackButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_setupBackButtonMouseReleased
        buttonPressed=false;
        if(mouseOnSetupBackButton) {
            setupBackButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            setupBackButton.setLocation(setupBackButton.getX() + 1, setupBackButton.getY());
            setupPanel.setVisible(false);
            mainPanel.setVisible(true);
        }
    }//GEN-LAST:event_setupBackButtonMouseReleased
    private void threePlayerToggleButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_threePlayerToggleButtonMouseReleased
        
    }//GEN-LAST:event_threePlayerToggleButtonMouseReleased
    private void fourPlayerToggleButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fourPlayerToggleButtonMousePressed
        playSound("click");
        if (numOfPlayers == 3) {
            fourPlayerToggleButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            fourPlayerToggleButton.setLocation(fourPlayerToggleButton.getX() - 1, fourPlayerToggleButton.getY());
            threePlayerToggleButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            threePlayerToggleButton.setLocation(threePlayerToggleButton.getX() + 1, threePlayerToggleButton.getY());
            numOfPlayers = 4;
            playerNameComboBox.addItem((Integer) 4);
            if (colorOfFirstPlayer == Player.Color.ORANGE) {
                orangePlayerNumberLabel.setText("1");
                greenPlayerNumberLabel.setText("3");
                whitePlayerNumberLabel.setText("4");
                redPlayerNumberLabel.setText("2");
            } else if (colorOfFirstPlayer == Player.Color.WHITE) {
                whitePlayerNumberLabel.setText("1");
                greenPlayerNumberLabel.setText("4");
                redPlayerNumberLabel.setText("3");
                orangePlayerNumberLabel.setText("2");
            } else if (colorOfFirstPlayer == Player.Color.RED) {
                redPlayerNumberLabel.setText("1");
                greenPlayerNumberLabel.setText("2");
                whitePlayerNumberLabel.setText("3");
                orangePlayerNumberLabel.setText("4");
            } else if (colorOfFirstPlayer == Player.Color.GREEN) {
                greenPlayerNumberLabel.setText("1");
                redPlayerNumberLabel.setText("4");
                whitePlayerNumberLabel.setText("2");
                orangePlayerNumberLabel.setText("3");
            }
        }
    }//GEN-LAST:event_fourPlayerToggleButtonMousePressed
    private void fourPlayerToggleButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fourPlayerToggleButtonMouseEntered
        mouseOnFourPlayerToggleButton = true;
    }//GEN-LAST:event_fourPlayerToggleButtonMouseEntered
    private void fourPlayerToggleButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fourPlayerToggleButtonMouseExited
        if (mouseOnFourPlayerToggleButton && buttonPressed) {
            fourPlayerToggleButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            fourPlayerToggleButton.setLocation(fourPlayerToggleButton.getX() + 1, fourPlayerToggleButton.getY() + 1);
        }
        mouseOnFourPlayerToggleButton = false;
        buttonPressed = false;
    }//GEN-LAST:event_fourPlayerToggleButtonMouseExited
    private void fourPlayerToggleButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fourPlayerToggleButtonMouseReleased
    }//GEN-LAST:event_fourPlayerToggleButtonMouseReleased
    private void threePlayerToggleButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_threePlayerToggleButtonMousePressed
        playSound("click");
        if (numOfPlayers == 4) {
            threePlayerToggleButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            threePlayerToggleButton.setLocation(threePlayerToggleButton.getX() - 1, threePlayerToggleButton.getY());
            fourPlayerToggleButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            fourPlayerToggleButton.setLocation(fourPlayerToggleButton.getX() + 1, fourPlayerToggleButton.getY());
            numOfPlayers = 3;
            playerNameComboBox.removeItem((Integer) 4);
            if (colorOfFirstPlayer == Player.Color.ORANGE) {
                orangePlayerNumberLabel.setText("1");
                greenPlayerNumberLabel.setText("2");
                whitePlayerNumberLabel.setText("3");
                redPlayerNumberLabel.setText("");
            } else if (colorOfFirstPlayer == Player.Color.WHITE) {
                whitePlayerNumberLabel.setText("1");
                greenPlayerNumberLabel.setText("3");
                redPlayerNumberLabel.setText("2");
                orangePlayerNumberLabel.setText("");
            } else if (colorOfFirstPlayer == Player.Color.RED) {
                redPlayerNumberLabel.setText("1");
                greenPlayerNumberLabel.setText("2");
                whitePlayerNumberLabel.setText("3");
                orangePlayerNumberLabel.setText("");
            } else if (colorOfFirstPlayer == Player.Color.GREEN) {
                greenPlayerNumberLabel.setText("1");
                redPlayerNumberLabel.setText("3");
                whitePlayerNumberLabel.setText("2");
                orangePlayerNumberLabel.setText("");
            }
        }
    }//GEN-LAST:event_threePlayerToggleButtonMousePressed
    private void threePlayerToggleButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_threePlayerToggleButtonMouseExited
        if(mouseOnThreePlayerToggleButton&&buttonPressed){
            threePlayerToggleButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            threePlayerToggleButton.setLocation(threePlayerToggleButton.getX() + 1, threePlayerToggleButton.getY() + 1);
        }
        mouseOnThreePlayerToggleButton=false;
        buttonPressed=false;
    }//GEN-LAST:event_threePlayerToggleButtonMouseExited
    private void threePlayerToggleButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_threePlayerToggleButtonMouseEntered
        mouseOnThreePlayerToggleButton=true;
    }//GEN-LAST:event_threePlayerToggleButtonMouseEntered
    private void radioButtonClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioButtonClicked
        playSound("click");
    }//GEN-LAST:event_radioButtonClicked
    private void volumeSliderMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_volumeSliderMouseReleased
        volumeInt=volumeSlider.getValue();
        playSound("beep");
    }//GEN-LAST:event_volumeSliderMouseReleased
    private void numberOfStartingGoldComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numberOfStartingGoldComboBoxActionPerformed
        startingGold = Integer.parseInt((String)numberOfStartingGoldComboBox.getSelectedItem());
    }//GEN-LAST:event_numberOfStartingGoldComboBoxActionPerformed

    private void optionsButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_optionsButtonMousePressed
        playSound("click");
        optionsButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        optionsButton.setLocation(optionsButton.getX()-1, optionsButton.getY());
        buttonPressed=true;
    }//GEN-LAST:event_optionsButtonMousePressed
    private void optionsButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_optionsButtonMouseReleased
        buttonPressed=false;
        if(mouseOnOptionsButton) {
            optionsButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            optionsButton.setLocation(optionsButton.getX() + 1, optionsButton.getY());
            optionsPanel.setVisible(true);
            mainPanel.setVisible(false);
        }
    }//GEN-LAST:event_optionsButtonMouseReleased
    private void optionsButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_optionsButtonMouseEntered
        mouseOnOptionsButton=true;
    }//GEN-LAST:event_optionsButtonMouseEntered
    private void optionsButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_optionsButtonMouseExited
        if(mouseOnOptionsButton&&buttonPressed){
            optionsButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            optionsButton.setLocation(optionsButton.getX() + 1, optionsButton.getY());
        }
        mouseOnOptionsButton=false;
        buttonPressed=false;
    }//GEN-LAST:event_optionsButtonMouseExited
    private void exitButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButtonMouseReleased
        buttonPressed=false;
        if(mouseOnExitButton) {
            exitButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            exitButton.setLocation(exitButton.getX() + 1, exitButton.getY());
            System.exit(0);
        }
    }//GEN-LAST:event_exitButtonMouseReleased
    private void exitButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButtonMousePressed
        playSound("click");
        exitButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        exitButton.setLocation(exitButton.getX()-1, exitButton.getY());
        buttonPressed=true;
    }//GEN-LAST:event_exitButtonMousePressed
    private void exitButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButtonMouseExited
        if(mouseOnExitButton&&buttonPressed){
            exitButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            exitButton.setLocation(exitButton.getX() + 1, exitButton.getY());
        }
        mouseOnExitButton=false;
        buttonPressed=false;
    }//GEN-LAST:event_exitButtonMouseExited
    private void exitButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButtonMouseEntered
        mouseOnExitButton=true;
    }//GEN-LAST:event_exitButtonMouseEntered
    private void backButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backButtonMouseReleased
        buttonPressed=false;
        if(mouseOnOptionsBackButton) {
            backButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            backButton.setLocation(backButton.getX() + 1, backButton.getY());
            optionsPanel.setVisible(false);
            mainPanel.setVisible(true);
        }
        
    }//GEN-LAST:event_backButtonMouseReleased
    private void backButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backButtonMousePressed
        playSound("click");
        backButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        backButton.setLocation(backButton.getX()-1, backButton.getY());
        buttonPressed=true;
    }//GEN-LAST:event_backButtonMousePressed
    private void backButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backButtonMouseEntered
        mouseOnOptionsBackButton=true;
    }//GEN-LAST:event_backButtonMouseEntered
    private void backButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backButtonMouseExited
        if(mouseOnOptionsBackButton&&buttonPressed){
            backButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            backButton.setLocation(backButton.getX() + 1, backButton.getY());
        }
        mouseOnOptionsBackButton=false;
        buttonPressed=false;
    }//GEN-LAST:event_backButtonMouseExited
    private void changeNameButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_changeNameButtonMouseEntered
        mouseOnChangeNameButton=true;
    }//GEN-LAST:event_changeNameButtonMouseEntered
    private void changeNameButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_changeNameButtonMouseExited
        if(mouseOnChangeNameButton&&buttonPressed){
            changeNameButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            changeNameButton.setLocation(changeNameButton.getX() + 1, changeNameButton.getY());
        }
        mouseOnChangeNameButton=false;
        buttonPressed=false;
    }//GEN-LAST:event_changeNameButtonMouseExited
    private void changeNameButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_changeNameButtonMousePressed
        playSound("click");
        changeNameButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        changeNameButton.setLocation(changeNameButton.getX()-1, changeNameButton.getY());
        buttonPressed=true;
    }//GEN-LAST:event_changeNameButtonMousePressed
    private void changeNameButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_changeNameButtonMouseReleased
        buttonPressed=false;
        if(mouseOnChangeNameButton) {
            changeNameButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            changeNameButton.setLocation(changeNameButton.getX() + 1, changeNameButton.getY());
            SettlersConfirmDialog s = new SettlersConfirmDialog(game_Frame,2,this);
            s.setVisible(true);
            s.changeNameOfPlayer((playerNameComboBox.getSelectedIndex()+1), 
                    name[playerNameComboBox.getSelectedIndex()],
                    playerNameComboBox.getSelectedIndex());
        }
    }//GEN-LAST:event_changeNameButtonMouseReleased
    private void customMapButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customMapButtonMouseEntered
        mouseOnCustomMapButton=true;
    }//GEN-LAST:event_customMapButtonMouseEntered
    private void customMapButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customMapButtonMouseExited
        if(mouseOnCustomMapButton&&buttonPressed){
            customMapButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            customMapButton.setLocation(customMapButton.getX() + 1, customMapButton.getY());
        }
        mouseOnCustomMapButton=false;
        buttonPressed=false;
    }//GEN-LAST:event_customMapButtonMouseExited
    private void customMapButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customMapButtonMousePressed
        playSound("click");
        customMapButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        customMapButton.setLocation(customMapButton.getX()-1, customMapButton.getY());
        buttonPressed=true;
    }//GEN-LAST:event_customMapButtonMousePressed
    private void customMapButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customMapButtonMouseReleased
        buttonPressed=false;
        if(mouseOnCustomMapButton) {
            customMapButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            customMapButton.setLocation(customMapButton.getX() + 1, customMapButton.getY());
            this.setVisible(false);
            customMapWindow cmw = new customMapWindow(this, chits);
            cmw.setVisible(true);
        }
    }//GEN-LAST:event_customMapButtonMouseReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JLabel backGroundChooseYourPlayer;
    private javax.swing.JLabel backGroundOptions;
    private javax.swing.JLabel backgroundMain;
    private javax.swing.JPanel borderPanel1;
    private javax.swing.JPanel borderPanel2;
    private javax.swing.JPanel borderPanel3;
    private javax.swing.JPanel borderPanel4;
    private javax.swing.JPanel borderPanel5;
    private javax.swing.JLabel catanHistoriesLabel;
    private javax.swing.JButton changeNameButton;
    private javax.swing.JLabel checkLabel;
    private javax.swing.JLabel chooseYourColorLabel;
    private javax.swing.ButtonGroup colorChooseButtonGroup;
    private javax.swing.JButton customMapButton;
    private javax.swing.JCheckBox doubleGoldCheckBox;
    private javax.swing.JButton exitButton;
    private javax.swing.JToggleButton fourPlayerToggleButton;
    private javax.swing.JLabel greenHouseExampleLabel;
    private javax.swing.JLabel greenPlayerNumberLabel;
    private javax.swing.JRadioButton greenRadioButton;
    private javax.swing.JLabel greenRailExampleLabel;
    private javax.swing.JLabel howManyPlayersLabel;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel namesPanel;
    private javax.swing.JButton newGameButton;
    private javax.swing.JComboBox numberOfHumanPlayersComboBox;
    private javax.swing.JLabel numberOfHumanPlayersLabel;
    private javax.swing.JComboBox numberOfStartingGoldComboBox;
    private javax.swing.JButton optionsButton;
    private javax.swing.JPanel optionsPanel;
    private javax.swing.JLabel optionsTitleLabel;
    private javax.swing.JLabel orangeHouseExampleLabel;
    private javax.swing.JLabel orangePlayerNumberLabel;
    private javax.swing.JRadioButton orangeRadioButton;
    private javax.swing.JLabel orangeRailExampleLabel;
    private javax.swing.JButton playButton;
    private javax.swing.JComboBox playerNameComboBox;
    private javax.swing.JLabel playerNumberTitleLabel;
    private javax.swing.JLabel playerOptionsLabel;
    private javax.swing.JLabel redHouseExampleLabel;
    private javax.swing.JLabel redPlayerNumberLabel;
    private javax.swing.JRadioButton redRadioButton;
    private javax.swing.JLabel redRailExampleLabel;
    private javax.swing.JLabel settingsLabel;
    private javax.swing.JLabel settlersOfAmericaLabel;
    private javax.swing.JButton setupBackButton;
    private javax.swing.JPanel setupPanel;
    private javax.swing.JLabel setupTitleLabel;
    private javax.swing.JLabel softToLoudLabel;
    private javax.swing.JLabel startingGoldLabel;
    private javax.swing.JLabel technicalOptionsLabel;
    private javax.swing.JToggleButton threePlayerToggleButton;
    private javax.swing.JLabel trailsToRailsLabel;
    private javax.swing.JSlider volumeSlider;
    private javax.swing.JLabel whiteHouseExampleLabel;
    private javax.swing.JLabel whitePlayerNumberLabel;
    private javax.swing.JRadioButton whiteRadioButton;
    private javax.swing.JLabel whiteRailExampleLabel;
    // End of variables declaration//GEN-END:variables
}

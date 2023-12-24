/**
 * class RobotGUI
 * RobotGUI add gui to the Robot bug lab for CPSC 115
 *
 * @author Federico Cedolini
 * @version 1.0
 * @since 05/02/2021
 * Known issues: none
 */

import java.awt.Color;
import java.awt.event.*;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.io.IOException;
import java.io.File;
import javax.sound.sampled.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JOptionPane;

class RobotGUI extends JFrame {
  private static final int MAXBUGS = 3;
  private static final int MAXWEAPONS = 2; //weapons per bug
  private static final int MAXSOUNDS = 6;

  //GUI
  ImageIcon[] robotsImg = new ImageIcon[MAXBUGS];
  //ImageIcon robot1Img = new ImageIcon("./Images/Mr_Robot.png");//will move to array when using diff images
  JLabel[] labelBugs = new JLabel[MAXBUGS];
  JButton[][] weapons = new JButton[MAXBUGS][MAXWEAPONS];
  JProgressBar[] health = new JProgressBar[MAXBUGS];
  GridBagConstraints gbc = new GridBagConstraints();
  int x = 0, y = 0, bugNum = 0;
  //SOUND
  String[] filenames = {"./Audios/hit.wav",  "./Audios/oneScream.wav", "./Audios/twoScreams.wav", "./Audios/threeScreams.wav","./Audios/longhonk.wav", "./Audios/smallhonk.wav"};
  File audioFile;
  AudioInputStream audioStream;
  Clip[] audioClips = new Clip[MAXSOUNDS];

  //Constructor
  RobotGUI(){
    this.setTitle("Robot Bug Lab");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false);
    this.setSize(800,450);
    this.setLayout(new GridBagLayout());
    setupAudio();
  }//Constructor

  /**
   * addBug adds a robot bug to the gui
   * @param myBug is the object that contains the information of the bug
   * @return gui id for the bug// can change to void and change bug's id directly
   */
  public int addBug(Bug myBug){
    if(bugNum >= MAXBUGS){
      System.out.println("MAX number of bugs reached.");
      return -1;
    }
    //display bug
    gbc.gridx = x;
    gbc.gridy = y;
    labelBugs[bugNum] = new JLabel();
    labelBugs[bugNum].setText(myBug.getName());
    robotsImg[bugNum] = new ImageIcon(myBug.getRobotImage()); //The image is set here
    labelBugs[bugNum].setIcon(robotsImg[bugNum]);
    labelBugs[bugNum].setHorizontalTextPosition(JLabel.CENTER);
    labelBugs[bugNum].setVerticalTextPosition(JLabel.TOP);
    labelBugs[bugNum].setFont(new Font("Serif", Font.BOLD, 30));
    this.add(labelBugs[bugNum], gbc);
    //display weapons and health
    setGUIWeapons(myBug);
    setHealthBar(myBug);
    bugNum++;
    x++;
    y = 0;
    return bugNum-1;
  }//addBug

  /**
   * setGUIWeapons creates the buttons for the weapons
   * @param myBug is the object that contains the information of the bug
   */
  private void setGUIWeapons(Bug myBug){
    //weapon 1
    weapons[x][y] = new JButton(myBug.getWeapon1());
    weapons[x][y].addActionListener(e -> myBug.hit(1, this));
    gbc.gridy = y+1;
    this.add(weapons[x][y], gbc);

    //weapon 2
    y++;
    weapons[x][y] = new JButton(myBug.getWeapon2());
    weapons[x][y].addActionListener(e -> myBug.hit(2, this));
    gbc.gridy = y+1;
    this.add(weapons[x][y], gbc);
  }//setGUIWeapons

  /**
   * setHealthBar creates progress bar for bug HP and set it to max
   * @param myBug is the object that contains the information of the bug
   */
  private void setHealthBar(Bug myBug){
    health[bugNum] = new JProgressBar(0, myBug.getEnergyLevel());
    health[bugNum].setValue(myBug.getEnergyLevel());
    health[bugNum].setString("Health " + myBug.getEnergyLevel());
    health[bugNum].setStringPainted(true);
    health[bugNum].setForeground(Color.red);
    gbc.gridy = y+2;

    this.add(health[bugNum], gbc);
  }//setHealthBar

  /**
   * hitBugGUI reduces bug's visual hp by updating progress bar
   * @param id is bug's gui id
   * @param energyLevel is the new hp for the bug
   */
  public void hitBugGUI(int id, int energyLevel){
    if(id < 0 || id >= MAXBUGS){
      System.out.println("Invalid ID");
      return;
    }
    audioClips[0].setMicrosecondPosition(0);
    health[id].setValue(energyLevel);
    if(energyLevel == 0)
      health[id].setString("DEAD");
    else{
      health[id].setString("Health " + energyLevel);
    }
    audioClips[0].start();
  }//hitBugGUI

  /**
   * playSound plays the selected sound
   * @param audioSelection, audio index to be played
   */
  public void playSound(int audioSelection){
    if(audioSelection >= 6){
      System.out.println("Invalid selection");
      return;
    }
    audioClips[audioSelection].setMicrosecondPosition(0);
    audioClips[audioSelection].start();
  }//playSound

  public void displayMessage(String message, String bugname){
    JOptionPane.showMessageDialog(this, message, bugname + " says:", JOptionPane.ERROR_MESSAGE);
  }

  /**
   * showGUI make gui visible
   */
  public void showGUI(){
    this.setVisible(true);
  }//showGUI

  /**
   * setupAudio opens and prepares audio file for when bug is hit
   */
  private void setupAudio(){
    try{
      for(int i = 0; i < MAXSOUNDS; i++){
        audioFile = new File(filenames[i]);
        audioStream = AudioSystem.getAudioInputStream(audioFile);
        audioClips[i] = AudioSystem.getClip();
        audioClips[i].open(audioStream);
      }
    } catch(Exception e){
      System.out.println("Error with Audio: " + e);
    }
  }//setupAudio
}//RobotGUI

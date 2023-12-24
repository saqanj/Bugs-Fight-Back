
/**
 * Yelly bug class which implements the bug class.
 * 
 * @author Saqlain Anjum
 * @version 1.0.0
 */
public class YellyBug extends Bug
{
    /**
     * Instance variables.
     */
    private static final String YELLY_BUG_PIC = "./images/Yellybug.png";
    private static final int SINGLE_YELL = 1, DOUBLE_YELL = 2, TRIPLE_YELL = 3;
    private int initialEnergyLevel;
    
    /**
     * YellyBug constructor method.
     * 
     * @param name The name of the bug. 
     * @param initialEnergyLevel The bug's initial energy level.
     * @param weapon1Desc Description of thefirst weapon.
     * @param weapon1Power First weapon power level.
     * @param weapon2Desc Description of the second weapon.
     * @param weapon2Power Second weapon power level.
     */
    public YellyBug(String name, int initialEnergyLevel, String weapon1Desc, int weapon1Power, String weapon2Desc, int weapon2Power){
        super(name, initialEnergyLevel, weapon1Desc, weapon1Power, weapon2Desc, weapon2Power);
        this.initialEnergyLevel = initialEnergyLevel;
        robotImage = YELLY_BUG_PIC;
    }
    /**
     * Implemented abstract method for playing sounds upon being hit.
     * 
     * @param myGUI specific ROBOT Gui for visual response.
     */
    public void onHit(RobotGUI myGUI){
        double level_1 = 0.67 * initialEnergyLevel;
        double level_2 = 0.33 * initialEnergyLevel;
        double level_3 = 0.00;
        if(energyLevel >= level_1){
            myGUI.playSound(TRIPLE_YELL);
        }
        else if(energyLevel >= level_2){
            myGUI.playSound(DOUBLE_YELL);
        }
        else if(energyLevel > level_3){
            myGUI.playSound(SINGLE_YELL);
        }
    }
}

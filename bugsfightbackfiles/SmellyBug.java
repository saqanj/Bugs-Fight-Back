
/**
 * Specific smelly bug class, extending the bug.
 * 
 * @author Saqlain Anjum
 * @version 1.0.0
 */
public class SmellyBug extends Bug {
    /**
     * Instance variables.
     */
    private final static String SMELLY_BUG_PIC = "./Images/Smellybug.png";
    private final static int STRONG_SMELL = 4, LAST_WHIFF = 5;
    private String bugSmell;
    private String name;

    /**
     * Constructor for a smelly bug.
     * 
     * @param name               Name of the bug.
     * @param initialEnergyLevel Initial energy level.
     * @param weapon1Desc        Weapon 1 description.
     * @param weapon1Power       Weapon 1 power-level.
     * @param weapon2Desc        Weapon 2 description.
     * @param weapon2Power       Weapon 2 power-level.
     * @param odorDesc           Description of odor released upon hit.
     */
    public SmellyBug(String name, int initialEnergyLevel, String weapon1Desc, int weapon1Power, String weapon2Desc,
            int weapon2Power, String odorDesc) {
        super(name, initialEnergyLevel, weapon1Desc, weapon1Power, weapon2Desc, weapon2Power);
        bugSmell = odorDesc;
        this.name = name;
        robotImage = SMELLY_BUG_PIC;
    }

    /**
     * Playing different sounds based on hits to the bug.
     * 
     * @param myGUI the ROBOT Gui.
     */
    public void onHit(RobotGUI myGUI) {
        if (energyLevel == 0) {
            myGUI.playSound(LAST_WHIFF);
        } else {
            myGUI.playSound(STRONG_SMELL);
            System.out.println(name + " says: Smell some " + bugSmell);
        }
    }
}

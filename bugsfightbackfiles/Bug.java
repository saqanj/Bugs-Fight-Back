/**
 * Abstract class for representing a bug object.
 * 
 * @author Saqlain Anjum
 * @version 1.0.0
 */
public abstract class Bug {

    /**
     * A bug's name, and user's weapon descriptions.
     */
    private String name, weapon1, weapon2;

    /**
     * A bug's current energy level.
     */
    protected int energyLevel;

    /**
     * Amount of energy a bug looses when hit with weapon 1 and 2 as well as bugID.
     */
    private int power1, power2, id;

    /**
     * Number of bugs, whether or not the game is over and the bug image.
     */
    private static int numberOfBugs = 0;
    private static boolean gameOver = false;
    protected String robotImage = "./Images/Robotbug.png";

    /**
     * Constructor for the bug object.
     * 
     * @param nameInfo      Name information for the bug.
     * @param initialELevel Initial energy level.
     * @param w1Descript    Weapon1's description from the user.
     * @param w1Power       Weapon1's power level.
     * @param w2Descript    Weapon2's description from the user.
     * @param w2Power       Weapon2's power level.
     */
    public Bug(String nameInfo, int initialELevel, String w1Descript, int w1Power, String w2Descript, int w2Power) {
        name = nameInfo;
        energyLevel = initialELevel;
        weapon1 = w1Descript;
        power1 = w1Power;
        weapon2 = w2Descript;
        power2 = w2Power;
        numberOfBugs++;

    }

    /**
     * Accessor method for the robot image.
     * 
     * @return Robot image link.
     */
    public String getRobotImage() {
        return (robotImage);
    }

    /**
     * Abstract method which uses parameters from the GUI.
     *
     * @param myGUI the GUI.
     */
    public abstract void onHit(RobotGUI myGUI);

    /**
     * Hit method for attacking the bug.
     *
     * @param weaponNum The exact weapon being used.
     * @param myGUI     The Robot GUI object being attacked.
     */
    public void hit(int weaponNum, RobotGUI myGUI) {
        if (energyLevel > 0) {
            if (weaponNum == 1) {
                energyLevel = energyLevel - power1;
            } else {
                energyLevel = energyLevel - power2;
            }
            if (energyLevel < 1) {
                System.out.print(name);
                System.out.println(" just died");
                energyLevel = 0;
                numberOfBugs--;
                if (numberOfBugs == 0) {
                    System.out.println("Game Over");
                    gameOver = true;
                }
            }

            myGUI.hitBugGUI(id, energyLevel);
            onHit(myGUI);

        } else {
            if (!(gameOver)) {

                System.out.print(name);
                System.out.println(" is already dead.");
            }

        }

    }

    /**
     * Accessor method for the first weapon.
     * 
     * @return The weapon1 specified by the user.
     */
    public String getWeapon1() {
        return weapon1;
    }

    /**
     * Accessor method for getting the second weapon.
     * 
     * @return The weapon2 specified by the user.
     */
    public String getWeapon2() {
        return weapon2;
    }

    /**
     * Determines wether or not a bug is alive.
     * 
     * @return true if alive, false if dead.
     */
    public boolean isAlive() {
        if (energyLevel > 0) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * Accessor method for a bug's name.
     * 
     * @return The bug's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Update method for the bug's ID.
     * 
     * @param i the new ID.
     */
    public void setId(int i) {
        id = i;
    }

    /**
     * Accessor method for the bug's energy level.
     * 
     * @return the energy level for the bug.
     */
    public int getEnergyLevel() {
        return energyLevel;
    }
}
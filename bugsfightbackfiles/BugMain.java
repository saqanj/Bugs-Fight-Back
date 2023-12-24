import javax.sound.sampled.*;
import java.util.Scanner;

import javax.sound.sampled.*;
import java.util.Scanner;

/**
 * Program entry point for the GUI and user inputs.
 * 
 * @author Saqlain Anjum
 * @version 1.0.0
 */
public class BugMain
{
   public static  void main (String argv[]) {
      RobotGUI mygui = new RobotGUI();
      Scanner sc = new Scanner(System.in);
      Scanner sc2 = new Scanner(System.in).useDelimiter("/n");
      int MAX = 3;
      
      String weapon_1 = "Shoe";
      String  weapon_2 = "Book";
      int  weapon_1_power = 5;     
      int  weapon_2_power = 3;
      int  initial_energy_level = 10;   
      int typeOne = 1;
      int typeTwo = 2;
      
      /* insert additional declarations for bug array and code to get bug names,make bugs and call setId */
      String bugName;
      int arraySize = 3; 
      Bug[] bugArray = new Bug[arraySize];
      

      /**
       * For-loop used to fill bugArray with three different object instances of either SmellyBugs or YellyBugs depending on user inputs.
       */
      for(int i = 0; i < arraySize; i++) {
          int bugType;
          String odorDesc;
          
          System.out.print("What do you want to name bug number ");
          System.out.print(i+1);
          System.out.println("?");
          bugName = sc2.nextLine();
          System.out.println("Would you like a YellyBug(1) or a SmellyBug(2)? Enter the desired values!");
          bugType = sc.nextInt();
          if (bugType == typeOne){
              bugArray[i] = new YellyBug(bugName, initial_energy_level, weapon_1, weapon_1_power, weapon_2, weapon_2_power);
          }
          else if (bugType == typeTwo){
              System.out.println("What would you like the Smelly Bug's Odor to be described as? ");
              odorDesc = sc2.nextLine();
              bugArray[i] = new SmellyBug(bugName, initial_energy_level, weapon_1, weapon_1_power, weapon_2, weapon_2_power, odorDesc);
          }
          bugArray[i].setId(mygui.addBug(bugArray[i]));
          
        }
      mygui.showGUI(); 
    }
}

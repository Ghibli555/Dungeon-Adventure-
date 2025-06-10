import java.util.*;
import java.io.InputStreamReader;
import java.io.BufferedReader;

/**
 *
 */
public class Game {
  public static String banner = "\nGame V0.1\n";
  public static Player player = null;  // Only one player
  
  
   // constructor
  public Game(){
    Scene.setScenes();
    player = new Player(Scene.map[2][2]);  
    //gui = new Gui(map);// gui code is removed
  }
  
  public void run() {
    System.out.println(banner); 
    int status = 0;
    while(status == 0)
      status = player.move();
    switch(status){
      case 1: System.out.println("You have died because you were　going out of bounds"); 
              break;
      case 2: System.out.println("You have died because your health is zero"); 
              break;
      case 3: System.out.println("Congratulations! You conquered the game!"); 
              break;
      case 4: System.out.println("You have died because you lost the fight!"); 
              break;
      case 5: System.out.println("You have died because you are run away from the battle");
              break;
      case 6: System.out.println("You have died because you did not have the rquired item!");
              break;
      default: System.out.println("You died but we do not know why, status: " + status);
    }
    System.out.println("Thank you for playing!");
    System.exit(0);
  }
     
  
    /**
     * @param args the command line arguments
     */
  public static void main(String[] args) {
    Game game = new Game();
    game.run();
  }
   
  public static int inputInt(String prompt){
    int answer = -1;
    String strInt = input(prompt);
    try{
       answer = Integer.parseInt(strInt);
    }
    catch(Exception e ){
      System.out.println("Conversion to int problem: " + e.toString());        
    }
    return answer;
  }
  static InputStreamReader isr = new InputStreamReader(System.in);
  static BufferedReader reader = new BufferedReader(isr);

  public static String input(String prompt){
    int diagnosticCount = 0;
    String answer = "";
    try {
      //kb = new Scanner(System.in);
      System.out.print(prompt);
      while(answer.length() == 0){
        diagnosticCount++;  
        answer = reader.readLine();
        diagnosticCount++;  
      }
    }
    catch(Exception e){
      System.out.println("answer: " + answer + ", diagnosticCount: " + diagnosticCount);
      System.out.println("input problem: " + e.toString());
      System.exit(0);
    }
    //try{
    //  isr.close();
    //  reader.close();
    //} catch(Exception e){System.out.println("reader.close " + e);}
    return answer;
  }
}// end Game class

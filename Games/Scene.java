//import javax.swing.*;

/**
 * A scene in the game
 */
public class Scene {
  /**
   * A scene action - set by Scene constructor
   * 
   */ 
  Action action = null;
  /**
   * A scene title
   * 
   */ 
  String title = null;
  /**
   * A scene's item
   * 
   */ 
  Item item = null;
  /**
   * A scene's required item
   * 
   */ 
  Item need = null;
  /**
   * A scene's announcement
   * 
   */ 
  String announcement = null;
  /**
  * c and y coordinates of scene
  * 
  */ 
  public int x = 0,
             y = 0;
  
 /**
  * title and action of scene
  * 
  */ 
  public Scene (String title, int action){
    this.title = title;
    this.action = new Action(action, this);
  }
  public Scene (int row, int col, String title, String ann, 
               int action, Item item, Item need){
    this(title, action);
    this.announcement = ann;
    this.y = row;
    this.x = col;
    this.item = item;
    this.need = need;
    map[row][col] = this;
  }
 
  /**
  * set x and y coordinates of scene
  * 
  */ 
  public void setAnnouncement(String announcement){
    this.announcement = announcement;
  }
  /**
  * set x and y coordinates of scene
  * 
  */ 
  public void setCoordinates(int x, int y){
    this.x = x;
    this.y = y;
  }
  
  public int enterScene(Player player){
    int status = 0;
    boolean ok = true; // maybe set false if player lacks item
    if(null != announcement) 
      System.out.println("\n" + announcement);
    if(null != need)
        ok = player.checkForItem(need);
    if(ok)
      status = action.doAction(player);
    else
      status = 6;
    return status;
  }
  
  public void setItem(Item item){
    this.item = item;
  }
  
  public boolean checkForItem(Item item){
    return this.item == item;
  }
  
  public void setNeed(Item item){
    this.need = item;
  }
  
  public boolean checkForNeed(Item item){
    return this.need == item;
  }
  
  @Override
  public String toString(){
    return title;  
  }
  /**
  * Setting the scenes.
  * 
  * Each scene has a title and a announcement.
  * Only one item may be required to be in the player's possession
  * If you want the player to have more than one, use an arraylist.
  * The item at the scene may be something to pickup, goes with 
  * PICKUP_ACTION, or it may be the npc name with a FIGHT_ACTION
  *   
  * 
  * This is the longest method in the Game program.  
  * One way to shorten it is to put all the data into text file
  *   and use that to populate the items and scenes.
  * 
  * You would need to put the items into an array, and track what
  *   indexes belong to which scene
  * 
  */
  public static void setScenes(){
 // set up items.
    Item torch        = new Item("torch");
    Item buckler       = new Item("buckler");
    Item spear       = new Item("spear");
    Item analepticum       = new Item("analepticum");
    Item giant      = new Item("giant");

    String strEdge = "The Cliff's Edge is dangeroun but it had something you necessity!";
    new Scene (0, 0, "Cliff's Edge",strEdge, Action.PICKUP_ACTION, torch, null);
    
    String strCPath  = "The Cobblestone Path connects west, south and east";    
    new Scene (0, 1, "Cobblestone Path",strCPath, Action.NO_ACTION, null, null);

    String strDungeon = "The Dark Dungeion is dark and dangerous without a torch";
    new Scene (0, 2, "Dark Dungeon",strDungeon, Action.NO_ACTION, null, torch);

    String strDragon = "I am a big giant and I will bring you down!";
    new Scene (0, 3, "Dark Dungeon",strDragon, Action.FIGHT_ACTION, giant, buckler);

    String strCastle = "You have achieved your final destination, the Castle!";
    new Scene (0, 4, "Castle",strCastle, Action.WIN_ACTION, null, null);

    String strPlain = "The Grassy Plain is getting close to your end game!";
    new Scene (1, 1, "Grassy Plain",strPlain, Action.NO_ACTION, null, null);

    String strCross = "I am a wild river and you need a shell to cross me!";
    new Scene (1, 2, "River Crossing",strCross, Action.NO_ACTION, null, buckler);

    String strWoods = "The Enchanted Woods are at the centre of your universe";
    new Scene (2, 2, "Enchanted Woods",strWoods, Action.NO_ACTION, null, null);

    String strTrail = "I am the Forest Trail and I may be guarded by a analepticum";
      new Scene (2, 3, "Forest Trail",strTrail, Action.FIGHT_ACTION, analepticum, null);
   
    String strCottage = "You can take a break in this cottage";
    new Scene (3, 3, "Cottage",strCottage, Action.PICKUP_ACTION, buckler, null);

    String strShore = "You are at the edge of a very large inhospitable lake!";
    new Scene (4, 2, "Lake Shore",strShore, Action.PICKUP_ACTION, buckler, null);

    String strRPath = "I am a very rocky path.  Look for the lake!";
    new Scene (4, 3, "Rocky Path",strRPath, Action.NO_ACTION, null, null);

    String strRange = "I am an impassable range.  Turn back!";
    new Scene (4, 4, "Mountain Range",strRange, Action.DEADEND_ACTION, null, null);

    // fill in the missing scenes
    for(int y = 0; y < ROWS; y++)
      for(int x = 0; x < COLS; x++)
        if(map[y][x] == null){
           String str = "You have been damage but still alive!";
           new Scene(y, x, "Off Limits", str, Action.DEADEND_ACTION, null, null);
        }
    // diagnostic to prove all scenes where they belong
    //for(int y = 0; y < ROWS; y++)   // diagnostic
      //for(int x = 0; x < COLS; x++)
        //System.out.println("x=" + x + ", y=" + y + ", title: " + map[y][x].title);
  }// end setScenes()

  
  /*
   * Set game dimensions for global reference
  */  
  public static final int ROWS = 5,
                          COLS = 5;
  /**
   *
  */
  public static Scene[][] map = new Scene[ROWS][COLS];

}// end Scene class

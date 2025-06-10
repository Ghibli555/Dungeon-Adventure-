

/**
 *
 */
public class Fight {
 /**
  * player pointer provides access to player's health
  */
  Player player = null;
 /**
  * Non Player Character
  */
  String npc = null;
 /**
  *
  */
  public Fight(Player player, String npc){
    this.player = player;
    this.npc = npc;
  } 
  
  public int run() {
    int status = 0;
    String answer = null;
    char c = 'n';
    if(player.currentScene.item == null)
      player.health += 10;
    else {
      answer = Game.input("Do you want to fight the " + npc + "(n/y)? ");
      c = Character.toLowerCase(answer.charAt(0));
      if(c == 'n')
        status = 5;// die as coward
      else
        status = doFight();
    }
    return status;
  }


  public int doFight() {
    int status = 0;
    int npcHealth = 80; // give player some advantage
    double factor = 30.; // max random number- 1
    while(player.health > 0 && npcHealth > 0){
      System.out.println("Health bar: " + player.health + 
                   ", " + npc + " health: " + npcHealth);
      npcHealth -= 1 + (int)(factor * (Math.random()));
      player.health -= 1 + (int)(factor * (Math.random()));
    }
    System.out.println("Health bar: " + player.health + 
                  ", " + npc + " health: " + npcHealth);
    if(npcHealth < 0){
      System.out.println("Congratulations, you defeated the " + npc);
      player.currentScene.item = null; // delete npc
    }else{
      status = 4;
    }
    return status;
  }
}

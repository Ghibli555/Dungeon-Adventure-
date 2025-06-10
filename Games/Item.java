/**
 *
 */
public class Item {
 /**
 * item title
 * 
 */ 
  public String title = null;
/**
  * c and y coordinates of scene
  * 
  */ 
  public Item (String title){
    this.title = title;
  }
  
  @Override
  public String toString(){
    return title;  
  }

   
}


/** 
 * Clears the Tool menu and runs the gc so that contributions can be updated without classes still being in use.
 */
public void clearToolMenu(){
  toolsMenu.removeAll();
  System.gc();
}

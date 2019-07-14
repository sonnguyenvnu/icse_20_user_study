/** 
 * Hide the menu bar, make the Frame undecorated, set it to screenRect. 
 */
private void setFullFrame(){
  PApplet.hideMenuBar();
  frame.removeNotify();
  frame.setUndecorated(true);
  frame.addNotify();
  frame.setBounds(screenRect);
}

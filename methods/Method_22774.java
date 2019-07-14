/** 
 * If a key is being grabbed, this method should be called with the appropriate key event. It executes the grab action with the typed character as the parameter.
 */
protected void handleGrabAction(KeyEvent evt){
  ActionListener _grabAction=grabAction;
  grabAction=null;
  executeAction(_grabAction,evt.getSource(),String.valueOf(evt.getKeyChar()));
}

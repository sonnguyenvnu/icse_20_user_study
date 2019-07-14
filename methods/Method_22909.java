/** 
 * Called by ErrorTable when a row is selected. Action taken is specific to each Mode, based on the object passed in.
 */
public void errorTableClick(Object item){
  highlight((Problem)item);
}

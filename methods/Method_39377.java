/** 
 * Adds self instance to the container so internal beans may fetch container for further usage. No wiring is used and no init methods are invoked.
 */
public void addSelf(final String name){
  addBean(name,this,WiringMode.NONE);
}

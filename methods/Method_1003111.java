/** 
 * Release all resources of this object.
 */
public void free(){
  debugCodeCall("free");
  state=State.CLOSED;
  value=null;
}

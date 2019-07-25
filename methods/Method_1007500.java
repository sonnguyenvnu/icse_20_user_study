/** 
 * end() is the last up(). returns true if the fiber is not pausing.
 */
public final boolean end(){
  assert iStack == 0 : "Reset: Expected iStack == 0, not " + iStack + "\n" + this;
  boolean isDone=!isPausing;
  if (isDone) {
    stateStack[0]=null;
  }
  isPausing=false;
  iStack=-1;
  return isDone;
}

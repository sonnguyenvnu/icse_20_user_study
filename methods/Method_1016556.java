/** 
 * Returns true iff the formatter misbehaved in any way (did not converge after a single iteration).
 */
public boolean misbehaved(){
  boolean isWellBehaved=type == Type.CONVERGE && steps.size() <= 1;
  return !isWellBehaved;
}

/** 
 * Convenience for checking what's on-screen. [fry]
 */
public final int getLastLine(){
  return getFirstLine() + getVisibleLines();
}

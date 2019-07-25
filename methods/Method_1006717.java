/** 
 * Switches on verbose logging to System.err.
 * @return this (for method chaining).
 */
public ClassGraph verbose(){
  if (topLevelLog == null) {
    topLevelLog=new LogNode();
  }
  return this;
}

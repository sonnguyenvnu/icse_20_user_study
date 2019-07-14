/** 
 * Sets whether the user has explicitly disabled incremental analysis or not. If so, incremental analysis is not used, and all suggestions to use it are disabled. The analysis cached location is ignored, even if it's specified.
 * @param noCache Whether to ignore incremental analysis or not
 */
public void setIgnoreIncrementalAnalysis(boolean noCache){
  this.ignoreIncrementalAnalysis=noCache;
}

/** 
 * Finish pool initialization.
 */
protected void initialize(){
  mMemoryTrimmableRegistry.registerMemoryTrimmable(this);
  mPoolStatsTracker.setBasePool(this);
}

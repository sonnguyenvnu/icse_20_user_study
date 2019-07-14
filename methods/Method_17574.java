/** 
 * Starts the next sample period. 
 */
protected void resetSample(double hitRate){
  previousHitRate=hitRate;
  missesInSample=0;
  hitsInSample=0;
  hitsInWindow=0;
  hitsInMain=0;
}

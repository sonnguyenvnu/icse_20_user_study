/** 
 * Called on end of seeking.
 * @param currentGranule The granule at the current input position.
 */
protected void onSeekEnd(long currentGranule){
  this.currentGranule=currentGranule;
}

/** 
 * Resets the state of the  {@link StreamReader}.
 * @param headerData Resets parsed header data too.
 */
protected void reset(boolean headerData){
  if (headerData) {
    setupData=new SetupData();
    payloadStartPosition=0;
    state=STATE_READ_HEADERS;
  }
 else {
    state=STATE_SKIP_HEADERS;
  }
  targetGranule=-1;
  currentGranule=0;
}

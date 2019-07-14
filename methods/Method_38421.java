/** 
 * Starts defining region by setting the start index and reset region length to zero.
 */
public void startRegion(final int start,final int tagLen,final int deepLevel){
  this.regionStart=start + tagLen;
  this.regionLength=0;
  this.regionTagStart=start;
  this.deepLevel=deepLevel;
}

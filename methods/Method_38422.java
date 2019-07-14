/** 
 * Ends region definition by setting the region length.
 */
public void endRegion(final int regionEnd,final int tagLen){
  this.regionLength=regionEnd - regionStart;
  this.regionTagEnd=regionEnd + tagLen;
}

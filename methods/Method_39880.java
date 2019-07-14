/** 
 * Sets the compressionThreshold number and create buffer for this size.
 */
protected void setBuffer(final int threshold){
  compressionThreshold=threshold;
  buffer=new byte[compressionThreshold];
}

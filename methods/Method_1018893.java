/** 
 * Copy frequency band values from one array to another.
 * @param from copy from this array
 * @param to copy to this array
 */
private void copy(float[] from,float[] to){
  System.arraycopy(from,0,to,0,bandCount);
}

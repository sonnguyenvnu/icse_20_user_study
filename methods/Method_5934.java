/** 
 * Updates the instance to wrap  {@code data}, and resets the position to zero.
 * @param data The array to wrap.
 * @param limit The limit to set.
 */
public void reset(byte[] data,int limit){
  this.data=data;
  this.limit=limit;
  position=0;
}

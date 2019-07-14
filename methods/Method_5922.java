/** 
 * Updates the instance to wrap  {@code data}, and resets the position to zero.
 * @param data The array to wrap.
 * @param limit The limit in bytes.
 */
public void reset(byte[] data,int limit){
  this.data=data;
  byteOffset=0;
  bitOffset=0;
  byteLimit=limit;
}

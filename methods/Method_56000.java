/** 
 * Returns the value at the specified index of the  {@code addressMode} field. 
 */
@NativeType("CUaddress_mode") public int addressMode(int index){
  return naddressMode(address(),index);
}

/** 
 * Returns the value of the  {@code time} field. 
 */
@NativeType("DWORD") public int time(){
  return ntime(address());
}

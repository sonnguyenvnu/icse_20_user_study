/** 
 * Unsafe version of:  {@link #bgfx_copy copy}
 * @param _size size of data to be copied
 */
public static long nbgfx_copy(long _data,int _size){
  long __functionAddress=Functions.copy;
  return invokePP(_data,_size,__functionAddress);
}

/** 
 * Unsafe version of:  {@link #bgfx_make_ref make_ref}
 * @param _size the number of bytes to reference
 */
public static long nbgfx_make_ref(long _data,int _size){
  long __functionAddress=Functions.make_ref;
  return invokePP(_data,_size,__functionAddress);
}

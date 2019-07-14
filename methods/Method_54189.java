/** 
 * Unsafe version of:  {@link #bgfx_make_ref_release make_ref_release}
 * @param _size the number of bytes to reference
 */
public static long nbgfx_make_ref_release(long _data,int _size,long _releaseFn,long _userData){
  long __functionAddress=Functions.make_ref_release;
  return invokePPPP(_data,_size,_releaseFn,_userData,__functionAddress);
}

/** 
 * Unsafe version of:  {@link #bgfx_get_uniform_info get_uniform_info} 
 */
public static void nbgfx_get_uniform_info(short _handle,long _info){
  long __functionAddress=Functions.get_uniform_info;
  invokePV(_handle,_info,__functionAddress);
}

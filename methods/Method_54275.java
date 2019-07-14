/** 
 * Unsafe version of:  {@link #bgfx_set_texture set_texture} 
 */
public static void nbgfx_set_texture(byte _stage,short _sampler,short _handle,int _flags){
  long __functionAddress=Functions.set_texture;
  invokeV(_stage,_sampler,_handle,_flags,__functionAddress);
}

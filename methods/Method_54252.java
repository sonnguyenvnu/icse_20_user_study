/** 
 * Unsafe version of:  {@link #bgfx_encoder_set_texture encoder_set_texture} 
 */
public static void nbgfx_encoder_set_texture(long _encoder,byte _stage,short _sampler,short _handle,int _flags){
  long __functionAddress=Functions.encoder_set_texture;
  if (CHECKS) {
    check(_encoder);
  }
  invokePV(_encoder,_stage,_sampler,_handle,_flags,__functionAddress);
}

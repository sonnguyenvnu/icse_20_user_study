/** 
 * Unsafe version of:  {@link #bgfx_encoder_set_scissor_cached encoder_set_scissor_cached} 
 */
public static void nbgfx_encoder_set_scissor_cached(long _encoder,short _cache){
  long __functionAddress=Functions.encoder_set_scissor_cached;
  if (CHECKS) {
    check(_encoder);
  }
  invokePV(_encoder,_cache,__functionAddress);
}

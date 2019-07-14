/** 
 * Unsafe version of:  {@link #bgfx_encoder_set_transform_cached encoder_set_transform_cached} 
 */
public static void nbgfx_encoder_set_transform_cached(long _encoder,int _cache,short _num){
  long __functionAddress=Functions.encoder_set_transform_cached;
  if (CHECKS) {
    check(_encoder);
  }
  invokePV(_encoder,_cache,_num,__functionAddress);
}

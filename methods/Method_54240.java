/** 
 * Unsafe version of:  {@link #bgfx_encoder_set_marker encoder_set_marker} 
 */
public static void nbgfx_encoder_set_marker(long _encoder,long _marker){
  long __functionAddress=Functions.encoder_set_marker;
  if (CHECKS) {
    check(_encoder);
  }
  invokePPV(_encoder,_marker,__functionAddress);
}

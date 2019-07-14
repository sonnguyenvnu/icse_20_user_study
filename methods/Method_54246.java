/** 
 * Unsafe version of:  {@link #bgfx_encoder_set_uniform encoder_set_uniform} 
 */
public static void nbgfx_encoder_set_uniform(long _encoder,short _handle,long _value,short _num){
  long __functionAddress=Functions.encoder_set_uniform;
  if (CHECKS) {
    check(_encoder);
  }
  invokePPV(_encoder,_handle,_value,_num,__functionAddress);
}

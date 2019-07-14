/** 
 * Unsafe version of:  {@link #bgfx_encoder_set_compute_index_buffer encoder_set_compute_index_buffer} 
 */
public static void nbgfx_encoder_set_compute_index_buffer(long _encoder,byte _stage,short _handle,int _access){
  long __functionAddress=Functions.encoder_set_compute_index_buffer;
  if (CHECKS) {
    check(_encoder);
  }
  invokePV(_encoder,_stage,_handle,_access,__functionAddress);
}

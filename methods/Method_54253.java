/** 
 * Unsafe version of:  {@link #bgfx_encoder_submit encoder_submit} 
 */
public static void nbgfx_encoder_submit(long _encoder,short _id,short _handle,int _depth,boolean _preserveState){
  long __functionAddress=Functions.encoder_submit;
  if (CHECKS) {
    check(_encoder);
  }
  invokePV(_encoder,_id,_handle,_depth,_preserveState,__functionAddress);
}

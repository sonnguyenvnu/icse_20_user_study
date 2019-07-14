/** 
 * Unsafe version of:  {@link #bgfx_encoder_dispatch encoder_dispatch} 
 */
public static void nbgfx_encoder_dispatch(long _encoder,short _id,short _handle,int _numX,int _numY,int _numZ){
  long __functionAddress=Functions.encoder_dispatch;
  if (CHECKS) {
    check(_encoder);
  }
  invokePV(_encoder,_id,_handle,_numX,_numY,_numZ,__functionAddress);
}

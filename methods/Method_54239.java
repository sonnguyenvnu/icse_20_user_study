/** 
 * End submitting draw calls from thread.
 * @param _encoder the encoder
 */
public static void bgfx_encoder_end(@NativeType("struct bgfx_encoder_s *") long _encoder){
  long __functionAddress=Functions.encoder_end;
  if (CHECKS) {
    check(_encoder);
  }
  invokePV(_encoder,__functionAddress);
}

/** 
 * Unsafe version of:  {@link #bgfx_encoder_set_transient_index_buffer encoder_set_transient_index_buffer} 
 */
public static void nbgfx_encoder_set_transient_index_buffer(long _encoder,long _tib,int _firstIndex,int _numIndices){
  long __functionAddress=Functions.encoder_set_transient_index_buffer;
  if (CHECKS) {
    check(_encoder);
    BGFXTransientIndexBuffer.validate(_tib);
  }
  invokePPV(_encoder,_tib,_firstIndex,_numIndices,__functionAddress);
}

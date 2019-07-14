/** 
 * Unsafe version of:  {@link #bgfx_set_transient_index_buffer set_transient_index_buffer} 
 */
public static void nbgfx_set_transient_index_buffer(long _tib,int _firstIndex,int _numIndices){
  long __functionAddress=Functions.set_transient_index_buffer;
  if (CHECKS) {
    BGFXTransientIndexBuffer.validate(_tib);
  }
  invokePV(_tib,_firstIndex,_numIndices,__functionAddress);
}

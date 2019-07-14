/** 
 * Unsafe version of:  {@link #bgfx_encoder_set_dynamic_vertex_buffer encoder_set_dynamic_vertex_buffer} 
 */
public static void nbgfx_encoder_set_dynamic_vertex_buffer(long _encoder,byte _stream,short _handle,int _startVertex,int _numVertices,short _declHandle){
  long __functionAddress=Functions.encoder_set_dynamic_vertex_buffer;
  if (CHECKS) {
    check(_encoder);
  }
  invokePV(_encoder,_stream,_handle,_startVertex,_numVertices,_declHandle,__functionAddress);
}

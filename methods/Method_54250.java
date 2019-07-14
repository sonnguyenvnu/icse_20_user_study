/** 
 * Unsafe version of:  {@link #bgfx_encoder_set_transient_vertex_buffer encoder_set_transient_vertex_buffer} 
 */
public static void nbgfx_encoder_set_transient_vertex_buffer(long _encoder,byte _stream,long _tvb,int _startVertex,int _numVertices,short _declHandle){
  long __functionAddress=Functions.encoder_set_transient_vertex_buffer;
  if (CHECKS) {
    check(_encoder);
    BGFXTransientVertexBuffer.validate(_tvb);
  }
  invokePPV(_encoder,_stream,_tvb,_startVertex,_numVertices,_declHandle,__functionAddress);
}

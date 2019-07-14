/** 
 * Unsafe version of:  {@link #bgfx_set_transient_vertex_buffer set_transient_vertex_buffer} 
 */
public static void nbgfx_set_transient_vertex_buffer(byte _stream,long _tvb,int _startVertex,int _numVertices){
  long __functionAddress=Functions.set_transient_vertex_buffer;
  if (CHECKS) {
    BGFXTransientVertexBuffer.validate(_tvb);
  }
  invokePV(_stream,_tvb,_startVertex,_numVertices,__functionAddress);
}

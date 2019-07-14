/** 
 * Unsafe version of:  {@link #bgfx_set_dynamic_vertex_buffer set_dynamic_vertex_buffer} 
 */
public static void nbgfx_set_dynamic_vertex_buffer(byte _stream,short _handle,int _startVertex,int _numVertices){
  long __functionAddress=Functions.set_dynamic_vertex_buffer;
  invokeV(_stream,_handle,_startVertex,_numVertices,__functionAddress);
}

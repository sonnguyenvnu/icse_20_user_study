/** 
 * Unsafe version of:  {@link #bgfx_set_vertex_buffer set_vertex_buffer} 
 */
public static void nbgfx_set_vertex_buffer(byte _stream,short _handle,int _startVertex,int _numVertices){
  long __functionAddress=Functions.set_vertex_buffer;
  invokeV(_stream,_handle,_startVertex,_numVertices,__functionAddress);
}

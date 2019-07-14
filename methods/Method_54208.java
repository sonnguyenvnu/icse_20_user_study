/** 
 * Unsafe version of:  {@link #bgfx_get_avail_transient_vertex_buffer get_avail_transient_vertex_buffer} 
 */
public static int nbgfx_get_avail_transient_vertex_buffer(int _num,long _decl){
  long __functionAddress=Functions.get_avail_transient_vertex_buffer;
  return invokePI(_num,_decl,__functionAddress);
}

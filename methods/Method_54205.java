/** 
 * Unsafe version of:  {@link #bgfx_create_dynamic_vertex_buffer create_dynamic_vertex_buffer} 
 */
public static short nbgfx_create_dynamic_vertex_buffer(int _num,long _decl,short _flags){
  long __functionAddress=Functions.create_dynamic_vertex_buffer;
  return invokePS(_num,_decl,_flags,__functionAddress);
}

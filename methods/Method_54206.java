/** 
 * Unsafe version of:  {@link #bgfx_create_dynamic_vertex_buffer_mem create_dynamic_vertex_buffer_mem} 
 */
public static short nbgfx_create_dynamic_vertex_buffer_mem(long _mem,long _decl,short _flags){
  long __functionAddress=Functions.create_dynamic_vertex_buffer_mem;
  if (CHECKS) {
    BGFXMemory.validate(_mem);
  }
  return invokePPS(_mem,_decl,_flags,__functionAddress);
}

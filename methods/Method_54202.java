/** 
 * Unsafe version of:  {@link #bgfx_create_vertex_buffer create_vertex_buffer} 
 */
public static short nbgfx_create_vertex_buffer(long _mem,long _decl,short _flags){
  long __functionAddress=Functions.create_vertex_buffer;
  if (CHECKS) {
    BGFXMemory.validate(_mem);
  }
  return invokePPS(_mem,_decl,_flags,__functionAddress);
}

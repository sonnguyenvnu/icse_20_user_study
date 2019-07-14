/** 
 * Unsafe version of:  {@link #bgfx_create_index_buffer create_index_buffer} 
 */
public static short nbgfx_create_index_buffer(long _mem,short _flags){
  long __functionAddress=Functions.create_index_buffer;
  if (CHECKS) {
    BGFXMemory.validate(_mem);
  }
  return invokePS(_mem,_flags,__functionAddress);
}

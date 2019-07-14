/** 
 * Unsafe version of:  {@link #bgfx_create_dynamic_index_buffer create_dynamic_index_buffer} 
 */
public static short nbgfx_create_dynamic_index_buffer(int _num,short _flags){
  long __functionAddress=Functions.create_dynamic_index_buffer;
  return invokeS(_num,_flags,__functionAddress);
}

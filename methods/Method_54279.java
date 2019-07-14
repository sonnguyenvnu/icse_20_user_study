/** 
 * Unsafe version of:  {@link #bgfx_set_compute_dynamic_index_buffer set_compute_dynamic_index_buffer} 
 */
public static void nbgfx_set_compute_dynamic_index_buffer(byte _stage,short _handle,int _access){
  long __functionAddress=Functions.set_compute_dynamic_index_buffer;
  invokeV(_stage,_handle,_access,__functionAddress);
}

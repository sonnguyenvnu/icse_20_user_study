/** 
 * Returns number of requested or maximum available indices.
 * @param _num number of required indices
 */
@NativeType("uint32_t") public static int bgfx_get_avail_transient_index_buffer(@NativeType("uint32_t") int _num){
  long __functionAddress=Functions.get_avail_transient_index_buffer;
  return invokeI(_num,__functionAddress);
}

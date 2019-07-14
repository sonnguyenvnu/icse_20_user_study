/** 
 * Set instance data buffer for draw primitive.
 * @param _handle dynamic vertex buffer
 * @param _start  first instance data
 * @param _num    number of data instances
 */
public static void bgfx_set_instance_data_from_dynamic_vertex_buffer(@NativeType("bgfx_dynamic_vertex_buffer_handle_t") short _handle,@NativeType("uint32_t") int _start,@NativeType("uint32_t") int _num){
  long __functionAddress=Functions.set_instance_data_from_dynamic_vertex_buffer;
  invokeV(_handle,_start,_num,__functionAddress);
}

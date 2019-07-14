/** 
 * Unsafe version of:  {@link #bgfx_get_avail_instance_data_buffer get_avail_instance_data_buffer} 
 */
public static int nbgfx_get_avail_instance_data_buffer(int _num,short _stride){
  long __functionAddress=Functions.get_avail_instance_data_buffer;
  return invokeI(_num,_stride,__functionAddress);
}

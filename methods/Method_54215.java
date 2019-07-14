/** 
 * Unsafe version of:  {@link #bgfx_alloc_instance_data_buffer alloc_instance_data_buffer} 
 */
public static void nbgfx_alloc_instance_data_buffer(long _idb,int _num,short _stride){
  long __functionAddress=Functions.alloc_instance_data_buffer;
  invokePV(_idb,_num,_stride,__functionAddress);
}

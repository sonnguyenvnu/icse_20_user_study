/** 
 * Unsafe version of:  {@link #bgfx_set_instance_data_buffer set_instance_data_buffer} 
 */
public static void nbgfx_set_instance_data_buffer(long _idb,int _start,int _num){
  long __functionAddress=Functions.set_instance_data_buffer;
  if (CHECKS) {
    BGFXInstanceDataBuffer.validate(_idb);
  }
  invokePV(_idb,_start,_num,__functionAddress);
}

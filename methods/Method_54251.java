/** 
 * Unsafe version of:  {@link #bgfx_encoder_set_instance_data_buffer encoder_set_instance_data_buffer} 
 */
public static void nbgfx_encoder_set_instance_data_buffer(long _encoder,long _idb,int _start,int _num){
  long __functionAddress=Functions.encoder_set_instance_data_buffer;
  if (CHECKS) {
    check(_encoder);
    BGFXInstanceDataBuffer.validate(_idb);
  }
  invokePPV(_encoder,_idb,_start,_num,__functionAddress);
}

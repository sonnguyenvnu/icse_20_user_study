/** 
 * Unsafe version of:  {@link #bgfx_encoder_dispatch_indirect encoder_dispatch_indirect} 
 */
public static void nbgfx_encoder_dispatch_indirect(long _encoder,short _id,short _handle,short _indirectHandle,short _start,short _num){
  long __functionAddress=Functions.encoder_dispatch_indirect;
  if (CHECKS) {
    check(_encoder);
  }
  invokePV(_encoder,_id,_handle,_indirectHandle,_start,_num,__functionAddress);
}

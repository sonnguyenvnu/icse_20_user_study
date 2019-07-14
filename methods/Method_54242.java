/** 
 * Unsafe version of:  {@link #bgfx_encoder_set_transform encoder_set_transform}
 * @param _num number of matrices in array
 */
public static int nbgfx_encoder_set_transform(long _encoder,long _mtx,short _num){
  long __functionAddress=Functions.encoder_set_transform;
  if (CHECKS) {
    check(_encoder);
  }
  return invokePPI(_encoder,_mtx,_num,__functionAddress);
}

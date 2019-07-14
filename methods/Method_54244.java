/** 
 * Unsafe version of:  {@link #bgfx_encoder_alloc_transform encoder_alloc_transform} 
 */
public static int nbgfx_encoder_alloc_transform(long _encoder,long _transform,short _num){
  long __functionAddress=Functions.encoder_alloc_transform;
  if (CHECKS) {
    check(_encoder);
  }
  return invokePPI(_encoder,_transform,_num,__functionAddress);
}

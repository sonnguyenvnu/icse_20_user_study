/** 
 * Unsafe version of:  {@link #bgfx_set_transform set_transform}
 * @param _num number of matrices in array
 */
public static int nbgfx_set_transform(long _mtx,short _num){
  long __functionAddress=Functions.set_transform;
  return invokePI(_mtx,_num,__functionAddress);
}

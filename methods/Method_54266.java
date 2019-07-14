/** 
 * Unsafe version of:  {@link #bgfx_alloc_transform alloc_transform} 
 */
public static int nbgfx_alloc_transform(long _transform,short _num){
  long __functionAddress=Functions.alloc_transform;
  return invokePI(_transform,_num,__functionAddress);
}

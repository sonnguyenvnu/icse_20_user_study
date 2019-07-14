/** 
 * Unsafe version of  {@link #resType}. 
 */
public static int nresType(long struct){
  return UNSAFE.getInt(null,struct + CUDA_RESOURCE_DESC.RESTYPE);
}

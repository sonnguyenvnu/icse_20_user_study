/** 
 * Unsafe version of  {@link #dstMemoryType}. 
 */
public static int ndstMemoryType(long struct){
  return UNSAFE.getInt(null,struct + CUDA_MEMCPY2D.DSTMEMORYTYPE);
}

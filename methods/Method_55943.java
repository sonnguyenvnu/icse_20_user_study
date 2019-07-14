/** 
 * Unsafe version of  {@link #Height(long) Height}. 
 */
public static void nHeight(long struct,long value){
  memPutAddress(struct + CUDA_MEMCPY3D.HEIGHT,value);
}

/** 
 * Unsafe version of  {@link #reserved(int) reserved}. 
 */
public static int nreserved(long struct,int index){
  return UNSAFE.getInt(null,struct + CUDA_EXTERNAL_SEMAPHORE_WAIT_PARAMS.RESERVED + check(index,16) * 4);
}

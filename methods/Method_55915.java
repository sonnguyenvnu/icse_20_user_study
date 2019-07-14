/** 
 * Unsafe version of  {@link #reserved(int,int) reserved}. 
 */
public static void nreserved(long struct,int index,int value){
  UNSAFE.putInt(null,struct + CUDA_EXTERNAL_SEMAPHORE_SIGNAL_PARAMS.RESERVED + check(index,16) * 4,value);
}

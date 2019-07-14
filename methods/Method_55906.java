/** 
 * Unsafe version of  {@link #flags(int) flags}. 
 */
public static void nflags(long struct,int value){
  UNSAFE.putInt(null,struct + CUDA_EXTERNAL_MEMORY_HANDLE_DESC.FLAGS,value);
}

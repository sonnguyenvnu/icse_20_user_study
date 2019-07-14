/** 
 * Unsafe version of  {@link #Format(int) Format}. 
 */
public static void nFormat(long struct,int value){
  UNSAFE.putInt(null,struct + CUDA_ARRAY_DESCRIPTOR.FORMAT,value);
}

/** 
 * Unsafe version of  {@link #resType(int) resType}. 
 */
public static void nresType(long struct,int value){
  UNSAFE.putInt(null,struct + CUDA_RESOURCE_DESC.RESTYPE,value);
}

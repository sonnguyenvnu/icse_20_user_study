/** 
 * Unsafe version of  {@link #dstMemoryType(int) dstMemoryType}. 
 */
public static void ndstMemoryType(long struct,int value){
  UNSAFE.putInt(null,struct + CUDA_MEMCPY3D.DSTMEMORYTYPE,value);
}

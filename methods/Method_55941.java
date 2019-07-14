/** 
 * Unsafe version of  {@link #srcMemoryType(int) srcMemoryType}. 
 */
public static void nsrcMemoryType(long struct,int value){
  UNSAFE.putInt(null,struct + CUDA_MEMCPY3D.SRCMEMORYTYPE,value);
}

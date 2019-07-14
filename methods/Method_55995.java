/** 
 * Unsafe version of  {@link #firstMipmapLevel(int) firstMipmapLevel}. 
 */
public static void nfirstMipmapLevel(long struct,int value){
  UNSAFE.putInt(null,struct + CUDA_RESOURCE_VIEW_DESC.FIRSTMIPMAPLEVEL,value);
}

/** 
 * Unsafe version of  {@link #lastMipmapLevel(int) lastMipmapLevel}. 
 */
public static void nlastMipmapLevel(long struct,int value){
  UNSAFE.putInt(null,struct + CUDA_RESOURCE_VIEW_DESC.LASTMIPMAPLEVEL,value);
}

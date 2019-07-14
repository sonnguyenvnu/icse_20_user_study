/** 
 * Unsafe version of  {@link #firstLayer(int) firstLayer}. 
 */
public static void nfirstLayer(long struct,int value){
  UNSAFE.putInt(null,struct + CUDA_RESOURCE_VIEW_DESC.FIRSTLAYER,value);
}

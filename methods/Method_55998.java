/** 
 * Unsafe version of  {@link #lastLayer(int) lastLayer}. 
 */
public static void nlastLayer(long struct,int value){
  UNSAFE.putInt(null,struct + CUDA_RESOURCE_VIEW_DESC.LASTLAYER,value);
}

/** 
 * Unsafe version of  {@link #mNumMeshChannels}. 
 */
public static int nmNumMeshChannels(long struct){
  return UNSAFE.getInt(null,struct + AIAnimation.MNUMMESHCHANNELS);
}

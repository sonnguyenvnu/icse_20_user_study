/** 
 * Unsafe version of  {@link #mNumMorphMeshChannels}. 
 */
public static int nmNumMorphMeshChannels(long struct){
  return UNSAFE.getInt(null,struct + AIAnimation.MNUMMORPHMESHCHANNELS);
}

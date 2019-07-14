/** 
 * Unsafe version of  {@link #mNumChannels}. 
 */
public static int nmNumChannels(long struct){
  return UNSAFE.getInt(null,struct + AIAnimation.MNUMCHANNELS);
}

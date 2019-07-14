/** 
 * Unsafe version of  {@link #mFilename}. 
 */
public static AIString nmFilename(long struct){
  return AIString.create(struct + AITexture.MFILENAME);
}

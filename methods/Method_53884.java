/** 
 * Unsafe version of  {@link #mKey}. 
 */
public static AIString nmKey(long struct){
  return AIString.create(struct + AIMaterialProperty.MKEY);
}

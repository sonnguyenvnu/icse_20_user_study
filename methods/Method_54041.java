/** 
 * Unsafe version of  {@link #mKeys}. 
 */
public static AIString.Buffer nmKeys(long struct){
  return AIString.create(memGetAddress(struct + AIMetaData.MKEYS),nmNumProperties(struct));
}

/** 
 * Unsafe version of  {@link #mKeys}. 
 */
public static AIMeshKey.Buffer nmKeys(long struct){
  return AIMeshKey.create(memGetAddress(struct + AIMeshAnim.MKEYS),nmNumKeys(struct));
}

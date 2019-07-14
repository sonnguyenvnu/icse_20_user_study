/** 
 * Unsafe version of  {@link #mValue}. 
 */
public static AIQuaternion nmValue(long struct){
  return AIQuaternion.create(struct + AIQuatKey.MVALUE);
}

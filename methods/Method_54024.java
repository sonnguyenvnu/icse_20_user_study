/** 
 * Unsafe version of  {@link #mValue}. 
 */
public static int nmValue(long struct){
  return UNSAFE.getInt(null,struct + AIMeshKey.MVALUE);
}

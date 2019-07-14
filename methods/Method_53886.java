/** 
 * Unsafe version of  {@link #mIndex}. 
 */
public static int nmIndex(long struct){
  return UNSAFE.getInt(null,struct + AIMaterialProperty.MINDEX);
}

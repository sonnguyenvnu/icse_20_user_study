/** 
 * Unsafe version of  {@link #mDataLength}. 
 */
public static int nmDataLength(long struct){
  return UNSAFE.getInt(null,struct + AIMaterialProperty.MDATALENGTH);
}

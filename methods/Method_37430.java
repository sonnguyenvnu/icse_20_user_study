/** 
 * Returns string hash of input byte array.
 */
public default String digestString(final byte[] byteArray){
  return StringUtil.toHexString(digest(byteArray));
}

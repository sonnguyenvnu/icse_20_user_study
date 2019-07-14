/** 
 * Unsafe version of  {@link #signatureString}. 
 */
public static String nsignatureString(long struct){
  return memUTF8(memGetAddress(struct + JNINativeMethod.SIGNATURE));
}

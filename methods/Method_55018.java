/** 
 * Unsafe version of  {@link #signature}. 
 */
public static ByteBuffer nsignature(long struct){
  return memByteBufferNT1(memGetAddress(struct + JNINativeMethod.SIGNATURE));
}

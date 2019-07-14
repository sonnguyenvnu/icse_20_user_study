/** 
 * Unsafe version of  {@link #lpSecurityDescriptor}. 
 */
public static long nlpSecurityDescriptor(long struct){
  return memGetAddress(struct + SECURITY_ATTRIBUTES.LPSECURITYDESCRIPTOR);
}

/** 
 * Returns the value of the  {@code lpSecurityDescriptor} field. 
 */
@NativeType("LPVOID") public long lpSecurityDescriptor(){
  return nlpSecurityDescriptor(address());
}

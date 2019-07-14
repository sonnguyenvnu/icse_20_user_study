/** 
 * Decodes the null-terminated string stored in the  {@code m_bodyName} field. 
 */
@NativeType("char[1024]") public String m_bodyNameString(){
  return nm_bodyNameString(address());
}

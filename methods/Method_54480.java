/** 
 * Unsafe version of  {@link #m_linkNameString}. 
 */
public static String nm_linkNameString(long struct){
  return memASCII(struct + B3JointInfo.M_LINKNAME);
}

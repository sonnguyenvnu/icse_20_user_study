/** 
 * Unsafe version of  {@link #m_flags}. 
 */
public static int nm_flags(long struct){
  return UNSAFE.getInt(null,struct + B3JointInfo.M_FLAGS);
}

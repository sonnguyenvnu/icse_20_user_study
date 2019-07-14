/** 
 * Unsafe version of  {@link #m_uIndex}. 
 */
public static int nm_uIndex(long struct){
  return UNSAFE.getInt(null,struct + B3JointInfo.M_UINDEX);
}

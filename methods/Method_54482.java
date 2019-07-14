/** 
 * Unsafe version of  {@link #m_qIndex}. 
 */
public static int nm_qIndex(long struct){
  return UNSAFE.getInt(null,struct + B3JointInfo.M_QINDEX);
}

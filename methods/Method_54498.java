/** 
 * Unsafe version of  {@link #m_qSize}. 
 */
public static int nm_qSize(long struct){
  return UNSAFE.getInt(null,struct + B3JointInfo.M_QSIZE);
}

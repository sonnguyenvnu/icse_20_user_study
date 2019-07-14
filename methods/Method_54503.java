/** 
 * Unsafe version of  {@link #m_qIndex(int) m_qIndex}. 
 */
public static void nm_qIndex(long struct,int value){
  UNSAFE.putInt(null,struct + B3JointInfo.M_QINDEX,value);
}

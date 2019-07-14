/** 
 * Unsafe version of  {@link #m_qSize(int) m_qSize}. 
 */
public static void nm_qSize(long struct,int value){
  UNSAFE.putInt(null,struct + B3JointInfo.M_QSIZE,value);
}

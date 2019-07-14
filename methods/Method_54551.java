/** 
 * Unsafe version of  {@link #m_qDofSize(int) m_qDofSize}. 
 */
public static void nm_qDofSize(long struct,int value){
  UNSAFE.putInt(null,struct + B3JointSensorState2.M_QDOFSIZE,value);
}

/** 
 * Unsafe version of  {@link #m_uDofSize(int) m_uDofSize}. 
 */
public static void nm_uDofSize(long struct,int value){
  UNSAFE.putInt(null,struct + B3JointSensorState2.M_UDOFSIZE,value);
}

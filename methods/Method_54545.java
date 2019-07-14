/** 
 * Unsafe version of  {@link #m_jointPosition(int,double) m_jointPosition}. 
 */
public static void nm_jointPosition(long struct,int index,double value){
  UNSAFE.putDouble(null,struct + B3JointSensorState2.M_JOINTPOSITION + check(index,4) * 8,value);
}

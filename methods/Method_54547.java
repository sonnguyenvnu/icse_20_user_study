/** 
 * Unsafe version of  {@link #m_jointVelocity(int,double) m_jointVelocity}. 
 */
public static void nm_jointVelocity(long struct,int index,double value){
  UNSAFE.putDouble(null,struct + B3JointSensorState2.M_JOINTVELOCITY + check(index,3) * 8,value);
}

/** 
 * Unsafe version of  {@link #m_jointLowerLimit(double) m_jointLowerLimit}. 
 */
public static void nm_jointLowerLimit(long struct,double value){
  UNSAFE.putDouble(null,struct + B3JointInfo.M_JOINTLOWERLIMIT,value);
}

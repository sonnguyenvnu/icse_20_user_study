/** 
 * Unsafe version of  {@link #m_jointMaxVelocity(double) m_jointMaxVelocity}. 
 */
public static void nm_jointMaxVelocity(long struct,double value){
  UNSAFE.putDouble(null,struct + B3JointInfo.M_JOINTMAXVELOCITY,value);
}

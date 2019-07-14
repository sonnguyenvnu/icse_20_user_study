/** 
 * Unsafe version of  {@link #m_jointMaxForce(double) m_jointMaxForce}. 
 */
public static void nm_jointMaxForce(long struct,double value){
  UNSAFE.putDouble(null,struct + B3JointInfo.M_JOINTMAXFORCE,value);
}

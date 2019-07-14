/** 
 * Unsafe version of  {@link #m_jointFriction(double) m_jointFriction}. 
 */
public static void nm_jointFriction(long struct,double value){
  UNSAFE.putDouble(null,struct + B3JointInfo.M_JOINTFRICTION,value);
}

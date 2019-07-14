/** 
 * Unsafe version of  {@link #m_jointDamping(double) m_jointDamping}. 
 */
public static void nm_jointDamping(long struct,double value){
  UNSAFE.putDouble(null,struct + B3JointInfo.M_JOINTDAMPING,value);
}

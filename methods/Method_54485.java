/** 
 * Unsafe version of  {@link #m_jointDamping}. 
 */
public static double nm_jointDamping(long struct){
  return UNSAFE.getDouble(null,struct + B3JointInfo.M_JOINTDAMPING);
}

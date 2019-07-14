/** 
 * Unsafe version of  {@link #m_jointLowerLimit}. 
 */
public static double nm_jointLowerLimit(long struct){
  return UNSAFE.getDouble(null,struct + B3JointInfo.M_JOINTLOWERLIMIT);
}

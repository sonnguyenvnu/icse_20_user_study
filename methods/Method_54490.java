/** 
 * Unsafe version of  {@link #m_jointMaxVelocity}. 
 */
public static double nm_jointMaxVelocity(long struct){
  return UNSAFE.getDouble(null,struct + B3JointInfo.M_JOINTMAXVELOCITY);
}

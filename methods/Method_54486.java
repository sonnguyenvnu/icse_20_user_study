/** 
 * Unsafe version of  {@link #m_jointFriction}. 
 */
public static double nm_jointFriction(long struct){
  return UNSAFE.getDouble(null,struct + B3JointInfo.M_JOINTFRICTION);
}

/** 
 * Unsafe version of  {@link #m_jointMaxForce}. 
 */
public static double nm_jointMaxForce(long struct){
  return UNSAFE.getDouble(null,struct + B3JointInfo.M_JOINTMAXFORCE);
}

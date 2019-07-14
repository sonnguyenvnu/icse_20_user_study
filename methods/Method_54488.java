/** 
 * Unsafe version of  {@link #m_jointUpperLimit}. 
 */
public static double nm_jointUpperLimit(long struct){
  return UNSAFE.getDouble(null,struct + B3JointInfo.M_JOINTUPPERLIMIT);
}

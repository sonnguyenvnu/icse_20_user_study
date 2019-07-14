/** 
 * Unsafe version of  {@link #m_angularDamping}. 
 */
public static double nm_angularDamping(long struct){
  return UNSAFE.getDouble(null,struct + B3DynamicsInfo.M_ANGULARDAMPING);
}

/** 
 * Unsafe version of  {@link #m_linearDamping}. 
 */
public static double nm_linearDamping(long struct){
  return UNSAFE.getDouble(null,struct + B3DynamicsInfo.M_LINEARDAMPING);
}

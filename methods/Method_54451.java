/** 
 * Unsafe version of  {@link #m_rollingFrictionCoeff}. 
 */
public static double nm_rollingFrictionCoeff(long struct){
  return UNSAFE.getDouble(null,struct + B3DynamicsInfo.M_ROLLINGFRICTIONCOEFF);
}

/** 
 * Unsafe version of  {@link #m_spinningFrictionCoeff}. 
 */
public static double nm_spinningFrictionCoeff(long struct){
  return UNSAFE.getDouble(null,struct + B3DynamicsInfo.M_SPINNINGFRICTIONCOEFF);
}

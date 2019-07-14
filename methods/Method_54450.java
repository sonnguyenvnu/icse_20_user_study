/** 
 * Unsafe version of  {@link #m_lateralFrictionCoeff}. 
 */
public static double nm_lateralFrictionCoeff(long struct){
  return UNSAFE.getDouble(null,struct + B3DynamicsInfo.M_LATERALFRICTIONCOEFF);
}

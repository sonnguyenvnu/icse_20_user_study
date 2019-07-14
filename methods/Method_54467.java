/** 
 * Unsafe version of  {@link #m_spinningFrictionCoeff(double) m_spinningFrictionCoeff}. 
 */
public static void nm_spinningFrictionCoeff(long struct,double value){
  UNSAFE.putDouble(null,struct + B3DynamicsInfo.M_SPINNINGFRICTIONCOEFF,value);
}

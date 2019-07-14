/** 
 * Unsafe version of  {@link #m_rollingFrictionCoeff(double) m_rollingFrictionCoeff}. 
 */
public static void nm_rollingFrictionCoeff(long struct,double value){
  UNSAFE.putDouble(null,struct + B3DynamicsInfo.M_ROLLINGFRICTIONCOEFF,value);
}

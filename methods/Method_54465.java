/** 
 * Unsafe version of  {@link #m_lateralFrictionCoeff(double) m_lateralFrictionCoeff}. 
 */
public static void nm_lateralFrictionCoeff(long struct,double value){
  UNSAFE.putDouble(null,struct + B3DynamicsInfo.M_LATERALFRICTIONCOEFF,value);
}

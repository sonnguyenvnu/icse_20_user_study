/** 
 * Unsafe version of  {@link #m_angularDamping(double) m_angularDamping}. 
 */
public static void nm_angularDamping(long struct,double value){
  UNSAFE.putDouble(null,struct + B3DynamicsInfo.M_ANGULARDAMPING,value);
}

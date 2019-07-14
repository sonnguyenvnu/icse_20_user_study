/** 
 * Unsafe version of  {@link #m_linearDamping(double) m_linearDamping}. 
 */
public static void nm_linearDamping(long struct,double value){
  UNSAFE.putDouble(null,struct + B3DynamicsInfo.M_LINEARDAMPING,value);
}

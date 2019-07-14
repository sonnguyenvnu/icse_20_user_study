/** 
 * Unsafe version of  {@link #m_restitution(double) m_restitution}. 
 */
public static void nm_restitution(long struct,double value){
  UNSAFE.putDouble(null,struct + B3DynamicsInfo.M_RESTITUTION,value);
}

/** 
 * Unsafe version of  {@link #m_contactDamping(double) m_contactDamping}. 
 */
public static void nm_contactDamping(long struct,double value){
  UNSAFE.putDouble(null,struct + B3DynamicsInfo.M_CONTACTDAMPING,value);
}

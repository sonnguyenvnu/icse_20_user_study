/** 
 * Unsafe version of  {@link #m_contactDamping}. 
 */
public static double nm_contactDamping(long struct){
  return UNSAFE.getDouble(null,struct + B3DynamicsInfo.M_CONTACTDAMPING);
}

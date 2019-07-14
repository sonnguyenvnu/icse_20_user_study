/** 
 * Unsafe version of  {@link #m_restitution}. 
 */
public static double nm_restitution(long struct){
  return UNSAFE.getDouble(null,struct + B3DynamicsInfo.M_RESTITUTION);
}

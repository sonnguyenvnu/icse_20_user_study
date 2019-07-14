/** 
 * Unsafe version of  {@link #m_contactStiffness}. 
 */
public static double nm_contactStiffness(long struct){
  return UNSAFE.getDouble(null,struct + B3DynamicsInfo.M_CONTACTSTIFFNESS);
}

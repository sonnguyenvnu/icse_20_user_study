/** 
 * Unsafe version of  {@link #m_activationState}. 
 */
public static int nm_activationState(long struct){
  return UNSAFE.getInt(null,struct + B3DynamicsInfo.M_ACTIVATIONSTATE);
}

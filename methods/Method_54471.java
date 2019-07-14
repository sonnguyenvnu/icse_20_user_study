/** 
 * Unsafe version of  {@link #m_activationState(int) m_activationState}. 
 */
public static void nm_activationState(long struct,int value){
  UNSAFE.putInt(null,struct + B3DynamicsInfo.M_ACTIVATIONSTATE,value);
}

/** 
 * Unsafe version of  {@link #m_contactSlop(double) m_contactSlop}. 
 */
public static void nm_contactSlop(long struct,double value){
  UNSAFE.putDouble(null,struct + B3PhysicsSimulationParameters.M_CONTACTSLOP,value);
}

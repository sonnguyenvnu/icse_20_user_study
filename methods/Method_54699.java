/** 
 * Unsafe version of  {@link #m_defaultNonContactERP(double) m_defaultNonContactERP}. 
 */
public static void nm_defaultNonContactERP(long struct,double value){
  UNSAFE.putDouble(null,struct + B3PhysicsSimulationParameters.M_DEFAULTNONCONTACTERP,value);
}

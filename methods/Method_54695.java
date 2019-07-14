/** 
 * Unsafe version of  {@link #m_defaultContactERP(double) m_defaultContactERP}. 
 */
public static void nm_defaultContactERP(long struct,double value){
  UNSAFE.putDouble(null,struct + B3PhysicsSimulationParameters.M_DEFAULTCONTACTERP,value);
}

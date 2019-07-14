/** 
 * Unsafe version of  {@link #m_frictionERP(double) m_frictionERP}. 
 */
public static void nm_frictionERP(long struct,double value){
  UNSAFE.putDouble(null,struct + B3PhysicsSimulationParameters.M_FRICTIONERP,value);
}

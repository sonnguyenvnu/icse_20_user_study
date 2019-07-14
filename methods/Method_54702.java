/** 
 * Unsafe version of  {@link #m_frictionCFM(double) m_frictionCFM}. 
 */
public static void nm_frictionCFM(long struct,double value){
  UNSAFE.putDouble(null,struct + B3PhysicsSimulationParameters.M_FRICTIONCFM,value);
}

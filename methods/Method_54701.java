/** 
 * Unsafe version of  {@link #m_defaultGlobalCFM(double) m_defaultGlobalCFM}. 
 */
public static void nm_defaultGlobalCFM(long struct,double value){
  UNSAFE.putDouble(null,struct + B3PhysicsSimulationParameters.M_DEFAULTGLOBALCFM,value);
}

/** 
 * Unsafe version of  {@link #m_restitutionVelocityThreshold(double) m_restitutionVelocityThreshold}. 
 */
public static void nm_restitutionVelocityThreshold(long struct,double value){
  UNSAFE.putDouble(null,struct + B3PhysicsSimulationParameters.M_RESTITUTIONVELOCITYTHRESHOLD,value);
}

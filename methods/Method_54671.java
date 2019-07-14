/** 
 * Unsafe version of  {@link #m_restitutionVelocityThreshold}. 
 */
public static double nm_restitutionVelocityThreshold(long struct){
  return UNSAFE.getDouble(null,struct + B3PhysicsSimulationParameters.M_RESTITUTIONVELOCITYTHRESHOLD);
}

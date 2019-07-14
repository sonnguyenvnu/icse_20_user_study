/** 
 * Unsafe version of  {@link #m_jointFeedbackMode}. 
 */
public static int nm_jointFeedbackMode(long struct){
  return UNSAFE.getInt(null,struct + B3PhysicsSimulationParameters.M_JOINTFEEDBACKMODE);
}

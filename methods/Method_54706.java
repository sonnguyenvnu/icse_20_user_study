/** 
 * Unsafe version of  {@link #m_jointFeedbackMode(int) m_jointFeedbackMode}. 
 */
public static void nm_jointFeedbackMode(long struct,int value){
  UNSAFE.putInt(null,struct + B3PhysicsSimulationParameters.M_JOINTFEEDBACKMODE,value);
}

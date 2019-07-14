/** 
 * Unsafe version of  {@link #m_gravityAcceleration(int,double) m_gravityAcceleration}. 
 */
public static void nm_gravityAcceleration(long struct,int index,double value){
  UNSAFE.putDouble(null,struct + B3PhysicsSimulationParameters.M_GRAVITYACCELERATION + check(index,3) * 8,value);
}

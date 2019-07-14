/** 
 * Unsafe version of  {@link #m_localInertialFrame(int) m_localInertialFrame}. 
 */
public static double nm_localInertialFrame(long struct,int index){
  return UNSAFE.getDouble(null,struct + B3DynamicsInfo.M_LOCALINERTIALFRAME + check(index,7) * 8);
}

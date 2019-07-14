/** 
 * Unsafe version of  {@link #m_localInertialFrame}. 
 */
public static DoubleBuffer nm_localInertialFrame(long struct){
  return memDoubleBuffer(struct + B3DynamicsInfo.M_LOCALINERTIALFRAME,7);
}

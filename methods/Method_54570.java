/** 
 * Unsafe version of  {@link #m_localInertialOrientation}. 
 */
public static DoubleBuffer nm_localInertialOrientation(long struct){
  return memDoubleBuffer(struct + B3LinkState.M_LOCALINERTIALORIENTATION,4);
}

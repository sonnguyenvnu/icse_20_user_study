/** 
 * Unsafe version of  {@link #m_localInertialPosition}. 
 */
public static DoubleBuffer nm_localInertialPosition(long struct){
  return memDoubleBuffer(struct + B3LinkState.M_LOCALINERTIALPOSITION,3);
}

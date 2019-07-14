/** 
 * Unsafe version of  {@link #m_localInertialDiagonal}. 
 */
public static DoubleBuffer nm_localInertialDiagonal(long struct){
  return memDoubleBuffer(struct + B3DynamicsInfo.M_LOCALINERTIALDIAGONAL,3);
}

/** 
 * Unsafe version of  {@link #m_localInertialDiagonal(int) m_localInertialDiagonal}. 
 */
public static double nm_localInertialDiagonal(long struct,int index){
  return UNSAFE.getDouble(null,struct + B3DynamicsInfo.M_LOCALINERTIALDIAGONAL + check(index,3) * 8);
}

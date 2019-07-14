/** 
 * Unsafe version of  {@link #m_localInertialPosition(int) m_localInertialPosition}. 
 */
public static double nm_localInertialPosition(long struct,int index){
  return UNSAFE.getDouble(null,struct + B3LinkState.M_LOCALINERTIALPOSITION + check(index,3) * 8);
}

/** 
 * Unsafe version of  {@link #m_worldPosition(int) m_worldPosition}. 
 */
public static double nm_worldPosition(long struct,int index){
  return UNSAFE.getDouble(null,struct + B3LinkState.M_WORLDPOSITION + check(index,3) * 8);
}

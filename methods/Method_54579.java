/** 
 * Unsafe version of  {@link #m_worldAABBMax(int) m_worldAABBMax}. 
 */
public static double nm_worldAABBMax(long struct,int index){
  return UNSAFE.getDouble(null,struct + B3LinkState.M_WORLDAABBMAX + check(index,3) * 8);
}

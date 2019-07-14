/** 
 * Unsafe version of  {@link #m_worldAABBMin(int) m_worldAABBMin}. 
 */
public static double nm_worldAABBMin(long struct,int index){
  return UNSAFE.getDouble(null,struct + B3LinkState.M_WORLDAABBMIN + check(index,3) * 8);
}

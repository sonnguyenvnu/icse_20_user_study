/** 
 * Unsafe version of  {@link #m_worldAABBMin(int,double) m_worldAABBMin}. 
 */
public static void nm_worldAABBMin(long struct,int index,double value){
  UNSAFE.putDouble(null,struct + B3LinkState.M_WORLDAABBMIN + check(index,3) * 8,value);
}

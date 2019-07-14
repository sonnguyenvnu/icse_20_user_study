/** 
 * Unsafe version of  {@link #m_worldAABBMax(int,double) m_worldAABBMax}. 
 */
public static void nm_worldAABBMax(long struct,int index,double value){
  UNSAFE.putDouble(null,struct + B3LinkState.M_WORLDAABBMAX + check(index,3) * 8,value);
}

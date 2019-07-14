/** 
 * Unsafe version of  {@link #m_worldPosition(int,double) m_worldPosition}. 
 */
public static void nm_worldPosition(long struct,int index,double value){
  UNSAFE.putDouble(null,struct + B3LinkState.M_WORLDPOSITION + check(index,3) * 8,value);
}

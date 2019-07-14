/** 
 * Unsafe version of  {@link #m_worldLinearVelocity(int,double) m_worldLinearVelocity}. 
 */
public static void nm_worldLinearVelocity(long struct,int index,double value){
  UNSAFE.putDouble(null,struct + B3LinkState.M_WORLDLINEARVELOCITY + check(index,3) * 8,value);
}

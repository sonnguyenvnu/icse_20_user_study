/** 
 * Unsafe version of  {@link #m_worldLinearVelocity(int) m_worldLinearVelocity}. 
 */
public static double nm_worldLinearVelocity(long struct,int index){
  return UNSAFE.getDouble(null,struct + B3LinkState.M_WORLDLINEARVELOCITY + check(index,3) * 8);
}

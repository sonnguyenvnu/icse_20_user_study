/** 
 * Unsafe version of  {@link #m_worldAngularVelocity(int) m_worldAngularVelocity}. 
 */
public static double nm_worldAngularVelocity(long struct,int index){
  return UNSAFE.getDouble(null,struct + B3LinkState.M_WORLDANGULARVELOCITY + check(index,3) * 8);
}

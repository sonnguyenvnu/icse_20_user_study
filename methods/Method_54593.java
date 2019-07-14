/** 
 * Unsafe version of  {@link #m_worldAngularVelocity(int,double) m_worldAngularVelocity}. 
 */
public static void nm_worldAngularVelocity(long struct,int index,double value){
  UNSAFE.putDouble(null,struct + B3LinkState.M_WORLDANGULARVELOCITY + check(index,3) * 8,value);
}

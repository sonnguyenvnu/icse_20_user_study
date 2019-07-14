/** 
 * Unsafe version of  {@link #m_localInertialPosition(int,double) m_localInertialPosition}. 
 */
public static void nm_localInertialPosition(long struct,int index,double value){
  UNSAFE.putDouble(null,struct + B3LinkState.M_LOCALINERTIALPOSITION + check(index,3) * 8,value);
}

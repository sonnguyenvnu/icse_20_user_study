/** 
 * Unsafe version of  {@link #m_localInertialOrientation(int,double) m_localInertialOrientation}. 
 */
public static void nm_localInertialOrientation(long struct,int index,double value){
  UNSAFE.putDouble(null,struct + B3LinkState.M_LOCALINERTIALORIENTATION + check(index,4) * 8,value);
}

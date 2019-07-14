/** 
 * Unsafe version of  {@link #m_localInertialOrientation(int) m_localInertialOrientation}. 
 */
public static double nm_localInertialOrientation(long struct,int index){
  return UNSAFE.getDouble(null,struct + B3LinkState.M_LOCALINERTIALORIENTATION + check(index,4) * 8);
}

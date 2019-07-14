/** 
 * Unsafe version of  {@link #m_jointAxis(int) m_jointAxis}. 
 */
public static double nm_jointAxis(long struct,int index){
  return UNSAFE.getDouble(null,struct + B3JointInfo.M_JOINTAXIS + check(index,3) * 8);
}

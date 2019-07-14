/** 
 * Unsafe version of  {@link #m_childFrame(int) m_childFrame}. 
 */
public static double nm_childFrame(long struct,int index){
  return UNSAFE.getDouble(null,struct + B3JointInfo.M_CHILDFRAME + check(index,7) * 8);
}

/** 
 * Unsafe version of  {@link #m_jointAxis}. 
 */
public static DoubleBuffer nm_jointAxis(long struct){
  return memDoubleBuffer(struct + B3JointInfo.M_JOINTAXIS,3);
}

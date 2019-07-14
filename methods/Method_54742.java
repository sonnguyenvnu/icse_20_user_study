/** 
 * Unsafe version of  {@link #m_childFrame}. 
 */
public static DoubleBuffer nm_childFrame(long struct){
  return memDoubleBuffer(struct + B3UserConstraint.M_CHILDFRAME,7);
}

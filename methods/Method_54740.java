/** 
 * Unsafe version of  {@link #m_parentFrame}. 
 */
public static DoubleBuffer nm_parentFrame(long struct){
  return memDoubleBuffer(struct + B3UserConstraint.M_PARENTFRAME,7);
}

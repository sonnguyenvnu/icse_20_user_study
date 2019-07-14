/** 
 * Unsafe version of  {@link #m_parentFrame(int) m_parentFrame}. 
 */
public static double nm_parentFrame(long struct,int index){
  return UNSAFE.getDouble(null,struct + B3UserConstraint.M_PARENTFRAME + check(index,7) * 8);
}

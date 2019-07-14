/** 
 * Unsafe version of  {@link #m_parentFrame(int,double) m_parentFrame}. 
 */
public static void nm_parentFrame(long struct,int index,double value){
  UNSAFE.putDouble(null,struct + B3UserConstraint.M_PARENTFRAME + check(index,7) * 8,value);
}

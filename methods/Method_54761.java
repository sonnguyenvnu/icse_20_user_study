/** 
 * Unsafe version of  {@link #m_jointAxis(int,double) m_jointAxis}. 
 */
public static void nm_jointAxis(long struct,int index,double value){
  UNSAFE.putDouble(null,struct + B3UserConstraint.M_JOINTAXIS + check(index,3) * 8,value);
}

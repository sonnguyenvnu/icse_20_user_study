/** 
 * Unsafe version of  {@link #m_jointType(int) m_jointType}. 
 */
public static void nm_jointType(long struct,int value){
  UNSAFE.putInt(null,struct + B3UserConstraint.M_JOINTTYPE,value);
}

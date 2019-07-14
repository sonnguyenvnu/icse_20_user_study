/** 
 * Unsafe version of  {@link #m_jointType}. 
 */
public static int nm_jointType(long struct){
  return UNSAFE.getInt(null,struct + B3UserConstraint.M_JOINTTYPE);
}

/** 
 * Unsafe version of  {@link #m_parentBodyIndex}. 
 */
public static int nm_parentBodyIndex(long struct){
  return UNSAFE.getInt(null,struct + B3UserConstraint.M_PARENTBODYINDEX);
}

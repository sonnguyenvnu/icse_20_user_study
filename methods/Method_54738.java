/** 
 * Unsafe version of  {@link #m_childBodyIndex}. 
 */
public static int nm_childBodyIndex(long struct){
  return UNSAFE.getInt(null,struct + B3UserConstraint.M_CHILDBODYINDEX);
}

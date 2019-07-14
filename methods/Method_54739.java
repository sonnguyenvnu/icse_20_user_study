/** 
 * Unsafe version of  {@link #m_childJointIndex}. 
 */
public static int nm_childJointIndex(long struct){
  return UNSAFE.getInt(null,struct + B3UserConstraint.M_CHILDJOINTINDEX);
}

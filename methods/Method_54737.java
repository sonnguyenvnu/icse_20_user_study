/** 
 * Unsafe version of  {@link #m_parentJointIndex}. 
 */
public static int nm_parentJointIndex(long struct){
  return UNSAFE.getInt(null,struct + B3UserConstraint.M_PARENTJOINTINDEX);
}

/** 
 * Unsafe version of  {@link #m_parentJointIndex(int) m_parentJointIndex}. 
 */
public static void nm_parentJointIndex(long struct,int value){
  UNSAFE.putInt(null,struct + B3UserConstraint.M_PARENTJOINTINDEX,value);
}

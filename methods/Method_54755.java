/** 
 * Unsafe version of  {@link #m_childJointIndex(int) m_childJointIndex}. 
 */
public static void nm_childJointIndex(long struct,int value){
  UNSAFE.putInt(null,struct + B3UserConstraint.M_CHILDJOINTINDEX,value);
}

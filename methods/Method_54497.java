/** 
 * Unsafe version of  {@link #m_parentIndex}. 
 */
public static int nm_parentIndex(long struct){
  return UNSAFE.getInt(null,struct + B3JointInfo.M_PARENTINDEX);
}

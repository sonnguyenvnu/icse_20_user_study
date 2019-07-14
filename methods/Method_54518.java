/** 
 * Unsafe version of  {@link #m_parentIndex(int) m_parentIndex}. 
 */
public static void nm_parentIndex(long struct,int value){
  UNSAFE.putInt(null,struct + B3JointInfo.M_PARENTINDEX,value);
}

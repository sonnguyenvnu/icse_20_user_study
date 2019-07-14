/** 
 * Unsafe version of  {@link #m_uIndex(int) m_uIndex}. 
 */
public static void nm_uIndex(long struct,int value){
  UNSAFE.putInt(null,struct + B3JointInfo.M_UINDEX,value);
}

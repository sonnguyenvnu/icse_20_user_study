/** 
 * Unsafe version of  {@link #m_uSize(int) m_uSize}. 
 */
public static void nm_uSize(long struct,int value){
  UNSAFE.putInt(null,struct + B3JointInfo.M_USIZE,value);
}

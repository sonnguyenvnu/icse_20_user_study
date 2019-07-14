/** 
 * Unsafe version of  {@link #m_jointIndex(int) m_jointIndex}. 
 */
public static void nm_jointIndex(long struct,int value){
  UNSAFE.putInt(null,struct + B3JointInfo.M_JOINTINDEX,value);
}

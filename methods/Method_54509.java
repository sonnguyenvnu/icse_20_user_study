/** 
 * Unsafe version of  {@link #m_jointUpperLimit(double) m_jointUpperLimit}. 
 */
public static void nm_jointUpperLimit(long struct,double value){
  UNSAFE.putDouble(null,struct + B3JointInfo.M_JOINTUPPERLIMIT,value);
}

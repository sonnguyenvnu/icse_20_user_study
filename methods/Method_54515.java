/** 
 * Unsafe version of  {@link #m_childFrame(int,double) m_childFrame}. 
 */
public static void nm_childFrame(long struct,int index,double value){
  UNSAFE.putDouble(null,struct + B3JointInfo.M_CHILDFRAME + check(index,7) * 8,value);
}

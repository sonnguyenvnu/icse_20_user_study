/** 
 * Unsafe version of  {@link #m_localInertialFrame(int,double) m_localInertialFrame}. 
 */
public static void nm_localInertialFrame(long struct,int index,double value){
  UNSAFE.putDouble(null,struct + B3DynamicsInfo.M_LOCALINERTIALFRAME + check(index,7) * 8,value);
}

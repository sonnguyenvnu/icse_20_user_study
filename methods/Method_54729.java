/** 
 * Unsafe version of  {@link #m_hitPositionWorld(int,double) m_hitPositionWorld}. 
 */
public static void nm_hitPositionWorld(long struct,int index,double value){
  UNSAFE.putDouble(null,struct + B3RayHitInfo.M_HITPOSITIONWORLD + check(index,3) * 8,value);
}

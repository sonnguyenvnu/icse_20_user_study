/** 
 * Unsafe version of  {@link #m_hitPositionWorld(DoubleBuffer) m_hitPositionWorld}. 
 */
public static void nm_hitPositionWorld(long struct,DoubleBuffer value){
  if (CHECKS) {
    checkGT(value,3);
  }
  memCopy(memAddress(value),struct + B3RayHitInfo.M_HITPOSITIONWORLD,value.remaining() * 8);
}

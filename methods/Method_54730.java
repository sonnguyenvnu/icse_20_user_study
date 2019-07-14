/** 
 * Unsafe version of  {@link #m_hitNormalWorld(DoubleBuffer) m_hitNormalWorld}. 
 */
public static void nm_hitNormalWorld(long struct,DoubleBuffer value){
  if (CHECKS) {
    checkGT(value,3);
  }
  memCopy(memAddress(value),struct + B3RayHitInfo.M_HITNORMALWORLD,value.remaining() * 8);
}

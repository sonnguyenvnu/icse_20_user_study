/** 
 * Unsafe version of  {@link #m_hitObjectLinkIndex}. 
 */
public static int nm_hitObjectLinkIndex(long struct){
  return UNSAFE.getInt(null,struct + B3RayHitInfo.M_HITOBJECTLINKINDEX);
}

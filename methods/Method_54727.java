/** 
 * Unsafe version of  {@link #m_hitObjectLinkIndex(int) m_hitObjectLinkIndex}. 
 */
public static void nm_hitObjectLinkIndex(long struct,int value){
  UNSAFE.putInt(null,struct + B3RayHitInfo.M_HITOBJECTLINKINDEX,value);
}

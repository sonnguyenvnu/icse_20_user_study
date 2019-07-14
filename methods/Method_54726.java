/** 
 * Unsafe version of  {@link #m_hitObjectUniqueId(int) m_hitObjectUniqueId}. 
 */
public static void nm_hitObjectUniqueId(long struct,int value){
  UNSAFE.putInt(null,struct + B3RayHitInfo.M_HITOBJECTUNIQUEID,value);
}

/** 
 * Unsafe version of  {@link #m_hitObjectUniqueId}. 
 */
public static int nm_hitObjectUniqueId(long struct){
  return UNSAFE.getInt(null,struct + B3RayHitInfo.M_HITOBJECTUNIQUEID);
}

/** 
 * Unsafe version of  {@link #m_rayHits}. 
 */
public static B3RayHitInfo.Buffer nm_rayHits(long struct){
  return B3RayHitInfo.create(memGetAddress(struct + B3RaycastInformation.M_RAYHITS),nm_numRayHits(struct));
}

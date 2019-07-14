/** 
 * Unsafe version of  {@link #m_rayHits(B3RayHitInfo.Buffer) m_rayHits}. 
 */
public static void nm_rayHits(long struct,B3RayHitInfo.Buffer value){
  memPutAddress(struct + B3RaycastInformation.M_RAYHITS,value.address());
  nm_numRayHits(struct,value.remaining());
}

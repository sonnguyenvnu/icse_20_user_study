/** 
 * Returns a  {@link B3RayHitInfo.Buffer} view of the struct array pointed to by the {@code m_rayHits} field. 
 */
@NativeType("struct b3RayHitInfo *") public B3RayHitInfo.Buffer m_rayHits(){
  return nm_rayHits(address());
}

/** 
 * Sets the address of the specified  {@link B3RayHitInfo.Buffer} to the {@code m_rayHits} field. 
 */
public B3RaycastInformation m_rayHits(@NativeType("struct b3RayHitInfo *") B3RayHitInfo.Buffer value){
  nm_rayHits(address(),value);
  return this;
}

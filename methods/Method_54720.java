/** 
 * Copies the specified  {@link DoubleBuffer} to the {@code m_hitNormalWorld} field. 
 */
public B3RayHitInfo m_hitNormalWorld(@NativeType("double[3]") DoubleBuffer value){
  nm_hitNormalWorld(address(),value);
  return this;
}

/** 
 * Maintenance operations
 * @since 6.0.2
 */
public void maintenance(){
  garbageCollection();
  mPreCache.fill();
}

/** 
 * Check whether the cache contains an image for the given frame index.
 */
public boolean contains(int frameIndex){
  return mBackingCache.contains(keyFor(frameIndex));
}

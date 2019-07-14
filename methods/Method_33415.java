/** 
 * Called when the animation is stopping
 */
protected void stopping(){
  region.get().setCache(oldCache);
  region.get().setCacheHint(oldCacheHint);
}

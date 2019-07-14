/** 
 * Clear the memory caches
 */
public void clearMemoryCaches(){
  Predicate<CacheKey> allPredicate=new Predicate<CacheKey>(){
    @Override public boolean apply(    CacheKey key){
      return true;
    }
  }
;
  mBitmapMemoryCache.removeAll(allPredicate);
  mEncodedMemoryCache.removeAll(allPredicate);
}

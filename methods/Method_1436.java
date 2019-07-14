/** 
 * Returns whether the image is stored in the disk cache.
 * @param imageRequest the imageRequest for the image to be looked up.
 * @return true if the image was found in the disk cache, false otherwise.
 */
public DataSource<Boolean> isInDiskCache(final ImageRequest imageRequest){
  final CacheKey cacheKey=mCacheKeyFactory.getEncodedCacheKey(imageRequest,null);
  final SimpleDataSource<Boolean> dataSource=SimpleDataSource.create();
  mMainBufferedDiskCache.contains(cacheKey).continueWithTask(new Continuation<Boolean,Task<Boolean>>(){
    @Override public Task<Boolean> then(    Task<Boolean> task) throws Exception {
      if (!task.isCancelled() && !task.isFaulted() && task.getResult()) {
        return Task.forResult(true);
      }
      return mSmallImageBufferedDiskCache.contains(cacheKey);
    }
  }
).continueWith(new Continuation<Boolean,Void>(){
    @Override public Void then(    Task<Boolean> task) throws Exception {
      dataSource.setResult(!task.isCancelled() && !task.isFaulted() && task.getResult());
      return null;
    }
  }
);
  return dataSource;
}

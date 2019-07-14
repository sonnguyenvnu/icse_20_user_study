/** 
 * Current disk caches size
 * @return size in Bytes
 */
public long getUsedDiskCacheSize(){
  return mMainBufferedDiskCache.getSize() + mSmallImageBufferedDiskCache.getSize();
}

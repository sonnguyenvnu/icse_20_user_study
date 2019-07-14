public DiskCacheReadProducer newDiskCacheReadProducer(Producer<EncodedImage> inputProducer){
  return new DiskCacheReadProducer(mDefaultBufferedDiskCache,mSmallImageBufferedDiskCache,mCacheKeyFactory,inputProducer);
}

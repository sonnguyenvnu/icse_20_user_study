private Bucket getCurrentBucket(){
  long currentTime=time.getCurrentTimeInMillis();
  Bucket currentBucket=buckets.peekLast();
  if (currentBucket != null && currentTime < currentBucket.windowStart + this.bucketSizeInMilliseconds) {
    return currentBucket;
  }
  if (newBucketLock.tryLock()) {
    try {
      if (buckets.peekLast() == null) {
        Bucket newBucket=new Bucket(currentTime,bucketDataLength);
        buckets.addLast(newBucket);
        return newBucket;
      }
 else {
        for (int i=0; i < numberOfBuckets; i++) {
          Bucket lastBucket=buckets.peekLast();
          if (currentTime < lastBucket.windowStart + this.bucketSizeInMilliseconds) {
            return lastBucket;
          }
 else           if (currentTime - (lastBucket.windowStart + this.bucketSizeInMilliseconds) > timeInMilliseconds) {
            reset();
            return getCurrentBucket();
          }
 else {
            Bucket[] allBuckets=buckets.getArray();
            buckets.addLast(new Bucket(lastBucket.windowStart + this.bucketSizeInMilliseconds,bucketDataLength));
            currentPercentileSnapshot=new PercentileSnapshot(allBuckets);
          }
        }
        return buckets.peekLast();
      }
    }
  finally {
      newBucketLock.unlock();
    }
  }
 else {
    currentBucket=buckets.peekLast();
    if (currentBucket != null) {
      return currentBucket;
    }
 else {
      try {
        Thread.sleep(5);
      }
 catch (      Exception e) {
      }
      return getCurrentBucket();
    }
  }
}

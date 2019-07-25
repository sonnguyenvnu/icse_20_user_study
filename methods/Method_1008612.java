private double round(double key){
  return Math.floor((key - emptyBucketInfo.offset) / emptyBucketInfo.interval) * emptyBucketInfo.interval + emptyBucketInfo.offset;
}

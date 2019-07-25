/** 
 * Sets the size - indicating how many term buckets should be returned (defaults to 10)
 */
public TermsAggregationBuilder size(int size){
  if (size <= 0) {
    throw new IllegalArgumentException("[size] must be greater than 0. Found [" + size + "] in [" + name + "]");
  }
  bucketCountThresholds.setRequiredSize(size);
  return this;
}

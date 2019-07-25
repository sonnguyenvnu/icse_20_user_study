private double variance(long owningBucketOrd){
  double sum=sums.get(owningBucketOrd);
  long count=counts.get(owningBucketOrd);
  return (sumOfSqrs.get(owningBucketOrd) - ((sum * sum) / count)) / count;
}

/** 
 * Creates a  {@link Distribution}.
 * @param count the count of the population values.
 * @param sum the sum of the population values.
 * @param sumOfSquaredDeviations the sum of squared deviations of the population values.
 * @param bucketOptions the bucket options used to create a histogram for the distribution.
 * @param buckets {@link Bucket}s of a histogram.
 * @return a {@code Distribution}.
 * @since 0.17
 */
public static Distribution create(long count,double sum,double sumOfSquaredDeviations,BucketOptions bucketOptions,List<Bucket> buckets){
  Utils.checkArgument(count >= 0,"count should be non-negative.");
  Utils.checkArgument(sumOfSquaredDeviations >= 0,"sum of squared deviations should be non-negative.");
  if (count == 0) {
    Utils.checkArgument(sum == 0,"sum should be 0 if count is 0.");
    Utils.checkArgument(sumOfSquaredDeviations == 0,"sum of squared deviations should be 0 if count is 0.");
  }
  Utils.checkNotNull(bucketOptions,"bucketOptions");
  List<Bucket> bucketsCopy=Collections.unmodifiableList(new ArrayList<Bucket>(Utils.checkNotNull(buckets,"buckets")));
  Utils.checkListElementNotNull(bucketsCopy,"bucket");
  return new AutoValue_Distribution(count,sum,sumOfSquaredDeviations,bucketOptions,bucketsCopy);
}

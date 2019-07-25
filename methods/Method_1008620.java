/** 
 * Creates a bucket ordering strategy which sorts buckets based on multiple criteria. A tie-breaker may be added to avoid non-deterministic ordering.
 * @param orders a list of {@link BucketOrder} parameters to sort on, in order of priority.
 */
public static BucketOrder compound(BucketOrder... orders){
  return compound(Arrays.asList(orders));
}

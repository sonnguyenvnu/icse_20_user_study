/** 
 * Adds the given <code>count</code> to the current value associated to the given  {@link Instant}. If there is no value currently associated to the {@link Instant} then the <code>count</code> is simply inserted. Notethat the caller of this method is responsible for dealing with the case where adding <code>count</code> would cause an overflow.
 * @param instant The instant at which the value was observed.
 * @param count   The value observed at the instant.
 */
public void upsert(final Instant instant,final long count){
  final long bucket=toLong(timeBucket,instant.toEpochMilli());
  timeSeries.merge(bucket,count,(x,y) -> x + y);
}

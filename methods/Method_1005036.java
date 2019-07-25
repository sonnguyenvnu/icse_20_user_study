/** 
 * Puts the provided <code>value</code> into the time series associated to the  {@link Instant} <code>instant</code>. Note that this overwrites anyprevious value in that bucket.
 * @param instant The instant at which the value was observed.
 * @param value   The value observed at the instant.
 */
@Override public void put(final Instant instant,final Long value){
  final long bucket=toLong(timeBucket,instant.toEpochMilli());
  timeSeries.put(bucket,value);
}

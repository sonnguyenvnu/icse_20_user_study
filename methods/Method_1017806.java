/** 
 * <p>Tells this generator to produce values within a specified {@linkplain InRange#min() minimum} and/or {@linkplain InRange#max() maximum}, inclusive, with uniform distribution, down to the nanosecond.</p> <p>If an endpoint of the range is not specified, the generator will use durations with second values of either  {@link Long#MIN_VALUE} or{@link Long#MAX_VALUE} (with nanoseconds set to 999,999,999) asappropriate.</p> <p> {@linkplain InRange#format()} is ignored. Durations are alwaysparsed using formats based on the ISO-8601 duration format {@code PnDTnHnMn.nS} with days considered to be exactly 24 hours.
 * @see Duration#parse(CharSequence)
 * @param range annotation that gives the range's constraints
 */
public void configure(InRange range){
  if (!defaultValueOf(InRange.class,"min").equals(range.min()))   min=Duration.parse(range.min());
  if (!defaultValueOf(InRange.class,"max").equals(range.max()))   max=Duration.parse(range.max());
  if (min.compareTo(max) > 0)   throw new IllegalArgumentException(String.format("bad range, %s > %s",range.min(),range.max()));
}

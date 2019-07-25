/** 
 * <p>Tells this generator to produce values within a specified {@linkplain InRange#min() minimum} and/or {@linkplain InRange#max() maximum}, inclusive, with uniform distribution, down to the nanosecond.</p> <p>If an endpoint of the range is not specified, the generator will use instants with values of either  {@link Instant#MIN} or{@link Instant#MAX} as appropriate.</p><p> {@linkplain InRange#format()} is ignored. Instants are alwaysparsed using  {@link java.time.format.DateTimeFormatter#ISO_INSTANT}.</p>
 * @param range annotation that gives the range's constraints
 */
public void configure(InRange range){
  if (!defaultValueOf(InRange.class,"min").equals(range.min()))   min=Instant.parse(range.min());
  if (!defaultValueOf(InRange.class,"max").equals(range.max()))   max=Instant.parse(range.max());
  if (min.compareTo(max) > 0)   throw new IllegalArgumentException(String.format("bad range, %s > %s",range.min(),range.max()));
}

/** 
 * <p>Tells this generator to produce values within a specified {@linkplain InRange#min() minimum} and/or {@linkplain InRange#max() maximum}, inclusive, with uniform distribution, down to the nanosecond.</p> <p>If an endpoint of the range is not specified, the generator will use times with values of either  {@link OffsetTime#MIN} or{@link OffsetTime#MAX} as appropriate.</p><p> {@link InRange#format()} describes{@linkplain DateTimeFormatter#ofPattern(String) how the generator is tointerpret the range's endpoints}.</p>
 * @param range annotation that gives the range's constraints
 */
public void configure(InRange range){
  DateTimeFormatter formatter=DateTimeFormatter.ofPattern(range.format());
  if (!defaultValueOf(InRange.class,"min").equals(range.min()))   min=OffsetTime.parse(range.min(),formatter);
  if (!defaultValueOf(InRange.class,"max").equals(range.max()))   max=OffsetTime.parse(range.max(),formatter);
  if (min.compareTo(max) > 0)   throw new IllegalArgumentException(String.format("bad range, %s > %s",range.min(),range.max()));
}

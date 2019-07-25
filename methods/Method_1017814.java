/** 
 * <p>Tells this generator to produce values within a specified {@linkplain InRange#min() minimum} and/or {@linkplain InRange#max() maximum}, inclusive, with uniform distribution.</p> <p>If an endpoint of the range is not specified, the generator will use ZoneOffsets with values of either  {@code ZoneOffset#MIN} or{@code ZoneOffset#MAX} as appropriate.</p><p> {@linkplain InRange#format()} is ignored. ZoneOffsets are alwaysparsed using their zone id.</p>
 * @see ZoneOffset#of(String)
 * @param range annotation that gives the range's constraints
 */
public void configure(InRange range){
  if (!defaultValueOf(InRange.class,"min").equals(range.min()))   min=ZoneOffset.of(range.min());
  if (!defaultValueOf(InRange.class,"max").equals(range.max()))   max=ZoneOffset.of(range.max());
  if (min.compareTo(max) > 0)   throw new IllegalArgumentException(String.format("bad range, %s > %s",min,max));
}

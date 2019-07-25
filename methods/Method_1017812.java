/** 
 * <p>Tells this generator to produce values within a specified {@linkplain InRange#min() minimum} and/or {@linkplain InRange#max() maximum}, inclusive, with uniform distribution.</p> <p>If an endpoint of the range is not specified, the generator will use Periods with values of either  {@code Period(Year#MIN_VALUE, -12, -31)}or  {@code Period(Year#MAX_VALUE, 12, 31)} as appropriate.</p><p> {@linkplain InRange#format()} is ignored.  Periods are always parsedusing formats based on the ISO-8601 period formats  {@code PnYnMnD} and{@code PnW}.</p>
 * @see Period#parse(CharSequence)
 * @param range annotation that gives the range's constraints
 */
public void configure(InRange range){
  if (!defaultValueOf(InRange.class,"min").equals(range.min()))   min=Period.parse(range.min());
  if (!defaultValueOf(InRange.class,"max").equals(range.max()))   max=Period.parse(range.max());
  if (toBigInteger(min).compareTo(toBigInteger(max)) > 0)   throw new IllegalArgumentException(String.format("bad range, %s > %s",range.min(),range.max()));
}

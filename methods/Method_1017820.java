/** 
 * <p>Tells this generator to produce values within a specified {@linkplain InRange#min() minimum} and/or {@linkplain InRange#max() maximum}, inclusive, with uniform distribution, down to the millisecond.</p> <p>If an endpoint of the range is not specified, the generator will use dates with milliseconds-since-the-epoch values of either {@link Integer#MIN_VALUE} or {@link Long#MAX_VALUE} as appropriate.</p><p> {@link InRange#format()} describes{@linkplain SimpleDateFormat#parse(String) how the generator is tointerpret the range's endpoints}.</p>
 * @param range annotation that gives the range's constraints
 */
public void configure(InRange range){
  SimpleDateFormat formatter=new SimpleDateFormat(range.format());
  formatter.setLenient(false);
  try {
    if (!defaultValueOf(InRange.class,"min").equals(range.min()))     min=formatter.parse(range.min());
    if (!defaultValueOf(InRange.class,"max").equals(range.max()))     max=formatter.parse(range.max());
  }
 catch (  ParseException e) {
    throw new IllegalArgumentException(e);
  }
  if (min.getTime() > max.getTime())   throw new IllegalArgumentException(String.format("bad range, %s > %s",range.min(),range.max()));
}

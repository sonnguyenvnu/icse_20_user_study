/** 
 * <p>Tells this generator to produce values within a specified {@linkplain InRange#min() minimum} (inclusive) and/or{@linkplain InRange#max() maximum} (exclusive), with uniformdistribution.</p> <p>If an endpoint of the range is not specified, its value takes on a magnitude influenced by {@link com.pholser.junit.quickcheck.generator.GenerationStatus#size()}.</p>
 * @param range annotation that gives the range's constraints
 */
public void configure(InRange range){
  if (!defaultValueOf(InRange.class,"min").equals(range.min()))   min=new BigDecimal(range.min());
  if (!defaultValueOf(InRange.class,"max").equals(range.max()))   max=new BigDecimal(range.max());
  if (min != null && max != null)   checkRange(Ranges.Type.FLOAT,min,max);
}

/** 
 * Tells this generator to produce values within a specified minimum and/or maximum, inclusive, with uniform distribution. {@link InRange#min} and {@link InRange#max} take precedence over{@link InRange#minLong()} and {@link InRange#maxLong()}, if non-empty.
 * @param range annotation that gives the range's constraints
 */
public void configure(InRange range){
  min=range.min().isEmpty() ? range.minLong() : Long.parseLong(range.min());
  max=range.max().isEmpty() ? range.maxLong() : Long.parseLong(range.max());
}

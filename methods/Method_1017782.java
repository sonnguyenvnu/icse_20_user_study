/** 
 * Tells this generator to produce values within a specified minimum and/or maximum, inclusive, with uniform distribution. {@link InRange#min} and {@link InRange#max} take precedence over{@link InRange#minInt()} and {@link InRange#maxInt()}, if non-empty.
 * @param range annotation that gives the range's constraints
 */
public void configure(InRange range){
  min=range.min().isEmpty() ? range.minInt() : Integer.parseInt(range.min());
  max=range.max().isEmpty() ? range.maxInt() : Integer.parseInt(range.max());
}

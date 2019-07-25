/** 
 * Tells this generator to produce values within a specified minimum (inclusive) and/or maximum (exclusive) with uniform distribution. {@link InRange#min} and {@link InRange#max} take precedence over{@link InRange#minDouble()} and {@link InRange#maxDouble()}, if non-empty.
 * @param range annotation that gives the range's constraints
 */
public void configure(InRange range){
  min=range.min().isEmpty() ? range.minDouble() : Double.parseDouble(range.min());
  max=range.max().isEmpty() ? range.maxDouble() : Double.parseDouble(range.max());
}

/** 
 * Tells this generator to produce values within a specified minimum and/or maximum, inclusive, with uniform distribution. {@link InRange#min} and {@link InRange#max} take precedence over{@link InRange#minChar()} and {@link InRange#maxChar()}, if non-empty.
 * @param range annotation that gives the range's constraints
 */
public void configure(InRange range){
  min=range.min().isEmpty() ? range.minChar() : range.min().charAt(0);
  max=range.max().isEmpty() ? range.maxChar() : range.max().charAt(0);
}

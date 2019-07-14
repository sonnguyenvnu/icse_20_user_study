/** 
 * Append a field prefix which applies only to the next appended field. If the field is not printed, neither is the prefix.
 * @param prefix custom prefix
 * @return this PeriodFormatterBuilder
 * @see #appendSuffix
 */
private PeriodFormatterBuilder appendPrefix(PeriodFieldAffix prefix){
  if (prefix == null) {
    throw new IllegalArgumentException();
  }
  if (iPrefix != null) {
    prefix=new CompositeAffix(iPrefix,prefix);
  }
  iPrefix=prefix;
  return this;
}

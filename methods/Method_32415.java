/** 
 * Set the maximum digits parsed for the next and following appended fields. By default, the maximum digits parsed is ten.
 * @return this PeriodFormatterBuilder
 */
public PeriodFormatterBuilder maximumParsedDigits(int maxDigits){
  iMaxParsedDigits=maxDigits;
  return this;
}

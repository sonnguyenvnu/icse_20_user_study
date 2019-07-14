/** 
 * Set the minimum digits printed for the next and following appended fields. By default, the minimum digits printed is one. If the field value is zero, it is not printed unless a printZero rule is applied.
 * @return this PeriodFormatterBuilder
 */
public PeriodFormatterBuilder minimumPrintedDigits(int minDigits){
  iMinPrintedDigits=minDigits;
  return this;
}

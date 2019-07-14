/** 
 * The alternate ISO format, PyyyymmddThhmmss, which excludes weeks. <p> Even if weeks are present in the period, they are not output. Fractional seconds (milliseconds) will appear if required.
 * @return the formatter
 */
public static PeriodFormatter alternate(){
  if (cAlternate == null) {
    cAlternate=new PeriodFormatterBuilder().appendLiteral("P").printZeroAlways().minimumPrintedDigits(4).appendYears().minimumPrintedDigits(2).appendMonths().appendDays().appendSeparatorIfFieldsAfter("T").appendHours().appendMinutes().appendSecondsWithOptionalMillis().toFormatter();
  }
  return cAlternate;
}

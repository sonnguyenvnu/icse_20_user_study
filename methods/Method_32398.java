/** 
 * The alternate ISO format, Pyyyy-mm-ddThh:mm:ss, which excludes weeks. <p> Even if weeks are present in the period, they are not output. Fractional seconds (milliseconds) will appear if required.
 * @return the formatter
 */
public static PeriodFormatter alternateExtended(){
  if (cAlternateExtended == null) {
    cAlternateExtended=new PeriodFormatterBuilder().appendLiteral("P").printZeroAlways().minimumPrintedDigits(4).appendYears().appendSeparator("-").minimumPrintedDigits(2).appendMonths().appendSeparator("-").appendDays().appendSeparatorIfFieldsAfter("T").appendHours().appendSeparator(":").appendMinutes().appendSeparator(":").appendSecondsWithOptionalMillis().toFormatter();
  }
  return cAlternateExtended;
}

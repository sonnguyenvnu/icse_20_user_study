/** 
 * The alternate ISO format, PyyyyWwwddThhmmss, which excludes months. <p> Even if months are present in the period, they are not output. Fractional seconds (milliseconds) will appear if required.
 * @return the formatter
 */
public static PeriodFormatter alternateWithWeeks(){
  if (cAlternateWithWeeks == null) {
    cAlternateWithWeeks=new PeriodFormatterBuilder().appendLiteral("P").printZeroAlways().minimumPrintedDigits(4).appendYears().minimumPrintedDigits(2).appendPrefix("W").appendWeeks().appendDays().appendSeparatorIfFieldsAfter("T").appendHours().appendMinutes().appendSecondsWithOptionalMillis().toFormatter();
  }
  return cAlternateWithWeeks;
}

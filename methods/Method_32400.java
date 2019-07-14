/** 
 * The alternate ISO format, Pyyyy-Www-ddThh:mm:ss, which excludes months. <p> Even if months are present in the period, they are not output. Fractional seconds (milliseconds) will appear if required.
 * @return the formatter
 */
public static PeriodFormatter alternateExtendedWithWeeks(){
  if (cAlternateExtendedWihWeeks == null) {
    cAlternateExtendedWihWeeks=new PeriodFormatterBuilder().appendLiteral("P").printZeroAlways().minimumPrintedDigits(4).appendYears().appendSeparator("-").minimumPrintedDigits(2).appendPrefix("W").appendWeeks().appendSeparator("-").appendDays().appendSeparatorIfFieldsAfter("T").appendHours().appendSeparator(":").appendMinutes().appendSeparator(":").appendSecondsWithOptionalMillis().toFormatter();
  }
  return cAlternateExtendedWihWeeks;
}

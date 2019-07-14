/** 
 * The standard ISO format - PyYmMwWdDThHmMsS. Milliseconds are not output. Note that the ISO8601 standard actually indicates weeks should not be shown if any other field is present and vice versa.
 * @return the formatter
 */
public static PeriodFormatter standard(){
  if (cStandard == null) {
    cStandard=new PeriodFormatterBuilder().appendLiteral("P").appendYears().appendSuffix("Y").appendMonths().appendSuffix("M").appendWeeks().appendSuffix("W").appendDays().appendSuffix("D").appendSeparatorIfFieldsAfter("T").appendHours().appendSuffix("H").appendMinutes().appendSuffix("M").appendSecondsWithOptionalMillis().appendSuffix("S").toFormatter();
  }
  return cStandard;
}

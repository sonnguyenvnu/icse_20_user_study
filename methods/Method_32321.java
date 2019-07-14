/** 
 * Returns a new formatter that will use the specified default year. <p> The default year is used when parsing in the case where there is a month or a day but not a year. Specifically, it is used if there is a field parsed with a duration between the length of a month and the length of a day inclusive. <p> This value is typically used to move the year from 1970 to a leap year to enable February 29th to be parsed. Unless customised, the year 2000 is used. <p> This setting has no effect when printing.
 * @param defaultYear  the default year to use
 * @return the new formatter, not null
 * @since 2.0
 */
public DateTimeFormatter withDefaultYear(int defaultYear){
  return new DateTimeFormatter(iPrinter,iParser,iLocale,iOffsetParsed,iChrono,iZone,iPivotYear,defaultYear);
}

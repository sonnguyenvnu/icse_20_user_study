/** 
 * Returns a new formatter that will use the specified pivot year for two digit year parsing in preference to that stored in the parser. <p> This setting is useful for changing the pivot year of formats built using a pattern -  {@link DateTimeFormat#forPattern(String)}. <p> When parsing, this pivot year is used. Null means no-override. There is no effect when printing. <p> The pivot year enables a two digit year to be converted to a four digit year. The pivot represents the year in the middle of the supported range of years. Thus the full range of years that will be built is <code>(pivot - 50) .. (pivot + 49)</code>. <pre> pivot   supported range   00 is   20 is   40 is   60 is   80 is --------------------------------------------------------------- 1950      1900..1999      1900    1920    1940    1960    1980 1975      1925..2024      2000    2020    1940    1960    1980 2000      1950..2049      2000    2020    2040    1960    1980 2025      1975..2074      2000    2020    2040    2060    1980 2050      2000..2099      2000    2020    2040    2060    2080 </pre>
 * @param pivotYear  the pivot year to use as an override when parsing
 * @return the new formatter
 * @since 1.1
 */
public DateTimeFormatter withPivotYear(Integer pivotYear){
  if (iPivotYear == pivotYear || (iPivotYear != null && iPivotYear.equals(pivotYear))) {
    return this;
  }
  return new DateTimeFormatter(iPrinter,iParser,iLocale,iOffsetParsed,iChrono,iZone,pivotYear,iDefaultYear);
}

/** 
 * Instructs the printer to emit a numeric year field which always prints and parses two digits. A pivot year is used during parsing to determine the range of supported years as <code>(pivot - 50) .. (pivot + 49)</code>. <pre> pivot   supported range   00 is   20 is   40 is   60 is   80 is --------------------------------------------------------------- 1950      1900..1999      1900    1920    1940    1960    1980 1975      1925..2024      2000    2020    1940    1960    1980 2000      1950..2049      2000    2020    2040    1960    1980 2025      1975..2074      2000    2020    2040    2060    1980 2050      2000..2099      2000    2020    2040    2060    2080 </pre>
 * @param pivot  pivot year to use when parsing
 * @return this DateTimeFormatterBuilder, for chaining
 */
public DateTimeFormatterBuilder appendTwoDigitYear(int pivot){
  return appendTwoDigitYear(pivot,false);
}

/** 
 * Get the millis of day field type. <p> This measures the milliseconds from the start of the day on the <b>local</b> time-line. On a daylight saving date, this means that some values will be missed (in spring) or duplicated (in autumn/fall).
 * @return the DateTimeFieldType constant
 */
public static DateTimeFieldType millisOfDay(){
  return MILLIS_OF_DAY_TYPE;
}

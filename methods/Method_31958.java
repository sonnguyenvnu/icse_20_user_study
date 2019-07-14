/** 
 * Convert a datetime from one chronology to another.
 */
private static long convertByYear(long instant,Chronology from,Chronology to){
  return to.getDateTimeMillis(from.year().get(instant),from.monthOfYear().get(instant),from.dayOfMonth().get(instant),from.millisOfDay().get(instant));
}

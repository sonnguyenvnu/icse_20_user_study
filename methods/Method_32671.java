/** 
 * Constructs a TimeOfDay from the specified millis of day using the specified chronology. <p> The millisOfDay value may exceed the number of millis in one day, but additional days will be ignored. This method uses the UTC time zone internally.
 * @param millisOfDay  the number of milliseconds into a day to convert
 * @param chrono  the chronology, null means ISO chronology
 */
public static TimeOfDay fromMillisOfDay(long millisOfDay,Chronology chrono){
  chrono=DateTimeUtils.getChronology(chrono);
  chrono=chrono.withUTC();
  return new TimeOfDay(millisOfDay,chrono);
}

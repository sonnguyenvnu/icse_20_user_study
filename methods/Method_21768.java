/** 
 * Get a new instance set to today
 * @return CalendarDay set to today's date
 */
@NonNull public static CalendarDay today(){
  return from(LocalDate.now());
}

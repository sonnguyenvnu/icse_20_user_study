/** 
 * Convert a datetime from one chronology to another.
 */
private static long convertByWeekyear(final long instant,Chronology from,Chronology to){
  long newInstant;
  newInstant=to.weekyear().set(0,from.weekyear().get(instant));
  newInstant=to.weekOfWeekyear().set(newInstant,from.weekOfWeekyear().get(instant));
  newInstant=to.dayOfWeek().set(newInstant,from.dayOfWeek().get(instant));
  newInstant=to.millisOfDay().set(newInstant,from.millisOfDay().get(instant));
  return newInstant;
}

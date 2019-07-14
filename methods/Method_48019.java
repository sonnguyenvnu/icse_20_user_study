/** 
 * @return array with weekday names starting according to locale settings,e.g. [Mo,Di,Mi,Do,Fr,Sa,So] in Germany
 */
public static String[] getLocaleDayNames(int format){
  String[] days=new String[7];
  Calendar calendar=new GregorianCalendar();
  calendar.set(DAY_OF_WEEK,calendar.getFirstDayOfWeek());
  for (int i=0; i < days.length; i++) {
    days[i]=calendar.getDisplayName(DAY_OF_WEEK,format,getLocale());
    calendar.add(DAY_OF_MONTH,1);
  }
  return days;
}

/** 
 * @return array with week days numbers starting according to localesettings, e.g. [2,3,4,5,6,7,1] in Europe
 */
public static Integer[] getLocaleWeekdayList(){
  Integer[] dayNumbers=new Integer[7];
  Calendar calendar=new GregorianCalendar();
  calendar.set(DAY_OF_WEEK,calendar.getFirstDayOfWeek());
  for (int i=0; i < dayNumbers.length; i++) {
    dayNumbers[i]=calendar.get(DAY_OF_WEEK);
    calendar.add(DAY_OF_MONTH,1);
  }
  return dayNumbers;
}

public static Date addYear(Date date,int year){
  Calendar calendar=Calendar.getInstance();
  calendar.setTime(date);
  calendar.add(Calendar.DAY_OF_YEAR,365 * year);
  return calendar.getTime();
}

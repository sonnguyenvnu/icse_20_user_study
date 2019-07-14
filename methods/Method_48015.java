public static String formatHeaderDate(GregorianCalendar day){
  Locale locale=getLocale();
  String dayOfMonth=Integer.toString(day.get(DAY_OF_MONTH));
  String dayOfWeek=day.getDisplayName(DAY_OF_WEEK,SHORT,locale);
  return dayOfWeek + "\n" + dayOfMonth;
}

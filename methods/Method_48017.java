private static String[] getDayNames(int format){
  String[] wdays=new String[7];
  Calendar day=new GregorianCalendar();
  day.set(DAY_OF_WEEK,Calendar.SATURDAY);
  for (int i=0; i < wdays.length; i++) {
    wdays[i]=day.getDisplayName(DAY_OF_WEEK,format,getLocale());
    day.add(DAY_OF_MONTH,1);
  }
  return wdays;
}

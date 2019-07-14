public static int getEventType(){
  Calendar calendar=Calendar.getInstance();
  calendar.setTimeInMillis(System.currentTimeMillis());
  int monthOfYear=calendar.get(Calendar.MONTH);
  int dayOfMonth=calendar.get(Calendar.DAY_OF_MONTH);
  int minutes=calendar.get(Calendar.MINUTE);
  int hour=calendar.get(Calendar.HOUR_OF_DAY);
  int eventType=-1;
  if (monthOfYear == 11 && dayOfMonth >= 24 && dayOfMonth <= 31 || monthOfYear == 0 && dayOfMonth == 1) {
    eventType=0;
  }
  return eventType;
}

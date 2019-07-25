private static String format(boolean allDay,Date date){
  if (date == null) {
    return null;
  }
  DateFormat format=allDay ? DateFormat.getDateInstance(DateFormat.MEDIUM) : DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.MEDIUM);
  return format.format(date);
}

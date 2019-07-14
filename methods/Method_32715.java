static int parseDayOfWeek(String str){
  DateTimeField field=ISOChronology.getInstanceUTC().dayOfWeek();
  return field.get(field.set(0,str,Locale.ENGLISH));
}

static int parseMonth(String str){
  DateTimeField field=ISOChronology.getInstanceUTC().monthOfYear();
  return field.get(field.set(0,str,Locale.ENGLISH));
}

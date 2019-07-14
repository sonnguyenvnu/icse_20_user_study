private String formatCustom(){
  SimpleDateFormat dateFormat=new SimpleDateFormat(format);
  if (timezoneName != null) {
    TimeZone zone=TimeZone.getTimeZone(timezoneName);
    dateFormat.setTimeZone(zone);
  }
  return dateFormat.format(date);
}

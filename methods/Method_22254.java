/** 
 * Gets the timestamp of a report from its name
 * @param reportFileName Name of the report to get the timestamp from.
 * @return timestamp of the report
 */
@NonNull public Calendar getTimestamp(@NonNull String reportFileName){
  final String timestamp=reportFileName.replace(ACRAConstants.REPORTFILE_EXTENSION,"").replace(ACRAConstants.SILENT_SUFFIX,"");
  final Calendar calendar=Calendar.getInstance();
  try {
    calendar.setTime(new SimpleDateFormat(ACRAConstants.DATE_TIME_FORMAT_STRING,Locale.ENGLISH).parse(timestamp));
  }
 catch (  ParseException ignored) {
  }
  return calendar;
}

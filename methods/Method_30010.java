public String getDateText(Context context){
  LocalDate date=getDate();
  String datePattern=context.getString(R.string.calendar_date_pattern);
  return date.format(DateTimeFormatter.ofPattern(datePattern));
}

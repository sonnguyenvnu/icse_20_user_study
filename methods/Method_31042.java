public static String formatDate(LocalDate date,ZoneId zone,Context context){
  ZonedDateTime now=ZonedDateTime.now().withZoneSameInstant(zone);
  LocalDate nowDate=now.toLocalDate();
  if (date.equals(nowDate)) {
    return context.getString(R.string.today);
  }
  if (date.plusDays(1).equals(nowDate)) {
    return context.getString(R.string.yesterday);
  }
 else   if (date.getYear() == nowDate.getYear()) {
    return DateTimeFormatter.ofPattern(context.getString(R.string.month_day_pattern)).format(date);
  }
 else {
    return DateTimeFormatter.ofPattern(context.getString(R.string.date_pattern)).format(date);
  }
}

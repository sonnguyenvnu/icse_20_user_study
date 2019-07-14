public static String formatDateTime(ZonedDateTime dateTime,Context context){
  ZonedDateTime now=ZonedDateTime.now().withZoneSameInstant(dateTime.getZone());
  LocalDate date=dateTime.toLocalDate();
  LocalDate nowDate=now.toLocalDate();
  if (date.equals(nowDate)) {
    Duration duration=Duration.between(dateTime,now);
    if (duration.compareTo(Duration.ZERO) > 0) {
      if (duration.compareTo(JUST_NOW_DURATION) < 0) {
        return context.getString(R.string.just_now);
      }
 else       if (duration.compareTo(MINUTE_PATTERN_DURATION) < 0) {
        return context.getString(R.string.minute_format,Math.round((float)duration.getSeconds() / SECONDS_PER_MINUTE));
      }
 else       if (duration.compareTo(HOUR_PATTERN_DURATION) < 0) {
        return context.getString(R.string.hour_format,Math.round((float)duration.getSeconds() / SECONDS_PER_HOUR));
      }
    }
    return DateTimeFormatter.ofPattern(context.getString(R.string.today_hour_minute_pattern)).format(dateTime);
  }
  if (date.plusDays(1).equals(nowDate)) {
    return DateTimeFormatter.ofPattern(context.getString(R.string.yesterday_hour_minute_pattern)).format(dateTime);
  }
 else   if (date.getYear() == nowDate.getYear()) {
    return DateTimeFormatter.ofPattern(context.getString(R.string.month_day_hour_minute_pattern)).format(dateTime);
  }
 else {
    return DateTimeFormatter.ofPattern(context.getString(R.string.date_hour_minute_pattern)).format(dateTime);
  }
}

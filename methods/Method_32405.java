private static PeriodFormatter buildRegExFormatter(ResourceBundle b,Locale locale){
  String[] variants=retrieveVariants(b);
  String regExSeparator=b.getString("PeriodFormat.regex.separator");
  PeriodFormatterBuilder builder=new PeriodFormatterBuilder();
  builder.appendYears();
  if (containsKey(b,"PeriodFormat.years.regex")) {
    builder.appendSuffix(b.getString("PeriodFormat.years.regex").split(regExSeparator),b.getString("PeriodFormat.years.list").split(regExSeparator));
  }
 else {
    builder.appendSuffix(b.getString("PeriodFormat.year"),b.getString("PeriodFormat.years"));
  }
  builder.appendSeparator(b.getString("PeriodFormat.commaspace"),b.getString("PeriodFormat.spaceandspace"),variants);
  builder.appendMonths();
  if (containsKey(b,"PeriodFormat.months.regex")) {
    builder.appendSuffix(b.getString("PeriodFormat.months.regex").split(regExSeparator),b.getString("PeriodFormat.months.list").split(regExSeparator));
  }
 else {
    builder.appendSuffix(b.getString("PeriodFormat.month"),b.getString("PeriodFormat.months"));
  }
  builder.appendSeparator(b.getString("PeriodFormat.commaspace"),b.getString("PeriodFormat.spaceandspace"),variants);
  builder.appendWeeks();
  if (containsKey(b,"PeriodFormat.weeks.regex")) {
    builder.appendSuffix(b.getString("PeriodFormat.weeks.regex").split(regExSeparator),b.getString("PeriodFormat.weeks.list").split(regExSeparator));
  }
 else {
    builder.appendSuffix(b.getString("PeriodFormat.week"),b.getString("PeriodFormat.weeks"));
  }
  builder.appendSeparator(b.getString("PeriodFormat.commaspace"),b.getString("PeriodFormat.spaceandspace"),variants);
  builder.appendDays();
  if (containsKey(b,"PeriodFormat.days.regex")) {
    builder.appendSuffix(b.getString("PeriodFormat.days.regex").split(regExSeparator),b.getString("PeriodFormat.days.list").split(regExSeparator));
  }
 else {
    builder.appendSuffix(b.getString("PeriodFormat.day"),b.getString("PeriodFormat.days"));
  }
  builder.appendSeparator(b.getString("PeriodFormat.commaspace"),b.getString("PeriodFormat.spaceandspace"),variants);
  builder.appendHours();
  if (containsKey(b,"PeriodFormat.hours.regex")) {
    builder.appendSuffix(b.getString("PeriodFormat.hours.regex").split(regExSeparator),b.getString("PeriodFormat.hours.list").split(regExSeparator));
  }
 else {
    builder.appendSuffix(b.getString("PeriodFormat.hour"),b.getString("PeriodFormat.hours"));
  }
  builder.appendSeparator(b.getString("PeriodFormat.commaspace"),b.getString("PeriodFormat.spaceandspace"),variants);
  builder.appendMinutes();
  if (containsKey(b,"PeriodFormat.minutes.regex")) {
    builder.appendSuffix(b.getString("PeriodFormat.minutes.regex").split(regExSeparator),b.getString("PeriodFormat.minutes.list").split(regExSeparator));
  }
 else {
    builder.appendSuffix(b.getString("PeriodFormat.minute"),b.getString("PeriodFormat.minutes"));
  }
  builder.appendSeparator(b.getString("PeriodFormat.commaspace"),b.getString("PeriodFormat.spaceandspace"),variants);
  builder.appendSeconds();
  if (containsKey(b,"PeriodFormat.seconds.regex")) {
    builder.appendSuffix(b.getString("PeriodFormat.seconds.regex").split(regExSeparator),b.getString("PeriodFormat.seconds.list").split(regExSeparator));
  }
 else {
    builder.appendSuffix(b.getString("PeriodFormat.second"),b.getString("PeriodFormat.seconds"));
  }
  builder.appendSeparator(b.getString("PeriodFormat.commaspace"),b.getString("PeriodFormat.spaceandspace"),variants);
  builder.appendMillis();
  if (containsKey(b,"PeriodFormat.milliseconds.regex")) {
    builder.appendSuffix(b.getString("PeriodFormat.milliseconds.regex").split(regExSeparator),b.getString("PeriodFormat.milliseconds.list").split(regExSeparator));
  }
 else {
    builder.appendSuffix(b.getString("PeriodFormat.millisecond"),b.getString("PeriodFormat.milliseconds"));
  }
  return builder.toFormatter().withLocale(locale);
}

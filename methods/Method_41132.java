private static CronScheduleBuilder cronScheduleNoParseException(String presumedValidCronExpression){
  try {
    return cronSchedule(new CronExpression(presumedValidCronExpression));
  }
 catch (  ParseException e) {
    throw new RuntimeException("CronExpression '" + presumedValidCronExpression + "' is invalid, which should not be possible, please report bug to Quartz developers.",e);
  }
}

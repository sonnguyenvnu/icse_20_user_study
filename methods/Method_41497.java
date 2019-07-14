public void setCronExpression(String cronExpression) throws ParseException {
  TimeZone origTz=getTimeZone();
  this.cronEx=new CronExpression(cronExpression);
  this.cronEx.setTimeZone(origTz);
}

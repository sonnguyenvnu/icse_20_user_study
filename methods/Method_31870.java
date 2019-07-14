public long getDateTimeMillis(long instant,int hourOfDay,int minuteOfHour,int secondOfMinute,int millisOfSecond) throws IllegalArgumentException {
  Chronology base;
  if ((base=iBase) != null && (iBaseFlags & 1) == 1) {
    return base.getDateTimeMillis(instant,hourOfDay,minuteOfHour,secondOfMinute,millisOfSecond);
  }
  return super.getDateTimeMillis(instant,hourOfDay,minuteOfHour,secondOfMinute,millisOfSecond);
}

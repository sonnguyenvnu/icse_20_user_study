private static TimeZone getTimezone(){
  if (fixedTimeZone != null)   return fixedTimeZone;
  return TimeZone.getDefault();
}

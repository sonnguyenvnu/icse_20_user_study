public static long getLocalTime(){
  if (fixedLocalTime != null)   return fixedLocalTime;
  TimeZone tz=getTimezone();
  long now=new Date().getTime();
  return now + tz.getOffset(now);
}

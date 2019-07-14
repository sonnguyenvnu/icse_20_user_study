public static long removeTimezone(long timestamp){
  TimeZone tz=getTimezone();
  return timestamp + tz.getOffset(timestamp);
}

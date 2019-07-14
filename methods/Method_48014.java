public static long applyTimezone(long localTimestamp){
  TimeZone tz=getTimezone();
  return localTimestamp - tz.getOffset(localTimestamp - tz.getOffset(localTimestamp));
}

@Nonnull private static TimeUnit chooseUnit(long nanos){
  if (DAYS.convert(nanos,NANOSECONDS) > 0) {
    return DAYS;
  }
  if (HOURS.convert(nanos,NANOSECONDS) > 0) {
    return HOURS;
  }
  if (MINUTES.convert(nanos,NANOSECONDS) > 0) {
    return MINUTES;
  }
  if (SECONDS.convert(nanos,NANOSECONDS) > 0) {
    return SECONDS;
  }
  if (MILLISECONDS.convert(nanos,NANOSECONDS) > 0) {
    return MILLISECONDS;
  }
  if (MICROSECONDS.convert(nanos,NANOSECONDS) > 0) {
    return MICROSECONDS;
  }
  return NANOSECONDS;
}

private static Instant create(long seconds,int nanoOfSecond){
  if ((seconds | nanoOfSecond) == 0) {
    return EPOCH;
  }
  if (seconds < MIN_SECOND || seconds > MAX_SECOND) {
    throw new DateTimeException("Instant exceeds minimum or maximum instant");
  }
  return new Instant(seconds,nanoOfSecond);
}

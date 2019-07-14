private long expireTimeMS(){
  try {
    Duration duration=expiry.getExpiryForCreation();
    if (duration.isZero()) {
      return 0;
    }
 else     if (duration.isEternal()) {
      return Long.MAX_VALUE;
    }
    long millis=TimeUnit.NANOSECONDS.toMillis(ticker.read());
    return duration.getAdjustedTime(millis);
  }
 catch (  Exception e) {
    return Long.MAX_VALUE;
  }
}

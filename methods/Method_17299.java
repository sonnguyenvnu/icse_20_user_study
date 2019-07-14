/** 
 * Returns the time when the entry will expire.
 * @param created if the write is an insert or update
 * @return the time when the entry will expire, zero if it should expire immediately,Long.MIN_VALUE if it should not be changed, or Long.MAX_VALUE if eternal
 */
protected final long getWriteExpireTimeMS(boolean created){
  try {
    Duration duration=created ? expiry.getExpiryForCreation() : expiry.getExpiryForUpdate();
    if (duration == null) {
      return Long.MIN_VALUE;
    }
 else     if (duration.isZero()) {
      return 0L;
    }
 else     if (duration.isEternal()) {
      return Long.MAX_VALUE;
    }
    return duration.getAdjustedTime(currentTimeMillis());
  }
 catch (  Exception e) {
    logger.log(Level.WARNING,"Failed to get the policy's expiration time",e);
    return Long.MIN_VALUE;
  }
}

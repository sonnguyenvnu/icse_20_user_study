/** 
 * Sets the access expiration time.
 * @param expirable the entry that was operated on
 * @param currentTimeMS the current time, or 0 if not read yet
 */
protected final void setAccessExpirationTime(Expirable<?> expirable,long currentTimeMS){
  try {
    Duration duration=expiry.getExpiryForAccess();
    if (duration == null) {
      return;
    }
 else     if (duration.isZero()) {
      expirable.setExpireTimeMS(0L);
    }
 else     if (duration.isEternal()) {
      expirable.setExpireTimeMS(Long.MAX_VALUE);
    }
 else {
      if (currentTimeMS == 0L) {
        currentTimeMS=currentTimeMillis();
      }
      long expireTimeMS=duration.getAdjustedTime(currentTimeMS);
      expirable.setExpireTimeMS(expireTimeMS);
    }
  }
 catch (  Exception e) {
    logger.log(Level.WARNING,"Failed to set the entry's expiration time",e);
  }
}

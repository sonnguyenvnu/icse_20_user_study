private long getExpirationTime(){
  return System.currentTimeMillis() + cacheTimeMS;
}

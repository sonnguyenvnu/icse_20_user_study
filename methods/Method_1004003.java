@Override public Result get(final String key){
  if (!initOK) {
    return new Result(EXCEPTION_ID_IDCACHE_INIT_FALSE,Status.EXCEPTION);
  }
  if (cache.containsKey(key)) {
    SegmentBuffer buffer=cache.get(key);
    if (!buffer.isInitOk()) {
synchronized (buffer) {
        if (!buffer.isInitOk()) {
          try {
            updateSegmentFromDb(key,buffer.getCurrent());
            logger.info("Init buffer. Update leafkey {} {} from db",key,buffer.getCurrent());
            buffer.setInitOk(true);
          }
 catch (          Exception e) {
            logger.warn("Init buffer {} exception",buffer.getCurrent(),e);
          }
        }
      }
    }
    return getIdFromSegmentBuffer(cache.get(key));
  }
  return new Result(EXCEPTION_ID_KEY_NOT_EXISTS,Status.EXCEPTION);
}

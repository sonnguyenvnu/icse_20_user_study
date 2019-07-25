@Override public boolean clean(Long key){
  if (segments.isEmpty())   return false;
  if (segments.lastKey() < key)   return false;
  DelaySegment segment=segments.remove(key);
  if (null == segment) {
    LOGGER.error("clean delay segment log failed,segment:{}",logDir,key);
    return false;
  }
  if (!segment.destroy()) {
    LOGGER.warn("remove delay segment failed.segment:{}",segment);
    return false;
  }
  LOGGER.info("remove delay segment success.segment:{}",segment);
  return true;
}

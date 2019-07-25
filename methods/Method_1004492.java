public boolean clean(Long key){
  LogSegment segment=segments.get(key);
  if (null == segment) {
    LOG.error("clean message segment log error,segment:{} is null",key);
    return false;
  }
  if (deleteSegment(key,segment)) {
    LOG.info("remove expired segment success. segment: {}",segment);
    return true;
  }
 else {
    LOG.warn("remove expired segment failed. segment: {}",segment);
    return false;
  }
}

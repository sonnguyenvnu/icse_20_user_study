private static long _XXXXX_(String mainTimestamp,String sourceTimestamp){
  if (sourceTimestamp == null && mainTimestamp != null) {
    return convertTimestampToLong(mainTimestamp);
  }
  if (mainTimestamp == null && sourceTimestamp != null) {
    return convertTimestampToLong(sourceTimestamp);
  }
  if (sourceTimestamp == null && mainTimestamp == null) {
    return -1;
  }
  return _XXXXX_(convertTimestampToLong(mainTimestamp),convertTimestampToLong(sourceTimestamp));
}
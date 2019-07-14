private static long getElapsedTime(@Nullable Long startTime,long endTime){
  if (startTime != null) {
    return endTime - startTime;
  }
  return -1;
}

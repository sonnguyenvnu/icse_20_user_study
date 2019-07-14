private boolean isEmptyVibration(long[] pattern){
  if (pattern == null || pattern.length == 0) {
    return false;
  }
  for (int a=0; a < pattern.length; a++) {
    if (pattern[a] != 0) {
      return false;
    }
  }
  return true;
}

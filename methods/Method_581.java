public boolean scanISO8601DateIfMatch(boolean strict){
  int rest=len - bp;
  return scanISO8601DateIfMatch(strict,rest);
}

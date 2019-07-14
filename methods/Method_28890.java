private static Long processInteger(final RedisInputStream is){
  return is.readLongCrLf();
}

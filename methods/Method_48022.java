public static long getStartOfDay(long timestamp){
  return (timestamp / DAY_LENGTH) * DAY_LENGTH;
}

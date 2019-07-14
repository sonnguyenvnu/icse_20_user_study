public static long getStartOfToday(){
  return getStartOfDay(getLocalTime() - NEW_DAY_OFFSET * HOUR_LENGTH);
}

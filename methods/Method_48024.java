public static long millisecondsUntilTomorrow(){
  return getStartOfToday() + DAY_LENGTH - (getLocalTime() - NEW_DAY_OFFSET * HOUR_LENGTH);
}

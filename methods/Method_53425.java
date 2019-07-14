private String toTimeSpanString(int deltaInSeconds){
  if (deltaInSeconds <= 1) {
    return formats[NOW].format(null);
  }
 else   if (deltaInSeconds < 60) {
    return formats[N_SECONDS_AGO].format(new Object[]{deltaInSeconds});
  }
  if (deltaInSeconds < 45 * 60) {
    int minutes=deltaInSeconds / 60;
    if (minutes == 1) {
      return formats[A_MINUTE_AGO].format(null);
    }
    return formats[N_MINUTES_AGO].format(new Object[]{minutes});
  }
  if (deltaInSeconds < 105 * 60) {
    return formats[AN_HOUR_AGO].format(null);
  }
  int hours=((deltaInSeconds + 15 * 60) / ONE_HOUR_IN_SECONDS);
  return formats[N_HOURS_AGO].format(new Object[]{hours});
}

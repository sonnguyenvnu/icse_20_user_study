public String toTimeSpanString(long milliseconds){
  int deltaInSeconds=(int)((System.currentTimeMillis() - milliseconds) / 1000);
  if (deltaInSeconds >= ONE_DAY_IN_SECONDS) {
    if (deltaInSeconds >= ONE_MONTH_IN_SECONDS) {
      return dateMonthYear.format(new Date(milliseconds));
    }
 else {
      return dateMonth.format(new Date(milliseconds));
    }
  }
  return toTimeSpanString(deltaInSeconds);
}

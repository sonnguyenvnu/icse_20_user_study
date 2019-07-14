int getMonthOfYear(long millis){
  return (getDayOfYear(millis) - 1) / MONTH_LENGTH + 1;
}

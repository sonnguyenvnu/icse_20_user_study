int getDayOfMonth(long millis){
  return (getDayOfYear(millis) - 1) % MONTH_LENGTH + 1;
}

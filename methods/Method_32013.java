long calculateFirstDayOfYearMillis(int year){
  if (year > MAX_YEAR) {
    throw new ArithmeticException("Year is too large: " + year + " > " + MAX_YEAR);
  }
  if (year < MIN_YEAR) {
    throw new ArithmeticException("Year is too small: " + year + " < " + MIN_YEAR);
  }
  year--;
  long cycle=year / CYCLE;
  long millis=MILLIS_YEAR_1 + cycle * MILLIS_PER_CYCLE;
  int cycleRemainder=(year % CYCLE) + 1;
  for (int i=1; i < cycleRemainder; i++) {
    millis+=(isLeapYear(i) ? MILLIS_PER_LONG_YEAR : MILLIS_PER_SHORT_YEAR);
  }
  return millis;
}

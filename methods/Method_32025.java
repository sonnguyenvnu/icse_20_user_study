long calculateFirstDayOfYearMillis(int year){
  int relativeYear=year - 1968;
  int leapYears;
  if (relativeYear <= 0) {
    leapYears=(relativeYear + 3) >> 2;
  }
 else {
    leapYears=relativeYear >> 2;
    if (!isLeapYear(year)) {
      leapYears++;
    }
  }
  long millis=(relativeYear * 365L + leapYears) * (long)DateTimeConstants.MILLIS_PER_DAY;
  return millis - (366L + 352) * DateTimeConstants.MILLIS_PER_DAY;
}

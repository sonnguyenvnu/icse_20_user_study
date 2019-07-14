long calculateFirstDayOfYearMillis(int year){
  int leapYears=year / 100;
  if (year < 0) {
    leapYears=((year + 3) >> 2) - leapYears + ((leapYears + 3) >> 2) - 1;
  }
 else {
    leapYears=(year >> 2) - leapYears + (leapYears >> 2);
    if (isLeapYear(year)) {
      leapYears--;
    }
  }
  return (year * 365L + (leapYears - DAYS_0000_TO_1970)) * DateTimeConstants.MILLIS_PER_DAY;
}

long getTotalMillisByYearMonth(int year,int month){
  if (--month % 2 == 1) {
    month/=2;
    return month * MILLIS_PER_MONTH_PAIR + MILLIS_PER_LONG_MONTH;
  }
 else {
    month/=2;
    return month * MILLIS_PER_MONTH_PAIR;
  }
}

int getDaysInMonthMax(int month){
  if (month == 12) {
    return LONG_MONTH_LENGTH;
  }
  return (--month % 2 == 0 ? LONG_MONTH_LENGTH : SHORT_MONTH_LENGTH);
}

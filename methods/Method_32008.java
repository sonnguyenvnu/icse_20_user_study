int getDayOfMonth(long millis){
  int doy=getDayOfYear(millis) - 1;
  if (doy == 354) {
    return 30;
  }
  return (doy % MONTH_PAIR_LENGTH) % LONG_MONTH_LENGTH + 1;
}

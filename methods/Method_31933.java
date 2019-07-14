public int getLeapAmount(long instant){
  return iChronology.getWeeksInYear(iChronology.getWeekyear(instant)) - 52;
}

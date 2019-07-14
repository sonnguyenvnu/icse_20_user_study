public int getLeapAmount(long instant){
  if (iChronology.isLeapYear(get(instant))) {
    return 1;
  }
 else {
    return 0;
  }
}

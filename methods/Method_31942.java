public long roundCeiling(long instant){
  int year=get(instant);
  long yearStartMillis=iChronology.getYearMillis(year);
  if (instant != yearStartMillis) {
    instant=iChronology.getYearMillis(year + 1);
  }
  return instant;
}

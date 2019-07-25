private DayAsDate max(DayAsDate d1,DayAsDate d2){
  if (d1 == null) {
    return d2;
  }
  if (d1.compareTo(d2) < 0) {
    return d2;
  }
  return d1;
}

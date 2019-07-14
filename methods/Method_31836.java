private void adjustForMinMax(){
  if (iMillis == Long.MIN_VALUE || iMillis == Long.MAX_VALUE) {
    iChronology=iChronology.withUTC();
  }
}

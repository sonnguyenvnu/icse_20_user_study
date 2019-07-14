/** 
 * Determines the correct chronology to use.
 * @param chrono  the proposed chronology
 * @return the actual chronology
 */
private Chronology selectChronology(Chronology chrono){
  chrono=DateTimeUtils.getChronology(chrono);
  if (iChrono != null) {
    chrono=iChrono;
  }
  if (iZone != null) {
    chrono=chrono.withZone(iZone);
  }
  return chrono;
}

protected void setupSelection(@ShowOtherDates int showOtherDates,boolean inRange,boolean inMonth){
  this.showOtherDates=showOtherDates;
  this.isInMonth=inMonth;
  this.isInRange=inRange;
  setEnabled();
}

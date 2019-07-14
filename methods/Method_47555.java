@Override public void onYearSelected(int year){
  adjustDayInMonthIfNeeded(mCalendar.get(Calendar.MONTH),year);
  mCalendar.set(Calendar.YEAR,year);
  updatePickers();
  setCurrentView(MONTH_AND_DAY_VIEW);
  updateDisplay(true);
}

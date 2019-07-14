@Override public void onDayOfMonthSelected(int year,int month,int day){
  mCalendar.set(Calendar.YEAR,year);
  mCalendar.set(Calendar.MONTH,month);
  mCalendar.set(Calendar.DAY_OF_MONTH,day);
  updatePickers();
  updateDisplay(true);
}

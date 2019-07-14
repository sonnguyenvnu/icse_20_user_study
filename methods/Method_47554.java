private void adjustDayInMonthIfNeeded(int month,int year){
  int day=mCalendar.get(Calendar.DAY_OF_MONTH);
  int daysInMonth=Utils.getDaysInMonth(month,year);
  if (day > daysInMonth) {
    mCalendar.set(Calendar.DAY_OF_MONTH,daysInMonth);
  }
}

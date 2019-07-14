private void setRangeDates(CalendarDay min,CalendarDay max){
  CalendarDay c=currentMonth;
  adapter.setRangeDates(min,max);
  currentMonth=c;
  if (min != null) {
    currentMonth=min.isAfter(currentMonth) ? min : currentMonth;
  }
  int position=adapter.getIndexForDay(c);
  pager.setCurrentItem(position,false);
  updateUi();
}

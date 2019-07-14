protected void updateUi(){
  for (  DayView dayView : dayViews) {
    CalendarDay day=dayView.getDate();
    dayView.setupSelection(showOtherDates,day.isInRange(minDate,maxDate),isDayEnabled(day));
  }
  postInvalidate();
}

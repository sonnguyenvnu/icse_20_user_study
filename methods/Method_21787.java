public void setSelectedDates(Collection<CalendarDay> dates){
  for (  DayView dayView : dayViews) {
    CalendarDay day=dayView.getDate();
    dayView.setChecked(dates != null && dates.contains(day));
  }
  postInvalidate();
}

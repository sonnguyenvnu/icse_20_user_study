public void setRangeDates(CalendarDay min,CalendarDay max){
  this.minDate=min;
  this.maxDate=max;
  for (  V pagerView : currentViews) {
    pagerView.setMinimumDate(min);
    pagerView.setMaximumDate(max);
  }
  if (min == null) {
    min=CalendarDay.from(today.getYear() - 200,today.getMonth(),today.getDay());
  }
  if (max == null) {
    max=CalendarDay.from(today.getYear() + 200,today.getMonth(),today.getDay());
  }
  rangeIndex=createRangeIndex(min,max);
  notifyDataSetChanged();
  invalidateSelectedDates();
}

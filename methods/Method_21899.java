@Override public void onRangeSelected(@NonNull final MaterialCalendarView widget,@NonNull final List<CalendarDay> dates){
  if (dates.size() > 0) {
    decorator.addFirstAndLast(dates.get(0),dates.get(dates.size() - 1));
    range.invalidateDecorators();
  }
}

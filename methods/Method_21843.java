@Override protected DateRangeIndex createRangeIndex(CalendarDay min,CalendarDay max){
  return new Monthly(min,max);
}

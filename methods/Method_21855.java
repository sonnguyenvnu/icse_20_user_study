@Override protected DateRangeIndex createRangeIndex(CalendarDay min,CalendarDay max){
  return new Weekly(min,max,mcv.getFirstDayOfWeek());
}

@Override protected boolean isDayEnabled(final CalendarDay day){
  return day.getMonth() == getFirstViewDay().getMonth();
}

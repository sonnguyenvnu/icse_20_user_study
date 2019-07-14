@Override protected int indexOf(WeekView view){
  CalendarDay week=view.getFirstViewDay();
  return getRangeIndex().indexOf(week);
}

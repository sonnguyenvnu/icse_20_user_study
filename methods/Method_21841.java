@Override protected int indexOf(MonthView view){
  CalendarDay month=view.getMonth();
  return getRangeIndex().indexOf(month);
}

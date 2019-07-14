@Override public void onMonthChanged(MaterialCalendarView widget,CalendarDay date){
  getSupportActionBar().setTitle(FORMATTER.format(date.getDate()));
}

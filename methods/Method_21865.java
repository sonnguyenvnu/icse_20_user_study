@Override public boolean shouldDecorate(final CalendarDay day){
  final DayOfWeek weekDay=day.getDate().getDayOfWeek();
  return weekDay == DayOfWeek.SATURDAY || weekDay == DayOfWeek.SUNDAY;
}

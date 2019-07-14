private int getWeekCountBasedOnMode(){
  int weekCount=calendarMode.visibleWeeksCount;
  final boolean isInMonthsMode=calendarMode.equals(CalendarMode.MONTHS);
  if (isInMonthsMode && mDynamicHeightEnabled && adapter != null && pager != null) {
    final LocalDate cal=adapter.getItem(pager.getCurrentItem()).getDate();
    final LocalDate tempLastDay=cal.withDayOfMonth(cal.lengthOfMonth());
    weekCount=tempLastDay.get(WeekFields.of(firstDayOfWeek,1).weekOfMonth());
  }
  return showWeekDays ? weekCount + DAY_NAMES_ROW : weekCount;
}

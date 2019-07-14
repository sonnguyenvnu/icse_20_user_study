private int getWeekOfMonth(OffsetDateTime offsetDateTime){
  LocalDate date=offsetDateTime.toLocalDate();
  DayOfWeek firstDayOfWeek=DayOfWeek.SUNDAY;
  int minDays=1;
  WeekFields week=WeekFields.of(firstDayOfWeek,minDays);
  TemporalField womField=week.weekOfMonth();
  return date.get(womField);
}

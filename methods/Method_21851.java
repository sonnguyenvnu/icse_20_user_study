public void setWeekDayFormatter(@Nullable final WeekDayFormatter formatter){
  this.formatter=formatter == null ? WeekDayFormatter.DEFAULT : formatter;
  setDayOfWeek(dayOfWeek);
}

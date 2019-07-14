protected LocalDate resetAndGetWorkingCalendar(){
  final TemporalField firstDayOfWeek=WeekFields.of(this.firstDayOfWeek,1).dayOfWeek();
  final LocalDate temp=getFirstViewDay().getDate().with(firstDayOfWeek,1);
  int dow=temp.getDayOfWeek().getValue();
  int delta=getFirstDayOfWeek().getValue() - dow;
  boolean removeRow=showOtherMonths(showOtherDates) ? delta >= 0 : delta > 0;
  if (removeRow) {
    delta-=DEFAULT_DAYS_IN_WEEK;
  }
  return temp.plusDays(delta);
}

@Override protected void buildDayViews(final Collection<DayView> dayViews,final LocalDate calendar){
  LocalDate temp=calendar;
  for (int r=0; r < DEFAULT_MAX_WEEKS; r++) {
    for (int i=0; i < DEFAULT_DAYS_IN_WEEK; i++) {
      addDayView(dayViews,temp);
      temp=temp.plusDays(1);
    }
  }
}

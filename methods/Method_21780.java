private void buildWeekDays(LocalDate calendar){
  LocalDate local=calendar;
  for (int i=0; i < DEFAULT_DAYS_IN_WEEK; i++) {
    WeekDayView weekDayView=new WeekDayView(getContext(),local.getDayOfWeek());
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
      weekDayView.setImportantForAccessibility(View.IMPORTANT_FOR_ACCESSIBILITY_NO);
    }
    weekDayViews.add(weekDayView);
    addView(weekDayView);
    local=local.plusDays(1);
  }
}

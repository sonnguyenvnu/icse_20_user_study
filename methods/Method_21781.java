protected void addDayView(Collection<DayView> dayViews,LocalDate temp){
  CalendarDay day=CalendarDay.from(temp);
  DayView dayView=new DayView(getContext(),day);
  dayView.setOnClickListener(this);
  dayView.setOnLongClickListener(this);
  dayViews.add(dayView);
  addView(dayView,new LayoutParams());
}

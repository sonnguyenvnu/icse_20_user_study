@OnClick(R.id.button_set_first_day) void onFirstDayOfWeekClicked(){
  int index=random.nextInt(DAYS_OF_WEEK.length);
  widget.state().edit().setFirstDayOfWeek(DAYS_OF_WEEK[index]).commit();
}

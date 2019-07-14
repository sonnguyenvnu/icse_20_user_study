@OnCheckedChanged(R.id.calendar_mode) void onCalendarModeChanged(boolean checked){
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
    TransitionManager.beginDelayedTransition(parent);
  }
  final CalendarMode mode=checked ? CalendarMode.WEEKS : CalendarMode.MONTHS;
  single.state().edit().setCalendarDisplayMode(mode).commit();
  multi.state().edit().setCalendarDisplayMode(mode).commit();
  range.state().edit().setCalendarDisplayMode(mode).commit();
  none.state().edit().setCalendarDisplayMode(mode).commit();
}

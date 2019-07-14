@OnCheckedChanged(R.id.calendar_mode) void onCalendarModeChanged(boolean checked){
  final CalendarMode mode=checked ? CalendarMode.WEEKS : CalendarMode.MONTHS;
  widget.state().edit().setCalendarDisplayMode(mode).commit();
}

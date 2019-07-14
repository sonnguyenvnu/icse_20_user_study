@OnCheckedChanged(R.id.show_week_days) void onShowWeekDaysChecked(boolean checked){
  widget.state().edit().setShowWeekDays(checked).commit();
}

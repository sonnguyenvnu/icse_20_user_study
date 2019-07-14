@OnCheckedChanged(R.id.enable_save_current_position) void onSaveCurrentPositionChecked(boolean checked){
  widget.state().edit().isCacheCalendarPositionEnabled(checked).commit();
}

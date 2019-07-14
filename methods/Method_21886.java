@OnClick(R.id.button_weeks) public void onSetWeekMode(){
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && animateModeTransition.isChecked()) {
    TransitionManager.beginDelayedTransition(parent);
  }
  widget.state().edit().setCalendarDisplayMode(CalendarMode.WEEKS).commit();
}

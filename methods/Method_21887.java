@OnClick(R.id.button_months) public void onSetMonthMode(){
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && animateModeTransition.isChecked()) {
    TransitionManager.beginDelayedTransition(parent);
  }
  widget.state().edit().setCalendarDisplayMode(CalendarMode.MONTHS).commit();
}

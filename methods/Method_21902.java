@OnClick(R.id.button_weeks) public void onSetWeekMode(){
  widget.state().edit().setCalendarDisplayMode(CalendarMode.WEEKS).commit();
}

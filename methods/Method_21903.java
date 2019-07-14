@OnClick(R.id.button_months) public void onSetMonthMode(){
  widget.state().edit().setCalendarDisplayMode(CalendarMode.MONTHS).commit();
}

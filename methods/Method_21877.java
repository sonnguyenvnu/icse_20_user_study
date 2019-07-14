@OnClick(R.id.button_max_date) void onMaxClicked(){
  showDatePickerDialog(this,widget.getMaximumDate(),(view,year,monthOfYear,dayOfMonth) -> widget.state().edit().setMaximumDate(CalendarDay.from(year,monthOfYear + 1,dayOfMonth)).commit());
}

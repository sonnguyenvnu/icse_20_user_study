@OnClick(R.id.button_min_date) void onMinClicked(){
  showDatePickerDialog(this,widget.getMinimumDate(),(view,year,monthOfYear,dayOfMonth) -> widget.state().edit().setMinimumDate(CalendarDay.from(year,monthOfYear + 1,dayOfMonth)).commit());
}

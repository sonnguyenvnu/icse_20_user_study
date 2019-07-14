@OnClick(R.id.button_selected_date) void onSelectedClicked(){
  showDatePickerDialog(this,widget.getSelectedDate(),(view,year,monthOfYear,dayOfMonth) -> widget.setSelectedDate(CalendarDay.from(year,monthOfYear + 1,dayOfMonth)));
}

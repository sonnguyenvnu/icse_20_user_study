@OnCheckedChanged(R.id.check_text_appearance) void onTextAppearanceChecked(boolean checked){
  if (checked) {
    widget.setHeaderTextAppearance(R.style.TextAppearance_AppCompat_Large);
    widget.setDateTextAppearance(R.style.TextAppearance_AppCompat_Medium);
    widget.setWeekDayTextAppearance(R.style.TextAppearance_AppCompat_Medium);
  }
 else {
    widget.setHeaderTextAppearance(R.style.TextAppearance_MaterialCalendarWidget_Header);
    widget.setDateTextAppearance(R.style.TextAppearance_MaterialCalendarWidget_Date);
    widget.setWeekDayTextAppearance(R.style.TextAppearance_MaterialCalendarWidget_WeekDay);
  }
  widget.setShowOtherDates(checked ? MaterialCalendarView.SHOW_ALL : MaterialCalendarView.SHOW_NONE);
}

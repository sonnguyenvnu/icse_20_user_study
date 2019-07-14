@OnClick(R.id.get_selected_dates) public void getSelectedDateClick(final View v){
  final List<CalendarDay> selectedDates=widget.getSelectedDates();
  if (!selectedDates.isEmpty()) {
    Toast.makeText(this,selectedDates.toString(),Toast.LENGTH_SHORT).show();
    Log.e("GettersActivity",selectedDates.toString());
  }
 else {
    Toast.makeText(this,"No Selection",Toast.LENGTH_SHORT).show();
  }
}

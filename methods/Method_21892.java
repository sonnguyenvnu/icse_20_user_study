@OnClick(R.id.get_selected_date) public void getSelectedDatesClick(final View v){
  final CalendarDay selectedDate=widget.getSelectedDate();
  if (selectedDate != null) {
    Toast.makeText(this,selectedDate.toString(),Toast.LENGTH_SHORT).show();
    Log.e("GettersActivity",selectedDate.toString());
  }
 else {
    Toast.makeText(this,"No Selection",Toast.LENGTH_SHORT).show();
  }
}

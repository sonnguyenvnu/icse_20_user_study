@Override public void onDateSet(long date){
  if (date > 0) {
    dueOnEditText.setText(ParseDateFormat.prettifyDate(date));
  }
}

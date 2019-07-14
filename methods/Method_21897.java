@Override public void onSelectedDayChange(CalendarView view,int year,int month,int dayOfMonth){
  textView.setText(FORMATTER.format(view.getDate()));
}

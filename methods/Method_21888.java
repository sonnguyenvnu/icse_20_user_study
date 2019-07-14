@Override public void onDateLongClick(@NonNull MaterialCalendarView widget,@NonNull CalendarDay date){
  Toast.makeText(this,FORMATTER.format(date.getDate()),Toast.LENGTH_SHORT).show();
}

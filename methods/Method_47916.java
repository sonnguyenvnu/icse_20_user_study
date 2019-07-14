public int getWeekday(){
  return toCalendar().get(DAY_OF_WEEK) % 7;
}

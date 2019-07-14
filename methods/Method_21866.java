@Override public boolean shouldDecorate(CalendarDay day){
  return date != null && day.equals(date);
}

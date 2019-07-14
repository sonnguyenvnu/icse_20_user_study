public int getIndexForDay(CalendarDay day){
  if (day == null) {
    return getCount() / 2;
  }
  if (minDate != null && day.isBefore(minDate)) {
    return 0;
  }
  if (maxDate != null && day.isAfter(maxDate)) {
    return getCount() - 1;
  }
  return rangeIndex.indexOf(day);
}

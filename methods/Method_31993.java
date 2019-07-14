public int dayOfWeekTextToValue(String text){
  Integer day=iParseDaysOfWeek.get(text);
  if (day != null) {
    return day.intValue();
  }
  throw new IllegalFieldValueException(DateTimeFieldType.dayOfWeek(),text);
}

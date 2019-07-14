static int adjustYearForSet(int year){
  if (year <= 0) {
    if (year == 0) {
      throw new IllegalFieldValueException(DateTimeFieldType.year(),Integer.valueOf(year),null,null);
    }
    year++;
  }
  return year;
}

public static Optional<Date> parse(Optional<String> yearValue,Optional<String> monthValue,Optional<String> dayValue){
  Optional<Year> year=yearValue.flatMap(Date::convertToInt).map(Year::of);
  Optional<Month> month=monthValue.flatMap(Month::parse);
  Optional<Integer> day=dayValue.flatMap(Date::convertToInt);
  if (year.isPresent()) {
    TemporalAccessor date;
    if (month.isPresent()) {
      if (day.isPresent()) {
        date=LocalDate.of(year.get().getValue(),month.get().getNumber(),day.get());
      }
 else {
        date=YearMonth.of(year.get().getValue(),month.get().getNumber());
      }
    }
 else {
      date=year.get();
    }
    return Optional.of(new Date(date));
  }
  return Optional.empty();
}

private String formatYear(YearMonth yearMonth){
  try {
    Chronology chrono=getPrimaryChronology();
    ChronoLocalDate cDate=chrono.date(yearMonth.atDay(1));
    return yearFormatter.withLocale(getLocale()).withChronology(chrono).withDecimalStyle(DecimalStyle.of(getLocale())).format(cDate);
  }
 catch (  DateTimeException ex) {
    return "";
  }
}

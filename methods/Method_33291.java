private String formatMonth(YearMonth yearMonth){
  try {
    Chronology chrono=getPrimaryChronology();
    ChronoLocalDate cDate=chrono.date(yearMonth.atDay(1));
    return monthFormatter.withLocale(getLocale()).withChronology(chrono).format(cDate);
  }
 catch (  DateTimeException ex) {
    return "";
  }
}

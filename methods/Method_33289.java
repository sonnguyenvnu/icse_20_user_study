void updateWeekNumberDateCells(){
  if (datePicker.isShowWeekNumbers()) {
    final Locale locale=getLocale();
    LocalDate firstDayOfMonth=selectedYearMonth.get().atDay(1);
    for (int i=0; i < 6; i++) {
      LocalDate date=firstDayOfMonth.plus(i,WEEKS);
      String weekNumber=weekNumberFormatter.withLocale(locale).withDecimalStyle(DecimalStyle.of(locale)).format(date);
      weekNumberCells.get(i).setText(weekNumber);
    }
  }
}

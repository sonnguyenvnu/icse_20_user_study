protected void updateMonthYearPane(){
  YearMonth yearMonth=selectedYearMonth.get();
  LocalDate value=datePicker.getValue();
  value=value == null ? LocalDate.now() : value;
  selectedDateLabel.setText(DateTimeFormatter.ofPattern("EEE, MMM dd").format(value));
  selectedYearLabel.setText(formatYear(yearMonth));
  monthYearLabel.setText(formatMonth(yearMonth) + " " + formatYear(yearMonth));
  Chronology chrono=datePicker.getChronology();
  LocalDate firstDayOfMonth=yearMonth.atDay(1);
  backMonthButton.setDisable(!isValidDate(chrono,firstDayOfMonth,-1,DAYS));
  forwardMonthButton.setDisable(!isValidDate(chrono,firstDayOfMonth,+1,MONTHS));
}

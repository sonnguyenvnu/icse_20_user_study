void clearFocus(){
  LocalDate focusDate=datePicker.getValue();
  if (focusDate == null) {
    focusDate=LocalDate.now();
  }
  if (YearMonth.from(focusDate).equals(selectedYearMonth.get())) {
    goToDate(focusDate,true);
  }
}

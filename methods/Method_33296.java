private void goToDate(LocalDate date,boolean focusDayCell){
  if (isValidDate(datePicker.getChronology(),date)) {
    selectedYearMonth.set(YearMonth.from(date));
    if (focusDayCell) {
      findDayCellOfDate(date).requestFocus();
    }
  }
}

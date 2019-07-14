private void goToDayCell(DateCell dateCell,int offset,ChronoUnit unit,boolean focusDayCell){
  YearMonth yearMonth=selectedYearMonth.get().plus(offset,unit);
  goToDate(dayCellDate(dateCell).plus(offset,unit).withYear(yearMonth.getYear()),focusDayCell);
}

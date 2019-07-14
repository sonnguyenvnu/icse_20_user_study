protected LocalDate dayCellDate(DateCell dateCell){
  assert dayCellDates != null;
  return dayCellDates[dayCells.indexOf(dateCell)];
}

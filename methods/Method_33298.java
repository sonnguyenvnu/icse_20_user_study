private DateCell findDayCellOfDate(LocalDate date){
  for (int i=0; i < dayCellDates.length; i++) {
    if (date.equals(dayCellDates[i])) {
      return dayCells.get(i);
    }
  }
  return dayCells.get(dayCells.size() / 2 + 1);
}

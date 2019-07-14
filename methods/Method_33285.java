void updateContentGrid(){
  contentGrid.getColumnConstraints().clear();
  contentGrid.getChildren().clear();
  int colsNumber=daysPerWeek + (datePicker.isShowWeekNumbers() ? 1 : 0);
  ColumnConstraints columnConstraints=new ColumnConstraints();
  columnConstraints.setPercentWidth(100);
  for (int i=0; i < colsNumber; i++) {
    contentGrid.getColumnConstraints().add(columnConstraints);
  }
  for (int i=0; i < daysPerWeek; i++) {
    contentGrid.add(weekDaysCells.get(i),i + colsNumber - daysPerWeek,1);
  }
  if (datePicker.isShowWeekNumbers()) {
    for (int i=0; i < 6; i++) {
      contentGrid.add(weekNumberCells.get(i),0,i + 2);
    }
  }
  for (int row=0; row < 6; row++) {
    for (int col=0; col < daysPerWeek; col++) {
      contentGrid.add(dayCells.get(row * daysPerWeek + col),col + colsNumber - daysPerWeek,row + 2);
    }
  }
}

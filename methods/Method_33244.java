private void buildCustomColors(){
  final ObservableList<Color> customColors=colorPicker.getCustomColors();
  customColorGrid.getChildren().clear();
  if (customColors.isEmpty()) {
    customColorLabel.setVisible(false);
    customColorLabel.setManaged(false);
    customColorGrid.setVisible(false);
    customColorGrid.setManaged(false);
    return;
  }
 else {
    customColorLabel.setVisible(true);
    customColorLabel.setManaged(true);
    customColorGrid.setVisible(true);
    customColorGrid.setManaged(true);
  }
  int customColumnIndex=0;
  int customRowIndex=0;
  int remainingSquares=customColors.size() % NUM_OF_COLUMNS;
  int numEmpty=(remainingSquares == 0) ? 0 : NUM_OF_COLUMNS - remainingSquares;
  for (int i=0; i < customColors.size(); i++) {
    Color c=customColors.get(i);
    ColorSquare square=new ColorSquare(c,i,true);
    customColorGrid.add(square,customColumnIndex,customRowIndex);
    customColumnIndex++;
    if (customColumnIndex == NUM_OF_COLUMNS) {
      customColumnIndex=0;
      customRowIndex++;
    }
  }
  for (int i=0; i < numEmpty; i++) {
    ColorSquare emptySquare=new ColorSquare();
    customColorGrid.add(emptySquare,customColumnIndex,customRowIndex);
    customColumnIndex++;
  }
  requestLayout();
}

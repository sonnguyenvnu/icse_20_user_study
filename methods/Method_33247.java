public void updateSelection(Color color){
  setFocusedSquare(null);
  for (  ColorSquare c : colorPickerGrid.getSquares()) {
    if (c.rectangle.getFill().equals(color)) {
      setFocusedSquare(c);
      return;
    }
  }
  for (  Node n : customColorGrid.getChildren()) {
    ColorSquare c=(ColorSquare)n;
    if (c.rectangle.getFill().equals(color)) {
      setFocusedSquare(c);
      return;
    }
  }
}

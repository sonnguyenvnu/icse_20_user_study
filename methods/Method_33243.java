private void setFocusedSquare(ColorSquare square){
  hoverSquare.setVisible(square != null);
  if (square == focusedSquare) {
    return;
  }
  focusedSquare=square;
  hoverSquare.setVisible(focusedSquare != null);
  if (focusedSquare == null) {
    return;
  }
  if (!focusedSquare.isFocused()) {
    focusedSquare.requestFocus();
  }
  hoverSquare.rectangle.setFill(focusedSquare.rectangle.getFill());
  Bounds b=square.localToScene(square.getLayoutBounds());
  double x=b.getMinX();
  double y=b.getMinY();
  double xAdjust;
  double scaleAdjust=hoverSquare.getScaleX() == 1.0 ? 0 : hoverSquare.getWidth() / 4.0;
  if (colorPicker.getEffectiveNodeOrientation() == NodeOrientation.RIGHT_TO_LEFT) {
    x=focusedSquare.getLayoutX();
    xAdjust=-focusedSquare.getWidth() + scaleAdjust;
  }
 else {
    xAdjust=focusedSquare.getWidth() / 2.0 + scaleAdjust;
  }
  hoverSquare.setLayoutX(snapPosition(x) - xAdjust);
  hoverSquare.setLayoutY(snapPosition(y) - focusedSquare.getHeight() / 2.0 + (hoverSquare.getScaleY() == 1.0 ? 0 : focusedSquare.getHeight() / 4.0));
}

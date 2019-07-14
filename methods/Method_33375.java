public void updateFocusColor(){
  Paint paint=control.getFocusColor();
  focusedLine.setBackground(paint == null ? Background.EMPTY : new Background(new BackgroundFill(paint,CornerRadii.EMPTY,Insets.EMPTY)));
}

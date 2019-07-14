public void updateUnfocusColor(){
  Paint paint=control.getUnFocusColor();
  line.setBackground(paint == null ? Background.EMPTY : new Background(new BackgroundFill(paint,CornerRadii.EMPTY,Insets.EMPTY)));
}

@Override protected double computePrefHeight(double width){
  if (!getChildren().isEmpty()) {
    return getChildren().get(0).prefHeight(width);
  }
  return super.computePrefHeight(width);
}

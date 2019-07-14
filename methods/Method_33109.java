@Override protected double computePrefWidth(double height){
  if (!getChildren().isEmpty()) {
    return getChildren().get(0).prefWidth(height);
  }
  return super.computePrefWidth(height);
}

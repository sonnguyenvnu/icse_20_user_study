private void updateValueStyleClass(){
  getSkinnable().pseudoClassStateChanged(MIN_VALUE,getSkinnable().getMin() == getSkinnable().getValue());
  getSkinnable().pseudoClassStateChanged(MAX_VALUE,getSkinnable().getMax() == getSkinnable().getValue());
}

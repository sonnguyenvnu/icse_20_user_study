private double computeErrorHeight(double errorContainerWidth){
  return errorLabel.prefHeight(errorContainerWidth) + snappedBottomInset() + snappedTopInset();
}

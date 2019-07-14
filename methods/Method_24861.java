public void handleHasJavaTabsChange(boolean hasJavaTabs){
  isEnabled=!hasJavaTabs;
  if (isEnabled) {
    notifySketchChanged();
  }
 else {
    preprocessingTask.cancel(false);
  }
}

public void reset(){
  myCheckingLevelSlider.setValue(Arrays.binarySearch(ModelCheckerSettings.CheckingLevel.values(),myModelCheckerSettings.getCheckingLevel()));
  myCheckStubsCheckBox.setSelected(myModelCheckerSettings.isCheckStubs());
  myCheckSpecificCheckBox.setSelected(myModelCheckerSettings.isIncludeAdditionalChecks());
}

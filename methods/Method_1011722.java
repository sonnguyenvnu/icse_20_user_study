public void apply() throws ConfigurationException {
  myModelCheckerSettings.setCheckingLevel(ModelCheckerSettings.CheckingLevel.values()[myCheckingLevelSlider.getValue()]);
  myModelCheckerSettings.setCheckStubs(myCheckStubsCheckBox.isSelected());
  myModelCheckerSettings.setIncludeAdditionalChecks(myCheckSpecificCheckBox.isSelected());
}

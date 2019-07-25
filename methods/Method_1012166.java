@Override public void apply(){
  myModelValidationSettings.setDisableCheckOpenAPI(!myCheckBoxOpenAPI.isSelected());
  myModelValidationSettings.setDisableTypeWasNotCalculated(!myCheckBoxTypeWasNotCalculated.isSelected());
}

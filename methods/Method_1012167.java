@Override public void reset(){
  myCheckBoxOpenAPI.setSelected(!myModelValidationSettings.isDisableCheckOpenAPI());
  myCheckBoxTypeWasNotCalculated.setSelected(!myModelValidationSettings.isDisableTypeWasNotCalculated());
}

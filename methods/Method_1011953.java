public void commit(){
  mySettings.setFieldPrefix(myFieldItem.getPrefix());
  mySettings.setFieldSuffix(myFieldItem.getSuffix());
  mySettings.setStaticFieldPrefix(myStaticField.getPrefix());
  mySettings.setStaticFieldSuffix(myStaticField.getSuffix());
  mySettings.setParameterPrefix(myParameter.getPrefix());
  mySettings.setParameterSuffix(myParameter.getSuffix());
  mySettings.setLocalVariablePrefix(myLocalVariable.getPrefix());
  mySettings.setLocalVariableSuffix(myLocalVariable.getSuffix());
  mySettings.setLineSeparator(getSelectedLineSeparator());
}

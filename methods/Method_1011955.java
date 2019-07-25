public void update(){
  myFieldItem.setSettings(mySettings.getFieldPrefix(),mySettings.getFieldSuffix());
  myStaticField.setSettings(mySettings.getStaticFieldPrefix(),mySettings.getStaticFieldSuffix());
  myParameter.setSettings(mySettings.getParameterPrefix(),mySettings.getParameterSuffix());
  myLocalVariable.setSettings(mySettings.getLocalVariablePrefix(),mySettings.getLocalVariableSuffix());
  for (  LineSeparatorOption option : LineSeparatorOption.values()) {
    if (Objects.equals(option.getSetting(),mySettings.getLineSeparator())) {
      myLineSeparatorComboBox.setSelectedItem(option);
    }
  }
}

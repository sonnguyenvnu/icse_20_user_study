public void apply() throws ConfigurationException {
  writeInternalErlangSdk((Sdk)internalErlangSdksComboBox.getSelectedItem());
  modified=false;
}

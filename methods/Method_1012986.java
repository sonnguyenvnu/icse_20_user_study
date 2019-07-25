@Override public void apply() throws ConfigurationException {
  final ZallySettings settings=ServiceManager.getService(ZallySettings.class);
  settings.setZallyUrl(zallyUrlTextField.getText());
  settings.setZtokenPath(ztokenPathTextField.getText());
}

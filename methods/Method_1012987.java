@Override public void reset(){
  final ZallySettings settings=ServiceManager.getService(ZallySettings.class);
  zallyUrlTextField.setText(settings.getZallyUrl());
  ztokenPathTextField.setText(settings.getZtokenPath());
}

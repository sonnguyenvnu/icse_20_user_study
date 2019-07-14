private void adjustLanguageControlsFor(LanguageConfig current){
  ignoreIdentifiersCheckbox.setEnabled(current.canIgnoreIdentifiers());
  ignoreLiteralsCheckbox.setEnabled(current.canIgnoreLiterals());
  ignoreAnnotationsCheckbox.setEnabled(current.canIgnoreAnnotations());
  ignoreUsingsCheckbox.setEnabled(current.canIgnoreUsings());
  extensionField.setText(current.extensions()[0]);
  boolean enableExtension=current.extensions()[0].isEmpty();
  extensionField.setEnabled(enableExtension);
  extensionLabel.setEnabled(enableExtension);
}
